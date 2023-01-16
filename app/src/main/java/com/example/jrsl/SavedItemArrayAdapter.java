package com.example.jrsl;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SavedItemArrayAdapter extends RecyclerView.Adapter<SavedItemArrayAdapter.MyViewHolder>{
    private ArrayList<CartItem> myCart;
    private SavedItemArrayAdapter.SavedClickListener mySavedClickListener;

    public SavedItemArrayAdapter(ArrayList<CartItem> myCart, SavedItemArrayAdapter.SavedClickListener mySavedClickListener){
        this.myCart = myCart;
        this.mySavedClickListener = mySavedClickListener;
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
        holder.savedBrand.setText(myCart.get(position).getBrand());

        //Set desc
        holder.savedDesc.setText(myCart.get(position).getDesc());

        //Set price
        String price = "$" + myCart.get(position).getPrice()+"0";
        holder.savedPrice.setText(price);

        // Set image
        holder.savedImage.setImageResource(myCart.get(position).getImage());

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
        private TextView savedBrand;
        private TextView savedDesc;
        private TextView savedPrice;
        private ImageView savedImage;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            savedBrand = itemView.findViewById(R.id.SavedBrand);
            savedDesc = itemView.findViewById(R.id.SavedDesc);
            savedPrice = itemView.findViewById(R.id.SavedPrice);
            savedImage = itemView.findViewById(R.id.SavedPicture);
        }
    }

    //RecyclerView Click Listener
    public interface SavedClickListener{
        void onItemClicked(CartItem savedItem);
    }
}
