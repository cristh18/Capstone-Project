package com.udacitynanodegree.cristhian.capstoneproject.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.udacitynanodegree.cristhian.capstoneproject.interfaces.GenericItem;
import com.udacitynanodegree.cristhian.capstoneproject.interfaces.GenericItemView;
import com.udacitynanodegree.cristhian.capstoneproject.ui.factories.GenericAdapterFactory;

import java.util.ArrayList;
import java.util.List;

public class GenericAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final String defaultCategory = "Other";

    protected List<GenericItem> items;

    protected GenericAdapterFactory factory;

    private String other;

    public GenericAdapter(GenericAdapterFactory factory) {
        this.factory = factory;
        this.items = new ArrayList<>();
        this.other = defaultCategory;
    }

    public GenericAdapter(GenericAdapterFactory factory, String other) {
        this.factory = factory;
        this.items = new ArrayList<>();
        this.other = other;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = (View) this.factory.onCreateViewHolder(parent, viewType);

        return new RecyclerView.ViewHolder(view) {
            @Override
            public String toString() {
                return super.toString();
            }
        };
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        GenericItemView genericItemView = (GenericItemView) holder.itemView;
        genericItemView.bind(getItem(position));
    }

    protected GenericItem getItem(int position) {
        return items.get(position);
    }

    @Override
    public int getItemViewType(int position) {
        return getItem(position).getType();
    }

    @Override
    public int getItemCount() {
        return items != null ? items.size() : 0;
    }

    public void setItems(List<? extends GenericItem> items) {

        this.items = new ArrayList<>();

        if (items != null) {

            for (int i = 0, size = items.size(); i < size; i++) {

                GenericItem genericItem = items.get(i);
                this.items.add(genericItem);

            }

        } else {
            this.items = new ArrayList<>();
        }

        notifyDataSetChanged();
    }

    public void update(GenericItem item) {
        int index = items.indexOf(item);
        if (index != -1) {
            items.set(index, item);
            notifyItemChanged(index);
        }
    }

    public void remove(GenericItem item) {
        int index = items.indexOf(item);
        if (index != -1) {
            items.remove(index);
            notifyItemRemoved(index);
        }
    }

    public void addItem(GenericItem item) {
        items.add(item);
        notifyDataSetChanged();
    }

    public void addItem(int index, GenericItem item) {
        if (items == null) {
            items = new ArrayList<>();
        }
        items.add(index, item);
        notifyItemInserted(index);
    }

    public void clear() {
        if (items != null) {
            items.clear();
            notifyDataSetChanged();
        }
    }

    public int getItemPosition(GenericItem item) {
        int itemPosition = -1;
        if (items != null && !items.isEmpty()) {
            itemPosition = items.indexOf(item);
        }
        return itemPosition;
    }

    public GenericItem getItemAtPosition(int position) {
        GenericItem genericItem = null;
        if (items != null && items.size() > position) {
            genericItem = getItem(position);
        }
        return genericItem;
    }
}
