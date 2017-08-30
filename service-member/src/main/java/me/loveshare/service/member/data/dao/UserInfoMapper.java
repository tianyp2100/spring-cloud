package me.loveshare.service.member.data.dao;

import me.loveshare.entity.dto.member.UserInfoDTO;
import me.loveshare.entity.vo.common.Query;
import me.loveshare.service.member.data.entity.dbo.UserInfo;

import java.util.List;

public interface UserInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserInfo record);

    int insertSelective(UserInfo record);

    UserInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserInfo record);

    int updateByPrimaryKey(UserInfo record);

    /**
     * 批量插入数据
     */
    int batchInsert(List<UserInfo> records);

    /**
     * 数据总条数
     */
    int selectUserInfoCount();

    /**
     * limit分页查询
     */
    List<UserInfoDTO> selectUserInfoPageLimit(Query query);

    /**
     * 通过id分页查询
     */
    List<UserInfoDTO> selectUserInfoPageJoinId(Query query);
}