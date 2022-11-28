package site.mizhuo.marry.goods.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.map.MapUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.mizhuo.marry.domain.UserDto;
import site.mizhuo.marry.goods.domain.GoodsInfo;
import site.mizhuo.marry.goods.mapper.GoodsInfoMapper;
import site.mizhuo.marry.goods.service.GoodsService;
import site.mizhuo.marry.utils.CommonUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Optional;

/**
 * @author mizhuo
 */
@Slf4j
@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    GoodsInfoMapper mapper;

    @Autowired
    private HttpServletRequest request;

    @Override
    public void saveGoods(GoodsInfo goods) {
        UserDto user = CommonUtils.getCurrentUser(request);
        Long userGroupId = Optional.ofNullable(user)
                .map(UserDto::getGroupId).orElse(null);
        goods.setUserGroupId(userGroupId);
        goods.setCreateTime(DateUtil.date());
        goods.setUpdateTime(DateUtil.date());
        mapper.insert(goods);
    }

    @Override
    public Page<GoodsInfo> queryGoodsList(Map<String,Object> params) {
        UserDto user = CommonUtils.getCurrentUser(request);
        Long userGroupId = Optional.ofNullable(user)
                .map(UserDto::getGroupId).orElse(null);
        Page<GoodsInfo> page = new Page<>(MapUtil.getLong(params,"pageNum"),MapUtil.getLong(params,"pageSize"));
        LambdaQueryWrapper<GoodsInfo> wrapper = new LambdaQueryWrapper<>();
        wrapper.select(GoodsInfo::getId,GoodsInfo::getGoodsName,GoodsInfo::getGoodsPic)
                .eq(GoodsInfo::getUserGroupId,userGroupId)
                .eq(GoodsInfo::getStatus,params.getOrDefault("status",1))
                .orderByAsc(GoodsInfo::getCreateTime);
        return mapper.selectPage(page, wrapper);
    }

    @Override
    public GoodsInfo queryGoodsInfo(Long id) {
        UserDto user = CommonUtils.getCurrentUser(request);
        Long userGroupId = Optional.ofNullable(user)
                .map(UserDto::getGroupId).orElse(null);
        LambdaQueryWrapper<GoodsInfo> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(GoodsInfo::getId,id)
                .eq(GoodsInfo::getUserGroupId,userGroupId)
                .eq(GoodsInfo::getStatus,1)
                .orderByAsc(GoodsInfo::getCreateTime);
        return mapper.selectOne(wrapper);
    }

    @Override
    public void updateGoodsInfo(GoodsInfo goods) {
        UserDto user = CommonUtils.getCurrentUser(request);
        Long userGroupId = Optional.ofNullable(user)
                .map(UserDto::getGroupId).orElse(null);
        goods.setUpdateTime(DateUtil.date());
        LambdaUpdateWrapper<GoodsInfo> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(GoodsInfo::getId,goods.getId())
                .eq(GoodsInfo::getUserGroupId,userGroupId);
        mapper.update(goods,wrapper);
    }

    @Override
    public void deleteGoods(Long[] ids) {
        UserDto user = CommonUtils.getCurrentUser(request);
        Long userGroupId = Optional.ofNullable(user)
                .map(UserDto::getGroupId).orElse(null);
        LambdaUpdateWrapper<GoodsInfo> wrapper = new LambdaUpdateWrapper<>();
        wrapper.set(GoodsInfo::getStatus,0)
                .in(GoodsInfo::getId,   ids)
                .eq(GoodsInfo::getUserGroupId,userGroupId);
        mapper.update(null,wrapper);
    }
}
