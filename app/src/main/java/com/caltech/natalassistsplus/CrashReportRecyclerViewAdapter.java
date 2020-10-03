package com.caltech.natalassistsplus;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CrashReportRecyclerViewAdapter extends RecyclerView.Adapter<CrashReportRecyclerViewAdapter.ViewHolder>{

    ArrayList<CrashReport> crashReports;

    public CrashReportRecyclerViewAdapter(ArrayList<CrashReport> crashReports){
        this.crashReports = crashReports;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View menuView = inflater.inflate(R.layout.layout_crash_report, parent, false);

        return new ViewHolder(menuView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String errStr = "Error Code : " + crashReports.get(position).errorCode + "\n" +
                "Error Message : " + crashReports.get(position).errorMessage + "\n" +
                "Error occurs at : " + crashReports.get(position).errorDetails;
        holder.errorTxt.setText(errStr);
    }

    @Override
    public int getItemCount() {
        return crashReports.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView errorTxt;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            errorTxt = itemView.findViewById(R.id.crashReportTxt);
        }
    }
}
