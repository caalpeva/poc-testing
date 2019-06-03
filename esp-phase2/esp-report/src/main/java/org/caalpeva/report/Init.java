package org.caalpeva.report;

import java.io.File;
import java.io.FileReader;

import org.caalpeva.commons.utils.DateUtils;
import org.caalpeva.report.csv.opencsv.OpenCsvReportReader;
import org.caalpeva.report.services.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

/**
 * Clase encargada de la validación de los argumentos de la línea de comandos
 * y de iniciar el contexto de aplicación mediante el framework spring
 * @author Alberto
 */
@Component
public class Init {

	//F:/ALBERTO/[EMPRESAS]/Otras/EsPublico/RegistroVentas1 - con retorno.csv"
	
	@Autowired
	private DataService dataService;
	
	public static void main(String[] args) {
		if (args.length != 1) {
			System.err.println("Syntax error. Format: filepath");
			System.exit(1);
		}

		ApplicationContext context = new ClassPathXmlApplicationContext(new String[] {
				"spring-context.xml", "spring-persistence.xml"});
		Init init = context.getBean(Init.class);
		
		File file = new File(args[0]);
		if (init.validate(file)) {
			init.start(file);
		}
	}
	
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
	
	public void start(File file) {
		long startTimeInMillis = System.currentTimeMillis();
		try {
			// Importar csv a bbdd
			dataService.importOrders(new OpenCsvReportReader(new FileReader(file)));
			// Exportar bbdd a csv ordenado por id
			File file2 = new File(file.getParentFile().getPath(), "sorted_" + file.getName());
			dataService.sortOrdersAndExport(file2.getPath());
			// Realizar un resumen de queries
			System.out.println("Time elapsed: " + DateUtils.formatElapsedTime(System.currentTimeMillis() - startTimeInMillis, true, true));
		} catch(Exception e) {
			
		}
	}
}