package ru.learnUp.market.view;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.learnUp.market.dao.entity.BooksOrder;

import java.sql.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerView {

private Long customerId;
private String customerName;
private String customerSurname;
private Date birthDate;


}
