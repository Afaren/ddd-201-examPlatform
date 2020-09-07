package exam.paperContext.infrastructure;

import exam.paperContext.domain.model.blankQuiz.BlankQuiz;
import exam.paperContext.domain.model.blankQuiz.BlankQuizId;
import exam.paperContext.domain.model.blankQuiz.BlankQuizRepository;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class MemoryBlankQuizRepository implements BlankQuizRepository {

    private Map<String, BlankQuizPO> bank = new HashMap<>();

    @Override
    public void save(BlankQuiz blankQuiz) {
        final BlankQuizPO blankQuizPO = BlankQuizPO.of(blankQuiz);
        bank.put(blankQuizPO.getBlankQuizId(), blankQuizPO);
    }

    @Override
    public Collection<BlankQuiz> getAll() {
        return bank.values()
                   .stream()
                   .map(BlankQuizPO::toDomainObject)
                   .collect(Collectors.toList());
    }

    @Override
    public Optional<BlankQuiz> find(BlankQuizId id) {
        return Optional.ofNullable(bank.get(id.getId()))
                       .map(BlankQuizPO::toDomainObject);
    }

    @Override
    public void delete(BlankQuizId id) {
        bank.remove(id.getId());
    }
}
