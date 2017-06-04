package net.q2ek.compileinfo;

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
import javax.lang.model.element.Name;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic.Kind;
import javax.tools.FileObject;
import javax.tools.StandardLocation;

import com.google.auto.service.AutoService;

import net.q2ek.compileinfo.implementation.FileWriter;
import net.q2ek.compileinfo.implementation.IOProblem;
import net.q2ek.compileinfo.implementation.PackageAndClassName;

/**
 * This is the annotation processor for the {@link CompileInfo} annotation.
 *
 * @author Edze Kruizinga
 */
@AutoService(Processor.class)
public class CompileInfoAnnotationProcessor extends AbstractProcessor {
	private Filer filer;
	private Messager messager;
	private final FileWriter writer = FileWriter.base64();

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
		this.messager.printMessage(Kind.NOTE, "Annotated:\t " + roundEnv.getElementsAnnotatedWith(CompileInfo.class));
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
		Name packageName = ((PackageElement) value.getEnclosingElement()).getQualifiedName();
		String name = generatedClassName(value);
		FileObject resource = this.filer.createResource(StandardLocation.SOURCE_OUTPUT, packageName, name + ".java");
		this.messager.printMessage(Kind.NOTE, "resource:\t " + resource.getName());
		this.writer.writeFile(PackageAndClassName.of(packageName.toString(), name), resource);
	}

	private String generatedClassName(TypeElement value) {
		return value.getSimpleName() + "CompileInfo";
	}
}
