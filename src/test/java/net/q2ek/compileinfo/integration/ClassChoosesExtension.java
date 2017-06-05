package net.q2ek.compileinfo.integration;

import java.time.LocalDateTime;

import net.q2ek.compileinfo.CompileInfo;

@CompileInfo(extension = "Plus")
class ClassChoosesExtension {
	public LocalDateTime compileDateTime() {
		return ClassChoosesExtensionPlus.localDateTime();
	}
}
