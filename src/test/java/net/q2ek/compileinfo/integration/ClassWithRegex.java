package net.q2ek.compileinfo.integration;

import java.time.LocalDateTime;
import java.util.Map;

import net.q2ek.compileinfo.CompileInfo;

@CompileInfo(regex = "java\\.version")
class ClassWithRegex {
	public LocalDateTime compileDateTime() {
		return ClassWithRegexCompileInfo.localDateTime();
	}

	public String get(String key) {
		return ClassWithRegexCompileInfo.get(key);
	}

	public Map<String, String> properties() {
		return ClassWithRegexCompileInfo.properties();
	}
}
