package ningjiaxin1.bwie.com.ejld.model;

import java.util.Map;

import ningjiaxin1.bwie.com.ejld.untils.MCallBack;


public interface IModel {
    void getRequest(String url, Map<String, String> params, Class clazz, MCallBack callBack);
}
