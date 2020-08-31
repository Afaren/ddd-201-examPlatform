package exam.paperContext.domain.model.blankQuiz;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Optional;

class BlankQuizTest {

    @Test
    void should_create_blankQuiz() {
        String teacherId = "aTeacherId";
        String content = "aBlankQuizContent";
        String referenceAnswer = "aReferenceAnswer";
        Integer score = 1;
        final BlankQuizId blankQuizId = BlankQuizId.nextId();
        final BlankQuiz blankQuiz = BlankQuiz.create(blankQuizId,
                                                     teacherId,
                                                     content,
                                                     referenceAnswer,
                                                     score);

        Assertions.assertThat(blankQuiz.getBlankQuizId())
                  .isEqualTo(blankQuizId);
        Assertions.assertThat(blankQuiz.getTeacherId())
                  .isEqualTo(teacherId);
        Assertions.assertThat(blankQuiz.getContent())
                  .isEqualTo(content);
        Assertions.assertThat(blankQuiz.getReferenceAnswer())
                  .isEqualTo(referenceAnswer);
        Assertions.assertThat(blankQuiz.getScore())
                  .isEqualTo(score);

        Assertions.assertThat(blankQuiz.getCreatedTime())
                  .isNotNull();

        Assertions.assertThat(blankQuiz.getUpdatedTime())
                  .isNotNull();
    }

    @ParameterizedTest
    @CsvSource(value = {
            "x", "Y"
    } )
    void should_update_blankQuiz(String source) {
        System.out.println(source);
        String teacherId = "aTeacherId";
        String content = "aBlankQuizContent";
        String referenceAnswer = "aReferenceAnswer";
        Integer score = 1;
        final BlankQuizId blankQuizId = BlankQuizId.nextId();
        final BlankQuiz blankQuiz = BlankQuiz.create(blankQuizId,
                                                     teacherId,
                                                     content,
                                                     referenceAnswer,
                                                     score);

        final String anotherTeacherId = "anotherTeacherId";
        blankQuiz.update(anotherTeacherId,
                         null,
                         null,
                         null);

        Assertions.assertThat(blankQuiz.getTeacherId())
                  .isEqualTo(anotherTeacherId);
    }


    @ParameterizedTest
    @CsvSource(value = {
            "anotherTeacherId, , ,",
            ", anotherContent, , ",
            ", , anotherReferenceAnswer, ",
            ", , , 2"
    })
    void should_update_blankQuiz_properties_when_new_property_exists(String anotherTeacherId,
                                 String anotherContent,
                                 String anotherReferenceAnswer,
                                 Integer anotherScore) {
        String teacherId = "aTeacherId";
        String content = "aBlankQuizContent";
        String referenceAnswer = "aReferenceAnswer";
        Integer score = 1;
        final BlankQuizId blankQuizId = BlankQuizId.nextId();
        final BlankQuiz blankQuiz = BlankQuiz.create(blankQuizId,
                                                     teacherId,
                                                     content,
                                                     referenceAnswer,
                                                     score);

        blankQuiz.update(anotherTeacherId,
                         anotherContent,
                         anotherReferenceAnswer,
                         anotherScore);

        Optional.ofNullable(anotherTeacherId).ifPresent(value -> {
            Assertions.assertThat(blankQuiz.getTeacherId())
                      .isEqualTo(value);
        });
        Optional.ofNullable(anotherContent).ifPresent(value -> {
            Assertions.assertThat(blankQuiz.getContent())
                      .isEqualTo(value);
        });
        Optional.ofNullable(anotherReferenceAnswer).ifPresent(value -> {
            Assertions.assertThat(blankQuiz.getReferenceAnswer())
                      .isEqualTo(value);
        });
        Optional.ofNullable(anotherScore).ifPresent(value -> {
            Assertions.assertThat(blankQuiz.getScore())
                      .isEqualTo(value);
        });
    }
}
