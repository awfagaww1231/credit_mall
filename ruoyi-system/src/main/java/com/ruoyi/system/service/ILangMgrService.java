package com.ruoyi.system.service;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.system.domain.LangMgr;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * 多语言配置包Service接口
 * 
 * @author ruoyi
 * @date 2023-03-22
 */
public interface ILangMgrService 
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
    public AjaxResult insertLangMgr(LangMgr langMgr) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException;

    /**
     * 修改多语言配置包
     * 
     * @param langMgr 多语言配置包
     * @return 结果
     */
    public AjaxResult updateLangMgr(LangMgr langMgr) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException;

    /**
     * 批量删除多语言配置包
     * 
     * @param ids 需要删除的多语言配置包主键集合
     * @return 结果
     */
    public int deleteLangMgrByIds(Long[] ids) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException;

    /**
     * 删除多语言配置包信息
     * 
     * @param id 多语言配置包主键
     * @return 结果
     */
    public int deleteLangMgrById(Long id);

    //重新加载缓存的语言包
    void reloadLangMgrCache() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException;
}
