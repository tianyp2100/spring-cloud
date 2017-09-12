package me.loveshare.service.member.api;

import lombok.extern.slf4j.Slf4j;
import me.loveshare.entity.bo.common.JsonResult;
import me.loveshare.entity.bo.common.JsonResultMethod;
import me.loveshare.util.common.DateUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Tony on 2017/9/12.<br/>
 * Spring-Cloud 接口版本设计：<br/>
 * http://ip:port/context-path/controller-mapping/version/method-mapping.json <br/>
 * http://192.168.1.119:8204/testversion/v2.0.0/time.json
 */
@Slf4j
@RestController
@RequestMapping("testversion")
public class TestVersionApi extends BasedApi {

    @GetMapping("${service-version}/time")
    public JsonResult testVersion(@RequestParam("version") String version) {
        return JsonResultMethod.code_200("Test version:[" + version + "] successfully.", DateUtils.current(5));
    }
}
