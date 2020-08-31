package exam.paperContext.userInterface;

import exam.paperContext.domain.model.blankQuiz.BlankQuiz;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BlankQuizDTO {
    private String blankQuizId;
    private String teacherId;
    private String content;
    private String referenceAnswer;
    private int score;

    public static BlankQuizDTO from(BlankQuiz blankQuiz) {
        return new BlankQuizDTO(blankQuiz.getBlankQuizId().getId(),  // todo 类型化ID如何装换为DTO，会有抽象泄漏问题
                                blankQuiz.getTeacherId(),
                                blankQuiz.getContent(),
                                blankQuiz.getReferenceAnswer(),
                                blankQuiz.getScore());
    }
}
