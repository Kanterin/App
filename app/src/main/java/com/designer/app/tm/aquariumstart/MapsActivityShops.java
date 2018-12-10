package com.designer.app.tm.aquariumstart;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;


public class MapsActivityShops extends FragmentActivity implements OnMapReadyCallback, LocationListener {

    final static int PERMISSION_ALL = 1;
    final static String[] PERMISSIONS = {android.Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION};
    private GoogleMap mMap;
    private MarkerOptions mo;
    private Marker marker;
    private LocationManager locationManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps_shops);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        mo = new MarkerOptions().position(new LatLng(0, 0)).title("My location");
        //mo = new MarkerOptions().position(new LatLng(0, 0)).title("My location");
        if (Build.VERSION.SDK_INT >= 23 && !isPermissionGranted()) {
            requestPermissions(PERMISSIONS, PERMISSION_ALL);
        } else requestLocation();
        if (!isLocationEnabled())
            showAlert(1);
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
  /*  @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng lublin = new LatLng(51.245, 22.570);
        mMap.addMarker(new MarkerOptions().position(lublin).title("Marker in Lublin"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(lublin));

    }*/
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        //mMap.setMyLocationEnabled(true);
        marker  =  mMap.addMarker(mo);



        LatLng sklep1 = new LatLng(51.21673089999999, 22.698131699999976);
        mMap.addMarker(new MarkerOptions().position(sklep1).title("Sklep zoologiczno-wędkarski").snippet("Kardynała Stefana Wyszyńskiego 17, 21-040 Świdnik, Polska").icon(BitmapDescriptorFactory.fromResource(R.mipmap.picker_shop)));
        LatLng sklep2 = new LatLng(51.194653, 22.584433999999987);
        mMap.addMarker(new MarkerOptions().position(sklep2).title("Sklep Zoologiczny Arctus Zoo Sp. z o.o. ").snippet("Abramowicka 43, Pasaż w sklepie Stokrotka, 20-093 Lublin").icon(BitmapDescriptorFactory.fromResource(R.mipmap.picker_shop)));
        LatLng sklep3 = new LatLng(51.1834247, 22.599511300000017);
        mMap.addMarker(new MarkerOptions().position(sklep3).title("Snake-Hurtownia zoologiczna").snippet("Feliksa Strojnowskiego 30, 20-386 Lublin").icon(BitmapDescriptorFactory.fromResource(R.mipmap.picker_shop)));
        LatLng sklep4 = new LatLng(51.2401523, 22.51969220000001);
        mMap.addMarker(new MarkerOptions().position(sklep4).title("LEOPARDUS- Lublin CH. E. Leclerc").snippet("Tomasza Zana 19, 20-601 Lublin").icon(BitmapDescriptorFactory.fromResource(R.mipmap.picker_shop)));
        LatLng sklep5 = new LatLng(51.2351664, 22.49348759999998);
        mMap.addMarker(new MarkerOptions().position(sklep5).title("King Zoo Sp. z o.o.").snippet("Orkana 4, 20-400 Lublin").icon(BitmapDescriptorFactory.fromResource(R.mipmap.picker_shop)));
        LatLng sklep6 = new LatLng(51.234162, 22.604311999999936);
        mMap.addMarker(new MarkerOptions().position(sklep6).title("Zoo Centrum").snippet("aleja Wincentego Witosa 14, 20-315 Lublin").icon(BitmapDescriptorFactory.fromResource(R.mipmap.picker_shop)));
        LatLng sklep7 = new LatLng(51.2317885, 22.594090999999935);
        mMap.addMarker(new MarkerOptions().position(sklep7).title("Centrum Zoologiczne 'Akwarium' ").snippet("Łabędzia 13, 20-335 Lublin").icon(BitmapDescriptorFactory.fromResource(R.mipmap.picker_shop)));
        LatLng sklep8 = new LatLng(51.2505002, 22.575590000000034);
        mMap.addMarker(new MarkerOptions().position(sklep8).title("LEOPARDUS- Lublin").snippet("aleja Unii Lubelskiej 2, 20-108 Lublin").icon(BitmapDescriptorFactory.fromResource(R.mipmap.picker_shop)));





    }

    @Override
    public void onLocationChanged(Location location) {

        LatLng myCoordinates = new LatLng(location.getLatitude(), location.getLongitude());
        marker.setPosition(myCoordinates);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(myCoordinates));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(10), 2000, null);
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }
    private void requestLocation() {
        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_FINE);
        criteria.setPowerRequirement(Criteria.POWER_HIGH);
        String provider = locationManager.getBestProvider(criteria, true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION);
        }
        locationManager.requestLocationUpdates(provider, 0, 0, this);
    }
    private boolean isLocationEnabled() {
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) ||
                locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }

    private boolean isPermissionGranted() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED || checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.v("mylog", "Permission is granted");
                return true;
            } else {
                Log.v("mylog", "Permission not granted");
                return false;
            }
        }
        return false;
    }
    private void showAlert(final int status) {
        String message, title, btnText;
        if (status == 1) {
            message = "Your Locations Settings is set to 'Off'.\nPlease Enable Location to " +
                    "use this app";
            title = "Enable Location";
            btnText = "Location Settings";
        } else {
            message = "Please allow this app to access location!";
            title = "Permission access";
            btnText = "Grant";
        }
        final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setCancelable(false);
        dialog.setTitle(title)
                .setMessage(message)
                .setPositiveButton(btnText, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                        if (status == 1) {
                            Intent myIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                            startActivity(myIntent);
                        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            requestPermissions(PERMISSIONS, PERMISSION_ALL);
                        }
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                        finish();
                    }
                });
        dialog.show();
    }
}
