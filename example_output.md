# CompileInfo

See also: [README.md](README.md)

## Generated classes for the examples

Generated class for the first example:
```java
package net.q2ek.compileinfo.example;

import java.time.ZonedDateTime;
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
    date = "2017-07-19T01:20:42.926+02:00")
class FirstExampleCompileInfo {
    private static final ZonedDateTime ZONED_DATE_TIME = ZonedDateTime.parse(
            "2017-07-19T01:20:42.926+02:00[Europe/Amsterdam]");

    static ZonedDateTime zonedDateTime() {
        return ZONED_DATE_TIME;
    }

    private static final Map<String, String> PROPERTIES = buildPropertiesMap();

    static Map<String, String> properties() {
        return PROPERTIES;
    }

    private static Map<String, String> buildPropertiesMap() {
        MapBuilder builder = MapBuilder.builder();
        builder.put("ZG91YmxlImhhY2s=",
                    "aGFja1wiaGFjaw==");
        builder.put("aGFjaw==",
                    "XCJoYWNr");
        builder.put("QlVJTERfRVhBTVBMRQ==",
                    "VGhlIHRyb3VibGUgd2l0aCBoYXZpbmcgYW4gb3BlbiBtaW5kLCBvZiBjb3Vyc2UsIGlzIHRoYXQgcGVvcGxlIHdpbGwgaW5zaXN0IG9uIGNvbWluZyBhbG9uZyBhbmQgdHJ5aW5nIHRvIHB1dCB0aGluZ3MgaW4gaXQu");
        return builder.build();
    }

    private static final Map<String, String> GETENV = buildGetenvMap();

    static Map<String, String> getenv() {
        return GETENV;
    }

    private static Map<String, String> buildGetenvMap() {
        MapBuilder builder = MapBuilder.builder();
        builder.put("QlVJTERfVVJM",
                    "U29tZSBodW1hbnMgd291bGQgZG8gYW55dGhpbmcgdG8gc2VlIGlmIGl0IHdhcyBwb3NzaWJsZSB0byBkbyBpdC4gSWYgeW91IHB1dCBhIGxhcmdlIHN3aXRjaCBpbiBzb21lIGNhdmUgc29tZXdoZXJlLCB3aXRoIGEgc2lnbiBvbiBpdCBzYXlpbmcgJ0VuZC1vZi10aGUtV29ybGQgU3dpdGNoLiBQTEVBU0UgRE8gTk9UIFRPVUNIJywgdGhlIHBhaW50IHdvdWxkbid0IGV2ZW4gaGF2ZSB0aW1lIHRvIGRyeQ==");
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
    date = "2017-07-19T20:26:20.603+02:00")
class DevOpsData {
    private static final ZonedDateTime ZONED_DATE_TIME = ZonedDateTime.parse(
            "2017-07-19T20:26:20.603+02:00[Europe/Amsterdam]");

    static ZonedDateTime zonedDateTime() {
        return ZONED_DATE_TIME;
    }

    private static final Map<String, String> PROPERTIES = buildPropertiesMap();

    static Map<String, String> properties() {
        return PROPERTIES;
    }

    private static Map<String, String> buildPropertiesMap() {
        MapBuilder builder = MapBuilder.builder();
        builder.put("QlVJTERfRVhBTVBMRQ==",
                    "VGhlIHRyb3VibGUgd2l0aCBoYXZpbmcgYW4gb3BlbiBtaW5kLCBvZiBjb3Vyc2UsIGlzIHRoYXQgcGVvcGxlIHdpbGwgaW5zaXN0IG9uIGNvbWluZyBhbG9uZyBhbmQgdHJ5aW5nIHRvIHB1dCB0aGluZ3MgaW4gaXQu");
        return builder.build();
    }

    private static final Map<String, String> GETENV = buildGetenvMap();

    static Map<String, String> getenv() {
        return GETENV;
    }

    private static Map<String, String> buildGetenvMap() {
        MapBuilder builder = MapBuilder.builder();
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
    date = "2017-07-19T20:26:20.612+02:00")
class ThirdExample_Gen {
    private static final ZonedDateTime ZONED_DATE_TIME = ZonedDateTime.parse(
            "2017-07-19T20:26:20.612+02:00[Europe/Amsterdam]");

    static ZonedDateTime zonedDateTime() {
        return ZONED_DATE_TIME;
    }

    private static final Map<String, String> GETENV = buildGetenvMap();

    static Map<String, String> getenv() {
        return GETENV;
    }

    private static Map<String, String> buildGetenvMap() {
        MapBuilder builder = MapBuilder.builder();
        builder.put("QlVJTERfVVJM",
                    "U29tZSBodW1hbnMgd291bGQgZG8gYW55dGhpbmcgdG8gc2VlIGlmIGl0IHdhcyBwb3NzaWJsZSB0byBkbyBpdC4gSWYgeW91IHB1dCBhIGxhcmdlIHN3aXRjaCBpbiBzb21lIGNhdmUgc29tZXdoZXJlLCB3aXRoIGEgc2lnbiBvbiBpdCBzYXlpbmcgJ0VuZC1vZi10aGUtV29ybGQgU3dpdGNoLiBQTEVBU0UgRE8gTk9UIFRPVUNIJywgdGhlIHBhaW50IHdvdWxkbid0IGV2ZW4gaGF2ZSB0aW1lIHRvIGRyeQ==");
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
