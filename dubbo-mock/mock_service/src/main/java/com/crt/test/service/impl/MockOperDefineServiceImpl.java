package com.crt.test.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import com.crt.test.mock.po.MockOperDefine;
import com.crt.test.mock.po.MockServiceDefine;
import com.crt.test.mock.po.ProtocolConfig;
import com.crt.test.mock.po.RegistryConfig;
import com.crt.test.mock.po.ServiceMethedRule;
import com.crt.test.service.MockOperDefineService;
import com.crt.test.service.MockServiceService;
import com.crt.test.service.ProtocolConfigService;
import com.crt.test.service.RegistryConfigService;
import com.crt.test.service.ServiceMethedRuleService;
import com.google.common.collect.Lists;

@Service
public class MockOperDefineServiceImpl implements MockOperDefineService {

    @Resource
    MockServiceService mockServiceServiceImpl;

    @Resource
    ProtocolConfigService protocolConfigServiceImpl;

    @Resource
    RegistryConfigService registryConfigServiceImpl;

    @Resource
    ServiceMethedRuleService serviceMethedRuleServiceImpl;

    @Override
    public MockOperDefine selectMockOperDefine(MockServiceDefine service) {
	MockOperDefine define = new MockOperDefine();
	if (service.getId() == null) {
	    service.setId(-1);
	}
	List<MockServiceDefine> mockServices = mockServiceServiceImpl.selectMockServiceDefine(service);
	List<ProtocolConfig> protocolConfigs = protocolConfigServiceImpl.selectProtocolConfig(null);
	List<RegistryConfig> registryConfigs = registryConfigServiceImpl.selectRegistryConfig(null);
	ServiceMethedRule rule = new ServiceMethedRule();
	rule.setServiceId(service.getId());
	List<ServiceMethedRule> serviceMethedRules = null;
	try {
	    serviceMethedRules = serviceMethedRuleServiceImpl.selectServiceMethedRule(rule);
	} catch (Exception e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	if (CollectionUtils.isEmpty(mockServices)) {
	    mockServices = Lists.newArrayList();
	    mockServices.add(new MockServiceDefine());
	}
	define.setMockServices(mockServices);
	define.setProtocolConfigs(protocolConfigs);
	define.setRegistryConfigs(registryConfigs);
	if (CollectionUtils.isNotEmpty(serviceMethedRules)) {
	    define.setServiceMethedRules(serviceMethedRules);
	}
	return define;
    }

}
