package cyan.simple.protobuf.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.ArrayType;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.MapType;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.*;

/**
 * <p>Json构建工具类</p>
 *
 * @author liuqingpo(snow22314@outlook.com)
 * @version V.1.0.1
 * @company 苏州中科蓝迪公司所有(c) 2016-2021
 * @date created on 上午 8:57 2019-12-4
 */
@Slf4j
public class JsonUtils {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    /**
     * 序列化为Json字符串
     * @param target 目标数据
     * @param <T> 目标类型
     * @return String json字符串
     */
    public static <T> String parserJson(T target) {
        if (GeneralUtils.isEmpty(target)) {
            return null;
        }
        try {
            return MAPPER.writeValueAsString(target);
        } catch (JsonProcessingException exception) {
            exception.printStackTrace();
            log.error("json序列化失败, "+ exception.getMessage());
            return null;
        }
    }

    public static <T> byte[] parserBytes(T target) {
        if (GeneralUtils.isEmpty(target)) {
            return null;
        }
        try {
            return MAPPER.writeValueAsBytes(target);
        } catch (JsonProcessingException exception) {
            exception.printStackTrace();
            log.error("json序列化失败, "+ exception.getMessage());
            return null;
        }
    }

    /**
     * json字符串解析为Bean
     * @param json json字符串数据
     * @param clazz bean类
     * @param <T> bean类型
     * @return T Bean
     */
    public static <T> T parserBean(String json, Class<T> clazz) {
        if (GeneralUtils.isEmpty(json)) {
            return null;
        }
        try {
            return MAPPER.readValue(json, clazz);
        } catch (Exception exception) {
            exception.printStackTrace();
            log.error("json反序列化失败, "+ exception.getMessage());
            return null;
        }
    }

    /**
     * array数据Json数据解析
     * @param json json数据
     * @param clazz array class
     * @param <T> bean类型
     * @return T bean
     */
    public static <T> T[] parserArray(String json, Class<T> clazz) {
        if (GeneralUtils.isEmpty(json)) {
            return null;
        }
        ArrayType arrayType = MAPPER.getTypeFactory().constructArrayType(clazz);
        try {
            return MAPPER.readValue(json, arrayType);
        } catch (Exception exception) {
            exception.printStackTrace();
            log.error("json反序列化失败, "+ exception.getMessage());
            return null;
        }
    }

    /**
     * json字符串解析为BeanList
     * @param json json字符串数据
     * @param listType 序列化指定的CollectionType类型
     * @param <T> Bean类型
     * @return List<T> BeanList
     */
    public static <T> List<T> parserList(String json, CollectionType listType) {
        if (GeneralUtils.isEmpty(json)) {
            return null;
        }
        try {
            return MAPPER.readValue(json, listType);
        } catch (Exception exception) {
            exception.printStackTrace();
            log.error("json反序列化失败, "+ exception.getMessage());
            return Collections.emptyList();
        }
    }

    /**
     * json字符串解析为BeanList
     * @param json json字符串数据
     * @param listType 序列化指定的CollectionType类型
     * @param <T> Bean类型
     * @return List<T> BeanList
     */
    public static <T> List<T> parserList(byte[] json, CollectionType listType) {
        if (GeneralUtils.isEmpty(json)) {
            return null;
        }
        try {
            return MAPPER.readValue(json, listType);
        } catch (Exception exception) {
            exception.printStackTrace();
            log.error("json反序列化失败, "+ exception.getMessage());
            return Collections.emptyList();
        }
    }

    /**
     * json字符串解析为BeanList
     * @param json json字符串数据
     * @param parserClazz List类
     * @param clazz Bean类
     * @param <Z> List类型
     * @param <T> Bean类型
     * @return List<T> BeanList
     */
    public static <Z extends List,T> List<T> parserList(String json, Class<Z> parserClazz, Class<T> clazz) {
        CollectionType listType = MAPPER.getTypeFactory().constructCollectionType(parserClazz, clazz);
        return parserList(json,listType);
    }

    /**
     * json字符串解析为BeanList
     * @param json json字符串数据
     * @param parserClazz List类
     * @param clazz Bean类
     * @param <Z> List类型
     * @param <T> Bean类型
     * @return List<T> BeanList
     */
    public static <Z extends List,T> List<T> parserList(byte[] json, Class<Z> parserClazz, Class<T> clazz) {
        CollectionType listType = MAPPER.getTypeFactory().constructCollectionType(parserClazz, clazz);
        return parserList(json,listType);
    }

    /**
     * json字符串解析为BeanList
     * @param json json字符串数据
     * @param clazz Bean类
     * @param <T> Bean类型
     * @return List<T> BeanList
     */
    public static <T> List<T> parserList(String json, Class<T> clazz) {
        return parserList(json,List.class,clazz);
    }


    /**
     * json字符串解析为BeanList
     * @param json json字符串数据
     * @param clazz Bean类
     * @param <T> Bean类型
     * @return List<T> BeanList
     */
    public static <T> List<T> parserList(byte[] json, Class<T> clazz) {
        return parserList(json,List.class,clazz);
    }

    /**
     * json字符串解析为BeanSet
     * @param json json字符串数据
     * @param setType 序列化指定的CollectionType类型
     * @param <T> Bean类型
     * @return Set<T> BeanSet
     */
    public static <T> Set<T> parserSet(String json, CollectionType setType) {
        if (GeneralUtils.isEmpty(json)) {
            return null;
        }
        try {
            return MAPPER.readValue(json, setType);
        } catch (Exception exception) {
            exception.printStackTrace();
            log.error("json反序列化失败, "+ exception.getMessage());
            return Collections.emptySet();
        }
    }

    /**
     * json字符串解析为BeanSet
     * @param json json字符串数据
     * @param parserClazz Set类
     * @param clazz Bean类
     * @param <Z> Set类型
     * @param <T> Bean类型
     * @return Set<T> BeanSet
     */
    public static <Z extends Set,T> Set<T> parserSet(String json, Class<Z> parserClazz, Class<T> clazz) {
        CollectionType setType = MAPPER.getTypeFactory().constructCollectionType(parserClazz, clazz);
        return parserSet(json,setType);
    }

    /**
     * json字符串解析为BeanSet
     * @param json json字符串数据
     * @param clazz Bean类
     * @param <T> Bean类型
     * @return Set<T> BeanSet
     */
    public static <T> Set<T> parserSet(String json, Class<T> clazz) {
        return parserSet(json,Set.class,clazz);
    }

    /**
     * json字符串解析为BeanMap
     * @param json json字符串数据
     * @param mapType 序列化指定的MapType类型
     * @param <T> key类型
     * @param <K> value类型
     * @return Map<T, K> BeanMap
     */
    public static <T,K> Map<T, K> parserMap(String json, MapType mapType) {
        if (GeneralUtils.isEmpty(json)) {
            return null;
        }
        try {
            return MAPPER.readValue(json, mapType);
        } catch (Exception exception) {
            exception.printStackTrace();
            log.error("json反序列化失败, "+ exception.getMessage());
            return Collections.emptyMap();
        }
    }

    /**
     * json字符串解析为BeanMap
     * @param json json字符串数据
     * @param parserClazz Map类
     * @param keyClazz key类
     * @param valueClazz value类
     * @param <Z> Map类型
     * @param <T> key类型
     * @param <K> value类型
     * @return
     */
    public static <Z extends Map,T,K> Map<T, K> parserMap(String json, Class<Z> parserClazz, Class<T> keyClazz, Class<K> valueClazz) {
        MapType mapType = MAPPER.getTypeFactory().constructMapType(parserClazz, keyClazz, valueClazz);
        return parserMap(json,mapType);
    }

    /**
     * json字符串解析为BeanMap
     * @param json json字符串数据
     * @param keyClazz key类
     * @param valueClazz value类
     * @param <T> key类型
     * @param <K> value类型
     * @return Map<T, K> BeanMap
     */
    public static <T,K> Map<T, K> parserMap(String json, Class<T> keyClazz, Class<K> valueClazz) {
        return parserMap(json, Map.class,keyClazz,valueClazz);
    }

    /**
     * json字符串解析为BeanMap
     * @param json json字符串数据
     * @param keyClazz key类
     * @param valueClazz value类
     * @param <T> key类型
     * @param <K> value类型
     * @return Hashtable<T, K> BeanMap
     */
    public static <K,T> Hashtable<K,T> parserTable(String json, Class<K> keyClazz , Class<T> valueClazz) {
        MapType mapType = MAPPER.getTypeFactory().constructMapType(Hashtable.class, keyClazz, valueClazz);
        if (GeneralUtils.isEmpty(json)) {
            return null;
        }
        try {
            return MAPPER.readValue(json, mapType);
        } catch (IOException exception) {
            exception.printStackTrace();
            log.error("json反序列化失败, "+ exception.getMessage());
            return new Hashtable<>();
        }
    }

    /**
     *
     * json字符串解析为BeanMapList
     * @param json json字符串数据
     * @param parserListClazz List类
     * @param parserMapClazz Map类
     * @param keyClazz key类
     * @param valueClazz value类
     * @param <H> List类型
     * @param <Y> Map类型
     * @param <T> key类型
     * @param <K> value类型
     * @return Map<T,List<K>> BeanMapList
     */
    public static <H extends List,Y extends Map,T,K> Map<T,List<K>> parserMapList(String json, Class<H> parserListClazz, Class<Y> parserMapClazz, Class<T> keyClazz, Class<K> valueClazz) {
        CollectionType collectionType = MAPPER.getTypeFactory().constructCollectionType(parserListClazz, valueClazz);
        MapType mapType = MAPPER.getTypeFactory().constructMapType(parserMapClazz, keyClazz, collectionType.getRawClass());
        return parserMap(json,mapType);
    }

    /**
     *
     * json字符串解析为BeanMapList
     * @param json json字符串数据
     * @param keyClazz key类
     * @param valueClazz value类
     * @param <T> key类型
     * @param <K> value类型
     * @return Map<T,List<K>> BeanMapList
     */
    public static <T,K> Map<T,List<K>> parserMapList(String json, Class<T> keyClazz, Class<K> valueClazz) {
        return parserMapList(json,List.class,Map.class,keyClazz,valueClazz);
    }

    /**
     *
     * json字符串解析为BeanMapList
     * @param json json字符串数据
     * @param wrapMapClazz 外层Map类
     * @param contentMapClazz 内层Map类
     * @param wrapKeyClazz 外层key类型
     * @param contentKeyClazz 内层mapKey
     * @param contentValueClazz 内层mapValue
     * @param <H> 外层Map类型
     * @param <Y> 内层Map类型
     * @param <Z> 外层mapKey类型
     * @param <T> 内层mapKey类型
     * @param <K> 内层mapValue类型
     * @return Map<Z, Map<T, K>> BeanMapMap
     */
    public static<H extends Map,Y extends Map,Z,T,K> Map<Z, Map<T, K>> parserMapMap(String json,Class<H> wrapMapClazz, Class<Y> contentMapClazz, Class<Z> wrapKeyClazz, Class<T> contentKeyClazz, Class<K> contentValueClazz) {
        MapType contentType = MAPPER.getTypeFactory().constructMapType(contentMapClazz, contentKeyClazz,contentValueClazz);
        MapType mapType = MAPPER.getTypeFactory().constructMapType(wrapMapClazz, wrapKeyClazz, contentType.getRawClass());
        return parserMap(json,mapType);
    }

    /**
     * json多层map数据解析
     * @param json json数据
     * @param wrapKeyClazz 外层mapKey
     * @param contentKeyClazz 内层mapKey
     * @param contentValueClazz 内层mapValue
     * @param <Z> 外层mapKey类型
     * @param <T> 内层mapKey类型
     * @param <K> 内层mapValue类型
     * @return Map<Z,Map<T, K>> BeanMapMap
     */
    public static <Z,T,K> Map<Z, Map<T, K>> parserMapMap(String json, Class<Z> wrapKeyClazz, Class<T> contentKeyClazz, Class<K> contentValueClazz) {
        return parserMapMap(json,Map.class,Map.class,wrapKeyClazz,contentKeyClazz,contentValueClazz);
    }

    /**
     * json字符串解析为GxReturn
     * @param json json字符串数据
     * @param clazz bean类
     * @param <T> bean类型
     * @return T Bean
     */
    public static <T,K> T parserBean(String json, Class<T> clazz, Class<K> innerClazz) throws JsonProcessingException {
        if (GeneralUtils.isEmpty(json)) {
            return null;
        }
        JavaType javaType = MAPPER.getTypeFactory().constructParametricType(clazz, innerClazz);
        return MAPPER.readValue(json, javaType);
    }

}
