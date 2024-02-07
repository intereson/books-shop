package by.intereson.ebookservice.entities;

import by.intereson.ebookservice.enums.StatusOrder;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.GenerationType.SEQUENCE;


@Data
@Entity
@Builder
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ORDERS")
public class Order {
    private static final String SEQ_NAME = "ORDER_SEQ";

    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = SEQ_NAME)
    @SequenceGenerator(name = SEQ_NAME, sequenceName = SEQ_NAME)
    private Long id;

    @CreationTimestamp
    @Column(name = "CREATE_DATE_TIME")
    private LocalDateTime createDateTime;
    @UpdateTimestamp
    @Column(name = "UPDATE_DATE_TIME")
    private LocalDateTime updateDateTime;
    @Enumerated(STRING)
    private StatusOrder statusOrder;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @OneToMany(cascade = ALL,mappedBy = "order")
    private List<PartOfTheOrder> parts;

}
