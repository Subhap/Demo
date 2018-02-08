package test.sdrc.com.mobilewebservicedemo;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import test.sdrc.com.mobilewebservicedemo.webservice.model.*;
import test.sdrc.com.mobilewebservicedemo.webservice.model.LoginModel;

public class MainActivity extends AppCompatActivity implements  LoginListener{
    private String username;
    private String password;
    private Button login;
    int login_timeout_in_second = 60;
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setTitle("");
        progressDialog.setMessage("Please wait.....");
        progressDialog.setCancelable(false);
        findViewById(R.id.login_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username = ((EditText) findViewById(R.id.username_et)).getText().toString();
                password = ((EditText) findViewById(R.id.password_et)).getText().toString();
				Log.v("",);
                progressDialog.show();
                LoginAsynTask loginAsyncTask = new LoginAsynTask();
                loginAsyncTask.setLoginListener((LoginListener) MainActivity.this);
                loginAsyncTask.execute(username
                        , password,
                        getString(R.string.server_url), login_timeout_in_second, isNetworkAvailable());
            }
        });

    }
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    @Override
    public void loginComplete(String  result) {
        progressDialog.dismiss();
        if(result.equalsIgnoreCase("User found")){
            finish();
            Intent in = new Intent(MainActivity.this,HomeActivity.class);
            startActivity(in);
        }else if(result.equalsIgnoreCase("No User found")){
            Toast.makeText(MainActivity.this,"invalid credentials",Toast.LENGTH_LONG).show();
        }else if(result.equalsIgnoreCase("unable to process your request")){
            Toast.makeText(MainActivity.this,"Sorry unable to process your request",Toast.LENGTH_LONG).show();
        }

    }
}
