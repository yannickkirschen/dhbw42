package sh.yannick.dhbw.dhbw42.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import sh.yannick.dhbw.dhbw42.api.QuestionsApi;
import sh.yannick.dhbw.dhbw42.model.QuestionsResponse;
import sh.yannick.dhbw.dhbw42.service.QuestionsService;

@RestController
@RequiredArgsConstructor
public class QuestionController implements QuestionsApi {
    private final QuestionsService service;

    @Override
    public ResponseEntity<QuestionsResponse> getQuestions() {
        QuestionsResponse response = new QuestionsResponse();
        response.setData(service.getQuestions());

        return ResponseEntity.ok(response);
    }
}
