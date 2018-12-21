package ningjiaxin1.bwie.com.ejld.persenter;

import java.util.Map;

import ningjiaxin1.bwie.com.ejld.MainActivity;
import ningjiaxin1.bwie.com.ejld.model.IModelmpl;
import ningjiaxin1.bwie.com.ejld.untils.MCallBack;
import ningjiaxin1.bwie.com.ejld.view.IView;


public class IPersentermpl implements IPersenter{
    IModelmpl iModelmpl;
    IView miView;
    public IPersentermpl(MainActivity iView){
        miView=iView;
        iModelmpl=new IModelmpl();
    }
    @Override
    public void startRequest(String url, Map<String, String> params, Class clazz) {
        iModelmpl.getRequest(url, params, clazz, new MCallBack() {
            @Override
            public void setdata(Object o) {
                miView.setonSuccess(o);
            }
        });
    }
}
