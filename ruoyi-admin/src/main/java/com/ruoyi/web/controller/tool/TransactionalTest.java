package com.ruoyi.web.controller.tool;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.system.domain.ShopReview;
import com.ruoyi.system.mapper.ShopReviewMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * swagger 回滚测试测试方法
 *
 * @author ruoyi
 */
@Api("回滚测试")
@RestController
@RequestMapping("/test/transactional")
public class TransactionalTest {

    @Autowired
    private ShopReviewMapper shopReviewMapper;



    @ApiOperation("test")
    @GetMapping("/test")
    @Transactional
    public AjaxResult test()
    {
        ShopReview shopReview1 = new ShopReview();
        shopReview1.setId(4L);
        shopReview1.setRemark("内容1");
        shopReviewMapper.updateShopReview(shopReview1);

        Integer a = null;
        a.toString();

        ShopReview shopReview2 = new ShopReview();
        shopReview2.setId(4L);
        shopReview2.setRemark("内容2");
        shopReviewMapper.updateShopReview(shopReview2);

        return AjaxResult.success();
    }


}
