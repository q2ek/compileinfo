package net.q2ek.compileinfo.api_0_15_0;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.ZonedDateTime;
import java.util.Map;

import org.junit.Test;

import net.q2ek.compileinfo.CompileInfo;

@CompileInfo(classname = "%sGeneratedApi", regex = "java\\.version", includeSystemProperties = true)
public class ApiDescriptionTest {
	@Test
	public void zonedDateTime_returnsZonedDateTime() {
		ZonedDateTime actual = ApiDescriptionTestGeneratedApi.zonedDateTime();

		assertThat(actual).isNotNull();
	}

	@Test
	public void staticProperties_returnsMapStringString() {
		Map<String, String> actual = ApiDescriptionTestGeneratedApi.properties();

		assertThat(actual).containsKey("java.version");
	}
}
