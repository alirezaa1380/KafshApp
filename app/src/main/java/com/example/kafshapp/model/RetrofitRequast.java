package com.example.kafshapp.model;

import com.example.kafshapp.Post;
import com.example.kafshapp.model.Comment;
import com.example.kafshapp.model.Person;

import java.util.List;

import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RetrofitRequast {
 @POST("AddUserByPostMethod.php")
 Call<String> post (@Body Person person);

 @GET("LoginAppKafsh2.php")
 Call<String> GetDataPerson(@Query("namePerson") String name,@Query("passwordPerson") String pass);

 @GET("GetaDataPost.php")
 Single<List<Post>> GetDataPost();

 @GET("DataMan.php")
 Single<List<Post>> GetDataMan();

 @GET("DataWoman.php")
 Single<List<Post>> GetDataWoman();

 @GET("DataBaby.php")
 Single<List<Post>> GetDataBaby();

 @POST("AddComment.php")
 Single<String> AddComment(@Body Comment comment);

 @GET("GetComment.php")
 Single<List<Comment>>GetDataComment(@Query("id") int id);
}
