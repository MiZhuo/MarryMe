package site.mizhuo.marry.goods.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import site.mizhuo.marry.common.CommonResult;
import site.mizhuo.marry.goods.constant.MessageConstant;
import site.mizhuo.marry.goods.domain.GoodsInfo;
import site.mizhuo.marry.goods.service.GoodsService;

import java.util.Map;

/**
 * @author mizhuo
 */
@RestController
@Api(tags = "GoodsController", value = "商品管理")
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    GoodsService goodsService;

    /**
     * 添加商品
     * @param goods
     * @return
     */
    @ApiOperation("添加商品")
    @PostMapping("/addGoods")
    public CommonResult<?> addGoods(GoodsInfo goods){
        if(goods == null){
            return CommonResult.failed(MessageConstant.ERROR_MESSAGE_001);
        }
        goodsService.saveGoods(goods);
        return CommonResult.success(MessageConstant.SUCCESS_MESSAGE_001);
    }

    /**
     * 获取商品列表
     * @param params 商品状态
     * @return
     */
    @ApiOperation("获取商品列表")
    @PostMapping("/getGoodsList")
    public CommonResult<Page<GoodsInfo>> getGoodsList(Map<String,Object> params){
        Page<GoodsInfo> goodsList = goodsService.queryGoodsList(params);
        return CommonResult.success(goodsList,MessageConstant.SUCCESS_MESSAGE_002);
    }

    /**
     * 获取商品详情
     * @param id
     * @return
     */
    @ApiOperation("获取商品详情")
    @PostMapping("/getGoodsInfo")
    public CommonResult<GoodsInfo> getGoodsInfo(@RequestParam("goodsId") Long id){
        if(id == null){
            return CommonResult.failed(MessageConstant.ERROR_MESSAGE_002);
        }
        GoodsInfo goods = goodsService.queryGoodsInfo(id);
        return CommonResult.success(goods,MessageConstant.SUCCESS_MESSAGE_003);
    }

    /**
     * 修改商品信息
     * @param goods
     * @return
     */
    @ApiOperation("修改商品信息")
    @PostMapping("/updateGoodsInfo")
    public CommonResult<?> updateGoodsInfo(GoodsInfo goods){
        if(goods == null){
            return CommonResult.failed(MessageConstant.ERROR_MESSAGE_001);
        }
        goodsService.updateGoodsInfo(goods);
        return CommonResult.success(MessageConstant.SUCCESS_MESSAGE_UPDATE);
    }

    /**
     * 删除商品
     * @param goodsId
     * @return
     */
    @ApiOperation("删除商品")
    @PostMapping("/deleteGoods")
    public CommonResult<?> deleteGoods(@RequestParam("goodsId") Long goodsId){
        goodsService.deleteGoods(goodsId);
        return CommonResult.success(MessageConstant.SUCCESS_MESSAGE_DELETE);
    }
}
