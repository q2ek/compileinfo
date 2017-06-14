package net.q2ek.compileinfo.example;

import net.q2ek.compileinfo.CompileInfo;

@CompileInfo
class FirstExample {
	public String compileDateTime() {
		return FirstExampleCompileInfo.zonedDateTime().toString();
	}

	public String jenkinsBuildUrl() {
		return FirstExampleCompileInfo.properties().get("env.BUILD_URL");
	}
}
