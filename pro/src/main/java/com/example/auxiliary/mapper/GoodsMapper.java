package com.example.auxiliary.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.auxiliary.entity.Goods;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GoodsMapper extends BaseMapper<Goods> {

    /**
     * 获取热门推荐商品
     *
     * @return 热门推荐商品列表
     */
    List<Goods> getHotGoods();

    /**
     * 获取用户推荐的商品
     *
     * @param userId 用户id
     * @return 用户推荐的商品
     */
    List<Goods> getUserGoods(@Param("userId") String userId);

    /**
     * 获取标签商品
     *
     * @param tags 标签
     * @return 推荐商品
     */
    List<Goods> getGoodsByTags(@Param("tags") List<String> tags);

    /**
     * 获取排行榜商品
     *
     * @return 排行榜商品
     */
    List<Goods> getRankGoods();

    /**
     * 根据收藏记录和浏览记录获取商品的标签（用于去获取猜用户喜欢的搜索）
     *
     * @param userId 用户id
     * @return 商品列表
     */
    List<Goods> getUserLikeGoods(@Param("userId") String userId);
}
