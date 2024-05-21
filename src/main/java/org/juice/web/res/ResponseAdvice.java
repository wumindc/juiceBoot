package org.juice.web.res;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;

import org.juice.annotions.JuiceController;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.Objects;

import io.micrometer.common.lang.NonNullApi;

@NonNullApi
@RestControllerAdvice(annotations = JuiceController.class)//指定要增强的包
public class ResponseAdvice implements ResponseBodyAdvice<Object> {

    /**
     * 判断是否支持对返回类型的处理
     *
     * @param returnType    方法参数的类型
     * @param converterType 转换器的类型
     * @return 是否支持处理
     */
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    /**
     * 在写入响应体之前对返回结果进行处理和封装
     *
     * @param body                  返回结果对象
     * @param returnType            方法参数的类型
     * @param selectedContentType   响应内容的类型
     * @param selectedConverterType 转换器的类型
     * @param request               HTTP 请求对象
     * @param response              HTTP 响应对象
     * @return 处理后的返回结果
     */
    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
                                  Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                  ServerHttpRequest request, ServerHttpResponse response) {
        // 防止重复包裹的问题出现
        if (body instanceof WebResponse) {
            return body;
        }

        //当返回结果为字符串类型需要单独处理
        Class<?> returnClass = returnType.getMethod().getReturnType();
        if (body instanceof String || Objects.equals(returnClass, String.class)) {
            // 如果返回结果是字符串类型，将其封装为成功的结果对象，并转换为 JSON 字符串
            JsonMapper jsonMapper = new JsonMapper();
            try {
                return jsonMapper.writeValueAsString(WebResponse.success(body));
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }

        // 将返回结果封装为成功的结果对象
        return WebResponse.success(body);
    }
}
