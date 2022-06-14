package com.whatsappgroup.whatsappgroupmessages.utils;

public enum ResponseCodes {


    GROUP_SAVE_OK(100, "Group save ok"),
    GROUP_NOT_EXIST(101, "The group does not exist"),
    GROUP_SAVE_FAIL(102, "Failed to create a group"),
    CONTACT_ADD_GROUP_OK(103, "Add contact ok"),


    CONTACT_SAVE_OK(200, "Contact save ok"),
    CONTACT_NOT_EXIST(201, "The contact does not exist"),
    CONTACT_SAVE_FAIL(202, "Failed to create a contact"),


    MESSAGE_SEND_OK(300, "Message send ok"),
    MESSAGE_NOT_EXIST(301, "The message does not exist"),
    MESSAGE_SAVE_FAIL(302, "Failed to create a message"),
    MESSAGES_BY_CONTACT_AND_GROUP_NOT_EXIST(303, "There are no messages for this group and this contact"),
    MESSAGES_BY_GROUP_NOT_EXIST(304, "There are no messages for this group"),
    MESSAGE_DELETE_BY_GROUP_ID_FAIL(305, "Failed to delete messages for this group"),
    MESSAGE_DELETE_BY_ID_FAIL(306, "Failed to delete message"),
    MESSAGE_DELETE_OK(307, "Message delete ok"),
    MESSAGE_MARK_READ_OK(308, "Message mark read ok"),


    NOTIFICATION_NOT_EXIST(400, "The notification does not exist"),
    NOTIFICATION_SAVE_FAIL(401, "Failed to create a notification"),
    NOTIFICATION_BY_GROUP_NOT_EXIST(402, "There are no notifications for this group"),
    NOTIFICATION_DELETE_BY_GROUP_ID_FAIL(403, "Failed to delete notifications for this group"),
    NOTIFICATION_DELETE_BY_ID_FAIL(404, "Failed to delete notification"),
    NOTIFICATION_DELETE_OK(405, "Notification delete ok"),
    NOTIFICATION_MARK_READ_OK(406, "Notification mark read ok"),

    ;

    private final int value;
    private final String reason;

    private ResponseCodes(int value, String reason) {
        this.value = value;
        this.reason = reason;
    }

    public int getValue() {
        return value;
    }

    public String getReason() {
        return reason;
    }

    @Override
    public String toString() {
        return reason;
    }
}
