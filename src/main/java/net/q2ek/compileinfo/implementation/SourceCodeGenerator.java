package net.q2ek.compileinfo.implementation;

import java.io.IOException;
import java.io.Writer;
import java.util.Properties;

/**
 * Any class implementing this interface generates java class source code
 * content. This would then presumably be put into a file.
 *
 * @author Edze Kruizinga
 */
public interface SourceCodeGenerator {
	/**
	 * @throws IOProblem
	 *             when {@link IOException} happens
	 */
	void write(PackageAndClassName parameters);

	/**
	 * Constructor parameter for classes that implement a ContentCreator.
	 */
	public interface ConstructorParameters {
		Writer writer();

		Properties properties();
	}
}
