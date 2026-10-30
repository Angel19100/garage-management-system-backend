package com.garage.entity;
 import java.time.LocalDate;
 import java.util.UUID;
 import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@Entity
@Table(name = "payment")
public class Payment {
    @Id 
    @GeneratedValue(strategy= GenerationType.UUID) 
    private UUID id;

    private String paymentMethod;

    private String note;

    private int amount;

    private LocalDate paidDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "job_card" , nullable = false)
    private JobCard jobCard;

    public Payment(){

    }

    public UUID getId(){
        return id;
    }

    public void setId(UUID id){
        this.id = id;
    }

    public String getPaymentMethod(){
        return paymentMethod;
    }

    public void setPaymentMethos(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getAmount() {
        return amount;
 
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public LocalDate getPaidDate() {
        return paidDate;
    }

    public void setPaidDate(LocalDate paidDate) {
        this.paidDate = paidDate;
    }

    public JobCard getJobCard(){
        return jobCard;
    }

    public void setJobCard(JobCard jobCard) {
        this.jobCard = jobCard;
    }

}
