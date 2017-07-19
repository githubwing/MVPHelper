package com.wingsofts.mvphelper.biz;

import com.intellij.notification.Notification;
import com.intellij.notification.NotificationType;
import com.intellij.notification.Notifications;
import com.intellij.openapi.ui.popup.Balloon;

/**
 * @author Administrator
 * @since 2017/4/27
 */
public class EventLogger {
    private static final String GROUP_ID = "Mvp Helper";//The unique group id where "Event Log" could use to group your messages together.
    private static final String TITLE = "Mvp Helper Event Log";//The title on Balloon

    /**
     * Print log to "Event Log"
     */
    public static void log(String msg) {
        Notification notification = new Notification(GROUP_ID, TITLE, msg, NotificationType.INFORMATION);//build a notification
        //notification.hideBalloon();//didn't work
        Notifications.Bus.notify(notification);//use the default bus to notify (application level)
        Balloon balloon = notification.getBalloon();
        if (balloon != null) {//fix: #20 潜在的NPE
            balloon.hide(true);//try to hide the balloon immediately.
        }
    }
}
