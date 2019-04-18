package com.crt.test.protocol;

import org.apache.commons.lang.StringUtils;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.ServiceConfig;
import com.alibaba.dubbo.rpc.service.GenericService;
import com.crt.test.mock.po.MockService;
import com.google.common.collect.Lists;

/**
 * 
 * @author duanzongxiang1
 *
 */
public class DubboServiceConfig {

    public ServiceConfig<GenericService> fillDubboService(MockService mockService,
	    com.crt.test.mock.po.RegistryConfig registryConfig, com.crt.test.mock.po.ProtocolConfig protocolConfig,
	    MockGenericService tmpMockservice) {
	ServiceConfig<GenericService> service = new ServiceConfig<GenericService>();
	int i = mockService.getServiceInterface().lastIndexOf(".");
	if (i == -1) {
	    service.setId(mockService.getServiceInterface());
	} else {
	    service.setId(mockService.getServiceInterface().substring(i + 1));
	}

	service.setInterface(mockService.getServiceInterface());
	service.setRef(tmpMockservice); // 指向一个通用服务实现
	RegistryConfig registry = createRegistry(registryConfig.getRegistryAddress(),
		registryConfig.getRegistryTimeout());
	service.setRegistry(registry);
	service.setProtocols(Lists
		.newArrayList(new ProtocolConfig(protocolConfig.getProtocolName(), protocolConfig.getProtocolPort())));
	if (!StringUtils.isBlank(mockService.getGroupName())) {
	    service.setGroup(mockService.getGroupName());
	}
	service.setVersion(mockService.getVersion());
	service.setTimeout(mockService.getTimeout());
	service.setRetries(mockService.getRetries());
	service.setApplication(new ApplicationConfig(mockService.getApplicationName()));
	return service;
    }

    public static RegistryConfig createRegistry(String address, int timeout) {
	RegistryConfig registry = new RegistryConfig();
	registry.setProtocol("zookeeper");
	registry.setAddress(address);
	registry.setTimeout(timeout);
	return registry;
    }
}
