package net.q2ek.compileinfo.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;

import net.q2ek.compileinfo.CompileInfo;
import net.q2ek.compileinfo.implementation.basics.ClassAttributes;
import net.q2ek.compileinfo.implementation.basics.MapDefinition;
import net.q2ek.compileinfo.implementation.basics.PropertyMapCodeGenerator;
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
			PropertyMapCodeGenerator propertyMapGenerator = propertyMapGenerator();
			this.sourceCodeGeneratorFactory = ClassSourceCodeGenerator.factory(
					this.annotationProcessorClass,
					this.classAttributes,
					propertyMapGenerator);
		}
		return this.sourceCodeGeneratorFactory;
	}

	private PropertyMapCodeGenerator propertyMapGenerator() {
		List<MapDefinition> definitions = new ArrayList<>();
		if (this.annotation.includeSystemProperties()) {
			definitions.add(MapDefinition.of(systemProperties(), "PROPERTIES"));
		}
		if (this.annotation.includeEnvironmentVariables()) {
			definitions.add(MapDefinition.of(environmentVariables(), "GETENV"));
		}
		if (definitions.isEmpty()) {
			return new NoopPropertyMapCodeGenerator();
		} else {
			return new Base64PropertyMapCodeGenerator(definitions);
		}
	}

	private Map<String, String> environmentVariables() {
		PropertiesProcessor properties = PropertiesProcessor.of(System.getenv());
		Pattern pattern = Pattern.compile(this.annotation.regex());
		return properties.filtered(pattern.asPredicate());
	}

	private Map<String, String> systemProperties() {
		PropertiesProcessor properties = PropertiesProcessor.of(System.getProperties());
		Pattern pattern = Pattern.compile(this.annotation.regex());
		return properties.filtered(pattern.asPredicate());
	}

	private CharSequence packagename() {
		return ((PackageElement) this.value.getEnclosingElement()).getQualifiedName();
	}

	private CharSequence classname() {
		return String.format(this.annotation.classname(), this.value.getSimpleName());
	}
}
