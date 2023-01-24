package com.example.jrsl;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
public class CartItemArrayAdapter extends RecyclerView.Adapter<CartItemArrayAdapter.MyViewHolder> {

    private ArrayList<CartItem> myCart;
    private CartClickListener myCartClickListener;

    public CartItemArrayAdapter(ArrayList<CartItem> myCart, CartClickListener myCartClickListener){
        this.myCart = myCart;
        this.myCartClickListener = myCartClickListener;
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
        holder.collectionType.setText(myCart.get(position).getCollectionType());

        //Set brand
        holder.brand.setText(myCart.get(position).getBrand());

        //Set desc
        holder.desc.setText(myCart.get(position).getDesc());

        //Set size
        holder.size.setText(myCart.get(position).getSize());

        //Set qty
        String qt = Integer.toString(myCart.get(position).getQty());
        holder.qty.setText(qt);

        //Set price
        String price = "$" + myCart.get(position).getPrice();
        holder.price.setText(price);

        // Set image
        if (myCart.get(position).getImage() != 0) {
            holder.image.setImageResource(myCart.get(position).getImage());
        } else {
            holder.image.setImageResource(R.drawable.karissa_blackstreetwearset); // or any other default image
        }

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
        private TextView collectionType;
        private TextView brand;
        private TextView desc;
        private TextView size;
        private TextView qty;
        private TextView price;
        private ImageView image;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
             collectionType = itemView.findViewById(R.id.CollectionType);
             brand = itemView.findViewById(R.id.CartBrand);
             desc = itemView.findViewById(R.id.CartDesc);
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
