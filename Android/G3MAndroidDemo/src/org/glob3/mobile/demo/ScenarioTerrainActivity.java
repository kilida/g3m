

package org.glob3.mobile.demo;

import org.glob3.mobile.generated.Angle;
import org.glob3.mobile.generated.ElevationDataProvider;
import org.glob3.mobile.generated.Geodetic2D;
import org.glob3.mobile.generated.LayerSet;
import org.glob3.mobile.generated.MapBoxLayer;
import org.glob3.mobile.generated.Sector;
import org.glob3.mobile.generated.SingleBillElevationDataProvider;
import org.glob3.mobile.generated.TimeInterval;
import org.glob3.mobile.generated.URL;
import org.glob3.mobile.generated.Vector2I;
import org.glob3.mobile.specific.G3MBuilder_Android;
import org.glob3.mobile.specific.G3MWidget_Android;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.RelativeLayout;


public class ScenarioTerrainActivity
         extends
            Activity {

   private G3MWidget_Android _g3mWidget;
   private RelativeLayout    _placeHolder;


   @Override
   protected void onCreate(final Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_scenario_terrain);

      final float _VerticalExaggeration = 2f;
      final double DELTA_HEIGHT = -700.905;
      // final double DELTA_HEIGHT = 0;


      final LayerSet layerSet = new LayerSet();


      final MapBoxLayer mboxTerrainLayer = new MapBoxLayer("examples.map-qogxobv1", TimeInterval.fromDays(30), true, 2);
      layerSet.addLayer(mboxTerrainLayer);


      final G3MBuilder_Android builder = new G3MBuilder_Android(this);
      //  builder.setPlanet(Planet.createSphericalEarth());
      builder.getPlanetRendererBuilder().setLayerSet(layerSet);


      final Geodetic2D lower = new Geodetic2D( //
               Angle.fromDegrees(40.1665739916489), //
               Angle.fromDegrees(-5.85449532145337));
      final Geodetic2D upper = new Geodetic2D( //
               Angle.fromDegrees(40.3320215899527), //
               Angle.fromDegrees(-5.5116079822178570));

      final Sector demSector = new Sector(lower, upper);

      // NROWS          1335
      // NCOLS          2516
      final ElevationDataProvider dem = new SingleBillElevationDataProvider(new URL("file:///0576.bil", false), demSector,
               new Vector2I(2516, 1335), DELTA_HEIGHT);

      builder.getPlanetRendererBuilder().setElevationDataProvider(dem);
      builder.getPlanetRendererBuilder().setVerticalExaggeration(_VerticalExaggeration);

      //The sector is shrinked to adjust the projection of
      builder.setShownSector(demSector.shrinkedByPercent(0.1f));


      _g3mWidget = builder.createWidget();

      _placeHolder = (RelativeLayout) findViewById(R.id.g3mWidgetHolder);
      _placeHolder.addView(_g3mWidget);

   }


   @Override
   public boolean onCreateOptionsMenu(final Menu menu) {
      // Inflate the menu; this adds items to the action bar if it is present.
      getMenuInflater().inflate(R.menu.scenario_terrain, menu);
      return true;
   }

}
