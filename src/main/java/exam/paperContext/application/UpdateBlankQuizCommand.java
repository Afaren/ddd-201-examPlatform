package exam.paperContext.application;

import lombok.Value;

@Value
public class UpdateBlankQuizCommand {
    String teacherId;
    String content;
    String referenceAnswer;
    Integer score;
}
