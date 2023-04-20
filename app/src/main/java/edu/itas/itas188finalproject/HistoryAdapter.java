package edu.itas.itas188finalproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder> {

    private List<RewrittenText> historyList;


    public HistoryAdapter(List<RewrittenText> historyList) {
        this.historyList = historyList;
    }

    @NonNull
    @Override
    public HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_history, parent, false);
        return new HistoryViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryViewHolder holder, int position) {
        RewrittenText rewrittenText = historyList.get(position);
        holder.itemTitle.setText("#" + (position + 1) + " Rephrase");
        holder.originalText.setText("Your Input: " + rewrittenText.getOriginalText());
        holder.rephrasedText.setText(rewrittenText.getRephrasedText());
    }

    @Override
    public int getItemCount() {
        return historyList.size();
    }

    public static class HistoryViewHolder extends RecyclerView.ViewHolder {
        TextView originalText;
        TextView rephrasedText;
        TextView itemTitle;

        public HistoryViewHolder(@NonNull View itemView) {
            super(itemView);
            itemTitle = itemView.findViewById(R.id.item_title);
            originalText = itemView.findViewById(R.id.original_text);
            rephrasedText = itemView.findViewById(R.id.rephrased_text);
        }
    }
}

