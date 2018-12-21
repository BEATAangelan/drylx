package ningjiaxin1.bwie.com.drylx.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.nio.channels.SelectableChannel;
import java.util.ArrayList;
import java.util.List;

import ningjiaxin1.bwie.com.drylx.bean.UserBean;

public class UserDao {
    private Sql sql;
    private SQLiteDatabase database;
    public UserDao(Context context){
        Sql sql = new Sql(context);
        database=sql.getReadableDatabase();
    }
    //添加
    public void insert(String name){
        ContentValues values = new ContentValues();
        values.put("name",name);
        database.insert("user",null,values);
    }
    //查询
    public List<UserBean> select(){
        Cursor query = database.query("user", null, null, null, null, null, null, null);
        List<UserBean> list=new ArrayList<>();
        while (query.moveToNext()){
            String name = query.getString(query.getColumnIndex("name"));
            UserBean bean = new UserBean(name);
            list.add(bean);
        }
        return list;
    }
    //删除
    public void delete(){
        database.delete("user",null,null);
    }
}
