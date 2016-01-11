package uk.ac.tae.myapp.asosonline.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.List;

import uk.ac.tae.myapp.asosonline.R;
import uk.ac.tae.myapp.asosonline.api.ItemClickedListener;
import uk.ac.tae.myapp.asosonline.model.ProductCategory.ListingForCat;

/**
 * Created by Karma on 20/12/15.
 */
public class CatListingAdapter extends RecyclerView.Adapter<CatListingAdapter.ViewHolder> {
    private List<ListingForCat> listingItems;
    private Context context;

    public CatListingAdapter(Context context, List<ListingForCat> listingItems) {
        this.context = context;
        this.listingItems = listingItems;
    }

    @Override
    public CatListingAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cardview_listing, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CatListingAdapter.ViewHolder holder, int position) {
        final ListingForCat listingItem = listingItems.get(position);
        holder.tvLTitle.setText(listingItem.getTitle());
        holder.tvLPrice.setText(listingItem.getCurrentPrice());
        Picasso.with(context).setIndicatorsEnabled(true);
        Picasso.with(context).load(listingItem.getProductImageUrl().get(0))
                .resize(100, 100).into(holder.ivLImage);
        holder.setItemClickedListener(new ItemClickedListener() {
            @Override
            public void onItemClicked(View view, int position) {
                String productId = listingItem.getProductId().toString();// FIXME: 20/12/15 
            }
        });
    }

    @Override
    public int getItemCount() {
        return listingItems.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvLTitle, tvLPrice;
        ImageView ivLImage;
        ItemClickedListener itemClickedListener;

        public ViewHolder(View itemView) {
            super(itemView);
            tvLTitle = (TextView) itemView.findViewById(R.id.tvPTitle);
            tvLPrice = (TextView) itemView.findViewById(R.id.tvPPrice);
            ivLImage = (ImageView) itemView.findViewById(R.id.ivPImage);
            ivLImage.setOnClickListener(this);
        }

        public void setItemClickedListener(ItemClickedListener itemClickedListener) {
            this.itemClickedListener = itemClickedListener;
        }

        @Override
        public void onClick(View v) {
            itemClickedListener.onItemClicked(v, getPosition());
        }
    }
}
