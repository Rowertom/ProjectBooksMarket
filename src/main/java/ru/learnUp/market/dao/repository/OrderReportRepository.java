package ru.learnUp.market.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.learnUp.market.dao.entity.OrderReport;

@Repository
public interface OrderReportRepository extends JpaRepository<OrderReport, Long> {
}
