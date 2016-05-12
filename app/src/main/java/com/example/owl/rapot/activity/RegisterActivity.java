package com.example.owl.rapot.activity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.owl.rapot.R;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText editTextnama,editTextusername,editTextpassword,editTextemail;
    private Button buttonRegister,buttontoLogin;

    private static final String REGISTER_URL = "http://192.168.88.125/tutorial/login_register/register.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        editTextnama = (EditText) findViewById(R.id.nama);
        editTextusername = (EditText) findViewById(R.id.username);
        editTextpassword = (EditText) findViewById(R.id.password);
        editTextemail = (EditText) findViewById(R.id.email);

        buttonRegister = (Button) findViewById(R.id.btnRegister);
        buttonRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == buttonRegister){
            registerUser();
        }
    }

    private void registerUser(){
        String nama = editTextnama.getText().toString().trim().toLowerCase();
        String username = editTextusername.getText().toString().trim().toLowerCase();
        String password = editTextpassword.getText().toString().trim().toLowerCase();
        String email = editTextemail.getText().toString().trim().toLowerCase();

        register(nama,username,password,email);
    }

    private void register(String nama,String username,String password,String email){
        String urlSuffix = "?nama="+nama+"&username="+username+"&password="+password+"&email="+email;
        class RegisterUser extends AsyncTask<String,Void,String>{

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(RegisterActivity.this,"Please Wait",null,true,true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(String... params) {
                String s = params[0];
                BufferedReader bufferedReader = null;
                try{
                    URL url = new URL(REGISTER_URL+s);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));

                    String result;

                    result = bufferedReader.readLine();

                    return result;
                }catch (Exception e){
                    return null;
                }
            }
        }

        RegisterUser ru = new RegisterUser();
        ru.execute(urlSuffix);
    }
}
