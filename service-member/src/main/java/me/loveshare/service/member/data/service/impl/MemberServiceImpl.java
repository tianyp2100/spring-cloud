package me.loveshare.service.member.data.service.impl;

import lombok.extern.slf4j.Slf4j;
import me.loveshare.entity.bo.common.JsonResult;
import me.loveshare.entity.bo.common.JsonResultMethod;
import me.loveshare.entity.bo.common.Page;
import me.loveshare.entity.dto.member.UserInfoDTO;
import me.loveshare.entity.vo.common.Query;
import me.loveshare.service.member.data.dao.UserInfoMapper;
import me.loveshare.service.member.data.service.MemberService;
import me.loveshare.util.common.DBUtils;
import me.loveshare.util.common.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by tony on 2017/8/26.
 */
@Slf4j
@Service("memberService")
@Transactional
public class MemberServiceImpl implements MemberService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public JsonResult list(Query query) {
        log.info("用户列表加载参数:" + JsonUtils.object2string(query));
        //数据库分页参数处理
        query.initPageParams();

        //数据库查询数据
        Integer total = userInfoMapper.selectUserInfoCount();
        List<UserInfoDTO> list = null;
        if (!DBUtils.numberBlank(total)) {
            list = userInfoMapper.selectUserInfoPageJoinId(query);
//            list = userInfoMapper.selectUserInfoPageLimit(query);
        } else {
            return JsonResultMethod.code_804();
        }

        //封装页面数据
        Page<UserInfoDTO> page = Page.page(total, list, query.getPageIndex(), query.getPageSize());

        return JsonResultMethod.code_200("加载用户列表数据成功", page);
    }
}
