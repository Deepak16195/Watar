package com.watar.soft.watar.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by user on 08-08-2018.
 */

public class Community {

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
        @SerializedName("playlists")
        private List<PlaylistsEntity> playlists;

        public List<PlaylistsEntity> getPlaylists() {
            return playlists;
        }

        public void setPlaylists(List<PlaylistsEntity> playlists) {
            this.playlists = playlists;
        }
    }

    public static class PlaylistsEntity {
        @Expose
        @SerializedName("tracks")
        private List<TracksEntity> tracks;
        @Expose
        @SerializedName("tracks_count")
        private int tracks_count;
        @Expose
        @SerializedName("views")
        private int views;
        @Expose
        @SerializedName("description")
        private String description;
        @Expose
        @SerializedName("updated_at")
        private String updated_at;
        @Expose
        @SerializedName("created_at")
        private String created_at;
        @Expose
        @SerializedName("public")
        private int aPublic;
        @Expose
        @SerializedName("name")
        private String name;
        @Expose
        @SerializedName("id")
        private int id;

        public List<TracksEntity> getTracks() {
            return tracks;
        }

        public void setTracks(List<TracksEntity> tracks) {
            this.tracks = tracks;
        }

        public int getTracks_count() {
            return tracks_count;
        }

        public void setTracks_count(int tracks_count) {
            this.tracks_count = tracks_count;
        }

        public int getViews() {
            return views;
        }

        public void setViews(int views) {
            this.views = views;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
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

        public int getaPublic() {
            return aPublic;
        }

        public void setaPublic(int apublic) {
            this.aPublic = apublic;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }

    public static class TracksEntity {
        @Expose
        @SerializedName("plays")
        private int plays;
        @Expose
        @SerializedName("album_id")
        private int album_id;
        @Expose
        @SerializedName("spotify_popularity")
        private int spotify_popularity;
        @Expose
        @SerializedName("artists")
        private List<String> artists;
        @Expose
        @SerializedName("duration")
        private int duration;
        @Expose
        @SerializedName("number")
        private int number;
        @Expose
        @SerializedName("album_name")
        private String album_name;
        @Expose
        @SerializedName("name")
        private String name;
        @Expose
        @SerializedName("id")
        private int id;

        public int getPlays() {
            return plays;
        }

        public void setPlays(int plays) {
            this.plays = plays;
        }

        public int getAlbum_id() {
            return album_id;
        }

        public void setAlbum_id(int album_id) {
            this.album_id = album_id;
        }

        public int getSpotify_popularity() {
            return spotify_popularity;
        }

        public void setSpotify_popularity(int spotify_popularity) {
            this.spotify_popularity = spotify_popularity;
        }

        public List<String> getArtists() {
            return artists;
        }

        public void setArtists(List<String> artists) {
            this.artists = artists;
        }

        public int getDuration() {
            return duration;
        }

        public void setDuration(int duration) {
            this.duration = duration;
        }

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }

        public String getAlbum_name() {
            return album_name;
        }

        public void setAlbum_name(String album_name) {
            this.album_name = album_name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}
