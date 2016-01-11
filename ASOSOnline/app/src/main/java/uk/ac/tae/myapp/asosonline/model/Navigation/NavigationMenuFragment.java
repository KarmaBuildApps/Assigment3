package uk.ac.tae.myapp.asosonline.model.Navigation;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import uk.ac.tae.myapp.asosonline.MainActivity;
import uk.ac.tae.myapp.asosonline.R;
import uk.ac.tae.myapp.asosonline.adapters.ASOSMenWomenAdapter;
import uk.ac.tae.myapp.asosonline.constants.ASOSGenderCloths;
import uk.ac.tae.myapp.asosonline.model.Listing;

/**
 * Created by Karma on 20/12/15.
 */
public class NavigationMenuFragment extends Fragment {
    private RecyclerView rcNavMenu;
    private ASOSMenWomenAdapter adapter;
    private ASOSGenderCloths manORWomen = ASOSGenderCloths.WOMEN; //Default menu
    private List<Listing> navMenu;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.nav_menu_fragment, container, false);
        rcNavMenu = (RecyclerView) view.findViewById(R.id.rvNavMenu);
        navMenu = ((MainActivity) getActivity()).getNavMenuMen(manORWomen);
        adapter = new ASOSMenWomenAdapter(getActivity(), R.layout.cardview_nav_menu, navMenu);
        rcNavMenu.setAdapter(adapter);
        return view;
    }
}
