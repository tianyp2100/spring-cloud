package me.loveshare.member.configuration;

import com.netflix.hystrix.exception.HystrixBadRequestException;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Tony on 2017/9/8.<br/>
 * ErrorDecoder处理<br/>
 * Feign client 异常：<br/>
 * 触发熔断的异常: 服务未启动、超时时间、retry的设置、业务异常(包含java异常)。<br/>
 * 其他异常: 服务端未提供方法、IllegalArgumentException、HystrixBadRequestException等，这种异常不会纳入circuit breaker的统计里头，即不会触发熔断。<br/>
 */
@Configuration
public class FeignErrorDecoderConfiguration implements ErrorDecoder {
    @Override
    public Exception decode(String methodKey, Response response) {
        int status = response.status();
        if (status >= 400 && status <= 499) {
            StringBuilder su = new StringBuilder();
            su.append("\n--------------------------Error--------------------------");
            su.append("\n\tCode: ").append(response.status());
            su.append("\n\tMessage: ").append(response.reason());
            su.append("\n\tURL: ").append(response.request().url());
            su.append("\n--------------------------Error--------------------------");
            return new HystrixBadRequestException(su.toString());
        }
        return feign.FeignException.errorStatus(methodKey, response);
    }
}
