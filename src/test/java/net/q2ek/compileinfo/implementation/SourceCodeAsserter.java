package net.q2ek.compileinfo.implementation;

import static org.assertj.core.api.Assertions.assertThat;

public class SourceCodeAsserter {

	public static void assertContent(String actual, PackageAndClassName packageAndClassname) {
		assertThat(actual).contains("package " + packageAndClassname.packagename());
		assertThat(actual).contains("import java.util.Map;");
		assertThat(actual).contains("import java.util.Set;");
		assertThat(actual).contains("import java.time.LocalDateTime;");
		assertThat(actual).contains("@author");
		assertThat(actual).contains("class " + packageAndClassname.classname());
		assertThat(actual).contains("LocalDateTime localDateTime()");
		assertThat(actual).contains("ZonedDateTime zonedDateTime()");
	}

}
