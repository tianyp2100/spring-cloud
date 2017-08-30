package me.loveshare.service.member.data.service;

import me.loveshare.entity.bo.common.JsonResult;
import me.loveshare.entity.vo.common.Query;

/**
 * Created by Tony on 2017/8/25.
 */
public interface MemberService {

    /**
     * 获取列表
     */

    JsonResult list(Query query);
}
