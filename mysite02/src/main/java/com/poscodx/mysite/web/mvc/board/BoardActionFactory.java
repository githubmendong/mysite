package com.poscodx.mysite.web.mvc.board;


import com.poscodx.web.mvc.Action;
import com.poscodx.web.mvc.ActionFactory;

public class BoardActionFactory implements ActionFactory {

    @Override
    public Action getAction(String actionName) {
        Action action = null;

        if ("write2".equals(actionName)) {
            System.out.println("wrtie2");
            action = new BoardWriteAction();

        } else if ("write".equals(actionName)) {
            System.out.println("wrtie");
            action = new BoardWriteFromAction();

        } else if ("view".equals(actionName)) {
            System.out.println("view");
            action = new BoardViewAction();
            
        }else if ("delete".equals(actionName)) {
            System.out.println("view");
            action = new BoardDeleteAction();

        } else if ("reply".equals(actionName)) {
            System.out.println("reply");
            action = new BoardRelpyFromAction();

        } else if ("reply2".equals(actionName)) {
            System.out.println("reply2");
            action = new BoardReplyAction();

        }   else {

            System.out.println("list");
            action = new BoardListAction();
        }

        return action;
    }
}
