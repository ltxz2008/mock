package com.crt.test.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import com.crt.test.ReflectUtil;
import com.crt.test.mock.auto.mapper.ProtocolConfigMapper;
import com.crt.test.mock.po.ProtocolConfig;
import com.crt.test.mock.po.ProtocolConfigExample;
import com.crt.test.service.ProtocolConfigService;

@Service public class ProtocolConfigServiceImpl implements ProtocolConfigService {

    @Resource ProtocolConfigMapper protocolConfigMapper;

    @Override
    public List<ProtocolConfig> selectProtocolConfig(ProtocolConfig protocolConfig) {
        ProtocolConfigExample example = new ProtocolConfigExample();
        ReflectUtil.invokeSelectParams(example, protocolConfig);
        return protocolConfigMapper.selectByExample(example);
    }

    @Override
    public int updateOrInsertProtocolConfig(ProtocolConfig protocolConfig) {
        if (protocolConfig == null) {
            return 0;
        }
        protocolConfig.setUpdateTime(new Date());
        int count = protocolConfigMapper.updateByPrimaryKeySelective(protocolConfig);
        if (count <= 0) {
            ProtocolConfigExample example = new ProtocolConfigExample();
            ReflectUtil.invokeSelectParams(example, protocolConfig);
            count = protocolConfigMapper.insert(protocolConfig);
            List<ProtocolConfig> services = selectProtocolConfig(protocolConfig);
            int id = 0;
            if (CollectionUtils.isNotEmpty(services)) {
                for (ProtocolConfig service : services) {
                    if (service.getId() > id) {
                        id = service.getId();
                    }
                }
            }
            protocolConfig.setId(id);
        }
        return count;
    }

    @Override
    public int deleteProtocolConfig(ProtocolConfig protocolConfig) {
        ProtocolConfigExample example = new ProtocolConfigExample();
        ReflectUtil.invokeSelectParams(example, protocolConfig);
        return protocolConfigMapper.deleteByExample(example);
    }

}
