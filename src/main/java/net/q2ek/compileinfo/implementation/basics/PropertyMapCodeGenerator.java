package net.q2ek.compileinfo.implementation.basics;

import java.util.function.Consumer;

public interface PropertyMapCodeGenerator {
	void write(Consumer<CharSequence> consumer);

	void imports(Consumer<CharSequence> consumer);
}
