package ru.otus.hw.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.otus.hw.domain.Student;

import static org.mockito.Mockito.*;

public class TestRunnerServiceImplTest {

    @Mock
    private TestService mockTestService;

    @Mock
    private StudentService studentService;

    @Mock
    private ResultService resultService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @DisplayName("Проверить факт вызова executeTestFor с тестовым студентом при запуске тестирования")
    @Test
    public void testRunnerServiceImplCallsExecuteTestForOnRun() {
        var testStudent = new Student("Ivan", "Ivanov");
        when(studentService.determineCurrentStudent()).thenReturn(testStudent);

        TestRunnerService testRunnerService =
                new TestRunnerServiceImpl(mockTestService, studentService, resultService);

        testRunnerService.run();
        verify(mockTestService).executeTestFor(testStudent);
    }

}
