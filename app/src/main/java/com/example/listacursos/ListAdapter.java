package com.example.listacursos;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {
    final private AdapterCallBack callBack;

    private List<ItemList> mItems=new ArrayList<>();

    public ListAdapter(List<ItemList>_items,AdapterCallBack _callback){
        mItems=_items;
        callBack=_callback;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView textView;
        LinearLayout contenedor;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            contenedor=itemView.findViewById(R.id.contenedor);
            imageView=itemView.findViewById(R.id.imageView);
            textView=itemView.findViewById(R.id.textView);
        }
    }//close ViewHolder

    @NonNull
    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i){
        LayoutInflater layoutInflater=LayoutInflater.from(viewGroup.getContext());

        View view=layoutInflater.inflate(R.layout.main_item,viewGroup,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }//ListAdapter.ViewHolder

    @Override
    public void onBindViewHolder(@NonNull ListAdapter.ViewHolder viewHolder,int i){
        ItemList itemList=mItems.get(i);
        viewHolder.imageView.setImageResource(itemList.getImageItem());
        viewHolder.textView.setText(itemList.getNameItem());
        viewHolder.contenedor.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (callBack!=null){
                            callBack.onItemClicked(v,i);
                        }
                    }
                }
        );
    }//close onBindViewHolder

    @Override
    public int getItemCount(){
        return mItems.size();
    }
    public interface AdapterCallBack{
        void onItemClicked(View v,int itemPosition);
    }
}//ListaAdapter

