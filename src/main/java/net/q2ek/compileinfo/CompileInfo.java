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
	 * <b>Beta version</b><br/>
	 * If set to false the generated class will not contain a map of system
	 * properties.
	 */
	boolean withPropertyMap() default true;

	/**
	 * <b>Beta version</b><br/>
	 * If any are given then only these system properties will be included in
	 * the generated class.
	 */
	String[] filterKeys() default {};

	/**
	 * <b>Beta version</b><br/>
	 * Add something other than "CompileInfo" to the generated classname
	 */
	String extension() default "CompileInfo";

	/**
	 * If given this will be used as the generated classname instead of
	 * [classname] + "CompileInfo"
	 */
	String classname() default "";
}
