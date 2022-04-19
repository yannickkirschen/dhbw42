package sh.yannick.dhbw.dhbw42.model.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString
@Entity(name = "USERS")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", nullable = false, updatable = false, unique = true)
    private Integer id;

    @Column(name = "DISPLAY_NAME")
    private String displayName;

    @Column(name = "ROLE")
    @Enumerated(EnumType.STRING)
    private Role role;
}
