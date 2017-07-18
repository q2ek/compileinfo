# CompileInfo

See also: [README.md](README.md)

## Releases

Available at Maven Central:
* 0.12.0
    - Uses base64 encoding to prevent code injection
* 0.13.0
    - Adds some options to the `@CompileInfo` annotation
* 0.14.0
    - Renames most of the options for the `@CompileInfo` annotation
    - Only reads system properties when needed.
* 0.15.0
    - Adds '@Generated' and '@SuppressWarnings' to the generated class and the generated class is package private.
    - The `@CompileInfo` annotation now uses `RetentionPolicy.SOURCE` instead of `RetentionPolicy.CLASS`.
    - The `@CompileInfo` annotation now has a `regex` property filter, and `classname` as a format string. Both of which replace previous options.
    - The generated class has fewer methods: it only has a method for the ZonedDateTime and a map of properties.
* 0.16.0
    - Added `includeEnvironmentVariables` option to the `@CompileInfo` annotation.
* 0.17.0
    - Write each generated file all at once.

### Planned work

* Maybe release 1.0.0 or 1.0.0-rc1
