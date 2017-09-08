package me.loveshare.member.client;

import me.loveshare.entity.bo.common.JsonResult;
import me.loveshare.entity.vo.common.Query;
import me.loveshare.member.hystrix.MemberClientHystrix;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * Created by Tony on 2017/3/23.
 * Feign是一种声明式、模板化的HTTP客户端
 */
@FeignClient(name = "service-member", fallback = MemberClientHystrix.class)
public interface MemberClient {

    /**
     * 用户列表 .<br/>
     * 注：POST 请求参数请使用(此版本)写法：@RequestBody Query bo .<br/>
     */
    @RequestMapping(value = "member/list", method = RequestMethod.POST)
    JsonResult list(@RequestBody Query query);

    /**
     * 用户列表-参数测试1 .<br/>
     * 注：GET 请求参数请使用(此版本)写法：以下所写 .<br/>
     * 注：多于三个参数此写法失败
     * 注注注: Default JDK URLConnection get参数最多3个，但OkHttp HttpClient则无限制
     */
    @RequestMapping(value = "member/list1", method = RequestMethod.GET)
    JsonResult list1(@RequestParam("id") Integer id, @RequestParam("name") String name, @RequestParam("code") String code);

    /**
     * 用户列表-参数测试2 --多于三个参数 .<br/>
     * 注：GET 请求参数请使用(此版本)写法：以下所写 .<br/>
     */
    @RequestMapping(value = "member/list2", method = RequestMethod.GET)
    JsonResult list2(@RequestParam Map<String, Object> map);

    /**
     * 异常测试: 服务端未提供此方法
     *
     * @param map
     * @return --------------------------Error--------------------------
     * Code: 404
     * Message: Not Found
     * URL: http://192.168.56.1:1201/member/list3?code=KWY987654&name=%E5%8D%A1%E5%93%87%E4%BC%8A&id=123
     * --------------------------Error--------------------------
     */
    @RequestMapping(value = "member/list3", method = RequestMethod.GET)
    JsonResult list3(@RequestParam Map<String, Object> map);


    /**
     * 异常测试: 超时测试  --进入熔断
     *
     * @param map
     * @return
     */
    @RequestMapping(value = "member/list4", method = RequestMethod.GET)
    JsonResult list4(@RequestParam Map<String, Object> map);

    /**
     * 异常测试:  多参数(3个以上)  -- 由于使用okhttp此错误不在出现
     * 注注注: Feign: Default JDK URLConnection get参数最多3个，但OkHttp HttpClient则无限制
     *
     * @return
     */
    @RequestMapping(value = "member/list5", method = RequestMethod.GET)
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
     *  java.lang.ArithmeticException: / by zero
     * @param a
     * @param b
     * @return
     */
    @RequestMapping(value = "member/list6", method = RequestMethod.GET)
    JsonResult list6(@RequestParam("id") Integer a, @RequestParam("id") Integer b);
}
