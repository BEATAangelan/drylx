package ningjiaxin1.bwie.com.drylx.myview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import ningjiaxin1.bwie.com.drylx.R;

public class ViewGroup extends LinearLayout {
    Context mContext;
    public ViewGroup(Context context) {
        super(context);
        mContext=context;
    }

    public ViewGroup(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext=context;
        init();
    }
    private void init(){
        View view = View.inflate(mContext, R.layout.item, null);
    }
}
