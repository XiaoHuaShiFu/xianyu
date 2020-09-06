package com.wudagezhandui.shixun.xianyu.dao;

import com.wudagezhandui.shixun.xianyu.pojo.do0.PaymentDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PaymentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PaymentDO record);

    int insertSelective(PaymentDO record);

    PaymentDO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PaymentDO record);

    int updateByPrimaryKey(PaymentDO record);

}
