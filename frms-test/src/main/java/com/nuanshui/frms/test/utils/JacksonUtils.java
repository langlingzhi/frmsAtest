package com.nuanshui.frms.test.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.ser.FilterProvider;

import java.io.IOException;
import java.util.List;

public class JacksonUtils {
    private JacksonUtils() {
        //default construct
    }

    /**
     * Json反序列化
     *
     * @param val
     * @param cls
     * @return
     * @throws JsonParseException
     * @throws IOException
     * @desc 1.实体上
     * @JsonInclude(Include.NON_NULL) 将该标记放在属性上，如果该属性为NULL则不参与序列化
     * 如果放在类上边,那对这个类的全部属性起作用
     * Include.Include.ALWAYS 默认
     * Include.NON_DEFAULT 属性为默认值不序列化
     * Include.NON_EMPTY 属性为 空（“”） 或者为 NULL 都不序列化
     * Include.NON_NULL 属性为NULL 不序列化
     * 2.代码上
     * ObjectMapper mapper = new ObjectMapper();
     * mapper.setSerializationInclusion(Include.
     * NON_NULL);
     * 通过该方法对mapper对象进行设置，所有序列化的对象都将按改规则进行系列化
     * Include.Include.ALWAYS 默认
     * Include.NON_DEFAULT 属性为默认值不序列化
     * Include.NON_EMPTY 属性为 空（“”） 或者为 NULL 都不序列化
     * Include.NON_NULL 属性为NULL 不序列化
     */
    public static <T> T parseJsonFromString(String val, Class<T> cls)
            throws JsonParseException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT,true);
        return mapper.readValue(val, cls);
    }

    /**
     * 反序列化 转成list
     * @param val
     * @param cls
     * @param <T>
     * @return
     * @throws JsonParseException
     * @throws IOException
     */
    public static <T> List<T> parseJson2List(String val, Class<T> cls)
            throws JsonParseException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return mapper.readValue(val, new TypeReference<List<T>>() {});
    }
    public static String serialObject(Object obj) throws JsonProcessingException {
        return serialObject(obj, null);
    }

    public static String serialObject(Object obj, FilterProvider filters) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        if (filters != null) {
            mapper.setFilterProvider(filters);
        }
        //把null替换为""字符串
        mapper.getSerializerProvider().setNullValueSerializer(
                new JsonSerializer<Object>() {
                    @Override
                    public void serialize(Object value, JsonGenerator gen, SerializerProvider serializers) throws IOException, JsonProcessingException {
                        gen.writeString("");
                    }
                });
        return mapper.writeValueAsString(obj);
    }

    public static JsonNode parseJsonFromString(String val) throws JsonProcessingException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readTree(val);
    }

}
