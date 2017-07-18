package net.q2ek.compileinfo.implementation;

import net.q2ek.compileinfo.implementation.basics.Appender;

public class StringAppender implements Appender {
	private StringBuilder builder = new StringBuilder();

	@Override
	public void accept(CharSequence value) {
		this.builder.append(value);
	}

	public String content() {
		return this.builder.toString();
	}

}
