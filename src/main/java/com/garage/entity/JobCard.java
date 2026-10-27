package com.garage.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.UUID;

@Entity 
@Table(name = "job_card")

public class JobCard {

    @Id 
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    
    private LocalDate entryDate;

    @Enumerated (EnumType.STRING)
    private JobCardStatus status;
    
    private String workToDo;

    @ManyToOne 
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;

    @ManyToOne 
    @JoinColumn(name = "mechanic_ id")
    private Mechanic mechanic;

    public JobCard(){
 } 

    public UUID getId(){
        return id;
    }

    public LocalDate getEntryDate(){
        return entryDate;
    }

    public void setEntryDate(LocalDate entryDate){
        this.entryDate = entryDate;
    }

    public JobCardStatus getStatus(){
        return status;
    }

    public void setStatus(JobCardStatus status){
        this.status = status;
    }

    public String getWorkToDo(){
        return workToDo;
    }

    public void setWorkToDo(String workToDo){
        this.workToDo = workToDo;
    }

    public Vehicle getVehicle(){
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle){
        this.vehicle = vehicle;
    }

    public Mechanic getMechanic(){
        return mechanic;
    }

    public void setMechanic(Mechanic mechanic){
        this.mechanic = mechanic;
    }

}
