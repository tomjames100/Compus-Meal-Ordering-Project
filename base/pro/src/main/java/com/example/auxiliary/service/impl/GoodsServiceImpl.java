package com.example.auxiliary.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.auxiliary.cumenum.GoodsStatusEnum;
import com.example.auxiliary.entity.Goods;
import com.example.auxiliary.entity.Result;
import com.example.auxiliary.mapper.GoodsMapper;
import com.example.auxiliary.service.IGoodsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author ztx
 * @date 2021-02-21 13:34
 */
@Service
@RequiredArgsConstructor
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements IGoodsService {


    @Override
    public Result<Map<String, Object>> getCatoryGoodList() {
        // 聚合对象
        Map<String, List<Goods>> collect = this.list(Wrappers.<Goods>lambdaQuery()
                .eq(Goods::getStatus, GoodsStatusEnum.ONLINE.getValue()))
            .stream().collect(Collectors.groupingBy(Goods::getCategory));
        List<String> menus = new ArrayList<>();
        List<Map<String, String>> goods = new ArrayList<>();
        collect.forEach((menu, value) -> {
            value.forEach(good -> {
                Map<String, String> map = new HashMap<>();
                map.put("id", good.getId());
                map.put("menu", menu);
                map.put("name", good.getName());
                map.put("imageUrl", good.getImageUrl());
                map.put("price", good.getPrice().toPlainString());
                goods.add(map);
            });
            menus.add(menu);
        });
        Map<String, Object> retMap = new HashMap<>();
        retMap.put("menus", menus);
        retMap.put("goods", goods);
        return Result.createSuccess(retMap);
    }

}
