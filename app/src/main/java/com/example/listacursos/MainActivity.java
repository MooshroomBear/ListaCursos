package com.example.listacursos;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import androidx.wear.widget.WearableLinearLayoutManager;
import androidx.wear.widget.WearableRecyclerView;

import com.example.listacursos.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends Activity {

    private ActivityMainBinding binding;

    private List <ItemList>items=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        WearableRecyclerView recyclerView=(WearableRecyclerView)findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setEdgeItemsCenteringEnabled(true);
        WearableLinearLayoutManager linearLayoutManager=new WearableLinearLayoutManager(this);
        linearLayoutManager.setOrientation(WearableLinearLayoutManager.VERTICAL);
        linearLayoutManager.setLayoutCallback(new CustomScrollingLayoutCallback());

        //binding = ActivityMainBinding.inflate(getLayoutInflater());
        //setContentView(binding.getRoot());


    }
}

class CustomScrollingLayoutCallback extends WearableLinearLayoutManager.LayoutCallback{
    private static final float MAX_ICON_PROGRESS=0.65f;
    private float progressToCenter;

    @Override
    public void onLayoutFinished(View child, RecyclerView parent){
        float centerOffset=
                ((float)child.getHeight()/2.0f/(float)parent.getHeight());
        float yRelativeToCenterOffset=
                (child.getY()/parent.getHeight())+centerOffset;

        progressToCenter=Math.abs(0.5f-yRelativeToCenterOffset);
        progressToCenter=Math.min(progressToCenter,MAX_ICON_PROGRESS);
        child.setScaleX(1-progressToCenter);
        child.setScaleY(1-progressToCenter);
    }
}