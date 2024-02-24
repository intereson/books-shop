package by.intereson.ebookservice.repositories.order;

import by.intereson.ebookservice.entities.Order;
import by.intereson.ebookservice.enums.OrderStatus;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.math.BigDecimal;
import java.util.List;

import static by.intereson.ebookservice.entities.QOrder.order;

public class OrderRepositoryCustomImpl extends QuerydslRepositorySupport implements OrderRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;

    public OrderRepositoryCustomImpl(JPAQueryFactory jpaQueryFactory) {
        super(Order.class);
        this.jpaQueryFactory = jpaQueryFactory;
    }

    @Override
    public List<Order> findOrdersByStatusAndSumPriceMoreRequestPrice(OrderStatus orderStatus, BigDecimal price) {
        return jpaQueryFactory.selectFrom(order)
                .where(order.orderStatus.eq(orderStatus)
                        .and(order.sumPrice.gt(price)))
                .fetch();

    }
}
