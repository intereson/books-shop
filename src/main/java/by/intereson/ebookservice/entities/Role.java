package by.intereson.ebookservice.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

import static jakarta.persistence.GenerationType.SEQUENCE;


@Data
@Entity
@Builder
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ROLES")
public class Role {
    private static final String SEQ_NAME = "ROLE_SEQ";

    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = SEQ_NAME)
    @SequenceGenerator(name = SEQ_NAME, sequenceName = SEQ_NAME)
    private Long id;
    @Column(name = "NAME")
    private String name;

    @ManyToMany(mappedBy = "roleList")
    private List<User> users;
}