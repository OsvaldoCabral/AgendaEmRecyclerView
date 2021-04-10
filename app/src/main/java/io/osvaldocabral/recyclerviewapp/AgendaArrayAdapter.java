package io.osvaldocabral.recyclerviewapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AgendaArrayAdapter extends RecyclerView.Adapter<AgendaArrayAdapter.ViewHolder> {

    private static  ClickListener clickListener;


    public void setClickListener(ClickListener clickListener) {
        AgendaArrayAdapter.clickListener = clickListener;
    }


    public interface ClickListener {
        void onItemClick(int position, View view);
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);

        View itemView = layoutInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AgendaArrayAdapter.ViewHolder holder, int position) {
        Agenda agenda = DataSingleton.getInstance().listAgenda.get(position);
        holder.textViewName.setText(agenda.getNome());
        holder.textViewPhone.setText(agenda.getTelefone());
    }

    @Override
    public int getItemCount() {
        return DataSingleton.getInstance().listAgenda.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView textViewName;
        TextView textViewPhone;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewName = itemView.findViewById(R.id.textViewName);
            textViewPhone = itemView.findViewById(R.id.textViewPhone);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickListener.onItemClick(getAdapterPosition(), v);
                }
            });
        }
    }
}
