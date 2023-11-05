package com.rezende.commerce.dto;

import com.rezende.commerce.entities.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UserDTO {

    private Long id;
    private String name;
    private String email;
    private String phone;
    private LocalDate birthDate;

    private final List<String> roles = new ArrayList<>();

    public UserDTO() {}

    public UserDTO(Long id, String name, String email, String phone, LocalDate birthDate) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.birthDate = birthDate;
    }

    public UserDTO(User entity) {
        id = entity.getId();
        name = entity.getName();
        email = entity.getUsername();
        phone = entity.getPhone();
        birthDate = entity.getBirthDate();
        entity.getAuthorities().forEach(x -> roles.add(x.getAuthority()));
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public List<String> getRoles() {
        return roles;
    }
}
