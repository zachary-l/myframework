package org.framework.mvc.web;

import org.framework.mvc.ActionContext;
import org.framework.mvc.ViewResult;
import org.framework.mvc.ann.RequestMapping;

public class Dispaly {
    @RequestMapping("/display")
    public String display(){
        User user = (User) ActionContext.getContext().getSession().get("user");
        String userName = "密码错误";
        if(user!=null){
            userName=user.getUserName();
        }
        return userName;
    }
}
