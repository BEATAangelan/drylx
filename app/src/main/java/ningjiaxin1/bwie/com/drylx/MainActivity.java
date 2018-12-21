package ningjiaxin1.bwie.com.drylx;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ningjiaxin1.bwie.com.drylx.bean.UserBean;
import ningjiaxin1.bwie.com.drylx.myview.Pull;
import ningjiaxin1.bwie.com.drylx.sql.UserDao;

public class MainActivity extends AppCompatActivity {
    private UserDao dao;
    private Pull pull;
    private EditText edit;
    private ImageView image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pull=findViewById(R.id.pull);
        edit = findViewById(R.id.edit);
        image = findViewById(R.id.image_shou);
        dao = new UserDao(this);
        init();
    }
    public void init(){
        List<UserBean> select = dao.select();
        for(int i=0;i<select.size();i++){
            TextView textView = new TextView(MainActivity.this);
            textView.setText(select.get(i).getName());
            pull.addView(textView);
        }
        findViewById(R.id.image_shou).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    TextView textView = new TextView(MainActivity.this);
                    textView.setText(edit.getText());
                    textView.setTextColor(Color.BLUE);
                pull.addView(textView);
                dao.insert(textView.getText().toString());
                Intent intent = new Intent(MainActivity.this, ShopActivity.class);
                startActivity(intent);
            }
        });
    }
}
