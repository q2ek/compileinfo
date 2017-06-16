package net.q2ek.compileinfo.integration;

import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;

import org.assertj.core.api.Assertions;
import org.junit.Ignore;
import org.junit.Test;

@Ignore
public class SystemVsEnvironmentTest {

	@Test
	public void systemVsEnvironment() {
		Map<String, String> properties = convert(System.getProperties());
		Map<String, String> environment = convert(System.getenv());

		Assertions.assertThat(properties).containsAllEntriesOf(environment);
	}

	@Test
	public void environmentVsSystem() {
		Map<String, String> properties = convert(System.getProperties());
		Map<String, String> environment = convert(System.getenv());

		Assertions.assertThat(environment).containsAllEntriesOf(properties);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static Map<String, String> convert(Properties properties) {
		return new TreeMap<String, String>((Map) properties);
	}

	private static Map<String, String> convert(Map<String, String> properties) {
		return new TreeMap<String, String>(properties);
	}
}
