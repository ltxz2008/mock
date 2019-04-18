package com.crt.test.mock.auto.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.crt.test.mock.po.ServiceMethedRule;
import com.crt.test.mock.po.ServiceMethedRuleExample;

public interface ServiceMethedRuleMapper {
    long countByExample(ServiceMethedRuleExample example);

    int deleteByExample(ServiceMethedRuleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ServiceMethedRule record);

    int insertSelective(ServiceMethedRule record);

    List<ServiceMethedRule> selectByExample(ServiceMethedRuleExample example);

    ServiceMethedRule selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ServiceMethedRule record, @Param("example") ServiceMethedRuleExample example);

    int updateByExample(@Param("record") ServiceMethedRule record, @Param("example") ServiceMethedRuleExample example);

    int updateByPrimaryKeySelective(ServiceMethedRule record);

    int updateByPrimaryKey(ServiceMethedRule record);
}