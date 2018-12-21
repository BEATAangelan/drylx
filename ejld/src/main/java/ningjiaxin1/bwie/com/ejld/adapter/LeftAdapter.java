package ningjiaxin1.bwie.com.ejld.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ningjiaxin1.bwie.com.ejld.R;
import ningjiaxin1.bwie.com.ejld.bean.Left;
import ningjiaxin1.bwie.com.ejld.bean.Right;

public class LeftAdapter extends RecyclerView.Adapter<LeftAdapter.ViewHolder> {
    private Context context;
    private List<Left.DataBean> list;

    public LeftAdapter(Context context) {
        this.context = context;
        list=new ArrayList<>();
    }

    public void setList(List<Left.DataBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public LeftAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context,R.layout.leftlayout,null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull LeftAdapter.ViewHolder viewHolder, final int i) {
        viewHolder.textView.setText(list.get(i).getName());
        viewHolder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(monClickListener!=null){
                    monClickListener.onClick(i,list.get(i).getCid()+"");
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        LinearLayout linearLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.typetitle);
            linearLayout = itemView.findViewById(R.id.liner);
        }
    }
    private onClickListener monClickListener;
    public void setOnClickListener(onClickListener onClickListener){
        monClickListener = onClickListener;
    }
    //接口
    public interface  onClickListener{
        void onClick(int position,String cid);
    }
}
