package service;

import org.junit.jupiter.api.Test;
import ru.otus.hw.service.TestRunnerService;
import ru.otus.hw.service.TestRunnerServiceImpl;
import ru.otus.hw.service.TestService;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class TestRunnerServiceImplTest {
    @Test
    public void TestRunnerServiceImpl_calls_executeTest_on_run() {
        TestService mockTestService = mock(TestService.class);
        TestRunnerService testRunnerService = new TestRunnerServiceImpl(mockTestService);
        testRunnerService.run();
        verify(mockTestService).executeTest();
    }
}
