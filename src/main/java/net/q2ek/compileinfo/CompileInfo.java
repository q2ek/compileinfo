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
	 * If set to false the generated class will not process any system
	 * properties.
	 */
	boolean generateProperties() default true;

	/**
	 * If any are given then only these system properties will be read and
	 * included in the generated class.
	 */
	String[] includeProperties() default {};
}
