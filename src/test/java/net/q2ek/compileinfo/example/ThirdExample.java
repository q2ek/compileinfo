package net.q2ek.compileinfo.example;

import java.time.format.DateTimeFormatter;

import net.q2ek.compileinfo.CompileInfo;

@CompileInfo(
	classname = "%s_Gen",
	includeSystemProperties = false,
	includeEnvironmentVariables = true,
	regex = "BUILD.*")
public class ThirdExample {
	public String compileDateTime() {
		return ThirdExample_Gen.zonedDateTime().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME).toString();
	}

	public String jenkinsBuildUrl() {
		return ThirdExample_Gen.getenv().get("BUILD_URL");
	}
}
