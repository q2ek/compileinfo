# CompileInfo

See also the top-level: [readme](README.md)

## API

Classes:
```java
net.q2ek.compileinfo.CompileInfo;
```
A class is generated named \<AnnotatedClassName\>CompileInfo, it has:
```java
static LocalDateTime localDateTime();
static ZonedDateTime zonedDateTime();

static Set<String> keySet();
static String get(String key);
```

## Versioning

This project uses [SemVer](http://semver.org/) for versioning.
