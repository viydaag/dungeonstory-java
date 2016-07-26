package com.dungeonstory.backend.data;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "AccessRole")
public class AccessRole extends AbstractTimestampEntity implements Serializable {

    private static final long serialVersionUID = 6653338145666517351L;

    @Column(name = "name", unique = true)
    private String name;

    @OneToMany(mappedBy = "role")
    private Set<User> users;

    public AccessRole() {

    }

    public AccessRole(String name) {
        this();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
