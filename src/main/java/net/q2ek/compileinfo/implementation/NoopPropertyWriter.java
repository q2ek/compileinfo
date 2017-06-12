package net.q2ek.compileinfo.implementation;

class NoopPropertyWriter implements PropertyWriter {
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
