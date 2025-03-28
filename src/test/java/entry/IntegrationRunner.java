package entry;

import org.junit.platform.suite.api.*;

@Suite
@IncludeEngines("cucumber")
@SelectDirectories("src/test/resources/features")
@IncludeTags({"smoke"})
@ExcludeTags({"bug"})
public class IntegrationRunner {
}
