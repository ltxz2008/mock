package com.crt.test.service;

import java.util.List;

import com.crt.test.mock.po.RegistryConfig;

public interface RegistryConfigService {
    List<RegistryConfig> selectRegistryConfig(RegistryConfig service);

    int updateOrInsertRegistryConfig(RegistryConfig RegistryConfig);

    int deleteRegistryConfig(RegistryConfig RegistryConfig);
}
