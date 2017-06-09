package com.example.rs.greendaodemo;

import android.app.Activity;
import android.app.ListActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.rs.greendaodemo.db.User;
import com.example.rs.greendaodemo.db.GreenDaoManager;
import com.example.rs.greendaodemo.greendao.gen.DaoMaster;
import com.example.rs.greendaodemo.greendao.gen.DaoSession;
import com.example.rs.greendaodemo.greendao.gen.UserDao;

import java.util.ArrayList;
import java.util.List;


public class DaoOpActivity extends Activity {
    private EditText editText;
    private DaoMaster daoMaster;
    private DaoSession daoSession;
    private Cursor cursor;
    private ListView  mUserLV;
    private UserDao mUserDao;
    public static final String TAG = "DaoExample";

    private UserAdapter mUserAdapter;
    private List<User> mUserList = new ArrayList<>();
    long count = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        editText = (EditText) findViewById(R.id.editTextNote);
        initData();
    }

    private void initData() {
        mUserDao = GreenDaoManager.getInstance(this).getSession().getUserDao();
        mUserList = GreenDaoManager.getInstance(this).getSession().getUserDao().queryBuilder().build().list();
        count = mUserList.size();
        mUserAdapter = new UserAdapter(this, mUserList);
        mUserAdapter.setListener(new UserAdapter.UserOnClickListener() {
            @Override
            public void onClick(int id,User user) {
                if(id == R.id.update){
                    updateUser(user.serviceName, editText.getText().toString());
                } else if(id == R.id.delete){
                    deleteUser(user.serviceName);
                }
            }
        });
        mUserLV = (ListView) findViewById(R.id.lv);
        mUserLV.setAdapter(mUserAdapter);
    }

    /**
     * Button 点击的监听事件
     *
     * @param view
     */
    public void onMyButtonClick(View view) {
        switch (view.getId()) {
            case R.id.buttonAdd:
                insertUser(count,editText.getText().toString());
                break;
            case R.id.buttonSearch:
                queryUser(editText.getText().toString());
                break;
            default:
                Log.d(TAG, "what has gone wrong ?");
                break;
        }
    }


    //查询
    private void queryUser(String name) {
        List result = mUserDao.queryBuilder()
                .where(UserDao.Properties.ServiceName.eq(name)).build().list();

        Toast.makeText(this, "查询到：" + result.size() + "条结果",
                Toast.LENGTH_SHORT).show();
    }

    //更新
    private void updateUser(String prevName, String newName) {
        User findUser = mUserDao.queryBuilder().where(UserDao.Properties.ServiceName.eq(prevName)).build().unique();
        if (findUser != null) {
            findUser.setServiceName(newName);
            mUserDao.update(findUser);
            Toast.makeText(this, "修改成功", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "用户不存在", Toast.LENGTH_SHORT).show();
        }

        refreshListView();
    }

    //删除
    private void deleteUser(String name) {
        User findUser = mUserDao.queryBuilder()
                .where(UserDao.Properties.ServiceName.eq(name)).build().unique();
        if(findUser != null) {
            mUserDao.deleteByKey(findUser.getServiceId());
            Toast.makeText(this, "删除成功", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "用户不存在", Toast.LENGTH_SHORT).show();
        }

        refreshListView();
    }

    //插入
    private void insertUser(Long id, String name) {
        User user = new User(id, name);
        mUserDao.insertOrReplace(user);
        count++;
        refreshListView();
    }

    private void refreshListView(){
        mUserList = mUserDao.queryBuilder().build().list();
        mUserAdapter.updateUsers(mUserList);
    }


}
