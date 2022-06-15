package com.example.auxiliary.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.auxiliary.entity.UserComment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserCommentMapper extends BaseMapper<UserComment> {

    List<UserComment> getUserCommentByGoodsId(@Param("goodsId") String goodsId);
}
