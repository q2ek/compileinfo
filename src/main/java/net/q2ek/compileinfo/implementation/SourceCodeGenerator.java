package net.q2ek.compileinfo.implementation;

/**
 * Any class implementing this interface generates java class source code
 * content. This would then presumably be put into a file.
 *
 * @author Edze Kruizinga
 */
interface SourceCodeGenerator {
	void write();
}
