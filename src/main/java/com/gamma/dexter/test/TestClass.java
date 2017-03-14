package com.gamma.dexter.test;

import java.util.List;

/**
 * Created by Anirban on 23/01/2017.
 */
public class TestClass {


    TestServiceHandler testServiceHandler = TestServiceHandler.instanceTestServiceHandler();

    public static void main(String[] args) {

        TestClass testClass= new TestClass();
        testClass.test();
    }

    List<TestUserModel> list = testServiceHandler.getData();

    //check the id. If it is not existing already then return true else false


    //check the id. If it is not existing already then return true else false
    String userName="anirba";

    public void test() {


        if(userName==null)

        {

            System.out.println(("Enter a Username").toString());
        }

        else

        {
            if (list.stream().filter(o -> o.getUsername().equals(userName)).findFirst().isPresent() == true) {
                System.out.println(("The Username is already used").toString());
            } else {
                System.out.println(("You have a valid Username").toString());
            }
        }
    }
}
