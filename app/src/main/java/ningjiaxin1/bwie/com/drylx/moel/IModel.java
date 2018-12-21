package ningjiaxin1.bwie.com.drylx.moel;

import java.util.Map;

import ningjiaxin1.bwie.com.drylx.untils.MCallBack;

public interface IModel{
    void getsRequest(String url, Map<String,String> params, Class clazz, MCallBack callBack );
}
