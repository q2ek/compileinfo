package net.q2ek.compileinfo.integration;

import java.time.LocalDateTime;
import java.util.Set;

import net.q2ek.compileinfo.CompileInfo;

@CompileInfo(includeProperties = { "java.version" })
class ClassWithOneProperty {
	public LocalDateTime compileDateTime() {
		return ClassWithOnePropertyCompileInfo.localDateTime();
	}

	public String get(String key) {
		return ClassWithOnePropertyCompileInfo.get(key);
	}

	public Set<String> keySet() {
		return ClassWithOnePropertyCompileInfo.keySet();
	}
}
