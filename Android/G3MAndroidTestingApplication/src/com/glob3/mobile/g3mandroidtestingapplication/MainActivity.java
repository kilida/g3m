

package com.glob3.mobile.g3mandroidtestingapplication;

import java.util.Random;

import org.glob3.mobile.generated.Angle;
import org.glob3.mobile.generated.Color;
import org.glob3.mobile.generated.ElevationDataProvider;
import org.glob3.mobile.generated.G3MContext;
import org.glob3.mobile.generated.GTask;
import org.glob3.mobile.generated.Geodetic2D;
import org.glob3.mobile.generated.Geodetic3D;
import org.glob3.mobile.generated.LayerSet;
import org.glob3.mobile.generated.LayerTilesRenderParameters;
import org.glob3.mobile.generated.MarksRenderer;
import org.glob3.mobile.generated.MeshRenderer;
import org.glob3.mobile.generated.PeriodicalTask;
import org.glob3.mobile.generated.Planet;
import org.glob3.mobile.generated.PlanetRenderer;
import org.glob3.mobile.generated.PlanetRendererBuilder;
import org.glob3.mobile.generated.Sector;
import org.glob3.mobile.generated.SingleBillElevationDataProvider;
import org.glob3.mobile.generated.TimeInterval;
import org.glob3.mobile.generated.URL;
import org.glob3.mobile.generated.Vector2I;
import org.glob3.mobile.generated.WMSLayer;
import org.glob3.mobile.generated.WMSServerVersion;
import org.glob3.mobile.specific.G3MBuilder_Android;
import org.glob3.mobile.specific.G3MWidget_Android;
import org.glob3.mobile.specific.TileVisitorCache_Android;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.RelativeLayout;


public class MainActivity
         extends
            Activity {

   private G3MWidget_Android _g3mWidget;
   private RelativeLayout    _placeHolder;


   @Override
   protected void onCreate(final Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main);
      onCreateCache();
   }


   private void onCreateDefault() {
      final G3MBuilder_Android builder = new G3MBuilder_Android(this);
      // builder.getPlanetRendererBuilder().setRenderDebug(true);

      // final ShapesRenderer shapesRenderer = new ShapesRenderer();
      // builder.addRenderer(shapesRenderer);

      final MarksRenderer marksRenderer = new MarksRenderer(true);
      builder.addRenderer(marksRenderer);

      final MeshRenderer meshRenderer = new MeshRenderer();
      meshRenderer.loadBSONMesh(new URL("file:///1951_r.bson"), Color.white());
      builder.addRenderer(meshRenderer);

      // final ShapeLoadListener Plistener = new ShapeLoadListener() {
      // @Override
      // public void onBeforeAddShape(final SGShape shape) {
      // // shape.setScale(2000);
      // //shape.setRoll(Angle.fromDegrees(-90));
      // }
      //
      //
      // @Override
      // public void onAfterAddShape(final SGShape shape) {
      //
      //
      // ILogger.instance().logInfo("Downloaded Building");
      //
      // final double fromDistance = 10000;
      // final double toDistance = 1000;
      //
      // final Angle fromAzimuth = Angle.fromDegrees(-90);
      // final Angle toAzimuth = Angle.fromDegrees(270);
      //
      // final Angle fromAltitude = Angle.fromDegrees(90);
      // final Angle toAltitude = Angle.fromDegrees(15);
      //
      // shape.orbitCamera(TimeInterval.fromSeconds(5), fromDistance,
      // toDistance, fromAzimuth, toAzimuth, fromAltitude,
      // toAltitude);
      //
      //
      // }
      //
      //
      // @Override
      // public void dispose() {
      // // TODO Auto-generated method stub
      //
      // }
      // };
      //
      //
      // shapesRenderer.loadBSONSceneJS(new URL("file:///target.bson"), "",
      // false, new Geodetic3D(Angle.fromDegrees(35.6452500000),
      // Angle.fromDegrees(-97.214), 30), AltitudeMode.RELATIVE_TO_GROUND,
      // Plistener);
      //
      //
      // builder.addRenderer(shapesRenderer);

      // if (false) {
      // shapesRenderer.loadBSONSceneJS(new URL("file:///A320.bson"),
      // URL.FILE_PROTOCOL + "textures-A320/", false,
      // new Geodetic3D(Angle.fromDegreesMinutesSeconds(38, 53, 42.24),
      // Angle.fromDegreesMinutesSeconds(-77, 2, 10.92),
      // 10000), AltitudeMode.ABSOLUTE, new ShapeLoadListener() {
      //
      // @Override
      // public void onBeforeAddShape(final SGShape shape) {
      // // TODO Auto-generated method stub
      // final double scale = 1e5;
      // shape.setScale(scale, scale, scale);
      // shape.setPitch(Angle.fromDegrees(90));
      //
      // }
      //
      //
      // @Override
      // public void onAfterAddShape(final SGShape shape) {
      // // TODO Auto-generated method stub
      //
      // }
      //
      //
      // @Override
      // public void dispose() {
      // // TODO Auto-generated method stub
      //
      // }
      // }, true);
      // }

      // if (false) { // Testing lights
      // shapesRenderer.addShape(new BoxShape(Geodetic3D.fromDegrees(0, 0, 0),
      // AltitudeMode.RELATIVE_TO_GROUND, new Vector3D(
      // 1000000, 1000000, 1000000), (float) 1.0, Color.red(), Color.black(),
      // true)); // With normals
      //
      // shapesRenderer.addShape(new BoxShape(Geodetic3D.fromDegrees(0, 180,
      // 0), AltitudeMode.RELATIVE_TO_GROUND, new Vector3D(
      // 1000000, 1000000, 1000000), (float) 1.0, Color.blue(), Color.black(),
      // true)); // With normals
      //
      // }

      // if (false) { // Adding and deleting marks
      //
      // final int time = 1; // SECS
      //
      // final GTask markTask = new GTask() {
      // ArrayList<Mark> _marks = new ArrayList<Mark>();
      //
      //
      // int randomInt(final int max) {
      // return (int) (Math.random() * max);
      // }
      //
      //
      // @Override
      // public void run(final G3MContext context) {
      // final double lat = randomInt(180) - 90;
      // final double lon = randomInt(360) - 180;
      //
      // final Mark m1 = new Mark("RANDOM MARK", new
      // URL("http://glob3m.glob3mobile.com/icons/markers/g3m.png", false),
      // Geodetic3D.fromDegrees(lat, lon, 0), AltitudeMode.RELATIVE_TO_GROUND,
      // 1e9);
      // marksRenderer.addMark(m1);
      //
      // _marks.add(m1);
      // if (_marks.size() > 5) {
      //
      // marksRenderer.removeAllMarks();
      //
      // for (int i = 0; i < _marks.size(); i++) {
      // _marks.get(i).dispose();
      // }
      //
      //
      // _marks.clear();
      //
      // }
      //
      // }
      // };
      //
      // builder.addPeriodicalTask(new
      // PeriodicalTask(TimeInterval.fromSeconds(time), markTask));
      // }

      // if (false) {
      //
      // final GInitializationTask initializationTask = new
      // GInitializationTask() {
      //
      // @Override
      // public void run(final G3MContext context) {
      //
      // final IBufferDownloadListener listener = new
      // IBufferDownloadListener() {
      //
      // @Override
      // public void onError(final URL url) {
      // // TODO Auto-generated method stub
      //
      // }
      //
      //
      // @Override
      // public void onDownload(final URL url,
      // final IByteBuffer buffer,
      // final boolean expired) {
      // // TODO Auto-generated method stub
      //
      // final Shape shape = SceneJSShapesParser.parseFromBSON(buffer,
      // URL.FILE_PROTOCOL + "2029/", true,
      // Geodetic3D.fromDegrees(0, 0, 0), AltitudeMode.ABSOLUTE);
      //
      // shapesRenderer.addShape(shape);
      // }
      //
      //
      // @Override
      // public void onCanceledDownload(final URL url,
      // final IByteBuffer buffer,
      // final boolean expired) {
      // // TODO Auto-generated method stub
      //
      // }
      //
      //
      // @Override
      // public void onCancel(final URL url) {
      // // TODO Auto-generated method stub
      //
      // }
      // };
      //
      // context.getDownloader().requestBuffer(new URL(URL.FILE_PROTOCOL +
      // "2029/2029.bson"), 1000, TimeInterval.forever(),
      // true, listener, true);
      //
      //
      // }
      //
      //
      // @Override
      // public boolean isDone(final G3MContext context) {
      // // TODO Auto-generated method stub
      // return true;
      // }
      //
      // };
      //
      // builder.setInitializationTask(initializationTask);
      //
      // }

      if (true) {

         final int time = 10; // SECS

         final GTask elevationTask = new GTask() {

            ElevationDataProvider _elevationDataProvider1 = new SingleBillElevationDataProvider(new URL(
                                                                   "file:///full-earth-2048x1024.bil", false),
                                                                   Sector.fullSphere(), new Vector2I(2048, 1024));


            @Override
            public void run(final G3MContext context) {
               final PlanetRenderer pr = _g3mWidget.getG3MWidget().getPlanetRenderer();

               final Random r = new Random();

               final int i = r.nextInt() % 4;
               switch (i) {
                  case 0:
                     pr.setElevationDataProvider(_elevationDataProvider1, false);
                     break;
                  case 1:

                     final ElevationDataProvider _elevationDataProvider2 = new SingleBillElevationDataProvider(new URL(
                              "file:///caceres-2008x2032.bil", false), Sector.fromDegrees(39.4642996294239623,
                              -6.3829977122432933, 39.4829891936013553, -6.3645288909498845), new Vector2I(2008, 2032), 0);


                     pr.setElevationDataProvider(_elevationDataProvider2, true);
                     break;
                  case 2:
                     pr.setVerticalExaggeration(r.nextInt() % 5);
                     break;
                  case 3:
                     pr.setElevationDataProvider(null, false);
                     break;

                  default:
                     break;
               }

               final ElevationDataProvider edp = pr.getElevationDataProvider();
               if (edp != null) {
                  edp.setEnabled((r.nextInt() % 2) == 0);
               }
            }
         };

         builder.addPeriodicalTask(new PeriodicalTask(TimeInterval.fromSeconds(time), elevationTask));

      }

      //      _g3mWidget = builder.createWidget();
      //      _placeHolder = (RelativeLayout) findViewById(R.id.g3mWidgetHolder);
      //      _placeHolder.addView(_g3mWidget);

   }


   private void onCreateCache() {
      //Sector Mining
      final Geodetic2D lower = new Geodetic2D( //
               Angle.fromDegrees(-17.2605373678851670), //
               Angle.fromDegrees(145.4760907919427950));
      final Geodetic2D upper = new Geodetic2D( //
               Angle.fromDegrees(-17.2423142646939311), //
               Angle.fromDegrees(145.4950606689779420));

      final Sector demSector = new Sector(lower, upper);


      //Sector Farm

      final Geodetic2D lowerFarm = new Geodetic2D( //
               Angle.fromDegrees(-17.1596722413563398), //
               Angle.fromDegrees(144.9450185314975954));
      final Geodetic2D upperFarm = new Geodetic2D( //
               Angle.fromDegrees(-17.1386328271386219), //
               Angle.fromDegrees(144.9843876856850784));


      final Sector sectorFarm = new Sector(lowerFarm, upperFarm);


      final G3MBuilder_Android builder = new G3MBuilder_Android(this);
      builder.setLogFPS(true);
      //final Planet planet = Planet.createEarth();

      final Planet planet = Planet.createSphericalEarth();
      builder.setPlanet(planet);


      //   builder.setShownSector(demSector);

      final PlanetRendererBuilder planetRendererBuilder = builder.getPlanetRendererBuilder();

      final LayerSet layerset = new LayerSet();
      final WMSLayer img2011 = new WMSLayer("redearthmapping:srhoj_satImg2011", new URL(
               "http://redearthmesh.com:8080/geoserver/redearthmapping/wms?", false), WMSServerVersion.WMS_1_3_0, sectorFarm,
               "image/jpeg", "EPSG:4326", "raster", false, null, TimeInterval.fromDays(30), true,
               LayerTilesRenderParameters.createDefaultWGS84(sectorFarm, 0, 3));//   reateDefaultMercator(0, 20));
      layerset.addLayer(img2011);

      planetRendererBuilder.setLayerSet(layerset);
      //  planetRendererBuilder.setLayerSet(MiningLayerBuilder.createMiningLayerSet());
      planetRendererBuilder.setRenderDebug(true);

      _g3mWidget = builder.createWidget();


      _g3mWidget.getG3MWidget().getPlanetRenderer().acceptTileVisitor(new TileVisitorCache_Android(_g3mWidget.getG3MContext()),
               sectorFarm, 0, 3);


      _g3mWidget.getG3MContext().getLogger().logInfo("Request for Precaching has been sent. Waiting responses...");

      _g3mWidget.setAnimatedCameraPosition(new Geodetic3D(Angle.fromDegrees(Double.valueOf(-17.14)),
               Angle.fromDegrees(Double.valueOf(144.96)), 10000));
      _placeHolder = (RelativeLayout) findViewById(R.id.g3mWidgetHolder);
      _placeHolder.addView(_g3mWidget);
   }


   private void onCreateGolfTic() {
      final float _VerticalExaggeration = 20f;
      final double DELTA_HEIGHT = -700.905;
      // final double DELTA_HEIGHT = 0;


      final LayerSet layerSet = new LayerSet();


      final GoogleLayer satelite = new GoogleLayer("http://khm1.googleapis.com/kh?v=141&hl=en-US", TimeInterval.fromDays(30));
      layerSet.addLayer(satelite);


      final G3MBuilder_Android builder = new G3MBuilder_Android(this);
      //  builder.setPlanet(Planet.createSphericalEarth());
      builder.getPlanetRendererBuilder().setLayerSet(layerSet);


      builder.setBackgroundColor(Color.fromRGBA255(185, 221, 209, 255).muchDarker());


      final Geodetic2D lower = new Geodetic2D( //
               Angle.fromDegrees(39.4167667), //
               Angle.fromDegrees(-6.3833833));


      final Geodetic2D upper = new Geodetic2D( //
               Angle.fromDegrees(39.4292667), //
               Angle.fromDegrees(-6.3737000));

      final Sector golfSector = new Sector(lower, upper);

      // NROWS          1335
      // NCOLS          2516
      //new Vector2I(2735, 1841)

      final ElevationDataProvider dem = new SingleBillElevationDataProvider(new URL("file:///campoGolf-1.bil", false),
               golfSector, new Vector2I(1841, 2735), DELTA_HEIGHT);

      final ElevationDataProvider demAux = new SingleBillElevationDataProvider(
               new URL("file:///full-earth-2048x1024.bil", false), golfSector, new Vector2I(2048, 1024), DELTA_HEIGHT);

      builder.getPlanetRendererBuilder().setElevationDataProvider(dem);
      builder.getPlanetRendererBuilder().setVerticalExaggeration(_VerticalExaggeration);

      //The sector is shrinked to adjust the projection of
      builder.setShownSector(golfSector.shrinkedByPercent(0.1f));

      _g3mWidget = builder.createWidget();

      // set the initial camera position to be into the valley
      final Geodetic3D position = Geodetic3D.fromDegrees(39.423, -6.38, 5000);
      final Angle heading = Angle.fromDegrees(51.146970);
      final Angle pitch = Angle.fromDegrees(69.137225);
      _g3mWidget.setCameraPosition(position);
      _g3mWidget.setCameraHeading(Angle.zero());
      _g3mWidget.setCameraPitch(Angle.zero());

      _placeHolder = (RelativeLayout) findViewById(R.id.g3mWidgetHolder);
      _placeHolder.addView(_g3mWidget);
   }


   @Override
   public boolean onCreateOptionsMenu(final Menu menu) {
      // Inflate the menu; this adds items to the action bar if it is present.
      getMenuInflater().inflate(R.menu.main, menu);
      return true;
   }

}
