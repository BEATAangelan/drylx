package ningjiaxin1.bwie.com.njx.perenter;

import ningjiaxin1.bwie.com.njx.model.IModelmpl;
import ningjiaxin1.bwie.com.njx.view.IView;

public class IPersentermpl implements IPersenter{
    IModelmpl miModelmpl;
    IView miView;
    public  IPersentermpl(IView iView){
        miModelmpl = new IModelmpl();
        miView=iView;
    }
    @Override
    public void postRequest(String url, Class clazz) {
        miModelmpl.setRequest(url,clazz);
    }
    //防止泄露
    public void onDesctry(){
        if(miModelmpl!=null){
            miModelmpl=null;
        }if(miView!=null){
            miModelmpl=null;
        }
    }
}
