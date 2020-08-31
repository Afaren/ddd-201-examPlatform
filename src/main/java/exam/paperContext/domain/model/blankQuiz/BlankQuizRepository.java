package exam.paperContext.domain.model.blankQuiz;

import java.util.Collection;
import java.util.Optional;

public interface BlankQuizRepository {
    void save(BlankQuiz blankQuiz);

    Collection<BlankQuiz> getAll();

    Optional<BlankQuiz> find(BlankQuizId id);

    void delete(BlankQuizId id);
}
