package exam.paperContext.userInterface;

import exam.paperContext.application.BlankQuizApplicationService;
import exam.paperContext.application.CreateBlankQuizCommand;
import exam.paperContext.application.UpdateBlankQuizCommand;
import exam.paperContext.domain.model.blankQuiz.BlankQuiz;
import exam.paperContext.domain.model.blankQuiz.BlankQuizId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/blankQuiz")
public class BlankQuizController {

    private final BlankQuizApplicationService blankQuizApplicationService;

    @Autowired
    public BlankQuizController(BlankQuizApplicationService blankQuizApplicationService) {
        this.blankQuizApplicationService = blankQuizApplicationService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    BlankQuizURI create(@RequestBody CreateBlankQuizCommand command) {
        final BlankQuizId blankQuizId = blankQuizApplicationService.create(command);
        return BlankQuizURI.from(blankQuizId);
    }

    @GetMapping("/{blankQuizId}")
    BlankQuizDTO getOne(@PathVariable String blankQuizId) {
        final BlankQuiz blankQuiz = blankQuizApplicationService.getOne(BlankQuizId.of(blankQuizId));
        return BlankQuizDTO.from(blankQuiz);
    }



    @GetMapping
    List<BlankQuizDTO> getAll() {
        return blankQuizApplicationService.getAll()
                                          .stream()
                                          .map(BlankQuizDTO::from)
                                          .collect(Collectors.toList());
    }

    @PutMapping("/{blankQuizId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void update(@PathVariable String blankQuizId, @RequestBody UpdateBlankQuizCommand command) {
        blankQuizApplicationService.update(blankQuizId, command);
    }

    @DeleteMapping("/{blankQuizId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void delete(@PathVariable String blankQuizId) {
        blankQuizApplicationService.delete(blankQuizId);
    }
}
