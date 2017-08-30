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
     */
    @RequestMapping(value = "member/list1", method = RequestMethod.GET)
    JsonResult list1(@RequestParam("id") Integer id, @RequestParam("name") String name, @RequestParam("code") String code);

    /**
     * 用户列表-参数测试2 --多于三个参数 .<br/>
     * 注：GET 请求参数请使用(此版本)写法：以下所写 .<br/>
     */
    @RequestMapping(value = "member/list2", method = RequestMethod.GET)
    JsonResult list2(@RequestParam Map<String, Object> map);
}
