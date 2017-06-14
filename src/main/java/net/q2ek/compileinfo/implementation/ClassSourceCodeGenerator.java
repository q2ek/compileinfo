package net.q2ek.compileinfo.implementation;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import net.q2ek.compileinfo.CompileInfo;
import net.q2ek.compileinfo.implementation.basics.Appender;
import net.q2ek.compileinfo.implementation.basics.ClassAttributes;
import net.q2ek.compileinfo.implementation.basics.PropertyMapCodeGenerator;
import net.q2ek.compileinfo.implementation.basics.PropertyWriterFactory;
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
	private final PropertyMapCodeGenerator propertyWriter;

	ClassSourceCodeGenerator(
		Class<?> annotationProcessor,
		ClassAttributes attributes,
		Appender appender,
		PropertyWriterFactory factory) {
		this.annotationProcessorClass = annotationProcessor;
		this.attributes = attributes;
		this.appender = appender;
		this.propertyWriter = factory.apply(appender);
	}

	private void append(CharSequence value) {
		this.appender.accept(value);
	}

	@Override
	public void write() {
		packageDeclaration(this.attributes.packagename());
		imports(this.propertyWriter.needsMapImport());
		classJavaDoc();
		classDeclaration(this.attributes.classname());
		isoZonedDateTimeConstant();
		writeZonedDateTime();
		this.propertyWriter.write();
		classEnd();
	}

	private void packageDeclaration(CharSequence packagename) {
		append("package " + packagename + ";\n\n");
	}

	private void imports(boolean propertiesMap) {
		append("import javax.annotation.Generated;\n");
		append("import java.time.ZonedDateTime;\n");
		if (propertiesMap) {
			append("import java.util.Map;\n");
		}
		append("\n");
	}

	private void classJavaDoc() {
		append("/**\n");
		append(" * @see " + CompileInfo.class.getCanonicalName() + "\n");
		append(" */\n");
	}

	private void classDeclaration(CharSequence classname) {
		String now = ZonedDateTime.now().format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
		append("@SuppressWarnings({ \"all\" })\n");
		generatedAnnotation(now);
		append("public class " + classname + "\n");
		append("{\n");
	}

	private void generatedAnnotation(String now) {
		String canonicalName = this.annotationProcessorClass.getCanonicalName();
		append("@Generated(\n");
		append("    value = { \"" + canonicalName + "\" },\n");
		append("    date = \"" + now + "\")\n");
	}

	private void classEnd() {
		append("}\n");
	}

	private void isoZonedDateTimeConstant() {
		append("    private static final ZonedDateTime ZONED_DATE_TIME = ZonedDateTime.parse(\n");
		append("            \"");
		append(ZonedDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME));
		append("\");\n");
		append("    \n");
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
