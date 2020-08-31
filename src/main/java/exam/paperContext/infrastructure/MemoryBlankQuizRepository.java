package exam.paperContext.infrastructure;

import exam.paperContext.domain.model.blankQuiz.BlankQuiz;
import exam.paperContext.domain.model.blankQuiz.BlankQuizId;
import exam.paperContext.domain.model.blankQuiz.BlankQuizRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MemoryBlankQuizRepository implements BlankQuizRepository {

    private Map<BlankQuizId, BlankQuiz> bank = new HashMap<>();

    @Override
    public void save(BlankQuiz blankQuiz) {
        bank.put(blankQuiz.getBlankQuizId(), blankQuiz);
    }

    @Override
    public Collection<BlankQuiz> getAll() {
        return bank.values();
    }

    @Override
    public Optional<BlankQuiz> find(BlankQuizId id) {
        return Optional.ofNullable(bank.get(id));
    }

    @Override
    public void delete(BlankQuizId id) {
        bank.remove(id);

    }
}
