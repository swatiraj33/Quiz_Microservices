package com.swati.quiz_controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.swati.model.QuestionWrapper;
import com.swati.model.QuizDto;
import com.swati.model.Response;
import com.swati.service.QuizService;

@RestController
@RequestMapping(value="quiz")
public class QuizController {
     
    @Autowired
     QuizService quizservice;
     @PostMapping("create")
    public ResponseEntity<String> createQuiz(@RequestBody QuizDto quizdto)
    {
              return  quizservice.create(quizdto.getCategory() ,quizdto.getNumQ(), quizdto.getTitle());

    }
    @GetMapping("get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuiz(@PathVariable int id )
    {
      return quizservice.getQuiz(id);
    }

    @PostMapping("submit/{id}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable int id , @RequestBody List<Response> response)
    {
      return quizservice.CalculateResult(id , response);

    }
}
