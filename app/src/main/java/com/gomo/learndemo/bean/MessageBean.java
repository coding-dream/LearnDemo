package com.gomo.learndemo.bean;

public class MessageBean {

    public MessageBean(String body) {
        this.body = body;
    }

    String title;
    String body;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "Message{" +
                "title='" + title + '\'' +
                ", body='" + body + '\'' +
                '}';
    }
}
