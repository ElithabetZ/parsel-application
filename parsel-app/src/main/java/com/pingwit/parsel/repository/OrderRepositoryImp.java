package com.pingwit.parsel.repository;

import com.pingwit.parsel.entity.Order;
import com.pingwit.parsel.entity.criteria.OrderCriteria;
import com.pingwit.parsel.entity.enums.Payment;
import com.pingwit.parsel.entity.enums.Status;
import com.pingwit.parsel.entity.enums.Type;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryImp implements OrderCustomRepository {

    private final EntityManager entityManager;

    @Override
    public List<Order> findAllByCriteria(Long userId, OrderCriteria criteria) {
        String query = "SELECT o.id, id_sender, id_receiver, id_route, id_transaction, type FROM public.order o ";
        String whereClause = "WHERE id_sender = " + userId;
        List<Payment> payment = criteria.getPayment();
        List<Status> status = criteria.getStatus();
        List<Type> type = criteria.getType();

        if (payment != null) {
            query += "JOIN transaction t ON o.id_transaction = t.id ";
            String paymentParam = payment.stream()
                    .map(Enum::toString).collect(Collectors.joining("','", "('", "')"));
            whereClause += " AND t.payment IN " + paymentParam;
        }

        if (status != null) {
            query += "JOIN route r ON o.id_route = r.id ";
            String statusParam = status.stream()
                    .map(Enum::toString).collect(Collectors.joining("','", "('", "')"));
            whereClause += " AND r.status IN " + statusParam;
        }

        if (type != null) {
            String typeParam = type.stream()
                    .map(String::valueOf).collect(Collectors.joining("','", "('", "')"));
            whereClause += " AND type IN " + typeParam;
        }

        query = query.concat(whereClause);


        return entityManager.createNativeQuery(query, Order.class).getResultList();
    }
}
