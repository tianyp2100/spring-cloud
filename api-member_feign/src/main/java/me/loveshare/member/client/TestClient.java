package me.loveshare.member.client;

import me.loveshare.entity.bo.common.JsonResult;
import me.loveshare.member.hystrix.TestClientHystrix;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Tony on 2017/9/12.
 */
@FeignClient(name = "service-member", fallback = TestClientHystrix.class)
public interface TestClient {

    /**
     * 版本测试和多FeignClient同名测试
     *
     * @param version
     * @return
     */
    @RequestMapping(value = "testversion/${service-version}/time", method = RequestMethod.GET)
    JsonResult testVersion(@RequestParam("version") String version);


}
