package ningjiaxin1.bwie.com.drylx;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ningjiaxin1.bwie.com.drylx.adapter.ShopAdapter;
import ningjiaxin1.bwie.com.drylx.bean.Shop;
import ningjiaxin1.bwie.com.drylx.persenter.IPersentermpl;
import ningjiaxin1.bwie.com.drylx.view.IView;

public class ShopActivity extends AppCompatActivity implements IView ,View.OnClickListener{
    private ShopAdapter adapter;
    private RecyclerView recyclerView_shop;
    IPersentermpl iPersentermpl;
    private TextView text_suan;
    private TextView button;
    String path="http://www.zhaoapi.cn/product/getCarts";
    private CheckBox choose_quan;
    List<Shop.DataBean> list=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        init();
    }
    private void init(){
        iPersentermpl = new IPersentermpl(this);
        recyclerView_shop = findViewById(R.id.shop_jia);
        choose_quan = findViewById(R.id.choose_quan);
        text_suan = findViewById(R.id.text_suan);
        button = findViewById(R.id.button_jie);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        adapter=new ShopAdapter(this);
        recyclerView_shop.setLayoutManager(linearLayoutManager);
        recyclerView_shop.setAdapter(adapter);
        Map<String,String> map=new HashMap<>();
        map.put(Constants.MAP_KEY_GET_PRODUCT_UID,71+"");
        iPersentermpl.setrequest(path,map,Shop.class);
        adapter.setListener(new ShopAdapter.ShopCallBackListener() {
            @Override
            public void callBack(List<Shop.DataBean> list) {
                double totalprice=0;
                int num=0;
                for (int a = 0; a < list.size(); a++) {
                    //获取商家里商品
                    List<Shop.DataBean.ListBean> listAll = list.get(a).getList();
                    for (int i = 0; i < listAll.size(); i++) {
                        num = num + listAll.get(i).getNum();
                        //取选中的状态
                        if (listAll.get(i).isCheck()) {
                            totalprice = totalprice + (listAll.get(i).getPrice() * listAll.get(i).getNum());
                            num = num + listAll.get(i).getNum();
                        }
                    }
                }

                if (num < num) {
                    //不是全部选中
                    choose_quan.setChecked(false);
                } else {
                    //是全部选中
                    choose_quan.setChecked(true);
                }

                text_suan.setText("合计：" + totalprice);
                button.setText("去结算(" + num + ")");
            }
        });
    }
    @Override
    public void swtonSuccess(Object o) {
         Shop bean= (Shop) o;
         List<Shop.DataBean> data = bean.getData();
         adapter.setList(data);
    }
    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.choose_quan:
                checkSeller(choose_quan.isChecked());
                adapter.notifyDataSetChanged();
        }
    }
    public void checkSeller(boolean bool){
        double totalPrice=0;
        int num=0;
        //循环遍历商家
        for(int a=0;a<list.size();a++){
            Shop.DataBean dataBean = list.get(a);
            dataBean.setCheck(bool);
            List<Shop.DataBean.ListBean> listAll = this.list.get(a).getList();

        //循环商品
        for (int i=0;i<list.size();i++){
            listAll.get(i).setCheck(bool);
            num+=listAll.get(i).getNum()+num;
            totalPrice+=listAll.get(i).getPrice()+totalPrice;
        }
     }
     if(bool){
         text_suan.setText("合计："+totalPrice);
         button.setText("去结算:"+num);
     }else {
         text_suan.setText("合计：0.00");
         button.setText("去结算:(0)");
     }
    }
}
