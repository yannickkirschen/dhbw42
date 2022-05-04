package sh.yannick.dhbw.dhbw42.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sh.yannick.dhbw.dhbw42.model.entity.Post;

import java.util.*;

public interface PostRepository extends JpaRepository<Post, UUID> {
    List<Post> findAllByParentId(UUID parentId);
}
