package net.q2ek.compileinfo.implementation;

import java.util.SortedMap;
import java.util.function.Predicate;
import java.util.regex.Pattern;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class PropertiesProcessorTest {

	@Test
	public void regexForJavaVersion_filtersJavaVersion() {
		PropertiesProcessor processor = PropertiesProcessor.of(SourceCodeTestHelper.properties());
		Predicate<String> predicate = Pattern.compile("java\\.version").asPredicate();
		SortedMap<String, String> filtered = processor.filtered(predicate);
		Assertions.assertThat(filtered).hasSize(1);
	}
}
