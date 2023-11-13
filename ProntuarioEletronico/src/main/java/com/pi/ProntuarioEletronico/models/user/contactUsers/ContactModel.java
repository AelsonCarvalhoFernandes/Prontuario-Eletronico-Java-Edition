package com.pi.ProntuarioEletronico.models.user.contactUsers;

import java.time.LocalDateTime;

import com.pi.ProntuarioEletronico.models.user.UserModel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "contacts")
public class ContactModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(name = "street", length = 50)
    @NotBlank
    private String street;

    @Column(name = "number", length = 10)
    @NotBlank
    private String number;

    @Column(name = "neighborhood", length = 50)
    @NotBlank
    private String neighborhood;

    @Column(name = "city", length = 50)
    @NotBlank
    private String city;

    @Column(name = "telephone", length = 15)
    private String telephone;

    @Column(name = "cellphone", length = 15)
    @NotBlank
    private String cellPhone;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    private UserModel user;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public ContactModel() {

    }

    public ContactModel(String street, String number, String neighborhood, String city, String telephone,
            String cellPhone, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.street = street;
        this.number = number;
        this.neighborhood = neighborhood;
        this.city = city;
        this.telephone = telephone;
        this.cellPhone = cellPhone;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getCellPhone() {
        return cellPhone;
    }

    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }
}
