package com.example.auxiliary.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.auxiliary.cumenum.GoodsStatusEnum;
import com.example.auxiliary.entity.Goods;
import com.example.auxiliary.entity.OrderRecord;
import com.example.auxiliary.entity.Result;
import com.example.auxiliary.entity.UserComment;
import com.example.auxiliary.service.IGoodsService;
import com.example.auxiliary.service.IOrderRecordService;
import com.example.auxiliary.service.IUserCommentService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 商品接口
 *
 * @author ztx
 * @date 2021-05-23 23:13
 */
@RequestMapping("/goods")
@RestController
@RequiredArgsConstructor
public class GoodsController {

    private final IGoodsService goodsService;
    private final IOrderRecordService orderRecordService;
    private final IUserCommentService userCommentService;

    /**
     * 获取菜单列表
     *
     * @return 菜单列表
     */
    @GetMapping("/goods")
    public Result<?> getGoods() {
        return Result.createSuccess(this.goodsService.list());
    }

    /**
     * 获取种类列表
     *
     * @return 种类列表
     */
    @GetMapping("/catoryGoodList")
    public Result<Map<String, Object>> getCatoryGoodList() {
        return this.goodsService.getCatoryGoodList();
    }

    /**
     * 添加商品
     *
     * @param goods 商品数据
     * @return 商品数据
     */
    @PostMapping("/goods")
    public Result<Goods> addGoods(@RequestBody Goods goods) {
        if (StringUtils.isEmpty(goods.getId())) {
            goods.setCreateDatetime(LocalDateTime.now());
            goods.setStatus(GoodsStatusEnum.ONLINE.getValue());
        }
        this.goodsService.saveOrUpdate(goods);
        return Result.createSuccess(goods);
    }

    /**
     * 删除商品信息
     *
     * @param id 商品id
     * @return 结果
     */
    @DeleteMapping("/{id}")
    public Result<Void> delete(@PathVariable String id) {
        this.goodsService.removeById(id);
        List<OrderRecord> orderRecordList = this.orderRecordService.list(Wrappers.<OrderRecord>lambdaQuery()
            .eq(OrderRecord::getGoodsId, id));
        this.orderRecordService.remove(Wrappers.<OrderRecord>lambdaQuery()
            .eq(OrderRecord::getGoodsId, id));
        if (!CollectionUtils.isEmpty(orderRecordList)) {
            this.userCommentService.remove(Wrappers.<UserComment>lambdaQuery()
                .in(UserComment::getOrderId, orderRecordList.stream().map(OrderRecord::getId).collect(Collectors.toList())));
        }
        return Result.createSuccess();
    }

}
