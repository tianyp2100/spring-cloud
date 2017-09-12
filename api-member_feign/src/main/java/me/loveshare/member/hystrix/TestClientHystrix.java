package me.loveshare.member.hystrix;

import lombok.extern.slf4j.Slf4j;
import me.loveshare.entity.bo.common.JsonResult;
import me.loveshare.entity.bo.common.JsonResultMethod;
import me.loveshare.member.client.TestClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Tony on 2017/9/12.
 */
@Slf4j
@Component
public class TestClientHystrix implements TestClient {

    @Override
    public JsonResult testVersion(@RequestParam("version") String version) {
        log.error("Test version，进入熔断器");
        return JsonResultMethod.code_500("Test version.");
    }
}
