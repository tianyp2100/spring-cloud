package me.loveshare.member.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import me.loveshare.entity.bo.common.JsonResult;
import me.loveshare.entity.dto.member.UserInfoDTO;
import me.loveshare.entity.vo.common.Query;
import me.loveshare.member.client.MemberClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Tony on 2017/8/1.
 * 用户数据接入API
 * 用户接口文档: http://192.168.1.119:1301/swagger-ui.html
 */
@Api(tags = {}, description = "用户数据接入API")
@Slf4j
@RestController
@RequestMapping("userinfo")
public class UserInfoApi extends BaseApi {

    @Autowired
    private MemberClient memberClient;

    /**
     * 用户列表
     * http://192.168.1.119:1301/userinfo/list.json
     */
    @ApiOperation(value = "用户列表", response = UserInfoDTO.class)
    @RequestMapping(value = "list", produces = "application/json; charset=UTF-8", method = {RequestMethod.GET, RequestMethod.POST})
    public JsonResult listC(Query query) {
        return memberClient.list(query);
    }

    /**
     * 用户列表-参数测试1
     * http://192.168.1.119:1301/userinfo/list2.json
     */
    @GetMapping("list1")
    @ApiOperation(value = "用户列表测试1", hidden = true)
    public JsonResult list1() {
        return memberClient.list1(123, "可可爱", "KK870587882");
    }

    /**
     * 用户列表-参数测试2
     * http://192.168.1.119:1301/userinfo/list2.json
     */
    @GetMapping("list2")
    @ApiOperation(value = "用户列表测试2", hidden = true)
    public JsonResult list2() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", 123);
        map.put("name", "卡哇伊");
        map.put("code", "KWY987654");
        return memberClient.list2(map);
    }

    /**
     * 错误信息测试-服务端未提供此方法
     */
    @GetMapping("list3")
    @ApiOperation(value = "用户列表测试3")
    public JsonResult list3() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", 123);
        map.put("name", "卡哇伊");
        map.put("code", "KWY987654");
        return memberClient.list3(map);
    }

    /**
     * 错误信息测试-超时测试
     */
    @GetMapping("list4")
    @ApiOperation(value = "异常测试： 超时测试")
    public JsonResult list4() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", 123);
        map.put("name", "卡哇伊");
        map.put("code", "KWY987654");
        return memberClient.list4(map);
    }

    /**
     * 错误信息测试-多参数(3个以上)
     */
    @GetMapping("list5")
    @ApiOperation(value = "异常测试： 多参数(3个以上)")
    public JsonResult list5() {
        return memberClient.list5(123, "可可爱", "KK870587882", (byte) 125, "男", "中国", "甘肃省");
    }

    /**
     * 5 / 0
     *
     * @return
     */
    @GetMapping("list6")
    @ApiOperation(value = "用户列表测试1", hidden = true)
    public JsonResult list6() {
        return memberClient.list6(5, 0);
    }
}
