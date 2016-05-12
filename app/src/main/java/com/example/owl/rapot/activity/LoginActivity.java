package com.example.owl.rapot.activity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.owl.rapot.MainActivity;
import com.example.owl.rapot.R;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText editTextUsername,editTextPassword;
    private Button btnLogin;

    String username,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextUsername = (EditText) findViewById(R.id.editTextUser);
        editTextPassword = (EditText) findViewById(R.id.editTextPass);

        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == btnLogin){
            invokeLogin();
        }
    }

    public void invokeLogin(){
        username = editTextUsername.getText().toString();
        password = editTextPassword.getText().toString();

        login(username,password);
    }

    private void login(final String username, final String password){
        class LoginAsync extends AsyncTask<String,Void,String>{
            private Dialog loadingdialog;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loadingdialog = ProgressDialog.show(LoginActivity.this,"Please Wait","Loading..");
            }

            @Override
            protected String doInBackground(String... params) {
                String uname = params[0];
                String pass = params[1];

                InputStream is = null;
                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("username",uname));
                nameValuePairs.add(new BasicNameValuePair("password",pass));
                String result = null;
                try{
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost("http://192.168.0.15/tutorial/login_register/login.php");
                    httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

                    HttpResponse response = httpClient.execute(httpPost);

                    HttpEntity entity = response.getEntity();
                    is = entity.getContent();

                    BufferedReader reader = new BufferedReader(new InputStreamReader(is,"UTF-8"),8);
                    StringBuilder sb = new StringBuilder();

                    String line = null;
                    while((line=reader.readLine())!=null){
                        sb.append(line + "\n");
                    }
                    result = sb.toString();
                }catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (ClientProtocolException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return result;
            }


            @Override
            protected void onPostExecute(String result) {
                System.out.println("RESULT"+result);
                String s = result.trim();
                loadingdialog.dismiss();
                if(s.equalsIgnoreCase("success login")){
                    //Intent kemana??
                    Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(getApplicationContext(),"Invalid Username or Password", Toast.LENGTH_LONG).show();
                }
            }
        }

        LoginAsync la = new LoginAsync();
        la.execute(username,password);
    }
}
