package engine.persistence;

import engine.model.Quiz;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuizRepository extends CrudRepository<Quiz , Integer> {

    Optional<List<Quiz>> findQuizByUserId (Integer id);

    @Query("SELECT q FROM Quiz q ORDER BY QUIZ_ID")
    Page<Quiz> findAllQuizzesWithPagination (Pageable pageable);
}
