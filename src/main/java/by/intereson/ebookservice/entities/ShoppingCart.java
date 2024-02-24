package by.intereson.ebookservice.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

import static by.intereson.ebookservice.utils.Constants.*;
import static jakarta.persistence.CascadeType.REMOVE;
import static jakarta.persistence.FetchType.EAGER;
import static jakarta.persistence.GenerationType.SEQUENCE;


@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = SHOPPING_CARTS)
public class ShoppingCart {
    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = SHOPPING_CART_SEQ_NAME)
    @SequenceGenerator(name = SHOPPING_CART_SEQ_NAME, sequenceName = SHOPPING_CART_SEQ_NAME,
            allocationSize = 1, initialValue = 100)
    private Long id;

    @Column(name = SUM_PRICE)
    private BigDecimal sumPrice;

    @OneToMany(cascade = REMOVE, fetch = EAGER, mappedBy = "shoppingCart")
    private List<OrderDetail> details;

    @OneToOne(mappedBy = "shoppingCart")
    private User user;
}
