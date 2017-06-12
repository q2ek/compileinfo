package net.q2ek.compileinfo.implementation;

import java.util.function.Function;

interface SourceCodeGeneratorFactory extends Function<Appender, SourceCodeGenerator> {
	// no content needed, this is a microtype
}
