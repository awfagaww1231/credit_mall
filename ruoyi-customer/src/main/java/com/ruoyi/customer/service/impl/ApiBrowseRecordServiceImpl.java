package com.ruoyi.customer.service.impl;

import java.util.List;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.customer.domain.ApiBrowseRecord;
import com.ruoyi.customer.mapper.ApiBrowseRecordMapper;
import com.ruoyi.customer.service.IApiBrowseRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户浏览足迹记录Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-11-07
 */
@Service
public class ApiBrowseRecordServiceImpl implements IApiBrowseRecordService
{
    @Autowired
    private ApiBrowseRecordMapper apiBrowseRecordMapper;

    /**
     * 查询用户浏览足迹记录
     * 
     * @param id 用户浏览足迹记录主键
     * @return 用户浏览足迹记录
     */
    @Override
    public ApiBrowseRecord selectBrowseRecordById(Long id)
    {
        return apiBrowseRecordMapper.selectBrowseRecordById(id);
    }

    /**
     * 查询用户浏览足迹记录列表
     * 
     * @param apiBrowseRecord 用户浏览足迹记录
     * @return 用户浏览足迹记录
     */
    @Override
    public List<ApiBrowseRecord> selectBrowseRecordList(ApiBrowseRecord apiBrowseRecord)
    {
        return apiBrowseRecordMapper.selectBrowseRecordList(apiBrowseRecord);
    }

    /**
     * 新增用户浏览足迹记录
     * 
     * @param apiBrowseRecord 用户浏览足迹记录
     * @return 结果
     */
    @Override
    public int insertBrowseRecord(ApiBrowseRecord apiBrowseRecord)
    {
        Long userId = apiBrowseRecord.getUserId();
        Long shopGoodsInfoId = apiBrowseRecord.getShopGoodsInfoId();
        ApiBrowseRecord apiBrowseRecordVo = apiBrowseRecordMapper.selectBrowseRecord(userId, shopGoodsInfoId);
        //如果已经有此商品的浏览记录，则更新浏览时间
        if (StringUtils.isNotNull(apiBrowseRecordVo)){
            apiBrowseRecordVo.setBrowseTime(DateUtils.getNowDate());
            return apiBrowseRecordMapper.updateBrowseRecord(apiBrowseRecordVo);
        }else {
            apiBrowseRecord.setBrowseTime(DateUtils.getNowDate());
            return apiBrowseRecordMapper.insertBrowseRecord(apiBrowseRecord);
        }
    }

    /**
     * 修改用户浏览足迹记录
     * 
     * @param apiBrowseRecord 用户浏览足迹记录
     * @return 结果
     */
    @Override
    public int updateBrowseRecord(ApiBrowseRecord apiBrowseRecord)
    {
        return apiBrowseRecordMapper.updateBrowseRecord(apiBrowseRecord);
    }

    /**
     * 批量删除用户浏览足迹记录
     * 
     * @param ids 需要删除的用户浏览足迹记录主键
     * @return 结果
     */
    @Override
    public int deleteBrowseRecordByIds(Long[] ids)
    {
        return apiBrowseRecordMapper.deleteBrowseRecordByIds(ids);
    }

    /**
     * 删除用户浏览足迹记录信息
     * 
     * @param id 用户浏览足迹记录主键
     * @return 结果
     */
    @Override
    public int deleteBrowseRecordById(Long id)
    {
        return apiBrowseRecordMapper.deleteBrowseRecordById(id);
    }
}
