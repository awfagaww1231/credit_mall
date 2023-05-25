package com.ruoyi.web.controller.app;

import com.ruoyi.common.annotation.RepeatSubmit;
import com.ruoyi.common.constant.HttpStatus;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.customer.common.AppletTokenService;
import com.ruoyi.customer.domain.ApiShoppingCart;
import com.ruoyi.customer.domain.AppletLoginUser;
import com.ruoyi.customer.service.IApiShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;

import java.util.ArrayList;
import java.util.List;

/**
 * 购物车信息Controller +
 * 
 * @author ruoyi
 * @date 2022-11-04
 */
@RestController
@RequestMapping("/api/shoppingCart")
public class ApiShoppingCartController extends BaseController
{
    @Autowired
    private IApiShoppingCartService apiShoppingCartService;

    @Autowired
    private AppletTokenService appletTokenService;

    /**
     * 查询购物车信息列表
     */
    @GetMapping("/shoppingCartList")
    public AjaxResult list(ApiShoppingCart apiShoppingCart)
    {
        AppletLoginUser loginUser = appletTokenService.getLoginUser ();
        if(StringUtils.isNull(loginUser)){
            return AjaxResult.success(getDataTable(new ArrayList<>()));
        }else {
            appletTokenService.refreshToken(loginUser);
        }
        apiShoppingCart.setUserId(loginUser.getApiUserInfo().getId());
        startPage();
        List<ApiShoppingCart> list = apiShoppingCartService.selectShoppingCartList(apiShoppingCart);
        return AjaxResult.success(getDataTable(list));
    }

    /**
     * 新增购物车信息
     */
    @PostMapping("/toAddShoppingCart")
    @RepeatSubmit(interval = 1500)
    public AjaxResult toAddShoppingCart(@RequestBody ApiShoppingCart apiShoppingCart)
    {
        AppletLoginUser loginUser = appletTokenService.getLoginUser ();
        if(StringUtils.isNull(loginUser)){
            return AjaxResult.error(HttpStatus.UNAUTHORIZED,"登录信息失效");
        }else {
            appletTokenService.refreshToken(loginUser);
        }
        if (StringUtils.isNull(apiShoppingCart.getShopGoodsInfoId())){
            return AjaxResult.error("获取商品信息详情异常，请刷新后尝试","hint_38");
        }
        if (StringUtils.isNull(apiShoppingCart.getCartQuantity())){
            return AjaxResult.error("获取商品信息详情异常，请刷新后尝试","hint_39");
        }
        if (apiShoppingCart.getCartQuantity() <= 0){
            return AjaxResult.error("数量至少为1","hint_40");
        }
        return apiShoppingCartService.toAddShoppingCart(apiShoppingCart,loginUser.getApiUserInfo().getId());
    }

    /**
     * 修改购物车信息
     */
    @PostMapping("/toUpdateShoppingCart")
    public AjaxResult toUpdateShoppingCart(@RequestBody ApiShoppingCart apiShoppingCart)
    {
        AppletLoginUser loginUser = appletTokenService.getLoginUser ();
        if(StringUtils.isNull(loginUser)){
            return AjaxResult.error(HttpStatus.UNAUTHORIZED,"登录信息失效");
        }else {
            appletTokenService.refreshToken(loginUser);
        }
        return toAjax(apiShoppingCartService.updateShoppingCart(apiShoppingCart));
    }

    /**
     * 删除购物车信息
     */
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        AppletLoginUser loginUser = appletTokenService.getLoginUser ();
        if(StringUtils.isNull(loginUser)){
            return AjaxResult.error(HttpStatus.UNAUTHORIZED,"登录信息失效");
        }else {
            appletTokenService.refreshToken(loginUser);
        }
        return toAjax(apiShoppingCartService.deleteShoppingCartByIds(ids));
    }
}
