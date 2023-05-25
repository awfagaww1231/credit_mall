package com.ruoyi.system.service.impl;

import com.ruoyi.system.domain.LoanApplyInfo;
import com.ruoyi.system.mapper.LoanApplyInfoMapper;
import com.ruoyi.system.service.ILoanApplyInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 贷款申请资料Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-04-03
 */
@Service
public class LoanApplyInfoServiceImpl implements ILoanApplyInfoService 
{
    @Autowired
    private LoanApplyInfoMapper loanApplyInfoMapper;

    /**
     * 查询贷款申请资料
     * 
     * @param id 贷款申请资料主键
     * @return 贷款申请资料
     */
    @Override
    public LoanApplyInfo selectLoanApplyInfoById(Long id)
    {
        return loanApplyInfoMapper.selectLoanApplyInfoById(id);
    }

    @Override
    public LoanApplyInfo selectLoanApplyInfoByUserId(Long userId) {
        return loanApplyInfoMapper.selectLoanApplyInfoByUserId(userId);
    }

    /**
     * 查询贷款申请资料列表
     * 
     * @param loanApplyInfo 贷款申请资料
     * @return 贷款申请资料
     */
    @Override
    public List<LoanApplyInfo> selectLoanApplyInfoList(LoanApplyInfo loanApplyInfo)
    {
        return loanApplyInfoMapper.selectLoanApplyInfoList(loanApplyInfo);
    }

    /**
     * 新增贷款申请资料
     * 
     * @param loanApplyInfo 贷款申请资料
     * @return 结果
     */
    @Override
    public int insertLoanApplyInfo(LoanApplyInfo loanApplyInfo)
    {
        return loanApplyInfoMapper.insertLoanApplyInfo(loanApplyInfo);
    }

    /**
     * 修改贷款申请资料
     * 
     * @param loanApplyInfo 贷款申请资料
     * @return 结果
     */
    @Override
    public int updateLoanApplyInfo(LoanApplyInfo loanApplyInfo)
    {
        return loanApplyInfoMapper.updateLoanApplyInfo(loanApplyInfo);
    }

    /**
     * 批量删除贷款申请资料
     * 
     * @param ids 需要删除的贷款申请资料主键
     * @return 结果
     */
    @Override
    public int deleteLoanApplyInfoByIds(Long[] ids)
    {
        return loanApplyInfoMapper.deleteLoanApplyInfoByIds(ids);
    }

    /**
     * 删除贷款申请资料信息
     * 
     * @param id 贷款申请资料主键
     * @return 结果
     */
    @Override
    public int deleteLoanApplyInfoById(Long id)
    {
        return loanApplyInfoMapper.deleteLoanApplyInfoById(id);
    }
}
