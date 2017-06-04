package net.q2ek.compileinfo.implementation;

import java.io.Writer;
import java.util.Properties;

import net.q2ek.compileinfo.implementation.SourceCodeGenerator.ConstructorParameters;

/**
 * Constructor parameter for classes that implement a ContentCreator.
 */
public interface PackageAndClassName {
	String packagename();

	String classname();

	/**
	 * Factory method
	 *
	 * @return a {@link ConstructorParameters} using the given {@link Writer}
	 *         and {@link Properties}
	 */
	public static PackageAndClassName of(String packagename,
			String classname) {
		return new PackageAndClassName() {
			@Override
			public String packagename() {
				return packagename;
			}

			@Override
			public String classname() {
				return classname;
			}

			@Override
			public String toString() {
				return packagename() + "." + classname();
			}
		};
	}
}