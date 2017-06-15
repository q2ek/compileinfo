# CompileInfo

See also: [README.md](README.md)

## Generated classes for the examples

Generated class for the first example:
```java
package net.q2ek.compileinfo.example;

import java.time.ZonedDateTime;
import java.util.Base64;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Generated;

/**
 * @see net.q2ek.compileinfo.CompileInfo
 */
@SuppressWarnings({ "all" })
@Generated(
    value = { "net.q2ek.compileinfo.implementation.CompileInfoAnnotationProcessor" },
    date = "2017-06-15T22:39:45.045+02:00")
class FirstExampleCompileInfo {
    private static final ZonedDateTime ZONED_DATE_TIME = ZonedDateTime.parse(
            "2017-06-15T22:39:45.045+02:00[Europe/Amsterdam]");

    static ZonedDateTime zonedDateTime() {
        return ZONED_DATE_TIME;
    }

    private static final Map<String, String> PROPERTIES = createMap();

    static Map<String, String> properties() {
        return PROPERTIES;
    }

    private static Map<String, String> createMap() {
        MapBuilder builder = MapBuilder.builder();
        builder.put("ZG91YmxlImhhY2s=",
                    "aGFja1wiaGFjaw==");
        builder.put("aGFjaw==",
                    "XCJoYWNr");
        builder.put("amF2YS52ZXJzaW9u",
                    "U291cmNlQ29kZUdlbmVyYXRvclRlc3Q=");
        builder.put("amF2YWRvYyBoYWNrICovIGV4cGxvc2lvbnM=",
                    "bm90aGluZyByZWFsbHk=");
        builder.put("dXNlci5uYW1l",
                    "U291cmNlQ29kZUdlbmVyYXRvclRlc3Q=");
        return builder.build();
    }

    private static class MapBuilder {
        private final Base64.Decoder decoder = Base64.getDecoder();
        private final Map<String, String> map = new HashMap<>();
        
        static MapBuilder builder() {
            return new MapBuilder();
        }
        
        private void put(String key, String value) {
            map.put(new String(decoder.decode(key)), new String(decoder.decode(value)));
        }
        
        Map<String, String> build() {
            return Collections.unmodifiableMap(map);
        };
    }
}
```

Generated class for the second example:
```java
package net.q2ek.compileinfo.example;

import java.time.ZonedDateTime;
import java.util.Base64;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Generated;

/**
 * @see net.q2ek.compileinfo.CompileInfo
 */
@SuppressWarnings({ "all" })
@Generated(
    value = { "net.q2ek.compileinfo.implementation.CompileInfoAnnotationProcessor" },
    date = "2017-06-15T22:39:45.415+02:00")
class DevOpsData {
    private static final ZonedDateTime ZONED_DATE_TIME = ZonedDateTime.parse(
            "2017-06-15T22:39:45.415+02:00[Europe/Amsterdam]");

    static ZonedDateTime zonedDateTime() {
        return ZONED_DATE_TIME;
    }

    private static final Map<String, String> PROPERTIES = createMap();

    static Map<String, String> properties() {
        return PROPERTIES;
    }

    private static Map<String, String> createMap() {
        MapBuilder builder = MapBuilder.builder();
        builder.put("ZG91YmxlImhhY2s=",
                    "aGFja1wiaGFjaw==");
        builder.put("aGFjaw==",
                    "XCJoYWNr");
        builder.put("amF2YS52ZXJzaW9u",
                    "U291cmNlQ29kZUdlbmVyYXRvclRlc3Q=");
        builder.put("amF2YWRvYyBoYWNrICovIGV4cGxvc2lvbnM=",
                    "bm90aGluZyByZWFsbHk=");
        return builder.build();
    }

    private static class MapBuilder {
        private final Base64.Decoder decoder = Base64.getDecoder();
        private final Map<String, String> map = new HashMap<>();
        
        static MapBuilder builder() {
            return new MapBuilder();
        }
        
        private void put(String key, String value) {
            map.put(new String(decoder.decode(key)), new String(decoder.decode(value)));
        }
        
        Map<String, String> build() {
            return Collections.unmodifiableMap(map);
        };
    }
}
```

Generated class for the third example:
```java
package net.q2ek.compileinfo.example;

import java.time.ZonedDateTime;

import javax.annotation.Generated;

/**
 * @see net.q2ek.compileinfo.CompileInfo
 */
@SuppressWarnings({ "all" })
@Generated(
    value = { "net.q2ek.compileinfo.implementation.CompileInfoAnnotationProcessor" },
    date = "2017-06-15T22:39:45.525+02:00")
class ThirdExample_Gen {
    private static final ZonedDateTime ZONED_DATE_TIME = ZonedDateTime.parse(
            "2017-06-15T22:39:45.525+02:00[Europe/Amsterdam]");

    static ZonedDateTime zonedDateTime() {
        return ZONED_DATE_TIME;
    }

}
```
