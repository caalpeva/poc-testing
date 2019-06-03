package org.caalpeva.report.csv.opencsv;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.opencsv.bean.AbstractBeanField;
import com.opencsv.exceptions.CsvConstraintViolationException;
import com.opencsv.exceptions.CsvDataTypeMismatchException;

/**
 * Clase encargada de convertir una String que representa a una fecha al tipo LocalDate
 * @author Alberto
 */
public class LocalDateConverter extends AbstractBeanField<LocalDate> {
	
    @Override
    protected LocalDate convert(String text) throws CsvDataTypeMismatchException, CsvConstraintViolationException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy");
        return LocalDate.parse(text.trim(), formatter);
    }
}