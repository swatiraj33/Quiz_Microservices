package com.swati.question_service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.swati.model.Question;
import com.swati.model.QuestionWrapper;

import com.swati.model.Response;
import com.swati.question_dao.QuestionDao;


@Service
public class QuestionService {
    
    @Autowired
    QuestionDao quesdao;
    public ResponseEntity<List<Question>> getAllQuestions()
    {
       try {
        return new ResponseEntity<>( quesdao.findAll() ,HttpStatus.OK);
       } catch (Exception e) {
          return new ResponseEntity<>( new ArrayList<>() ,HttpStatus.BAD_REQUEST);
       }
        
    }
    
    public ResponseEntity<List<Question>> getQuestionByCategory(String category) {
         try {
            return new ResponseEntity<>(quesdao.findQuestionByCategory(category),HttpStatus.OK) ;
         } catch (Exception e) {
            return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST) ;
         }
         
    }

    public ResponseEntity<String> addQuestions(Question question) {

        quesdao.save(question);
        try {
            
         return new ResponseEntity<>("Success", HttpStatus.OK);
        } catch (Exception e) {
          return new ResponseEntity<>(" Question Not Found ", HttpStatus.BAD_REQUEST);
        } 
    }


    public ResponseEntity<String> deleteQuestionById(int id) {
       
     
        if(quesdao.existsById(id))
        {
        quesdao.deleteById(id);
        return new ResponseEntity<>("Question deleted successfully", HttpStatus.OK);
        }

        return new ResponseEntity<>("Id not Present ", HttpStatus.BAD_REQUEST);
   
    
     
    
    
    
}

   public ResponseEntity<List<Integer>> getQuestionforQuiz(String category, Integer numques) {

       List<Integer> question= quesdao.findRandomQuestionsByCategory(category, numques);
       return new ResponseEntity<>(question , HttpStatus.OK);
      
   }

public ResponseEntity<List<QuestionWrapper>> getQuesById(List<Integer> quesId) {
  List<QuestionWrapper> wrapper  = new ArrayList<>();
  List<Question> ques= new ArrayList<>();
  for(Integer id : quesId)
  {
   ques.add(quesdao.findById(id).get());
  }

   for(Question question: ques)
   {
      QuestionWrapper wp= new QuestionWrapper();
       wp.setOption1(question.getOption1());
       wp.setOption2(question.getOption2());
       wp.setOption3(question.getOption3());
       wp.setOption4(question.getOption4());
       wp.setId(question.getId());
       wp.setQuestion_title(question.getQuestion_title());
       
       wrapper.add(wp);
   }
   
    return new ResponseEntity<>(wrapper, HttpStatus.OK);
}   

/**
 * @param response
 * @return
 */
public ResponseEntity<Integer> calculateScore(List<Response> response) {

      
      int right=0;
      for(Response res: response)
      {
       
         Question question = quesdao.findById(res.getId()).get();
        if(res.getResponse().equals(question.getRight_answer()))
        {
        right++;
        }

      }
       return  new ResponseEntity<>(right , HttpStatus.OK);    
       
}
}

       
