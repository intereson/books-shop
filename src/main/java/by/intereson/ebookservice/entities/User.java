package by.intereson.ebookservice.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

import static by.intereson.ebookservice.utils.Constants.*;
import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.CascadeType.PERSIST;
import static jakarta.persistence.FetchType.EAGER;
import static jakarta.persistence.GenerationType.SEQUENCE;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = USERS)
public class User {
    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = USER_SEQ_NAME)
    @SequenceGenerator(name = USER_SEQ_NAME, sequenceName = USER_SEQ_NAME,
            allocationSize = 1, initialValue = 100)
    private Long id;

    @Column(name = USER_NAME, nullable = false)
    private String name;

    @Column(name = FAMILY, nullable = false)
    private String surname;

    @Column(name = EMAIL, nullable = false, unique = true)
    private String email;

    @Column(name = LOGIN, nullable = false, unique = true)
    private String login;

    @Column(name = PASSWORD, nullable = false)
    private String password;

    @CreationTimestamp
    @Column(name = CREATE_TIME)
    private LocalDateTime createDateTime;

    @ManyToMany(cascade = PERSIST, fetch = EAGER)
    @JoinTable(name = USER_ROLE,
            joinColumns = {@JoinColumn(name = USER_ID)},
            inverseJoinColumns = {@JoinColumn(name = ROLE_ID)})
    private List<Role> roleList;

    @OneToOne(cascade = ALL, fetch = EAGER)
    @JoinColumn(name = SHOPPING_CART_ID)
    private ShoppingCart shoppingCart;

    @OneToMany(mappedBy = "user", fetch = EAGER)
    private List<Order> orders;

    @ManyToMany(fetch = EAGER)
    @JoinTable(name = USER_LIKE_BOOK,
            joinColumns = {@JoinColumn(name = USER_ID)},
            inverseJoinColumns = {@JoinColumn(name = BOOK_ID)})
    private List<Book> likedBooks;
}
