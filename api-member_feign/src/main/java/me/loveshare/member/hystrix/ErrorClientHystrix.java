package me.loveshare.member.hystrix;

import lombok.extern.slf4j.Slf4j;
import me.loveshare.entity.bo.common.JsonResult;
import me.loveshare.entity.bo.common.JsonResultMethod;
import me.loveshare.member.client.ErrorClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * Created by Tony on 2017/9/12.
 */
@Slf4j
@Component
public class ErrorClientHystrix implements ErrorClient {
    @Override
    public JsonResult testFeignErrordecoder(@RequestParam("arg1") String arg1, @RequestParam("arg2") String arg2) {
        log.error("测试：Feign + OkHttpClient + ErrorDecoder，进入熔断器");
        return JsonResultMethod.code_500("The test's Feign + OkHttpClient + ErrorDecoder.");
    }

    @Override
    public JsonResult list3(@RequestParam Map<String, Object> map) {
        log.error("加载用户列表3：进入熔断发生错误");
        return JsonResultMethod.code_500();
    }

    @Override
    public JsonResult list4(@RequestParam Map<String, Object> map) {
        log.error("加载用户列表4：进入熔断发生错误");
        return JsonResultMethod.code_500();
    }

    @Override
    public JsonResult list5(@RequestParam("id") Integer id,
                            @RequestParam("name") String name,
                            @RequestParam("code") String code,
                            @RequestParam("age") Byte age,
                            @RequestParam("sex") String sex,
                            @RequestParam("country") String country,
                            @RequestParam("province") String province) {
        log.error("加载用户列表5：进入熔断发生错误");
        return JsonResultMethod.code_500();
    }

    @Override
    public JsonResult list6(@RequestParam("id") Integer a, @RequestParam("id") Integer b) {
        log.error("加载用户列表6：进入熔断发生错误");
        return JsonResultMethod.code_500();
    }
}
