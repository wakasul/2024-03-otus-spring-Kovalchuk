package ru.otus.hw.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.hw.dao.QuestionDao;
import ru.otus.hw.domain.Answer;
import ru.otus.hw.domain.Question;
import ru.otus.hw.domain.Student;
import ru.otus.hw.domain.TestResult;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TestServiceImpl implements TestService {

    private final IOService ioService;

    private final QuestionDao questionDao;

    @Override
    public TestResult executeTestFor(Student student) {
        ioService.printLine("");
        ioService.printFormattedLine("Please answer the questions below%n");
        var questions = questionDao.findAll();
        var testResult = new TestResult(student);

        for (var question: questions) {
            boolean isAnswerValid = this.askStudent(question);
            testResult.applyAnswer(question, isAnswerValid);
        }
        return testResult;
    }

    private boolean askStudent(Question question) {
        this.printQuestionWithAnswers(question);
        int studentAnswer = ioService.readIntForRangeWithPrompt(
                1,
                question.answers().size(),
                "Your answer:",
                "Incorrect answer format. You should enter an index of right answer.");

        return question.answers().get(studentAnswer).isCorrect();
    }

    private void printQuestionWithAnswers(Question question) {
        ioService.printLine("----------");
        ioService.printLine(question.text());
        this.printAnswers(question.answers());
        ioService.printLine("----------");
    }

    private void printAnswers(List<Answer> answers) {
        int answerNumber = 1;
        for (Answer answer : answers) {
            ioService.printFormattedLine(
                    "%s. %s",
                    answerNumber,
                    answer.text());

            answerNumber++;
        }
    }
}
