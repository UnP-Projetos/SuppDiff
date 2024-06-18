package com.suppdiff.application.DTO;

import java.util.Date;

import com.suppdiff.application.enums.TypeUser;
import com.suppdiff.domain.entities.Person;

public class UserDto extends Person {
    private TypeUser typeUser;

    public UserDto(String name, String email, String cpf, String phone, Date birthData, String password, TypeUser typeUser) {
        this.setName(name);
        this.setEmail(email);
        this.setCpf(cpf);
        this.setPhone(phone);
        this.setBirthDate(birthData);
        this.setPassword(password);
        this.setTypeUser(typeUser);
    }

    public TypeUser getTypeUser() {
        return typeUser;
    }

    public void setTypeUser(TypeUser typeUser) {
        this.typeUser = typeUser;
    }
}
