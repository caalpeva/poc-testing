package org.caalpeva.report;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import org.caalpeva.commons.utils.DateUtils;
import org.caalpeva.report.csv.opencsv.OpenCsvReportReader;
import org.caalpeva.report.csv.opencsv.OpenCsvReportWriter;
import org.caalpeva.report.services.DataReportService;
import org.caalpeva.report.utils.ConsoleUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

/**
 * Clase encargada de la validación de los argumentos de la línea de comandos
 * y de iniciar el contexto de aplicación mediante el framework spring
 * @author Alberto
 */
@Component
public class Init {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	//F:/ALBERTO/[EMPRESAS]/Otras/EsPublico/RegistroVentas1 - con retorno.csv"
	
	@Autowired
	private DataReportService dataService;
	
	/**
	 * Método de arranque de la aplicación
	 * @param args
	 */
	public static void main(String[] args) {
		if (args.length != 1) {
			System.err.println("Syntax error. Format: <filepath>");
			System.exit(1);
		}

		ConfigurableApplicationContext  context = new ClassPathXmlApplicationContext(new String[] {
				"spring-context.xml", "spring-persistence.xml"});
		Init init = context.getBean(Init.class);
		
		File file = new File(args[0]);
		if (init.validate(file)) {
			init.start(file);
		}
		
		context.close();
	}
	
	/**
	 * Método encargado de validar el fichero especificado
	 * @param file
	 * @return
	 */
	public boolean validate(File file) {
		if (!file.exists()) {
			System.err.println("File does not exist");
			return false;
		}
		
		if (file.isDirectory()) {
			System.err.println("The path belongs to a directory");
			return false;
		}
		
		if (!file.canRead()) {
			System.err.println("Unable to read the file");
			return false;
		}
		
		return true;
	}
	
	/**
	 * Método encargado de delegar adecuadamente las tareas de la aplicación
	 * @param file
	 */
	public void start(File file) {
		try {
			// Importar csv a bbdd
			System.out.println("Importing data..");
			System.out.println("Wait a moment this may take a few minutes.");
			
			long importstartTime = System.currentTimeMillis();
			dataService.importOrders(new OpenCsvReportReader(new FileReader(file)));
			long importEndTime = System.currentTimeMillis();
			long importElapsedTime = System.currentTimeMillis() - importstartTime;
			System.out.println(String.format("Elapsed time in the import of data: %s",
					DateUtils.formatElapsedTime(importElapsedTime)));
			
			// Exportar bbdd a csv ordenado por id
			System.out.println("The data import was finished.");
			System.out.println("Exporting data..");
			System.out.println("Wait a moment this may take a few minutes.");
			
			// Se exporta en el mismo directorio del fichero importado
			// TODO: Para más seguridad sería adecuado añadir algún timestamp
			// o generación de números aleatorios en el nombre del fichero exportado
			File exportFile = new File(file.getParentFile().getPath(), "sorted_" + file.getName());
			dataService.sortOrdersAndExport(new OpenCsvReportWriter(new FileWriter(exportFile.getPath())));
			long exportElapsedTime = importEndTime - System.currentTimeMillis();
			System.out.println(String.format("Elapsed time in the export of data: %s",
					DateUtils.formatElapsedTime(exportElapsedTime)));
			
			// Realizar un resumen de queries
			System.out.println("Generating reports...");
			ConsoleUtils.waitForAnyPressedKeyToContinue();
			dataService.generateOrderReports();
		} catch(Exception e) {
			System.err.println("An error occurred in the application. Contact your administrator.");
			logger.error(e.getMessage());
			e.printStackTrace();
		}
	}
}