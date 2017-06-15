# CompileInfo

See also: [README.md](README.md)

## API

The first part of the interface is the [net.q2ek.compileinfo.CompileInfo](src/main/java/net/q2ek/compileinfo/CompileInfo.java) annotation.

A generated class has these methods:
```java
static ZonedDateTime zonedDateTime();

static Map<String, String> properties();
```

Java unit test for the API: [ApiTest.java](src/test/java/net/q2ek/compileinfo/api/ApiTest.java)

## Versioning

This project uses [SemVer](http://semver.org/) for versioning.
