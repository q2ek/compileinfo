# CompileInfo

See also: [README.md](README.md)

## Generated classes for the examples

Generated class for the first example:
```java
package net.q2ek.compileinfo.example;

import javax.annotation.Generated;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Map;

/**
 * @see net.q2ek.compileinfo.CompileInfo
 */
@Generated(
    value = {"net.q2ek.compileinfo.implementation.CompileInfoAnnotationProcessor"},
    date = "2017-06-14T00:06:02.606")
public class FirstExampleCompileInfo
{
    private static final LocalDateTime LOCAL_DATE_TIME = LocalDateTime.parse("2017-06-14T00:06:02.607");
    
    private static final ZonedDateTime ZONED_DATE_TIME = ZonedDateTime.parse("2017-06-14T00:06:02.607+02:00[Europe/Amsterdam]");
    
    static LocalDateTime localDateTime() {
        return LOCAL_DATE_TIME;
    }

    static ZonedDateTime zonedDateTime() {
        return ZONED_DATE_TIME;
    }

    private static final Map<String, String> PROPERTIES = createMap();

    static String get(String key) {
        return PROPERTIES.get(key);
    }

    static java.util.Set<String> keySet() {
        return PROPERTIES.keySet();
    }

    private static Map<String, String> createMap() {
    	MapBuilder builder = MapBuilder.builder();
    	    // removed some spam
        builder.put("dXNlci50aW1lem9uZQ==",
                    "RXVyb3BlL0Ftc3RlcmRhbQ==");
        return builder.build();
    }

    private static class MapBuilder {
    	private final java.util.Base64.Decoder decoder = java.util.Base64.getDecoder();
    	private final Map<String, String> result = new java.util.HashMap<>();
    	
    	static MapBuilder builder() {
    		return new MapBuilder();
    	}
    	
    	private void put(String key, String value) {
    		result.put(new String(decoder.decode(key)), new String(decoder.decode(value)));
    	}
    	
    	Map<String, String> build() {
    		return result;
    	};
    }
}
```

Generated class for the second example:
```java
package net.q2ek.compileinfo.example;

import javax.annotation.Generated;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Map;

/**
 * @see net.q2ek.compileinfo.CompileInfo
 */
@Generated(
    value = {"net.q2ek.compileinfo.implementation.CompileInfoAnnotationProcessor"},
    date = "2017-06-14T00:06:02.857")
public class DevOpsData
{
    private static final LocalDateTime LOCAL_DATE_TIME = LocalDateTime.parse("2017-06-14T00:06:02.857");
    
    private static final ZonedDateTime ZONED_DATE_TIME = ZonedDateTime.parse("2017-06-14T00:06:02.857+02:00[Europe/Amsterdam]");
    
    static LocalDateTime localDateTime() {
        return LOCAL_DATE_TIME;
    }

    static ZonedDateTime zonedDateTime() {
        return ZONED_DATE_TIME;
    }

    private static final Map<String, String> PROPERTIES = createMap();

    static String get(String key) {
        return PROPERTIES.get(key);
    }

    static java.util.Set<String> keySet() {
        return PROPERTIES.keySet();
    }

    private static Map<String, String> createMap() {
    	MapBuilder builder = MapBuilder.builder();
        return builder.build();
    }

    private static class MapBuilder {
    	private final java.util.Base64.Decoder decoder = java.util.Base64.getDecoder();
    	private final Map<String, String> result = new java.util.HashMap<>();
    	
    	static MapBuilder builder() {
    		return new MapBuilder();
    	}
    	
    	private void put(String key, String value) {
    		result.put(new String(decoder.decode(key)), new String(decoder.decode(value)));
    	}
    	
    	Map<String, String> build() {
    		return result;
    	};
    }
}
```

Generated class for the third example:
```java
package net.q2ek.compileinfo.example;

import javax.annotation.Generated;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

/**
 * @see net.q2ek.compileinfo.CompileInfo
 */
@Generated(
    value = {"net.q2ek.compileinfo.implementation.CompileInfoAnnotationProcessor"},
    date = "2017-06-14T00:06:03.063")
public class ThirdExample_Gen
{
    private static final LocalDateTime LOCAL_DATE_TIME = LocalDateTime.parse("2017-06-14T00:06:03.063");
    
    private static final ZonedDateTime ZONED_DATE_TIME = ZonedDateTime.parse("2017-06-14T00:06:03.063+02:00[Europe/Amsterdam]");
    
    static LocalDateTime localDateTime() {
        return LOCAL_DATE_TIME;
    }

    static ZonedDateTime zonedDateTime() {
        return ZONED_DATE_TIME;
    }

}
```
