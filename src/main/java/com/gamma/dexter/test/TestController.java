package com.gamma.dexter.test;

import com.gamma.dexter.console.draft.ResponseWrapper;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Created by Anirban on 17/01/2017.
 */
@Controller
public class TestController {
    private static TestHandler testHandler = TestHandler.instanceTestHandler();

    @RequestMapping(value = "listAllUsers", method = RequestMethod.GET)
    public
    @ResponseBody
    String listAllUser() {
        List<TestUserModel> data = testHandler.getData();
        ResponseWrapper wrapper = new ResponseWrapper();
        for (TestUserModel testUserModel : data) {
            wrapper.addPayload(testUserModel);
        }
        return wrapper.getResponse();
    }


    @RequestMapping(value = "topUsageCharts", method = RequestMethod.GET)
    public
    @ResponseBody
    String topUsageCharts() {
        JSONArray array = new JSONArray();
        Map<String, String> topUsages = testHandler.findTopUsages();
        for (String name : topUsages.keySet()) {
            int value = Integer.parseInt(topUsages.get(name));
            JSONObject json = new JSONObject();
            json.put("label", name);
            json.put("value", value);
            array.add(json);
        }
        JSONObject mainObj = new JSONObject();
        mainObj.put("data", array);

        return mainObj.toString();

    }

    @RequestMapping(value = "insertUser", method = RequestMethod.POST)
    public
    @ResponseBody
    String insertUser(@RequestBody TestUserModel testUserModel) {
        ResponseWrapper wrapper = new ResponseWrapper();
        try {
            if (!testHandler.insertUser(testUserModel)) {
                wrapper.setSuccess(false);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return wrapper.getResponse();
    }


    public static void main(String[] args) {
TestController testController=new TestController();
        testController.topUsageCharts();
    }
}