package school.tss.shop;

import org.springframework.boot.test.context.SpringBootTest;

/**
 * The base test. All Tests that need spring dependencies should extend this test.
 */
@SpringBootTest(properties = {"spring.shell.interactive.enabled=false"})
public abstract class BaseTest {
}
