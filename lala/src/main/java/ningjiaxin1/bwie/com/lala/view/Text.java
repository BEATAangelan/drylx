package ningjiaxin1.bwie.com.lala.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import ningjiaxin1.bwie.com.lala.R;

@SuppressLint("AppCompatCustomView")
public class Text extends LinearLayout {
    Context mContext;
    Button button,title_bar_right;
    EditText title_bar_title;
    public Text(Context context) {
        super(context);
        initView();
    }

    public Text(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public Text(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    public void initView(){
        View view = View.inflate(mContext, R.layout.activity_main, null);
        button = view.findViewById(R.id.title_bar_left);
        title_bar_right = view.findViewById(R.id.title_bar_right);
        title_bar_title = view.findViewById(R.id.title_bar_title);
        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(monClickCallBack!=null){
                    monClickCallBack.getdata(title_bar_title.getText().toString());
                }
            }
        });
        addView(view);
    }
    //定义接口回调
    public void setOnClickCallBack(OnClickCallBack onClickCallBack){
        monClickCallBack=onClickCallBack;
    }
    OnClickCallBack monClickCallBack;
    public interface OnClickCallBack{
        void getdata(String str);
    }
}
