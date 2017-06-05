package net.q2ek.compileinfo.integration;

import java.time.LocalDateTime;
import java.util.Set;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class Integration_DefaultTest {
	private final ClassWithoutProperties withoutProperties = new ClassWithoutProperties();
	private final ClassWithOneProperty withOneProperty = new ClassWithOneProperty();

	@Test
	public void compileDateTime_containsSomething() {
		LocalDateTime actual = this.withoutProperties.compileDateTime();

		Assertions.assertThat(actual).isNotNull();
	}

	@Test
	public void classWithOneProperty_containsOnlyOneProperty() {
		Set<String> keySet = this.withOneProperty.keySet();

		Assertions.assertThat(keySet).hasSize(1);
	}
}
