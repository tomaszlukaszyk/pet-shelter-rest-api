package com.codecool.petshelter.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;

@Entity
public class Adoption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String adopterName;

    @Column(nullable = false)
    private String adopterAddress;

    @Column(nullable = false)
    private String adopterPhoneNumber;

    @OneToOne
    @JsonManagedReference
    private Pet adoptedPet;

    public Adoption() {
    }

    public Adoption(String adopterName, String adopterAddress, String adopterPhoneNumber) {
        this.adopterName = adopterName;
        this.adopterAddress = adopterAddress;
        this.adopterPhoneNumber = adopterPhoneNumber;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAdopterName() {
        return adopterName;
    }

    public void setAdopterName(String adopterName) {
        this.adopterName = adopterName;
    }

    public String getAdopterAddress() {
        return adopterAddress;
    }

    public void setAdopterAddress(String adopterAddress) {
        this.adopterAddress = adopterAddress;
    }

    public String getAdopterPhoneNumber() {
        return adopterPhoneNumber;
    }

    public void setAdopterPhoneNumber(String adopterPhoneNumber) {
        this.adopterPhoneNumber = adopterPhoneNumber;
    }

    public Pet getAdoptedPet() {
        return adoptedPet;
    }

    public void setAdoptedPet(Pet adoptedPet) {
        this.adoptedPet = adoptedPet;
        adoptedPet.setAdoption(this);
    }
}
