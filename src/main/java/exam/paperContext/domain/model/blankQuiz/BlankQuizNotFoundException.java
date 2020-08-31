package exam.paperContext.domain.model.blankQuiz;

// TODO: application layer 抛出异常，服务器报 500，所以到了 userInterface layer 还需要将异常包装成 web 异常？
public class BlankQuizNotFoundException extends IllegalArgumentException {
    public BlankQuizNotFoundException(BlankQuizId id) {
        super(String.format("BlankQuizNotFoundException: target blankQuiz [%s] not found", id));
    }
}
