package net.q2ek.compileinfo.implementation;

import java.util.function.Function;

import net.q2ek.compileinfo.implementation.basics.Appender;

interface PropertyWriterFactory extends Function<Appender, PropertyWriter> {
	// no content needed, this is a microtype
}
