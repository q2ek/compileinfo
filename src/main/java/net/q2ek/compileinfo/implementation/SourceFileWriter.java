package net.q2ek.compileinfo.implementation;

import java.io.IOException;
import java.io.Writer;

import javax.annotation.processing.Filer;
import javax.lang.model.element.TypeElement;
import javax.tools.FileObject;
import javax.tools.JavaFileObject;

import net.q2ek.compileinfo.implementation.basics.ClassAttributes;

class SourceFileWriter {
	private final Filer filer;

	SourceFileWriter(Filer filer) {
		this.filer = filer;
	}

	private JavaFileObject sourceFileFor(CharSequence name,
			TypeElement originatingType) throws IOException {
		return this.filer.createSourceFile(name, originatingType);
	}

	/**
	 * @throws IOProblem
	 *             when an {@link IOException} happens
	 */
	void write(ClassAttributes classAttributes, TypeElement annotatedClass,
			String content) {
		CharSequence filename = classAttributes.name();
		try {
			FileObject fileObject = sourceFileFor(filename, annotatedClass);
			writeFile(fileObject, content);
		} catch (IOException e) {
			throw new IOProblem("Could not create: " + classAttributes.name(), e);
		}
	}

	private static void writeFile(FileObject fileObject, String content) {
		try (Writer writer = fileObject.openWriter()) {
			writer.write(content);
		} catch (IOException e) {
			throw new IOProblem("Could not write: " + fileObject.getName(), e);
		}
	}

}
