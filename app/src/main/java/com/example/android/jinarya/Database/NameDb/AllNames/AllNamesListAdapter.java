package com.example.android.jinarya.Database.NameDb.AllNames;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.jinarya.Database.NameDb.NamesContract;
import com.example.android.jinarya.R;

/**
 * Created by Aadhyamo on 05/02/18.
 */

public class AllNamesListAdapter extends
        RecyclerView.Adapter<AllNamesListAdapter.AllNamesNumberViewHolder> {

    private final String LOG_TAG = this.getClass().getName();

    private Cursor mCursor = null;

    final private allNamesListItemClickListener mOnClickListener;

    final private allNamesListItemLongClickListener mOnLongClickListener;

    public interface allNamesListItemClickListener {
        void onListItemClick(long namesId);
    }

    public interface allNamesListItemLongClickListener {
        void onListItemLongClick(long namesId);
    }

    public AllNamesListAdapter(Cursor cursor, allNamesListItemClickListener listener,
                               allNamesListItemLongClickListener listItemLongClickListener) {
        mCursor = cursor;
        mOnClickListener = listener;
        mOnLongClickListener = listItemLongClickListener;
    }



    @Override
    public AllNamesNumberViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.all_names_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);

        View view =
                inflater.inflate(layoutIdForListItem, viewGroup, false);

        return new AllNamesNumberViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return mCursor.getCount();
    }

    public void swapAllNamesCursor(Cursor newCursor) {
        // Always close the previous mCursor first
        if (mCursor != null) mCursor.close();
        mCursor = newCursor;
        if (newCursor != null) {
            // Force the RecyclerView to refresh
            this.notifyDataSetChanged();
        }
    }


    class AllNamesNumberViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener, View.OnLongClickListener {

        TextView idTextView;

        ImageView userIconImageView;

        TextView nameTextView;

        TextView ageTextView;


        public AllNamesNumberViewHolder(View itemView) {

            super(itemView);

            idTextView = itemView.findViewById(R.id.all_names_id);
            ageTextView = itemView.findViewById(R.id.all_names_age);
            userIconImageView = itemView.findViewById(R.id.all_names_user_icon);
            nameTextView = itemView.findViewById(R.id.all_names_name);

            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Long allNameId = Long.parseLong(idTextView.getText().toString());
            mOnClickListener.onListItemClick(allNameId);
        }

        @Override
        public boolean onLongClick(View v) {
            Long allNameId = Long.parseLong(idTextView.getText().toString());
            mOnLongClickListener.onListItemLongClick(allNameId);
            return false;
        }




    }

    @Override
    public void onBindViewHolder(AllNamesNumberViewHolder holder, int position) {

        if ( !mCursor.moveToPosition(position))
            return;

        String allNameId = mCursor.getString
                (mCursor.getColumnIndex(NamesContract.NamesEntry._ID));
        String name = mCursor.getString
                (mCursor.getColumnIndex(NamesContract.NamesEntry.COLUMN_NAME));
        String age = mCursor.getString
                (mCursor.getColumnIndex(NamesContract.NamesEntry.COLUMN_AGE));
        String gender = mCursor.getString
                (mCursor.getColumnIndex(NamesContract.NamesEntry.COLUMN_GENDER));

        holder.idTextView.setText(allNameId);
        holder.ageTextView.setText(age);
        holder.nameTextView.setText(name);


        if ( gender.equals("Male")) {
            holder.userIconImageView.setImageResource(R.drawable.ic_male_icon);
        } else {
            holder.userIconImageView.setImageResource(R.drawable.ic_female_icon);
        }

    }
}

