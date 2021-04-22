package com.saturn.background.tool;

import com.intellij.notification.Notification;
import com.intellij.notification.NotificationType;
import com.intellij.notification.Notifications;

public class NotificationCenter {
    static void notice(String message) {
        Notification n = new Notification(
                "Background Image",
                "Notice",
                message,
                NotificationType.INFORMATION);
        Notifications.Bus.notify(n);
    }
}
