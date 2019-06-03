package org.caalpeva.report.csv.opencsv;

import java.time.LocalDate;

import org.caalpeva.report.csv.CsvBaseLine;

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
        return LocalDate.parse(text.trim(), CsvBaseLine.formatter);
    }
}