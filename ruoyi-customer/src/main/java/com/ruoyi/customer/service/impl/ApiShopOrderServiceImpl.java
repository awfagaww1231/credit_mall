package com.ruoyi.customer.service.impl;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.x.lang.LangUtils;
import com.ruoyi.customer.domain.*;
import com.ruoyi.customer.mapper.*;
import com.ruoyi.customer.service.IApiShopOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class ApiShopOrderServiceImpl implements IApiShopOrderService {

    @Autowired
    private ApiShopOrderMapper apiShopOrderMapper;

    @Resource
    private ApiShoppingCartMapper apiShoppingCartMapper;

    @Resource
    private ApiAuthMapper apiAuthMapper;

    @Resource
    private ApiUserBillDetailsMapper apiUserBillDetailsMapper;

    @Resource
    private ApiShopInfoMapper apiShopInfoMapper;

    @Resource
    private ApiSysUserMapper apiSysUserMapper;

    @Resource
    private ApiSellerBillDetailsMapper apiSellerBillDetailsMapper;

    /**
     * 新增店铺订单
     */
    @Override
    @Transactional
    public AjaxResult insertShopOrder(List<ApiShopOrder> apiShopOrders,Long userId) throws InterruptedException {
        for (int i = 0; i < apiShopOrders.size(); i++) {
            ApiShopOrder apiShopOrder = apiShopOrders.get(i);
            //验证订单信息是否丢失
            if (StringUtils.isNull(apiShopOrder.getGoodsId())){
                return AjaxResult.error("订单信息生成错误，请刷新后再试","hint_83");
            }
            if (StringUtils.isNull(apiShopOrder.getQuantity())){
                return AjaxResult.error("订单信息生成错误，请刷新后再试","hint_83");
            }
            if (StringUtils.isNull(apiShopOrder.getSinglePrice())){
                return AjaxResult.error("订单信息生成错误，请刷新后再试","hint_83");
            }
            if (StringUtils.isNull(apiShopOrder.getTotalPrice())){
                return AjaxResult.error("订单信息生成错误，请刷新后再试","hint_83");
            }
            if (StringUtils.isNull(apiShopOrder.getShopId())){
                return AjaxResult.error("订单信息生成错误，请刷新后再试","hint_83");
            }
            if (StringUtils.isNull(apiShopOrder.getSinglePrice())){
                return AjaxResult.error("订单信息生成错误，请刷新后再试","hint_83");
            }
            //订单号
            String orderCode =  String.valueOf(System.currentTimeMillis()) + String.valueOf(userId);
            Thread.sleep(1);
            apiShopOrder.setCreateTime(DateUtils.getNowDate());
            apiShopOrder.setOrderCode(orderCode);
            apiShopOrder.setUserId(userId);
            apiShopOrder.setStatus(0);
        }
        //批量插入订单
        int insertShopOrders = apiShopOrderMapper.insertShopOrders(apiShopOrders);
        if (apiShopOrders.size() != insertShopOrders){
            throw new RuntimeException("操作失败");
        }

        ArrayList<Long> shoppingCartIds = new ArrayList<>();
        ArrayList<ApiShopOrder> list = new ArrayList<>();
        for (int i = 0; i < apiShopOrders.size(); i++) {
            //清空购物车信息
            Long shoppingCartId = apiShopOrders.get(i).getShoppingCartId();
            if (StringUtils.isNotNull(shoppingCartId)){
                shoppingCartIds.add(shoppingCartId);
            }
            Long id = apiShopOrders.get(i).getId();
            //提供详细信息放入请求响应
            ApiShopOrder apiShopOrder = apiShopOrderMapper.selectShopOrderById(id);
            if (StringUtils.isNotNull(apiShopOrder)){
                list.add(apiShopOrder);
            }
        }
        if (shoppingCartIds.size() > 0){
            //清空购物车信息
            int deleteShoppingCartByIds = apiShoppingCartMapper.deleteShoppingCartByIds((shoppingCartIds.toArray(new Long[shoppingCartIds.size()])));
            if (shoppingCartIds.size() != deleteShoppingCartByIds){
                throw new RuntimeException("操作失败");
            }
        }
        return AjaxResult.success().put("apiShopOrders",list);
    }

    /**
     * 去结算店铺商品订单
     */
    @Override
    @Transactional
    public AjaxResult toPayShopOrder(List<ApiShopOrder> apiShopOrders, Long userId) {
        //用户信息
        ApiUserInfo apiUserInfo = apiAuthMapper.selectUserById(userId);
        if (StringUtils.isNull(apiUserInfo)){
            return AjaxResult.error("出错啦，请重新登录后尝试","hint_9");
        }
        if (apiShopOrders.size() == 0){
            return AjaxResult.error("至少选择一笔订单结算","hint_85");
        }
        BigDecimal totalPrice = BigDecimal.ZERO;
        for (int i = 0; i < apiShopOrders.size(); i++) {
            ApiShopOrder apiShopOrder = apiShopOrders.get(i);
            //验证订单信息是否丢失
            if (StringUtils.isNull(apiShopOrder.getUserId())){
                return AjaxResult.error("订单信息获取异常，请重新登录后尝试","hint_86");
            }
            if (StringUtils.isNull(apiShopOrder.getStatus())){
                return AjaxResult.error("订单信息获取异常，请重新登录后尝试","hint_86");
            }
            if (StringUtils.isNull(apiShopOrder.getApiUserReceiveAddress())){
                return AjaxResult.error("请先填写收货地址信息","hint_87");
            }
//
            if (StringUtils.isNull(apiShopOrder.getApiUserReceiveAddress().getId()) | StringUtils.isNull(apiShopOrder.getApiUserReceiveAddress().getReceiverName())
                    | StringUtils.isNull(apiShopOrder.getApiUserReceiveAddress().getReceiverArea()) | StringUtils.isNull(apiShopOrder.getApiUserReceiveAddress().getReceiverAddress())){
                return AjaxResult.error("请先填写收货地址信息","hint_87");
            }
            if (StringUtils.isNull(apiShopOrder.getTotalPrice())){
                return AjaxResult.error("订单信息获取异常，请重新登录后尝试","hint_86");
            }
            //验证支付此订单的操作是否本人
            if (!apiUserInfo.getId().equals(apiShopOrder.getUserId())){
                return AjaxResult.error("出错啦，请重新登录后尝试","hint_9");
            }
            //验证订单状态是否为未支付
            if (apiShopOrder.getStatus() != 0){
                if (apiShopOrder.getStatus() == 2){
                    return AjaxResult.error("订单号为" + apiShopOrder.getOrderCode() + "的订单已经取消，无法支付","DTEWS");
                }
                return AjaxResult.error("订单号为" + apiShopOrder.getOrderCode() + "的订单已经支付过，无需重复支付","DTEWS");
            }
            //每笔订单的总价
            BigDecimal totalPricePerOrder = apiShopOrder.getTotalPrice();
            totalPrice = totalPrice.add(totalPricePerOrder);
        }
        BigDecimal userAmount = null;
        //已使用额度
        BigDecimal creditCardUsedAmount = apiUserInfo.getCreditCardUsedAmount();
        //剩余额度
        BigDecimal creditCardRemainingAmount = apiUserInfo.getCreditCardRemainingAmount();
        //如果支付环境正常，则开始支付操作
        //支付方式
        Integer payMethod = apiShopOrders.get(0).getPayMethod();
        if (payMethod == null){
            return AjaxResult.error("请选择支付方式","hint_89");
        }
        //平台余额支付
        if (payMethod == 0){
            //用户余额
            userAmount = apiUserInfo.getAmount();
            if (userAmount == null){
                userAmount = BigDecimal.ZERO;
            }
            //如果余额不足
            if (userAmount.compareTo(totalPrice) == -1){
                return AjaxResult.error("余额不足，请先进行充值","hint_90");
            }
            //如果余额充足,则更新余额
            int updateUserAmount = apiAuthMapper.updateUserAmount(apiUserInfo.getId(), userAmount.subtract(totalPrice), apiUserInfo.getAmount());
            if (updateUserAmount <= 0){
                throw new RuntimeException("操作失败");
            }
        }else {
            //信用额度支付
            if (apiUserInfo.getCreditCardStatus() != 5){
                return AjaxResult.error("信用卡片还未激活，无法使用信用额度","hint_91");
            }
            if (apiUserInfo.getCreditCardEnableStatus() != 1){
                if (apiUserInfo.getCreditCardEnableStatus() == 0){
                    return AjaxResult.error("信用卡片还未激活，无法使用信用额度","hint_92");
                }else if (apiUserInfo.getCreditCardEnableStatus() == 2){
                    return AjaxResult.error("信用卡片已激活，但是无法使用，请联系客服","hint_93");
                }else if (apiUserInfo.getCreditCardEnableStatus() == 3){
                    return AjaxResult.error("请先前往开通vip权限","hint_109");
                }else {
                    return AjaxResult.error();
                }
            }

            //如果可用信用额度不足
            if (creditCardRemainingAmount.compareTo(totalPrice) == -1){
                return AjaxResult.error("可用信用额度不足","hint_94");
            }
            //如果可用信用额度充足,则更新已使用额度
            ApiUserInfo apiUserInfoVo = new ApiUserInfo();
            apiUserInfoVo.setId(apiUserInfo.getId());
            apiUserInfoVo.setCreditCardUsedAmount(creditCardUsedAmount.add(totalPrice));
            apiUserInfoVo.setCreditCardUsedAmountBefore(creditCardUsedAmount);
            int updateUserInfo = apiAuthMapper.updateUserInfo(apiUserInfoVo);
            if (updateUserInfo <= 0){
                throw new RuntimeException("操作失败");
            }
        }

        //变更订单状态为已支付,并且插入流水记录
        for (int i = 0; i < apiShopOrders.size(); i++) {
            ApiShopOrder apiShopOrder = apiShopOrders.get(i);
            //设置收获地址信息
            ApiUserReceiveAddress apiUserReceiveAddress = apiShopOrder.getApiUserReceiveAddress();
            String receiveInf = apiUserReceiveAddress.getReceiverName() + " / " + apiUserReceiveAddress.getReceiverMobile() + " / "+ apiUserReceiveAddress.getReceiverArea() +  " / " + apiUserReceiveAddress.getReceiverAddress();
            //填充收货信息(收货人+收货手机+收货地址)
            apiShopOrder.setReceiveInfo(receiveInf);
            //变更订单状态为已支付
            apiShopOrder.setStatus(1);
            //更新支付时间
            apiShopOrder.setPayTime(DateUtils.getNowDate());
            int updateShopOrder = apiShopOrderMapper.updateShopOrder(apiShopOrder);
            if (updateShopOrder <= 0){
                throw new RuntimeException("操作失败");
            }

            //插入流水记录
            ApiUserBillDetails userBillDetails = new ApiUserBillDetails();
            userBillDetails.setOrderCode(apiShopOrder.getOrderCode());
            userBillDetails.setOrderAmount(apiShopOrder.getTotalPrice());
            userBillDetails.setOrderType(1);
            userBillDetails.setOrderTime(apiShopOrder.getPayTime());
            userBillDetails.setUserId(apiShopOrder.getUserId());
            userBillDetails.setOrderClass(2);
            if (payMethod == 0){
                userBillDetails.setAmountBefore(userAmount);
                userBillDetails.setAmountAfter(userAmount.subtract(apiShopOrder.getTotalPrice()));
                apiUserBillDetailsMapper.insertUserBillDetails(userBillDetails);
                userAmount = userAmount.subtract(apiShopOrder.getTotalPrice());
            }else {
                //已使用额度
                userBillDetails.setAmountAfter(creditCardUsedAmount.add(apiShopOrder.getTotalPrice()));
                apiUserBillDetailsMapper.insertUserBillDetails(userBillDetails);
            }

        }
        return AjaxResult.success();
    }

    @Override
    public List<ApiShopOrder> myShopOrder(ApiShopOrder apiShopOrder) {
        apiShopOrder.setLanguageId(LangUtils.getLanguageId());
        List<ApiShopOrder> apiShopOrders = apiShopOrderMapper.myShopOrder(apiShopOrder);
        for (int i = 0; i < apiShopOrders.size(); i++) {
            //获取物流订单号
            if (apiShopOrders.get(i).getStatus() == 3 | apiShopOrders.get(i).getStatus() == 4) {
                Long shopOrderId = apiShopOrders.get(i).getId();
                ApiShipmentNumber shipmentNumber = apiShopOrderMapper.selectShipmentNumberByShopOrderId(shopOrderId);
                if (StringUtils.isNotNull(shipmentNumber)) {
                    apiShopOrders.get(i).setShipmentNumber(shipmentNumber.getShipmentnumber());
                }
            }
        }
        return apiShopOrders;
    }

    @Override
    public ApiShopOrder selectShopOrderById(Long id) {
        ApiShopOrder apiShopOrder = apiShopOrderMapper.selectShopOrderById(id);
        return apiShopOrder;
    }

    @Override
    public int updateShopOrder(ApiShopOrder apiShopOrder) {
        return apiShopOrderMapper.updateShopOrder(apiShopOrder);
    }

    @Override
    @Transactional
    public AjaxResult toReceipt(Long id,Long userId) {
        ApiShopOrder apiShopOrder = apiShopOrderMapper.selectShopOrderById(id);
        if (StringUtils.isNull(apiShopOrder)){
            return AjaxResult.error("获取订单信息异常","hint_95");
        }
        //验证确认收货操作的操作人是否本人
        if (!userId.equals(apiShopOrder.getUserId())){
            return AjaxResult.error("出错啦，请重新登录后尝试","hint_9");
        }
        if (apiShopOrder.getStatus() != 3){
            return AjaxResult.error("此订单状态异常，请刷新后尝试","hint_97");
        }
        //如果一切正常，执行确认收货操作
        //更新完订单完成时间
        apiShopOrder.setFinishTime(DateUtils.getNowDate());
        //订单完成,商家的订单收入入账
        ApiShopInfo apiShopInfo = apiShopInfoMapper.selectShopInfoById(apiShopOrder.getShopId());
        if (StringUtils.isNull(apiShopInfo)){
            return AjaxResult.error("获取订单信息异常，请刷新后尝试","hint_50");
        }
        SysUser user = apiSysUserMapper.selectUserById(apiShopInfo.getShopOwnerId());
        if (StringUtils.isNull(user)){
            return AjaxResult.error();
        }
        BigDecimal sellerAmount = user.getAmount();
        if (sellerAmount == null){
            sellerAmount = BigDecimal.ZERO;
        }
        user.setAmount(sellerAmount.add(apiShopOrder.getTotalPrice()));
        int updateUser = apiSysUserMapper.updateUser(user);
        if (updateUser <= 0){
            throw new RuntimeException("操作失败");
        }
        //插入流水记录
        ApiSellerBillDetails sellerBillDetails = new ApiSellerBillDetails();
        sellerBillDetails.setOrderCode(apiShopOrder.getOrderCode());
        sellerBillDetails.setOrderAmount(apiShopOrder.getTotalPrice());
        sellerBillDetails.setOrderType(0);
        //流水时间为订单提交至平台的时间
        sellerBillDetails.setOrderTime(apiShopOrder.getFinishTime());
        sellerBillDetails.setUserId(apiShopInfo.getShopOwnerId());
        sellerBillDetails.setOrderClass(4);
        sellerBillDetails.setAmountBefore(sellerAmount);
        sellerBillDetails.setAmountAfter(sellerAmount.add(apiShopOrder.getTotalPrice()));
        int insertSellerBillDetails = apiSellerBillDetailsMapper.insertSellerBillDetails(sellerBillDetails);
        if (insertSellerBillDetails <= 0){
            throw new RuntimeException("操作失败");
        }
        //变更状态为待评论
        apiShopOrder.setStatus(7);
        int updateShopOrder = apiShopOrderMapper.updateShopOrder(apiShopOrder);
        if (updateShopOrder <= 0){
            throw new RuntimeException("操作失败");
        }
        return AjaxResult.success();
    }


    @Override
    public AjaxResult deleteShopOrderByIds(List<Long> ids) {
        for (int i = 0; i < ids.size(); i++) {
            ApiShopOrder apiShopOrder = apiShopOrderMapper.selectShopOrderById(ids.get(i));
            if (StringUtils.isNull(apiShopOrder)){
                return AjaxResult.error("未找到订单信息，请刷新后尝试","hint_99");
            }
            if (apiShopOrder.getStatus() != 0){
                return AjaxResult.error("订单号为"+ apiShopOrder.getOrderCode() +"的订单已经支付过，无法取消","DTEWS");
            }
        }
        int deleteShopOrderByIds = apiShopOrderMapper.deleteShopOrderByIds(ids);
        if (deleteShopOrderByIds != ids.size()){
            throw new RuntimeException("操作失败");
        }
        return AjaxResult.success();
    }
}
