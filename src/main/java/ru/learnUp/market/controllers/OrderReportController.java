package ru.learnUp.market.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.learnUp.market.dao.entity.Customer;
import ru.learnUp.market.dao.entity.OrderReport;
import ru.learnUp.market.dao.service.OrderReportService;
import ru.learnUp.market.dao.service.UserService;
import ru.learnUp.market.dao.user.User;
import ru.learnUp.market.messagesService.FileDirectoryProvider;
import ru.learnUp.market.messagesService.MessageProducer;


import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@RestController
@RequestMapping("/report")
public class OrderReportController {

    private final OrderReportService reportService;
    private final MessageProducer messageProducer;
    private final FileDirectoryProvider fileDirectoryProvider;

    public OrderReportController(
                                 OrderReportService reportService,
                                 MessageProducer messageProducer,
                                 FileDirectoryProvider fileDirectoryProvider
    ) {
        this.reportService = reportService;
        this.messageProducer = messageProducer;
        this.fileDirectoryProvider = fileDirectoryProvider;
    }

    @GetMapping
    public String getReport() {
        Calendar startDate = Calendar.getInstance();
        startDate.add(Calendar.DAY_OF_MONTH, -30);

        List<OrderReport> orderReports = reportService.getAll().stream()
                .filter(orderReport -> orderReport.getCalendar().after(startDate))
                .collect(Collectors.toList());

        StringBuilder result = new StringBuilder("");
        orderReports.stream()
                .forEach(orderReport -> result.append(orderReport.toString()));

        messageProducer.sendMessage(result.toString());

        DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        StringBuilder fileName = new StringBuilder("_"
        + df.format(Calendar.getInstance().getTime()));

        try {
            fileDirectoryProvider.writeString(fileName.toString(), result.toString());
        } catch (IOException e) {
            log.error("Can't write to file...");
        }
        return fileName.toString();
    }
}
