package com.gamma.dexter.test;

import java.util.List;
import java.util.Map;

/**
 * Created by Anirban on 17/01/2017.
 */
public class TestHandler {

    TestServiceHandler testServiceHandler = TestServiceHandler.instanceTestServiceHandler();
    private static TestHandler instance = null;

    public static TestHandler instanceTestHandler() {
        if (instance == null) {
            instance = new TestHandler();
        }
        return instance;
    }

    public List<TestUserModel> getData() {
        return testServiceHandler.getData();
    }

    public Map<String, String> findTopUsages() {
        return testServiceHandler.findTopUsages();
    }

    public boolean insertUser(TestUserModel testUserModel){
           return testServiceHandler.insertUser(testUserModel);
    }
}
