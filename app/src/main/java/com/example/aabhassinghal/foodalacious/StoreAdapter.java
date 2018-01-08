package com.example.aabhassinghal.foodalacious;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aabhassinghal on 1/7/18.
 */

public class StoreAdapter extends RecyclerView.Adapter<StoreAdapter.MyViewHolder> implements Filterable {
    private Context context;
    private List<Recipe> recipeList;
    private List<Recipe> recipeListfilter;
    private StorageAdapterListener listener;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, servings;
        public ImageView thumbnail;

        public MyViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.title);
            servings = view.findViewById(R.id.price);
            thumbnail = view.findViewById(R.id.thumbnail);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // send selected contact in callback
                    listener.onRecipeSelected(recipeListfilter.get(getAdapterPosition()));
                }
            });
        }

    }

    public StoreAdapter(Context context, List<Recipe> recipeList, StorageAdapterListener listener) {
        this.context = context;
        this.recipeList = recipeList;
        this.recipeListfilter = recipeList;
        this.listener = listener;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.store_item_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final Recipe recipe = recipeList.get(position);
        holder.name.setText(recipe.getTitle());
        holder.servings.setText("" + recipe.getServings());
        if (recipe.getIcon() != null) {
            Glide.with(context)
                    .load(recipe.getIcon())
                    .into(holder.thumbnail);
        } else {
            Glide.with(context)
                    .load(R.mipmap.ic_launcher)
                    .into(holder.thumbnail);
        }

    }

    @Override
    public int getItemCount() {
        return recipeList.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    recipeListfilter = recipeList;
                } else {
                    List<Recipe> filteredList = new ArrayList<>();
                    for (Recipe row : recipeList) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.getTitle().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(row);
                        }
                    }

                    recipeListfilter = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = recipeListfilter;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                recipeListfilter = (ArrayList<Recipe>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public interface StorageAdapterListener {
        void onRecipeSelected(Recipe recipe);
    }
}