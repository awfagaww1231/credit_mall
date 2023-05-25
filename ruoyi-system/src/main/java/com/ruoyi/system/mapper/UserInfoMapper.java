package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.UserInfo;
import com.ruoyi.system.domain.UserPromotionStatistics;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

/**
 * C端用户信息Mapper接口
 * 
 * @author ruoyi
 * @date 2022-11-01
 */
public interface UserInfoMapper 
{
    /**
     * 查询C端用户信息
     * 
     * @param id C端用户信息主键
     * @return C端用户信息
     */
    public UserInfo selectUserInfoById(Long id);

    /**
     * 查询用户信息
     *
     * @param mobile 用户手机号
     * @return 用户信息
     */
    public UserInfo selectUserInfoByMobile(String mobile);

    /**
     * 查询用户信息
     *
     * @param inviteCode 用户邀请码
     * @return 用户信息
     */
    UserInfo selectUserByInviteCode(String inviteCode);

    /**
     * 查询C端用户信息列表
     * 
     * @param userInfo C端用户信息
     * @return C端用户信息集合
     */
    public List<UserInfo> selectUserInfoList(UserInfo userInfo);

    /**
     * 查询机器人列表
     *
     * @return C端用户信息集合
     */
    public UserInfo selectRobotInfoRandom();

    /**
     * 新增C端用户信息
     * 
     * @param userInfo C端用户信息
     * @return 结果
     */
    public int insertUserInfo(UserInfo userInfo);

    /**
     * 修改C端用户信息
     * 
     * @param userInfo C端用户信息
     * @return 结果
     */
    public int updateUserInfo(UserInfo userInfo);

    /**
     * 删除C端用户信息
     * 
     * @param id C端用户信息主键
     * @return 结果
     */
    public int deleteUserInfoById(Long id);

    /**
     * 批量删除C端用户信息
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteUserInfoByIds(Long[] ids);

    //根据id查询启用禁用状态
    int queryUserStatus(Long id);

    //根据id变更用户的启用禁用状态
    int chuangeUserStatus(@Param("id") Long id, @Param("status") int status);

    /**
     * 重置登录密码
     */
    int resetPassword(@Param("id") Long id,
                      @Param("password") String password);

    /**
     * 重置支付密码
     */
    int resetPayPassword(@Param("id") Long id,
                         @Param("payPassword") String payPassword);

    //修改用户金额
    int updateUserAmount(@Param("id") Long id,
                         @Param("amount") BigDecimal amount,
                         @Param("amountBefore") BigDecimal amountBefore);


    //查询此卡号是否已经存在
    int selectCountByCreditCardNumber(String creditCardNumber);

    //查询用户推广统计
    public List<UserPromotionStatistics> queryUserPromotionStatistics(UserPromotionStatistics userPromotionStatistics);

}
