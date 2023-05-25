package com.ruoyi.customer.mapper;

import com.ruoyi.customer.domain.ApiUserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

@Mapper
public interface ApiAuthMapper {

    //根据手机号码查找用户
    ApiUserInfo selectUserByMobile(String mobile);

    //根据邮箱查找用户
    ApiUserInfo selectUserByEmail(String mobile);

    //根据邀请码查找用户
    ApiUserInfo selectUserByInviteCode(String inviteCode);

    //注册用户
    int insertUser(ApiUserInfo apiUserInfo);

    //根据id查找用户
    ApiUserInfo selectUserById(Long userId);

    /**
     * 修改C端用户信息
     *
     * @param apiUserInfo C端用户信息
     * @return 结果
     */
    public int updateUserInfo(ApiUserInfo apiUserInfo);

    //修改用户金额
    int updateUserAmount(@Param("id") Long id,
                         @Param("amount") BigDecimal amount,
                         @Param("amountBefore") BigDecimal amountBefore);

    //查找下级团队
    List<Long> queryMyTeam(@Param ("list") List<Long> list);

}
