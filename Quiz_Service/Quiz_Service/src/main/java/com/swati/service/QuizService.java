package com.swati.service;

import org.hibernate.mapping.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.swati.model.QuestionWrapper;
import com.swati.model.Quiz;
import com.swati.model.Response;
import com.swati.quiz_dao.QuizDao;
import com.swati.Feign.QuizInterface;

@Service
public class QuizService {

    @Autowired
    QuizDao quizdao;
    
    @Autowired
    QuizInterface quizinterface;
    public ResponseEntity<String> create(String category, int numques, String title) {
      List<Integer> question =quizinterface.generate(category, numques).getBody();
      Quiz quiz = new Quiz();
      quiz.setQuestionIds(question);
      quiz.setTitle(title);
      quizdao.save(quiz);

    
        return new ResponseEntity<>("Success" , HttpStatus.CREATED);
          

    }
    public ResponseEntity<List<QuestionWrapper>> getQuiz(int id) {
        
        Quiz quiz = quizdao.findById(id).get();
        List<Integer> questionId = quiz.getQuestionIds();
        List<QuestionWrapper> question = quizinterface.getQuestionById(questionId).getBody();


        return new ResponseEntity<>(question , HttpStatus.OK);
    
    
}
    public ResponseEntity<Integer> CalculateResult(int id, List<Response> response) {
    
       ResponseEntity<Integer> score = quizinterface.getScore(response);
        return score ;
    }
}
    

    

