package com.example.android.jinarya.Database.NameDb.OwnerNames;

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
 * Created by Aadhyamo on 10/02/18.
 */

public class OwnerNamesListAdapter extends
        RecyclerView.Adapter<OwnerNamesListAdapter.OwnerNamesNumberViewHolder> {

    private final String LOG_TAG = this.getClass().getName();

    private Cursor mCursor = null;

    final private OwnerNamesListItemClickListener mOnClickListener;

    final private OwnerNamesListItemLongClickListener mOnLongClickListener;

    public interface OwnerNamesListItemClickListener {
        void onListItemClick(long namesId);
    }

    public interface OwnerNamesListItemLongClickListener {
        void onListItemLongClick(long namesId);
    }

    public OwnerNamesListAdapter(Cursor cursor, OwnerNamesListItemClickListener listener,
                                 OwnerNamesListItemLongClickListener listItemLongClickListener) {
        mCursor = cursor;
        mOnClickListener = listener;
        mOnLongClickListener = listItemLongClickListener;

    }

    class OwnerNamesNumberViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener, View.OnLongClickListener {

        TextView idTextView;

        ImageView userIconImageView;

        TextView nameTextView;

        TextView ageTextView;


        public OwnerNamesNumberViewHolder(View itemView) {

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
            Long nameId = Long.parseLong(idTextView.getText().toString());
            mOnClickListener.onListItemClick(nameId);
        }

        @Override
        public boolean onLongClick(View v) {
            Long nameId = Long.parseLong(idTextView.getText().toString());
            mOnLongClickListener.onListItemLongClick(nameId);
            return false;
        }




    }

    @Override
    public OwnerNamesNumberViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutIdForListItem = R.layout.all_names_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);

        View view =
                inflater.inflate(layoutIdForListItem, parent, false);

        return new OwnerNamesNumberViewHolder(view);

    }

    @Override
    public int getItemCount() {
        return mCursor.getCount();
    }

    public void swapOwnerNamesCursor(Cursor newCursor) {
        // Always close the previous mCursor first
        if (mCursor != null) mCursor.close();
        mCursor = newCursor;
        if (newCursor != null) {
            // Force the RecyclerView to refresh
            this.notifyDataSetChanged();
        }
    }



    @Override
    public void onBindViewHolder(OwnerNamesNumberViewHolder holder, int position) {

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
