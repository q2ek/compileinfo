# CompileInfo

This [Java][java] library contains the [CompileInfo](src/main/java/net/q2ek/compileinfo/CompileInfo.java) annotation which provides a convenient way to get access to information that was available at compile-time.

More specifically it is an annotation processor that captures the time and system properties at compile time and stores them in a generated Java class. This is helpful for devops purposes, like in a delivery pipeline. It allows developers, operations and testers to know exactly when and where an application was built, if these properties are exposed by the application at runtime.

## Usage

You will need to include `compileinfo-${version}.jar` in your build.

### Usage with Maven:

In a Maven project, one would include the `net.q2ek:compileinfo` artifact as a `provided` dependency:

```xml
<dependency>
	<groupId>net.q2ek</groupId>
	<artifactId>compileinfo</artifactId>
	<version>1.0.0-rc1</version>
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
		return FirstExampleCompileInfo.getenv().get("BUILD_URL");
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

See [RELEASES.md](RELEASES.md) for a list of the release history.

## Authors

* [Edze Kruizinga](https://github.com/EdzeKruizinga)

## License

This project is licensed under the Apache 2.0 License - see the [LICENSE.md](LICENSE.md) file for details.

[java]: https://en.wikipedia.org/wiki/Java_(programming_language)
