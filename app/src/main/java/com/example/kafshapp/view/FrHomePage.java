package com.example.kafshapp.view;

import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.example.kafshapp.adapter.Adapter;
import com.example.kafshapp.model.ApiClient;
import com.example.kafshapp.Post;
import com.example.kafshapp.R;
import com.example.kafshapp.model.RetrofitRequast;
import com.example.kafshapp.model.ShearedPerfernce;

import java.util.HashMap;
import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class FrHomePage extends Fragment implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener {
    EditText editTextSearch;
    ImageView imageViewSearch
             ,imageViewBack
            ,imageViewDiscommend;
    SliderLayout sliderLayout;
    HashMap<String, String> Hash;
    RecyclerView recyclerView;
    RetrofitRequast requast;
    ProgressBar progressBar;
    TextView textViewDiscommend,textView;
    CardView cardView;
    Adapter adapter;
    Disposable disposable;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_fr_home_page, container, false);
        Initialize(view);
        OnSearch();
        SlidersetUp();
        GetPostFromServer();


        return view;
    }


    public void Initialize(View view){
        editTextSearch=view.findViewById(R.id.edr_serach);
        imageViewSearch=view.findViewById(R.id.imv_search);
        imageViewBack=view.findViewById(R.id.imv_back);
        sliderLayout=view.findViewById(R.id.sliderLayout);
        recyclerView=view.findViewById(R.id.recy);
        progressBar=view.findViewById(R.id.progressBarRecycler);
        imageViewDiscommend=view.findViewById(R.id.imv_discommend);
        textViewDiscommend=view.findViewById(R.id.txt_discommend);
        textView=view.findViewById(R.id.txt2);
        cardView=view.findViewById(R.id.cardView1);
    }

    public void OnSearch(){
        imageViewSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextSearch.setVisibility(View.VISIBLE);
                imageViewBack.setVisibility(View.VISIBLE);
                imageViewSearch.setVisibility(View.GONE);
            }
        });


        imageViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextSearch.setVisibility(View.GONE);
                imageViewBack.setVisibility(View.GONE);
                imageViewSearch.setVisibility(View.VISIBLE);
            }
        });


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
    }

    private void SlidersetUp() {
        Hash=new HashMap<>();
        Hash.put("Nike","http://www.kafshapp.ir/images/slider_image_1.jpg");
        Hash.put("Adidas","http://www.kafshapp.ir/images/banner_addidas3.jpg");
        Hash.put("Addidas","http://www.kafshapp.ir/images/banner_addidas4.jpg");



        for (String name:Hash.keySet()) {
            TextSliderView textSliderView=new TextSliderView(getContext());
            textSliderView.description(name).image(Hash.get(name)).setScaleType(BaseSliderView.ScaleType.Fit);
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle().putString("extra",name);
            sliderLayout.addSlider(textSliderView);
        }
        sliderLayout.setPresetTransformer(SliderLayout.Transformer.Foreground2Background);
        sliderLayout.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        sliderLayout.setCustomAnimation(new DescriptionAnimation());
        sliderLayout.setDuration(2500);


    }

    private void GetPostFromServer() {

        requast= ApiClient.getRetrofit("https://www.kafshapp.ir/").create(RetrofitRequast.class);
        requast.GetDataPost()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<Post>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        disposable=d;
                    }

                    @Override
                    public void onSuccess(@NonNull List<Post> list) {
                        progressBar.setVisibility(View.GONE);
                        adapter=new Adapter(getContext(),list);
                        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false));
                        recyclerView.setAdapter(adapter);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        progressBar.setVisibility(View.GONE);
                        imageViewDiscommend.setVisibility(View.VISIBLE);
                        textViewDiscommend.setVisibility(View.VISIBLE);
                        textView.setVisibility(View.GONE);
                        sliderLayout.setVisibility(View.GONE);
                        cardView.setVisibility(View.GONE);
                    }
                });
    }

    @Override
    public void onSliderClick(BaseSliderView slider) {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onStart() {
        super.onStart();
        ShearedPerfernce shearedPerfernce=new ShearedPerfernce(getContext());
        String name=shearedPerfernce.getFirstName();
        String password=shearedPerfernce.getLastName();
        String phone=shearedPerfernce.getPhone();
        if (name.length()==0){
            Navigation.findNavController(getView()).navigate(R.id.action_frHomePage_to_navigation22);
        }

    }

    @Override
    public void onDetach() {
        super.onDetach();
        disposable.dispose();
    }
}