package com.dungeonstory.backend.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "User")
@NamedQueries({ @NamedQuery(name = User.findByUsername, query = "SELECT u FROM User u WHERE u.username = :username"),
        @NamedQuery(name = User.updatePassword, query = "Update User u SET u.password = :password WHERE u.id = :userId") })
public class User
        extends AbstractTimestampEntity {

    private static final long serialVersionUID = -8735932805533401960L;

    public static final String findByUsername = "User.findByUsername";
    public static final String updatePassword = "User.updatePassword";

    public enum UserStatus {
        WAITING_FOR_APPROBATION("En attente"), ACTIVE("Actif"), INACTIVE("Désactivé");

        private String value;

        private UserStatus(String name) {
            this.value = name;
        }

        public String getValue() {
            return value;
        }

        @Override
        public String toString() {
            return getValue();
        }
    };

    @NotNull
    @Size(min = 1)
    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @NotNull
    @Column(name = "password", nullable = false)
    private String password;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "role", nullable = false)
    private AccessRole role;

    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Size(min = 1, max = 255)
    @Pattern(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}$", message = "Le courriel doit suivre le format \"aaaa@domaine.xxx\"")
    @Column(name = "email", nullable = false)
    private String email;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "status", nullable = false)
    private UserStatus status;

    @OneToOne(mappedBy = "user", orphanRemoval = true, fetch = FetchType.EAGER)
    private Character character;

    @OneToOne
    @JoinColumn(name = "adventureId")
    private Adventure adventure;

    public User() {
        super();
    }

    public User(String username, String password, AccessRole role, String name, String email, UserStatus status) {
        this();
        this.username = username;
        this.password = password;
        this.role = role;
        this.name = name;
        this.email = email;
        this.status = status;
    }

    public User(String username) {
        this();
        this.username = username;
        this.password = "";
        this.status = UserStatus.ACTIVE;
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

    public AccessRole getRole() {
        return role;
    }

    public void setRole(AccessRole role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public Character getCharacter() {
        return character;
    }

    public void setCharacter(Character character) {
        this.character = character;
    }

    public Adventure getAdventure() {
        return adventure;
    }

    public void setAdventure(Adventure adventure) {
        this.adventure = adventure;
    }

    public boolean isActive() {
        return getStatus() == UserStatus.ACTIVE;
    }

    public boolean isAdmin() {
        return getRole() == AccessRole.ADMIN;
    }

    public boolean isInactive() {
        return getStatus() == UserStatus.INACTIVE;
    }

    @Override
    @PrePersist
    protected void onCreate() {
        super.onCreate();
        this.status = UserStatus.WAITING_FOR_APPROBATION;
    }
}
