package net.q2ek.compileinfo.implementation;

/**
 * Constructor parameter for classes that implement a ContentCreator.
 */
public interface PackageAndClassName {
	String packagename();

	String classname();

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