package by.intereson.ebookservice.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
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
@Table(name = "USERS")
public class User {
    private static final String SEQ_NAME="USER_SEQ";

    @Id
    @GeneratedValue(strategy = SEQUENCE,generator = SEQ_NAME)
    @SequenceGenerator(name = SEQ_NAME,sequenceName = SEQ_NAME)
    private Long id;
    @Column(name = "NAME", nullable = false)
    private String name;
    @Column(name = "FAMILY", nullable = false)
    private String surname;
    @Column(name = "EMAIL", nullable = false, unique = true)
    private String email;
    @Column(name = "LOGIN", nullable = false, unique = true)
    private String login;
    @Column(name = "PASSWORD", nullable = false)
    private String password;
    @CreationTimestamp
    @Column(name = "CREATE_TIME")
    private LocalDateTime createDateTime;

    @ManyToMany(fetch = EAGER,cascade = CascadeType.ALL)
    @JoinTable(name = "USER_ROLE",
            joinColumns = {@JoinColumn(name = "USER_ID")},
            inverseJoinColumns = {@JoinColumn(name = "ROLE_ID")})
    private List<Role> roleList;

    @OneToOne(cascade = CascadeType.ALL,fetch = LAZY)
    @JoinColumn(name = "SHOPPING_CART_ID")
    private ShoppingCart shoppingCart;

    @OneToMany(cascade = CascadeType.ALL,fetch = LAZY)
    private List<Order> orders;

    @OneToMany(cascade = CascadeType.ALL,fetch = EAGER)
    private List<Book> likedBooks;
}
