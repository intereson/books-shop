package by.intereson.ebookservice.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

import static jakarta.persistence.FetchType.EAGER;
import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.SEQUENCE;


@Data
@Entity
@Builder
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "SHOPPING_CARTS")
public class ShoppingCart {
    private static final String SEQ_NAME="SHOPPING_CART_SEQ";

    @Id
    @GeneratedValue(strategy = SEQUENCE,generator = SEQ_NAME)
    @SequenceGenerator(name = SEQ_NAME,sequenceName = SEQ_NAME)
    private Long idShoppingCart;

    @ManyToMany(fetch = LAZY)
    @JoinTable(name = "SHOPPING_CART_BOOK",
            joinColumns = {@JoinColumn(name = "SHOPPING_CART_ID")},
            inverseJoinColumns = {@JoinColumn(name = "BOOK_ID")})
    private List<Book> booksByShoppingCart;

    @OneToOne(mappedBy = "shoppingCart")
    private User user;


}
