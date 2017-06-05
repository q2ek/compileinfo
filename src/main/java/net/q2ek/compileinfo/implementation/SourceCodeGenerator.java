package net.q2ek.compileinfo.implementation;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;

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
	void write(WriteParameters parameters);

	/**
	 * Constructor parameter for classes that implement a ContentCreator.
	 */
	public interface ConstructorParameters {
		Writer writer();

		public static SourceCodeGenerator.ConstructorParameters of(
				Writer writer) {
			return new SourceCodeGenerator.ConstructorParameters() {
				@Override
				public Writer writer() {
					return writer;
				}
			};
		}
	}

	/**
	 * Constructor parameter for classes that implement a ContentCreator.
	 */
	public interface WriteParameters {
		PackageAndClassName packageAndClassName();

		boolean addProperties();

		Map<String, String> properties();

		public static WriteParameters of(
				Map<String, String> properties,
				PackageAndClassName packageAndClassName,
				boolean addProperties) {
			return new WriteParameters() {
				@Override
				public PackageAndClassName packageAndClassName() {
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
