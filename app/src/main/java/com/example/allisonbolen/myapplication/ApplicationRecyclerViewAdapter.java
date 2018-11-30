package com.example.allisonbolen.myapplication;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.allisonbolen.myapplication.ApplicationFragment.OnListFragmentInteractionListener;
import com.example.allisonbolen.myapplication.dummy.DummyContent.Application_Information_Object;
import org.joda.time.format.DateTimeFormat;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Application_Information_Object} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class ApplicationRecyclerViewAdapter extends RecyclerView.Adapter<ApplicationRecyclerViewAdapter.ViewHolder> {

    private final List<Application_Information_Object> mValues;
    private final OnListFragmentInteractionListener mListener;

    public ApplicationRecyclerViewAdapter(List<Application_Information_Object> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_application, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.CmpyName.setText(mValues.get(position).getCompanyName());
        holder.DateLastAccessed.setText(mValues.get(position).getAppDate());
        holder.JobTitle.setText(mValues.get(position).getJobTitle());
        holder.JobDescp.setText(mValues.get(position).getJobDesc());

//        holder.CompanyImage.setImageDrawable();

        holder.cardView.setOnClickListener(v -> {
            Intent infoPage = new Intent(v.getContext(), InfoVeiwPage.class);

            //ADD DATA TO OUR INTENT
            infoPage.putExtra("App",mValues.get(position));
            infoPage.putExtra("Position", position);
            //START DETAIL ACTIVITY
            v.getContext().startActivity(infoPage);
        });
    }

    @Override
    public int getItemCount() {
        return HomeActivity.allApps.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView CmpyName, DateLastAccessed, JobDescp, JobTitle;
//        public final ImageView CompanyImage;
        public Application_Information_Object mItem;
        private CardView cardView;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            CmpyName = (TextView) view.findViewById(R.id.Company_Name);
            DateLastAccessed = (TextView) view.findViewById(R.id.DateLastOpened);
            JobDescp = (TextView) view.findViewById(R.id.jobDescription);
            JobTitle = (TextView) view.findViewById(R.id.JobTitle);
//            CompanyImage = (ImageView) view.findViewById(R.id.imageView2);
            cardView = (CardView) view.findViewById(R.id.card_view);
        }


        @Override
        public String toString() {
            return super.toString() + " '" + DateLastAccessed.getText() + "'";
        }
    }
}
