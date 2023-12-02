package com.swati.Feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.swati.model.QuestionWrapper;
import com.swati.model.Response;

@FeignClient("QUESTION-SERVICE")
public interface QuizInterface {
    
    @GetMapping("question/generate")
    public ResponseEntity<List<Integer>> generate(@RequestParam String category , @RequestParam Integer  numques);
     
    @PostMapping("question/getQuestions")
     public ResponseEntity<List<QuestionWrapper>> getQuestionById(@RequestBody List<Integer> quesId );
   
     @PostMapping("question/Score")
     public ResponseEntity<Integer> getScore(@RequestBody List<Response> response);
}
