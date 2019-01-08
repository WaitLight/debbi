package org.dl.debbi.controller.config;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.MethodParameter;
import org.springframework.util.StreamUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.imageio.event.IIOReadProgressListener;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.nio.charset.Charset;

public class DebbiArgumentResolvers implements HandlerMethodArgumentResolver {

    public static final String BODY = "body";

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return String.class.equals(parameter.getParameterType());
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        // TODO 因为这个get获取不到参数
        HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
        JsonNode body = (JsonNode) request.getAttribute(BODY);
        if (body == null) {
            String bodyStr = StreamUtils.copyToString(request.getInputStream(), Charset.forName("UTF-8"));
            if (!StringUtils.isEmpty(bodyStr)) {
                body = objectMapper.readValue(bodyStr, JsonNode.class);
                request.setAttribute(BODY, body);
            }
        }

        if (body == null) {
            return null;
        } else {
            return convert((body).get(parameter.getParameterName()), parameter.getParameterType());
        }
    }

    private static Object convert(JsonNode json, Type type) throws Exception {
        if (json == null) return null;
        try {
            if (type instanceof ParameterizedType) // for parameterized type
                return objectMapper.convertValue(json, new TypeReference<>() {
                    @Override
                    public Type getType() {
                        return type;
                    }
                });
            Class aClass = (Class) type;
            if (aClass == String.class) {
                String trim = StringUtils.trimWhitespace(json.asText());
                if (trim.equals("")) return null;
                return objectMapper.convertValue(trim, aClass); // for class
            }
            return objectMapper.convertValue(json, aClass);
        } catch (Exception e) {
            throw new Exception("illegal_request_format");
        }
    }


}
