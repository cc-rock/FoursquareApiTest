package net.example.foursquareapitest.venuessearch;

import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import net.example.foursquareapitest.R;
import net.example.foursquareapitest.model.entities.Venue;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Carlo on 23/04/2017.
 */

public class VenuesSearchAdapter extends RecyclerView.Adapter<VenuesSearchAdapter.VenueViewHolder> {

    List<Venue> items = new ArrayList<>();

    public void setItems(List<Venue> newItems) {
        items.clear();
        items.addAll(newItems);
        notifyDataSetChanged();
    }

    @Override
    public VenueViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.venue_item, parent, false);
        return new VenueViewHolder(view);
    }

    @Override
    public void onBindViewHolder(VenueViewHolder holder, int position) {
        Venue venue = items.get(position);
        Resources res = holder.itemView.getResources();
        holder.name.setText(venue.getName());
        holder.address.setText(
                res.getString(
                        R.string.venue_address,
                        venue.getLocation().getAddress(),
                        venue.getLocation().getPostalCode(),
                        venue.getLocation().getCity()
                )
        );
        holder.checkinsAndRating.setText(
                res.getString(
                        R.string.venue_checkins_rating,
                        venue.getStats().getCheckinsCount(),
                        venue.getRating()
                )
        );
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class VenueViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.name)
        TextView name;

        @BindView(R.id.address)
        TextView address;

        @BindView(R.id.checkins_and_rating)
        TextView checkinsAndRating;

        public VenueViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
