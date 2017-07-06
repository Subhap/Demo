package test.sdrc.com.mobilewebservicedemo;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import test.sdrc.com.mobilewebservicedemo.webservice.model.*;

/**
 * Created by SDRC_DEV on 20-06-2017.
 */

public interface LoginService {
    @POST("login")
    Call<MasterDataModel> validateLogin(@Body test.sdrc.com.mobilewebservicedemo.webservice.model.LoginModel loginModel);


}