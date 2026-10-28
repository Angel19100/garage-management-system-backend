package com.garage.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.List;
import java.util.UUID;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity 
@Table(name = "mechanic")

public class Mechanic {

    @Id 
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String firstName;

    private String lastName;

    private String phone;

    @ManyToOne 
    @JoinColumn (name = "bay_id")
    private Bay bay;

    @ManyToMany 
    @JoinTable(name = "mechanic_speciality",
        joinColumns = @JoinColumn(name = "mechanic_id"),
        inverseJoinColumns = @JoinColumn(name = "speciality_id")     
    )

    @JsonIgnore
    private List<Speciality> specialities;

    public Mechanic(){

    }

    public UUID getId(){
        return id;
    }

    public void setId(UUID id){
        this.id = id;
    }

    public String getFirstName(){
        return firstName;
    }

    public void setFirstName(String firstName){
        this.firstName = firstName;
    }

    public String getLastName(){
        return lastName;
    }

    public void setLastName(String lastName){
        this.lastName = lastName;

    }

    public String getPhone(){
        return phone;
    }

    public void setPhone(String phone){
        this.phone = phone; 
    }

    public Bay getBay(){
        return bay;
    }

    public void setBay(Bay bay){
        this.bay = bay;
    }

    public List<Speciality> getSpecialities(){
        return specialities;
    }

    public void setSpecialities(List<Speciality> specialities){
        this.specialities = specialities;
    }

}
