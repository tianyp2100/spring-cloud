package me.loveshare.member.client;

import me.loveshare.entity.bo.common.JsonResult;
import me.loveshare.member.hystrix.ErrorClientHystrix;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * Created by Tony on 2017/9/12. <br/>
 * Feign + OkHttpClient + ErrorDecoder测试
 */
@FeignClient(name = "service-member", fallback = ErrorClientHystrix.class)
public interface ErrorClient {

    /**
     * 测试方法
     * 注：此方法在服务提供者不存在 .<br/>
     * <p>
     * ***** Feign Error: {"method":"MemberClient#testFeignErrordecoder(String,String)","code":404,"reason":"Not Found"}
     */
    @RequestMapping(value = "testerror/test-feign-errordecoder", method = RequestMethod.GET)
    JsonResult testFeignErrordecoder(@RequestParam("arg1") String arg1, @RequestParam("arg2") String arg2);

    /**
     * 异常测试: 服务端未提供此方法
     *
     * @param map
     * @return --------------------------Error--------------------------
     * Code: 404
     * Message: Not Found
     * URL: http://192.168.56.1:1201/testerror/list3?code=KWY987654&name=%E5%8D%A1%E5%93%87%E4%BC%8A&id=123
     * --------------------------Error--------------------------
     */
    @RequestMapping(value = "testerror/list3", method = RequestMethod.GET)
    JsonResult list3(@RequestParam Map<String, Object> map);


    /**
     * 异常测试: 超时测试  --进入熔断
     *
     * @param map
     * @return
     */
    @RequestMapping(value = "testerror/list4", method = RequestMethod.GET)
    JsonResult list4(@RequestParam Map<String, Object> map);

    /**
     * 异常测试:  多参数(3个以上)  -- 由于使用okhttp此错误不在出现
     * 注注注: Feign: Default JDK URLConnection get参数最多3个，但OkHttp HttpClient则无限制
     *
     * @return
     */
    @RequestMapping(value = "testerror/list5", method = RequestMethod.GET)
    JsonResult list5(
            @RequestParam("id") Integer id,
            @RequestParam("name") String name,
            @RequestParam("code") String code,
            @RequestParam("age") Byte age,
            @RequestParam("sex") String sex,
            @RequestParam("country") String country,
            @RequestParam("province") String province
    );

    /**
     * 5 / 0
     * java.lang.ArithmeticException: / by zero
     *
     * @param a
     * @param b
     * @return
     */
    @RequestMapping(value = "testerror/list6", method = RequestMethod.GET)
    JsonResult list6(@RequestParam("id") Integer a, @RequestParam("id") Integer b);
}
