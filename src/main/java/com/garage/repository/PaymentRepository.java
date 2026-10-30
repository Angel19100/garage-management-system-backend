package com.garage.repository;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.garage.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, UUID> {  @Query("SELECT p FROM Payment p " +
           "WHERE p.jobCard.vehicle.plateNumber = :plateNumber " +
           "ORDER BY p.paidDate DESC")
    List<Payment> findPaymentsByVehiclePlateNumber(
            @Param("plateNumber") String plateNumber);

    boolean existsByJobCardId(UUID jobCardId);
}

    

