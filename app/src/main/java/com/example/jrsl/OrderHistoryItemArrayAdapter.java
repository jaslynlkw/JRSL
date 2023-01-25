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
public class OrderHistoryItemArrayAdapter extends RecyclerView.Adapter<OrderHistoryItemArrayAdapter.MyViewHolder> {

    private final Context context;
    private ArrayList<OrderDetailsItem> myCart;
    private CartClickListener myCartClickListener;

    public OrderHistoryItemArrayAdapter(Context context, ArrayList<OrderDetailsItem> myCart, CartClickListener myCartClickListener){
        this.myCart = myCart;
        this.myCartClickListener = myCartClickListener;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Inflate RecyclerView row
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_orderhistory_item, parent, false);

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
        //Set date
        String date = "Order Date:\n" + myCart.get(position).getOrderDate();
        holder.orderDate.setText(date);

        //Set reference
        String ref = "Order Reference:\n" + myCart.get(position).getOrderRef();
        holder.orderRef.setText(ref);

        //Set status
        String status = "Order Status:\n" + myCart.get(position).getOrderStatus();
        holder.orderStatus.setText(status);

        // Set image
        Glide.with(context)
                .load(myCart.get(position).getOrderImageURL())
                .into(holder.orderImage);

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
        private TextView orderDate;
        private TextView orderRef;
        private TextView orderStatus;
        private ImageView orderImage;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            orderDate = itemView.findViewById(R.id.orderHistoryDate);
            orderRef = itemView.findViewById(R.id.orderHistoryReference);
            orderStatus = itemView.findViewById(R.id.orderHistoryStatus);
            orderImage = itemView.findViewById(R.id.orderHistoryPicture);
        }
    }

    //RecyclerView Click Listener
    public interface CartClickListener {
        void onItemClicked(OrderDetailsItem orderHistoryItem);
    }
}
