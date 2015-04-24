package com.example.please.devitti;

import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Parcelable;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class Maps extends FragmentActivity {

    _DATABASEManager dM = new _DATABASEManager();
    Cause[] allCauses;
    Double myLat = 0.999;
    Double myLong = 0.000;
    String percntCmpl[];
    String dataFromSignIn[];
    private GoogleMap mMap; // Might be null if Google Play services APK is not available.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
//        Parcelable[] allParcelables = getIntent().getExtras().getParcelableArray("causes");
//        allCauses = new Cause[allParcelables.length];
//        for (int i = 0 ; i < allParcelables.length; i++) {
//            allCauses[i] = (Cause)allParcelables[i];
//        }
//        dataFromSignIn
        dataFromSignIn = (String[]) getIntent().getSerializableExtra("dataFromSignIn");
        percntCmpl = (String[]) getIntent().getSerializableExtra("percentageCompleted");
        String temp = (String) getIntent().getSerializableExtra("noOfCauses");
        int noOfCauses =Integer.valueOf(temp);
        System.out.println(">><><><><><><>"+noOfCauses+"<><><><><><><><><");
        allCauses  = new Cause[noOfCauses];
        for (int i = 0;i  <noOfCauses;  i++)
        {
            System.out.println("got     >"+"cause"+i);
            allCauses[i] = (Cause) getIntent().getSerializableExtra("cause"+i);
            System.out.println("CauseId recieved: <><><><><> " +allCauses[i].causeId);
            System.out.println("RECIEVED PERCENTAGE COMPLETED: " +percntCmpl[i]);
        }

//        for (int i = 0 ; i <causesForMaps.length;i++)
//        {
//            openMapSearchPage.putExtra("cause"+i, causesForMaps[i]);
//        }

//        allCauses= (Cause[]) getIntent().getSerializableExtra("causes");
//        for(int i = 0 ;i <allCauses.length;i++)
//        {
//            System.out.println("CauseId recieved: " +allCauses.length);
//        }
        setUpMapIfNeeded();

    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    public class getCausesInBagrd extends AsyncTask<String, Integer, Cause[]>
    {

        @Override
        protected Cause[] doInBackground(String... params) {
            return dM.getAllCauses();
        }

        @Override
        protected void onPostExecute(Cause[] s) {
            super.onPostExecute(s);

            allCauses =s;
//            new getCausesDetailInBgrd().execute();

        }
    }

    public class getCausesDetaillInBgrd extends AsyncTask<String, Integer, Cause[]>
    {

        @Override
        protected Cause[] doInBackground(String... params) {
//            return dM.getAllCausesByUserAndDetailsForAllCauses(allCauses);
            return dM.getAllCausesDetailsForAllCauses(allCauses);
        }

        @Override
        protected void onPostExecute(Cause[] s) {
            super.onPostExecute(s);

            allCauses =s;
//            new getAllCausesAndDetailsInBagrd().execute();

        }
    }


    /**
     * Sets up the map if it is possible to do so (i.e., the Google Play services APK is correctly
     * installed) and the map has not already been instantiated.. This will ensure that we only ever
     * call {@link #setUpMap()} once when {@link #mMap} is not null.
     * <p/>
     * If it isn't installed {@link SupportMapFragment} (and
     * {@link com.google.android.gms.maps.MapView MapView}) will show a prompt for the user to
     * install/update the Google Play services APK on their device.
     * <p/>
     * A user can return to this FragmentActivity after following the prompt and correctly
     * installing/updating/enabling the Google Play services. Since the FragmentActivity may not
     * have been completely destroyed during this process (it is likely that it would only be
     * stopped or paused), {@link #onCreate(Bundle)} may not be called again so we should call this
     * method in {@link #onResume()} to guarantee that it will be called.
     */
    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }
    }

    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case, we
     * just add a marker near Africa.
     * <p/>
     * This should only be called once and when we are sure that {@link #mMap} is not null.
     */
    private void setUpMap() {





        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.setMyLocationEnabled(true);
        Location location;

        //own location
//        LocationManager lm = (LocationManager) getSystemService(LOCATION_SERVICE);
//        Criteria cc = new Criteria();
//        String provider  = lm.getBestProvider(cc,true);
//        location = lm.getLastKnownLocation(provider);
//        lm.requestLocationUpdates(lm.GPS_PROVIDER,0,0,new LocationListener() {
//            @Override
//            public void onLocationChanged(Location location) {
//                myLat  = location.getLatitude();
//                myLong = location.getLongitude();
//
//            }
//
//            @Override
//            public void onStatusChanged(String provider, int status, Bundle extras) {
//
//            }
//
//            @Override
//            public void onProviderEnabled(String provider) {
//
//            }
//
//            @Override
//            public void onProviderDisabled(String provider) {
//
//            }
//        });
//
//        LatLng lt = new LatLng(myLat,myLong);
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(lt));

        LatLng ltt =  new LatLng(new Double(32.592033),new Double(71.552968));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(ltt));

        mMap.animateCamera(CameraUpdateFactory.zoomTo(6));

for (int i = 0 ; i <allCauses.length; i ++)
{
//    mMap.addMarker(new MarkerOptions().position(new LatLng(allCauses[i].latitude,allCauses[i].longitude).ti))

    Double lati = Double.parseDouble(allCauses[i].latitude);
    Double longti = Double.parseDouble(allCauses[i].longitude);

    mMap.addMarker(new MarkerOptions().position(new LatLng(lati, longti)).title(allCauses[i].causeId));


}


//        mMap.addMarker(new MarkerOptions().position(new LatLng(0, 0)).title("Marker"));
//        mMap.addMarker(new MarkerOptions().position(new LatLng(32.592033, 71.552968)).title("Marker"));
//        mMap.addMarker(new MarkerOptions().position(new LatLng(32.584874, 71.558590)).title("Marker"));
//        mMap.addMarker(new MarkerOptions().position(new LatLng(32.581475, 71.559019)).title("Marker"));


        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {


                LatLng ltlng = marker.getPosition();
                String id = marker.getTitle();
                for(int i = 0 ; i < allCauses.length;  i++)
                {
                    System.out.println("marker id: >" +id+"<");
                    System.out.println("causeid: >" +allCauses[i].causeId+"<");
                    String str1 = String.valueOf(allCauses[i].causeId);
                    String str2 = String.valueOf(marker.getTitle());

                    if(str1.equals(str2) )
                    {
                        System.out.println("what the hellllllllllllllllllll!!!!!!");
                        Bundle bndl=new Bundle();
                        Cause ii  = allCauses[i];
                        Intent intnt = new Intent(Maps.this, CauseFullDetailView.class);
                        intnt.putExtra("cause", ii);
                        intnt.putExtra("dataFromSignIn",dataFromSignIn);
                        intnt.putExtra("percentageCompleted",percntCmpl[i].toString());
                        startActivity(intnt);


                    }
                }
                //now compare it with all the other postions to get the one clicked
                // then bring in the data\
                System.out.println("Latitudeeee: "+ltlng.latitude);
                System.out.println("Longitudeee: "+ltlng.longitude);
                Toast.makeText(getApplicationContext(),"Latitudeeee: "+ltlng.latitude+" Longitudeee: "+ltlng.longitude,Toast.LENGTH_SHORT);


                return false;
            }
        });



    }
}
