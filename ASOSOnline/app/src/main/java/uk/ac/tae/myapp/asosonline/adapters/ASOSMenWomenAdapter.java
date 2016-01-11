package uk.ac.tae.myapp.asosonline.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import uk.ac.tae.myapp.asosonline.R;
import uk.ac.tae.myapp.asosonline.api.ItemClickedListener;
import uk.ac.tae.myapp.asosonline.model.Listing;

/**
 * Created by Karma on 19/12/15.
 */
public class ASOSMenWomenAdapter extends RecyclerView.Adapter<ASOSMenWomenAdapter.ViewHolder> {
    private Context context;
    private int row_layout;
    private List<Listing> navMenuItems;

    public ASOSMenWomenAdapter(Context context, int row_layout, List<Listing> navMenuItems) {
        this.context = context;
        this.row_layout = row_layout;
        this.navMenuItems = navMenuItems;
    }

    @Override
    public ASOSMenWomenAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(row_layout, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ASOSMenWomenAdapter.ViewHolder holder, int position) {
        final Listing productCat = navMenuItems.get(position);
        holder.tvMTitle.setText(productCat.getName());
        holder.tvMCounter.setText(productCat.getProductCount());
        holder.setMenuItemClickedListener(new ItemClickedListener() {
            @Override
            public void onItemClicked(View view, int position) {
                String catId = productCat.getCategoryId();// FIXME: 20/12/15

            }
        });

    }

    @Override
    public int getItemCount() {
        return navMenuItems.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvMTitle, tvMCounter;
        ItemClickedListener menuItemClickedListener;

        public ViewHolder(View itemView) {
            super(itemView);
            tvMTitle = (TextView) itemView.findViewById(R.id.tvNCatTitle);
            tvMCounter = (TextView) itemView.findViewById(R.id.tvNCatCounter);
            itemView.setOnClickListener(this);
        }

        public void setMenuItemClickedListener(ItemClickedListener menuItemClickedListener) {
            this.menuItemClickedListener = menuItemClickedListener;
        }

        @Override
        public void onClick(View v) {
            menuItemClickedListener.onItemClicked(v, getPosition());
        }
    }
}
