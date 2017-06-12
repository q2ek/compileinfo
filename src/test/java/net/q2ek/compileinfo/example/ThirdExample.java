package net.q2ek.compileinfo.example;

import net.q2ek.compileinfo.CompileInfo;

@CompileInfo(extension = "_Gen", generateProperties = false)
public class ThirdExample {
	public String compileDateTime() {
		return ThirdExample_Gen.localDateTime().toString();
	}
}
