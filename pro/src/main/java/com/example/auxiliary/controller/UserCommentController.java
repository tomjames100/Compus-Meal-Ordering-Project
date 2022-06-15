package com.example.auxiliary.controller;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.auxiliary.cumenum.OrderStatusEnum;
import com.example.auxiliary.entity.Goods;
import com.example.auxiliary.entity.OrderRecord;
import com.example.auxiliary.entity.Result;
import com.example.auxiliary.entity.UserComment;
import com.example.auxiliary.service.IGoodsService;
import com.example.auxiliary.service.IOrderRecordService;
import com.example.auxiliary.service.IUserCommentService;
import com.example.auxiliary.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 商品评论接口
 *
 * @author ztx
 * @date 2021-05-23 23:13
 */
@RequestMapping("/userComment")
@RestController
@RequiredArgsConstructor
public class UserCommentController {

    private final IUserCommentService userCommentService;
    private final IOrderRecordService orderRecordService;
    private final IUserService userService;
    private final IGoodsService goodsService;

    /**
     * 提交商品评论
     *
     * @param userComment 评论数据
     * @return 结果
     */
    @PostMapping("/comment")
    public Result<UserComment> comitComment(@RequestBody UserComment userComment) {
        userComment.setCreateDatetime(LocalDateTime.now());
        this.userCommentService.saveOrUpdate(userComment);
        // 评论完之后修改订单表的状态，改为已经评论
        this.orderRecordService.update(Wrappers.<OrderRecord>lambdaUpdate()
            .set(OrderRecord::getStatus, OrderStatusEnum.COMPELTE.getValue())
            .eq(OrderRecord::getOrderId, userComment.getOrderId()));
        return Result.createSuccess(userComment);
    }

    /**
     * 获取推荐列表
     *
     * @return 推荐列表
     */
    @GetMapping("/allComment")
    public Result<?> getAllComment() {
        List<UserComment> userCommentList = this.userCommentService.list(Wrappers.<UserComment>lambdaQuery()
            .orderByDesc(UserComment::getCreateDatetime)
            .last("limit 100")
        );
        userCommentList.forEach(comment -> {
            comment.setUser(this.userService.getById(comment.getUserId()));
            List<OrderRecord> goodsList = this.orderRecordService.list(Wrappers.<OrderRecord>lambdaQuery()
                .select(OrderRecord::getOrderId, OrderRecord::getGoodsId)
                .eq(OrderRecord::getOrderId, comment.getOrderId()));
            List<Goods> goods = this.goodsService.list(Wrappers.<Goods>lambdaQuery().in(Goods::getId, goodsList.stream()
                .map(OrderRecord::getGoodsId)
                .collect(Collectors.toList())));
            if (CollectionUtils.isNotEmpty(goods)) {
                comment.setGoodsImageUrls(goods.stream().map(Goods::getImageUrl).collect(Collectors.toList()));
            }
        });
        return Result.createSuccess(userCommentList);
    }

}
