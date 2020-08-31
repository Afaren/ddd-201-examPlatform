package exam.paperContext.application;

import exam.paperContext.domain.model.blankQuiz.BlankQuiz;
import exam.paperContext.domain.model.blankQuiz.BlankQuizId;
import exam.paperContext.domain.model.blankQuiz.BlankQuizNotFoundException;
import exam.paperContext.domain.model.blankQuiz.BlankQuizRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class BlankQuizApplicationService {
    private BlankQuizRepository blankQuizRepository;

    public BlankQuizApplicationService(BlankQuizRepository blankQuizRepository) {
        this.blankQuizRepository = blankQuizRepository;
    }

    public BlankQuizId create(CreateBlankQuizCommand command) {
        final BlankQuizId blankQuizId = BlankQuizId.nextId();
        final BlankQuiz blankQuiz = BlankQuiz.create(blankQuizId,
                                                     command.getTeacherId(),
                                                     command.getContent(),
                                                     command.getReferenceAnswer(),
                                                     command.getScore());
        blankQuizRepository.save(blankQuiz);
        return blankQuizId;
    }


    public Collection<BlankQuiz> getAll() {
        return blankQuizRepository.getAll();
    }

    public void update(String blankQuizId, UpdateBlankQuizCommand command) {
        final BlankQuizId id = BlankQuizId.of(blankQuizId);
        final BlankQuiz blankQuiz = blankQuizRepository.find(id)
                                                       .orElseThrow(() -> new BlankQuizNotFoundException(id));
        blankQuiz.update(command.getTeacherId(),
                         command.getContent(),
                         command.getReferenceAnswer(),
                         command.getScore());
        blankQuizRepository.save(blankQuiz);
    }


    public void delete(String blankQuizId) {
        blankQuizRepository.delete(BlankQuizId.of(blankQuizId));
    }


    public BlankQuiz getOne(BlankQuizId id) {
        return blankQuizRepository.find(id)
                                  .orElseThrow(() -> new BlankQuizNotFoundException(id));
    }
}
