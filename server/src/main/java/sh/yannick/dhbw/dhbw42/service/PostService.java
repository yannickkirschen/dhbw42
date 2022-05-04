package sh.yannick.dhbw.dhbw42.service;

import org.springframework.stereotype.Service;
import sh.yannick.dhbw.dhbw42.model.Post;
import sh.yannick.dhbw.dhbw42.model.Reaction;
import sh.yannick.dhbw.dhbw42.model.*;
import sh.yannick.dhbw.dhbw42.model.entity.*;
import sh.yannick.dhbw.dhbw42.repository.*;

import java.time.LocalDate;
import java.util.*;

@Service
public record PostService(PostRepository postRepository, UserRepository userRepository) {
    public Optional<Post> getPost(UUID id) {
        Optional<sh.yannick.dhbw.dhbw42.model.entity.Post> optionalPost = postRepository.findById(id);
        if (optionalPost.isEmpty()) {
            return Optional.empty();
        }

        Post post = new Post();
        entityToTransferObject(optionalPost.get(), post);
        post.setChildren(postRepository.findAllByParentId(id).stream().map(c -> c.getId().toString()).toList());

        return Optional.of(post);
    }

    public Post createPost(PostCreation postCreation) {
        Optional<User> optionalUser = userRepository.findByDisplayName(postCreation.getCreator());

        User user;
        if (optionalUser.isEmpty()) {
            user = new User();
            user.setDisplayName(postCreation.getCreator());
            user.setRole(Role.ANONYMOUS);
            userRepository.save(user);
        } else {
            user = optionalUser.get();
        }

        sh.yannick.dhbw.dhbw42.model.entity.Post postEntity = new sh.yannick.dhbw.dhbw42.model.entity.Post();

        if (postCreation.getParent() != null) {
            postEntity.setParentId(UUID.fromString(postCreation.getParent()));
        }
        postEntity.setCreator(user);
        postEntity.setTitle(postCreation.getTitle());
        postEntity.setContent(postCreation.getContent());
        postEntity.setDateCreated(LocalDate.now());

        postRepository.save(postEntity);

        Post post = new Post();
        entityToTransferObject(postEntity, post);
        return post;
    }

    public Optional<Post> updatePost(UUID id, PostUpdate postUpdate) {
        Optional<sh.yannick.dhbw.dhbw42.model.entity.Post> optionalPostEntity = postRepository.findById(id);
        if (optionalPostEntity.isEmpty()) {
            return Optional.empty();
        }

        sh.yannick.dhbw.dhbw42.model.entity.Post postEntity = optionalPostEntity.get();

        if (postUpdate.getTitle() != null) {
            postEntity.setTitle(postUpdate.getTitle());
        }

        if (postUpdate.getContent() != null) {
            postEntity.setContent(postUpdate.getContent());
        }

        ReactionChange reactionChange = postUpdate.getReaction();
        sh.yannick.dhbw.dhbw42.model.entity.Reaction reaction = null;
        int reactionIndex = 0;

        for (int i = 0; i < postEntity.getReactions().size(); i++) {
            if (postEntity.getReactions().get(i).getCharacter().equals(reactionChange.getUnicodeChar())) {
                reaction = postEntity.getReactions().get(i);
                reactionIndex = i;
            }
        }

        if (reaction != null) {
            reaction.setAmount(reaction.getAmount() + reactionChange.getChange());

            if (reaction.getAmount() < 1) {
                postEntity.getReactions().remove(reactionIndex);
            }
        } else {
            reaction = new sh.yannick.dhbw.dhbw42.model.entity.Reaction();
            reaction.setCharacter(reactionChange.getUnicodeChar());
            reaction.setAmount(reactionChange.getChange());

            postEntity.getReactions().add(reaction);
        }

        postRepository.save(postEntity);
        Post post = new Post();
        entityToTransferObject(postEntity, post);
        return Optional.of(post);
    }

    public Optional<Post> deletePost(UUID id) {
        Optional<Post> optionalPost = getPost(id);
        if (optionalPost.isEmpty()) {
            return Optional.empty();
        }

        postRepository.deleteById(id);
        return optionalPost;
    }

    private void entityToTransferObject(sh.yannick.dhbw.dhbw42.model.entity.Post entity, Post post) {
        post.setId(entity.getId().toString());

        if (entity.getParentId() != null) {
            post.setParent(entity.getParentId().toString());
        }

        post.setTitle(entity.getTitle());
        post.setCreator(entity.getCreator().getDisplayName());
        post.setContent(entity.getContent());

        post.setReactions(entity.getReactions().stream().map(r -> {
            Reaction reaction = new Reaction();
            reaction.setAmount(r.getAmount());
            reaction.setUnicodeChar(r.getCharacter());
            return reaction;
        }).toList());
        post.setDateCreated(entity.getDateCreated().toString());
    }
}
