package com.poscodx.mysite.web.mvc.user;

import com.poscodx.web.mvc.Action;

public class UserActionFactory {


    @Override
    public Action getAction(String actionName){

        Action action = null;

        if("joinform".equals(actionName)) {
            action = new JoinFormAction();
        } else if("join".equals(actionName)) {
            action = new JoinAction();
        } else if("joinsuccess".equals(actionName)) {
            action = new JoinSuccessAction();
        }else if("joinsuccess".equals(actionName)) {
            action = new JoinSuccessAction();
        }

    }
}
