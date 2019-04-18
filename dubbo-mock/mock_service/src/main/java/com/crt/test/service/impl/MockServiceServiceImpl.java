package com.crt.test.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import com.crt.test.ReflectUtil;
import com.crt.test.mock.auto.mapper.MockServiceMapper;
import com.crt.test.mock.define.mapper.MockServiceDefineMapper;
import com.crt.test.mock.po.MockService;
import com.crt.test.mock.po.MockServiceDefine;
import com.crt.test.mock.po.MockServiceExample;
import com.crt.test.protocol.MockServer;
import com.crt.test.service.MockServiceService;

@Service public class MockServiceServiceImpl implements MockServiceService {

    @Resource MockServiceMapper                    mockServiceMapper;

    @Resource MockServiceDefineMapper              mockServiceDefineMapper;

    @Resource(name = "dubboMockServer") MockServer mockServer;

    @Override
    public List<MockService> selectMockService(MockService mockService) {
        MockServiceExample example = new MockServiceExample();
        ReflectUtil.invokeSelectParams(example, mockService);
        return mockServiceMapper.selectByExample(example);
    }

    @Override
    public int updateOrInsertMockService(MockService mockService) {
        if (mockService == null) {
            return 0;
        }
        int count = 0;
        mockService.setUpdateTime(new Date());
        if (mockService.getId()!= null) {
            if ("running".equals(mockService.getServiceStatus())) {
                mockServer.startService(mockService.getId());
            } else if ("stop".equals(mockService.getServiceStatus())) {
                mockServer.stopService(mockService.getId());
            }
            count = mockServiceMapper.updateByPrimaryKeySelective(mockService);
        } else {
            count = mockServiceMapper.insert(mockService);
            List<MockService> services = selectMockService(mockService);
            if (CollectionUtils.isNotEmpty(services)) {
                for (MockService service : services) {
                    mockService.setId(service.getId());
                }
            }
        }
        return count;
    }

    @Override
    public int deleteMockService(MockService mockService) {
        return mockServiceMapper.deleteByPrimaryKey(mockService.getId());
    }

    @Override
    public List<MockServiceDefine> selectMockServiceDefine(MockServiceDefine serviceDefine) {
        return mockServiceDefineMapper.selectMockServiceDefine(serviceDefine);
    }

    @Override
    public List<String> selectMockRoleNames(Integer id) {
        return mockServiceDefineMapper.selectMockRoleNames(id);
    }

}
