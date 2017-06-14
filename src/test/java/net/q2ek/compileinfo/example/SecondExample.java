package net.q2ek.compileinfo.example;

import net.q2ek.compileinfo.CompileInfo;

@CompileInfo(classname = "DevOpsData", regex = "env\\..*")
public class SecondExample {
	public String compileDateTime() {
		return DevOpsData.localDateTime().toString();
	}

	public String jenkinsBuildUrl() {
		return DevOpsData.get("env.BUILD_URL");
	}
}
