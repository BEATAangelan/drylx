package ningjiaxin1.bwie.com.drylx.untils;

import android.os.Handler;
import android.os.Looper;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

public class OkHttpUntils {
    private static OkHttpUntils instance;
    private OkHttpClient mClient;
    private Handler handler=new Handler(Looper.getMainLooper());

    public static OkHttpUntils getInstance(){
        if(instance==null){
            synchronized (OkHttpUntils.class){
                instance=new OkHttpUntils();
            }
        }
        return instance;
    }
    public interface OkCallBack{
        void failed(Exception e);
        void onSuccess(Object o);
    }
    public OkHttpUntils(){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        HttpLoggingInterceptor interceptor1 = interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        mClient = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .addInterceptor(interceptor1)
                .build();
    }
    //get请求
    public void getRequest(String url, final Class clazz, final OkCallBack okCallBack){
        Request build = new Request.Builder()
                .get()
                .url(url)
                .build();
        Call call = new OkHttpClient().newCall(build);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        okCallBack.failed(e);
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result = response.body().string();
                Gson gson = new Gson();
                final Object o = gson.fromJson(result, clazz);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        okCallBack.onSuccess(o);
                    }
                });
            }
        });
    }
    //post请求
    public  void postRequest(String url, Map<String,String> params, final Class clazz, final OkCallBack okCallBack){
        FormBody.Builder builder = new FormBody.Builder();
        for(Map.Entry<String,String> entry:params.entrySet()){
            builder.add(entry.getKey(),entry.getValue());
        }
        RequestBody body = builder.build();
        Request build = new Request.Builder()
                .post(body)
                .url(url)
                .build();
        Call call = new OkHttpClient().newCall(build);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        okCallBack.failed(e);
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    String result = response.body().string();
                    Gson gson = new Gson();
                    final Object o = gson.fromJson(result, clazz);
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            okCallBack.onSuccess(o);
                        }
                    });
                }catch (Exception e){
                    okCallBack.failed(e);
                }
            }
        });
    }
}
