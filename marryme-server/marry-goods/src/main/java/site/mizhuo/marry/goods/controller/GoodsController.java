package site.mizhuo.marry.goods.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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
@RequestMapping("/")
public class GoodsController {

    @Autowired
    GoodsService goodsService;

    /**
     * 添加商品
     * @param goods 商品信息
     * @return void
     */
    @ApiOperation("添加商品")
    @PostMapping("/goods")
    public CommonResult<?> addGoods(GoodsInfo goods){
        if(goods == null){
            return CommonResult.failed(MessageConstant.ERROR_MESSAGE_001);
        }
        goodsService.saveGoods(goods);
        return CommonResult.success(MessageConstant.SUCCESS_MESSAGE_001);
    }

    /**
     * 获取商品列表
     * @param params 商品状态以及分页参数
     * @return 商品列表
     */
    @ApiOperation("获取商品列表")
    @GetMapping("/goodsList")
    public CommonResult<Page<GoodsInfo>> getGoodsList(@RequestParam Map<String,Object> params){
        Page<GoodsInfo> goodsList = goodsService.queryGoodsList(params);
        return CommonResult.success(goodsList,MessageConstant.SUCCESS_MESSAGE_002);
    }

    /**
     * 获取商品详情
     * @param id 商品ID
     * @return 商品详情
     */
    @ApiOperation("获取商品详情")
    @GetMapping("/goodsInfo/{id}")
    public CommonResult<GoodsInfo> getGoodsInfo(@PathVariable Long id){
        if(id == null){
            return CommonResult.failed(MessageConstant.ERROR_MESSAGE_002);
        }
        GoodsInfo goods = goodsService.queryGoodsInfo(id);
        return CommonResult.success(goods,MessageConstant.SUCCESS_MESSAGE_003);
    }

    /**
     * 修改商品信息
     * @param goods 商品信息
     * @return void
     */
    @ApiOperation("修改商品信息")
    @PutMapping("/goods")
    public CommonResult<?> updateGoodsInfo(GoodsInfo goods){
        if(goods == null){
            return CommonResult.failed(MessageConstant.ERROR_MESSAGE_001);
        }
        goodsService.updateGoodsInfo(goods);
        return CommonResult.success(MessageConstant.SUCCESS_MESSAGE_UPDATE);
    }

    /**
     * 删除商品
     * @param ids 商品ID
     * @return void
     */
    @ApiOperation("删除商品")
    @DeleteMapping("/goods/{ids}")
    public CommonResult<?> deleteGoods(@PathVariable Long[] ids){
        goodsService.deleteGoods(ids);
        return CommonResult.success(MessageConstant.SUCCESS_MESSAGE_DELETE);
    }
}
