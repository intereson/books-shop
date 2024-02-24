package by.intereson.ebookservice.entities;

import by.intereson.ebookservice.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static by.intereson.ebookservice.utils.Constants.*;
import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.FetchType.EAGER;
import static jakarta.persistence.GenerationType.SEQUENCE;


@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = ORDERS)
public class Order {
    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = ORDER_SEQ_NAME)
    @SequenceGenerator(name = ORDER_SEQ_NAME, sequenceName = ORDER_SEQ_NAME,
            allocationSize = 1, initialValue = 100)
    private Long id;

    @CreationTimestamp
    @Column(name = CREATE_DATE_TIME)
    private LocalDateTime createDateTime;

    @UpdateTimestamp
    @Column(name = UPDATE_DATE_TIME)
    private LocalDateTime updateDateTime;

    @Enumerated(STRING)
    private OrderStatus orderStatus;

    @Column(name = SUM_PRICE)
    private BigDecimal sumPrice;

    @ManyToOne
    @JoinColumn(name = USER_ID)
    private User user;

    @OneToMany(mappedBy = "order", fetch = EAGER, orphanRemoval = true)
    private List<OrderDetail> details;
}
