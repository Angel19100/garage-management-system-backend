
package com.garage.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.garage.entity.JobCard;
import com.garage.entity.JobCardStatus;
import com.garage.entity.Payment;
import com.garage.repository.JobCardRepository;
import com.garage.repository.PaymentRepository;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final JobCardRepository jobCardRepository;

    public PaymentService(
            PaymentRepository paymentRepository,
            JobCardRepository jobCardRepository) {
        this.paymentRepository = paymentRepository;
        this.jobCardRepository = jobCardRepository;
    }

    // E1: Save a payment for a finished job card
    public String savePayment(UUID jobCardId, Payment payment) {
        Optional<JobCard> optionalJobCard =
                jobCardRepository.findById(jobCardId);

        if (optionalJobCard.isEmpty()) {
            return "The job card with that id does not exist";
        }

        JobCard jobCard = optionalJobCard.get();

        if (jobCard.getStatus() != JobCardStatus.FINISHED) {
            return "Payments are only for FINISHED job cards";
        }

        payment.setId(null);
        payment.setJobCard(jobCard);
        payment.setPaidDate(LocalDate.now());

        paymentRepository.save(payment);

        return "Payment saved successfully";
    }

    // E2: Find payments for one vehicle, newest first
    public List<Payment> getPaymentsByVehiclePlateNumber(String plateNumber) {
        return paymentRepository.findPaymentsByVehiclePlateNumber(plateNumber);
    }

    // E2 extra convenience: retrieve all payments
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    // E3 Delete one payment
    public String deletePayment(UUID id) {
        Optional<Payment> optionalPayment = paymentRepository.findById(id);

        if (optionalPayment.isEmpty()) {
            return "The payment with that id does not exist";
        }

        paymentRepository.delete(optionalPayment.get());

        return "Payment deleted successfully";
    }
}
