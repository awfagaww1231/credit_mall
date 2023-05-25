package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.LangMgr;

import java.util.List;

/**
 * 多语言配置包Mapper接口
 * 
 * @author ruoyi
 * @date 2023-03-22
 */
public interface LangMgrMapper 
{
    /**
     * 查询多语言配置包
     * 
     * @param id 多语言配置包主键
     * @return 多语言配置包
     */
    public LangMgr selectLangMgrById(Long id);

    /**
     * 查询多语言配置包列表
     * 
     * @param langMgr 多语言配置包
     * @return 多语言配置包集合
     */
    public List<LangMgr> selectLangMgrList(LangMgr langMgr);

    /**
     * 新增多语言配置包
     * 
     * @param langMgr 多语言配置包
     * @return 结果
     */
    public int insertLangMgr(LangMgr langMgr);

    /**
     * 修改多语言配置包
     * 
     * @param langMgr 多语言配置包
     * @return 结果
     */
    public int updateLangMgr(LangMgr langMgr);

    /**
     * 删除多语言配置包
     * 
     * @param id 多语言配置包主键
     * @return 结果
     */
    public int deleteLangMgrById(Long id);

    /**
     * 批量删除多语言配置包
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteLangMgrByIds(Long[] ids);
}
