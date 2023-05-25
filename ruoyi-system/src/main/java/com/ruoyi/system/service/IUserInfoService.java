package com.ruoyi.system.service;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.system.domain.UserInfo;
import com.ruoyi.system.domain.UserPromotionStatistics;

import java.util.List;

/**
 * C端用户信息Service接口
 * 
 * @author ruoyi
 * @date 2022-11-01
 */
public interface IUserInfoService 
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
     * 查询C端用户信息列表
     * 
     * @param userInfo C端用户信息
     * @return C端用户信息集合
     */
    public List<UserInfo> selectUserInfoList(UserInfo userInfo);

    /**
     * 新增C端用户信息
     * 
     * @param userInfo C端用户信息
     * @return 结果
     */
    public AjaxResult insertUserInfo(UserInfo userInfo);

    /**
     * 修改C端用户信息
     * 
     * @param userInfo C端用户信息
     * @return 结果
     */
    public int updateUserInfo(UserInfo userInfo);

    /**
     * 批量删除C端用户信息
     * 
     * @param ids 需要删除的C端用户信息主键集合
     * @return 结果
     */
    public int deleteUserInfoByIds(Long[] ids);

    /**
     * 删除C端用户信息信息
     * 
     * @param id C端用户信息主键
     * @return 结果
     */
    public int deleteUserInfoById(Long id);

    //启用禁用
    int updateStatus(Long id);

    /**
     * 重置登录密码
     */
    int resetPassword(Long id,String password);

    /**
     * 重置支付密码
     */
    int resetPayPassword(Long id,String payPassword);

    /**
     * 配置信用卡片额度与可用状态
     */
    AjaxResult setCreditCardInfo(UserInfo userInfo);

    /**
     * 查看用户所有信息（包括房产证。驾驶证。行驶证等。。）
     */
    AjaxResult getUserAllAssetInformation(Long userId);

    /**
     * 修改实名认证信息
     */
    AjaxResult updateAuthInfo(UserInfo userInfo);

    List<UserPromotionStatistics> queryUserPromotionStatistics(UserPromotionStatistics userPromotionStatistics);

}
