package CodeSmell.Project;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({GuiTest.class, ExcelReaderTest.class, RuleSetTest.class, RuleTest.class })
public class AllTests {

}
