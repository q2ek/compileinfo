package net.q2ek.compileinfo.implementation;

import java.util.function.Function;

interface PropertyWriterFactory extends Function<Appender, PropertyWriter> {
	// no content needed, this is a microtype
}
