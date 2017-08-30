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
}
