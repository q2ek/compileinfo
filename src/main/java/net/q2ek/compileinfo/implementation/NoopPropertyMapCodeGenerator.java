package net.q2ek.compileinfo.implementation;

import java.util.function.Consumer;

import net.q2ek.compileinfo.implementation.basics.PropertyMapCodeGenerator;

class NoopPropertyMapCodeGenerator implements PropertyMapCodeGenerator {
	public NoopPropertyMapCodeGenerator() {
		// nothing needed
	}

	@Override
	public void write(Consumer<CharSequence> consumer) {
		// do noting
	}

	@Override
	public void imports(Consumer<CharSequence> consumer) {
		// no imports needed
	}
}
