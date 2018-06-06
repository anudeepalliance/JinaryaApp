package com.example.android.jinarya.Database.ReportsDb;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.jinarya.R;

import static com.example.android.jinarya.OwnerInfoEditActivity.formatAndShortenNameForRecyclerView;
import static com.example.android.jinarya.OwnerInfoEditActivity.formatDateString;

/**
 * Created by Aadhyamo on 19/12/17.
 */

public class ReportsListAdapter extends RecyclerView.Adapter<ReportsListAdapter.NumberViewHolder> {

    private static final String LOG_TAG = ReportsListAdapter.class.getSimpleName();

    private Cursor mCursor = null;

    final private ListItemClickListener mOnClickListener;

    final private ListItemLongClickListener mOnLongClickListener;

    public ReportsListAdapter(Cursor cursor, ListItemClickListener listener,
                              ListItemLongClickListener listItemLongClickListener) {
        mCursor = cursor;
        mOnClickListener = listener;
        mOnLongClickListener = listItemLongClickListener;
    }

    public interface ListItemClickListener {
        void onListItemClick(long reportId);
    }

    public interface ListItemLongClickListener {
        void onListItemLongClick(long reportId);
    }

    @Override
    public NumberViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.activity_reports_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);

        View view =
                inflater.inflate(layoutIdForListItem, viewGroup, false);

        return new NumberViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return mCursor.getCount();
    }

    public void swapCursor(Cursor newCursor) {
        // Always close the previous mCursor first
        if (mCursor != null) mCursor.close();
        mCursor = newCursor;
        if (newCursor != null) {
            // Force the RecyclerView to refresh
            this.notifyDataSetChanged();
        }
    }


    class NumberViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener, View.OnLongClickListener {

        TextView positionView;

        TextView idTextView;

        TextView boyNameTextView;

        TextView girlNameTextView;

        TextView timeStampTextView;

        TextView resultSummaryTextView;

        TextView scorePercentageTextView;

        ImageView scorePercentageImageView;

        TextView testTypeTextView;

        public NumberViewHolder(View itemView) {

            super(itemView);

            positionView = itemView.findViewById(R.id.position_number);
            idTextView = itemView.findViewById(R.id.report_id);
            boyNameTextView = itemView.findViewById(R.id.report_item_boy_name);
            girlNameTextView = itemView.findViewById(R.id.report_item_girl_name);
            timeStampTextView = itemView.findViewById(R.id.report_item_time_stamp);
            resultSummaryTextView = itemView.findViewById(R.id.report_item_result_summary);
            scorePercentageTextView = itemView.findViewById(R.id.report_item_score_percentage);
            scorePercentageImageView = itemView.findViewById(R.id.report_item_score_circle);
            testTypeTextView = itemView.findViewById(R.id.report_item_test_type);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Long reportId = Long.parseLong(idTextView.getText().toString());
            mOnClickListener.onListItemClick(reportId);
        }

        @Override
        public boolean onLongClick(View v) {
            Long reportId = Long.parseLong(idTextView.getText().toString());
            mOnLongClickListener.onListItemLongClick(reportId);
            return false;
        }


    }

    @Override
    public void onBindViewHolder(NumberViewHolder holder, int position) {

        if ( !mCursor.moveToPosition(position))
            return;

        String reportId = mCursor.getString
                (mCursor.getColumnIndex(ReportsContract.ReportsEntry._ID));

        String boyName = mCursor.getString
                (mCursor.getColumnIndex(ReportsContract.ReportsEntry.COLUMN_OWNER_NAME));
        boyName = formatAndShortenNameForRecyclerView(boyName);

        String girlName = mCursor.getString
                (mCursor.getColumnIndex(ReportsContract.ReportsEntry.COLUMN_GUEST_NAME));
        girlName = formatAndShortenNameForRecyclerView(girlName);

        String timeStamp = mCursor.getString
                (mCursor.getColumnIndex(ReportsContract.ReportsEntry.COLUMN_TIMESTAMP));
        timeStamp = formatDateString(timeStamp);

        String resultSummary = mCursor.getString
                (mCursor.getColumnIndex(ReportsContract.ReportsEntry.COLUMN_REPORT_INSIGHTS_FOR_OWNER));
        resultSummary = resultSummary.substring(0,resultSummary.length() - 30) + "...";

        int testScore = mCursor.getInt
                (mCursor.getColumnIndex(ReportsContract.ReportsEntry.COLUMN_COMPATIBILITY_SCORE));

        String score = Integer.toString(testScore);

        String testType = mCursor.getString
                (mCursor.getColumnIndex(ReportsContract.ReportsEntry.COLUMN_TEST_TYPE));

        holder.idTextView.setText(reportId);
        holder.boyNameTextView.setText(boyName);
        holder.girlNameTextView.setText(girlName);
        holder.timeStampTextView.setText(timeStamp);
        holder.resultSummaryTextView.setText(resultSummary);
        holder.scorePercentageTextView.setText(score);
        holder.testTypeTextView.setText(testType);

        if ( testScore <= 30 ) {
            holder.scorePercentageImageView.setColorFilter(Color.parseColor("#ff1a1a"));
        } else if ( testScore > 30 && testScore <= 60) {
            holder.scorePercentageImageView.setColorFilter(Color.parseColor("#ff6600"));
        } else if ( testScore > 60 && testScore <= 89) {
            holder.scorePercentageImageView.setColorFilter(Color.parseColor("#008ae0"));
        } else {
            holder.scorePercentageImageView.setColorFilter(Color.parseColor("#00e600"));
        }
    }
}
