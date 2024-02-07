package by.intereson.ebookservice.entities;

import jakarta.persistence.*;
import lombok.*;

import static jakarta.persistence.FetchType.EAGER;
import static jakarta.persistence.GenerationType.SEQUENCE;

@Data
@Entity
@Builder
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "PART_OF_THE_ORDERS")
public class PartOfTheOrder {
    private static final String SEQ_NAME = "PART_OF_THE_ORDER_SEQ";

    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = SEQ_NAME)
    @SequenceGenerator(name = SEQ_NAME, sequenceName = SEQ_NAME)
    private Long id;
    @Column(name = "SUM_PRICE")
    private Double sumPrice;
    @Column(name = "QUANTITY")
    private Integer quantity;
    @ManyToOne
    @JoinColumn(name = "ORDER_ID")
    private Order order;
    @ManyToOne(fetch = EAGER)
    @JoinColumn(name = "BOOK_ID")
    private Book book;

}
