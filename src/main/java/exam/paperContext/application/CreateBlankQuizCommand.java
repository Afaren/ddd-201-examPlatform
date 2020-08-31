package exam.paperContext.application;

import lombok.Value;

@Value
public class CreateBlankQuizCommand {
    String teacherId;
    String content;
    String referenceAnswer;
    int score;
}
