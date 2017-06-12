package net.q2ek.compileinfo.implementation;

import java.util.SortedMap;

import javax.lang.model.element.Name;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;

import net.q2ek.compileinfo.CompileInfo;
import net.q2ek.compileinfo.implementation.basics.ClassAttributes;
import net.q2ek.compileinfo.implementation.basics.PropertyWriterFactory;
import net.q2ek.compileinfo.implementation.basics.SourceCodeGeneratorFactory;

class TypeElementProcessor {
	private final PropertiesProcessor properties = PropertiesProcessor.of(System.getProperties());
	private final TypeElement value;
	private final CompileInfo annotation;
	private ClassAttributes classAttributes;
	private SourceCodeGeneratorFactory sourceCodeGeneratorFactory;

	private TypeElementProcessor(TypeElement value) {
		this.value = value;
		this.annotation = value.getAnnotation(CompileInfo.class);
	}

	static TypeElementProcessor of(TypeElement value) {
		return new TypeElementProcessor(value);
	}

	public ClassAttributes classAttributes() {
		if (this.classAttributes == null) {
			this.classAttributes = findClassAttributes();
		}
		return this.classAttributes;
	}

	public SourceCodeGeneratorFactory sourceCodeGeneratorFactory() {
		if (this.sourceCodeGeneratorFactory == null) {
			PropertyWriterFactory propertyWriterFactory = propertyWriterFactory();
			this.sourceCodeGeneratorFactory = appender -> new Base64SourceCodeGenerator(
					this.classAttributes,
					appender,
					propertyWriterFactory);
		}
		return this.sourceCodeGeneratorFactory;
	}

	private PropertyWriterFactory propertyWriterFactory() {
		if (generateProperties()) {
			return appender -> new Base64PropertyWriter(appender, filterProperties());
		} else {
			return unused -> new NoopPropertyWriter();
		}
	}

	private SortedMap<String, String> filterProperties() {
		String[] includeProperties = this.annotation.includeProperties();
		return this.properties.filtered(includeProperties);
	}

	private ClassAttributes findClassAttributes() {
		Name packageName = ((PackageElement) this.value.getEnclosingElement()).getQualifiedName();
		String classname = classnameFor(this.annotation.classname());
		return ClassAttributes.of(packageName, classname);
	}

	private boolean generateProperties() {
		return this.annotation.generateProperties();
	}

	private String classnameFor(String classname) {
		if (classname.isEmpty()) {
			return this.value.getSimpleName() + this.annotation.extension();
		} else {
			return classname;
		}
	}
}
