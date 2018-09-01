package com.watar.soft.watar.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by user on 01-08-2018.
 */

public class SignUp {


    @Expose
    @SerializedName("success")
    private boolean success;
    @Expose
    @SerializedName("data")
    private List<String> data;
    @Expose
    @SerializedName("message")
    private String message;
    @Expose
    @SerializedName("messages")
    private MessagesEntity messages;
    @Expose
    @SerializedName("status")
    private int status;

    public boolean getSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public MessagesEntity getMessages() {
        return messages;
    }

    public void setMessages(MessagesEntity messages) {
        this.messages = messages;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public static class MessagesEntity {
        @Expose
        @SerializedName("mobile")
        private List<String> mobile;
        @Expose
        @SerializedName("password")
        private List<String> password;
        @Expose
        @SerializedName("email")
        private List<String> email;
        @Expose
        @SerializedName("username")
        private List<String> username;
        @Expose
        @SerializedName("last_name")
        private List<String> last_name;
        @Expose
        @SerializedName("first_name")
        private List<String> first_name;
        @Expose
        @SerializedName("avatar")
        private List<String> avatar;

        public List<String> getMobile() {
            return mobile;
        }

        public void setMobile(List<String> mobile) {
            this.mobile = mobile;
        }

        public List<String> getPassword() {
            return password;
        }

        public void setPassword(List<String> password) {
            this.password = password;
        }

        public List<String> getEmail() {
            return email;
        }

        public void setEmail(List<String> email) {
            this.email = email;
        }

        public List<String> getUsername() {
            return username;
        }

        public void setUsername(List<String> username) {
            this.username = username;
        }

        public List<String> getLast_name() {
            return last_name;
        }

        public void setLast_name(List<String> last_name) {
            this.last_name = last_name;
        }

        public List<String> getFirst_name() {
            return first_name;
        }

        public void setFirst_name(List<String> first_name) {
            this.first_name = first_name;
        }

        public List<String> getAvatar() {
            return avatar;
        }

        public void setAvatar(List<String> avatar) {
            this.avatar = avatar;
        }
    }
}
