package com.ruoyi.system.service.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.domain.UserEvaluation;
import com.ruoyi.system.mapper.UserEvaluationMapper;
import com.ruoyi.system.service.IShopOrderService;
import com.ruoyi.system.service.IUserEvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户评价信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-11-07
 */
@Service
public class UserEvaluationServiceImpl implements IUserEvaluationService 
{
    @Autowired
    private UserEvaluationMapper userEvaluationMapper;

    @Autowired
    private IShopOrderService shopOrderService;

    /**
     * 查询用户评价信息
     * 
     * @param id 用户评价信息主键
     * @return 用户评价信息
     */
    @Override
    public UserEvaluation selectUserEvaluationById(Long id)
    {
        return userEvaluationMapper.selectUserEvaluationById(id);
    }

    /**
     * 查询用户评价信息列表
     * 
     * @param userEvaluation 用户评价信息
     * @return 用户评价信息
     */
    @Override
    public List<UserEvaluation> selectUserEvaluationList(UserEvaluation userEvaluation)
    {
        return userEvaluationMapper.selectUserEvaluationList(userEvaluation);
    }

    /**
     * 新增用户评价信息
     * 
     * @param userEvaluation 用户评价信息
     * @return 结果
     */
    @Override
    public int insertUserEvaluation(UserEvaluation userEvaluation)
    {
        userEvaluation.setCreateTime(DateUtils.getNowDate());
        return userEvaluationMapper.insertUserEvaluation(userEvaluation);
    }

    /**
     * 修改用户评价信息
     * 
     * @param userEvaluation 用户评价信息
     * @return 结果
     */
    @Override
    public int updateUserEvaluation(UserEvaluation userEvaluation)
    {
        return userEvaluationMapper.updateUserEvaluation(userEvaluation);
    }

    /**
     * 批量删除用户评价信息
     * 
     * @param ids 需要删除的用户评价信息主键
     * @return 结果
     */
    @Override
    public int deleteUserEvaluationByIds(Long[] ids)
    {
        return userEvaluationMapper.deleteUserEvaluationByIds(ids);
    }

    /**
     * 删除用户评价信息信息
     * 
     * @param id 用户评价信息主键
     * @return 结果
     */
    @Override
    public int deleteUserEvaluationById(Long id)
    {
        return userEvaluationMapper.deleteUserEvaluationById(id);
    }
}
