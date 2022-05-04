package sh.yannick.dhbw.dhbw42.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import sh.yannick.dhbw.dhbw42.api.PostApi;
import sh.yannick.dhbw.dhbw42.model.*;
import sh.yannick.dhbw.dhbw42.service.PostService;

import java.util.*;

@RestController
@RequiredArgsConstructor
public class PostController implements PostApi {
    private final PostService service;

    @Override
    public ResponseEntity<PostResponse> getPost(String id) {
        UUID uuid;
        try {
            uuid = UUID.fromString(id);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }

        PostResponse response = new PostResponse();

        Optional<Post> optionalPost = service.getPost(uuid);
        if (optionalPost.isPresent()) {
            response.setData(optionalPost.get());
            return ResponseEntity.ok(response);
        }

        return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<PostIdResponse> createPost(PostCreation postCreation) {
        Post post = service.createPost(postCreation);
        PostIdResponse response = new PostIdResponse();
        response.setData(new PostId().id(post.getId()));
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<PostResponse> updatePost(String id, PostUpdate postUpdate) {
        UUID uuid;
        try {
            uuid = UUID.fromString(id);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }

        PostResponse response = new PostResponse();

        Optional<Post> optionalPost = service.updatePost(uuid, postUpdate);
        if (optionalPost.isPresent()) {
            response.setData(optionalPost.get());
            return ResponseEntity.ok(response);
        }

        return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<PostResponse> deletePost(String id) {
        UUID uuid;
        try {
            uuid = UUID.fromString(id);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }

        PostResponse response = new PostResponse();

        Optional<Post> optionalPost = service.deletePost(uuid);
        if (optionalPost.isPresent()) {
            response.setData(optionalPost.get());
            return ResponseEntity.ok(response);
        }

        return ResponseEntity.notFound().build();
    }
}
