package com.terminoz.scrapopedia;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/*
* RecycleView.Adapter
* RecycleView.viewHolder
* */
public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder>{

    private Context context;
    private List<Product> productList;
    private String mainproduct;


    public ProductAdapter(Context context, List<Product> productList) {
        this.context = context;
        this.productList = productList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.list_layout, null);
        ProductViewHolder holder = new ProductViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ProductViewHolder holder, int position) {
        final Product product = productList.get(position);
        holder.textViewtitle.setText(product.getTitle());
        holder.textViewdesc.setText(product.getDesc());
        holder.imageView.setImageDrawable(context.getResources().getDrawable(product.getImage()));
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,Vegetable.class);
                intent.putExtra("Veg",product.getTitle());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

//        holder.imageView.setImageBitmap(product.getBitmap());
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView textViewtitle, textViewdesc;
        public ProductViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageview);
            textViewtitle = itemView.findViewById(R.id.textView);
            textViewdesc = itemView.findViewById(R.id.textView3);

        }
    }
}
