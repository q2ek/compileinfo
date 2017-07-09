# CompileInfo

This [Java][java] library contains the [CompileInfo](src/main/java/net/q2ek/compileinfo/CompileInfo.java) annotation which provides a convenient way to get access to information that was available at compile-time.

More specifically it is an annotation processor that captures the time and system properties at compile time and stores them in a generated Java class. This is helpful for devops purposes, like in a delivery pipeline. It allows developers, operations and testers to know exactly when and where an application was built, if these properties are exposed by the application at runtime.

## Usage

You will need to include `compileinfo-${version}.jar` in your build.

### Usage with Maven:

In a Maven project, one would include the `net.q2ek:compileinfo` artifact as a "provided" dependency:

```xml
<dependency>
	<groupId>net.q2ek</groupId>
	<artifactId>compileinfo</artifactId>
	<version>0.16.0</version>
	<scope>provided</scope>
</dependency>
```

### IDE support

To support annotation processing in your IDE see: https://immutables.github.io/apt.html

### Examples

Example of typical basic usage:
```java
import net.q2ek.compileinfo.CompileInfo;

@CompileInfo
class FirstExample {
	public String compileDateTime() {
		return FirstExampleCompileInfo.zonedDateTime().toString();
	}

	public String jenkinsBuildUrl() {
		return FirstExampleCompileInfo.properties().get("env.BUILD_URL");
	}
}
```

All examples:
- [FirstExample](src/test/java/net/q2ek/compileinfo/example/FirstExample.java)

- [SecondExample](src/test/java/net/q2ek/compileinfo/example/SecondExample.java)

- [ThirdExample](src/test/java/net/q2ek/compileinfo/example/ThirdExample.java)

See [example_output.md](example_output.md) for the generated classes for these examples.

### API

See [API.md](API.md) for the API description.

## Built with

* [AutoService](https://github.com/google/auto/tree/master/service) - Google Auto Service
* [Maven](https://maven.apache.org/) - Dependency Management and the project is a standard Maven project.

## Releases

Available at Maven Central:
* 0.12.0
    - Uses base64 encoding to prevent code injection
* 0.13.0
    - Adds some options to the CompileInfo annotation
* 0.14.0
    - Renames most of the options for the CompileInfo annotation
    - Only reads system properties when needed.
* 0.15.0
    - Adds '@Generated' and '@SuppressWarnings' to the generated class and the generated class is package private.
    - The '@CompileTime' annotation now uses 'RetentionPolicy.SOURCE' instead of 'RetentionPolicy.CLASS'.
    - The '@CompileTime' annotation now has a 'regex' property filter, and 'classname' as a format string both of which replace previous options.
    - The generated class has fewer methods: it only has a method for the ZonedDateTime and a map of properties.
* 0.16.0
    - Added 'includeEnvironmentVariables' option to the CompileInfo annotation.

### Planned work

* Maybe release 1.0.0 or 1.0.0-rc1

## Authors

* [EdzeKruizinga](https://github.com/EdzeKruizinga)

## License

This project is licensed under the Apache 2.0 License - see the [LICENSE.md](LICENSE.md) file for details.

[java]: https://en.wikipedia.org/wiki/Java_(programming_language)
