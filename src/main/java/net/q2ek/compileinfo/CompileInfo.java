package net.q2ek.compileinfo;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotate a class to generate {@code <AnnotatedClassNamex>CompileInfo.java}.
 * <br/>
 *
 * Example: <br/>
 *
 * <pre>
 * <code>
 * {@literal @}CompileInfo
 * class MyClass {
 * 	public String compileTime() {
 * 		return MyClassCompileInfo.zonedDateTime().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
 * 	}
 *
 * 	public String jenkinsBuildUrl() {
 * 		return MyClassCompileInfo.properties().get("env.BUILD_URL");
 * 	}
 * }
 * </code>
 * </pre>
 *
 * @author Edze Kruizinga
 */
@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.TYPE)
public @interface CompileInfo {
	/**
	 * This will be used as the format string for the generated classname
	 * instead of "%sCompileInfo"
	 */
	String classname() default "%sCompileInfo";

	/**
	 * If set to false no system properties will be read and the generated class
	 * will not contain a map of system properties.
	 */
	boolean includeSystemProperties() default true;

	/**
	 * If set to false no environment variables will be read and the generated
	 * class will not contain a map of environment variables.
	 */
	boolean includeEnvironmentVariables() default true;

	/**
	 * System properties and environment variables keys will be filtered using
	 * this regex.
	 */
	String regex() default ".*";
}
