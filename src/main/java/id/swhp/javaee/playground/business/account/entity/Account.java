package id.swhp.javaee.playground.business.account.entity;

import java.time.Instant;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Sukma Wardana
 * @since 1.0
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "account", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"username", "email"})
})
public class Account {

    @Id
    @GeneratedValue(generator = "account_id_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "account_id_seq", sequenceName = "account_id_seq", allocationSize = 1)
    private Long id;

    @NotNull
    @Size(min = 1, max = 100)
    private String email;
    
    @NotNull
    @Size(max = 100)
    private String username;

    @NotNull
    private String password;
    
    @Size(max = 100)
    private String avatar;
    
    @Column(name = "birth_date")
    private LocalDate birthDate;
    
    @Enumerated(EnumType.STRING)
    private Sex sex;

    @NotNull
    private boolean active;

    @NotNull
    private Instant created;

    @NotNull
    private Instant updated;

    public Account() {
    }

    public Account(String email, String username, String password) {
        this.email = email;
        this.username = username;
        this.password = password;
    }
    
    @PrePersist
    public void generateInformation() {
        this.active = false;
        this.created = Instant.now();
        this.updated = Instant.now();
        if (this.sex == null) {
            this.sex = Sex.MALE;
        }
    }

    @PreUpdate
    public void updateLastModify() {
        this.updated = Instant.now();
    }

    @Override
    public String toString() {
        return "Account{" + "email=" + email + ", username=" + username + '}';
    }

}
