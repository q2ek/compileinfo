package net.q2ek.compileinfo;

import static java.lang.annotation.RetentionPolicy.CLASS;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Annotate a class to generate {@code <AnnotatedClassNamex>CompileInfo.java}
 * <br/>
 *
 * Example: <br/>
 *
 * <pre>
 * <code>import net.q2ek.compileinfo.CompileInfo;
 *
 * {@literal @}CompileInfo
 * class MyClass {
 * 	public String compileTime() {
 * 		return MyClassCompileInfo.localDateTime().toString();
 * 	}
 *
 * 	public String jenkinsBuildUrl() {
 * 		return MyClassCompileInfo.get("env.BUILD_URL");
 * 	}
 * }
 * </code>
 * </pre>
 *
 * @author Edze Kruizinga
 */
@Retention(CLASS)
@Target(ElementType.TYPE)
public @interface CompileInfo {
	/**
	 * If set to false the generated class will not contain a map of system
	 * properties.
	 */
	boolean includeSystemProperties() default true;

	/**
	 * System properties will be filtered using this regex.
	 */
	String regex() default ".*";

	/**
	 * Add something other than "CompileInfo" to the generated classname
	 */
	String extension() default "CompileInfo";

	/**
	 * If given this will be used as the generated classname instead of
	 * [classname] + "CompileInfo"
	 */
	String classname() default "";
}
