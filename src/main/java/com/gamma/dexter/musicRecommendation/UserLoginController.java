package com.gamma.dexter.musicRecommendation;

import com.gamma.dexter.console.draft.ResponseWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by Debashish Sen on 18-Apr-17.
 */

@Controller
public class UserLoginController {
    private static UserLoginHandler userLoginHandler = UserLoginHandler.instanceUserLoginHandler();
    private static UserContext userContext = UserContext.getInstance();
    @RequestMapping(value = "userLogin", method = RequestMethod.POST)
    public
    @ResponseBody
    String userLogin(@RequestBody UserLoginModel userLoginModel, HttpServletRequest request) {
        ResponseWrapper wrapper = new ResponseWrapper();
        try {
            if (userLoginHandler.userLogin(userLoginModel)) {
                wrapper.setSuccess(true);
                int userId=userLoginHandler.getUserId(userLoginModel);
                HttpSession session=request.getSession();
                userContext.setUserId(userId);
            }
            else{
                wrapper.setSuccess(false);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return wrapper.getResponse();
    }

    @RequestMapping(value = "userLogout",method = RequestMethod.GET)
    public String userLogout(HttpServletRequest request, HttpServletResponse response) {

        HttpSession session=request.getSession();
      //  int uid=(int)(session.getAttribute("userId"));
        //System.out.println("Session Invalidated for UserId: "+uid);
        session.invalidate();


//        response.setContentType("text/html");
//        String site = new String("/demo");
//        response.setStatus(response.SC_MOVED_TEMPORARILY);
//        response.setHeader("Location", site);

      //  Subject currentSubject = SecurityUtils.getSubject();
       // currentSubject.logout();
        return "demo";
    }


    @RequestMapping(value = "userSignup", method = RequestMethod.POST)
    public
    @ResponseBody
    String userLogin(@RequestBody UserSignupModel userSignupModel) {
        ResponseWrapper wrapper = new ResponseWrapper();
        try {
            if (userLoginHandler.insertUser(userSignupModel)) {
                wrapper.setSuccess(true);
            }
            else{
                wrapper.setSuccess(false);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return wrapper.getResponse();
    }

}
