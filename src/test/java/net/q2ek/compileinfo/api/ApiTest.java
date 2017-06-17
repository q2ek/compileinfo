package net.q2ek.compileinfo.api;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.ZonedDateTime;
import java.util.Map;

import org.junit.Test;

import net.q2ek.compileinfo.CompileInfo;

/**
 * This test describes the API for net.q2ek.compileinfo
 *
 * @author Edze Kruizinga
 */
@CompileInfo(
	classname = "%sCompileInfo",
	regex = ".*",
	includeSystemProperties = true,
	includeEnvironmentVariables = true)
public class ApiTest {
	@Test
	public void zonedDateTime_returnsZonedDateTime() {
		ZonedDateTime actual = ApiTestCompileInfo.zonedDateTime();

		assertThat(actual).isNotNull();
	}

	@Test
	public void staticProperties_returnsMapStringString() {
		Map<String, String> actual = ApiTestCompileInfo.properties();

		assertThat(actual).containsKey("java.version");
	}

	@Test
	public void staticGetenv_returnsMapStringString() {
		Map<String, String> actual = ApiTestCompileInfo.getenv();

		assertThat(actual).containsKey("JAVA_HOME");
	}
}
