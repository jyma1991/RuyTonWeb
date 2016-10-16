package com.ryt.web.common;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.durcframework.core.UserContext;
import com.ryt.web.entity.user.User;

public class RMSSessionListener implements HttpSessionListener {

    public void sessionCreated(HttpSessionEvent event) {}

    public void sessionDestroyed(HttpSessionEvent event) {
        User user = UserContext.getInstance().getUser(event.getSession());
        if (user != null) {
            RMSContext.getInstance().clearUserRightData(user.getUsername());
        }
    }

}
