package me.loveshare.service.member.api;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import me.loveshare.entity.bo.common.JsonResult;
import me.loveshare.entity.vo.common.Query;
import me.loveshare.service.member.data.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Created by Tony on 2017/3/23.
 * 用户服务层接入API
 */
@Slf4j
@RestController
@RequestMapping("member")
public class MemberApi extends BasedApi {

    @Autowired
    private MemberService memberService;

    @PostMapping("list")
    public JsonResult liseC(@RequestBody Query query) {
        if (query == null) query = new Query();
        return memberService.list(query);
    }

    @GetMapping("list1")
    public JsonResult list1(@RequestParam("id") Integer id, @RequestParam("name") String name, @RequestParam("code") String code) {
        log.info("参数测试1:" + id + " / " + name + " / " + code);
        return memberService.list(new Query());
    }

    @GetMapping("list2")
    public JsonResult list2(@RequestParam Map<String, Object> map) {
        log.info("参数测试2:" + JSON.toJSONString(map));
        return memberService.list(new Query());
    }
}
