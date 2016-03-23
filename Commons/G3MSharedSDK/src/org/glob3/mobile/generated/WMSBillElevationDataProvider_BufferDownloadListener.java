package org.glob3.mobile.generated; 
public class WMSBillElevationDataProvider_BufferDownloadListener extends IBufferDownloadListener
{
  private final Sector _sector ;
  private final int _width;
  private final int _height;

  private IElevationDataListener _listener;
  private final boolean _autodeleteListener;



  public WMSBillElevationDataProvider_BufferDownloadListener(Sector sector, Vector2I extent, IElevationDataListener listener, boolean autodeleteListener)
  {
     _sector = new Sector(sector);
     _width = extent._x;
     _height = extent._y;
     _listener = listener;
     _autodeleteListener = autodeleteListener;

  }

  public final void onDownload(URL url, IByteBuffer buffer, boolean expired)
  {
    final Vector2I resolution = new Vector2I(_width, _height);
    ShortBufferElevationData elevationData = BilParser.parseBil16(_sector, resolution, buffer);
    if (buffer != null)
       buffer.dispose();

    if (elevationData == null)
    {
      _listener.onError(_sector, resolution);
    }
    else
    {
      _listener.onData(_sector, resolution, elevationData);
    }

    if (_autodeleteListener)
    {
      if (_listener != null)
         _listener.dispose();
      _listener = null;
    }
  }

  public final void onError(URL url)
  {
    final Vector2I resolution = new Vector2I(_width, _height);

    _listener.onError(_sector, resolution);
    if (_autodeleteListener)
    {
      if (_listener != null)
         _listener.dispose();
      _listener = null;
    }
  }

  public final void onCancel(URL url)
  {

  }

  public final void onCanceledDownload(URL url, IByteBuffer data, boolean expired)
  {

  }


}
//ElevationData* WMSBillElevationDataProvider::createSubviewOfElevationData(ElevationData* elevationData,
//                                                                          const Sector& sector,
//                                                                          const Vector2I& extent) const{
//  return new SubviewElevationData(elevationData,
//                                  false,
//                                  sector,
//                                  extent,
//                                  false);
//}