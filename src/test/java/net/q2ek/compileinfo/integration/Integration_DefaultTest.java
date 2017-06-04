package net.q2ek.compileinfo.integration;

import java.time.LocalDateTime;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class Integration_DefaultTest {
	private final ClassWithOutProperties theClass = new ClassWithOutProperties();

	@Test
	public void compileDateTime_containsSomething() {
		LocalDateTime actual = this.theClass.compileDateTime();

		Assertions.assertThat(actual).isNotNull();
	}
}
