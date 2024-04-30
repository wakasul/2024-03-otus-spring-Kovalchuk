package ru.otus.hw.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.otus.hw.config.TestFileNameProvider;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;


public class CsvQuestionDaoTest {

    @Mock
    private TestFileNameProvider testFileNameProvider;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @DisplayName("Проверить результаты вызова метода findAll")
    @Test
    public void CsvQuestionDaoTest_check_findAll_result() {
        when(testFileNameProvider.getTestFileName()).thenReturn("questions.csv");
        var questionDao = new CsvQuestionDao(testFileNameProvider);
        var questions = questionDao.findAll();
        assertThat(questions.size()).isEqualTo(3);
        assertThat(questions.get(0).text()).isEqualTo("Q1");
        assertThat(questions.get(0).answers().get(1).isCorrect()).isTrue();
        assertThat(questions.get(1).answers().get(1).isCorrect()).isFalse();
    }

}
