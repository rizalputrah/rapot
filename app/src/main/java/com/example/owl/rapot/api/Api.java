package com.example.owl.rapot.api;

import com.example.owl.rapot.model.Rapot;
import com.example.owl.rapot.model.Result;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by OWL on 10/05/2016.
 */
public interface Api{
    @GET("tutorial/login_register/crud/getNilai.php")
    Call<Rapot> getJSON();
}
