package by.intereson.ebookservice.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.CascadeType.REMOVE;
import static jakarta.persistence.FetchType.EAGER;
import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.SEQUENCE;


@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "SHOPPING_CARTS")
public class ShoppingCart {
    private static final String SEQ_NAME = "SHOPPING_CART_SEQ";

    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = SEQ_NAME)
    @SequenceGenerator(name = SEQ_NAME, sequenceName = SEQ_NAME,allocationSize = 1)
    private Long idShoppingCart;
    @Column(name = "SUM_PRICE")
    private Double sumPrice;

    @OneToMany(cascade = REMOVE,mappedBy = "shoppingCart",fetch = LAZY)
    private List<PartOfTheOrder> parts;

    @OneToOne(fetch = LAZY,mappedBy = "shoppingCart")
    private User user;
}
