package com.example.jrsl;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ProductItemArrayAdapter extends RecyclerView.Adapter<ProductItemArrayAdapter.MyViewHolder> {

    private ArrayList<ProductItem> products;
    private MyRecyclerViewItemClickListener myItemClickListener;

    public ProductItemArrayAdapter(ArrayList<ProductItem> products, MyRecyclerViewItemClickListener itemClickListener) {
        this.products = products;
        this.myItemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Inflate RecyclerView row
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_products_item, parent, false);

        //Create View Holder
        final MyViewHolder myViewHolder = new MyViewHolder(view);

        //Item Clicks
        myViewHolder.itemView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                myItemClickListener.onItemClicked(products.get(myViewHolder.getLayoutPosition()));
            }
        });

        return myViewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        //Set collection
        holder.productCollection.setText(products.get(position).getCollection());

        //Set desc
        holder.productName.setText(products.get(position).getName());

        //Set price
        String price = "$" + products.get(position).getPrice()+"0";
        holder.productPrice.setText(price);

        // Set image
        holder.productImage.setImageResource(products.get(position).getImage());

        //Set saved icon
        int savedStatus = products.get(position).getSavedStatus();
        if (savedStatus == 1) {
            holder.productSavedStatus.setImageResource(R.drawable.icon_saved);
        }
        holder.productSavedStatus.setImageResource(R.drawable.icon_save);

    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    @Override
    public int getItemViewType(int position)
    {
        return position;
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }


    //RecyclerView View Holder
    class MyViewHolder extends RecyclerView.ViewHolder
    {
        private TextView productCollection;;
        private TextView productName;
        private TextView productPrice;
        private ImageView productImage;
        private ImageView productSavedStatus;

        MyViewHolder(@NonNull View itemView)
        {
            super(itemView);
            productCollection = itemView.findViewById(R.id.ProductCollection);
            productName = itemView.findViewById(R.id.ProductName);
            productPrice = itemView.findViewById(R.id.ProductPrice);
            productImage = itemView.findViewById(R.id.ProductImage);
            productSavedStatus = itemView.findViewById(R.id.SavedIcon);
        }
    }

    //RecyclerView Click Listener
    public interface MyRecyclerViewItemClickListener
    {
        void onItemClicked(ProductItem products);
    }

}