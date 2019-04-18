package com.crt.test.service;

import java.util.List;

import com.crt.test.mock.po.ServiceMethedRule;

public interface ServiceMethedRuleService {
    List<ServiceMethedRule> selectServiceMethedRule(ServiceMethedRule service);

    int updateOrInsertServiceMethedRule(ServiceMethedRule ServiceMethedRule);

    int deleteServiceMethedRule(ServiceMethedRule ServiceMethedRule);
}
