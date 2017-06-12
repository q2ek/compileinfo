package net.q2ek.compileinfo.implementation;

import java.io.IOException;
import java.util.Collections;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic.Kind;
import javax.tools.FileObject;
import javax.tools.StandardLocation;

import com.google.auto.service.AutoService;

import net.q2ek.compileinfo.CompileInfo;

/**
 * This is the annotation processor for the {@link CompileInfo} annotation.
 *
 * @author Edze Kruizinga
 */
@AutoService(Processor.class)
public class CompileInfoAnnotationProcessor extends AbstractProcessor {
	private Filer filer;
	private Messager messager;

	public CompileInfoAnnotationProcessor() {
		super();
	}

	@Override
	public synchronized void init(ProcessingEnvironment processingEnvironment) {
		super.init(processingEnvironment);
		this.filer = processingEnvironment.getFiler();
		this.messager = processingEnvironment.getMessager();
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

	private void process(TypeElement value) {
		try {
			processUnsafe(value);
		} catch (IOException | IOProblem e) {
			this.messager.printMessage(Kind.ERROR, e.getLocalizedMessage(), value);
		}
	}

	private void processUnsafe(TypeElement value) throws IOException {
		TypeElementProcessor processor = TypeElementProcessor.of(value);
		FileObject resource = resourceFor(processor.classAttributes());
		FileObjectWriter.writeFile(resource, processor.sourceCodeGeneratorFactory());
	}

	private FileObject resourceFor(ClassAttributes packageAndClassName) throws IOException {
		FileObject resource = this.filer.createResource(
				StandardLocation.SOURCE_OUTPUT,
				packageAndClassName.packagename(),
				packageAndClassName.classname() + ".java");
		return resource;
	}

}
