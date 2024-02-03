package by.intereson.ebookservice.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

import static by.intereson.ebookservice.utils.Constance.*;
import static jakarta.persistence.GenerationType.AUTO;


@Data
@Entity
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Table(name = SHOPPING_CART)
public class ShoppingCart {
    @Id
    @GeneratedValue(strategy = AUTO)
    private long idShoppingCart;

    @ManyToMany
    @JoinTable(name = SHOPPING_CART_BOOK,
            joinColumns = {@JoinColumn(name = SHOPPING_CART_ID)},
            inverseJoinColumns = {@JoinColumn(name = BOOK_ID)})
    private List<Book> booksByShoppingCart;

    @OneToOne(mappedBy = "shoppingCart")
    private User user;

    @OneToOne
    @JoinColumn(name = ORDER_ID)
    private Order order;
}
