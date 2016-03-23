package org.glob3.mobile.generated; 
public class G3MCBuilder_SceneDescriptionBufferListener extends IBufferDownloadListener
{
  private G3MCBuilder _builder;

  public G3MCBuilder_SceneDescriptionBufferListener(G3MCBuilder builder)
  {
     _builder = builder;
  }


  public final void onDownload(URL url, IByteBuffer buffer, boolean expired)
  {

    _builder.parseSceneDescription(buffer.getAsString(), url);
    if (buffer != null)
       buffer.dispose();
  }

  public final void onError(URL url)
  {
    ILogger.instance().logError("Can't download SceneJSON from %s", url.getPath());
  }

  public final void onCancel(URL url)
  {
    // do nothing
  }

  public final void onCanceledDownload(URL url, IByteBuffer buffer, boolean expired)
  {
    // do nothing
  }

}