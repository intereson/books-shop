package by.intereson.ebookservice.entities;

import by.intereson.ebookservice.enums.StatusOrder;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

import static by.intereson.ebookservice.utils.Constance.*;
import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.FetchType.EAGER;
import static jakarta.persistence.GenerationType.AUTO;


@Data
@Entity
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Table(name = ORDER)
public class Order {
    @Id
    @GeneratedValue(strategy = AUTO)
    private long id;
    @Column(name = TOTAL_PRICE)
    private double totalPrice;
    @Column(name = CREATION_DATE_TIME)
    private LocalDateTime creationDateTime;
    @Column(name = UPDATE_DATE_TIME)
    private LocalDateTime updateDateTime;
    @Enumerated(STRING)
    private StatusOrder statusOrder;

    @ManyToMany(fetch = EAGER)
    @JoinTable(name = ORDER_BOOK,
            joinColumns = {@JoinColumn(name = ORDER_ID)},
            inverseJoinColumns = {@JoinColumn(name = BOOK_ID)})
    private List<Book> booksByOrder;

    @ManyToOne
    @JoinColumn(name = USER_ID)
    private User user;

    @OneToOne(mappedBy = "order")
    private ShoppingCart shoppingCart;

}
