package com.github.lumoping.gitcheck.notifier;

import com.intellij.notification.Notification;
import com.intellij.notification.NotificationGroupManager;
import com.intellij.notification.NotificationType;
import com.intellij.notification.Notifications;

public class GitCheckNotifier {
    public static void notifyError(String content) {
        Notification notification = NotificationGroupManager.getInstance().getNotificationGroup("git-check")
                .createNotification(content, NotificationType.ERROR);
        Notifications.Bus.notify(notification);

    }
}
