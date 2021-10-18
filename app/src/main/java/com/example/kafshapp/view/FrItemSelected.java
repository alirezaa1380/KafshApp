package com.example.kafshapp.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.example.kafshapp.model.ApiClient;
import com.example.kafshapp.AppDatabse;
import com.example.kafshapp.model.Comment;
import com.example.kafshapp.Post;
import com.example.kafshapp.R;
import com.example.kafshapp.model.RetrofitRequast;
import com.example.kafshapp.RoomDao;

import java.util.HashMap;
import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class FrItemSelected extends Fragment implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener {
    TextView textViewItemColor1
            ,textViewItemColor2
            ,textViewItemColor3
            ,textViewItemColor4;

    TextView textViewsize1,
            textViewsize2,
            textViewsize3,
            textViewsize4,
            textViewsize5,
            textViewsize6;


    TextView textViewInformationItem
            ,textViewPrice
            ,textViewOff
            ,textViewSumPrice;

    TextView textViewComment
            ,textView_name_item_selcted1
            ,textView_name_item_selcted2
            ,textView_title_item_selcted1
            ,textView_title_item_selcted2;

    SliderLayout sliderLayout;
    HashMap<String, String> Hash;
    String image;
    String colorApplay;
    int sizeApplay;
    Button buttonAddToBasket;
    int id;
    int idGroup;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fr_item_selected, container, false);
        Initialize(view);





        //Get data from adapter
       String title= getArguments().getString("title");
       image=getArguments().getString("image");
       String color=getArguments().getString("color");
       String color1=getArguments().getString("color1");
       String color2=getArguments().getString("color2");
       String color3=getArguments().getString("color3");
       String colornumber1=getArguments().getString("colornumber1");
       String colornumber2=getArguments().getString("colornumber2");
       String colornumber3=getArguments().getString("colornumber3");
       String colornumber4=getArguments().getString("colornumber4");
       String price=getArguments().getString("price");
       String off=getArguments().getString("off");
       String sumPrice=getArguments().getString("sumprice");
       id=getArguments().getInt("id");
       idGroup=getArguments().getInt("id_group");
       SlidersetUp();


       //Checked login from page home and page group
       Bundle bundle=new Bundle();
        if (id>idGroup){
            RetrofitRequast requast= ApiClient.getRetrofit("https://www.kafshapp.ir/").create(RetrofitRequast.class);
            requast.GetDataComment(id)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new SingleObserver<List<Comment>>() {
                        @Override
                        public void onSubscribe(@NonNull Disposable d) {

                        }

                        @Override
                        public void onSuccess(@NonNull List<Comment> list) {
                            List<Comment> list1=list;
                            textView_name_item_selcted1.setText(list1.get(0).getName());
                            textView_title_item_selcted1.setText(list1.get(0).getComment());

                            try {
                                textView_name_item_selcted2.setText(list1.get(1).getName());
                                textView_title_item_selcted2.setText(list1.get(1).getComment());
                            }catch (Exception e){
                            }

                        }

                        @Override
                        public void onError(@NonNull Throwable e) {

                        }
                    });

        }else {
            RetrofitRequast requast=ApiClient.getRetrofit("https://www.kafshapp.ir/").create(RetrofitRequast.class);
            requast.GetDataComment(idGroup)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new SingleObserver<List<Comment>>() {
                        @Override
                        public void onSubscribe(@NonNull Disposable d) {

                        }

                        @Override
                        public void onSuccess(@NonNull List<Comment> list) {
                            List<Comment> list1=list;
                            textView_name_item_selcted1.setText(list1.get(0).getName());
                            textView_title_item_selcted1.setText(list1.get(0).getComment());

                            try {
                                textView_name_item_selcted2.setText(list1.get(1).getName());
                                textView_title_item_selcted2.setText(list1.get(1).getComment());
                            }catch (Exception e){
                            }

                        }

                        @Override
                        public void onError(@NonNull Throwable e) {

                        }
                    });
        }




        //Set backgrand and title textviewSize and textviewColor
       textViewInformationItem.setText(title);
       textViewPrice.setText(price);
       textViewOff.setText(off);
       textViewItemColor1.setText(color);
       textViewItemColor2.setText(color1);
       textViewItemColor3.setText(color2);
       textViewItemColor4.setText(color3);
       textViewSumPrice.setText(sumPrice);

       if (colornumber1.equals("1")){
           textViewItemColor1.setBackgroundResource(R.drawable.back_color_and_size);
       }

        if (colornumber2.equals("1")){
            textViewItemColor2.setBackgroundResource(R.drawable.back_color_and_size);
        }

        if (colornumber3.equals("1")){
            textViewItemColor3.setBackgroundResource(R.drawable.back_color_and_size);
        }

        if (colornumber4.equals("1")){
            textViewItemColor4.setBackgroundResource(R.drawable.back_color_and_size);
        }

        textViewItemColor1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (colornumber1.equals("0")){
                    Toast.makeText(getContext(), "این رنگ در فروشگاه موجود نیست", Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(getContext(), "رنگ"+" "+color+" "+"انتخاب شد", Toast.LENGTH_SHORT).show();
                    colorApplay=color;
                }
            }
        });

        textViewItemColor2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (colornumber2.equals("0")){
                    Toast.makeText(getContext(), "این رنگ در فروشگاه موجود نیست", Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(getContext(), "رنگ"+" "+color1+" "+"انتخاب شد", Toast.LENGTH_SHORT).show();
                    colorApplay=color1;
                }
            }
        });


        textViewItemColor3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (colornumber3.equals("0")){
                    Toast.makeText(getContext(), "این رنگ در فروشگاه موجود نیست", Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(getContext(),  "رنگ"+" "+color2+" "+"انتخاب شد", Toast.LENGTH_SHORT).show();
                    colorApplay=color2;
                }
            }
        });

        textViewItemColor4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (colornumber4.equals("0")){
                    Toast.makeText(getContext(), "این رنگ در فروشگاه موجود نیست", Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(getContext(),  "رنگ"+" "+color3+" "+"انتخاب شد", Toast.LENGTH_SHORT).show();
                    colorApplay=color3;
                }
            }
        });

        textViewsize1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textViewsize1.setBackgroundResource(R.drawable.back_color_and_size);
                textViewsize2.setBackgroundResource(R.drawable.back_color_and_sizee);
                textViewsize3.setBackgroundResource(R.drawable.back_color_and_sizee);
                textViewsize4.setBackgroundResource(R.drawable.back_color_and_sizee);
                textViewsize5.setBackgroundResource(R.drawable.back_color_and_sizee);
                textViewsize6.setBackgroundResource(R.drawable.back_color_and_sizee);
                sizeApplay= Integer.parseInt(textViewsize1.getText().toString());

            }
        });


        textViewsize2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textViewsize1.setBackgroundResource(R.drawable.back_color_and_sizee);
                textViewsize2.setBackgroundResource(R.drawable.back_color_and_size);
                textViewsize3.setBackgroundResource(R.drawable.back_color_and_sizee);
                textViewsize4.setBackgroundResource(R.drawable.back_color_and_sizee);
                textViewsize5.setBackgroundResource(R.drawable.back_color_and_sizee);
                textViewsize6.setBackgroundResource(R.drawable.back_color_and_sizee);
                sizeApplay= Integer.parseInt(textViewsize2.getText().toString());

            }
        });


        textViewsize3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textViewsize1.setBackgroundResource(R.drawable.back_color_and_sizee);
                textViewsize2.setBackgroundResource(R.drawable.back_color_and_sizee);
                textViewsize3.setBackgroundResource(R.drawable.back_color_and_size);
                textViewsize4.setBackgroundResource(R.drawable.back_color_and_sizee);
                textViewsize5.setBackgroundResource(R.drawable.back_color_and_sizee);
                textViewsize6.setBackgroundResource(R.drawable.back_color_and_sizee);
                sizeApplay= Integer.parseInt(textViewsize3.getText().toString());

            }
        });


        textViewsize4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textViewsize1.setBackgroundResource(R.drawable.back_color_and_sizee);
                textViewsize2.setBackgroundResource(R.drawable.back_color_and_sizee);
                textViewsize3.setBackgroundResource(R.drawable.back_color_and_sizee);
                textViewsize4.setBackgroundResource(R.drawable.back_color_and_size);
                textViewsize5.setBackgroundResource(R.drawable.back_color_and_sizee);
                textViewsize6.setBackgroundResource(R.drawable.back_color_and_sizee);
                sizeApplay= Integer.parseInt(textViewsize4.getText().toString());

            }
        });


        textViewsize5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textViewsize1.setBackgroundResource(R.drawable.back_color_and_sizee);
                textViewsize2.setBackgroundResource(R.drawable.back_color_and_sizee);
                textViewsize3.setBackgroundResource(R.drawable.back_color_and_sizee);
                textViewsize4.setBackgroundResource(R.drawable.back_color_and_sizee);
                textViewsize5.setBackgroundResource(R.drawable.back_color_and_size);
                textViewsize6.setBackgroundResource(R.drawable.back_color_and_sizee);
                sizeApplay= Integer.parseInt(textViewsize5.getText().toString());
            }
        });

        textViewsize6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textViewsize1.setBackgroundResource(R.drawable.back_color_and_sizee);
                textViewsize2.setBackgroundResource(R.drawable.back_color_and_sizee);
                textViewsize3.setBackgroundResource(R.drawable.back_color_and_sizee);
                textViewsize4.setBackgroundResource(R.drawable.back_color_and_sizee);
                textViewsize5.setBackgroundResource(R.drawable.back_color_and_sizee);
                textViewsize6.setBackgroundResource(R.drawable.back_color_and_size);
                sizeApplay= Integer.parseInt(textViewsize6.getText().toString());
            }
        });




        buttonAddToBasket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (colorApplay==null){
                    Toast.makeText(getContext(), "رنگ مورد نظر را انتخاب کنید", Toast.LENGTH_SHORT).show();
                }else if (sizeApplay==0){
                    Toast.makeText(getContext(), "سایز مورد نظر را انتخاب کنبد", Toast.LENGTH_SHORT).show();
                }else {
                    Post post=new Post();
                    post.setOff(Integer.parseInt(off));
                    post.setColor(colorApplay);
                    post.setColor3(color1);
                    post.setColor4(color2);
                    post.setColor5(color3);
                    post.setColornumber(Integer.parseInt(sumPrice));
                    post.setColornumber2(Integer.parseInt(colornumber2));
                    post.setColornumber3(Integer.parseInt(colornumber3));
                    post.setColornumber4(Integer.parseInt(colornumber4));
                    post.setImage(image);
                    post.setPrice(Integer.parseInt(price));
                    post.setTitle(title);
                    post.setType(sizeApplay);
                    RoomDao dao= AppDatabse.getAppDatabse(getContext()).roomDao();
                    dao.AddFromDatabaseBasket(post);
                    Toast.makeText(getContext(), "با موفقیت به سبد خرید اضافه شد", Toast.LENGTH_LONG).show();
                }

            }
        });

        textViewComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle=new Bundle();

                if (id>idGroup){
                    bundle.putInt("id",id);
                }else {
                    bundle.putInt("id",idGroup);
                }
                Navigation.findNavController(v).navigate(R.id.action_frItemSelected_to_frComment,bundle);
            }
        });



        return view;
    }

    public void Initialize(View view){
        textViewInformationItem=view.findViewById(R.id.txt_information_item);
        textViewItemColor1=view.findViewById(R.id.txt_item_color1);
        textViewItemColor2=view.findViewById(R.id.txt_item_color2);
        textViewItemColor3=view.findViewById(R.id.txt_item_color3);
        textViewItemColor4=view.findViewById(R.id.txt_item_color4);
        textViewOff=view.findViewById(R.id.txt_item_off);
        textViewPrice=view.findViewById(R.id.txt_item_price);
        textViewSumPrice=view.findViewById(R.id.txt_item_price_sum);
        sliderLayout=view.findViewById(R.id.sliderLayout2);
        textViewsize1=view.findViewById(R.id.txt_size_shose1);
        textViewsize2=view.findViewById(R.id.txt_size_shose2);
        textViewsize3=view.findViewById(R.id.txt_size_shose3);
        textViewsize4=view.findViewById(R.id.txt_size_shose4);
        textViewsize5=view.findViewById(R.id.txt_size_shose5);
        textViewsize6=view.findViewById(R.id.txt_size_shose6);
        buttonAddToBasket=view.findViewById(R.id.buttonAddToBasket);
        textViewComment=view.findViewById(R.id.txt_view_comment);
        textView_title_item_selcted1=view.findViewById(R.id.txt_title_item_selcted1);
        textView_title_item_selcted2=view.findViewById(R.id.txt_title_item_selcted2);
        textView_name_item_selcted1=view.findViewById(R.id.txt_name_item_selcted1);
        textView_name_item_selcted2=view.findViewById(R.id.txt_name_item_selcted2);
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
    private void SlidersetUp() {
        Hash=new HashMap<>();
        Hash.put("",image);


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
        sliderLayout.setDuration(5000);


    }
}