package com.crt.test.mock.auto.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.crt.test.mock.po.MockTestParam;
import com.crt.test.mock.po.MockTestParamExample;

public interface MockTestParamMapper {
    long countByExample(MockTestParamExample example);

    int deleteByExample(MockTestParamExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MockTestParam record);

    int insertSelective(MockTestParam record);

    List<MockTestParam> selectByExample(MockTestParamExample example);

    MockTestParam selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MockTestParam record, @Param("example") MockTestParamExample example);

    int updateByExample(@Param("record") MockTestParam record, @Param("example") MockTestParamExample example);

    int updateByPrimaryKeySelective(MockTestParam record);

    int updateByPrimaryKey(MockTestParam record);
}