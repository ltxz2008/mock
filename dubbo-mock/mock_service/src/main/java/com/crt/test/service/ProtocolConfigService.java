package com.crt.test.service;

import java.util.List;

import com.crt.test.mock.po.ProtocolConfig;

public interface ProtocolConfigService {
    List<ProtocolConfig> selectProtocolConfig(ProtocolConfig service);

    int updateOrInsertProtocolConfig(ProtocolConfig ProtocolConfig);

    int deleteProtocolConfig(ProtocolConfig ProtocolConfig);
}
