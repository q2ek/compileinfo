package net.q2ek.compileinfo.implementation;

import java.io.IOException;
import java.util.Map;

/**
 * Any class implementing this interface generates java class source code
 * content. This would then presumably be put into a file.
 *
 * @author Edze Kruizinga
 */
interface SourceCodeGenerator {
	/**
	 * @throws IOProblem
	 *             when {@link IOException} happens
	 */
	void write(WriteParameters parameters);

	/**
	 * Constructor parameter for classes that implement a ContentCreator.
	 */
	interface WriteParameters {
		ClassAttributes packageAndClassName();

		boolean addProperties();

		Map<String, String> properties();

		static WriteParameters of(
				Map<String, String> properties,
				ClassAttributes packageAndClassName,
				boolean addProperties) {
			return new WriteParameters() {
				@Override
				public ClassAttributes packageAndClassName() {
					return packageAndClassName;
				}

				@Override
				public boolean addProperties() {
					return addProperties;
				}

				@Override
				public Map<String, String> properties() {
					return properties;
				}
			};
		}
	}
}
