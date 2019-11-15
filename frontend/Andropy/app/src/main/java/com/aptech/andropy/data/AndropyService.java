package com.aptech.andropy.data;

/**
 * Created By  Archit
 * on 11/13/2019
 * for Andropy
 */
import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
public interface AndropyService {

    @Multipart
    @POST("api/run")
    Call<String> getValue(@Part MultipartBody.Part attachedMedia);

}
