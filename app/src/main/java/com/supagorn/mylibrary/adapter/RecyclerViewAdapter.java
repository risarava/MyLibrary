package com.supagorn.mylibrary.adapter;

/**
 * Created by iabellah on 2016-05-16.
 */
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.supagorn.mylibrary.R;
import com.supagorn.mylibrary.data.Library;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<Library> libraryArrayList = new ArrayList<Library>();

    public RecyclerViewAdapter() {
    }

    public RecyclerViewAdapter(ArrayList<Library> libraryArrayList) {
        this.libraryArrayList = libraryArrayList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_library, parent, false);
        return new RecyclerViewHolders(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((RecyclerViewHolders) holder).txtTitle.setText(libraryArrayList.get(position).getTitle());
        ((RecyclerViewHolders) holder).imgLibrary.setImageResource(libraryArrayList.get(position).getImage());
        holder.itemView.setTag(position);
        // holder.itemView.setOnClickListener(this);
    }

    @Override
    public int getItemCount() {
        return this.libraryArrayList.size();
    }

    /*
    @Override
    public void onClick(View v) {
        int position = (Integer) v.getTag();
        //Intent intent = new Intent(v.getContext(), ProductSlideActivity.class);
        // intent.putExtra("productsList", allProductArrayList);
        // intent.putExtra("clickPosition", position);
        //v.getContext().startActivity(intent);
    }*/

    public class RecyclerViewHolders extends RecyclerView.ViewHolder {

        public ImageView imgLibrary;
        public TextView txtTitle;

        public RecyclerViewHolders(View itemView) {
            super(itemView);
            imgLibrary = (ImageView) itemView.findViewById(R.id.library_imageeview);
            txtTitle = (TextView) itemView.findViewById(R.id.title_textview);
        }
    }

    public static class RecyclerItemClickListener implements RecyclerView.OnItemTouchListener {
        private OnItemClickListener mListener;

        public interface OnItemClickListener {
            void onItemClick(View view, int position);

            void onLongItemClick(View view, int position);
        }

        private GestureDetector mGestureDetector;

        public RecyclerItemClickListener(Context context, final RecyclerView recyclerView, OnItemClickListener listener) {
            mListener = listener;
            mGestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                    if (child != null && mListener != null) {
                        mListener.onLongItemClick(child, recyclerView.getChildAdapterPosition(child));
                    }
                }
            });
        }

        @Override public boolean onInterceptTouchEvent(RecyclerView view, MotionEvent e) {
            View childView = view.findChildViewUnder(e.getX(), e.getY());
            if (childView != null && mListener != null && mGestureDetector.onTouchEvent(e)) {
                mListener.onItemClick(childView, view.getChildAdapterPosition(childView));
                return true;
            }
            return false;
        }

        @Override public void onTouchEvent(RecyclerView view, MotionEvent motionEvent) { }

        @Override
        public void onRequestDisallowInterceptTouchEvent (boolean disallowIntercept){}
    }
}