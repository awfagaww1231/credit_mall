package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.UserBillDetails;

import java.util.List;

/**
 * C端用户账单明细Mapper接口
 * 
 * @author ruoyi
 * @date 2022-11-10
 */
public interface UserBillDetailsMapper 
{
    /**
     * 查询C端用户账单明细
     * 
     * @param id C端用户账单明细主键
     * @return C端用户账单明细
     */
    public UserBillDetails selectUserBillDetailsById(Long id);

    /**
     * 查询C端用户账单明细列表
     * 
     * @param userBillDetails C端用户账单明细
     * @return C端用户账单明细集合
     */
    public List<UserBillDetails> selectUserBillDetailsList(UserBillDetails userBillDetails);

    /**
     * 新增C端用户账单明细
     * 
     * @param userBillDetails C端用户账单明细
     * @return 结果
     */
    public int insertUserBillDetails(UserBillDetails userBillDetails);

    /**
     * 修改C端用户账单明细
     * 
     * @param userBillDetails C端用户账单明细
     * @return 结果
     */
    public int updateUserBillDetails(UserBillDetails userBillDetails);

    /**
     * 删除C端用户账单明细
     * 
     * @param id C端用户账单明细主键
     * @return 结果
     */
    public int deleteUserBillDetailsById(Long id);

    /**
     * 批量删除C端用户账单明细
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteUserBillDetailsByIds(Long[] ids);
}
