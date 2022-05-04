package sh.yannick.dhbw.dhbw42.service;

import org.springframework.stereotype.Service;
import sh.yannick.dhbw.dhbw42.model.Question;
import sh.yannick.dhbw.dhbw42.model.entity.Post;
import sh.yannick.dhbw.dhbw42.repository.PostRepository;

import java.util.*;

@Service
public record QuestionsService(PostRepository repository) {
    public List<Question> getQuestions() {
        List<Post> posts = repository.findAllByParentId(null);
        List<Question> questions = new ArrayList<>(posts.size());

        for (Post post : posts) {
            Question question = new Question();
            question.setId(post.getId().toString());
            question.setTitle(post.getTitle());
            question.setCreator(post.getCreator().getDisplayName());

            List<Post> answers = repository.findAllByParentId(post.getId());
            question.setAnswerCount(answers.size());
            question.setDateCreated(post.getDateCreated().toString());

            questions.add(question);
        }

        return questions;
    }
}
