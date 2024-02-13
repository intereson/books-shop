package by.intereson.ebookservice.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

import static jakarta.persistence.CascadeType.*;
import static jakarta.persistence.FetchType.EAGER;
import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.SEQUENCE;

@Data
@Entity
@Builder

@NoArgsConstructor
@AllArgsConstructor
@Table(name = "USERS")
public class User {
    private static final String SEQ_NAME="USER_SEQ";

    @Id
    @GeneratedValue(strategy = SEQUENCE,generator = SEQ_NAME)
    @SequenceGenerator(name = SEQ_NAME,sequenceName = SEQ_NAME,allocationSize = 1)
        private Long id;
    @Column(name = "NAME", nullable = false)
    private String name;
    @Column(name = "FAMILY", nullable = false)
    private String surname;
    @Column(name = "EMAIL", nullable = false)
    private String email;
    @Column(name = "LOGIN", nullable = false)
    private String login;
    @Column(name = "PASSWORD", nullable = false)
    private String password;
    @CreationTimestamp
    @Column(name = "CREATE_TIME")
    private LocalDateTime createDateTime;

    @ManyToMany(cascade = PERSIST,fetch = LAZY)
    @JoinTable(name = "USER_ROLE",
            joinColumns = {@JoinColumn(name = "USER_ID")},
            inverseJoinColumns = {@JoinColumn(name = "ROLE_ID")})
    private List<Role> roleList;

    @OneToOne(cascade ={PERSIST,REMOVE},fetch = LAZY)
    @JoinColumn(name = "SHOPPING_CART_ID")
    private ShoppingCart shoppingCart;

    @OneToMany(mappedBy = "user",fetch = LAZY)
    private List<Order> orders;

    @ManyToMany(fetch = LAZY)
           @JoinTable (name = "USER_LIKE_BOOK",
            joinColumns = {@JoinColumn(name = "USER_ID")},
            inverseJoinColumns = {@JoinColumn(name = "BOOK_ID")})
    private List<Book> likedBooks;
}
