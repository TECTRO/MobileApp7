package com.tectro.mobileapp7.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.util.Consumer;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tectro.mobileapp7.Dialogs.MoveDialog;
import com.tectro.mobileapp7.R;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {
    private final LayoutInflater inflater;
    private final List<AtomicReference<String>> collection;
    private FragmentManager manager;

    public ListAdapter(Context context, List<AtomicReference<String>> Collection, FragmentManager manager) {
        inflater = LayoutInflater.from(context);
        collection = Collection;
        this.manager = manager;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_list_layout, parent, false);
        ViewHolder holder = new ViewHolder(view);
        view.findViewById(R.id.list_item).setOnClickListener(v ->
        {
            int pos = holder.getAdapterPosition();
            if (pos != RecyclerView.NO_POSITION)
                MoveDialog.GetDialog(collection.get(pos)).show(manager,"cst");
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textItem.setText(collection.get(position).toString());
    }

    @Override
    public int getItemCount() {
        return collection.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView textItem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textItem = itemView.findViewById(R.id.list_item);
        }
    }
}
