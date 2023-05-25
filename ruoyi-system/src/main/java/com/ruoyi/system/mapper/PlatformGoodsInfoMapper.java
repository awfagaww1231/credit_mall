package com.ruoyi.system.mapper;

import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.system.domain.PlatformGoodsInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 平台商品信息(平台的)Mapper接口
 * 
 * @author ruoyi
 * @date 2022-10-27
 */
public interface PlatformGoodsInfoMapper 
{
    /**
     * 查询平台商品信息(平台的)
     * 
     * @param id 平台商品信息(平台的)主键
     * @return 平台商品信息(平台的)
     */
    public PlatformGoodsInfo selectPlatformGoodsInfoById(Long id);

    /**
     * 查询平台商品信息(平台的)列表
     * 
     * @param platformGoodsInfo 平台商品信息(平台的)
     * @return 平台商品信息(平台的)集合
     */
    public List<PlatformGoodsInfo> selectPlatformGoodsInfoList(PlatformGoodsInfo platformGoodsInfo);

    /**
     * 新增平台商品信息(平台的)
     * 
     * @param platformGoodsInfo 平台商品信息(平台的)
     * @return 结果
     */
    public int insertPlatformGoodsInfo(PlatformGoodsInfo platformGoodsInfo);
    /**
     * 新增用户信息
     *
     * @param user 用户信息
     * @return 结果
     */
    public int insertUser(SysUser user);

    /**
     * 修改平台商品信息(平台的)
     * 
     * @param platformGoodsInfo 平台商品信息(平台的)
     * @return 结果
     */
    public int updatePlatformGoodsInfo(PlatformGoodsInfo platformGoodsInfo);

    /**
     * 删除平台商品信息(平台的)
     * 
     * @param id 平台商品信息(平台的)主键
     * @return 结果
     */
    public int deletePlatformGoodsInfoById(Long id);

    /**
     * 批量删除平台商品信息(平台的)
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deletePlatformGoodsInfoByIds(Long[] ids);

    //删除的时候变更状态为删除
    int delete(Long[] ids);

    //批量上架
    int onTheShelf(@Param("ids") List<Long> ids);

    //批量下架
    int offTheShelf(@Param("ids")List<Long> ids);

    //根据多语言id和商品id查询多语言值对象
    PlatformGoodsInfo selectLanguageObject(@Param("languageId")Long languageId,
                                      @Param("goodsId")Long goodsId);

    //变更多语言值
    int changeLanguageValue(PlatformGoodsInfo platformGoodsInfo);

    //新增多语言值
    int insertLanguageValue(PlatformGoodsInfo platformGoodsInfo);

    PlatformGoodsInfo selectPlatformGoodsInfoLanguageById(@Param("id")Long id, @Param("languageId")Long languageId);
}
