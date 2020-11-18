package com.liger.practice.retrofit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * @author Liger
 * @date 2020/11/18 17:18
 */
public interface GitHubService {

    @GET("users/{user}")
    Call<User> listRepos(@Path("user") String user);
}
