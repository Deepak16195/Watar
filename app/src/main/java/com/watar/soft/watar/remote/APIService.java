package com.watar.soft.watar.remote;


import com.watar.soft.watar.Model.Artist;
import com.watar.soft.watar.Model.ArtsitDetails;
import com.watar.soft.watar.Model.Community;
import com.watar.soft.watar.Model.Genres;
import com.watar.soft.watar.Model.GenresAlbum;
import com.watar.soft.watar.Model.GenresByName;
import com.watar.soft.watar.Model.Login;
import com.watar.soft.watar.Model.OTPConfirm;
import com.watar.soft.watar.Model.SignUp;
import com.watar.soft.watar.Model.TrendingTracks;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Url;
import rx.Observable;


/**
 * Created by user on 07-09-2017.
 */

public interface APIService {

    /**
     * @param Key
     * @param Brand
     * @param Password
     * @return

     */

    @Headers("Accept:application/json,Content-Type:application/json")
    @FormUrlEncoded
    @POST("auth/login")
    Observable<Login> chkLogin(@Header("Key") String Key, @Field("email") String Brand, @Field("password") String Password);

    @Headers("Accept:application/json,Content-Type:application/json")
    @Multipart
    @POST("auth/register")
    Observable<SignUp> Register(@Header("Key") String Key, @Part("first_name") RequestBody first_name, @Part("last_name") RequestBody last_name,
                                @Part("mobile") RequestBody mobile, @Part("email") RequestBody email, @Part("username") RequestBody username,
                                @Part("password") RequestBody password, @Part("password_confirmation") RequestBody password_confirmation, @Part MultipartBody.Part avatar);

    @Headers("Accept:application/json,Content-Type:application/json")
    @FormUrlEncoded
    @POST("auth/code/resend")
    Observable<SignUp> OTPResend(@Header("Key") String Key, @Field("mobile") String mobile);



    @Headers("Accept:application/json,Content-Type:application/json")
    @FormUrlEncoded
    @POST("auth/code/confirm")
    Observable<OTPConfirm> OTPConfirm(@Header("Key") String Key, @Field("code") String OTP, @Field("mobile") String mobile);

    @Headers("Accept:application/json,Content-Type:application/json")
    @GET("genres/popular")
    Observable<Genres> getGeners(@Header("Key") String Key);

    @Headers("Accept:application/json,Content-Type:application/json")
    @GET("tracks/top")
    Observable<TrendingTracks> getTrending(@Header("Key") String Key);

    @Headers("Accept:application/json,Content-Type:application/json")
    @GET
    Observable<GenresByName> getGenerByName(@Header("Key") String Key, @Url String URL);

    @Headers("Accept:application/json,Content-Type:application/json")
    @GET
    Observable<GenresAlbum> getAlbumByID(@Header("Key") String Key, @Url String URL);

    @Headers("Accept:application/json,Content-Type:application/json")
    @GET("artists")
    Observable<Artist> getArtist(@Header("Key") String Key);

    @Headers("Accept:application/json,Content-Type:application/json")
    @GET
    Observable<ArtsitDetails> getArtistDetails(@Header("Key") String Key, @Url String URL);

    @Headers("Accept:application/json,Content-Type:application/json")
    @GET("playlists")
    Observable<Community> getCommunity(@Header("Key") String Key);


    @Headers("Accept:application/json,Content-Type:application/json")
    @POST("auth/logout")
    Observable<Login> getLogOut(@Header("Authorization") String Authorization,@Header("Key") String Key);


    @Headers("Accept:application/json,Content-Type:application/json")
    @FormUrlEncoded
    @POST("auth/password/reset")
    Observable<SignUp> ResetPassword(@Header("Key") String Key, @Field("password") String Password, @Field("password_confirmation") String PasswordConfirmation,@Field("mobile") String Mobile);


/*
    @GET("API/Brand")
    Observable<Image> getProducts(@Query("Brand") String Brand, @Query("Flag") String Flag, @Query("DealerNo") String DealerNo);

    @GET("API/Scheme")
    Observable<Image> getSchemeProducts(@Query("Search") String Search);*/



}