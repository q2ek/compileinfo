package net.q2ek.compileinfo;

import java.io.IOException;
import java.io.Writer;
import java.util.Properties;

/**
 * Any class implementing this interface generates a java class source code
 * content. This would them presumably be put into a file. It is used by the
 * {@link FileWriter} which is in turn used by
 * {@link CompileInfoAnnotationProcessor}
 *
 * @author Edze Kruizinga
 */
public interface ContentCreator {
	/**
	 * @throws IOProblem
	 *             when {@link IOException} happens
	 */
	void write(String packageName, String name);

	public interface Input {
		Writer writer();

		Properties properties();
	}
}
