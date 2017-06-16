package net.q2ek.compileinfo.example;

import net.q2ek.compileinfo.CompileInfo;

@CompileInfo
class FirstExample {
	public String compileDateTime() {
		return FirstExampleCompileInfo.zonedDateTime().toString();
	}

	public String javaVersion() {
		return FirstExampleCompileInfo.properties().get("java.version");
	}

	public String jenkinsBuildUrl() {
		return FirstExampleCompileInfo.getenv().get("env.BUILD_URL");
	}
}
