package com.watar.soft.watar.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by RDave on 27/07/2018.
 */

public class Login {

    @Expose
    @SerializedName("status")
    private int status;
    @Expose
    @SerializedName("data")
    private List<DataEntity> data;
    @Expose
    @SerializedName("message")
    private String message;
    @Expose
    @SerializedName("success")
    private boolean success;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<DataEntity> getData() {
        return data;
    }

    public void setData(List<DataEntity> data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean getSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public static class DataEntity {
        @Expose
        @SerializedName("user")
        private UserEntity user;
        @Expose
        @SerializedName("api_token")
        private String api_token;

        public UserEntity getUser() {
            return user;
        }

        public void setUser(UserEntity user) {
            this.user = user;
        }

        public String getApi_token() {
            return api_token;
        }

        public void setApi_token(String api_token) {
            this.api_token = api_token;
        }
    }

    public static class UserEntity {
        @Expose
        @SerializedName("followed_users")
        private List<String> followed_users;
        @Expose
        @SerializedName("has_password")
        private boolean has_password;
        @Expose
        @SerializedName("display_name")
        private String display_name;
        @Expose
        @SerializedName("followers_count")
        private int followers_count;
        @Expose
        @SerializedName("mobile")
        private String mobile;
        @Expose
        @SerializedName("avatar")
        private String avatar;
        @Expose
        @SerializedName("language")
        private String language;
        @Expose
        @SerializedName("confirmed")
        private int confirmed;
        @Expose
        @SerializedName("updated_at")
        private String updated_at;
        @Expose
        @SerializedName("created_at")
        private String created_at;
        @Expose
        @SerializedName("email")
        private String email;
        @Expose
        @SerializedName("last_name")
        private String last_name;
        @Expose
        @SerializedName("first_name")
        private String first_name;
        @Expose
        @SerializedName("username")
        private String username;
        @Expose
        @SerializedName("id")
        private int id;

        public List<String> getFollowed_users() {
            return followed_users;
        }

        public void setFollowed_users(List<String> followed_users) {
            this.followed_users = followed_users;
        }

        public boolean getHas_password() {
            return has_password;
        }

        public void setHas_password(boolean has_password) {
            this.has_password = has_password;
        }

        public String getDisplay_name() {
            return display_name;
        }

        public void setDisplay_name(String display_name) {
            this.display_name = display_name;
        }

        public int getFollowers_count() {
            return followers_count;
        }

        public void setFollowers_count(int followers_count) {
            this.followers_count = followers_count;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getLanguage() {
            return language;
        }

        public void setLanguage(String language) {
            this.language = language;
        }

        public int getConfirmed() {
            return confirmed;
        }

        public void setConfirmed(int confirmed) {
            this.confirmed = confirmed;
        }

        public String getUpdated_at() {
            return updated_at;
        }

        public void setUpdated_at(String updated_at) {
            this.updated_at = updated_at;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getLast_name() {
            return last_name;
        }

        public void setLast_name(String last_name) {
            this.last_name = last_name;
        }

        public String getFirst_name() {
            return first_name;
        }

        public void setFirst_name(String first_name) {
            this.first_name = first_name;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}
