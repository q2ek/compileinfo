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
 * 		return MyClassCompileInfo.getenv().get("BUILD_URL");
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
	 * This will be used as the format string for the generated classname. The
	 * default setting is {@code "%sCompileInfo"}.
	 */
	String classname() default "%sCompileInfo";

	/**
	 * Determines whether or not system properties will be read and put into the
	 * generated class. The default setting is {@code true}. <br/>
	 *
	 * If {@code true} the generated class will contain:
	 * {@code static Map<String, String> properties();}
	 */
	boolean includeSystemProperties() default true;

	/**
	 * Determines whether or not environment variables will be read and put into
	 * the generated class. The default setting is {@code false}. <br/>
	 *
	 * If {@code true} the generated class will contain:
	 * {@code static Map<String, String> getenv();}
	 */
	boolean includeEnvironmentVariables() default true;

	/**
	 * System properties and environment variables keys will be filtered using
	 * this regex. The default setting is {@code ".*"}.
	 */
	String regex() default ".*";
}
