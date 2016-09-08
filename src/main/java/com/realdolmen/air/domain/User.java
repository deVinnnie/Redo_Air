package com.realdolmen.air.domain;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@NamedQueries({
    @NamedQuery(name = User.FIND_BY_EMAIL, query = "SELECT u FROM User u WHERE u.email = :email")
})
public abstract class User extends AbstractEntity{
    public static final String FIND_BY_EMAIL = "User.findByEmail";

    @Column(unique = true)
    @Email
    private String email;

    /**
     * Hashed password of the user.
     * Should be set by the service creating the user!
     */
    @NotBlank
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    public User(){
        this(Role.NONE);
    }

    public User(Role role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
