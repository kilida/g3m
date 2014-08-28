package org.glob3.mobile.generated; 
public class DTT_IImageBuilderListener implements IImageBuilderListener
{


  private DefaultTileTexturizer _defaultTileTesturizer;


  public DTT_IImageBuilderListener(DefaultTileTexturizer defaultTileTesturizer)
  {
     _defaultTileTesturizer = defaultTileTesturizer;
  }

  public void dispose()
  {
  }

  public final void imageCreated(IImage image, String imageName)
  {
    _defaultTileTesturizer.setDefaultBackGroundImage(image);
    _defaultTileTesturizer.setDefaultBackGroundImageName(imageName);
    _defaultTileTesturizer.setDefaultBackGroundImageLoaded(true);
  }

  public final void onError(String error)
  {
    //Exception
  }
}