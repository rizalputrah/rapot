package com.example.owl.rapot;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class FragmentContent extends Fragment {
    private ProgressDialog pDialog;
    private RecyclerView recyclerView;
    private ArrayList<Rapot> daftarNilai = new ArrayList<>();
    private RapotAdapter rAdapter;

    private static String url = "http://192.168.88.74/tutorial/login_register/crud/getNilai.php";

    //JSON Node names
    private static final String TAG_RESULT = "result";
    private static final String TAG_ID = "id";
    private static final String TAG_MAPEL = "mapel";
    private static final String TAG_NILAI = "nilai";

    JSONArray result = null;

    ArrayList<HashMap<String, String>> nilaiList;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }



    private class GetNilai extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(getActivity());
            pDialog.setMessage("Please Wait..");
            pDialog.setCancelable(true);
            pDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            ServiceHandler sh = new ServiceHandler();

            String jsonstr = sh.makeServiceCall(url, ServiceHandler.GET);
            Log.d("Response: ", "> " + jsonstr);
            if (jsonstr != null) {
                try {
                    JSONObject jsonObject = new JSONObject(jsonstr);
                    result = jsonObject.getJSONArray(TAG_RESULT);

                    for (int i = 0; i < result.length(); i++) {
                        JSONObject c = result.getJSONObject(i);

                        Rapot rapot = new Rapot();

                        rapot.setMapel(TAG_MAPEL);
                        rapot.setNilai(TAG_NILAI);

                        daftarNilai.add(rapot);

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                Log.e("ServiceHandler", "Couldn't get any data form url");
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if (pDialog.isShowing())
                pDialog.dismiss();
            /**
             * Updating parsed JSON data into ListView
             * */

            //RECYCLER VIEW NE NANG KENE

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_content,container,false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        rAdapter = new RapotAdapter(getContext(),daftarNilai);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(rAdapter);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        new GetNilai();
    }
}