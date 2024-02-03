package by.intereson.ebookservice.entities;

import by.intereson.ebookservice.enums.Genre;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

import static by.intereson.ebookservice.utils.Constance.*;

import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.GenerationType.AUTO;

@Data
@Entity
@Builder
@ToString
@NoArgsConstructor
@EqualsAndHashCode
@AllArgsConstructor
@Table(name = BOOK)
public class Book {
    @Id
    @GeneratedValue(strategy = AUTO)
    private long id;
    @Column(name = BOOK_NAME,nullable = false)
    private String bookName;
    @Column(name =AUTHOR,nullable = false)
    private String author;
    @Column(name = PUBLISHING_YEAR)
    private int publishingYear;
    @Column(name = PUBLISHING_HOUSE)
    private String publishingHouse;
    @Column(name = NOTATION)
    private String notation;
    @Column(name = PRICE)
    private double price;
    @Column(name = DATE_OF_ADDITION)
    private String dateTime;

    @Enumerated(STRING)
    private Genre genre;

    @ManyToMany(mappedBy = "likedBooks")
    private List<User> users;

    @ManyToMany(mappedBy = "booksByOrder")
    private List<Order> orders;

    @ManyToMany(mappedBy = "booksByShoppingCart")
    private List<ShoppingCart> shoppingCarts;
}
