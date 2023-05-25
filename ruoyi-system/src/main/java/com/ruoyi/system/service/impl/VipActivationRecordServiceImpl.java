package com.ruoyi.system.service.impl;

import com.ruoyi.common.annotation.DataScope;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.system.domain.UserInfo;
import com.ruoyi.system.domain.VipActivationRecord;
import com.ruoyi.system.mapper.UserInfoMapper;
import com.ruoyi.system.mapper.VipActivationRecordMapper;
import com.ruoyi.system.service.IVipActivationRecordService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * vip权限激活记录Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-04-19
 */
@Service
public class VipActivationRecordServiceImpl implements IVipActivationRecordService 
{
    @Resource
    private VipActivationRecordMapper vipActivationRecordMapper;

    @Resource
    private UserInfoMapper userInfoMapper;

    /**
     * 查询vip权限激活记录
     * 
     * @param id vip权限激活记录主键
     * @return vip权限激活记录
     */
    @Override
    public VipActivationRecord selectVipActivationRecordById(Long id)
    {
        return vipActivationRecordMapper.selectVipActivationRecordById(id);
    }

    /**
     * 查询vip权限激活记录列表
     * 
     * @param vipActivationRecord vip权限激活记录
     * @return vip权限激活记录
     */
    @Override
    @DataScope(userAlias = "su")
    public List<VipActivationRecord> selectVipActivationRecordList(VipActivationRecord vipActivationRecord)
    {
        return vipActivationRecordMapper.selectVipActivationRecordList(vipActivationRecord);
    }

    /**
     * 新增vip权限激活记录
     * 
     * @param vipActivationRecord vip权限激活记录
     * @return 结果
     */
    @Override
    public int insertVipActivationRecord(VipActivationRecord vipActivationRecord)
    {
        vipActivationRecord.setCreateTime(new Date());
        return vipActivationRecordMapper.insertVipActivationRecord(vipActivationRecord);
    }

    /**
     * 修改vip权限激活记录
     * 
     * @param vipActivationRecord vip权限激活记录
     * @return 结果
     */
    @Override
    public int updateVipActivationRecord(VipActivationRecord vipActivationRecord)
    {
        vipActivationRecord.setUpdateTime(new Date());
        return vipActivationRecordMapper.updateVipActivationRecord(vipActivationRecord);
    }

    /**
     * 批量删除vip权限激活记录
     * 
     * @param ids 需要删除的vip权限激活记录主键
     * @return 结果
     */
    @Override
    public int deleteVipActivationRecordByIds(Long[] ids)
    {
        return vipActivationRecordMapper.deleteVipActivationRecordByIds(ids);
    }

    /**
     * 删除vip权限激活记录信息
     * 
     * @param id vip权限激活记录主键
     * @return 结果
     */
    @Override
    public int deleteVipActivationRecordById(Long id)
    {
        return vipActivationRecordMapper.deleteVipActivationRecordById(id);
    }

    //vip权限开通审核通过
    @Override
    @Transactional
    public AjaxResult agree(Long id) {
        //vip权限开通信息
        VipActivationRecord vipActivationRecord = vipActivationRecordMapper.selectVipActivationRecordById(id);
        if (vipActivationRecord.getStatus() != 0){
            return AjaxResult.error("此用户申请开通vip权限已经审核过");
        }

        //用户id
        Long userId = vipActivationRecord.getUserId();
        UserInfo userInfo = userInfoMapper.selectUserInfoById(userId);
        if (userInfo.getCreditCardStatus() != 5){
            return AjaxResult.error("此用户信用卡还未激活");
        }

        //变更申请记录的状态为已通过
        vipActivationRecord.setStatus(1);
        vipActivationRecord.setUpdateTime(new Date());
        int updateVipActivationRecord = vipActivationRecordMapper.updateVipActivationRecord(vipActivationRecord);
        if (updateVipActivationRecord <= 0){
            throw new RuntimeException();
        }

        UserInfo userInfoVo = new UserInfo();
        userInfoVo.setId(userInfo.getId());
        //信用卡片状态变为可用
        userInfoVo.setCreditCardEnableStatus(1);
        //叠加信用总额度
        userInfoVo.setCreditCardTotalAmount(userInfo.getCreditCardTotalAmount().add(vipActivationRecord.getPayAmount()));
        int updateUserInfo = userInfoMapper.updateUserInfo(userInfoVo);
        if (updateUserInfo <= 0){
            throw new RuntimeException();
        }
        return AjaxResult.success();
    }

    //vip权限开通审核驳回
    @Override
    @Transactional
    public AjaxResult reject(Long id, String remark) {
        //vip权限开通信息
        VipActivationRecord vipActivationRecord = vipActivationRecordMapper.selectVipActivationRecordById(id);
        if (vipActivationRecord.getStatus() != 0){
            return AjaxResult.error("此用户申请开通vip权限已经审核过");
        }

        //变更申请记录的状态为已驳回
        vipActivationRecord.setStatus(2);
        //驳回原因
        vipActivationRecord.setRemark(remark);
        vipActivationRecord.setUpdateTime(new Date());
        int updateVipActivationRecord = vipActivationRecordMapper.updateVipActivationRecord(vipActivationRecord);
        if (updateVipActivationRecord <= 0){
            throw new RuntimeException();
        }
        UserInfo userInfoVo = new UserInfo();
        userInfoVo.setId(vipActivationRecord.getUserId());
        userInfoVo.setCreditCardEnableStatus(3);//已激活，未开通vip权限，额度不可用
        int updateUserInfo = userInfoMapper.updateUserInfo(userInfoVo);
        if (updateUserInfo <= 0){
            throw new RuntimeException();
        }
        return AjaxResult.success();
    }
}
