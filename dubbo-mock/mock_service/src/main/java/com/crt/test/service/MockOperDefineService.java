package com.crt.test.service;

import com.crt.test.mock.po.MockOperDefine;
import com.crt.test.mock.po.MockServiceDefine;

public interface MockOperDefineService {

    MockOperDefine selectMockOperDefine(MockServiceDefine service);
}
