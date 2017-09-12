package me.loveshare.member.hystrix;

import lombok.extern.slf4j.Slf4j;
import me.loveshare.entity.bo.common.JsonResult;
import me.loveshare.entity.bo.common.JsonResultMethod;
import me.loveshare.entity.vo.common.Query;
import me.loveshare.member.client.MemberClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * Created by Tony on 2017/3/23.
 * 用户消费者服务熔断器   ---超时时间、retry的设置、服务未启动、业务异常
 */
@Slf4j
@Component
public class MemberClientHystrix implements MemberClient {

    @Override
    public JsonResult list(@RequestBody Query bo) {
        log.error("加载用户列表：进入熔断发生错误");
        return JsonResultMethod.code_500();
    }

    @Override
    public JsonResult list1(@RequestParam("id") Integer id, @RequestParam("name") String name, @RequestParam("code") String code) {
        log.error("加载用户列表1：进入熔断发生错误");
        return JsonResultMethod.code_500();
    }

    @Override
    public JsonResult list2(@RequestParam Map<String, Object> map) {
        log.error("加载用户列表2：进入熔断发生错误");
        return JsonResultMethod.code_500();
    }
}
