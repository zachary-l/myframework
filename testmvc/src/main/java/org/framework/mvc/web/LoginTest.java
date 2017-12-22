package org.framework.mvc.web;

import org.framework.mvc.ActionContext;
import org.framework.mvc.ViewResult;
import org.framework.mvc.ann.RequestMapping;
import org.framework.mvc.view.ForwardView;

import javax.servlet.http.HttpSession;
import javax.swing.*;
import java.util.Map;

public class LoginTest {
    @RequestMapping("/login")
    public ViewResult login(User user){
        if(user!=null){
            if(user.getUserName()=="zachary"&&user.getPassword()=="123456"){
                Map<String,Object> session = ActionContext.getContext().getSessionMap();
                session.put("user",user);
            }
        }
        return new ForwardView("/display");
    }
}
