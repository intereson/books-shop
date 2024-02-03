package by.intereson.ebookservice.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

import static by.intereson.ebookservice.utils.Constance.*;
import static jakarta.persistence.CascadeType.REMOVE;
import static jakarta.persistence.FetchType.EAGER;
import static jakarta.persistence.GenerationType.AUTO;

@Data
@Entity
@Builder
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Table(name = USERS)
public class User {
    @Id
    @GeneratedValue(strategy = AUTO)
    private long id;
    @Column(name = NAME, nullable = false)
    private String name;
    @Column(name = FAMILY, nullable = false)
    private String surname;
    @Column(name = EMAIL, nullable = false, unique = true)
    private String email;
    @Column(name = LOGIN, nullable = false, unique = true)
    private String login;
    @Column(name = PASSWORD, nullable = false)
    private String password;
    @Column(name = CREATION_DATE_TIME)
    private LocalDateTime createDateTime;

    @ManyToMany(fetch = EAGER)
    @JoinTable(name = USER_ROLE,
            joinColumns = {@JoinColumn(name = USER_ID)},
            inverseJoinColumns = {@JoinColumn(name = ROLE_ID)})
    private List<Role> roleList;

    @OneToOne(cascade = REMOVE, fetch = EAGER)
    @JoinColumn(name = SHOPPING_CART_ID, unique = true, nullable = false)
    private ShoppingCart shoppingCart;

    @OneToMany(mappedBy = "user", fetch = EAGER)
    private List<Order> orders;

    @ManyToMany(fetch = EAGER)
    @JoinTable(name = USER_SELECTED_BOOKS,
            joinColumns = {@JoinColumn(name = USER_ID)},
            inverseJoinColumns = {@JoinColumn(name = BOOK_ID)})
    private List<Book> likedBooks;
}
