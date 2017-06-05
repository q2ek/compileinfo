package net.q2ek.compileinfo;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;
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

import net.q2ek.compileinfo.implementation.ClassAttributes;
import net.q2ek.compileinfo.implementation.FileObjectWriter;
import net.q2ek.compileinfo.implementation.IOProblem;
import net.q2ek.compileinfo.implementation.PropertiesProcessor;
import net.q2ek.compileinfo.implementation.SourceCodeGenerator.WriteParameters;

/**
 * This is the annotation processor for the {@link CompileInfo} annotation.
 *
 * @author Edze Kruizinga
 */
@AutoService(Processor.class)
public class CompileInfoAnnotationProcessor extends AbstractProcessor {
	private Filer filer;
	private Messager messager;
	private final FileObjectWriter writer = FileObjectWriter.base64();
	private final PropertiesProcessor properties = PropertiesProcessor.of(System.getProperties());

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
		messagerNote("Annotated:\t " + roundEnv.getElementsAnnotatedWith(CompileInfo.class));
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
		WriteParameters parameters = parametersFor(value);
		FileObject resource = resourceFor(parameters.packageAndClassName());
		this.writer.writeFile(resource, parameters);
	}

	private void messagerNote(String message) {
		this.messager.printMessage(Kind.NOTE, message);
	}

	private FileObject resourceFor(ClassAttributes packageAndClassName) throws IOException {
		FileObject resource = this.filer.createResource(
				StandardLocation.SOURCE_OUTPUT,
				packageAndClassName.packagename(),
				packageAndClassName.classname() + ".java");
		messagerNote("resource:\t " + resource.getName());
		return resource;
	}

	private WriteParameters parametersFor(TypeElement value) {
		ClassAttributes packageAndClassName = packageAndClassNameOf(value);
		boolean generateProperties = value.getAnnotation(CompileInfo.class).generateProperties();
		Map<String, String> properties = useProperties(value);
		return WriteParameters.of(properties, packageAndClassName, generateProperties);
	}

	private Map<String, String> useProperties(TypeElement value) {
		String[] includeProperties = value.getAnnotation(CompileInfo.class).includeProperties();
		return this.properties.filtered(includeProperties);
	}

	private ClassAttributes packageAndClassNameOf(TypeElement value) {
		Name packageName = ((PackageElement) value.getEnclosingElement()).getQualifiedName();
		String classname = classnameFor(value, value.getAnnotation(CompileInfo.class).classname());
		return ClassAttributes.of(packageName, classname);
	}

	private String classnameFor(TypeElement value, String classname) {
		if (classname.isEmpty()) {
			return value.getSimpleName() + value.getAnnotation(CompileInfo.class).extension();
		} else {
			return classname;
		}
	}

}
