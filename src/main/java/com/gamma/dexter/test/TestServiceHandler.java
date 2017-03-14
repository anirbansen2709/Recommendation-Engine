package com.gamma.dexter.test;

import java.util.List;
import java.util.Map;

/**
 * Created by Anirban on 17/01/2017.
 */
public class TestServiceHandler {

    TestDbModel testDbModel = TestDbModel.intance();
    private static TestServiceHandler instance = null;

    public static TestServiceHandler instanceTestServiceHandler() {
        if (instance == null) {
            instance = new TestServiceHandler();
        }
        return instance;
    }

    public List<TestUserModel> getData() {
        return testDbModel.getData();
    }
    public Map<String, String> findTopUsages() {
        return testDbModel.findTopUsages("memory",5);
    }

    public boolean insertUser(TestUserModel testUserModel) {
        if (!isUserNameDuplicate(testUserModel)) {
            testDbModel.insertUser(testUserModel);
            return true;
        }
        return false;
    }

    private boolean isUserNameDuplicate(TestUserModel testUserModel) {
        return testDbModel.isUserNameDuplicate(testUserModel);
    }
}
