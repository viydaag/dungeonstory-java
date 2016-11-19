package com.dungeonstory.backend.data;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "AccessRole")
public class AccessRole extends AbstractTimestampEntity implements Serializable {

    private static final long serialVersionUID = 6653338145666517351L;
    
    public enum RoleType {
        ADMIN,
        MODERATOR,
        PLAYER
    }

    @NotNull
    @Column(name = "name", nullable = false, unique = true)
    private String name;
    
    @NotNull
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "roleType", nullable = false)
    private RoleType type;

    @OneToMany(mappedBy = "role")
    private Set<User> users;

    public AccessRole() {
        super();
    }

    public AccessRole(String name, RoleType type) {
        this();
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public RoleType getType() {
        return type;
    }

    public void setType(RoleType type) {
        this.type = type;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

}
