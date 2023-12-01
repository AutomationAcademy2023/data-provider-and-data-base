import org.testng.annotations.Test;

import static org.testng.Assert.fail;

public class RetryAnalyzerTest {
    @Test(retryAnalyzer = MyRetryAnalyzer.class)
    public void testMethod() {
        // For demonstration, let's purposely fail this test
        fail("Purposely failing this test to demonstrate retry.");
    }
}
