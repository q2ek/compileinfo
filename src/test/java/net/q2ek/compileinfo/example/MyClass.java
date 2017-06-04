package net.q2ek.compileinfo.example;

import net.q2ek.compileinfo.CompileInfo;

@CompileInfo
class MyClass {
	public String compileDateTime() {
		return MyClassCompileInfo.localDateTime().toString();
	}

	public String jenkinsBuildUrl() {
		return MyClassCompileInfo.get("env.BUILD_URL");
	}
}
