package net.q2ek.compileinfo.integration;

import java.time.LocalDateTime;
import java.util.Map;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class Integration_DefaultTest {
	private final ClassWithoutProperties withoutProperties = new ClassWithoutProperties();
	private final ClassWithRegex withRegex = new ClassWithRegex();

	@Test
	public void compileDateTime_containsSomething() {
		LocalDateTime actual = this.withoutProperties.compileDateTime();

		Assertions.assertThat(actual).isNotNull();
	}

	@Test
	public void classWithRegex_containsOnlyOneProperty() {
		Map<String, String> actual = this.withRegex.properties();

		Assertions.assertThat(actual).hasSize(1);
	}
}
