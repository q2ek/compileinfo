package net.q2ek.compileinfo.implementation;

import net.q2ek.compileinfo.implementation.basics.PropertyMapCodeGenerator;

class NoopPropertyWriter implements PropertyMapCodeGenerator {
	public NoopPropertyWriter() {
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
