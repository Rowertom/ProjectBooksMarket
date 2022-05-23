package ru.learnUp.market.dao.service;

import org.springframework.stereotype.Service;
import ru.learnUp.market.dao.entity.OrderReport;
import ru.learnUp.market.dao.repository.OrderReportRepository;

import java.util.List;

@Service
public class OrderReportService {

    private final OrderReportRepository reportRepository;

    public OrderReportService(OrderReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    public void create(OrderReport orderReport) {
        reportRepository.save(orderReport);
    }

    public List<OrderReport> getAll() {return reportRepository.findAll();}
}
