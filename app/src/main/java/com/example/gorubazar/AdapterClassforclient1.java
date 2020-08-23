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
public class AdapterClassforclient1 extends RecyclerView.Adapter<AdapterClassforclient1.ExampleViewholder> {
    private List<Show_elementforclient> examplelist;
    //private ArrayList<Show_element> data=new ArrayList();
    private Context context;
    private OnItemClickListener mListener;
    public AdapterClassforclient1(Context context,List<Show_elementforclient> examplelist) {
        this.examplelist = examplelist;
        this.context=context;

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
    public AdapterClassforclient1.ExampleViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.detailsclentside,parent,false);
        ExampleViewholder vh=new ExampleViewholder(v);
        return vh;

    }

    @Override
    public void onBindViewHolder(@NonNull ExampleViewholder holder, int position) {
        Show_elementforclient currentItem=examplelist.get(position);
        //holder.imageview.setImageResource(currentItem.getImagesource());

        holder.seller_name.setText(currentItem.getSeller_name());
        //holder.seller_name.setText("istiak");
        // data.add(new Show_element(currentItem.getProduct_price(),currentItem.getProduct_type(),currentItem.getProduct_image()));


    }

    @Override
    public int getItemCount() {
        return examplelist.size();
    }
    public  class ExampleViewholder extends RecyclerView.ViewHolder{


        public TextView seller_name;



        public ExampleViewholder( View itemView) {
            super(itemView);
            seller_name=itemView.findViewById(R.id.seller_name);

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


