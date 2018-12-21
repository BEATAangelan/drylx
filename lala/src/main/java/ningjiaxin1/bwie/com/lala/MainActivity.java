package ningjiaxin1.bwie.com.lala;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ningjiaxin1.bwie.com.lala.adapter.RecyclerAdapter;
import ningjiaxin1.bwie.com.lala.bean.Goods;
import ningjiaxin1.bwie.com.lala.persenter.IPersentermpl;
import ningjiaxin1.bwie.com.lala.view.IView;

public class MainActivity extends AppCompatActivity implements IView ,View.OnClickListener{
    private String path="http://www.zhaoapi.cn/product/searchProducts";
    private RecyclerAdapter adapter;
    IPersentermpl iPersentermpl;
    private RecyclerView recyclerView;
    EditText title_bar_title;
    Button title_bar_right,zong,xl,jg,sx;
     List<Goods.DataBean> list=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        title_bar_title = findViewById(R.id.title_bar_title);
        title_bar_right= findViewById(R.id.title_bar_right);
        init();
    }
    public void init(){
        iPersentermpl = new IPersentermpl(this);
        recyclerView = findViewById(R.id.relat);
        final Map<String,String> map=new HashMap<>();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter=new RecyclerAdapter(this);
        recyclerView.setAdapter(adapter);
        title_bar_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = title_bar_title.getText().toString();
                map.put("keywords","s");
                map.put("page",1+"");
                iPersentermpl.startRequest(path,map,Goods.class);
            }
        });
      adapter.setOnClickListener(new RecyclerAdapter.OnItemClickLinstener() {
          @Override
          public void OnClickLinstener(View item, int position) {

          }
      });
    }
    @Override
    public void setonSuccess(Object o) {
        Goods bewan= (Goods) o;
        List<Goods.DataBean> data = bewan.getData();
        adapter.setList(data);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.zong:

                break;
            case R.id.jg:
                final Map<String,String> map=new HashMap<>();
                map.put("keywords","s");
                map.put("page",1+"");
                map.put("sort",2+"");
                iPersentermpl.startRequest(path,map,Goods.class);
                break;
            case R.id.xl:
                final Map<String,String> maps=new HashMap<>();
                maps.put("keywords","s");
                maps.put("page",1+"");
                maps.put("sort",1+"");
                iPersentermpl.startRequest(path,maps,Goods.class);
                break;
                default:
                    break;

        }
    }
}
