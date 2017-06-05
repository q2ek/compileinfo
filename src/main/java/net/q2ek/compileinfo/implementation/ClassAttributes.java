package net.q2ek.compileinfo.implementation;

/**
 * Constructor parameter for classes that implement a ContentCreator.
 */
interface ClassAttributes {
	CharSequence packagename();

	CharSequence classname();

	static ClassAttributes of(
			CharSequence packagename,
			CharSequence classname) {
		return new ClassAttributes() {
			@Override
			public CharSequence packagename() {
				return packagename;
			}

			@Override
			public CharSequence classname() {
				return classname;
			}

			@Override
			public String toString() {
				return packagename() + "." + classname();
			}
		};
	}
}