//
//  MeshRenderer.cpp
//  G3MiOSSDK
//
//  Created by Diego Gomez Deck on 12/22/12.
//
//

#include "MeshRenderer.hpp"

#include "Mesh.hpp"
#include "Camera.hpp"

void MeshRenderer::clearMeshes() {
  const int meshesCount = _meshes.size();
  for (int i = 0; i < meshesCount; i++) {
    Mesh* mesh = _meshes[i];
    delete mesh;
  }
  _meshes.clear();
}

MeshRenderer::~MeshRenderer() {
  const int meshesCount = _meshes.size();
  for (int i = 0; i < meshesCount; i++) {
    Mesh* mesh = _meshes[i];
    delete mesh;
  }
}

void MeshRenderer::updateGLState(const G3MRenderContext* rc){

  const Camera* cam = rc->getCurrentCamera();
  if (_projection == NULL){
    _projection = new ProjectionGLFeature(cam->getProjectionMatrix().asMatrix44D());
    _glState.addGLFeature(_projection);
  } else{
    _projection->setMatrix(cam->getProjectionMatrix().asMatrix44D());
  }

  if (_model == NULL){
    _model = new ModelGLFeature(cam->getModelMatrix().asMatrix44D());
    _glState.addGLFeature(_model);
  } else{
    _model->setMatrix(cam->getModelMatrix().asMatrix44D());
  }
}

void MeshRenderer::render(const G3MRenderContext* rc) {
  const Frustum* frustum = rc->getCurrentCamera()->getFrustumInModelCoordinates();

  //_glState.getGPUProgramState()->setUniformMatrixValue(MODELVIEW, rc->getCurrentCamera()->getModelViewMatrix(), false);
//  _glState.setModelView(rc->getCurrentCamera()->getModelViewMatrix().asMatrix44D(), false);


  //rc->getCurrentCamera()->addProjectionAndModelGLFeatures(_glState);
  updateGLState(rc);


  const int meshesCount = _meshes.size();
  for (int i = 0; i < meshesCount; i++) {
    Mesh* mesh = _meshes[i];
    const Extent* extent = mesh->getExtent();

    if ( extent->touches(frustum) ) {
      mesh->render(rc, &_glState);
    }
  }
}

void MeshRenderer::createGLState() const{
  
//  _glState.getGLGlobalState()->enableDepthTest();

//  GPUProgramState& progState = *_glState.getGPUProgramState();
//  progState.setUniformValue(EnableTexture, false);
//  progState.setUniformValue(POINT_SIZE, (float)1.0);
//  progState.setUniformValue(SCALE_TEXTURE_COORDS, Vector2D(1.0,1.0));
//  progState.setUniformValue(TRANSLATION_TEXTURE_COORDS, Vector2D(0.0,0.0));
}
