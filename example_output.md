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
        builder.put("YXd0LnRvb2xraXQ=",
                    "c3VuLmF3dC5YMTEuWFRvb2xraXQ=");
        builder.put("Y2xhc3N3b3JsZHMuY29uZg==",
                    "L3Vzci9zaGFyZS9tYXZlbi9iaW4vbTIuY29uZg==");
        builder.put("ZmlsZS5lbmNvZGluZw==",
                    "VVRGLTg=");
        builder.put("ZmlsZS5lbmNvZGluZy5wa2c=",
                    "c3VuLmlv");
        builder.put("ZmlsZS5zZXBhcmF0b3I=",
                    "Lw==");
        builder.put("Z3VpY2UuZGlzYWJsZS5taXNwbGFjZWQuYW5ub3RhdGlvbi5jaGVjaw==",
                    "dHJ1ZQ==");
        builder.put("amF2YS5hd3QuZ3JhcGhpY3NlbnY=",
                    "c3VuLmF3dC5YMTFHcmFwaGljc0Vudmlyb25tZW50");
        builder.put("amF2YS5hd3QucHJpbnRlcmpvYg==",
                    "c3VuLnByaW50LlBTUHJpbnRlckpvYg==");
        builder.put("amF2YS5jbGFzcy5wYXRo",
                    "L3Vzci9zaGFyZS9tYXZlbi9ib290L3BsZXh1cy1jbGFzc3dvcmxkcy0yLnguamFy");
        builder.put("amF2YS5jbGFzcy52ZXJzaW9u",
                    "NTIuMA==");
        builder.put("amF2YS5lbmRvcnNlZC5kaXJz",
                    "L3Vzci9saWIvanZtL2phdmEtOC1vcGVuamRrLWFtZDY0L2pyZS9saWIvZW5kb3JzZWQ=");
        builder.put("amF2YS5leHQuZGlycw==",
                    "L3Vzci9saWIvanZtL2phdmEtOC1vcGVuamRrLWFtZDY0L2pyZS9saWIvZXh0Oi91c3IvamF2YS9wYWNrYWdlcy9saWIvZXh0");
        builder.put("amF2YS5ob21l",
                    "L3Vzci9saWIvanZtL2phdmEtOC1vcGVuamRrLWFtZDY0L2pyZQ==");
        builder.put("amF2YS5pby50bXBkaXI=",
                    "L3RtcA==");
        builder.put("amF2YS5saWJyYXJ5LnBhdGg=",
                    "L3Vzci9qYXZhL3BhY2thZ2VzL2xpYi9hbWQ2NDovdXNyL2xpYi94ODZfNjQtbGludXgtZ251L2puaTovbGliL3g4Nl82NC1saW51eC1nbnU6L3Vzci9saWIveDg2XzY0LWxpbnV4LWdudTovdXNyL2xpYi9qbmk6L2xpYjovdXNyL2xpYg==");
        builder.put("amF2YS5ydW50aW1lLm5hbWU=",
                    "T3BlbkpESyBSdW50aW1lIEVudmlyb25tZW50");
        builder.put("amF2YS5ydW50aW1lLnZlcnNpb24=",
                    "MS44LjBfMTMxLTh1MTMxLWIxMS0wdWJ1bnR1MS4xNi4wNC4yLWIxMQ==");
        builder.put("amF2YS5zcGVjaWZpY2F0aW9uLm5hbWU=",
                    "SmF2YSBQbGF0Zm9ybSBBUEkgU3BlY2lmaWNhdGlvbg==");
        builder.put("amF2YS5zcGVjaWZpY2F0aW9uLnZlbmRvcg==",
                    "T3JhY2xlIENvcnBvcmF0aW9u");
        builder.put("amF2YS5zcGVjaWZpY2F0aW9uLnZlcnNpb24=",
                    "MS44");
        builder.put("amF2YS52ZW5kb3I=",
                    "T3JhY2xlIENvcnBvcmF0aW9u");
        builder.put("amF2YS52ZW5kb3IudXJs",
                    "aHR0cDovL2phdmEub3JhY2xlLmNvbS8=");
        builder.put("amF2YS52ZW5kb3IudXJsLmJ1Zw==",
                    "aHR0cDovL2J1Z3JlcG9ydC5zdW4uY29tL2J1Z3JlcG9ydC8=");
        builder.put("amF2YS52ZXJzaW9u",
                    "MS44LjBfMTMx");
        builder.put("amF2YS52bS5pbmZv",
                    "bWl4ZWQgbW9kZQ==");
        builder.put("amF2YS52bS5uYW1l",
                    "T3BlbkpESyA2NC1CaXQgU2VydmVyIFZN");
        builder.put("amF2YS52bS5zcGVjaWZpY2F0aW9uLm5hbWU=",
                    "SmF2YSBWaXJ0dWFsIE1hY2hpbmUgU3BlY2lmaWNhdGlvbg==");
        builder.put("amF2YS52bS5zcGVjaWZpY2F0aW9uLnZlbmRvcg==",
                    "T3JhY2xlIENvcnBvcmF0aW9u");
        builder.put("amF2YS52bS5zcGVjaWZpY2F0aW9uLnZlcnNpb24=",
                    "MS44");
        builder.put("amF2YS52bS52ZW5kb3I=",
                    "T3JhY2xlIENvcnBvcmF0aW9u");
        builder.put("amF2YS52bS52ZXJzaW9u",
                    "MjUuMTMxLWIxMQ==");
        builder.put("bGluZS5zZXBhcmF0b3I=",
                    "Cg==");
        builder.put("bWF2ZW4uaG9tZQ==",
                    "L3Vzci9zaGFyZS9tYXZlbg==");
        builder.put("bWF2ZW4ubXVsdGlNb2R1bGVQcm9qZWN0RGlyZWN0b3J5",
                    "L2hvbWUvZHJhZ25zbGF5ci9naXRodWIvcTJlay9jb21waWxlaW5mbw==");
        builder.put("b3MuYXJjaA==",
                    "YW1kNjQ=");
        builder.put("b3MubmFtZQ==",
                    "TGludXg=");
        builder.put("b3MudmVyc2lvbg==",
                    "NC40LjAtNzktZ2VuZXJpYw==");
        builder.put("cGF0aC5zZXBhcmF0b3I=",
                    "Og==");
        builder.put("c2VjdXJlcmFuZG9tLnNvdXJjZQ==",
                    "ZmlsZTovZGV2Ly4vdXJhbmRvbQ==");
        builder.put("c3VuLmFyY2guZGF0YS5tb2RlbA==",
                    "NjQ=");
        builder.put("c3VuLmJvb3QuY2xhc3MucGF0aA==",
                    "L3Vzci9saWIvanZtL2phdmEtOC1vcGVuamRrLWFtZDY0L2pyZS9saWIvcmVzb3VyY2VzLmphcjovdXNyL2xpYi9qdm0vamF2YS04LW9wZW5qZGstYW1kNjQvanJlL2xpYi9ydC5qYXI6L3Vzci9saWIvanZtL2phdmEtOC1vcGVuamRrLWFtZDY0L2pyZS9saWIvc3VucnNhc2lnbi5qYXI6L3Vzci9saWIvanZtL2phdmEtOC1vcGVuamRrLWFtZDY0L2pyZS9saWIvanNzZS5qYXI6L3Vzci9saWIvanZtL2phdmEtOC1vcGVuamRrLWFtZDY0L2pyZS9saWIvamNlLmphcjovdXNyL2xpYi9qdm0vamF2YS04LW9wZW5qZGstYW1kNjQvanJlL2xpYi9jaGFyc2V0cy5qYXI6L3Vzci9saWIvanZtL2phdmEtOC1vcGVuamRrLWFtZDY0L2pyZS9saWIvamZyLmphcjovdXNyL2xpYi9qdm0vamF2YS04LW9wZW5qZGstYW1kNjQvanJlL2NsYXNzZXM=");
        builder.put("c3VuLmJvb3QubGlicmFyeS5wYXRo",
                    "L3Vzci9saWIvanZtL2phdmEtOC1vcGVuamRrLWFtZDY0L2pyZS9saWIvYW1kNjQ=");
        builder.put("c3VuLmNwdS5lbmRpYW4=",
                    "bGl0dGxl");
        builder.put("c3VuLmNwdS5pc2FsaXN0",
                    "");
        builder.put("c3VuLmRlc2t0b3A=",
                    "Z25vbWU=");
        builder.put("c3VuLmlvLnVuaWNvZGUuZW5jb2Rpbmc=",
                    "VW5pY29kZUxpdHRsZQ==");
        builder.put("c3VuLmphdmEuY29tbWFuZA==",
                    "b3JnLmNvZGVoYXVzLnBsZXh1cy5jbGFzc3dvcmxkcy5sYXVuY2hlci5MYXVuY2hlciBjbGVhbiBpbnN0YWxs");
        builder.put("c3VuLmphdmEubGF1bmNoZXI=",
                    "U1VOX1NUQU5EQVJE");
        builder.put("c3VuLmpudS5lbmNvZGluZw==",
                    "VVRGLTg=");
        builder.put("c3VuLm1hbmFnZW1lbnQuY29tcGlsZXI=",
                    "SG90U3BvdCA2NC1CaXQgVGllcmVkIENvbXBpbGVycw==");
        builder.put("c3VuLm9zLnBhdGNoLmxldmVs",
                    "dW5rbm93bg==");
        builder.put("dXNlci5jb3VudHJ5",
                    "VVM=");
        builder.put("dXNlci5kaXI=",
                    "L2hvbWUvZHJhZ25zbGF5ci9naXRodWIvcTJlay9jb21waWxlaW5mbw==");
        builder.put("dXNlci5ob21l",
                    "L2hvbWUvZHJhZ25zbGF5cg==");
        builder.put("dXNlci5sYW5ndWFnZQ==",
                    "ZW4=");
        builder.put("dXNlci5uYW1l",
                    "ZHJhZ25zbGF5cg==");
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
