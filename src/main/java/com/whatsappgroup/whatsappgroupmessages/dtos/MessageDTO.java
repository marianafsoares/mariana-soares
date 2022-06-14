package com.whatsappgroup.whatsappgroupmessages.dtos;

import com.whatsappgroup.whatsappgroupmessages.models.Contact;
import com.whatsappgroup.whatsappgroupmessages.models.Group;
import java.util.Date;

public class MessageDTO {

    private Long id;

    private Contact contact;

    private String message;

    private int isRead;

    private Date createdAt;

    private Group group;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getIsRead() {
        return isRead;
    }

    public void setIsRead(int isRead) {
        this.isRead = isRead;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }
}
