package sh.yannick.dhbw.dhbw42.model.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString
@Entity(name = "REACTION")
public class Reaction {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "CHARACTER", nullable = false, updatable = false, unique = true)
    private String character;

    @Column(name = "AMOUNT", nullable = false)
    private Integer amount;
}
