package com.blockverse.issues.utils;

import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ModelMapperUtils extends ModelMapper {

    public ModelMapperUtils() {
        super();

        Converter<LocalDateTime, String> dateTimeConverter = new AbstractConverter<LocalDateTime, String>() {
            protected String convert(LocalDateTime source) {
                return source == null ? null : source.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME).replace("T", " ");
            }
        };
        this.addConverter(dateTimeConverter);

        Converter<String, LocalDateTime> stringToDateTimeConverter = new AbstractConverter<String, LocalDateTime>() {
            protected LocalDateTime convert(String source) {
                return source == null ? null : LocalDateTime.parse(source.replace(" ", "T"), DateTimeFormatter.ISO_LOCAL_DATE_TIME);
            }
        };
        this.addConverter(stringToDateTimeConverter);
    }

}