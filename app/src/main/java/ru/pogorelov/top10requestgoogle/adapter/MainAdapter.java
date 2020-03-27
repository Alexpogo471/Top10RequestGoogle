package ru.pogorelov.top10requestgoogle.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ru.pogorelov.top10requestgoogle.R;
import ru.pogorelov.top10requestgoogle.model.entity.Item;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainHolder> {

    private List<Item> items;

    public MainAdapter(List<Item> items) {
        this.items = items;
    }

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public MainHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout,parent,false);
        return new MainHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MainHolder holder, int position) {
        Item item = items.get(position);
        holder.tv_title.setText(item.getTitle());
        holder.tv_description.setText(item.getDescription());
        holder.tv_link.setText(item.getUrl());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class MainHolder extends RecyclerView.ViewHolder {

        private TextView tv_title;
        private TextView tv_description;
        private TextView tv_link;

        public MainHolder(@NonNull View itemView) {
            super(itemView);

            tv_title = itemView.findViewById(R.id.tv_title);
            tv_description = itemView.findViewById(R.id.tv_description);
            tv_link = itemView.findViewById(R.id.tv_link);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClickListener != null){
                        onItemClickListener.onItemClick(getAdapterPosition());
                    }
                }
            });
        }
    }

    public void setItems(List<Item> items){

        this.items = items;

    }

    public List<Item> getItems(){
        return items;
    }


}
