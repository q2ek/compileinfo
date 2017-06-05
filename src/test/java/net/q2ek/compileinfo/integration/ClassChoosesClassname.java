package net.q2ek.compileinfo.integration;

import java.time.LocalDateTime;

import net.q2ek.compileinfo.CompileInfo;

@CompileInfo(classname = "Info")
class ClassChoosesClassname {
	public LocalDateTime compileDateTime() {
		return Info.localDateTime();
	}
}
