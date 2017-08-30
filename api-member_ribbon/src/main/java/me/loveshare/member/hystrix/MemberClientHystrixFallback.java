package me.loveshare.member.hystrix;

import lombok.extern.slf4j.Slf4j;
import me.loveshare.entity.bo.common.JsonResult;
import me.loveshare.entity.bo.common.JsonResultMethod;
import org.springframework.stereotype.Component;

/**
 * Created by Tony on 2017/3/23.
 * 用户消费者服务熔断器
 */
@Slf4j
@Component
public class MemberClientHystrixFallback {

    public JsonResult listFallback(Integer id, String name, String code) {
        log.error("加载用户列表：进入熔断发生错误");
        return JsonResultMethod.code_500();
    }
}
