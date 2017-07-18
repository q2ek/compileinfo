package net.q2ek.compileinfo.implementation.basics;

public interface ClassAttributes {
	CharSequence packagename();

	CharSequence classname();

	CharSequence name();

	static ClassAttributes of(
			CharSequence packagename,
			CharSequence classname) {
		return new ClassAttributes() {
			final String name = new StringBuilder()
					.append(packagename)
					.append(".")
					.append(classname)
					.toString();

			@Override
			public CharSequence packagename() {
				return packagename;
			}

			@Override
			public CharSequence classname() {
				return classname;
			}

			@Override
			public CharSequence name() {
				return name;
			}

			@Override
			public String toString() {
				return packagename() + "." + classname();
			}
		};
	}
}