package com.example.owl.rapot;

import android.app.Activity;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xml.sax.Parser;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends ListActivity {

    public ListView lv;

    private ProgressDialog pDialog;

    private static String url ="http://192.168.43.190/tutorial/login_register/crud/getNilai.php";

    //JSON Node names
    private static final String TAG_RESULT="result";
    private static final String TAG_ID="id";
    private static final String TAG_MAPEL="mapel";
    private static final String TAG_NILAI="nilai";

    JSONArray result = null;

    ArrayList<HashMap<String,String>> nilaiList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nilaiList = new ArrayList<HashMap<String,String>>();
        ListView lv = getListView();
        new GetNilai().execute();

    }

    private class GetNilai extends AsyncTask<Void,Void,Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(MainActivity.this);
            pDialog.setMessage("Please Wait..");
            pDialog.setCancelable(false);
            pDialog.show();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            ServiceHandler sh = new ServiceHandler();

            String jsonstr = sh.makeServiceCall(url,ServiceHandler.GET);
            Log.d("Response: ","> "+jsonstr);
            if(jsonstr != null){
                try{
                    JSONObject jsonObject = new JSONObject(jsonstr);
                    result = jsonObject.getJSONArray(TAG_RESULT);

                    for (int i = 0;i < result.length();i++){
                        JSONObject c = result.getJSONObject(i);

                        String id = c.getString(TAG_ID);
                        String mapel = c.getString(TAG_MAPEL);
                        String nilai = c.getString(TAG_NILAI);
                        int biji = Integer.parseInt(nilai);

                        HashMap<String,String> hasil = new HashMap<String,String>();

                        hasil.put(TAG_ID,id);
                        hasil.put(TAG_MAPEL,mapel);
                        hasil.put(TAG_NILAI,nilai);

                        nilaiList.add(hasil);


                        if(biji < 70){
                            Intent intent = new Intent(MainActivity.this,Notification_activity.class);
                            startActivity(intent);
                            finish();
                        }
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }else{
                Log.e("ServiceHandler","Couldn't get any data form url");
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


            ListAdapter adapter = new SimpleAdapter(
                    MainActivity.this, nilaiList,
                    R.layout.list_item, new String[] { TAG_ID, TAG_MAPEL,
                    TAG_NILAI }, new int[] { R.id.id,
                    R.id.mapel, R.id.nilai });

            setListAdapter(adapter);

        }
    }
}
