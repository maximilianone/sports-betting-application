package com.epam.training.sportsbetting.domain.user;

import java.time.LocalDate;

public class Admin extends User {
    public Admin(String name, LocalDate dateOfBirth, String email, String password, boolean enabled) {
        super(name, dateOfBirth, email, password, Access.ADMIN, enabled);
    }

    @Override
    public void setAccess(Access access){

    }
}
