package com.android4dev.navigationview;

import android.app.ProgressDialog;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android4dev.navigationview.MapAPI.MapAPI;
import com.android4dev.navigationview.MapAdapter.CustomInfoWindowAdapter;
import com.android4dev.navigationview.MapModel.Maps;
import com.android4dev.navigationview.MapModel.Result;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MapFragment extends Fragment implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {
    private SupportMapFragment mapFragment;
    private List<Result> resultList;
    private static GoogleMap mMap;
    //    private LayoutInflater inflater;
    ProgressDialog progressDialog;
    FragmentManager fragmentManager;
    private ArrayList<LatLng> apiMarkersLatLngs;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_map, container, false);
//        this.inflater = inflater;
//        initiateGoogleMap();
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        getSyndeyCruises();
        return v;
    }


    private void initiateGoogleMap() {
        fragmentManager = getChildFragmentManager();
        mapFragment = (SupportMapFragment) fragmentManager.findFragmentById(R.id.flMap);
        if (mapFragment == null) {
            mapFragment = SupportMapFragment.newInstance();
            fragmentManager.beginTransaction().replace(R.id.flMap, mapFragment).commit();
        }
        mapFragment.getMapAsync(this);
    }

//    @Override
//    public void onResume() {
//        super.onResume();
//        if (mMap == null) {
//            mapFragment.getMapAsync(this);
//
//        }
//    }

    public void dismissProgressDialog() {
        if (progressDialog != null)
            progressDialog.dismiss();
    }

    public void getSyndeyCruises() {
        MapAPI.getMapAPI().getCruises(new Callback<Maps>() {
            @Override
            public void success(Maps maps, Response response) {
                dismissProgressDialog();
                resultList = maps.getResults();
                initiateGoogleMap();
                Log.i("MapFragment", "Status : " + maps.getStatus());
            }

            @Override
            public void failure(RetrofitError error) {
                dismissProgressDialog();
                Log
                        .d("MapFragment", " Error " + error.getMessage());
            }
        });

    }

    public void addMarkers() {
        apiMarkersLatLngs = new ArrayList<>();
        String title;
        String snippet;
        double lat;
        double lng;
        LatLng markPosition;
        for (int i = 0; i < resultList.size(); i++) {
            title = resultList.get(i).getName();
            snippet = resultList.get(i).getVicinity();
            lat = resultList.get(i).getGeometry().getLocation().getLat();
            lng = resultList.get(i).getGeometry().getLocation().getLng();
            markPosition = new LatLng(lat, lng);
            apiMarkersLatLngs.add(markPosition);
            mMap.addMarker(new MarkerOptions().position(markPosition).title(title).snippet(snippet));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(lat, lng)));
        }
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        addMarkers();
        mMap.setInfoWindowAdapter(new CustomInfoWindowAdapter(getActivity()));
        mMap.setOnMarkerClickListener(this);
//        mMap.setOnMarkerDragListener(this);//setOnMarkerDrageListener
        displayMarkersInView();
    }

    private void displayMarkersInView() {
        final View mapView = fragmentManager.findFragmentById(R.id.flMap).getView();
//        if (mapView.getViewTreeObserver().isAlive()) {
//            mapView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
//                @Override
//                public void onGlobalLayout() {
        if (apiMarkersLatLngs.size() > 0) {
            Log.i("Camera", "Alive and OK");
            LatLngBounds.Builder latLngBoundsBuilder = LatLngBounds.builder();
            LatLngBounds latLngBounds;
            for (int i = 0; i < apiMarkersLatLngs.size(); i++) {
                latLngBoundsBuilder.include(apiMarkersLatLngs.get(i));
            }
            latLngBounds = latLngBoundsBuilder.build();
//                        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
//                            mapView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
//                        } else {
//                            mapView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
//                        }
            mMap.moveCamera(CameraUpdateFactory.newLatLngBounds(latLngBounds, 50));
            
        }
//                }
//            });
//        }
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        marker.setIcon(BitmapDescriptorFactory.defaultMarker());
        return false;
    }

}
