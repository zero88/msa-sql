package io.github.zero88.msa.sql.converter;

import java.time.Duration;
import java.time.format.DateTimeParseException;
import java.util.Objects;

import org.jooq.Converter;

import io.github.zero88.msa.sql.DatabaseException;

public final class DurationConverter implements Converter<String, Duration> {

    @Override
    public Duration from(String databaseObject) {
        if (Objects.isNull(databaseObject)) {
            return null;
        }
        try {
            return Duration.parse(databaseObject);
        } catch (DateTimeParseException e) {
            throw new DatabaseException("Wrong Duration data format: " + databaseObject, e);
        }
    }

    @Override
    public String to(Duration userObject) {
        if (Objects.isNull(userObject)) {
            return null;
        }
        return userObject.toString();
    }

    @Override
    public Class<String> fromType() { return String.class; }

    @Override
    public Class<Duration> toType() { return Duration.class; }

}
