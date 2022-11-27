package site.mizhuo.marry.goods.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import site.mizhuo.marry.goods.domain.GoodsInfo;

import java.util.List;
import java.util.Map;

/**
 * @author mizhuo
 */
public interface GoodsService {
    /**
     * 添加商品
     * @param goods 商品信息
     */
    void saveGoods(GoodsInfo goods);

    /**
     * 查询商品列表
     * @param params 商品状态以及分页参数 1未采购 2已采购 0已删除
     * @return 商品列表
     */
    Page<GoodsInfo> queryGoodsList(Map<String,Object> params);

    /**
     * 查询商品详情
     * @param id 商品ID
     * @return 商品信息
     */
    GoodsInfo queryGoodsInfo(Long id);

    /**
     * 更新商品信息
     * @param goods
     */
    void updateGoodsInfo(GoodsInfo goods);

    /**
     * 删除商品信息
     * @param ids
     */
    void deleteGoods(Long[] ids);
}
