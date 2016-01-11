package uk.ac.tae.myapp.asosonline;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.os.TransactionTooLargeException;
import android.provider.SyncStateContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import java.util.List;

import retrofit.RestAdapter;
import uk.ac.tae.myapp.asosonline.adapters.ASOSRestAdapter;
import uk.ac.tae.myapp.asosonline.constants.ASOSGenderCloths;
import uk.ac.tae.myapp.asosonline.model.ASOSMen;
import uk.ac.tae.myapp.asosonline.model.ASOSWomen;
import uk.ac.tae.myapp.asosonline.model.Listing;
import uk.ac.tae.myapp.asosonline.model.MainDisplayPage.CatListingFragment;
import uk.ac.tae.myapp.asosonline.model.ProductCategory.ListingForCat;

public class MainActivity extends AppCompatActivity {
    ASOSRestAdapter asosRestAdapter;
    ASOSMen asosMen;
    ASOSWomen asosWomen;
    Toolbar toolbar;
    List<Listing> navMenuMen, navNanWomen;
    FragmentManager fragmentManager;
    FragmentTransaction ft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.asos_main);
        toolbar = (Toolbar) findViewById(R.id.toolbarASOS);
//        toolbar.setNavigationIcon(R.drawable.back);
//        toolbar.setNavigationContentDescription(R.string.back_button);
        toolbar.setLogo(R.drawable.logo);
        toolbar.setLogoDescription(R.string.asos_logo);

        asosRestAdapter = new ASOSRestAdapter(this);
        asosMen = asosRestAdapter.resquestMenProducts();


        asosRestAdapter.requestProductsByCatId("catalog01_1000_2623");
        asosRestAdapter.requestProductDetail("1702301");

        fragmentManager = getFragmentManager();
        ft = fragmentManager.beginTransaction();


    }

//    public NavigationMenuFragment getNavMenuFragment() {
//
//    }

    public List<Listing> getNavMenuMen(ASOSGenderCloths gender) {
        List<Listing> navMenuItems = null;
        if (gender == ASOSGenderCloths.MEN)
            navMenuItems = navMenuMen;
        else if (gender == ASOSGenderCloths.WOMEN)
            navMenuItems = navNanWomen;

        return navMenuItems;
    }

    public List<ListingForCat> getListingItems(String listingCatId) {
        List<ListingForCat> listingItems = asosRestAdapter.requestProductsByCatId(listingCatId);
        return listingItems;
    }

    public void changeListingPage(String catId) {
        Bundle bundle = new Bundle();
        bundle.putString("catId", catId);
        CatListingFragment fr = new CatListingFragment();
        fr.setArguments(bundle);
    }
}
