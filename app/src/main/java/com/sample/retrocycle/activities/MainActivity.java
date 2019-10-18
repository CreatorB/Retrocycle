package com.sample.retrocycle.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.sample.retrocycle.R;
import com.sample.retrocycle.adapters.UserAdapter;
import com.sample.retrocycle.models.mUser.GUsers;
import com.sample.retrocycle.utils.retrofit.RetroClient;
import com.sample.retrocycle.utils.retrofit.RetroInterface;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    RetroInterface mInterface;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    public static MainActivity ma;
    private List<GUsers> usersList;
    private ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mInterface = RetroClient.getClient().create(RetroInterface.class);
        ma=this;

        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading");
        pDialog.setIcon(R.mipmap.ic_launcher);
        pDialog.setCancelable(false);

        initData();
    }

    private void initData() {
        pDialog.show();
        Call <List<GUsers>> call = mInterface.getUsers();
        call.enqueue(new Callback<List<GUsers>>() {
            @Override
            public void onResponse(Call<List<GUsers>> call, Response<List<GUsers>> response) {
                pDialog.dismiss();
                if(response.isSuccessful()){
                    usersList = response.body();
                    mAdapter = new UserAdapter(usersList);
                    mRecyclerView.setAdapter(mAdapter);
                    mAdapter.notifyDataSetChanged();
                }else {
                    Toast.makeText(ma, "Sorry, No Data Available from Server", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<GUsers>> call, Throwable t) {
                pDialog.dismiss();
                Toast.makeText(ma, t.getMessage(), Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });
    }
}
