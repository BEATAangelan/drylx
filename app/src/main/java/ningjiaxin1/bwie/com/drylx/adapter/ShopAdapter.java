package ningjiaxin1.bwie.com.drylx.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ningjiaxin1.bwie.com.drylx.R;
import ningjiaxin1.bwie.com.drylx.bean.Shop;

public class ShopAdapter extends RecyclerView.Adapter<ShopAdapter.ViewHolder> {
    private Context context;
    private List<Shop.DataBean> list;

    public ShopAdapter(Context context) {
        this.context = context;
        list=new ArrayList<>();
    }

    public void setList(List<Shop.DataBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ShopAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LinearLayout.inflate(context, R.layout.shangjia, null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(@NonNull final ShopAdapter.ViewHolder viewHolder, final int i) {
      viewHolder.textView.setText(list.get(i).getSellerName());
        final ProductsAdapter productsAdapter = new ProductsAdapter(context,list.get(i).getList());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        viewHolder.recyclerView_pin.setLayoutManager(linearLayoutManager);
        viewHolder.recyclerView_pin.setAdapter(productsAdapter);
        viewHolder.check_jia.setChecked(list.get(i).isCheck());
        productsAdapter.setListener(new ProductsAdapter.ShopCallBackListener() {
            @Override
            public void callBack() {
                if(mShopCallBackListener!=null){
                    mShopCallBackListener.callBack(list);
                }
                List<Shop.DataBean.ListBean> listBeans=list.get(i).getList();
                boolean isAllChecked=true;
                for(Shop.DataBean.ListBean bean:listBeans){
                    if(!bean.isCheck()){
                        isAllChecked=false;
                        break;
                    }
                }
                viewHolder.check_jia.setChecked(isAllChecked);
                list.get(i).setCheck(isAllChecked);
            }
        });
        viewHolder.check_jia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.get(i).setCheck(viewHolder.check_jia.isChecked());
                productsAdapter.selectRemoveAll(viewHolder.check_jia.isChecked());
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        RecyclerView recyclerView_pin;
        TextView textView;
        CheckBox check_jia;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            recyclerView_pin = itemView.findViewById(R.id.recy_pin);
            textView = itemView.findViewById(R.id.text_jia);
            check_jia = itemView.findViewById(R.id.checkbox_jia);
        }
    }
    private ShopCallBackListener mShopCallBackListener;

    public void setListener(ShopCallBackListener listener) {
        this.mShopCallBackListener = listener;
    }

    public interface ShopCallBackListener {
        void callBack(List<Shop.DataBean> list);
    }
}
