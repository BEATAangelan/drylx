package ningjiaxin1.bwie.com.drylx;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ningjiaxin1.bwie.com.drylx.bean.Shop;
import ningjiaxin1.bwie.com.drylx.view.IView;
import ningjiaxin1.bwie.com.drylx.adapter.ProductsAdapter;
public class CustomCounterView extends RelativeLayout implements View.OnClickListener{
    private Context context;
    ImageView image_jian,image_jia;
    EditText edit_num;
    private int nun;
    private int position;
    ProductsAdapter productsAdapter;
    private List<Shop.DataBean.ListBean> list=new ArrayList<>();
    public CustomCounterView(@NonNull Context context) {
        super(context);
        init(context);
    }

    public CustomCounterView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CustomCounterView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    @Override
    public void onDraw(Canvas c) {
        super.onDraw(c);
    }

    public void init(Context context){
        this.context=context;
        View view = View.inflate(context, R.layout.item1, null);
        image_jian = view.findViewById(R.id.image_jian);
        image_jia = view.findViewById(R.id.image_jia);
        edit_num = view.findViewById(R.id.edit_num);
        image_jia.setOnClickListener(this);
        image_jian.setOnClickListener(this);
        addView(view);
        edit_num.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {
                    String s1 = edit_num.getText().toString();
                    nun=Integer.parseInt(String.valueOf(s1));
                    list.get(position).setNum(nun);
                }catch (Exception e){
                    list.get(position).setNum(1);
                }
                if(monClick!=null){
                    monClick.OnClick();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
    public void setData(ProductsAdapter productsAdapter, List<Shop.DataBean.ListBean> list, int i) {
        this.list=list;
        this.productsAdapter=productsAdapter;
        position=i;
        nun=list.get(i).getNum();
        edit_num.setText(nun+"");
    }
    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.image_jia:
                nun++;
                edit_num.setText(nun+"");
                list.get(position).setNum(nun);
                monClick.OnClick();
                productsAdapter.notifyItemChanged(position);
                break;
            case R.id.image_jian:
                if(nun>1){
                    nun--;
                }else
                {
                    Toast.makeText(context,"我是有底线的",Toast.LENGTH_SHORT).show();
                }
                edit_num.setText(nun+"");
                list.get(position).setNum(nun);
                monClick.OnClick();
                productsAdapter.notifyDataSetChanged();
                break;
                default:
                    break;
        }
    }

    //定义接口
    public interface OnClick{
        void OnClick();
    }
    private OnClick monClick;
    public void setOnClick(OnClick onClick){
        monClick=onClick;
    }
}
