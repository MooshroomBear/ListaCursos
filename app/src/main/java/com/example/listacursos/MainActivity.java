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
import java.util.List;

public class MainActivity extends Activity {

    private ActivityMainBinding binding;

    private List<ItemList> items=new ArrayList<>();

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

        items.add(new ItemList(R.drawable.flutter,"Flutter","Curso de flutter"));
        items.add(new ItemList(R.drawable.java,"Java","Curso de java"));
        items.add(new ItemList(R.drawable.php,"PHP","Curso de php"));
        items.add(new ItemList(R.drawable.javascript,"JavaScript","Curso de javascript"));
        items.add(new ItemList(R.drawable.cplus,"C#","Curso de c#"));

        //binding = ActivityMainBinding.inflate(getLayoutInflater());
        //setContentView(binding.getRoot());

        //Items

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

class ItemList{
    final private int imageItem;
    final private String nameItem;
    final private String description;
    public ItemList(int _imageItem,String _nameItem,String _description){
        this.imageItem=_imageItem;
        this.nameItem=_nameItem;
        this.description=_description;
    }
    public int getImageItem(){return imageItem;}
    public String getNameItem(){return nameItem;}
    public String getDescription(){return description;}
}//close ItemList



