package com.crt.test.service;

import java.util.List;

import com.crt.test.mock.po.MockService;
import com.crt.test.mock.po.MockServiceDefine;

public interface MockServiceService {

    List<MockServiceDefine> selectMockServiceDefine(MockServiceDefine serviceDefine);

    List<MockService> selectMockService(MockService service);

    int updateOrInsertMockService(MockService mockService);

    int deleteMockService(MockService mockService);

    List<String> selectMockRoleNames(Integer id);
}
