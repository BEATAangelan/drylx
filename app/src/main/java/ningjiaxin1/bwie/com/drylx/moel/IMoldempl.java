package ningjiaxin1.bwie.com.drylx.moel;

import java.util.Map;

import ningjiaxin1.bwie.com.drylx.untils.MCallBack;
import ningjiaxin1.bwie.com.drylx.untils.OkHttpUntils;

    public class IMoldempl implements IModel{
        MCallBack mCallBack;
        @Override
        public void getsRequest(String url, Map<String, String> params, Class clazz, MCallBack callBack) {
            mCallBack=callBack;
            OkHttpUntils.getInstance().postRequest(url,params,clazz, new OkHttpUntils.OkCallBack() {
                @Override
                public void failed(Exception e) {
                    mCallBack.setdata(e);
                }

                @Override
                public void onSuccess(Object o) {
                    mCallBack.setdata(o);
                }
            });
        }
    }
