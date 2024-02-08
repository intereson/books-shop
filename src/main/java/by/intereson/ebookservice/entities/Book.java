package by.intereson.ebookservice.entities;

import by.intereson.ebookservice.enums.Genre;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.List;

import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.GenerationType.SEQUENCE;

@Data
@Entity
@Builder
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "BOOKS")
public class Book {
    private static final String SEQ_NAME = "book_seq";

    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = SEQ_NAME)
    @SequenceGenerator(name = SEQ_NAME, sequenceName = SEQ_NAME)
    private Long id;
    @Column(name = "BOOK_NAME", nullable = false)
    private String bookName;
    @Column(name = "AUTHOR", nullable = false)
    private String author;
    @Column(name = "PUBLISHING_YEAR")
    private Integer publishingYear;
    @Column(name = "PUBLISHING_HOUSE")
    private String publishingHouse;
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "PRICE")
    private Double price;
    @CreationTimestamp
    private String dateTime;
    @Enumerated(STRING)
    private Genre genre;
    @Column(name = "QUANTITY")
    private Integer quantity;

    @ManyToMany(mappedBy = "booksByShoppingCart")
    private List<ShoppingCart> shoppingCarts;

}
