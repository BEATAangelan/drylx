package ningjiaxin1.bwie.com.lala.untils;

import android.os.Looper;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import android.os.Handler;
import android.os.Looper;

import com.google.gson.Gson;

import java.io.IOException;
import java.text.Normalizer;
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
        private static volatile OkHttpUntils instance;
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
            void onSuccess(Object o);
            void onfail(Exception e);
        }
        public OkHttpUntils(){
            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
            HttpLoggingInterceptor interceptor = httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            mClient = new OkHttpClient.Builder()
                    .readTimeout(10, TimeUnit.SECONDS)
                    .writeTimeout(10, TimeUnit.SECONDS)
                    .connectTimeout(10, TimeUnit.SECONDS)
                    .addInterceptor(interceptor)
                    .build();
        }
        //post请求
        public void postRequest(String url, Map<String,String> params, final Class clazz, final OkCallBack okCallBack){
            FormBody.Builder builder = new FormBody.Builder();
            for(Map.Entry<String,String> entry:params.entrySet()){
                builder.add(entry.getKey(),entry.getValue());
            }
            RequestBody build = builder.build();
            Request build1 = new Request.Builder()
                    .post(build)
                    .url(url)
                    .build();
            Call call = mClient.newCall(build1);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, final IOException e) {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            okCallBack.onfail(e);
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
        }}


