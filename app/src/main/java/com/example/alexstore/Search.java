package com.example.alexstore;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Search extends Fragment implements View.OnClickListener {

    private View view;
    private ImageSlider slider;
    private SearchView searchView;
    private TextView marquee_text, vezi_tot;
    private BDComunicare bdComunicare;
    private ViewPager2 viewPager2;
    private Handler sliderHandler = new Handler();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.search_activity, container, false);
        slider = (ImageSlider) view.findViewById(R.id.slider);
        searchView = view.findViewById(R.id.search_bar);
        viewPager2 = view.findViewById(R.id.viewPagerImageSlider);
        marquee_text = view.findViewById(R.id.marquee_text);
        vezi_tot = view.findViewById(R.id.vezi_tot);
        marquee_text.setSelected(true);
        bdComunicare = new BDComunicare();
        bdComunicare.getPoze_prezentare();
        bdComunicare.getReferinta().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<SlideModel> slideModels = new ArrayList<>();
                for (DataSnapshot ds : snapshot.getChildren()) {

                    slideModels.add(new SlideModel(ds.getValue(String.class), ScaleTypes.FIT));

                }
                slider.setImageList(slideModels);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        bdComunicare.getPoze_multiColor();
        bdComunicare.getReferinta().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {


                List<SliderItem> sliderItems = new ArrayList<>();

                for (DataSnapshot ds : snapshot.getChildren()) {
                    sliderItems.add(new SliderItem(ds.getValue(String.class)));
                }
                viewPager2.setAdapter(new SliderAdapter(sliderItems, viewPager2));
                viewPager2.setClipToPadding(false);
                viewPager2.setClipChildren(false);
                viewPager2.setOffscreenPageLimit(3);
                viewPager2.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);

                CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
                compositePageTransformer.addTransformer(new MarginPageTransformer(40));
                compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
                    @Override
                    public void transformPage(@NonNull View page, float position) {
                        float r = 1 - Math.abs(position);
                        page.setScaleY(0.85f + r * 0.15f);
                    }
                });
                viewPager2.setPageTransformer(compositePageTransformer);
                viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
                    @Override
                    public void onPageSelected(int position) {
                        super.onPageSelected(position);
                        sliderHandler.removeCallbacks(sliderRunnable);
                        sliderHandler.postDelayed(sliderRunnable, 3000);
                    }
                });


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        viewPager2.setOnClickListener(this);
        vezi_tot.setOnClickListener(this);


        Point size = new Point();
        getActivity().getWindowManager().getDefaultDisplay().getSize(size);
        int screenHeight = size.y;
        ViewGroup.LayoutParams params = slider.getLayoutParams();
        params.height = screenHeight - 3 * getStatusBarHeight();
        return view;
    }


    @Override
    public void onClick(View v) {

        if(v==vezi_tot||v==viewPager2) {
            Intent intent = new Intent(getActivity(), Lista_Produse.class);
            startActivity(intent);
            getActivity().getFragmentManager().popBackStack();
        }

    }

    private Runnable sliderRunnable = new Runnable() {
        @Override
        public void run() {
            viewPager2.setCurrentItem(viewPager2.getCurrentItem() + 1);
        }
    };

    public int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }


}
