package com.example.movielistui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

    private final String[] localTitles;
    private final int[] localImages;

    public interface OnItemClickListener {
        void onItemClick(View v, int pos);
    }

    private OnItemClickListener itemClickListener;

    public void setOnItemClickListener(OnItemClickListener lis) {
        itemClickListener = lis;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView imageView;
        private final TextView tvTitle;
        private final TextView tvRating;
        private final TextView tvGenre;
        private final TextView tvYear;

        public ViewHolder(View view, final OnItemClickListener listener) {
            super(view);
            imageView = view.findViewById(R.id.imageView);
            tvTitle = view.findViewById(R.id.tvTitle);
            tvGenre = view.findViewById(R.id.tvGenre);
            tvRating = view.findViewById(R.id.tvRating);
            tvYear = view.findViewById(R.id.tvYear);

            view.setOnClickListener(v -> {
                int pos = getAdapterPosition();
                if (listener != null && pos != RecyclerView.NO_POSITION) {
                    listener.onItemClick(v, pos);
                }
            });
        }

        public ImageView getImageView() { return imageView; }
        public TextView getTvTitle() { return tvTitle; }
        public TextView getTvRating() { return tvRating; }
        public TextView getTvGenre() { return tvGenre; }
        public TextView getTvYear() { return tvYear; }
    }

    // 생성자 수정: titles와 images를 받아야 함
    public CustomAdapter(String[] titles, int[] images) {
        this.localTitles = titles;
        this.localImages = images;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movieitem, parent, false);
        return new ViewHolder(view, itemClickListener);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.getImageView().setImageResource(localImages[position]);
        holder.getTvTitle().setText(localTitles[position]);
        holder.getTvRating().setText(String.valueOf(9.0f + position / 100f));
        holder.getTvGenre().setText("판타지");
        holder.getTvYear().setText(String.valueOf(2020 + position));
    }

    @Override
    public int getItemCount() {
        return localTitles.length;
    }
}
