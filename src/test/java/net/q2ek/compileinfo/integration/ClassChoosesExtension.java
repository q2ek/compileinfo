package net.q2ek.compileinfo.integration;

import java.time.LocalDateTime;

import net.q2ek.compileinfo.CompileInfo;

@CompileInfo(classname = "%sPlus")
class ClassChoosesExtension {
	public LocalDateTime compileDateTime() {
		return ClassChoosesExtensionPlus.zonedDateTime().toLocalDateTime();
	}
}
