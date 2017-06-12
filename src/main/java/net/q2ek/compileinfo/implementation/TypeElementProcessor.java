package net.q2ek.compileinfo.implementation;

import java.util.Arrays;
import java.util.SortedMap;

import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;

import net.q2ek.compileinfo.CompileInfo;
import net.q2ek.compileinfo.implementation.basics.ClassAttributes;
import net.q2ek.compileinfo.implementation.basics.PropertyWriterFactory;
import net.q2ek.compileinfo.implementation.basics.SourceCodeGeneratorFactory;

class TypeElementProcessor {
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
			this.classAttributes = ClassAttributes.of(packagename(), classname());
		}
		return this.classAttributes;
	}

	public SourceCodeGeneratorFactory sourceCodeGeneratorFactory() {
		if (this.sourceCodeGeneratorFactory == null) {
			PropertyWriterFactory propertyWriterFactory = propertyWriterFactory();
			this.sourceCodeGeneratorFactory = appender -> new ClassSourceCodeGenerator(
					this.classAttributes,
					appender,
					propertyWriterFactory);
		}
		return this.sourceCodeGeneratorFactory;
	}

	private PropertyWriterFactory propertyWriterFactory() {
		if (this.annotation.withPropertyMap()) {
			return appender -> new Base64PropertyWriter(appender, properties());
		} else {
			return unused -> new NoopPropertyWriter();
		}
	}

	private SortedMap<String, String> properties() {
		PropertiesProcessor properties = PropertiesProcessor.of(System.getProperties());
		String[] filterKeys = this.annotation.filterKeys();
		if (filterKeys.length == 0) {
			return properties.unfiltered();
		}
		return properties.filtered(Arrays.asList(filterKeys));
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
