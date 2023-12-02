package com.swati.quiz_application;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.swati.model.Question;
import com.swati.model.QuestionWrapper;
import com.swati.model.Response;
import com.swati.question_service.QuestionService;



@RestController
@RequestMapping("question")
public class QuestionController {
     
     @Autowired
     QuestionService  quesser; 
     @GetMapping(value ="/allquestions" , produces="application/json")
     public ResponseEntity<List<Question>> getAllQuestions()
     {
          return  quesser.getAllQuestions() ;
          
     }
      
     @GetMapping(value="/category/{category}")
     public ResponseEntity<List<Question>> getQuestionsByCategory(@PathVariable String category)
     {
          return quesser.getQuestionByCategory(category);
     }
    
     @PostMapping(value = "/add")
     public ResponseEntity<String> addQuestions(@RequestBody Question question) 
     {
          
          return  quesser.addQuestions(question);
     }
     
     @DeleteMapping(value ="delete/{id}")
     public ResponseEntity<String> deleteQuestionById(@PathVariable int id )
     {
          return quesser.deleteQuestionById(id);
     }

     @GetMapping("generate")
     public ResponseEntity<List<Integer>> generate(@RequestParam String category , @RequestParam Integer  numques)
     {
          return quesser.getQuestionforQuiz(category , numques);
          

     }
     
     @PostMapping("getQuestions")
     public ResponseEntity<List<QuestionWrapper>> getQuestionById(@RequestBody List<Integer> quesId )
     {
          return quesser.getQuesById(quesId);

     }
     @PostMapping("Score")
     public ResponseEntity<Integer> getScore(@RequestBody List<Response> response)
      {

        return quesser.calculateScore(response);

      }
     
}
