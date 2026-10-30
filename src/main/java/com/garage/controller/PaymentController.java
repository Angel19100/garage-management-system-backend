
package com.garage.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.garage.entity.Payment;
import com.garage.service.PaymentService;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    // E1 Save a payment
    @PostMapping("/save")
    public ResponseEntity<String> savePayment(
            @RequestParam UUID jobCardId,
            @RequestBody Payment payment) {

        String result = paymentService.savePayment(jobCardId, payment);

        if (result.equals("The job card with that id does not exist")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
        }

        if (result.equals("Payments are only for FINISHED job cards")) {
            return ResponseEntity.badRequest().body(result);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    // E2 Get payments for a particular vehicle
    @GetMapping("/vehicle/{plateNumber}")
    public ResponseEntity<List<Payment>> getPaymentsByVehicle(
            @PathVariable String plateNumber) {
        return ResponseEntity.ok(
                paymentService.getPaymentsByVehiclePlateNumber(plateNumber));
    }

    // Get all payments across all job cards
    @GetMapping("/all")
    public ResponseEntity<List<Payment>> getAllPayments() {
        return ResponseEntity.ok(paymentService.getAllPayments());
    }

    // E3 Delete a payment
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletePayment(@PathVariable UUID id) {
        String result = paymentService.deletePayment(id);

        if (result.equals("The payment with that id does not exist")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
        }

        return ResponseEntity.ok(result);
    }
}
