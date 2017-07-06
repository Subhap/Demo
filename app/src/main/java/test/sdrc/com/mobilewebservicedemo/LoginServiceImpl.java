package test.sdrc.com.mobilewebservicedemo;

import retrofit2.Call;
import retrofit2.http.Body;
import test.sdrc.com.mobilewebservicedemo.webservice.model.LoginModel;
import test.sdrc.com.mobilewebservicedemo.LoginService;
import test.sdrc.com.mobilewebservicedemo.MasterDataModel;

/**
 * Created by Ratikanta Pradhan (ratikanta@sdrc.co.in), created on 05-05-2017.
 * This class will implement some methods of LoginService
 */

public class LoginServiceImpl implements LoginService {
    @Override
    public Call<MasterDataModel> validateLogin(@Body test.sdrc.com.mobilewebservicedemo.webservice.model.LoginModel loginModel) {
        return null;
    }
}