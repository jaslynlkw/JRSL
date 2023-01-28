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
public class OrderDetailsItemArrayAdapter extends RecyclerView.Adapter<OrderDetailsItemArrayAdapter.MyViewHolder> {

    private final Context context;
    private ArrayList<CartItem> myCart;
    private CartClickListener myCartClickListener;

    public OrderDetailsItemArrayAdapter(Context context, ArrayList<CartItem> myCart, CartClickListener myCartClickListener){
        this.myCart = myCart;
        this.myCartClickListener = myCartClickListener;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Inflate RecyclerView row
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_orderdetails_item, parent, false);

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

        //Set brand
        holder.brand.setText(myCart.get(position).getCollection());

        //Set desc
        holder.desc.setText(myCart.get(position).getName());

        //Set size
        holder.size.setText(myCart.get(position).getSize());

        //Set qty
        String qt = Integer.toString(myCart.get(position).getQty());
        holder.qty.setText(qt);

        //Set price
        String price = "$" + myCart.get(position).getPrice()+"0";
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
        private TextView brand;
        private TextView desc;
        private TextView size;
        private TextView qty;
        private TextView price;
        private ImageView image;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            brand = itemView.findViewById(R.id.orderDetailsBrand);
            desc = itemView.findViewById(R.id.orderDetailsDesc);
            size = itemView.findViewById(R.id.orderDetailsSize);
            qty = itemView.findViewById(R.id.orderDetailsQty);
            price = itemView.findViewById(R.id.orderDetailsPrice);
            image = itemView.findViewById(R.id.orderDetailsPicture);
        }
    }

    //RecyclerView Click Listener
    public interface CartClickListener {
        void onItemClicked(CartItem cartItem);
    }
}
