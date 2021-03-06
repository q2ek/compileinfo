package net.q2ek.compileinfo.implementation;

import java.io.IOException;

/**
 * Wraps an {@link IOException} in a {@link RuntimeException}
 *
 * @author Edze Kruizinga
 */
class IOProblem extends RuntimeException {
	private static final long serialVersionUID = -5518438508622155938L;

	IOProblem(String message, IOException cause) {
		super(message, cause);
	}
}
