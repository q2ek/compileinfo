package net.q2ek.compileinfo.implementation;

import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Set;

import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ElementVisitor;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.Name;
import javax.lang.model.element.NestingKind;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.TypeParameterElement;
import javax.lang.model.type.TypeMirror;

import org.junit.Test;
import org.mockito.Mockito;

import net.q2ek.compileinfo.CompileInfo;

public class TypeElementProcessorTest {
	@Test
	public void processing_works() {
		TypeElement value = new TestTypeElement();
		TypeElementProcessor processor = TypeElementProcessor.of(this.getClass(), value);
		StringAppender appender = new StringAppender();
		processor.sourceCodeGeneratorFactory().apply(appender).write();

		System.out.println(appender.content());
	}

	private static <A extends Annotation> CompileInfo compileInfo(Class<A> annotationType) {
		CompileInfo info = new CompileInfo() {
			@Override
			public Class<? extends Annotation> annotationType() {
				return annotationType;
			}

			@Override
			public String regex() {
				return ".*";
			}

			@Override
			public boolean includeSystemProperties() {
				return true;
			}

			@Override
			public boolean includeEnvironmentVariables() {
				return true;
			}

			@Override
			public String classname() {
				return "%sCompileInfo";
			}
		};
		return info;
	}

	private static class TestTypeElement implements TypeElement {
		Name name = Mockito.mock(Name.class);

		@Override
		public <R, P> R accept(ElementVisitor<R, P> v, P p) {
			throw new UnsupportedOperationException("Not implemented.");
		}

		@Override
		public TypeMirror asType() {
			throw new UnsupportedOperationException("Not implemented.");
		}

		@Override
		public <A extends Annotation> A getAnnotation(Class<A> annotationType) {
			CompileInfo info = compileInfo(annotationType);
			return (A) info;
		}

		@Override
		public List<? extends AnnotationMirror> getAnnotationMirrors() {
			throw new UnsupportedOperationException("Not implemented.");
		}

		@Override
		public ElementKind getKind() {
			throw new UnsupportedOperationException("Not implemented.");
		}

		@Override
		public Set<Modifier> getModifiers() {
			throw new UnsupportedOperationException("Not implemented.");
		}

		@Override
		public <A extends Annotation> A[] getAnnotationsByType(Class<A> annotationType) {
			throw new UnsupportedOperationException("Not implemented.");
		}

		@Override
		public List<? extends Element> getEnclosedElements() {
			throw new UnsupportedOperationException("Not implemented.");
		}

		@Override
		public Element getEnclosingElement() {
			PackageElement packageElement = Mockito.mock(PackageElement.class);
			Name packageName = Mockito.mock(Name.class);
			Mockito.when(packageElement.getQualifiedName()).thenReturn(packageName);
			return packageElement;
		}

		@Override
		public List<? extends TypeMirror> getInterfaces() {
			throw new UnsupportedOperationException("Not implemented.");
		}

		@Override
		public NestingKind getNestingKind() {
			throw new UnsupportedOperationException("Not implemented.");
		}

		@Override
		public Name getQualifiedName() {
			throw new UnsupportedOperationException("Not implemented.");
		}

		@Override
		public Name getSimpleName() {
			return this.name;
		}

		@Override
		public TypeMirror getSuperclass() {
			throw new UnsupportedOperationException("Not implemented.");
		}

		@Override
		public List<? extends TypeParameterElement> getTypeParameters() {
			throw new UnsupportedOperationException("Not implemented.");
		}

	}
}
