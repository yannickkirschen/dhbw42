package sh.yannick.dhbw.dhbw42.model.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;

@Getter
@Setter
@ToString
@Entity(name = "POST")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", nullable = false, updatable = false, unique = true)
    private UUID id;

    @Column(name = "PARENT_ID")
    private UUID parentId;

    @ManyToOne(targetEntity = User.class, optional = false)
    private User creator;

    @Column(name = "TITLE", nullable = false)
    private String title;

    @Column(name = "CONTENT")
    private String content;

    @ToString.Exclude
    @OneToMany(targetEntity = Reaction.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reaction> reactions = new ArrayList<>();

    @Column(name = "DATE_CREATED", nullable = false, updatable = false)
    private LocalDate dateCreated;
}
