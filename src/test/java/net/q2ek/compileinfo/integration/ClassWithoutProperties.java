package net.q2ek.compileinfo.integration;

import java.time.LocalDateTime;

import net.q2ek.compileinfo.CompileInfo;

@CompileInfo(includeSystemProperties = false)
class ClassWithoutProperties {
	public LocalDateTime compileDateTime() {
		return ClassWithoutPropertiesCompileInfo.zonedDateTime().toLocalDateTime();
	}
}
