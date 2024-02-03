package by.intereson.ebookservice.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

import static by.intereson.ebookservice.utils.Constance.NAME;
import static by.intereson.ebookservice.utils.Constance.ROLE;
import static jakarta.persistence.GenerationType.AUTO;


@Data
@Entity
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Table(name = ROLE)
public class Role {
    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;
    @Column(name = NAME)
    private String name;

    @ManyToMany(mappedBy = "roleList")
    private List<User> users;
}
