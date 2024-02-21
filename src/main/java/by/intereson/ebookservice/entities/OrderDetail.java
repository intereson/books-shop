package by.intereson.ebookservice.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

import static by.intereson.ebookservice.utils.Constants.*;
import static jakarta.persistence.GenerationType.SEQUENCE;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = ORDER_DETAILS)
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = ORDER_DETAIL_SEQ_NAME)
    @SequenceGenerator(name = ORDER_DETAIL_SEQ_NAME, sequenceName = ORDER_DETAIL_SEQ_NAME,
            allocationSize = 1, initialValue = 100)
    private Long id;

    @Column(name = SUM_PRICE)
    private BigDecimal sumPrice;

    @Column(name = BOOK_PRICE)
    private BigDecimal price;

    @Column(name = QUANTITY, nullable = false)
    private Integer quantity;

    @Column(name = BOOK_NAME, nullable = false)
    private String bookName;

    @ManyToOne
    @JoinColumn(name = ORDER_ID)
    private Order order;

    @ManyToOne
    @JoinColumn(name = BOOK_ID, nullable = false)
    private Book book;

    @ManyToOne
    @JoinColumn(name = SHOPPING_CART_ID)
    private ShoppingCart shoppingCart;
}
