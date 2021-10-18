package com.example.kafshapp.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.kafshapp.adapter.AdapterComment;
import com.example.kafshapp.model.ApiClient;
import com.example.kafshapp.model.Comment;
import com.example.kafshapp.R;
import com.example.kafshapp.model.RetrofitRequast;
import com.example.kafshapp.model.ShearedPerfernce;

import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class FrComment extends Fragment {

    EditText editTextComment;
    ImageView imageViewSave;
    RetrofitRequast requast;
    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_fr_comment, container, false);
        Initialize(view);

        //Get comment from server
        requast= ApiClient.getRetrofit("https://www.kafshapp.ir/").create(RetrofitRequast.class);
        requast.GetDataComment(getArguments().getInt("id"))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<Comment>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@NonNull List<Comment> comments) {
                        AdapterComment adapterComment=new AdapterComment(getContext(),comments);
                        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false));
                        recyclerView.setAdapter(adapterComment);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }
                });


        //Save comment in server
        imageViewSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Comment comment=new Comment();
                comment.setComment(editTextComment.getText().toString());
                comment.setUserid(getArguments().getInt("id"));
                ShearedPerfernce shearedPerfernce=new ShearedPerfernce(getContext());
                comment.setName(shearedPerfernce.getFirstName());
//        comment.setCommentLike(false);
                requast=ApiClient.getRetrofit("https://www.kafshapp.ir/").create(RetrofitRequast.class);
                requast.AddComment(comment)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new SingleObserver<String>() {
                            @Override
                            public void onSubscribe(@NonNull Disposable d) {

                            }

                            @Override
                            public void onSuccess(@NonNull String s) {
                                Navigation.findNavController(v).navigate(R.id.action_frComment_to_frHomePage);
                                Toast.makeText(getContext(), "نظر شما ثبت شد", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onError(@NonNull Throwable e) {
                            }
                        });

            }
        });



        return view;
    }

    private void Initialize(View view) {
        editTextComment=view.findViewById(R.id.edt_comment);
        imageViewSave=view.findViewById(R.id.imv_save_comment);
        recyclerView=view.findViewById(R.id.recyclerView_comment);
    }

}