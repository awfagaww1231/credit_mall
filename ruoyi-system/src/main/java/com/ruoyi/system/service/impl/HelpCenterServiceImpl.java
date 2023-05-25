package com.ruoyi.system.service.impl;

import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.x.lang.LangUtils;
import com.ruoyi.system.domain.HelpCenter;
import com.ruoyi.system.mapper.HelpCenterMapper;
import com.ruoyi.system.service.IHelpCenterService;
import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * 帮助中心Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-04-19
 */
@Service
public class HelpCenterServiceImpl implements IHelpCenterService 
{
    @Resource
    private HelpCenterMapper helpCenterMapper;

    /**
     * 查询帮助中心
     * 
     * @param id 帮助中心主键
     * @return 帮助中心
     */
    @Override
    public HelpCenter selectHelpCenterById(Long id)
    {
        return helpCenterMapper.selectHelpCenterById(id);
    }

    /**
     * 查询帮助中心列表
     * 
     * @param helpCenter 帮助中心
     * @return 帮助中心
     */
    @Override
    public List<HelpCenter> selectHelpCenterList(HelpCenter helpCenter) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        List<HelpCenter> helpCenters = helpCenterMapper.selectHelpCenterList(helpCenter);
        //取多语言值
        if (helpCenter.getIsLang() != null){
            //语言
            String lang = LangUtils.getLang();
            if (!lang.equals("zh")){
                lang = LangUtils.toUpperCaseFirstIndex(lang);
                for (int i = 0; i < helpCenters.size(); i++) {
                    helpCenter = helpCenters.get(i);
                    Object question = PropertyUtils.describe(helpCenter).get("question" + lang);
                    Object answer = PropertyUtils.describe(helpCenter).get("answer" + lang);
                    if (StringUtils.isNotNull(question)){
                        String questionLangValue = String.valueOf(question);
                        if (StringUtils.isNotEmpty(questionLangValue)){
                            helpCenter.setQuestion(questionLangValue);
                        }
                    }
                    if (StringUtils.isNotNull(answer)){
                        String answerLangValue = String.valueOf(answer);
                        if (StringUtils.isNotEmpty(answerLangValue)){
                            helpCenter.setAnswer(answerLangValue);
                        }
                    }
                }
            }
        }
        return helpCenters;
    }

    /**
     * 新增帮助中心
     * 
     * @param helpCenter 帮助中心
     * @return 结果
     */
    @Override
    public int insertHelpCenter(HelpCenter helpCenter)
    {
        return helpCenterMapper.insertHelpCenter(helpCenter);
    }

    /**
     * 修改帮助中心
     * 
     * @param helpCenter 帮助中心
     * @return 结果
     */
    @Override
    public int updateHelpCenter(HelpCenter helpCenter)
    {
        return helpCenterMapper.updateHelpCenter(helpCenter);
    }

    /**
     * 批量删除帮助中心
     * 
     * @param ids 需要删除的帮助中心主键
     * @return 结果
     */
    @Override
    public int deleteHelpCenterByIds(Long[] ids)
    {
        return helpCenterMapper.deleteHelpCenterByIds(ids);
    }

    /**
     * 删除帮助中心信息
     * 
     * @param id 帮助中心主键
     * @return 结果
     */
    @Override
    public int deleteHelpCenterById(Long id)
    {
        return helpCenterMapper.deleteHelpCenterById(id);
    }
}
