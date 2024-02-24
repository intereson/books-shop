package by.intereson.ebookservice.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static by.intereson.ebookservice.utils.Constants.*;
import static jakarta.persistence.GenerationType.SEQUENCE;


@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = ROLES)
public class Role {
    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = ROLE_SEQ_NAME)
    @SequenceGenerator(name = ROLE_SEQ_NAME, sequenceName = ROLE_SEQ_NAME,
            allocationSize = 1, initialValue = 100)
    private Long id;

    @Column(name = ROLE_NAME, unique = true, nullable = false)
    private String roleName;
}
