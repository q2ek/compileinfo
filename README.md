# CompileInfo

This library contains the CompileInfo annotation which provides a convenient way to get compile time information in a [Java][java] application.

More specifically it is an annotation processor that captures the time and system properties at compile time and stores them in a generated Java class. This is helpful for devops purposes or in any delivery pipeline. It allows developers, operations and testers to know exactly when and where an application was built if these properties are exposed by the application at runtime.

## Usage

To use in a Maven project:

In order to activate metadata generation you will need to include 
`compileinfo-${version}.jar` in your build at compile time.

In a Maven project, one would include the `net.q2ek:compileinfo` artifact as a "compile" dependency:

```xml
<dependency>
	<groupId>net.q2ek</groupId>
	<artifactId>compileinfo</artifactId>
	<version>0.14.0</version>
	<scope>compile</scope>
</dependency>
```

To support annotation processing in your IDE see:
https://immutables.github.io/apt.html

### Examples

Example of typical basic usage:
```java

import net.q2ek.compileinfo.CompileInfo;

@CompileInfo
class FirstExample {
	public String compileDateTime() {
		return FirstExampleCompileInfo.localDateTime().toString();
	}

	public String jenkinsBuildUrl() {
		return FirstExampleCompileInfo.get("env.BUILD_URL");
	}
}
```

Example of use of options for the CompileInfo annotation:
```java

import net.q2ek.compileinfo.CompileInfo;

@CompileInfo(classname = "DevOpsData", filterKeys = { "env.BUILD_URL" })
public class SecondExample {
	public String compileDateTime() {
		return DevOpsData.localDateTime().toString();
	}

	public String jenkinsBuildUrl() {
		return DevOpsData.get("env.BUILD_URL");
	}
}
```

Example of some more options for the CompileInfo annotation:
```java
import net.q2ek.compileinfo.CompileInfo;

@CompileInfo(extension = "_Gen", withPropertyMap = false)
public class ThirdExample {
	public String compileDateTime() {
		return ThirdExample_Gen.localDateTime().toString();
	}
}
```

### API

See [API.md](API.md) for the API description.

## Built With

* [AutoService](https://github.com/google/auto/tree/master/service) - Google Auto Service
* [Maven](https://maven.apache.org/) - Dependency Management

The project is a standard Maven project.

## Releases

Available at Maven Central:
* 0.11.0 is available at Maven Central
* 0.12.0 Uses base64 encoding to prevent code injection
* 0.13.0 Adds some options to the CompileInfo annotation
* 0.14.0 Renames most of the options for the CompileInfo annotation and system properties are only read when needed.

### Planned work

* Maybe release 1.0.0 or 1.0.0-rc1

## Authors

* [EdzeKruizinga](https://github.com/EdzeKruizinga) - *Initial work*

## License

This project is licensed under the Apache 2.0 License - see the [LICENSE.md](LICENSE.md) file for details

[java]: https://en.wikipedia.org/wiki/Java_(programming_language)
