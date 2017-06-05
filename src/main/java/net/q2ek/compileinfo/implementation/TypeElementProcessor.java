package net.q2ek.compileinfo.implementation;

import java.util.Map;

import javax.lang.model.element.Name;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;

import net.q2ek.compileinfo.CompileInfo;
import net.q2ek.compileinfo.implementation.SourceCodeGenerator.WriteParameters;

public class TypeElementProcessor {
	private final PropertiesProcessor properties = PropertiesProcessor.of(System.getProperties());
	private final TypeElement value;
	private final CompileInfo annotation;

	private TypeElementProcessor(TypeElement value) {
		this.value = value;
		this.annotation = this.value.getAnnotation(CompileInfo.class);
	}

	static WriteParameters parametersFor(TypeElement value) {
		return new TypeElementProcessor(value).parameters();
	}

	private WriteParameters parameters() {
		return WriteParameters.of(
				filterProperties(),
				packageAndClassName(),
				generateProperties());
	}

	private Map<String, String> filterProperties() {
		String[] includeProperties = this.annotation.includeProperties();
		return this.properties.filtered(includeProperties);
	}

	private ClassAttributes packageAndClassName() {
		Name packageName = ((PackageElement) this.value.getEnclosingElement()).getQualifiedName();
		String classname = classnameFor(this.value, this.annotation.classname());
		return ClassAttributes.of(packageName, classname);
	}

	private boolean generateProperties() {
		return this.annotation.generateProperties();
	}

	private String classnameFor(TypeElement value, String classname) {
		if (classname.isEmpty()) {
			return value.getSimpleName() + this.annotation.extension();
		} else {
			return classname;
		}
	}
}
