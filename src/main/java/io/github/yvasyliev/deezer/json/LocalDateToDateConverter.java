package io.github.yvasyliev.deezer.json;

import com.fasterxml.jackson.databind.util.StdConverter;

import java.time.LocalDate;
import java.util.Date;

public class LocalDateToDateConverter extends StdConverter<LocalDate, Date> {
    @Override
    public Date convert(LocalDate value) {
        return java.sql.Date.valueOf(value);
    }
}
