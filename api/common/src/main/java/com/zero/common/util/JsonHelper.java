package com.zero.common.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

/**
 * json字符与对像转换
 * 
 * @author yezhaoxing
 * @date 2017/5/11
 */
public final class JsonHelper {

    private static final Logger LOG = LoggerFactory.getLogger(JsonHelper.class);
    public static ObjectMapper objectMapper;

    public static <T> T readValue(String jsonStr, Class<T> valueType) {
        T rtn = null;
        if (!StringUtils.isEmpty(jsonStr)) {
            if (objectMapper == null) {
                objectMapper = new ObjectMapper();
            }
            try {
                rtn = objectMapper.readValue(jsonStr, valueType);
            } catch (Exception e) {
                LOG.error(e.getMessage(), e);
            }
        }
        return rtn;
    }

    public static <T> T readValue(String jsonStr, TypeReference<T> valueTypeRef) {
        T rtn = null;
        if (!StringUtils.isEmpty(jsonStr)) {
            if (objectMapper == null) {
                objectMapper = new ObjectMapper();
            }
            try {
                rtn = objectMapper.readValue(jsonStr, valueTypeRef);
            } catch (Exception e) {
                LOG.error(e.getMessage(), e);
            }
        }
        return rtn;
    }

    public static String toJSon(Object object) {
        String rtn = null;
        if (object != null) {
            if (objectMapper == null) {
                objectMapper = new ObjectMapper();
            }
            try {
                rtn = objectMapper.writeValueAsString(object);
            } catch (Exception e) {
                LOG.error(e.getMessage(), e);
            }
        }
        return rtn;
    }
}