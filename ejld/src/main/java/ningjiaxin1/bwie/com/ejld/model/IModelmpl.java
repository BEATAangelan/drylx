package ningjiaxin1.bwie.com.ejld.model;

import java.util.Map;

import ningjiaxin1.bwie.com.ejld.untils.MCallBack;
import ningjiaxin1.bwie.com.ejld.untils.OkHttpUntils;


public class IModelmpl implements IModel{
    MCallBack mCallBack;

    @Override
    public void getRequest(String url, Map<String, String> params, Class clazz, MCallBack callBack) {
        mCallBack=callBack;
        OkHttpUntils.getInstance().postRequest(url, params, clazz, new OkHttpUntils.OkCallBack() {
            @Override
            public void onSuccess(Object o) {
                mCallBack.setdata(o);
            }

            @Override
            public void onfail(Exception e) {
                mCallBack.setdata(e);
            }
        });
    }
}
