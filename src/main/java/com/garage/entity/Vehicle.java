package com.garage.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.UUID;

@Entity 
@Table(name = "vehicle")

public class Vehicle {
    @Id 
    @GeneratedValue (strategy = GenerationType.UUID)
    private UUID id;

    private String make;

    private String model;

    @Column(unique = true)
    private String plateNumber;

    private String ownerName;
    
    private String vehicleType;

    private Vehicle(){    
    }

    public UUID getId(){
        return id;
    }

    public void setId(UUID id){
        this.id = id;

    }
    
    public String getMake(){
        return make;
    }

    public void setMake(String make){
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model){
        this.model = model;
    }

    public String getPlateNumber() {
        return plateNumber;
    }
    
    public void setPlateNumber(String plateNumber){
        this.plateNumber = plateNumber;
    }

    public String getOwnerName(){
        return ownerName;
    }
    
    public void setOwnerName(String ownerName){
        this.ownerName = ownerName;
    }

    public String getVehicleType(){
        return vehicleType;
    }

    public void setVehicleType(String vehicleType){
        this.vehicleType = vehicleType;
    }

}
