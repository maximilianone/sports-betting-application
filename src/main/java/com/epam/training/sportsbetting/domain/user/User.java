package com.epam.training.sportsbetting.domain.user;

import java.time.LocalDate;

public class User {
    private String name;
    private LocalDate dateOfBirth;
    private String email;
    private String password;
    private Access access;
    private boolean enabled;

    public enum Access {
        PLAYER,
        ADMIN
    }

    public User() {

    }

    public User(String name, LocalDate dateOfBirth, String email, String password, Access access, boolean enabled) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.password = password;
        this.access = access;
        this.enabled = enabled;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
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

    public Access getAccess() {
        return access;
    }

    public void setAccess(Access access) {
        this.access = access;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
