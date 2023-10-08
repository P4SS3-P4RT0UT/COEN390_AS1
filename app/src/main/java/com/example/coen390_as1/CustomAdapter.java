package com.example.coen390_as1;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

    // Extract list of counter keys
    private String[] eventList;

    /*
     Initialize the event list
     */
    public CustomAdapter(String[] eventList) {
        this.eventList = eventList;
    }

    /*
    Create a new view
    */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_event, viewGroup, false);
        return new ViewHolder(view);
    }

    /*
    Replace the view content
    */
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        // Get a string from the list at some position and replace contents of the view with that string
        String eventName = eventList[position];
        viewHolder.eventName.setText(eventName);
    }

    /*
    Return the size of the array of events
     */
    @Override
    public int getItemCount() {
        return eventList.length;
    }

    /*
    Reference to the view
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView eventName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            eventName = itemView.findViewById(R.id.event_name);
        }
    }
}
