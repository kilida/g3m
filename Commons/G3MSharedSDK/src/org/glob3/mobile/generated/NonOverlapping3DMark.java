package org.glob3.mobile.generated; 
import java.util.*;

public class NonOverlapping3DMark
{

    /*added things here:*/

    //unlike for 2D, not every node has to have an anchor, so make 3D mark a node, where some
    //may be anchors and some may not be.
    private boolean _isAnchor;
    private boolean _isVisited; //for graph traversals

    //shape for anchor and regular nodes:
    private Shape _anchorShape;
    private Shape _nodeShape;
    private ShapesRenderer _shapesRenderer;

    //nodes can have multiple nodes they are attached to, call these neighbors
    //edges go from this node to neighbor nodes
    private java.util.ArrayList<NonOverlapping3DMark> _neighbors = new java.util.ArrayList<NonOverlapping3DMark>();
    private NonOverlapping3DMark _anchor; //anchor also included in neighbors. node can only have one anchor

    private float _springLengthInMeters;

    private Vector3D _cartesianPos;
    private Geodetic3D _geoPosition ;

    private float _dX; //Velocity vector (pixels per second)
    private float _dY;
    private float _dZ;
    private float _fX; //Applied Force
    private float _fY;
    private float _fZ;
    private float _tX; //current translation (cumulative dX, dY, dX)
    private float _tY;
    private float _tZ;

    private final float _springK;
    private final float _maxSpringLength;
    private final float _minSpringLength;
    private final float _electricCharge;
    private final float _maxWidgetSpeedInPixelsPerSecond;
    private final float _resistanceFactor;
    private final float _minWidgetSpeedInPixelsPerSecond;



    public NonOverlapping3DMark(Shape anchorShape, Shape nodeShape, Geodetic3D position, ShapeTouchListener touchListener, float springLengthInMeters, float springK, float maxSpringLength, float minSpringLength, float electricCharge, float maxWidgetSpeedInPixelsPerSecond, float minWidgetSpeedInPixelsPerSecond)
    {
       this(anchorShape, nodeShape, position, touchListener, springLengthInMeters, springK, maxSpringLength, minSpringLength, electricCharge, maxWidgetSpeedInPixelsPerSecond, minWidgetSpeedInPixelsPerSecond, 0.95f);
    }
    public NonOverlapping3DMark(Shape anchorShape, Shape nodeShape, Geodetic3D position, ShapeTouchListener touchListener, float springLengthInMeters, float springK, float maxSpringLength, float minSpringLength, float electricCharge, float maxWidgetSpeedInPixelsPerSecond)
    {
       this(anchorShape, nodeShape, position, touchListener, springLengthInMeters, springK, maxSpringLength, minSpringLength, electricCharge, maxWidgetSpeedInPixelsPerSecond, 35.0f, 0.95f);
    }
    public NonOverlapping3DMark(Shape anchorShape, Shape nodeShape, Geodetic3D position, ShapeTouchListener touchListener, float springLengthInMeters, float springK, float maxSpringLength, float minSpringLength, float electricCharge)
    {
       this(anchorShape, nodeShape, position, touchListener, springLengthInMeters, springK, maxSpringLength, minSpringLength, electricCharge, 1000.0f, 35.0f, 0.95f);
    }
    public NonOverlapping3DMark(Shape anchorShape, Shape nodeShape, Geodetic3D position, ShapeTouchListener touchListener, float springLengthInMeters, float springK, float maxSpringLength, float minSpringLength)
    {
       this(anchorShape, nodeShape, position, touchListener, springLengthInMeters, springK, maxSpringLength, minSpringLength, 3000000.0f, 1000.0f, 35.0f, 0.95f);
    }
    public NonOverlapping3DMark(Shape anchorShape, Shape nodeShape, Geodetic3D position, ShapeTouchListener touchListener, float springLengthInMeters, float springK, float maxSpringLength)
    {
       this(anchorShape, nodeShape, position, touchListener, springLengthInMeters, springK, maxSpringLength, 0.0f, 3000000.0f, 1000.0f, 35.0f, 0.95f);
    }
    public NonOverlapping3DMark(Shape anchorShape, Shape nodeShape, Geodetic3D position, ShapeTouchListener touchListener, float springLengthInMeters, float springK)
    {
       this(anchorShape, nodeShape, position, touchListener, springLengthInMeters, springK, 0.0f, 0.0f, 3000000.0f, 1000.0f, 35.0f, 0.95f);
    }
    public NonOverlapping3DMark(Shape anchorShape, Shape nodeShape, Geodetic3D position, ShapeTouchListener touchListener, float springLengthInMeters)
    {
       this(anchorShape, nodeShape, position, touchListener, springLengthInMeters, 7.0f, 0.0f, 0.0f, 3000000.0f, 1000.0f, 35.0f, 0.95f);
    }
    public NonOverlapping3DMark(Shape anchorShape, Shape nodeShape, Geodetic3D position, ShapeTouchListener touchListener)
    {
       this(anchorShape, nodeShape, position, touchListener, 1000.0f, 7.0f, 0.0f, 0.0f, 3000000.0f, 1000.0f, 35.0f, 0.95f);
    }
    public NonOverlapping3DMark(Shape anchorShape, Shape nodeShape, Geodetic3D position)
    {
       this(anchorShape, nodeShape, position, null, 1000.0f, 7.0f, 0.0f, 0.0f, 3000000.0f, 1000.0f, 35.0f, 0.95f);
    }
    public NonOverlapping3DMark(Shape anchorShape, Shape nodeShape, Geodetic3D position, ShapeTouchListener touchListener, float springLengthInMeters, float springK, float maxSpringLength, float minSpringLength, float electricCharge, float maxWidgetSpeedInPixelsPerSecond, float minWidgetSpeedInPixelsPerSecond, float resistanceFactor)
    
    {
       _geoPosition = new Geodetic3D(position);
       _springLengthInMeters = springLengthInMeters;
       _cartesianPos = null;
       _dX = 0F;
       _dY = 0F;
       _dZ = 0F;
       _fX = 0F;
       _fY = 0F;
       _fZ = 0F;
       _tX = 0F;
       _tY = 0F;
       _tZ = 0F;
       _springK = springK;
       _maxSpringLength = maxSpringLength;
       _minSpringLength = minSpringLength;
       _electricCharge = electricCharge;
       _maxWidgetSpeedInPixelsPerSecond = maxWidgetSpeedInPixelsPerSecond;
       _resistanceFactor = resistanceFactor;
       _minWidgetSpeedInPixelsPerSecond = minWidgetSpeedInPixelsPerSecond;
    
        //Initialize shape to something
        _anchorShape = new EllipsoidShape(new Geodetic3D(Angle.fromDegrees(0), Angle.fromDegrees(0), 5), AltitudeMode.ABSOLUTE, new Vector3D(100000, 100000, 100000), (short) 16, 0, false, false, Color.fromRGBA((float).5,(float) 1, (float).5, (float).9));
    
        _nodeShape = new EllipsoidShape(new Geodetic3D(Angle.fromDegrees(0), Angle.fromDegrees(0), 5), AltitudeMode.ABSOLUTE, new Vector3D(100000, 100000, 100000), (short) 16, 0, false, false, Color.fromRGBA((float).5, (float)0, (float).5, (float) .9));
    
        //set value of shape to the thing passed in
       // *_anchorShape = *anchorShape;
       // *_nodeShape = *nodeShape;
    
        (_nodeShape).setPosition(_geoPosition);
        (_anchorShape).setPosition(_geoPosition);
    
        _shapesRenderer = new ShapesRenderer();
        _shapesRenderer.addShape(_nodeShape);
    
    }

    public void dispose()
    {
        if (_cartesianPos != null)
           _cartesianPos.dispose();
        if (_anchorShape != null)
           _anchorShape.dispose();
        if (_nodeShape != null)
           _nodeShape.dispose();
    }
    public final Geodetic3D getPos()
    {
        return _geoPosition;
    }

    public final boolean isVisited()
    {
        return _isVisited;
    }
    public final boolean isAnchor()
    {
        return _isAnchor;
    }
    public final java.util.ArrayList<NonOverlapping3DMark> getNeighbors()
    {
        return _neighbors;
    }
    public final void setVisited(boolean visited)
    {
        _isVisited = visited;
    }
    public final void addEdge(NonOverlapping3DMark n)
    {
        _neighbors.add(n);
         n.addNeighbor(n);
    }
    public final void setAsAnchor()
    {
        _isAnchor = true;
        _shapesRenderer.removeAllShapes(false); //no.. this deletes shapes
        _shapesRenderer.addShape(_anchorShape);
    }
    public final void addNeighbor(NonOverlapping3DMark n)
    {
        _neighbors.add(n);
    }
    public final void addAnchor(NonOverlapping3DMark anchor)
    {
        _neighbors.add(anchor);
        anchor.addNeighbor(this);
        _anchor = anchor;
        anchor.setAsAnchor();
    }
    public final NonOverlapping3DMark getAnchor()
    {
        return _anchor;
    }
    public final Shape getShape()
    {
        if(_isAnchor)
           return _anchorShape;
        else
           return _nodeShape;
    }
    //MarkWidget getWidget() const;

    public final Vector3D clampVector(Vector3D v, float min, float max)
    {
        float l = (float) v.length();
        if(l < min)
        {
            return (v.normalized()).times(min);
        }
        if(l > max)
        {
            return (v.normalized()).times(max);
        }
		return v;
    }

    public final Vector3D getCartesianPosition(Planet planet)
    {
       // if (_cartesianPos == NULL){
        Vector3D translation = new Vector3D(_tX, _tY, _tZ);
        if (this.getAnchor() != null)
        {
            _cartesianPos = new Vector3D(getAnchor().getCartesianPosition(planet).add(translation));
            _anchorShape.setPosition(planet.toGeodetic3D(_cartesianPos));
            _nodeShape.setPosition(planet.toGeodetic3D(_cartesianPos));
    
        }
        else
        {
            _cartesianPos = new Vector3D(planet.toCartesian(_geoPosition).add(translation));
            _anchorShape.setPosition(planet.toGeodetic3D(_cartesianPos));
            _nodeShape.setPosition(planet.toGeodetic3D(_cartesianPos));
        }
        //}
        return _cartesianPos;
    }

//C++ TO JAVA CONVERTER TODO TASK: The implementation of the following method could not be found:
//    void computeAnchorScreenPos(Camera cam, Planet planet);

    //Vector2F getScreenPos() const{ return _widget.getScreenPos();}
    //Vector2F getAnchorScreenPos() const{ return _anchorWidget.getScreenPos();}

    public final void render(G3MRenderContext rc, GLState glState)
    {
        getCartesianPosition(rc.getPlanet());
        _shapesRenderer.render(rc, glState);
    }

    public final void applyCoulombsLaw(NonOverlapping3DMark that, Planet planet)
    {
        //get 3d positionf or both
        Vector3D pos = getCartesianPosition(planet);
        Vector3D d = getCartesianPosition(planet).sub(that.getCartesianPosition(planet)); //.normalized();
    
        double distance = d.length();
        Vector3D direction = d.normalized();
        //float k = 5;
        float strength = (float)(this._electricCharge * that._electricCharge/(distance *distance));
        if(distance < .01) //right on top of each other, pull them apart by a small random force before doing actual calculation
        {
            strength = 1F;
            Vector3D force = (new Vector3D(Math.random()*100 % 5, Math.random()*100 % 5, Math.random()*100 % 5)).times(strength);
            this.applyForce((float) force._x, (float) force._y, (float) force._z);
    }
        else
        {
             Vector3D force = direction.times(strength);
             this.applyForce((float) force._x, (float) force._y, (float) force._z);
        }
    
        //force from center of planet: - TODO: it's making it go in the z direction instead of x direction?? why?
        Vector3D d2 = (getCartesianPosition(planet)).normalized();
        double distance2 = d2.length();
        float planetCharge = 1F;
        Vector3D direction2 = d2.normalized();
        float strength2 = (float)(planetCharge / distance2 *distance2);
        Vector3D force2 = direction2.times(strength2);
    
        this.applyForce((float) force2._z, (float) force2._y, (float) force2._x); //why does it do what is expected only if I swap x and z...?
      //  this->applyForce(force._x, force._y, force._z);
    
    
    }
    public final void applyCoulombsLawFromAnchor(NonOverlapping3DMark that, Planet planet)
    {
    
        Vector3D dAnchor = getCartesianPosition(planet).sub(that.getCartesianPosition(planet));
    
        double distanceAnchor = dAnchor.length() + 0.001;
        Vector3D directionAnchor = dAnchor.div((float) distanceAnchor);
    
        float strengthAnchor = (float)(this._electricCharge * that._electricCharge / (distanceAnchor * distanceAnchor));
    
        this.applyForce((float) directionAnchor._x * strengthAnchor, (float) directionAnchor._y * strengthAnchor, (float) directionAnchor._z);
    }

    public final void applyHookesLaw(Planet planet)
    {
        //if(getAnchor() != NULL) {
        for(int i = 0; i < this._neighbors.size(); i++)
        {
            Vector3D d = getCartesianPosition(planet).sub(_neighbors.get(i).getCartesianPosition(planet));
            double mod = d.length();
            double displacement = _springLengthInMeters - mod;
            if(Math.abs(mod) > 100) //only do this if distance is above some threshold
            {
            Vector3D direction = d.div((float)mod);
          // float k = _electricCharge/5;
            float force = (float)(_springK * displacement)/1000000;
    
            applyForce((float)(direction._x * force), (float)(direction._y * force), (float) direction._z * force); //swap x and z here??
            }
        }
       // }
    }

    public final void applyForce(float x, float y)
    {
        _fX += x;
        _fY += y;
    }

    public final void applyForce(float x, float y, float z)
    {
        _fX += x;
        _fY += y;
        _fZ += z;
    }

    public final void updatePositionWithCurrentForce(double elapsedMS, float viewportWidth, float viewportHeight, Planet planet)
    {
    
        Vector3D oldVelocity = new Vector3D(_dX, _dY, _dZ);
        Vector3D force = new Vector3D(_fX, _fY, _fZ);
    
        //Assuming Widget Mass = 1.0
        float time = (float)(elapsedMS); // / 1000.0);
        Vector3D velocity = oldVelocity.add(force.times(time)).times(_resistanceFactor); //Resistance force applied as x0.85
    
        //testing this out:
        _dX = (float) velocity._x;
        _dY = (float) velocity._y;
        _dZ = (float) velocity._z;
    
    
        //Clamping Velocity
       /* double velocityPPS = velocity.length();
        if (velocityPPS > _maxWidgetSpeedInPixelsPerSecond){
            _dX = (float)(velocity._x * (_maxWidgetSpeedInPixelsPerSecond / velocityPPS));
            _dY = (float)(velocity._y * (_maxWidgetSpeedInPixelsPerSecond / velocityPPS));
            _dZ = (float)(velocity._z * (_maxWidgetSpeedInPixelsPerSecond / velocityPPS));
       
        } else{
            if (velocityPPS < _minWidgetSpeedInPixelsPerSecond){
                _dX = 0.0;
                _dY = 0.0;
                _dZ = 0.0;
            } else{
                //Normal case
                _dX = (float)velocity._x;
                _dY = (float)velocity._y;
                _dZ = (float)velocity._z;
            }
        }*/
    
        //Update position
        Vector3D position = getCartesianPosition(planet);
    
        float newX = (float) position._x + (_dX * time);
        float newY = (float) position._y + (_dY * time);
        float newZ = (float) position._z + (_dZ * time);
    
        //update translation
        _tX+=_dX *time;
        _tY+=_dY *time;
        _tZ+=_dZ *time;
    
    
        //clamp translation
        //Vector3D temp_translation = Vector3D(_tX, _tY, _tZ);
       // Vector3D translation = clampVector(temp_translation, -1000000000, 1000000000);
        Vector3D translation = new Vector3D(_tX, _tY, _tZ);
    
    
    
        //find the maximum, but clamped spring length - TODO this is wrong
        float springx = 0F;
        float springy = 0F;
        float springz = 0F;
    
        //TODO
       /* for(int i = 0; i < _neighbors.size(); i++) {
            Vector3D anchorPos = _neighbors[i]->getCartesianPosition(planet);
            Vector3D temp_spring = Vector3D(newX,newY,newZ).sub(anchorPos);
            Vector3D spring = clampVector(temp_spring, _minSpringLength, _maxSpringLength);
            if(spring.length() > Vector3D(springx, springy, springz).length()) {
                springx = spring._x;
                springy = spring._y;
                springz = spring._z;
            }
        }*/
    
    
      /* if(this->getAnchor() != NULL) {
      
        Vector3D anchorPos = getAnchor()->getCartesianPosition(planet); //getScreenPos();
      
        Vector3D temp_spring = Vector3D(newX,newY, newZ).sub(anchorPos);
        Vector3D spring = clampVector(temp_spring, _minSpringLength, _maxSpringLength);
        Vector3D finalPos = anchorPos.add(spring);
        _anchorShape->setTranslation(finalPos);
        _nodeShape->setTranslation(finalPos);
       // _widget.set3DPos(  finalPos._x, finalPos._y, finalPos._z);
        //_widget.clampPositionInsideScreen((int)viewportWidth, (int)viewportHeight, 5); // 5 pixels of margin
       }
       */
        _anchorShape.setTranslation(translation);
        _nodeShape.setTranslation(translation); //TODO: spring??
    
        //Force has been applied and must be reset
        _fX = 0F;
        _fY = 0F;
        _fZ = 0F;
    
    
    }

    public final void onResizeViewportEvent(int width, int height)
    {
        //TODO
    }

    public final void resetShapePositionVelocityAndForce()
    {
         _dX = 0F;
         _dY = 0F;
         _dZ = 0F;
         _fX = 0F;
         _fY = 0F;
         _fZ = 0F;
         _tX = 0F;
         _tY = 0F;
         _tZ = 0F;
        _anchorShape.setPosition(_geoPosition);
        _nodeShape.setPosition(_geoPosition);
     }

    public final boolean onTouchEvent(float x, float y)
    {
        //TODO
        return false;
    }

}