package me.loveshare.member.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import me.loveshare.entity.bo.common.JsonResult;
import me.loveshare.entity.bo.common.JsonResultMethod;
import me.loveshare.entity.dto.member.UserInfoDTO;
import me.loveshare.entity.vo.common.Query;
import me.loveshare.member.client.ErrorClient;
import me.loveshare.member.client.MemberClient;
import me.loveshare.member.client.TestClient;
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
    @Autowired
    private ErrorClient errorClient;
    @Autowired
    private TestClient testClient;

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
        return errorClient.list3(map);
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
        return errorClient.list4(map);
    }

    /**
     * 错误信息测试-多参数(3个以上)
     */
    @GetMapping("list5")
    @ApiOperation(value = "异常测试： 多参数(3个以上)")
    public JsonResult list5() {
        return errorClient.list5(123, "可可爱", "KK870587882", (byte) 125, "男", "中国", "甘肃省");
    }

    /**
     * 5 / 0
     *
     * @return
     */
    @GetMapping("list6")
    @ApiOperation(value = "用户列表测试1", hidden = true)
    public JsonResult list6() {
        return errorClient.list6(5, 0);
    }

    //Java架构测试API --对业务无效

    /**
     * http://192.168.1.119:8301/userinfo/test-feign-errordecoder.json
     * <p>
     * ***** Feign Error: {"method":"TestClient#testFeignErrordecoder(String,String)","code":404,"reason":"Not Found"}
     */
    @GetMapping("test-feign-errordecoder")
    @ApiOperation(value = "Feign + OkHttpClient + ErrorDecoder测试")
    public JsonResult testFeignErrordecoderC() {
        try {
            return errorClient.testFeignErrordecoder("Feign", "ErrorDecoder");
        } catch (Exception e) {
            log.error("Feign + OkHttpClient + ErrorDecoder测试异常:" + e.getMessage(), e);
            return JsonResultMethod.code_500("Feign + OkHttpClient + ErrorDecoder测试");
        }
    }

    /**
     * http://192.168.1.119:8301/userinfo/test-version.json .<br/>
     * https://192.168.1.119:1301/userinfo/test-version.json
     * <p>
     * [Service]User-Access-Args:{"ip":"192.168.56.1","method":"GET","url":"/testversion/v2.0.0/time?version=v2.0.0","}
     */
    @GetMapping("test-version")
    @ApiOperation(value = "接口版本测试")
    public JsonResult testVersionC() {
        try {
            return testClient.testVersion("v2.0.0");
        } catch (Exception e) {
            log.error("接口版本测试异常:" + e.getMessage(), e);
            return JsonResultMethod.code_500("接口版本测试");
        }
    }
}
