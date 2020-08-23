package com.example.gorubazar;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;
public class AdapterClass extends RecyclerView.Adapter<AdapterClass.ExampleViewholder> {
    private List<Show_element> examplelist;
    private ArrayList<Show_element> data=new ArrayList();
   private Context context;
    private OnItemClickListener mListener;
    public AdapterClass(Context context,List<Show_element> examplelist) {
        this.examplelist = examplelist;
        this.context=context;

    }
    public AdapterClass()
    {

    }
    public interface OnItemClickListener{
        void OnItemClick(int position);
    }

    public void setOnClickListener (OnItemClickListener listener){
        mListener=listener;
    }

public void name()
{

}



    @Override
    //public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       // View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.details,parent,false);
       // Viewholder vh=new Viewholder(v);
       // return vh;

    //}
    public AdapterClass.ExampleViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View v= LayoutInflater.from(context).inflate(R.layout.details,parent,false);
     ExampleViewholder vh=new ExampleViewholder(v);
     return vh;

    }

    @Override
    public void onBindViewHolder(@NonNull ExampleViewholder holder, int position) {
        Show_element currentItem=examplelist.get(position);
        //holder.imageview.setImageResource(currentItem.getImagesource());
        Picasso.with(context)
                .load(currentItem.getProduct_image())
                .fit()
                .centerCrop()
                .into(holder.imageview);
        holder.price.setText(currentItem.getProduct_price());

       // data.add(new Show_element(currentItem.getProduct_price(),currentItem.getProduct_type(),currentItem.getProduct_image()));


    }

    @Override
    public int getItemCount() {
        return examplelist.size();
    }
    public  class ExampleViewholder extends RecyclerView.ViewHolder{

        public ImageView imageview;
        public TextView price;


        public ExampleViewholder( View itemView) {
            super(itemView);
            imageview= itemView.findViewById(R.id.image);

            price=itemView.findViewById(R.id.price);
            itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    if(mListener!=null){
                        int position=getAdapterPosition();
                        if(position!=RecyclerView.NO_POSITION){
                            mListener.OnItemClick(position);
                        }
                    }
                }
            });
        }

    }
    }


