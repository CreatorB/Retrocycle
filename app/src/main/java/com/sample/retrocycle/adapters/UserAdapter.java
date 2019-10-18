package com.sample.retrocycle.adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.sample.retrocycle.R;
import com.sample.retrocycle.models.mUser.GUsers;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.MyViewHolder>{
    List<GUsers> mUser;

    public UserAdapter(List <GUsers> UserList) {
        mUser = UserList;
    }

    @Override
    public MyViewHolder onCreateViewHolder (ViewGroup parent, int viewType){
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_row_users, parent, false);
        MyViewHolder mViewHolder = new MyViewHolder(mView);
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder (MyViewHolder holder,final int position){
        holder.mTextViewNama.setText("Name : " + mUser.get(position).getName());
        holder.mTextViewCompany.setText("Company of : " + mUser.get(position).getCompany().getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent mIntent = new Intent(view.getContext(), Details.class);
//                mIntent.putExtra("Name", mUser.get(position).getName());
//                view.getContext().startActivity(mIntent);
                Toast.makeText(view.getContext(), "My Email : "+mUser.get(position).getEmail(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount () {
        return mUser.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextViewNama, mTextViewCompany;

        public MyViewHolder(View itemView) {
            super(itemView);
            mTextViewNama = (TextView) itemView.findViewById(R.id.tvNama);
            mTextViewCompany = (TextView) itemView.findViewById(R.id.tvCompany);
        }
    }
}