package id.swhp.javaee.playground.business.account.entity;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
@NamedQueries({
    @NamedQuery(name = Account.FIND_BY_EMAIL, query = "select a from Account a where a.email = :email"),
    @NamedQuery(name = Account.FIND_BY_USERNAME, query = "select a from Account a where a.username = :username")
})
public class Account {
    
    public static final String FIND_BY_EMAIL = "Account.findByEmail";
    public static final String FIND_BY_USERNAME = "Account.findByUsername";

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id")
    private Role role;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Instant getCreated() {
        return created;
    }

    public void setCreated(Instant created) {
        this.created = created;
    }

    public Instant getUpdated() {
        return updated;
    }

    public void setUpdated(Instant updated) {
        this.updated = updated;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Account account = (Account) obj;
        return Objects.equals(id, account.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Account{" + "email=" + email + ", username=" + username + '}';
    }

}
