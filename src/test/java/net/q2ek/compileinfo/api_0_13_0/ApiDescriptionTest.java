package net.q2ek.compileinfo.api_0_13_0;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Set;

import org.junit.Test;

import net.q2ek.compileinfo.CompileInfo;

@CompileInfo
public class ApiDescriptionTest {
	@Test
	public void localDateTime_method_returns_LocalDateTime() {
		LocalDateTime actual = ApiDescriptionTestCompileInfo.localDateTime();

		assertThat(actual).isNotNull();
	}

	@Test
	public void zonedDateTime_method_returns_ZonedDateTime() {
		ZonedDateTime actual = ApiDescriptionTestCompileInfo.zonedDateTime();

		assertThat(actual).isNotNull();
	}

	@Test
	public void static_keySet_method_returns_Set_of_String() {
		Set<String> actual = ApiDescriptionTestCompileInfo.keySet();

		assertThat(actual).isNotNull();
	}

	@Test
	public void static_get_method_returns_String() {
		String actual = ApiDescriptionTestCompileInfo.get(firstKey());

		assertThat(actual).isNotNull();
	}

	private String firstKey() {
		return ApiDescriptionTestCompileInfo.keySet().iterator().next();
	}
}
