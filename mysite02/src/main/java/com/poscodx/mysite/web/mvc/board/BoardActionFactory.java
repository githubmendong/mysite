package com.poscodx.mysite.web.mvc.board;


import com.poscodx.mysite.web.mvc.guestbook.DeleteAction;
import com.poscodx.mysite.web.mvc.guestbook.ListAction;
import com.poscodx.web.mvc.Action;
import com.poscodx.web.mvc.ActionFactory;

public class BoardActionFactory implements ActionFactory {

    @Override
    public Action getAction(String actionName) {
        Action action = null;

        if ("view".equals(actionName)) {
            action = new BoardViewAction();
        } else if ("writeform".equals(actionName)) {
            action = new BoardWriteFromAction();
        } else if ("write".equals(actionName)) {
            action = new BoardWriteAction();
        } else if ("delete".equals(actionName)) {
            action = new BoardDeleteAction();
        } else if ("replyform".equals(actionName)) {
            action = new BoardRelpyFromAction();
        } else if ("reply".equals(actionName)) {
            action = new BoardReplyAction();
        } else if ("page".equals(actionName)) {
            action = new PagingAction();
        } else if ("modifyform".equals(actionName)) {
            action = new ModifyFormAction();
        } else if ("modify".equals(actionName)) {
            action = new ModifyAction();
        } else {
            action = new BoardListAction();
        }
        return action;
    }
}
