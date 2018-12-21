package ningjiaxin1.bwie.com.drylx.persenter;

import java.util.Map;

import ningjiaxin1.bwie.com.drylx.moel.IMoldempl;
import ningjiaxin1.bwie.com.drylx.untils.MCallBack;
import ningjiaxin1.bwie.com.drylx.view.IView;

public class IPersentermpl implements IPersenter{
    IMoldempl iMoldempl;
    IView miView;
    public IPersentermpl(IView iView){
        miView=iView;
        iMoldempl = new IMoldempl();
    }
    @Override
    public void setrequest(String url, Map<String, String> params, Class clazz) {
        iMoldempl.getsRequest(url, params, clazz, new MCallBack() {
            @Override
            public void setdata(Object o) {
                miView.swtonSuccess(o);
            }
        });
    }
}
