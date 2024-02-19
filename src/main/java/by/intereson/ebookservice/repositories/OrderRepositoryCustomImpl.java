package by.intereson.ebookservice.repositories;

import by.intereson.ebookservice.entities.Order;
import by.intereson.ebookservice.enums.OrderStatus;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class OrderRepositoryCustomImpl extends QuerydslRepositorySupport implements OrderRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;

    public OrderRepositoryCustomImpl(JPAQueryFactory jpaQueryFactory) {
        super(Order.class);
        this.jpaQueryFactory = jpaQueryFactory;
    }

    @Override
    public List<Order> findOrdersByStatusAndSumPriceMoreHundred(OrderStatus orderStatus) {
//        QOrder
//        jpaQueryFactory.select(order);
        return null;
    }
}
