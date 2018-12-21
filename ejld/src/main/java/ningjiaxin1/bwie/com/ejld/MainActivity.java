package ningjiaxin1.bwie.com.ejld;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ningjiaxin1.bwie.com.ejld.adapter.LeftAdapter;
import ningjiaxin1.bwie.com.ejld.adapter.RightAdapter;
import ningjiaxin1.bwie.com.ejld.bean.Left;
import ningjiaxin1.bwie.com.ejld.bean.Right;
import ningjiaxin1.bwie.com.ejld.persenter.IPersentermpl;
import ningjiaxin1.bwie.com.ejld.view.IView;

public class MainActivity extends AppCompatActivity implements IView ,View.OnClickListener{
    private String urlleft="http://www.zhaoapi.cn/product/getCatagory";
    private String urlRigth="http://www.zhaoapi.cn/product/getProductCatagory";
    private RecyclerView rightrecycle;
    private RecyclerView leftrecycle;
    private LeftAdapter leftAdapter;
    private RightAdapter rightAdapter;
    IPersentermpl iPersentermpl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rightrecycle = findViewById(R.id.rightrecy);
        leftrecycle = findViewById(R.id.leftrecy);
        leftAdapter = new LeftAdapter(this);
        rightAdapter = new RightAdapter(this);
        initLeft();
        initRight();
    }
    private void initLeft(){
        iPersentermpl=new IPersentermpl(this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        leftrecycle.setLayoutManager(linearLayoutManager);

        leftrecycle.setAdapter(leftAdapter);
        HashMap<String,String> map=new HashMap<>();
        iPersentermpl.startRequest(urlleft,map,Left.class);
         leftAdapter.setOnClickListener(new LeftAdapter.onClickListener() {
             @Override
             public void onClick(int position, String cid) {
                 getData(cid);
             }
         });
        }

    private void getData(String uid) {
        HashMap<String,String>map = new HashMap<>();
        map.put("cid",uid);
        iPersentermpl.startRequest(urlRigth,map,Right.class);
    }

    private void initRight(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        rightrecycle.setLayoutManager(linearLayoutManager);
        rightrecycle.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        rightrecycle.setAdapter(rightAdapter);

//        adapterr=new RightAdapter(this);
//        rightrecycle.setAdapter(adapter);
//        Map<String,String> map=new HashMap<>();
//        map.put("cid",1+"");

    }
    @Override
    public void setonSuccess(Object o) {
        if(o instanceof Left){
            Left bean= (Left) o;
            leftAdapter.setList(bean.getData());
        }
        if(o instanceof Right){
            Right bean= (Right) o;
            rightAdapter.setList(bean.getData());
        }
    }

    @Override
    public void onClick(View v) {

    }
}
