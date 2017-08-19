package id.swhp.javaee.playground.application.jpa;

import java.sql.Date;
import java.time.LocalDate;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * JPA Convention to automatically convert LocalDate Java 8 Time API into
 * Database (PostgreSQL) Date and vice versa, should be able to handle null
 * value since some of column are not restricted.
 *
 * @author Sukma Wardana
 * @since 1.0
 */
@Converter(autoApply = true)
public class LocalDateConverter implements AttributeConverter<LocalDate, Date> {

    @Override
    public Date convertToDatabaseColumn(final LocalDate localDate) {
        return (localDate == null) ? null : Date.valueOf(localDate);
    }

    @Override
    public LocalDate convertToEntityAttribute(final Date dbData) {
        return (dbData == null) ? null : dbData.toLocalDate();
    }

}
