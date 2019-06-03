package org.caalpeva.report.csv;

import java.io.IOException;

/**
 * Interfaz genérica que se debe utilizar para realizar la escritura de pedidos en formato CSV  
 * @author Alberto
 */
public interface CsvReportWriter {
	public void writeLine(CsvReportLine csvReportLine) throws IOException;
	public void close() throws IOException;
}