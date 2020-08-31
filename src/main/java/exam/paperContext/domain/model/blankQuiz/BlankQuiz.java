package exam.paperContext.domain.model.blankQuiz;

import exam.paperContext.shared.Entity;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Optional;

@Getter
public class BlankQuiz implements Entity<BlankQuiz> {
    private final BlankQuizId blankQuizId;
    private String teacherId;
    private String content;
    private String referenceAnswer;
    private Integer score;
    private final LocalDateTime createdTime;
    private LocalDateTime updatedTime;

    private BlankQuiz(BlankQuizId blankQuizId,
                      String teacherId,
                      String content,
                      String referenceAnswer,
                      Integer score) {
        this.blankQuizId = blankQuizId;
        this.teacherId = teacherId;
        this.content = content;
        this.referenceAnswer = referenceAnswer;
        this.score = score;
        createdTime = updatedTime = LocalDateTime.now();
    }

    @Override
    public boolean sameIdentityAs(BlankQuiz other) {
        return this.getBlankQuizId().sameValueAs(other.getBlankQuizId());
    }

    public static BlankQuiz create(BlankQuizId blankQuizId,
                                   String teacherId,
                                   String content,
                                   String referenceAnswer,
                                   int score) {
        return new BlankQuiz(blankQuizId, teacherId, content, referenceAnswer, score);
    }


    public void update(String teacherId, String content, String referenceAnswer, Integer score) {
        Optional.ofNullable(teacherId)
                .ifPresent(value -> this.teacherId = value);
        Optional.ofNullable(content)
                .ifPresent(value -> this.content = value);
        Optional.ofNullable(referenceAnswer)
                .ifPresent(value -> this.referenceAnswer = value);
        Optional.ofNullable(score)
                .ifPresent(value -> this.score = value);

        this.updatedTime = LocalDateTime.now();
    }
}
