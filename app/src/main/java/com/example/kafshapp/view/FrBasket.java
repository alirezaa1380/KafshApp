package com.example.kafshapp.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kafshapp.adapter.AdapterBasket;
import com.example.kafshapp.AppDatabse;
import com.example.kafshapp.Post;
import com.example.kafshapp.R;
import com.example.kafshapp.RoomDao;

import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class FrBasket extends Fragment {
    RecyclerView recyclerView;
    ImageView imageViewSearch
            ,imageViewBack;

    EditText editTextSearch;
    TextView textViewSearch;
    AdapterBasket adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_fr_basket, container, false);
        initialaize(view);

        //save item in basketBuy
        RoomDao dao= AppDatabse.getAppDatabse(getContext()).roomDao();
        dao.GetDataFromDatabaseBasket()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<Post>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@NonNull List<Post> list) {
                        adapter=new AdapterBasket(getContext(),list);
                        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false));
                        recyclerView.setAdapter(adapter);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }
                });

        imageViewSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textViewSearch.setVisibility(View.GONE);
                imageViewBack.setVisibility(View.VISIBLE);
                editTextSearch.setVisibility(View.VISIBLE);
                imageViewSearch.setVisibility(View.GONE);
            }
        });

        imageViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageViewBack.setVisibility(View.GONE);
                textViewSearch.setVisibility(View.VISIBLE);
                editTextSearch.setVisibility(View.GONE);
                imageViewSearch.setVisibility(View.VISIBLE);
            }
        });


        //Search

        editTextSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter.filter(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        return view;
    }

    private void initialaize(View view) {
        recyclerView=view.findViewById(R.id.recycler_basket);
        imageViewSearch=view.findViewById(R.id.imv_search_basket);
        imageViewBack=view.findViewById(R.id.imv_back__basket);
        textViewSearch=view.findViewById(R.id.txt_search_basket);
        editTextSearch=view.findViewById(R.id.edt_serach_basket);
    }
}