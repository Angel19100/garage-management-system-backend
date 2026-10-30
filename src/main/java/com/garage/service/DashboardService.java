package com.garage.service;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.garage.entity.JobCardStatus;
import com.garage.repository.VehicleRepository;
import com.garage.repository.MechanicRepository;
import com.garage.repository.JobCardRepository;
import com.garage.repository.PaymentRepository;

@Service
public class DashboardService {

    private final VehicleRepository vehicleRepository;
    private final MechanicRepository mechanicRepository;
    private final JobCardRepository jobCardRepository;
    private final PaymentRepository paymentRepository;
    private final BayService bayService;

    public DashboardService(
            VehicleRepository vehicleRepository,
            MechanicRepository mechanicRepository,
            JobCardRepository jobCardRepository,
            PaymentRepository paymentRepository,
            BayService bayService) {

        this.vehicleRepository = vehicleRepository;
        this.mechanicRepository = mechanicRepository;
        this.jobCardRepository = jobCardRepository;
        this.paymentRepository = paymentRepository;
        this.bayService = bayService;
    }

    public Map<String, Long> getDashboardSummary() {

        Map<String, Long> summary = new LinkedHashMap<>();

        summary.put("vehicles", vehicleRepository.count());

        summary.put("mechanics", mechanicRepository.count());

        summary.put("freeBays", (long) bayService.getFreeBays().size());

        summary.put("jobCardsToday",
                jobCardRepository.countByEntryDate(LocalDate.now()));

        summary.put("opened",
                jobCardRepository.countByStatus(JobCardStatus.OPENED));

        summary.put("payments", paymentRepository.count());

        return summary;
    }
}