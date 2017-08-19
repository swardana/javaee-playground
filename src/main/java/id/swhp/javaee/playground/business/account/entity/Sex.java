package id.swhp.javaee.playground.business.account.entity;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;

/**
 *
 * @author Sukma Wardana
 * @since 1.0
 */
@XmlEnum
public enum Sex {
    @XmlEnumValue(value = "male")
    MALE,
    @XmlEnumValue(value = "female")
    FEMALE
}
