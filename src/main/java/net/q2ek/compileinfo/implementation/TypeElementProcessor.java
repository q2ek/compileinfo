package net.q2ek.compileinfo.implementation;

import java.util.SortedMap;
import java.util.regex.Pattern;

import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;

import net.q2ek.compileinfo.CompileInfo;
import net.q2ek.compileinfo.implementation.basics.ClassAttributes;
import net.q2ek.compileinfo.implementation.basics.PropertyWriterFactory;
import net.q2ek.compileinfo.implementation.basics.SourceCodeGeneratorFactory;

class TypeElementProcessor {
	private final Class<?> annotationProcessorClass;
	private final TypeElement value;
	private final CompileInfo annotation;
	private ClassAttributes classAttributes;
	private SourceCodeGeneratorFactory sourceCodeGeneratorFactory;

	private TypeElementProcessor(Class<?> annotationProcessorClass, TypeElement value) {
		this.annotationProcessorClass = annotationProcessorClass;
		this.value = value;
		this.annotation = value.getAnnotation(CompileInfo.class);
	}

	static TypeElementProcessor of(Class<?> annotationProcessorClass, TypeElement value) {
		return new TypeElementProcessor(annotationProcessorClass, value);
	}

	public ClassAttributes classAttributes() {
		if (this.classAttributes == null) {
			this.classAttributes = ClassAttributes.of(packagename(), classname());
		}
		return this.classAttributes;
	}

	public SourceCodeGeneratorFactory sourceCodeGeneratorFactory() {
		if (this.sourceCodeGeneratorFactory == null) {
			PropertyWriterFactory propertyWriterFactory = propertyWriterFactory();
			this.sourceCodeGeneratorFactory = appender -> new ClassSourceCodeGenerator(
					this.annotationProcessorClass,
					this.classAttributes,
					appender,
					propertyWriterFactory);
		}
		return this.sourceCodeGeneratorFactory;
	}

	private PropertyWriterFactory propertyWriterFactory() {
		if (this.annotation.includeSystemProperties()) {
			return appender -> new Base64PropertyWriter(appender, properties());
		} else {
			return unused -> new NoopPropertyWriter();
		}
	}

	private SortedMap<String, String> properties() {
		PropertiesProcessor properties = PropertiesProcessor.of(System.getProperties());
		Pattern pattern = Pattern.compile(this.annotation.regex());
		return properties.filtered(pattern.asPredicate());
	}

	private CharSequence packagename() {
		return ((PackageElement) this.value.getEnclosingElement()).getQualifiedName();
	}

	private CharSequence classname() {
		String classname = this.annotation.classname();
		if (classname.isEmpty()) {
			return this.value.getSimpleName() + this.annotation.extension();
		} else {
			return classname;
		}
	}
}
