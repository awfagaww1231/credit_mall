package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.LoanApplyInfo;

import java.util.List;

/**
 * 贷款申请资料Mapper接口
 * 
 * @author ruoyi
 * @date 2023-04-03
 */
public interface LoanApplyInfoMapper 
{
    /**
     * 查询贷款申请资料
     * 
     * @param id 贷款申请资料主键
     * @return 贷款申请资料
     */
    public LoanApplyInfo selectLoanApplyInfoById(Long id);


    /**
     * 查询贷款申请资料
     *
     * @param userId 贷款申请资料主键
     * @return 贷款申请资料
     */
    public LoanApplyInfo selectLoanApplyInfoByUserId(Long userId);

    /**
     * 查询贷款申请资料列表
     * 
     * @param loanApplyInfo 贷款申请资料
     * @return 贷款申请资料集合
     */
    public List<LoanApplyInfo> selectLoanApplyInfoList(LoanApplyInfo loanApplyInfo);

    /**
     * 新增贷款申请资料
     * 
     * @param loanApplyInfo 贷款申请资料
     * @return 结果
     */
    public int insertLoanApplyInfo(LoanApplyInfo loanApplyInfo);

    /**
     * 修改贷款申请资料
     * 
     * @param loanApplyInfo 贷款申请资料
     * @return 结果
     */
    public int updateLoanApplyInfo(LoanApplyInfo loanApplyInfo);

    /**
     * 删除贷款申请资料
     * 
     * @param id 贷款申请资料主键
     * @return 结果
     */
    public int deleteLoanApplyInfoById(Long id);

    /**
     * 批量删除贷款申请资料
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteLoanApplyInfoByIds(Long[] ids);
}
