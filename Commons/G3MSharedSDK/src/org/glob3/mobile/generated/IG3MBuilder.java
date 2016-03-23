package org.glob3.mobile.generated; 
//
//  IG3MBuilder.cpp
//  G3MiOSSDK
//
//  Created by Mari Luz Mateo on 20/11/12.
//
//

//
//  IG3MBuilder.hpp
//  G3MiOSSDK
//
//  Created by Mari Luz Mateo on 20/11/12.
//
//


//class GL;
//class IStorage;
//class IDownloader;
//class IThreadUtils;
//class ICameraActivityListener;
//class CameraRenderer;
//class ICameraConstrainer;
//class Color;
//class GInitializationTask;
//class PeriodicalTask;
//class G3MWidget;
//class TileRendererBuilder;
//class Planet;
//class Renderer;
//class WidgetUserData;

public abstract class IG3MBuilder
{

  private GL _gl;
  private IDownloader _downloader;
  private IThreadUtils _threadUtils;
  private ICameraActivityListener _cameraActivityListener;
  private Planet _planet; // REMOVED FINAL WORD BY CONVERSOR RULE
  private java.util.ArrayList<ICameraConstrainer> _cameraConstraints;
  private CameraRenderer _cameraRenderer;
  private Color _backgroundColor;
  private TileRendererBuilder _tileRendererBuilder;
  private Renderer _busyRenderer;
  private java.util.ArrayList<Renderer> _renderers;
  private GInitializationTask _initializationTask;
  private boolean _autoDeleteInitializationTask;
  private java.util.ArrayList<PeriodicalTask> _periodicalTasks;
  private boolean _logFPS;
  private boolean _logDownloaderStatistics;
  private WidgetUserData _userData;


  /**
   * Returns the _gl.
   *
   * @return _gl: GL*
   */
  private GL getGL()
  {
    if (_gl == null)
    {
      ILogger.instance().logError("Logic Error: _gl not initialized");
    }
  
    return _gl;
  }

  /**
   * Returns the _downloader. If it does not exist, it will be default initializated.
   *
   * @return _downloader: IDownloader*
   */
  private IDownloader getDownloader()
  {
    if (_downloader == null)
    {
      _downloader = createDefaultDownloader();
    }
  
    return _downloader;
  }

  /**
   * Returns the _threadUtils. If it does not exist, it will be default initializated.
   *
   * @return _threadUtils: IThreadUtils*
   */
  private IThreadUtils getThreadUtils()
  {
    if (_threadUtils == null)
    {
      _threadUtils = createDefaultThreadUtils();
    }
  
    return _threadUtils;
  }

  /**
   * Returns the _cameraActivityListener. If it does not exist, it will be default initializated.
   *
   * @return _threadUtils: IThreadUtils*
   */
  private ICameraActivityListener getCameraActivityListener()
  {
    return _cameraActivityListener;
  }

  /**
   * Returns the _cameraConstraints list. If it does not exist, it will be default initializated.
   * @see IG3MBuilder#createDefaultCameraConstraints() 
   *
   * @return _cameraConstraints: std::vector<ICameraConstrainer*>
   */
  private java.util.ArrayList<ICameraConstrainer> getCameraConstraints()
  {
    if (_cameraConstraints == null)
    {
      _cameraConstraints = createDefaultCameraConstraints();
    }
  
    return _cameraConstraints;
  }

  /**
   * Returns the _cameraRenderer. If it does not exist, it will be default initializated.
   * @see IG3MBuilder#createDefaultCameraRenderer()
   *
   * @return _cameraRenderer: CameraRenderer*
   */
  private CameraRenderer getCameraRenderer()
  {
    if (_cameraRenderer == null)
    {
      _cameraRenderer = createDefaultCameraRenderer();
    }
  
    return _cameraRenderer;
  }

  /**
   * Returns the _busyRenderer. If it does not exist, it will be default initializated.
   *
   * @return _busyRenderer: Renderer*
   */
  private Renderer getBusyRenderer()
  {
    if (_busyRenderer == null)
    {
      _busyRenderer = new BusyMeshRenderer(Color.newFromRGBA((float)0, (float)0, (float)0, (float)1));
    }
  
    return _busyRenderer;
  }

  /**
   * Returns the _backgroundColor. If it does not exist, it will be default initializated.
   *
   * @return _backgroundColor: Color*
   */
  private Color getBackgroundColor()
  {
    if (_backgroundColor == null)
    {
      _backgroundColor = Color.newFromRGBA((float)0, (float)0.1, (float)0.2, (float)1);
    }
  
    return _backgroundColor;
  }

  /**
   * Returns the renderers list. If it does not exist, it will be default initializated.
   * @see IG3MBuilder#createDefaultRenderers()
   *
   * @return _renderers: std::vector<Renderer*>
   */
  private java.util.ArrayList<Renderer> getRenderers()
  {
    if (_renderers == null)
    {
      _renderers = createDefaultRenderers();
    }
    return _renderers;
  }

  /**
   * Returns the value of _logFPS flag.
   *
   * @return _logFPS: bool
   */
  private boolean getLogFPS()
  {
    return _logFPS;
  }

  /**
   * Returns the value of _logDownloaderStatistics flag.
   *
   * @return _logDownloaderStatistics: bool
   */
  private boolean getLogDownloaderStatistics()
  {
    return _logDownloaderStatistics;
  }

  /**
   * Returns the initialization task.
   *
   * @return _logDownloaderStatistics: GInitializationTask*
   */
  private GInitializationTask getInitializationTask()
  {
    return _initializationTask;
  }

  /**
   * Returns the value of _autoDeleteInitializationTask flag.
   *
   * @return _autoDeleteInitializationTask: bool
   */
  private boolean getAutoDeleteInitializationTask()
  {
    return _autoDeleteInitializationTask;
  }

  /**
   * Returns the array of periodical tasks. If it does not exist, it will be default initializated.
   * @see IG3MBuilder#createDefaultPeriodicalTasks()
   *
   * @return _periodicalTasks: std::vector<PeriodicalTask*>
   */
  private java.util.ArrayList<PeriodicalTask> getPeriodicalTasks()
  {
    if (_periodicalTasks == null)
    {
      _periodicalTasks = createDefaultPeriodicalTasks();
    }
    return _periodicalTasks;
  }

  /**
   * Returns the user data.
   *
   * @return _userData: WidgetUserData*
   */
  private WidgetUserData getUserData()
  {
    return _userData;
  }


  private java.util.ArrayList<ICameraConstrainer> createDefaultCameraConstraints()
  {
    java.util.ArrayList<ICameraConstrainer> cameraConstraints = new java.util.ArrayList<ICameraConstrainer>();
    SimpleCameraConstrainer scc = new SimpleCameraConstrainer();
    cameraConstraints.add(scc);
  
    return cameraConstraints;
  }
  private CameraRenderer createDefaultCameraRenderer()
  {
    CameraRenderer cameraRenderer = new CameraRenderer();
    final boolean useInertia = true;
    cameraRenderer.addHandler(new CameraSingleDragHandler(useInertia));
    final boolean processRotation = true;
    final boolean processZoom = true;
    cameraRenderer.addHandler(new CameraDoubleDragHandler(processRotation, processZoom));
    cameraRenderer.addHandler(new CameraRotationHandler());
    cameraRenderer.addHandler(new CameraDoubleTapHandler());
  
    return cameraRenderer;
  }
  private java.util.ArrayList<Renderer> createDefaultRenderers()
  {
    java.util.ArrayList<Renderer> renderers = new java.util.ArrayList<Renderer>();
  
    return renderers;
  }
  private java.util.ArrayList<PeriodicalTask> createDefaultPeriodicalTasks()
  {
    java.util.ArrayList<PeriodicalTask> periodicalTasks = new java.util.ArrayList<PeriodicalTask>();
  
    return periodicalTasks;
  }

  private void pvtSetInitializationTask(GInitializationTask initializationTask, boolean autoDeleteInitializationTask)
  {
    if (_initializationTask != null)
    {
      ILogger.instance().logError("LOGIC ERROR: _initializationTask already initialized");
      return;
    }
    if (initializationTask == null)
    {
      ILogger.instance().logError("LOGIC ERROR: initializationTask cannot be NULL");
      return;
    }
    _initializationTask = initializationTask;
    _autoDeleteInitializationTask = autoDeleteInitializationTask;
  }


  /**
   * Returns TRUE if the given renderer list contains, at least, an instance of 
   * the TileRenderer class. Returns FALSE if not.
   *
   * @return bool
   */
  private boolean containsTileRenderer(java.util.ArrayList<Renderer> renderers)
  {
    for (int i = 0; i < renderers.size(); i++)
    {
      if (renderers.get(i).isTileRenderer())
      {
        return true;
      }
    }
    return false;
  }

  protected IStorage _storage;


  /**
   * Returns the _storage. If it does not exist, it will be default initializated.
   *
   * @return _storage: IStorage*
   */
  protected final IStorage getStorage()
  {
    if (_storage == null)
    {
      _storage = createDefaultStorage();
    }
  
    return _storage;
  }


  /**
   * Creates the generic widget using all the parameters previously set.
   *
   * @return G3MWidget*
   */
  protected final G3MWidget create()
  {
    /**
     * If any renderers were set or added, the main renderer will be a composite renderer.
     *    If the renderers list does not contain a tileRenderer, it will be created and added.
     *    The renderers contained in the list, will be added to the main renderer.
     * If not, the main renderer will be made up of an only renderer (tileRenderer).
     */
    Renderer mainRenderer = null;
    if (getRenderers().size() > 0)
    {
      mainRenderer = new CompositeRenderer();
      if (!containsTileRenderer(getRenderers()))
      {
        ((CompositeRenderer) mainRenderer).addRenderer(getTileRendererBuilder().create());
      }
      for (int i = 0; i < getRenderers().size(); i++)
      {
        ((CompositeRenderer) mainRenderer).addRenderer(getRenderers().get(i));
      }
    }
    else
    {
      mainRenderer = getTileRendererBuilder().create();
    }
  
    G3MWidget g3mWidget = G3MWidget.create(getGL(), getStorage(), getDownloader(), getThreadUtils(), getCameraActivityListener(), getPlanet(), getCameraConstraints(), getCameraRenderer(), mainRenderer, getBusyRenderer(), getBackgroundColor(), getLogFPS(), getLogDownloaderStatistics(), getInitializationTask(), getAutoDeleteInitializationTask(), getPeriodicalTasks());
  
    g3mWidget.setUserData(getUserData());
  
    _gl = null;
    _storage = null;
    _downloader = null;
    _threadUtils = null;
    _cameraActivityListener = null;
    _planet = null;
    _cameraConstraints = null;
    _cameraRenderer = null;
    _renderers = null;
    _busyRenderer = null;
    _initializationTask = null;
    _periodicalTasks = null;
    _userData = null;
  
    return g3mWidget;
  }

  protected abstract IThreadUtils createDefaultThreadUtils();
  protected abstract IStorage createDefaultStorage();
  protected abstract IDownloader createDefaultDownloader();


  public IG3MBuilder()
  {
     _gl = null;
     _storage = null;
     _downloader = null;
     _threadUtils = null;
     _cameraActivityListener = null;
     _planet = null;
     _cameraConstraints = null;
     _cameraRenderer = null;
     _backgroundColor = null;
     _tileRendererBuilder = null;
     _busyRenderer = null;
     _renderers = null;
     _initializationTask = null;
     _autoDeleteInitializationTask = true;
     _periodicalTasks = null;
     _logFPS = false;
     _logDownloaderStatistics = false;
     _userData = null;
  }
  public void dispose()
  {
    if (_gl != null)
       _gl.dispose();
    if (_storage != null)
       _storage.dispose();
    if (_downloader != null)
       _downloader.dispose();
    if (_threadUtils != null)
       _threadUtils.dispose();
    if (_cameraActivityListener != null)
       _cameraActivityListener.dispose();
    if (_planet != null)
       _planet.dispose();
    if (_cameraConstraints != null)
    {
      for (int i = 0; i < _cameraConstraints.size(); i++)
      {
        if (_cameraConstraints.get(i) != null)
           _cameraConstraints.get(i).dispose();
      }
      _cameraConstraints = null;
    }
    if (_cameraRenderer != null)
       _cameraRenderer.dispose();
    if (_renderers != null)
    {
      for (int i = 0; i < _renderers.size(); i++)
      {
        if (_renderers.get(i) != null)
           _renderers.get(i).dispose();
      }
      _renderers = null;
    }
    if (_busyRenderer != null)
       _busyRenderer.dispose();
    if (_backgroundColor != null)
       _backgroundColor.dispose();
    if (_initializationTask != null)
       _initializationTask.dispose();
    if (_periodicalTasks != null)
    {
      for (int i = 0; i < _periodicalTasks.size(); i++)
      {
        if (_periodicalTasks.get(i) != null)
           _periodicalTasks.get(i).dispose();
      }
      _periodicalTasks = null;
    }
    if (_userData != null)
       _userData.dispose();
    if (_tileRendererBuilder != null)
       _tileRendererBuilder.dispose();
  }

  /**
   * Sets the _gl.
   *
   * @param gl - cannot be NULL.
   */
  public final void setGL(GL gl)
  {
    if (_gl != null)
    {
      ILogger.instance().logError("LOGIC ERROR: _gl already initialized");
      return;
    }
    if (gl == null)
    {
      ILogger.instance().logError("LOGIC ERROR: _gl cannot be NULL");
      return;
    }
    _gl = gl;
  }

  /**
   * Sets the _storage.
   *
   * @param storage
   */
  public final void setStorage(IStorage storage)
  {
    if (_storage != null)
    {
      ILogger.instance().logError("LOGIC ERROR: _storage already initialized");
      return;
    }
    _storage = storage;
  }

  /**
   * Sets the _downloader
   *
   * @param downloader - cannot be NULL.
   */
  public final void setDownloader(IDownloader downloader)
  {
    if (_downloader != null)
    {
      ILogger.instance().logError("LOGIC ERROR: _downloader already initialized");
      return;
    }
    if (downloader == null)
    {
      ILogger.instance().logError("LOGIC ERROR: _downloader cannot be NULL");
      return;
    }
    _downloader = downloader;
  }

  /**
   * Sets the _threadUtils
   *
   * @param threadUtils - cannot be NULL.
   */
  public final void setThreadUtils(IThreadUtils threadUtils)
  {
    if (_threadUtils != null)
    {
      ILogger.instance().logError("LOGIC ERROR: _threadUtils already initialized");
      return;
    }
    if (threadUtils == null)
    {
      ILogger.instance().logError("LOGIC ERROR: _threadUtils cannot be NULL");
      return;
    }
    _threadUtils = threadUtils;
  }

  /**
   * Sets the _cameraActivityListener
   *
   * @param cameraActivityListener - cannot be NULL.
   */
  public final void setCameraActivityListener(ICameraActivityListener cameraActivityListener)
  {
    if (_cameraActivityListener != null)
    {
      ILogger.instance().logError("LOGIC ERROR: _cameraActivityListener already initialized");
      return;
    }
    if (cameraActivityListener == null)
    {
      ILogger.instance().logError("LOGIC ERROR: cameraActivityListener cannot be NULL");
      return;
    }
    _cameraActivityListener = cameraActivityListener;
  }

  /**
   * Sets the _planet
   *
   * @param planet - cannot be NULL.
   */
  public final void setPlanet(Planet planet)
  {
    if (_planet != null)
    {
      ILogger.instance().logError("LOGIC ERROR: _planet already initialized");
      return;
    }
    if (planet == null)
    {
      ILogger.instance().logError("LOGIC ERROR: _planet cannot be NULL");
      return;
    }
    _planet = planet;
  }

  /**
   * Adds a new camera constraint to the constraints list.
   * The camera constraint list will be initializated with a default constraints set.
   * @see IG3MBuilder#createDefaultCameraConstraints()
   *
   * @param cameraConstraint - cannot be NULL.
   */
  public final void addCameraConstraint(ICameraConstrainer cameraConstraint)
  {
    if (cameraConstraint == null)
    {
      ILogger.instance().logError("LOGIC ERROR: trying to add a NULL cameraConstraint object");
      return;
    }
    getCameraConstraints().add(cameraConstraint);
  }

  /**
   * Sets the camera constraints list, ignoring the default camera constraints list 
   * and the camera constraints previously added, if added.
   *
   * @param cameraConstraints - std::vector<ICameraConstrainer*>
   */
  public final void setCameraConstrainsts(java.util.ArrayList<ICameraConstrainer> cameraConstraints)
  {
    if (_cameraConstraints != null)
    {
      ILogger.instance().logWarning("LOGIC WARNING: camera contraints previously set will be ignored and deleted");
      for (int i = 0; i < _cameraConstraints.size(); i++)
      {
        if (_cameraConstraints.get(i) != null)
           _cameraConstraints.get(i).dispose();
      }
      _cameraConstraints.clear();
    }
    else
    {
      _cameraConstraints = new java.util.ArrayList<ICameraConstrainer>();
    }
    for (int i = 0; i < cameraConstraints.size(); i++)
    {
      _cameraConstraints.add(cameraConstraints.get(i));
    }
  }

  /**
   * Sets the _cameraRenderer
   *
   * @param cameraRenderer - cannot be NULL.
   */
  public final void setCameraRenderer(CameraRenderer cameraRenderer)
  {
    if (_cameraRenderer != null)
    {
      ILogger.instance().logError("LOGIC ERROR: _cameraRenderer already initialized");
      return;
    }
    if (cameraRenderer == null)
    {
      ILogger.instance().logError("LOGIC ERROR: _cameraRenderer cannot be NULL");
      return;
    }
    _cameraRenderer = cameraRenderer;
  }

  /**
   * Sets the _backgroundColor
   *
   * @param backgroundColor - cannot be NULL.
   */
  public final void setBackgroundColor(Color backgroundColor)
  {
    if (_backgroundColor != null)
    {
      ILogger.instance().logError("LOGIC ERROR: _backgroundColor already initialized");
      return;
    }
    if (backgroundColor == null)
    {
      ILogger.instance().logError("LOGIC ERROR: _backgroundColor cannot be NULL");
      return;
    }
    _backgroundColor = backgroundColor;
  }

  /**
   * Sets the _busyRenderer
   *
   * @param busyRenderer - cannot be NULL.
   */
  public final void setBusyRenderer(Renderer busyRenderer)
  {
    if (_busyRenderer != null)
    {
      ILogger.instance().logError("LOGIC ERROR: _busyRenderer already initialized");
      return;
    }
    if (busyRenderer == null)
    {
      ILogger.instance().logError("LOGIC ERROR: _busyRenderer cannot be NULL");
      return;
    }
    _busyRenderer = busyRenderer;
  }

  /**
   * Adds a new renderer to the renderers list.
   * The renderers list will be initializated with a default renderers set (empty set at the moment).
   *
   * @param renderer - cannot be either NULL or an instance of TileRenderer
   */
  public final void addRenderer(Renderer renderer)
  {
    if (renderer == null)
    {
      ILogger.instance().logError("LOGIC ERROR: trying to add a NULL renderer object");
      return;
    }
    if (renderer.isTileRenderer())
    {
      ILogger.instance().logError("LOGIC ERROR: a new TileRenderer is not expected to be added");
      return;
    }
    getRenderers().add(renderer);
  }

  /**
   * Sets the renderers list, ignoring the default renderers list and the renderers
   * previously added, if added.
   * The renderers list must contain at least an instance of the TileRenderer class.
   *
   * @param renderers - std::vector<Renderer*>
   */
  public final void setRenderers(java.util.ArrayList<Renderer> renderers)
  {
    if (!containsTileRenderer(renderers))
    {
      ILogger.instance().logError("LOGIC ERROR: renderers list must contain at least an instance of the TileRenderer class");
      return;
    }
    if (_renderers != null)
    {
      ILogger.instance().logWarning("LOGIC WARNING: renderers previously set will be ignored and deleted");
      for (int i = 0; i < _renderers.size(); i++)
      {
        if (_renderers.get(i) != null)
           _renderers.get(i).dispose();
      }
      _renderers.clear();
    }
    else
    {
      _renderers = new java.util.ArrayList<Renderer>();
    }
    for (int i = 0; i < renderers.size(); i++)
    {
      _renderers.add(renderers.get(i));
    }
  }

  /**
   * Adds a new periodical task to the periodical tasks list.
   * The periodical tasks list will be initializated with a default periodical task set (empty set at the moment).
   *
   * @param periodicalTasks - cannot be NULL
   */
  public final void addPeriodicalTask(PeriodicalTask periodicalTask)
  {
    if (periodicalTask == null)
    {
      ILogger.instance().logError("LOGIC ERROR: trying to add a NULL periodicalTask object");
      return;
    }
    getPeriodicalTasks().add(periodicalTask);
  }

  /**
   * Sets the periodical tasks list, ignoring the default periodical tasks list and the
   * periodical tasks previously added, if added.
   *
   * @param periodicalTasks - std::vector<PeriodicalTask*>
   */
  public final void setPeriodicalTasks(java.util.ArrayList<PeriodicalTask> periodicalTasks)
  {
    if (_periodicalTasks != null)
    {
      ILogger.instance().logWarning("LOGIC WARNING: periodical tasks previously set will be ignored and deleted");
      for (int i = 0; i < _periodicalTasks.size(); i++)
      {
        if (_periodicalTasks.get(i) != null)
           _periodicalTasks.get(i).dispose();
      }
      _periodicalTasks.clear();
    }
    else
    {
      _periodicalTasks = new java.util.ArrayList<PeriodicalTask>();
    }
    for (int i = 0; i < periodicalTasks.size(); i++)
    {
      _periodicalTasks.add(periodicalTasks.get(i));
    }
  }

  /**
   * Sets the _logFPS
   *
   * @param logFPS
   */
  public final void setLogFPS(boolean logFPS)
  {
    _logFPS = logFPS;
  }

  /**
   * Sets the _logDownloaderStatistics
   *
   * @param logDownloaderStatistics
   */
  public final void setLogDownloaderStatistics(boolean logDownloaderStatistics)
  {
    _logDownloaderStatistics = logDownloaderStatistics;
  }

  /**
   * Sets the _userData
   *
   * @param userData - cannot be NULL.
   */
  public final void setUserData(WidgetUserData userData)
  {
    if (_userData != null)
    {
      ILogger.instance().logError("LOGIC ERROR: _userData already initialized");
      return;
    }
    if (userData == null)
    {
      ILogger.instance().logError("LOGIC ERROR: userData cannot be NULL");
      return;
    }
    _userData = userData;
  }
  public final void setInitializationTask(GInitializationTask initializationTask) {
    pvtSetInitializationTask(initializationTask,
                             true // parameter ignored in Java code 
);
  }


  /**
   * Returns the _planet. If it does not exist, it will be default initializated.
   *
   * @return _planet: const Planet*
   */
  public final Planet getPlanet()
  {
    if (_planet == null)
    {
      _planet = Planet.createEarth();
    }
    return _planet;
  }

  /**
   * Returns the _tileRendererBuilder. If it does not exist, it will be default initializated. 
   *
   * @return _tileRendererBuilder: TileRendererBuilder*
   */
  public final TileRendererBuilder getTileRendererBuilder()
  {
    if (_tileRendererBuilder == null)
    {
      _tileRendererBuilder = new TileRendererBuilder();
    }
  
    return _tileRendererBuilder;
  }
}
