package ru.otus.hw.service;

import lombok.RequiredArgsConstructor;
import ru.otus.hw.dao.QuestionDao;
import ru.otus.hw.domain.Answer;
import ru.otus.hw.domain.Question;

import java.util.List;

@RequiredArgsConstructor
public class TestServiceImpl implements TestService {

    private final IOService ioService;

    private final QuestionDao questionDao;

    @Override
    public void executeTest() {
        ioService.printLine("");
        ioService.printFormattedLine("Please answer the questions below%n");
        // Получить вопросы из дао и вывести их с вариантами ответов
        List<Question> questions = questionDao.findAll();
        questions.forEach(this::printQuestionWithAnswers);
    }

    private void printQuestionWithAnswers(Question question) {
        ioService.printLine(question.text());
        this.printAnswers(question.answers());
        ioService.printLine("");
    }

    private void printAnswers(List<Answer> answers) {
        int answerNumber = 1;
        for (Answer answer : answers) {
            ioService.printFormattedLine(
                    "%s. %s %s",
                    answerNumber,
                    answer.text(),
                    answer.isCorrect() ? "[CORRECT]" : null);

            answerNumber++;
        }
    }
}
