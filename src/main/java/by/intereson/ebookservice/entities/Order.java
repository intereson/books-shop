package by.intereson.ebookservice.entities;

import by.intereson.ebookservice.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

import static jakarta.persistence.CascadeType.REMOVE;
import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.GenerationType.SEQUENCE;


@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ORDERS")
public class Order {
    private static final String SEQ_NAME = "ORDER_SEQ";

    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = SEQ_NAME)
    @SequenceGenerator(name = SEQ_NAME, sequenceName = SEQ_NAME,allocationSize = 1)
    private Long id;

    @CreationTimestamp
    @Column(name = "CREATE_DATE_TIME")
    private LocalDateTime createDateTime;
    @UpdateTimestamp
    @Column(name = "UPDATE_DATE_TIME")
    private LocalDateTime updateDateTime;
    @Enumerated(STRING)
    private OrderStatus orderStatus;
    @Column(name = "SUM_PRICES")
    private Double sumPrice;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "order",cascade = REMOVE)
    private List<PartOfTheOrder> parts;

}
