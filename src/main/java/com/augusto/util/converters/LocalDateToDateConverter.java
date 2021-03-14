package com.augusto.util.converters;

import static java.time.Instant.ofEpochMilli;
import static java.time.LocalDateTime.ofInstant;
import static java.time.ZoneId.systemDefault;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.AttributeConverter;

public class LocalDateToDateConverter implements AttributeConverter<LocalDate, Date> {

	@Override
	public Date convertToDatabaseColumn(LocalDate attribute) {
		return Date.from(attribute.atStartOfDay(systemDefault()).toInstant());
	}

	@Override
	public LocalDate convertToEntityAttribute(Date dbData) {
		return ofInstant(ofEpochMilli(dbData.getTime()), systemDefault()).toLocalDate();
	}

}
