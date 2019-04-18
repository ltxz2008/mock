package com.crt.test.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import com.crt.test.ReflectUtil;
import com.crt.test.mock.auto.mapper.ServiceMethedRuleMapper;
import com.crt.test.mock.po.ServiceMethedRule;
import com.crt.test.mock.po.ServiceMethedRuleExample;
import com.crt.test.service.ServiceMethedRuleService;

@Service public class ServiceMethedRuleServiceImpl implements ServiceMethedRuleService {

    @Resource ServiceMethedRuleMapper serviceMethedRuleMapper;

    @Override
    public List<ServiceMethedRule> selectServiceMethedRule(ServiceMethedRule serviceMethedRule) {
        ServiceMethedRuleExample example = new ServiceMethedRuleExample();
        ReflectUtil.invokeSelectParams(example, serviceMethedRule);
        example.setOrderByClause(" method_name,exec_sort");
        return serviceMethedRuleMapper.selectByExample(example);
    }

    @Override
    public int updateOrInsertServiceMethedRule(ServiceMethedRule serviceMethedRule) {
        if (serviceMethedRule == null) {
            return 0;
        }
        serviceMethedRule.setUpdateTime(new Date());
        int count = serviceMethedRuleMapper.updateByPrimaryKey(serviceMethedRule);
        if (count <= 0) {
            ServiceMethedRuleExample example = new ServiceMethedRuleExample();
            ReflectUtil.invokeSelectParams(example, serviceMethedRule);
            count = serviceMethedRuleMapper.insert(serviceMethedRule);
            List<ServiceMethedRule> services = selectServiceMethedRule(serviceMethedRule);
            int id = 0;
            if (CollectionUtils.isNotEmpty(services)) {
                for (ServiceMethedRule service : services) {
                    if (service.getId() > id) {
                        id = service.getId();
                    }
                }
            }
            serviceMethedRule.setId(id);
        }
        return count;
    }

    @Override
    public int deleteServiceMethedRule(ServiceMethedRule serviceMethedRule) {
        ServiceMethedRuleExample example = new ServiceMethedRuleExample();
        ReflectUtil.invokeSelectParams(example, serviceMethedRule);
        return serviceMethedRuleMapper.deleteByExample(example);
    }

}
