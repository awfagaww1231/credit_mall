package com.ruoyi.system.service;

import com.ruoyi.system.domain.UserEvaluation;

import java.util.List;

/**
 * 用户评价信息Service接口
 * 
 * @author ruoyi
 * @date 2022-11-07
 */
public interface IUserEvaluationService 
{
    /**
     * 查询用户评价信息
     * 
     * @param id 用户评价信息主键
     * @return 用户评价信息
     */
    public UserEvaluation selectUserEvaluationById(Long id);

    /**
     * 查询用户评价信息列表
     * 
     * @param userEvaluation 用户评价信息
     * @return 用户评价信息集合
     */
    public List<UserEvaluation> selectUserEvaluationList(UserEvaluation userEvaluation);

    /**
     * 新增用户评价信息
     * 
     * @param userEvaluation 用户评价信息
     * @return 结果
     */
    public int insertUserEvaluation(UserEvaluation userEvaluation);

    /**
     * 修改用户评价信息
     * 
     * @param userEvaluation 用户评价信息
     * @return 结果
     */
    public int updateUserEvaluation(UserEvaluation userEvaluation);

    /**
     * 批量删除用户评价信息
     * 
     * @param ids 需要删除的用户评价信息主键集合
     * @return 结果
     */
    public int deleteUserEvaluationByIds(Long[] ids);

    /**
     * 删除用户评价信息信息
     * 
     * @param id 用户评价信息主键
     * @return 结果
     */
    public int deleteUserEvaluationById(Long id);
}
