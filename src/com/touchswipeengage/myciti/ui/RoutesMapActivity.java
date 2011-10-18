/**
 * 
 */
package com.touchswipeengage.myciti.ui;

import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.SubMenu;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;
import com.touchswipeengage.myciti.R;
import com.touchswipeengage.myciti.domain.ContentRepository;
import com.touchswipeengage.myciti.domain.Route;
import com.touchswipeengage.myciti.domain.Station;

/**
 * @author mwho
 *
 */
public class RoutesMapActivity extends MapActivity {
	private MapView mapview;
	private MapController mapController;
	private Route mRoute;
	private GeoPoint resetPoint;
	private int resetLat, resetLong;
	/* (non-Javadoc)
	 * @see com.google.android.maps.MapActivity#isRouteDisplayed()
	 */
	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.google.android.maps.MapActivity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.map_layout);
        this.mapview = (MapView) findViewById(R.id.basic_mapview);
        mapview.setSatellite(false);
        mapview.setStreetView(false);
        mapview.setBuiltInZoomControls(true);
        mapController = mapview.getController();
		//mapController.setZoom(11); // Zoom 1 is world view
		int routeId = getIntent().getIntExtra("routeId", 0);
		this.mRoute = ContentRepository.getInstance().getRoute(routeId);
		List<Station> stations = this.mRoute.getStations();
		List<Overlay> listOfOverlays = mapview.getOverlays();
		// SETUP OVERLAY
		MarketItemisedOverlay itisedOverlay = new MarketItemisedOverlay(getResources().getDrawable(this.mRoute.getMarker()));
		int minLat = 0, maxLat = 0, minLong = 0, maxLong = 0;
		for (Station station: stations) {
			GeoPoint gp = new GeoPoint((int) (station.getLatitude() * 1E6), (int) (station.getLongitude() * 1E6));
			OverlayItem overlayitem = new OverlayItem(gp, station.getName(), station.getDescription());
			itisedOverlay.addOverlay(overlayitem);
			minLat = minLat == 0 ? (int)(station.getLatitude() * 1E6) : Math.min(minLat, (int)(station.getLatitude() * 1E6));
			maxLat = maxLat == 0 ? (int)(station.getLatitude() * 1E6) : Math.max(maxLat, (int)(station.getLatitude() * 1E6));
			minLong = minLong == 0 ? (int)(station.getLongitude() * 1E6) : Math.min(minLong, (int)(station.getLongitude() * 1E6));
			maxLong = maxLong == 0 ? (int)(station.getLongitude() * 1E6) : Math.max(maxLong, (int)(station.getLongitude() * 1E6));
		}
		listOfOverlays.add(itisedOverlay);
		// THIS SETS UP STATIC POSITION
		mapController.zoomToSpan(resetLat = Math.abs(maxLat - minLat), resetLong = Math.abs(maxLong - minLong));
		this.resetPoint = new GeoPoint(((maxLat + minLat)/2), ((maxLong + minLong)/2));
		mapController.animateTo(this.resetPoint);
        
	}
	
	/**
	 * @author mwho
	 *
	 */
	public class GeoUpdateHandler implements LocationListener {

		@Override
		public void onLocationChanged(Location location) {
			int lat = (int) (location.getLatitude() * 1E6);
			int lng = (int) (location.getLongitude() * 1E6);
			GeoPoint point = new GeoPoint(lat, lng);
			mapController.animateTo(point); //	mapController.setCenter(point);
		}

		@Override
		public void onProviderDisabled(String provider) {
		}

		@Override
		public void onProviderEnabled(String provider) {
		}

		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {
		}
	}
	
	/**
	 * @author mwho
	 *
	 */
	class MarketItemisedOverlay extends ItemizedOverlay<OverlayItem> {
		private List<OverlayItem> mOverlayItems = new ArrayList<OverlayItem>();
		private Context mContext;
		public MarketItemisedOverlay(Context context, Drawable defaultMarker) {
			super(defaultMarker);
			this.mContext = context;
		}
		public MarketItemisedOverlay(Drawable defaultMarker) {
			super(boundCenter(defaultMarker));
		}
		public void addOverlay(OverlayItem overlay) {
			this.mOverlayItems.add(overlay);
			super.populate();
		}
		@Override
		protected OverlayItem createItem(int i) {
			
			return mOverlayItems.get(i);
		}

		@Override
		public int size() {
			return this.mOverlayItems.size();
		}
		/* (non-Javadoc)
		 * @see com.google.android.maps.ItemizedOverlay#onTap(int)
		 */
		@Override
		protected boolean onTap(int index) {
			OverlayItem item = mOverlayItems.get(index);
			AlertDialog.Builder dialog = new AlertDialog.Builder(RoutesMapActivity.this);
			dialog.setTitle(item.getTitle());
			dialog.setMessage(item.getSnippet());
			dialog.show();
			return true;
		}
		
		
	}
	/**
	 * @author mwho
	 *
	 */
	class MapOverlay extends com.google.android.maps.Overlay
    {
		private GeoPoint p;
		
		public MapOverlay(GeoPoint gp) {
			this.p = gp;
		}
        @Override
        public boolean draw(Canvas canvas, MapView mapView, 
        boolean shadow, long when) 
        {
            super.draw(canvas, mapView, shadow);                   
 
            //---translate the GeoPoint to screen pixels---
            Point screenPts = new Point();
            mapView.getProjection().toPixels(p, screenPts);
 
            //---add the marker---
            Bitmap bmp = BitmapFactory.decodeResource(
                getResources(), R.drawable.da_marker_red);            
            Matrix matrix = new Matrix();
            matrix.postScale(0.5f, 0.5f); // douple the size
            canvas.drawBitmap(bmp, screenPts.x-(bmp.getWidth()/2), screenPts.y, null);         
            canvas.drawBitmap(bmp, matrix, null);         
            return true;
        }
    } 
	/* (non-Javadoc)
	 * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.map_menu, menu);
		super.onCreateOptionsMenu(menu);
		SubMenu gotoSub = menu.addSubMenu(R.string.menu_option_goto);
		gotoSub.setHeaderIcon(R.drawable.tab_ic_map);
		List<Station> stations = this.mRoute.getStations();
		for (Station station : stations) {
			gotoSub.add(station.getName());
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see android.view.MenuItem.OnMenuItemClickListener#onMenuItemClick(android.view.MenuItem)
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.reset_position:
			mapController.zoomToSpan(resetLat, resetLong);
			mapController.animateTo(this.resetPoint);
			break;
		case R.id.statellite_view:
			item.setChecked(!item.isChecked());
			this.mapview.setSatellite(item.isChecked());
			break;
		default:
			List<Station> stations = this.mRoute.getStations();
			for (Station station : stations) {
				if (item.getTitle().equals(station.getName())) {
					mapController.setZoom(16); // Zoom 1 is world view
					mapController.animateTo(new GeoPoint((int)(station.getLatitude() * 1E6), (int)(station.getLongitude() * 1E6)));
				}
					
			}
			break;
		}
		return true;
	}
}
