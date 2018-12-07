package com.example.gl.demo_android.mvp.net;

import com.example.gl.demo_android.mvp.model.IpInfo;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface IpService {
    @FormUrlEncoded
    @POST("getIpInfo.php")
    Observable<IpInfo> getIpInfo(@Field("ip") String ip);
}
