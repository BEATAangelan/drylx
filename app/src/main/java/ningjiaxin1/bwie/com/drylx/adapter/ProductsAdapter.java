package ningjiaxin1.bwie.com.drylx.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import ningjiaxin1.bwie.com.drylx.CustomCounterView;
import ningjiaxin1.bwie.com.drylx.R;
import ningjiaxin1.bwie.com.drylx.bean.Shop;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ViewHolder> {
    private Context context;
    private List<Shop.DataBean.ListBean> list;


    public ProductsAdapter(Context context, List<Shop.DataBean.ListBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ProductsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.shangpin, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProductsAdapter.ViewHolder viewHolder, final int i) {
        String url = list.get(i).getImages().split("\\|")[0].replace("https", "http");
        Glide.with(context).load(url).into(viewHolder.image_pin);
        viewHolder.text_title.setText(list.get(i).getTitle());
        viewHolder.text_price.setText(list.get(i).getPrice()+"");
        //根据我记录的状态，改变勾选
        viewHolder.checkBox_pin.setChecked(list.get(i).isCheck());
        //商品的跟商家的有所不同，商品添加了选中改变的监听
        viewHolder.checkBox_pin.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //优先改变自己的状态
                list.get(i).setCheck(isChecked);
                //回调，目的是告诉activity，有人选中状态被改变
                if(mShopCallBackListener!=null){
                    mShopCallBackListener.callBack();
                }
            }
        });
        viewHolder.customCounterView.setData(this,list,i);
        viewHolder.customCounterView.setOnClick(new CustomCounterView.OnClick() {
            @Override
            public void OnClick() {
                  if(mShopCallBackListener!=null){
                      mShopCallBackListener.callBack();
                  }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CheckBox checkBox_pin;
        TextView text_title,text_price;
        ImageView image_pin;
        CustomCounterView customCounterView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            checkBox_pin = itemView.findViewById(R.id.checkbox_pin);
            text_title = itemView.findViewById(R.id.text_title);
            text_price = itemView.findViewById(R.id.text_price);
            image_pin = itemView.findViewById(R.id.image_pin);
            customCounterView = itemView.findViewById(R.id.custom);
        }
    }
    //修改商品的全选和反选
    public void selectRemoveAll(boolean isSelectAll){
        for (Shop.DataBean.ListBean listBean:list){
            listBean.setCheck(isSelectAll);
        }
    }
    private ShopCallBackListener mShopCallBackListener;
    public interface ShopCallBackListener{
        void callBack();
    }
    public void setListener(ShopCallBackListener shopCallBackListener){
        mShopCallBackListener=shopCallBackListener;
    }
}
