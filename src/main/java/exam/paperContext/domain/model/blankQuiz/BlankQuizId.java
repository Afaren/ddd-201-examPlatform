package exam.paperContext.domain.model.blankQuiz;

import exam.paperContext.shared.ValueObject;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.UUID;

@ToString
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Getter
public class BlankQuizId implements ValueObject<BlankQuizId> {
    private String id;

    public static BlankQuizId nextId() {
        return new BlankQuizId(UUID.randomUUID().toString());
    }

    public static BlankQuizId of(String blankQuizId) {
        return new BlankQuizId(blankQuizId);
    }

    @Override
    public boolean sameValueAs(BlankQuizId other) {
        return this.equals(other);
    }
}
