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

public class GadsIQAdapter extends ListAdapter<GadsIQ, GadsIQAdapter.GadsHolder> {
    private static final DiffUtil.ItemCallback<GadsIQ> DIFF_CALLBACK = new DiffUtil.ItemCallback<GadsIQ>() {
        @Override
        public boolean areItemsTheSame(@NonNull GadsIQ oldItem, @NonNull GadsIQ newItem) {
            return oldItem.getName() == newItem.getName();
        }

        @Override
        public boolean areContentsTheSame(@NonNull GadsIQ oldItem, @NonNull GadsIQ newItem) {
            return oldItem.getCountry().equals(newItem.getCountry()) &&
                    oldItem.getUrl().equals(newItem.getUrl()) &&
                    oldItem.getScore() == (newItem.getScore());
        }
    };

    private OnItemClickListener listener;

    public GadsIQAdapter() {
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
        GadsIQ currentGad = getItem(position);

        //setting the name
        holder.tvName.setText(currentGad.getName());

        //creating and setting score and country string
        String miscString = currentGad.getScore() + " skill IQ Score, " + currentGad.getCountry();
        holder.tvMisc.setText(miscString);

        //to load the image
        String url = currentGad.getUrl();
        Picasso.get()
                .load(url)
                .into(holder.ivBadge);
    }

    public GadsIQ getVisitorAt(int position) {
        return getItem(position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;

    }

    public interface OnItemClickListener {
        void onItemClick(GadsIQ gadsIQ);
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
