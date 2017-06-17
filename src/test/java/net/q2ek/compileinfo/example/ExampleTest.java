package net.q2ek.compileinfo.example;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class ExampleTest {

	@Test
	public void firstExample_works() {
		String actual = new FirstExample().compileDateTime();
		new FirstExample().javaVersion();

		assertThat(actual).isNotEmpty();
	}

	@Test
	public void secondExample_works() {
		String time = new SecondExample().compileDateTime();
		new SecondExample().jenkinsBuildUrl();

		assertThat(time).isNotEmpty();
	}

	@Test
	public void thirdExample_works() {
		String time = new ThirdExample().compileDateTime();
		new ThirdExample().jenkinsBuildUrl();

		assertThat(time).isNotEmpty();
	}
}
