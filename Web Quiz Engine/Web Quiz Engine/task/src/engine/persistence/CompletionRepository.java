package engine.persistence;

import engine.model.Completion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompletionRepository extends CrudRepository <Completion, Integer> {

    //@Query("SELECT c FROM Completion c where c.user.email = ?1 order by COMPLETED_AT desc")
    @Query(value = "SELECT DISTINCT  new engine.model.CompletionDto(c.quiz.id , c.completedAt) from Completion c where c.user.email = ?1  ORDER BY c.completedAt desc ")
    Page<Completion> findAllPaged (String  userName ,Pageable pageable);
}
