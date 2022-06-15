package com.example.auxiliary.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.auxiliary.entity.Goods;
import com.example.auxiliary.entity.Result;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public interface IGoodsService extends IService<Goods> {
    /**
     * 获取种类列表
     *
     * @return 种类列表
     */
    Result<Map<String, Object>> getCatoryGoodList();
}
