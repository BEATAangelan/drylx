package ningjiaxin1.bwie.com.erweima;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.lang.ref.WeakReference;

public class MainActivity extends AppCompatActivity {
    private Button sao;
    private Button sheng;
    private EditText editText;
    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
//    private void init(){
//        sao = findViewById(R.id.sao);
//        sheng = findViewById(R.id.sheng);
//        editText = findViewById(R.id.edit);
//        imageView = findViewById(R.id.image);
//        sao.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
//        sheng.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                createQRCode();
//            }
//        });
//    }
//
//    private void createQRCode() {
//        QRTask qrTask = new QRTask(this, imageView, editText);
//        qrTask.execute(editText.getText().toString());
//    }
//
//    private class QRTask extends AsyncTask<String,Void,Bitmap> {
//        private WeakReference<Context> mcontext;
//        private WeakReference<ImageView> mimageview;
//
//        public QRTask(Context context, ImageView imageView, EditText editText) {
//            mcontext = new WeakReference<>(context);
//            mimageview = new WeakReference<>(imageView);
//        }
//
//        @Override
//        protected Bitmap doInBackground(String... strings) {
//            String str = strings[0];
//            if (TextUtils.isEmpty(str)) {
//                return null;
//            }
//            int size = mcontext.get().getResources().getDimensionPixelOffset(R.dimen.ss);
//            //return QRCodeEncoder.syncEncodeQRCode(str,size);
//
//        }


}
