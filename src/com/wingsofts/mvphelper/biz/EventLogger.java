package com.wingsofts.mvphelper.biz;

import com.intellij.notification.Notification;
import com.intellij.notification.NotificationType;
import com.intellij.notification.Notifications;

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
        Notifications.Bus.notify(notification);//use the default bus to notify (application level)
        //noinspection ConstantConditions: since the notification has been notified, the balloon won't be null.
        notification.getBalloon().hide(true);//try to hide the balloon immediately.
    }
}
