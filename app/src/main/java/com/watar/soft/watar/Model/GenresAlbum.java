package com.watar.soft.watar.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by user on 07-08-2018.
 */

public class GenresAlbum {


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
        @SerializedName("album")
        private AlbumEntity album;

        public AlbumEntity getAlbum() {
            return album;
        }

        public void setAlbum(AlbumEntity album) {
            this.album = album;
        }
    }

    public static class AlbumEntity {
        @Expose
        @SerializedName("tracks")
        private List<TracksEntity> tracks;
        @Expose
        @SerializedName("artist")
        private ArtistEntity artist;
        @Expose
        @SerializedName("views")
        private int views;
        @Expose
        @SerializedName("spotify_popularity")
        private int spotify_popularity;
        @Expose
        @SerializedName("artist_id")
        private int artist_id;
        @Expose
        @SerializedName("image")
        private String image;
        @Expose
        @SerializedName("release_date")
        private String release_date;
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

        public ArtistEntity getArtist() {
            return artist;
        }

        public void setArtist(ArtistEntity artist) {
            this.artist = artist;
        }

        public int getViews() {
            return views;
        }

        public void setViews(int views) {
            this.views = views;
        }

        public int getSpotify_popularity() {
            return spotify_popularity;
        }

        public void setSpotify_popularity(int spotify_popularity) {
            this.spotify_popularity = spotify_popularity;
        }

        public int getArtist_id() {
            return artist_id;
        }

        public void setArtist_id(int artist_id) {
            this.artist_id = artist_id;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getRelease_date() {
            return release_date;
        }

        public void setRelease_date(String release_date) {
            this.release_date = release_date;
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

    public static class ArtistEntity {
        @Expose
        @SerializedName("views")
        private int views;
        @Expose
        @SerializedName("created_at")
        private String created_at;
        @Expose
        @SerializedName("bio")
        private BioEntity bio;
        @Expose
        @SerializedName("updated_at")
        private String updated_at;
        @Expose
        @SerializedName("image_large")
        private String image_large;
        @Expose
        @SerializedName("image_small")
        private String image_small;
        @Expose
        @SerializedName("spotify_popularity")
        private int spotify_popularity;
        @Expose
        @SerializedName("spotify_followers")
        private int spotify_followers;
        @Expose
        @SerializedName("name")
        private String name;
        @Expose
        @SerializedName("id")
        private int id;

        public int getViews() {
            return views;
        }

        public void setViews(int views) {
            this.views = views;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public BioEntity getBio() {
            return bio;
        }

        public void setBio(BioEntity bio) {
            this.bio = bio;
        }

        public String getUpdated_at() {
            return updated_at;
        }

        public void setUpdated_at(String updated_at) {
            this.updated_at = updated_at;
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

        public int getSpotify_popularity() {
            return spotify_popularity;
        }

        public void setSpotify_popularity(int spotify_popularity) {
            this.spotify_popularity = spotify_popularity;
        }

        public int getSpotify_followers() {
            return spotify_followers;
        }

        public void setSpotify_followers(int spotify_followers) {
            this.spotify_followers = spotify_followers;
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

    public static class BioEntity {
        @Expose
        @SerializedName("images")
        private List<ImagesEntity> images;
        @Expose
        @SerializedName("bio")
        private String bio;

        public List<ImagesEntity> getImages() {
            return images;
        }

        public void setImages(List<ImagesEntity> images) {
            this.images = images;
        }

        public String getBio() {
            return bio;
        }

        public void setBio(String bio) {
            this.bio = bio;
        }
    }

    public static class ImagesEntity {
        @Expose
        @SerializedName("url")
        private String url;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
