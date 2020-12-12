package CodeSmell.Project;

import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(JUnitPlatform.class)
@SuiteClasses({ AppTest.class, ExcelReaderTest.class, RuleSetTest.class, RuleTest.class})
public class AllTests {
}
