package com.example.android.gadsproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

public class GadsAdapter extends ListAdapter<Gads, GadsAdapter.GadsHolder> {
    private static final DiffUtil.ItemCallback<Gads> DIFF_CALLBACK = new DiffUtil.ItemCallback<Gads>() {
        @Override
        public boolean areItemsTheSame(@NonNull Gads oldItem, @NonNull Gads newItem) {
            return oldItem.getName() == newItem.getName();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Gads oldItem, @NonNull Gads newItem) {
            return oldItem.getCountry().equals(newItem.getCountry()) &&
                    oldItem.getUrl().equals(newItem.getUrl()) &&
                    oldItem.getHours() == (newItem.getHours());
        }
    };

    private OnItemClickListener listener;

    public GadsAdapter() {
        super(DIFF_CALLBACK);
    }

    @NonNull
    @Override
    public GadsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.gads_list_item, parent, false);
        return new GadsHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull GadsHolder holder, int position) {
        Gads currentGad = getItem(position);

        //setting the name
        holder.tvName.setText(currentGad.getName());

        //creating and setting hour and country string
        String miscString = currentGad.getHours() + " learning Hours, " + currentGad.getCountry();
        holder.tvMisc.setText(miscString);

        //to load the image
        String url = currentGad.getUrl();
        Picasso.get()
                .load(url)
                .into(holder.ivBadge);
    }

    public Gads getVisitorAt(int position) {
        return getItem(position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;

    }

    public interface OnItemClickListener {
        void onItemClick(Gads visitor);
    }

    class GadsHolder extends RecyclerView.ViewHolder {
        private TextView tvName;
        private TextView tvMisc;
        private ImageView ivBadge;


        public GadsHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name);
            tvMisc = itemView.findViewById(R.id.tv_misc);
            ivBadge = itemView.findViewById(R.id.iv_badge);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(getItem(position));
                    }
                }
            });
        }
    }
}
