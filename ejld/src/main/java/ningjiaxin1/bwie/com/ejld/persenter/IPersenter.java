package ningjiaxin1.bwie.com.ejld.persenter;

import java.util.Map;

public interface IPersenter {
    void startRequest(String url, Map<String, String> params, Class clazz);
}
