package engine.controller;

import engine.model.Answer;
import engine.model.Completion;
import engine.model.Quiz;

import engine.model.User;
import engine.persistence.UserRepository;
import engine.service.CompletionService;
import engine.service.QuizService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@Validated
public class QuizController {

    private final QuizService quizService;
    private final UserRepository userRepository;
    private final CompletionService completionService;


    public QuizController(@Autowired QuizService quizService,
                          @Autowired UserRepository userRepository,
                          @Autowired CompletionService completionService) {
        this.quizService = quizService;
        this.userRepository = userRepository;
        this.completionService = completionService;
    }

    /**
     * add Quiz to the Web App
     *
     * @param quiz to be added
     * @return the added Quiz with the Response
     */
    @PostMapping("/api/quizzes")
    public Object addQuiz(@RequestBody @Valid Quiz quiz) {
        UserDetails userName = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<User> author = userRepository.findByEmail(userName.getUsername());
        author.ifPresent(quiz::setUser);
        return quizService.save(quiz);
    }


    @GetMapping("/api/quizzes/{id}")
    public Quiz getQuizById(@PathVariable int id) {
        Optional<Quiz> quiz = quizService.findQuizById(id);
        if (quiz.isPresent()) return quiz.get();
        throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "entity not found"
        );
    }


    @GetMapping(path = "/api/quizzes/completed", produces = APPLICATION_JSON_VALUE)
    public Page<Completion> getCompletions(@RequestParam int page) {
        UserDetails currentUserDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<User> currentUser = userRepository.findByEmail(currentUserDetails.getUsername());
        if (currentUser.isPresent()) {
            return completionService.getCompletionsPaged(currentUser.get().getEmail(), PageRequest.of(page, 10));
        }
        throw new ResponseStatusException(
                HttpStatus.UNAUTHORIZED
        );
    }

    @GetMapping("/api/quizzes")
    public Page<Quiz> getQuizzesWithPage(@RequestParam int page) {
        return quizService.getAllInPage(PageRequest.of(page, 10));
    }

//    @GetMapping(value = "/api/quizzes", produces = APPLICATION_JSON_VALUE)
//    public ArrayList<Quiz> getAllQuizzes() {
//        return (ArrayList<Quiz>) quizService.findAll();
//    }

    @PostMapping("/api/quizzes/{id}/solveEvo")
    public Answer solveQuiz(@PathVariable int id,
                            @RequestBody Map<String, ArrayList<Integer>> answer) {
        ArrayList<Integer> answerArr = answer.get("answer");
        UserDetails currentUserDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<User> currentUser = userRepository.findByEmail(currentUserDetails.getUsername());
        for (Quiz quiz : quizService.findAll()) {
            if (quiz.getId() == id) {
                if (answerArr.containsAll(quiz.getAnswer()) && quiz.getAnswer().containsAll(answerArr)) {
                    currentUser.ifPresent(user -> completionService.save(new Completion(user, quiz)));
                    return new Answer(true, "good job!");
                } else return new Answer(false, "try Again");
            }
        }
        throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "entity not found"
        );
    }

    @PostMapping("/api/quizzes/{id}/solve")
    public Answer solveEvolved (@PathVariable int id,
                               @RequestBody Map<String, ArrayList<Integer>> answer) {
        ArrayList<Integer> answerArr = answer.get("answer");
        Optional<Quiz> quiz = quizService.findQuizById(id);
        UserDetails currentUserDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<User> currentUser = userRepository.findByEmail(currentUserDetails.getUsername());
        if (quiz.isPresent()) {
            if (currentUser.isPresent()) {
                if (answerArr.containsAll(quiz.get().getAnswer()) && quiz.get().getAnswer().containsAll(answerArr)) {
                    completionService.save(new Completion(currentUser.get(), quiz.get()));
                    return new Answer(true, "good job!");
                } else {
                    return new Answer(false, "try Again");
                }
            }
        }
        throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "entity not found"
        );
    }

    /**
     * @param id
     */
    @DeleteMapping("api/quizzes/{id}")
    public ResponseEntity deleteQuiz(@PathVariable int id) {

        UserDetails currentUserDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<User> currentUser = userRepository.findByEmail(currentUserDetails.getUsername());
        Optional<Quiz> toBeDeleted = quizService.findQuizById(id);

        if (currentUser.isPresent()) {
            if (toBeDeleted.isPresent()) {
                if (toBeDeleted.get().getUser().getId() == currentUser.get().getId()) {
                    quizService.delete(id);
                    return ResponseEntity.status(204).build();
                } else return ResponseEntity.status(403).build();
            }
        }
        return ResponseEntity.status(404).build();
    }
}
