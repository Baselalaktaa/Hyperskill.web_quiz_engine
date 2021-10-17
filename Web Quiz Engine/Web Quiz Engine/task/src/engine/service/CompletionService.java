package engine.service;


import engine.model.Completion;
import engine.persistence.CompletionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CompletionService {
    @Autowired
    private CompletionRepository completionRepository;

    public Page<Completion> getCompletionsPaged (String userName,Pageable pageable) {
        return completionRepository.findAllPaged(userName,pageable);
    }


    public void save (Completion completion) {
        completionRepository.save(completion);
    }
}
