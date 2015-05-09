//
//  SphericalPlanet.hpp
//  G3MiOSSDK
//
//  Created by Diego Gomez Deck on 31/05/12.
//  Copyright (c) 2012 IGO Software SL. All rights reserved.
//

#ifndef G3MiOSSDK_SphericalPlanet
#define G3MiOSSDK_SphericalPlanet

#include "MutableVector3D.hpp"
#include "Geodetic3D.hpp"
#include "Planet.hpp"

#include "Sphere.hpp"
#include "Sector.hpp"

class SphericalPlanet: public Planet {
private:
#ifdef C_CODE
  const Sphere _sphere;
#endif
#ifdef JAVA_CODE
  private Sphere _sphere;
#endif
  const Vector3D _radii;

  mutable MutableVector3D _origin;
  mutable MutableVector3D _initialPoint;
  mutable double          _dragRadius;
  mutable MutableVector3D _centerPoint;
  mutable MutableVector3D _centerRay;
  mutable MutableVector3D _initialPoint0;
  mutable MutableVector3D _initialPoint1;
  mutable MutableVector3D _lastDragAxis;
  mutable double          _lastDragRadians;
  mutable double          _lastDragRadiansStep;
  mutable double          _angleBetweenInitialRays;
  mutable double          _angleBetweenInitialPoints;
  mutable bool            _validSingleDrag;
  
  mutable double          _prevFactor;
  
  mutable double          _dragRadius0;
  mutable double          _dragRadius1;
  mutable double          _lastDoubleDragAngle;
  
  mutable MutableMatrix44D _dragMatrix;
  mutable MutableMatrix44D _doubleDragMatrix;
  mutable MutableVector3D  _finalPoint0;
  mutable MutableVector3D  _finalPoint1;
  mutable MutableVector3D  _draggedCameraPos;
  mutable MutableVector3D  _transformedFinalRay1;
  mutable MutableMatrix44D _geodeticTransformMatrix;
  mutable MutableMatrix44D _rotationMatrix;
  mutable MutableVector3D  _transformedInitialPoint1;
  mutable MutableVector3D  _transformedFinalPoint1;
  mutable MutableVector3D  _transformedCameraPos;
  mutable int              _iter;
  
  
  mutable double _lastCorrectingRollAngle;
  mutable MutableVector3D _lastCorrectingRollRotationAxis;
  
  
  MutableMatrix44D createDragMatrix(const Vector3D initialPoint,
                                    const Vector3D finalPoint) const;
  
  void createDragMatrix(const MutableVector3D& initialPoint,
                        const MutableVector3D& finalPoint,
                        MutableMatrix44D& matrix) const;
  
  double testDoubleDragIteration(double factor,
                                 const Vector3D& finalRay0,
                                 const Vector3D& finalRay1) const;
  
  void createInversedGeodeticTransformMatrix(const MutableVector3D& position,
                                             MutableMatrix44D& result) const;
  

public:

  SphericalPlanet(const Sphere& sphere);

  ~SphericalPlanet() {
#ifdef JAVA_CODE
  super.dispose();
#endif

  }

  Vector3D getRadii() const {
    return _radii;
  }

  Vector3D centricSurfaceNormal(const Vector3D& position) const {
    return position.normalized();
  }

  Vector3D geodeticSurfaceNormal(const Vector3D& position) const {
    return position.normalized();
  }

  Vector3D geodeticSurfaceNormal(const MutableVector3D& position) const {
    return position.normalized().asVector3D();
  }
  

  Vector3D geodeticSurfaceNormal(const Angle& latitude,
                                 const Angle& longitude) const;
  
  Vector3D geodeticSurfaceNormal(const Geodetic3D& geodetic) const {
    return geodeticSurfaceNormal(geodetic._latitude, geodetic._longitude);
  }

  Vector3D geodeticSurfaceNormal(const Geodetic2D& geodetic) const {
    return geodeticSurfaceNormal(geodetic._latitude, geodetic._longitude);
  }
  
  void geodeticSurfaceNormal(const Angle& latitude,
                             const Angle& longitude,
                             MutableVector3D& result) const;

  std::vector<double> intersectionsDistances(double originX,
                                             double originY,
                                             double originZ,
                                             double directionX,
                                             double directionY,
                                             double directionZ) const;

  Vector3D toCartesian(const Angle& latitude,
                       const Angle& longitude,
                       const double height) const;

  Vector3D toCartesian(const Geodetic3D& geodetic) const {
    return toCartesian(geodetic._latitude,
                       geodetic._longitude,
                       geodetic._height);
  }

  Vector3D toCartesian(const Geodetic2D& geodetic) const {
    return toCartesian(geodetic._latitude,
                       geodetic._longitude,
                       0.0);
  }

  Vector3D toCartesian(const Geodetic2D& geodetic,
                       const double height) const {
    return toCartesian(geodetic._latitude,
                       geodetic._longitude,
                       height);
  }

  void toCartesian(const Angle& latitude,
                   const Angle& longitude,
                   const double height,
                   MutableVector3D& result) const;

  void toCartesian(const Geodetic3D& geodetic,
                   MutableVector3D& result) const {
    toCartesian(geodetic._latitude,
                geodetic._longitude,
                geodetic._height,
                result);
  }

  void toCartesian(const Geodetic2D& geodetic,
                   MutableVector3D& result) const {
    toCartesian(geodetic._latitude,
                geodetic._longitude,
                0,
                result);
  }
  void toCartesian(const Geodetic2D& geodetic,
                   const double height,
                   MutableVector3D& result) const {
    toCartesian(geodetic._latitude,
                geodetic._longitude,
                height,
                result);
  }

  Geodetic2D toGeodetic2D(const Vector3D& position) const;
  
  void toGeodetic2D(double x, double y, double z,
                    double& latitudeInRadians,
                    double& longitudeInRadians) const;

  Geodetic3D toGeodetic3D(const Vector3D& position) const;
  
  void toGeodetic3D(const MutableVector3D& position,
                    double& latitudeInRadians,
                    double& longitudeInRadians,
                    double& height) const;
  
  Vector3D scaleToGeodeticSurface(const Vector3D& position) const;

  Vector3D scaleToGeocentricSurface(const Vector3D& position) const;

  std::list<Vector3D> computeCurve(const Vector3D& start,
                                   const Vector3D& stop,
                                   double granularity) const;

  Geodetic2D getMidPoint (const Geodetic2D& P0, const Geodetic2D& P1) const;


  double computePreciseLatLonDistance(const Geodetic2D& g1,
                                      const Geodetic2D& g2) const;

  double computeFastLatLonDistance(const Geodetic2D& g1,
                                   const Geodetic2D& g2) const;

  Vector3D closestPointToSphere(const Vector3D& pos, const Vector3D& ray) const;

  Vector3D closestIntersection(const Vector3D& pos, const Vector3D& ray) const {
    return Sphere::closestIntersectionCenteredSphereWithRay(pos,
                                                            ray,
                                                            _sphere._radius);
  }


  MutableMatrix44D createGeodeticTransformMatrix(const Geodetic3D& position) const;
  
  bool isFlat() const { return false; }

  void beginSingleDrag(const Vector3D& origin, const Vector3D& touchedPosition) const;
  
  MutableMatrix44D singleDrag(const Vector3D& finalRay) const;
  
  Effect* createEffectFromLastSingleDrag() const;
  
  void beginDoubleDrag(const Vector3D& origin,
                       const Vector3D& centerRay,
                       const Vector3D& centerPosition,
                       const Vector3D& touchedPosition0,
                       const Vector3D& touchedPosition1) const;
  
  MutableMatrix44D doubleDrag(const Vector3D& finalRay0,
                              const Vector3D& finalRay1) const;
  
  Effect* createDoubleTapEffect(const Vector3D& origin,
                                const Vector3D& centerRay,
                                const Vector3D& touchedPosition) const;

  double distanceToHorizon(const Vector3D& position) const;
  
  MutableMatrix44D drag(const Geodetic3D& origin, const Geodetic3D& destination) const;
  
  Vector3D getNorth() const {
    return Vector3D::upZ();
  }

  void applyCameraConstrainers(const Camera* previousCamera,
                               Camera* nextCamera) const;

  Geodetic3D getDefaultCameraPosition(const Sector& rendereSector) const {
    const Vector3D asw = toCartesian(rendereSector.getSW());
    const Vector3D ane = toCartesian(rendereSector.getNE());
    const double height = asw.sub(ane).length() * 1.9;

    return Geodetic3D(rendereSector._center,
                      height);
  }
  
  void correctPitchAfterDoubleDrag(Camera* camera, const Vector2F& finalPixel0, const Vector2F& finalPixel1) const;

  const std::string getType() const {
    return "Spherical";
  }
};

#endif
