package net.q2ek.compileinfo.integration;

import java.time.LocalDateTime;

import net.q2ek.compileinfo.CompileInfo;

@CompileInfo(properties = false)
class ClassWithOutProperties {
	public LocalDateTime compileDateTime() {
		return ClassWithOutPropertiesCompileInfo.localDateTime();
	}
}
