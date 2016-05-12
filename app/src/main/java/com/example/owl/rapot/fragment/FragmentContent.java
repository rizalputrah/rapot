package com.example.owl.rapot.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.owl.rapot.R;
import com.example.owl.rapot.api.Api;
import com.example.owl.rapot.model.Rapot;
import com.example.owl.rapot.model.Result;
import com.example.owl.rapot.view.RapotAdapter;
import java.util.ArrayList;
import java.util.Arrays;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class FragmentContent extends Fragment {
    private RecyclerView recyclerView;
    private ArrayList<com.example.owl.rapot.model.Result> rapots;
    private String n = "";
    private RapotAdapter adapter;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private ArrayList<com.example.owl.rapot.model.Result> result;
    private CardView card;

    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_content,container,false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefreshLayout);

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadJSON(0);
            }
        });
        loadJSON(0);
        return view;
    }


    private void loadJSON(int page) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.0.15")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Api api = retrofit.create(Api.class);
        Call<Rapot> call = api.getJSON();
        call.enqueue(new Callback<Rapot>() {
            @Override
            public void onResponse(Call<Rapot> call, Response<Rapot> response) {

                Rapot rapot = response.body();
                rapots = new ArrayList<>(Arrays.asList(rapot.getResult()));
                adapter = new RapotAdapter(rapots);
                recyclerView.setAdapter(adapter);
                mSwipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onFailure(Call<Rapot> call, Throwable t) {
                Log.d("Error",t.getMessage());
            }
        });

    }
}
