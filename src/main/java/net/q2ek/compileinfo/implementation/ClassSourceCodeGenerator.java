package net.q2ek.compileinfo.implementation;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import net.q2ek.compileinfo.CompileInfo;
import net.q2ek.compileinfo.implementation.basics.Appender;
import net.q2ek.compileinfo.implementation.basics.ClassAttributes;
import net.q2ek.compileinfo.implementation.basics.PropertyMapCodeGenerator;
import net.q2ek.compileinfo.implementation.basics.SourceCodeGenerator;

/**
 * Generates the java class source code.
 *
 * @see SourceCodeGenerator
 *
 * @author Edze Kruizinga
 */
class ClassSourceCodeGenerator implements SourceCodeGenerator {
	private final Class<?> annotationProcessorClass;
	private final ClassAttributes attributes;
	private final Appender appender;
	private final PropertyMapCodeGenerator propertyMapper;

	ClassSourceCodeGenerator(
		Class<?> annotationProcessor,
		ClassAttributes attributes,
		Appender appender,
		PropertyMapCodeGenerator propertyMapper) {
		this.annotationProcessorClass = annotationProcessor;
		this.attributes = attributes;
		this.appender = appender;
		this.propertyMapper = propertyMapper;
	}

	private void append(CharSequence value) {
		this.appender.accept(value);
	}

	@Override
	public void write() {
		ZonedDateTime now = ZonedDateTime.now();
		packageDeclaration(this.attributes.packagename());
		imports();
		classJavaDoc();
		suppressWarnings();
		generatedAnnotation(now);
		classDeclaration(this.attributes.classname());
		isoZonedDateTimeConstant(now);
		writeZonedDateTime();
		this.propertyMapper.write(this.appender);
		classEnd();
	}

	private void packageDeclaration(CharSequence packagename) {
		append("package " + packagename + ";\n\n");
	}

	private void imports() {
		append("import java.time.ZonedDateTime;\n");
		this.propertyMapper.imports(this.appender);
		append("\n");
		append("import javax.annotation.Generated;\n");
		append("\n");
	}

	private void classJavaDoc() {
		append("/**\n");
		append(" * @see " + CompileInfo.class.getCanonicalName() + "\n");
		append(" */\n");
	}

	private void classDeclaration(CharSequence classname) {
		append("class " + classname + " {\n");
	}

	private void suppressWarnings() {
		append("@SuppressWarnings({ \"all\" })\n");
	}

	private void generatedAnnotation(ZonedDateTime now) {
		String canonicalName = this.annotationProcessorClass.getCanonicalName();
		String nowFormatted = now.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
		append("@Generated(\n");
		append("    value = { \"" + canonicalName + "\" },\n");
		append("    date = \"" + nowFormatted + "\")\n");
	}

	private void classEnd() {
		append("}\n");
	}

	private void isoZonedDateTimeConstant(ZonedDateTime now) {
		String nowFormatted = now.format(DateTimeFormatter.ISO_DATE_TIME);
		append("    private static final ZonedDateTime ZONED_DATE_TIME = ZonedDateTime.parse(\n");
		append("            \"" + nowFormatted + "\");\n\n");
	}

	private void writeZonedDateTime() {
		append("    static ZonedDateTime zonedDateTime() {\n");
		append("        return ZONED_DATE_TIME;\n");
		methodEnd();
	}

	private void methodEnd() {
		append("    }\n\n");
	}
}
