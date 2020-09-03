package exam.paperContext.application;

import exam.paperContext.domain.model.blankQuiz.BlankQuiz;
import exam.paperContext.domain.model.blankQuiz.BlankQuizId;
import exam.paperContext.domain.model.blankQuiz.BlankQuizNotFoundException;
import exam.paperContext.domain.model.blankQuiz.BlankQuizRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

class BlankQuizApplicationServiceTest {
    private BlankQuizRepository repository;
    private BlankQuizApplicationService blankQuizApplicationService;

    @BeforeEach
    void setUp() {
        repository = mock(BlankQuizRepository.class);
        blankQuizApplicationService = new BlankQuizApplicationService(repository);
    }

    @Test
    void should_save_blankQuiz() {
        final String aTeacherId = "aTeacherId";
        final String aContent = "aContent";
        final String aReferenceAnswer = "aReferenceAnswer";
        final Integer aScore = 1;
        blankQuizApplicationService.create(new CreateBlankQuizCommand(aTeacherId,
                                                                      aContent,
                                                                      aReferenceAnswer,
                                                                      aScore));

        verify(repository).save(argThat(actual -> {
            return actual.getTeacherId().equals(aTeacherId) &&
                   actual.getContent().equals(aContent) &&
                   actual.getReferenceAnswer().equals(aReferenceAnswer) &&
                   actual.getScore().equals(aScore);
        }));
    }

    @Test
    void should_get_all_blankQuiz() {
        blankQuizApplicationService.getAll();
        verify(repository, times(1)).getAll();
    }

    @Test
    void should_update_blankQuiz() {
        final String aBlankQuizId = "aBlankQuizId";
        final BlankQuizId blankQuizId = BlankQuizId.of(aBlankQuizId);
        given(repository.find(blankQuizId)).willReturn(Optional.of(BlankQuiz.create(blankQuizId,
                                                                                    "aTeacherId",
                                                                                    "aContent",
                                                                                    "aReferenceAnswer",
                                                                                    1)));
        final String anotherTeacherId = "anotherTeacherId";
        final String anotherContent = "anotherContent";
        final String anotherReferenceAnswer = "anotherReferenceAnswer";
        final Integer anotherScore = 2;

        blankQuizApplicationService.update(aBlankQuizId,
                                           new UpdateBlankQuizCommand(anotherTeacherId, anotherContent, anotherReferenceAnswer, anotherScore));

        verify(repository, times(1)).save(argThat(actual -> {
            return actual.getTeacherId().equals(anotherTeacherId) &&
                   actual.getContent().equals(anotherContent) &&
                   actual.getReferenceAnswer().equals(anotherReferenceAnswer) &&
                   actual.getScore().equals(anotherScore);
        }));

    }

    @Test
    void should_delete_blankQuiz() {
        final String aBlankQuizId = "aBlankQuizId";
        blankQuizApplicationService.delete(aBlankQuizId);
        verify(repository, times(1)).delete(BlankQuizId.of(aBlankQuizId));
    }

    @Test
    void should_find_blankQuiz_when_exist() {
        final BlankQuizId aBlankQuizId = BlankQuizId.of("aBlankQuizId");
        final String aTeacherId = "aTeacherId";
        final String aContent = "aContent";
        final String aReferenceAnswer = "aReferenceAnswer";
        final Integer aScore = 1;
        given(repository.find(aBlankQuizId))
                .willReturn(Optional.of(BlankQuiz.create(aBlankQuizId,
                                                         aTeacherId,
                                                         aContent,
                                                         aReferenceAnswer,
                                                         aScore)));


        final BlankQuiz actual = blankQuizApplicationService.getOne(aBlankQuizId);


        verify(repository, times(1)).find(eq(aBlankQuizId));
        Assertions.assertThat(actual.getTeacherId()).isEqualTo(aTeacherId);
        Assertions.assertThat(actual.getContent()).isEqualTo(aContent);
        Assertions.assertThat(actual.getReferenceAnswer()).isEqualTo(aReferenceAnswer);
        Assertions.assertThat(actual.getScore()).isEqualTo(aScore);
    }

    @Test
    void should_throw_not_found_exception_when_search_an_non_exist_blankQuiz() {
        final BlankQuizId nonExistBlankQuizId = BlankQuizId.of("nonExistBlankQuizId");
        given(repository.find(nonExistBlankQuizId)).willReturn(Optional.empty());

        Assertions.assertThatThrownBy(() -> blankQuizApplicationService.getOne(nonExistBlankQuizId))
                  .isInstanceOf(BlankQuizNotFoundException.class);
    }
}
