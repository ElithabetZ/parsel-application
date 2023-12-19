package com.pingwit.parsel.entity.criteria;

import com.pingwit.parsel.entity.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import com.pingwit.parsel.entity.enums.Payment;
import com.pingwit.parsel.entity.enums.Type;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderCriteria {
    private List<Payment> payment;
    private List<Status> status;
    private List<Type> type;
}
