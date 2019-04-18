package com.crt.test.protocol;

import java.util.List;
import java.util.Map;

/**
 * 
 * @author duanzongxiang1
 *
 */
public interface TestAbcService {

    List<Par> getList(Par par);

    public TestReturnObject getObject();

    public TestReturnObjectList getObjectList();

    void testVoid();

    int testReturnInt();

    Integer testReturnInteger(int i);

    String testReturnString(String s);

    Map<String, Par> testReturnMap();

    TestReturnObjectList getReturnObjectList();

}
