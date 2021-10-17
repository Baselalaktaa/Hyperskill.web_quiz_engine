package engine.service;

import engine.model.Quiz;
import engine.persistence.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class QuizService {
    private final QuizRepository quizRepository;


    public QuizService (@Autowired QuizRepository quizRepository) {
        this.quizRepository = quizRepository;
    }

    public Optional<Quiz> findQuizById (Integer id) {
       return quizRepository.findById(id);
    }

    public Page<Quiz> getAllInPage (Pageable req) {
        return quizRepository.findAllQuizzesWithPagination(req);
    }


    public Quiz save (Quiz quiz) {
        return quizRepository.save(quiz);
    }


    public List<Quiz> findAll () {
        return (List<Quiz>) quizRepository.findAll();
    }

    public void delete (int id){
        Optional<Quiz> tobeDeleted = findQuizById(id);
        if (tobeDeleted.isPresent()){
            quizRepository.delete(tobeDeleted.get());
        }
        else throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST , "not Found!"
        );
    }


    public Optional<List<Quiz>> getQuizzesByAuthor (int id) {
        return quizRepository.findQuizByUserId(id);
    }
}
