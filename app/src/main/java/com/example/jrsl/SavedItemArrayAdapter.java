package com.example.jrsl;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class SavedItemArrayAdapter extends RecyclerView.Adapter<SavedItemArrayAdapter.MyViewHolder>{
    private ArrayList<ProductItem> myCart;
    private SavedItemArrayAdapter.SavedClickListener mySavedClickListener;
    private Context context;

    public SavedItemArrayAdapter(Context context, ArrayList<ProductItem> myCart, SavedItemArrayAdapter.SavedClickListener mySavedClickListener){
        this.myCart = myCart;
        this.mySavedClickListener = mySavedClickListener;
        this.context = context;
    }

    @NonNull
    @Override
    public SavedItemArrayAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Inflate RecyclerView row
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_saved_item, parent, false);

        //Create View Holder
        final SavedItemArrayAdapter.MyViewHolder myViewHolder = new SavedItemArrayAdapter.MyViewHolder(view);

        //Item Clicks
        myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mySavedClickListener.onItemClicked(myCart.get(myViewHolder.getLayoutPosition()));
            }
        });

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SavedItemArrayAdapter.MyViewHolder holder, int position) {

        //Set brand
        holder.savedCollection.setText(myCart.get(position).getCollection());

        //Set desc
        holder.savedName.setText(myCart.get(position).getName());

        //Set price
        String price = "$" + myCart.get(position).getPrice()+"0";
        holder.savedPrice.setText(price);

        // Set image
        Glide.with(context)
                .load(myCart.get(position).getImageURL())
                .into(holder.savedImage);
        Log.d(null,myCart.get(position).getImageURL());

    }

    @Override
    public int getItemCount() {
        return myCart.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    //RecyclerView View Holder
    class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView savedCollection;
        private TextView savedName;
        private TextView savedPrice;
        private ImageView savedImage;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            savedCollection = itemView.findViewById(R.id.SavedCollection);
            savedName = itemView.findViewById(R.id.SavedName);
            savedPrice = itemView.findViewById(R.id.SavedPrice);
            savedImage = itemView.findViewById(R.id.SavedPicture);
        }
    }



    //RecyclerView Click Listener
    public interface SavedClickListener{
        void onItemClicked(ProductItem savedItem);
    }
}
