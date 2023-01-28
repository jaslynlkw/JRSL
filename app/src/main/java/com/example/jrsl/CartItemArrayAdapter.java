package com.example.jrsl;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
public class CartItemArrayAdapter extends RecyclerView.Adapter<CartItemArrayAdapter.MyViewHolder> {

    private final Context context;
    private ArrayList<CartItem> myCart;
    private CartClickListener myCartClickListener;

    public CartItemArrayAdapter(Context context, ArrayList<CartItem> myCart, CartClickListener myCartClickListener){
        this.myCart = myCart;
        this.myCartClickListener = myCartClickListener;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Inflate RecyclerView row
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_cartitem, parent, false);

        //Create View Holder
        final MyViewHolder myViewHolder = new MyViewHolder(view);

        //Item Clicks
        myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myCartClickListener.onItemClicked(myCart.get(myViewHolder.getLayoutPosition()));
            }
        });

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        //Set collection type
        holder.collection.setText(myCart.get(position).getCollection());

        //Set brand
        holder.name.setText(myCart.get(position).getName());


        //Set size
        holder.size.setText(myCart.get(position).getSize());

        //Set qty
        String qt = Integer.toString(myCart.get(position).getQty());
        holder.qty.setText(qt);

        //Set price
        String price = "$" + myCart.get(position).getPrice() + "0";
        holder.price.setText(price);

        // Set image
        Glide.with(context)
                .load(myCart.get(position).getImageURL())
                .into(holder.image);

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
        private TextView collection;
        private TextView name;
        private TextView size;
        private TextView qty;
        private TextView price;
        private ImageView image;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
             collection = itemView.findViewById(R.id.CartCollection);
             name = itemView.findViewById(R.id.CartName);
             size = itemView.findViewById(R.id.CartSize);
             qty = itemView.findViewById(R.id.CartQty);
             price = itemView.findViewById(R.id.CartPrice);
             image = itemView.findViewById(R.id.cartPicture);
        }
    }

    //RecyclerView Click Listener
    public interface CartClickListener {
        void onItemClicked(CartItem cartItem);
    }
}
