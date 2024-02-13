package by.intereson.ebookservice.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "PART_OF_THE_ORDERS")
public class PartOfTheOrder {
    private static final String SEQ_NAME = "PART_OF_THE_ORDER_SEQ";

    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = SEQ_NAME)
    @SequenceGenerator(name = SEQ_NAME, sequenceName = SEQ_NAME, allocationSize = 1)
    private Long id;
    @Column(name = "SUM_PRICE")
    private Double sumPrice;
    @Column(name = "PRICE_BOOK")
    private Double price;
    @Column(name = "QUANTITY")
    private Integer quantity;
    @Column(name = "BOOK_NAME")
    private String bookName;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ORDER_ID")
    private Order order;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BOOK_ID")
    private Book book;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SHOPPING_CART_ID")
    private ShoppingCart shoppingCart;
}
