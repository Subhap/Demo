package test.sdrc.com.mobilewebservicedemo;

import android.os.AsyncTask;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;


import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Ratikanta Pradhan (ratikanta@sdrc.co.in) on 26-04-2017.
 * This AsyncTask will help login process
 */

public class LoginAsynTask extends AsyncTask<Object, Void, String> {
    private MasterDataModel masterDataModel;
    private String result;
    private LoginListener loginListener;
    @Override
    protected String doInBackground(Object[] objects) {
        try {
            String username = (String) objects[0];
            String password = (String) objects[1];
                //There is no user in database, we have to hit the server
                //checking internet connection
                if ((Boolean) objects[4]) {
                    String url = (String) objects[2];
                    int login_timeout_in_second = (Integer) objects[3];
                    HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
                    interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                    //We are using OkHttpClient for setting the timeout only
                    final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                            .readTimeout(login_timeout_in_second, TimeUnit.SECONDS)
                            .connectTimeout(login_timeout_in_second, TimeUnit.SECONDS)
                            .addInterceptor(interceptor)
                            .build();
                    Gson gson = new GsonBuilder()
                            .setLenient()
                            .create();
                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(url)
                            .addConverterFactory(GsonConverterFactory.create(gson))
                            .client(okHttpClient)
                            .build();

                    LoginService service = retrofit.create(LoginService.class);
                    test.sdrc.com.mobilewebservicedemo.webservice.model.LoginModel loginDataModel1 = new  test.sdrc.com.mobilewebservicedemo.webservice.model.LoginModel();
                    loginDataModel1.setUserName(username);
                    loginDataModel1.setPassword(password);

                    Call<MasterDataModel> call = service.validateLogin(loginDataModel1);

                    call.enqueue(new Callback<MasterDataModel>() {
                        @Override
                        public void onResponse(Call<MasterDataModel> call, retrofit2.Response<MasterDataModel> response) {
                            //handling the responce
                            if (response.code() == 200) {
                                masterDataModel = response.body();
                                if(masterDataModel.getMessage()!=null ){

                                    result= masterDataModel.getMessage();
                                }
                                onPostExecute(result);

                            } else {
                                LoginModel loginDataModelErr = new LoginModel();
                                switch (response.code()) {
                                    case 401:

                                        break;
                                    case 404:

                                        break;
                                    case 500:

                                        break;
                                    default:

                                        break;
                                }
                                onPostExecute(result);
                            }
                        }

                        @Override
                        public void onFailure(Call<MasterDataModel> call, Throwable t) {
                            //handling the error
                            result ="unable to process your request";
                            onPostExecute(result);
                        }


                    });

                } else {

                    return "";
                }


            return "";

        }catch (Exception e){
            e.printStackTrace();

            return "";
        }
    }

    @Override
    protected void onPostExecute(String o) {
        if(this.loginListener != null){
            loginListener.loginComplete(o);
        }
        super.onPostExecute(o);
    }

    public void setLoginListener(LoginListener loginListener) {
        this.loginListener = loginListener;
    }
}
