package me.loveshare.member.client;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import me.loveshare.entity.bo.common.JsonResult;
import me.loveshare.member.hystrix.MemberClientHystrixFallback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Tony on 2017/3/23.
 * Feign是一种声明式、模板化的HTTP客户端
 */
@Slf4j
@Component
public class MemberClient extends MemberClientHystrixFallback {

    @Autowired
    private RestTemplate restTemplate;

    /**
     * 用户列表
     */
    @HystrixCommand(fallbackMethod = "listFallback")
    public JsonResult list(Integer id, String name, String code) {

        String url = "http://SERVICE-MEMBER/member/list1?id=" + id + "&name=" + name + "&code=" + code;

        return restTemplate.getForEntity(url, JsonResult.class).getBody();
    }
}
