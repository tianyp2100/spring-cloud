package me.loveshare.service.member.api;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import me.loveshare.entity.bo.common.JsonResult;
import me.loveshare.entity.bo.common.JsonResultEnum;
import me.loveshare.entity.vo.common.Query;
import me.loveshare.service.member.data.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by Tony on 2017/9/12.
 * Feign + OkHttpClient + ErrorDecoder测试
 */
@Slf4j
@RestController
@RequestMapping("testerror")
public class TestErrorApi extends BasedApi {
    @Autowired
    private MemberService memberService;

    @GetMapping("list4")
    public JsonResult list4(@RequestParam Map<String, Object> map) {
        log.info("异常测试4 -超时测试:" + JSON.toJSONString(map));
        try {
            Thread.sleep(5 * 60 * 1000);
        } catch (InterruptedException e) {
            log.error("线程中断异常" + e.getMessage(), e);
        }
        return memberService.list(new Query());
    }

    //注注注: Feign: Default JDK URLConnection get参数最多3个，但OkHttp HttpClient则无限制
    @GetMapping("list5")
    public JsonResult list5(
            @RequestParam("id") Integer id,
            @RequestParam("name") String name,
            @RequestParam("code") String code,
            @RequestParam("age") Byte age,
            @RequestParam("sex") String sex,
            @RequestParam("country") String country,
            @RequestParam("province") String province) {
        log.info("异常测试5 --多参数(3个以上):" + id + " / " + name + " / " + code + " / " + age + " / " + sex + " / " + country + " / " + province);
        //异常测试5 --多参数(3个以上):123 / 可可爱 / KK870587882 / 125 / 男 / 中国 / 甘肃省
        return memberService.list(new Query());
    }

    @GetMapping("list6")
    public JsonResult list6(@RequestParam("id") Integer a, @RequestParam("id") Integer b) {
        return new JsonResult(JsonResultEnum.SUCCESS.getCode(), "处理成功", (a / b));
    }
}
