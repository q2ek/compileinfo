package net.q2ek.compileinfo.implementation;

import net.q2ek.compileinfo.implementation.basics.PropertyMapCodeGenerator;

class NoopPropertyMapCodeGenerator implements PropertyMapCodeGenerator {
	public NoopPropertyMapCodeGenerator() {
	}

	@Override
	public void write() {
		// do noting
	}

	@Override
	public boolean needsMapImport() {
		return false;
	}
}
