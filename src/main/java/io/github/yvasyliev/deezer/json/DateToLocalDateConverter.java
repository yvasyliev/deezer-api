package io.github.yvasyliev.deezer.json;

import com.fasterxml.jackson.databind.util.StdConverter;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class DateToLocalDateConverter extends StdConverter<Date, LocalDate> {
    @Override
    public LocalDate convert(Date value) {
        return value
                .toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }
}
