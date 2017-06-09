package com.example.rs.greendaodemo;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rs.greendaodemo.db.GreenDaoManager;
import com.example.rs.greendaodemo.db.User;
import com.example.rs.greendaodemo.greendao.gen.UserDao;

import java.util.List;

/**
 * Created by rongsheng1 on 2017/3/16.
 */

class UserAdapter extends BaseAdapter {
    private Context context;
    private List<User> users;
    private UserOnClickListener listener;

    public interface UserOnClickListener {
        void onClick(int id, User user);
    }

    public void setListener(UserOnClickListener listener) {
        this.listener = listener;
    }

    public UserAdapter(Context context, List<User> users) {
        this.context = context;
        this.users = users;
    }

    @Override
    public int getCount() {
        return users == null ? 0 : users.size();
    }

    @Override
    public Object getItem(int position) {
        return users.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public void updateUsers(List<User> users) {
        this.users = users;
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_layout, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        final User user = (User) getItem(position);
        holder.idTv.setText("" + user.getServiceId());
        holder.nameTv.setText(user.getServiceName());
        holder.update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onClick(R.id.update, user);
                }
            }
        });
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onClick(R.id.delete, user);
                }
            }
        });
        return convertView;
    }


    private class ViewHolder {
        private TextView idTv;
        private TextView nameTv;
        private Button update;
        private Button delete;

        public ViewHolder(View v) {
            idTv = (TextView) v.findViewById(R.id.id);
            nameTv = (TextView) v.findViewById(R.id.name);
            update = (Button) v.findViewById(R.id.update);
            delete = (Button) v.findViewById(R.id.delete);

        }
    }
}
