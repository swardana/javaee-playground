package id.swhp.javaee.playground.application.jpa;

import java.util.UUID;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * JPA Convention to automatically convert UUID from Database (PostgreSQL) into
 * Java and vice versa.
 *
 * @author Sukma Wardana
 * @since 1.0
 */
@Converter(autoApply = true)
public class UUIDConverter implements AttributeConverter<UUID, UUID> {

    @Override
    public UUID convertToDatabaseColumn(final UUID attribute) {
        return (attribute == null) ? UUID.randomUUID() : attribute;
    }

    @Override
    public UUID convertToEntityAttribute(final UUID dbData) {
        return dbData;
    }

}
