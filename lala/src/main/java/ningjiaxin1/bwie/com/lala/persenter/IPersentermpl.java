package ningjiaxin1.bwie.com.lala.persenter;

import java.util.Map;

import ningjiaxin1.bwie.com.lala.model.IModelmpl;
import ningjiaxin1.bwie.com.lala.untils.MCallBack;
import ningjiaxin1.bwie.com.lala.view.IView;

public class IPersentermpl implements IPersenter{
    IModelmpl iModelmpl;
    IView miView;
    public IPersentermpl(IView iView){
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
