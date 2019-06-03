package org.caalpeva.report.csv;

import java.util.Iterator;

/**
 * Interfaz genérica que se debe utilizar para realizar la lectura de pedidos desde formato CSV  
 * @author Alberto
 */
public interface CsvReportReader {
	//public List<CsvReportLine> parse();
	public Iterator<CsvReportLine> iterator();
}