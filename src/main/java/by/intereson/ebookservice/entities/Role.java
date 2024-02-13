package by.intereson.ebookservice.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

import static jakarta.persistence.GenerationType.SEQUENCE;


@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ROLES")
public class Role {
    private static final String SEQ_NAME = "ROLE_SEQ";

    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = SEQ_NAME)
    @SequenceGenerator(name = SEQ_NAME, sequenceName = SEQ_NAME,allocationSize = 1)
    private Long id;
    @Column(name = "NAME",unique = true,nullable = false)
    private String name;

//    @ManyToMany(mappedBy = "roleList",fetch = FetchType.LAZY)
//    private List<User> users;
}
