package com.augusto.util;

import static java.time.Instant.ofEpochMilli;
import static java.time.LocalDateTime.ofInstant;
import static java.time.ZoneId.systemDefault;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Objects;

public class DateUtil {
	/***
	 * 
	 * @param date string no formato yyyy-MM-dd
	 * @return uma instancia de java.util.Date;
	 * @throws ParseException
	 */
	public static Date parseDate(String date) throws ParseException {
		if (Objects.nonNull(date)) {
			return new SimpleDateFormat("yyyy-MM-dd").parse(date);
		}
		return null;
	}
	
	public static LocalDate stringDateToLocalDate(String date) {
		return LocalDate.parse(date);
	}
	
	
	public static Date locaDateToDate(LocalDate attribute) {
		return Date.from(attribute.atStartOfDay(systemDefault()).toInstant());
	}

	public static LocalDate dateToLocalDate(Date dbData) {
		if(Objects.nonNull(dbData)) {
			return ofInstant(ofEpochMilli(dbData.getTime()), systemDefault()).toLocalDate();			
		}
		return null;
	}
}
