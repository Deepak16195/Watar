package com.watar.soft.watar.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by user on 07-08-2018.
 */

public class Artist {


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
        @SerializedName("artists")
        private List<ArtistsEntity> artists;

        public List<ArtistsEntity> getArtists() {
            return artists;
        }

        public void setArtists(List<ArtistsEntity> artists) {
            this.artists = artists;
        }
    }

    public static class ArtistsEntity {
        @Expose
        @SerializedName("trackCount")
        private int trackCount;
        @Expose
        @SerializedName("albumCount")
        private int albumCount;
        @Expose
        @SerializedName("image_large")
        private String image_large;
        @Expose
        @SerializedName("image_small")
        private String image_small;
        @Expose
        @SerializedName("name")
        private String name;
        @Expose
        @SerializedName("id")
        private int id;

        public int getTrackCount() {
            return trackCount;
        }

        public void setTrackCount(int trackCount) {
            this.trackCount = trackCount;
        }

        public int getAlbumCount() {
            return albumCount;
        }

        public void setAlbumCount(int albumCount) {
            this.albumCount = albumCount;
        }

        public String getImage_large() {
            return image_large;
        }

        public void setImage_large(String image_large) {
            this.image_large = image_large;
        }

        public String getImage_small() {
            return image_small;
        }

        public void setImage_small(String image_small) {
            this.image_small = image_small;
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
