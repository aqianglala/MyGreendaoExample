package com.example.mygreendao;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.mygreendao.db.User;
import com.example.mygreendao.db.helper.DbManager;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tv_info;
    private DbManager mDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.add).setOnClickListener(this);
        findViewById(R.id.delete).setOnClickListener(this);
        findViewById(R.id.update).setOnClickListener(this);
        findViewById(R.id.query_all).setOnClickListener(this);
        findViewById(R.id.query_name_eq_1).setOnClickListener(this);

        tv_info = findViewById(R.id.info);
        mDb = ((App)getApplication()).getDb();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add:
                boolean insert = mDb.insert(new User("1"));
                tv_info.setText(insert ? "插入成功" : "插入失败");
                break;
            case R.id.update:
                List<User> byName = mDb.getByName("1");
                if (byName != null && byName.size() > 0) {
                    User user = byName.get(0);
                    user.setName("2");
                    mDb.update(user);
                    tv_info.setText("更新成功");
                } else {
                    tv_info.setText("找不到 name = “1” 的用户");
                }
                break;
            case R.id.query_all:
                StringBuilder info = new StringBuilder();
                List<User> all = mDb.getAll();
                for (User user: all) {
                    info.append(user.getName());
                    info.append("\n");
                }
                tv_info.setText(info.toString());
                break;
            case R.id.query_name_eq_1:
                List<User> byName1 = mDb.getByName("1");
                tv_info.setText(byName1 != null && byName1.size() > 0 ? "找到了":"找不到 name = “1” 的用户");
                break;
            case R.id.delete:
                List<User> byName2 = mDb.getByName("1");
                if (byName2 != null && byName2.size() > 0) {
                    mDb.delete(byName2.get(0));
                    tv_info.setText("删除成功");
                } else {
                    tv_info.setText("找不到 name = “1” 的用户");
                }
                break;
        }
    }
}
