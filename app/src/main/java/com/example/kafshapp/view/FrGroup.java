package com.example.kafshapp.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kafshapp.adapter.AdapterGroup;
import com.example.kafshapp.model.ApiClient;
import com.example.kafshapp.Post;
import com.example.kafshapp.R;
import com.example.kafshapp.model.RetrofitRequast;

import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class FrGroup extends Fragment {
    RecyclerView recyclerViewMan,recyclerViewWoman,recyclerViewBaby;
    RetrofitRequast requast;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_fr_group, container, false);
        Initialize(view);



        GetDataFromServer();


        return  view;
    }

    private void GetDataFromServer() {

        requast= ApiClient.getRetrofit("https://www.kafshapp.ir/").create(RetrofitRequast.class);
        requast.GetDataMan()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<Post>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@NonNull List<Post> list) {
                        AdapterGroup adapter=new AdapterGroup(getContext(),list) ;
                        recyclerViewMan.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false));
                        recyclerViewMan.setAdapter(adapter);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }
                });

        requast.GetDataWoman()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<Post>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@NonNull List<Post> list) {
                        AdapterGroup adapter=new AdapterGroup(getContext(),list) ;
                        recyclerViewWoman.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false));
                        recyclerViewWoman.setAdapter(adapter);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }
                });

        requast.GetDataBaby()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<Post>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@NonNull List<Post> list) {
                        AdapterGroup adapter=new AdapterGroup(getContext(),list) ;
                        recyclerViewBaby.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false));
                        recyclerViewBaby.setAdapter(adapter);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }
                });

    }

    public void Initialize(View view){
        recyclerViewMan =view.findViewById(R.id.recyclerView_Man);
        recyclerViewWoman=view.findViewById(R.id.recyclerView_woman);
        recyclerViewBaby=view.findViewById(R.id.Recycler_Baby);

    }
}