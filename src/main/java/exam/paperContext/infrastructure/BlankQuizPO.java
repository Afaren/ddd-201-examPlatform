package exam.paperContext.infrastructure;

import exam.paperContext.domain.model.blankQuiz.BlankQuiz;
import exam.paperContext.domain.model.blankQuiz.BlankQuizId;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class BlankQuizPO {
    private final String blankQuizId;
    private final String teacherId;
    private final String content;
    private final String referenceAnswer;
    private final Integer score;
    private final LocalDateTime createdTime;
    private final LocalDateTime updatedTime;

    public BlankQuizPO(String blankQuizId,
                       String teacherId,
                       String content,
                       String referenceAnswer,
                       Integer score, LocalDateTime createdTime, LocalDateTime updatedTime) {
        this.blankQuizId = blankQuizId;
        this.teacherId = teacherId;
        this.content = content;
        this.referenceAnswer = referenceAnswer;
        this.score = score;
        this.createdTime = createdTime;
        this.updatedTime = updatedTime;
    }

    public static BlankQuizPO of(BlankQuiz blankQuiz) {
        return new BlankQuizPO(blankQuiz.getBlankQuizId().getId(),
                               blankQuiz.getTeacherId(),
                               blankQuiz.getContent(),
                               blankQuiz.getReferenceAnswer(),
                               blankQuiz.getScore(),
                               blankQuiz.getCreatedTime(),
                               blankQuiz.getUpdatedTime());
    }

    public BlankQuiz toDomainObject() {
        return BlankQuiz.create(BlankQuizId.of(this.blankQuizId),
                                this.teacherId,
                                this.content,
                                this.referenceAnswer,
                                this.score);
    }
}
