package uk.ac.tae.myapp.asosonline.model.MainDisplayPage;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import uk.ac.tae.myapp.asosonline.MainActivity;
import uk.ac.tae.myapp.asosonline.R;
import uk.ac.tae.myapp.asosonline.adapters.CatListingAdapter;
import uk.ac.tae.myapp.asosonline.model.ProductCategory.ListingForCat;

/**
 * Created by Karma on 20/12/15.
 */
public class CatListingFragment extends Fragment {
    private RecyclerView rcListing;
    private List<ListingForCat> listingItems;
    private String catId;
    private CatListingAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.listing_fragment, container, false);
        rcListing = (RecyclerView) v.findViewById(R.id.rvListing);
        catId = getArguments().getString("catId");//getId from activity
        listingItems = ((MainActivity) getActivity()).getListingItems(catId);//request listing items from main activity
        adapter = new CatListingAdapter(getActivity().getApplicationContext(), listingItems);
        rcListing.setAdapter(adapter);
        return v;
    }
}
