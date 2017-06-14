package net.q2ek.compileinfo.example;

import java.time.format.DateTimeFormatter;

import net.q2ek.compileinfo.CompileInfo;

@CompileInfo(classname = "DevOpsData", regex = "env\\..*")
public class SecondExample {
	public String compileDateTime() {
		return DevOpsData.zonedDateTime().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME).toString();
	}

	public String jenkinsBuildUrl() {
		return DevOpsData.properties().get("env.BUILD_URL");
	}
}
