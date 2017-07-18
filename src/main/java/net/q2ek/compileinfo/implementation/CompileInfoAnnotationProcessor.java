package net.q2ek.compileinfo.implementation;

import static javax.tools.Diagnostic.Kind.ERROR;
import static javax.tools.Diagnostic.Kind.WARNING;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Collections;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;

import com.google.auto.service.AutoService;

import net.q2ek.compileinfo.CompileInfo;

/**
 * This is the annotation processor for the {@link CompileInfo} annotation.
 *
 * @author Edze Kruizinga
 */
@AutoService(Processor.class)
public class CompileInfoAnnotationProcessor extends AbstractProcessor {
	private Messager			messager;
	private SourceFileWriter	sourceFileWriter;

	public CompileInfoAnnotationProcessor() {
		super();
	}

	@Override
	public synchronized void init(ProcessingEnvironment processingEnvironment) {
		super.init(processingEnvironment);
		this.messager = processingEnvironment.getMessager();
		this.sourceFileWriter = new SourceFileWriter(processingEnvironment.getFiler());
	}

	@Override
	public Set<String> getSupportedAnnotationTypes() {
		return Collections.singleton(CompileInfo.class.getCanonicalName());
	}

	@Override
	public SourceVersion getSupportedSourceVersion() {
		return SourceVersion.latestSupported();
	}

	@Override
	public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
		for (Element element : roundEnv.getElementsAnnotatedWith(CompileInfo.class)) {
			process((TypeElement) element);
		}
		return false;
	}

	private void process(TypeElement annotatedClass) {
		try {
			processUnsafe(annotatedClass);
		} catch (IOProblem e) {
			String stackTrace = stackTrace(e);
			String message = "CompileInfo processing failed due to: "
					+ e.getClass().getCanonicalName()
					+ ": "
					+ stackTrace;
			this.messager.printMessage(WARNING, message, annotatedClass);
		} catch (RuntimeException e) { // One of wich is IOProblem
			String stackTrace = stackTrace(e);
			String message = "CompileInfo processing failed due to: "
					+ e.getClass().getCanonicalName()
					+ ": "
					+ stackTrace;
			this.messager.printMessage(ERROR, message, annotatedClass);
		}
	}

	private String stackTrace(Exception e) {
		StringWriter sw = new StringWriter();
		e.printStackTrace(new PrintWriter(sw));
		return sw.toString();
	}

	private void processUnsafe(TypeElement annotatedClass) {
		TypeElementProcessor processor =
				TypeElementProcessor.of(CompileInfoAnnotationProcessor.class, annotatedClass);
		String content = generateSourceCodeWith(processor);
		this.sourceFileWriter.write(processor.classAttributes(), annotatedClass, content);
	}

	private String generateSourceCodeWith(TypeElementProcessor processor) {
		StringAppender appender = new StringAppender();
		processor.sourceCodeGeneratorFactory().apply(appender).write();
		return appender.content();
	}
}
