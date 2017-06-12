package net.q2ek.compileinfo.integration;

import java.time.LocalDateTime;

import net.q2ek.compileinfo.CompileInfo;

@CompileInfo(withPropertyMap = false)
class ClassWithoutProperties {
	public LocalDateTime compileDateTime() {
		return ClassWithoutPropertiesCompileInfo.localDateTime();
	}
}
