package exam.paperContext.userInterface;

import exam.paperContext.domain.model.blankQuiz.BlankQuizId;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BlankQuizURI {
    private String uri;

    public static BlankQuizURI from(BlankQuizId blankQuizId) {
        return new BlankQuizURI("blankQuiz/" + blankQuizId);

    }
}
