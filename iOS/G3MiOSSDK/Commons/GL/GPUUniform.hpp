//
//  GPUUniform.h
//  G3MiOSSDK
//
//  Created by Jose Miguel SN on 05/04/13.
//
//

#ifndef G3MiOSSDK_GPUUniform_h
#define G3MiOSSDK_GPUUniform_h

#include "GL.hpp"
#include "GLConstants.hpp"
#include "IGLUniformID.hpp"
#include "IStringBuilder.hpp"
#include "GPUVariable.hpp"

class GPUUniform;

class GPUUniformValue{
  const int _type;
  
  mutable GPUUniform* _uniform;
  
public:
  GPUUniformValue(int type):_type(type), _uniform(NULL){}
  int getType() const { return _type;}
  virtual ~GPUUniformValue(){}
  virtual void setUniform(GL* gl, const IGLUniformID* id) const = 0;
  virtual bool isEqualsTo(const GPUUniformValue* v) const = 0;
  virtual GPUUniformValue* deepCopy() const = 0;
  
  GPUUniform* getLinkedUniform() const { return _uniform;}
  
  virtual std::string description() const = 0;
  
  void linkToGPUUniform(GPUUniform* u){
    _uniform = u;
  }
  
  void unLinkToGPUUniform(){
    _uniform = NULL;
  }
  
  void setValueToLinkedUniform() const;
  
  virtual void setLastGPUUniformValue(GPUUniformValue* old){} //Used with matrix transform value
  
  
  virtual void copyFrom(GPUUniformValue* v) = 0;
};


class GPUUniform: public GPUVariable{
  
protected:
  const IGLUniformID* _id;
  
  bool _dirty;
  GPUUniformValue* _value;
  const int _type;
public:
  
  virtual ~GPUUniform(){
    delete _id;
    delete _value;
  }
  
  GPUUniform(const std::string&name, IGLUniformID* id, int type):
  GPUVariable(name, UNIFORM),
  _id(id),
  _dirty(false),
  _value(NULL),
  _type(type){}
  
  const std::string getName() const{ return _name;}
  const IGLUniformID* getID() const{ return _id;}
  int getType() const{ return _type;}
  bool wasSet() const { return _value != NULL;}
  GPUUniformValue* getSetValue() const { return _value;}
  
  void unset(){
    if (_value != NULL){
      delete _value;
      _value = NULL;
    }
    _dirty = false;
  }
  
  void set(GPUUniformValue* v){
    if (_type != v->getType()){ //type checking
      //      delete v;
      ILogger::instance()->logError("Attempting to set uniform " + _name + "with invalid value type.");
      return;
    }
    if (_value == NULL || !_value->isEqualsTo(v)){
      _dirty = true;
      
      if (_value != NULL){
        v->setLastGPUUniformValue(_value); //Multiply matrix when needed
        _value->copyFrom(v);
      } else{
        _value = v->deepCopy();
      }
    }
  }
  
  virtual void applyChanges(GL* gl){
    if (_dirty){
      _value->setUniform(gl, _id);
      _dirty = false;
    }
  }
};

////////////////////////////////////////////////////////////////////////
class GPUUniformValueBool:public GPUUniformValue{
public:
  bool _value;
  
  GPUUniformValueBool(bool b):GPUUniformValue(GLType::glBool()),_value(b){}
  
  void setUniform(GL* gl, const IGLUniformID* id) const{
    if (_value) gl->uniform1i(id, 1);
    else gl->uniform1i(id, 0);
  }
  bool isEqualsTo(const GPUUniformValue* v) const{
    return _value == ((GPUUniformValueBool*)v)->_value;
  }
  GPUUniformValue* deepCopy() const{
    return new GPUUniformValueBool(_value);
  }
  
  void copyFrom(GPUUniformValue* v){
    _value = ((GPUUniformValueBool*)v)->_value;
  }
  
  std::string description() const{
    IStringBuilder *isb = IStringBuilder::newStringBuilder();
    isb->addString("Uniform Value Boolean: ");
    isb->addBool(_value);
    std::string s = isb->getString();
    delete isb;
    return s;
  }
};
class GPUUniformBool: public GPUUniform{
public:
  GPUUniformBool(const std::string&name, IGLUniformID* id):GPUUniform(name,id, GLType::glBool()){}
};
////////////////////////////////////////////////////////////////////////
class GPUUniformValueVec2Float:public GPUUniformValue{
public:
  double _x, _y;
  
  GPUUniformValueVec2Float(double x, double y):GPUUniformValue(GLType::glVec2Float()), _x(x),_y(y){}
  
  void setUniform(GL* gl, const IGLUniformID* id) const{
    gl->uniform2f(id, (float)_x, (float)_y);
  }
  bool isEqualsTo(const GPUUniformValue* v) const{
    GPUUniformValueVec2Float *v2 = (GPUUniformValueVec2Float *)v;
    return (_x == v2->_x) && (_y == v2->_y);
  }
  GPUUniformValue* deepCopy() const{
    return new GPUUniformValueVec2Float(_x,_y);
  }
  
  void copyFrom(GPUUniformValue* v){
    _x = ((GPUUniformValueVec2Float*)v)->_x;
    _y = ((GPUUniformValueVec2Float*)v)->_y;
  }
  
  std::string description() const{
    IStringBuilder *isb = IStringBuilder::newStringBuilder();
    isb->addString("Uniform Value Vec2Float: x:");
    isb->addDouble(_x);
    isb->addString("y:");
    isb->addDouble(_y);
    std::string s = isb->getString();
    delete isb;
    return s;
  }
};
class GPUUniformVec2Float: public GPUUniform{
public:
  GPUUniformVec2Float(const std::string&name, IGLUniformID* id):GPUUniform(name,id, GLType::glVec2Float()){}
};
////////////////////////////////////////////////////////////////////////
class GPUUniformValueVec4Float:public GPUUniformValue{
public:
  double _x, _y, _z, _w;
  
  GPUUniformValueVec4Float(double x, double y, double z, double w):
  GPUUniformValue(GLType::glVec4Float()),_x(x),_y(y), _z(z), _w(w){}
  
  void setUniform(GL* gl, const IGLUniformID* id) const{
    gl->uniform4f(id, (float)_x, (float)_y, (float)_z, (float)_w);
  }
  bool isEqualsTo(const GPUUniformValue* v) const{
    GPUUniformValueVec4Float *v2 = (GPUUniformValueVec4Float *)v;
    return (_x == v2->_x) && (_y == v2->_y) && (_z == v2->_z) && (_w == v2->_w);
  }
  GPUUniformValue* deepCopy() const{
    return new GPUUniformValueVec4Float(_x,_y,_z,_w);
  }
  
  void copyFrom(GPUUniformValue* v){
    _x = ((GPUUniformValueVec4Float*)v)->_x;
    _y = ((GPUUniformValueVec4Float*)v)->_y;
    _z = ((GPUUniformValueVec4Float*)v)->_z;
    _w = ((GPUUniformValueVec4Float*)v)->_w;
  }
  
  std::string description() const{
    IStringBuilder *isb = IStringBuilder::newStringBuilder();
    isb->addString("Uniform Value Vec4Float: x:");
    isb->addDouble(_x);
    isb->addString("y:");
    isb->addDouble(_y);
    isb->addString("z:");
    isb->addDouble(_z);
    isb->addString("w:");
    isb->addDouble(_w);
    std::string s = isb->getString();
    delete isb;
    return s;
  }
};
class GPUUniformVec4Float: public GPUUniform{
public:
  GPUUniformVec4Float(const std::string&name, IGLUniformID* id):GPUUniform(name,id, GLType::glVec4Float()){}
};
////////////////////////////////////////////////////////////////////////
class GPUUniformValueMatrix4FloatTransform:public GPUUniformValue{
  
  GPUUniformValueMatrix4FloatTransform(const GPUUniformValueMatrix4FloatTransform* that):
  GPUUniformValue(GLType::glMatrix4Float()),
  _m(MutableMatrix44D(that->_m)),
  _isTransform(that->_isTransform),
  _transformedMatrix(MutableMatrix44D(that->_transformedMatrix)){}
  
public:
#ifdef C_CODE
  MutableMatrix44D _m;
#endif
#ifdef JAVA_CODE
  public MutableMatrix44D _m;
#endif
  
  MutableMatrix44D _transformedMatrix;
  
  bool _isTransform;
  
  GPUUniformValueMatrix4FloatTransform(const MutableMatrix44D& m, bool isTransform):
  GPUUniformValue(GLType::glMatrix4Float()),_m(m), _isTransform(isTransform), _transformedMatrix(m){}
  
  void setLastGPUUniformValue(GPUUniformValue* old){
    if (_isTransform){
      if (old == NULL){
        ILogger::instance()->logError("Trying to apply transformation to matrix without previous value");
      } else{
        const MutableMatrix44D* lastM = ((GPUUniformValueMatrix4FloatTransform*)old)->getValue();
        _transformedMatrix.copyValue(lastM->multiply(_m));
      }
    }
  }
  
  void setUniform(GL* gl, const IGLUniformID* id) const{
    gl->uniformMatrix4fv(id, false, &_transformedMatrix);
  }
  
  bool isEqualsTo(const GPUUniformValue* v) const{
    GPUUniformValueMatrix4FloatTransform *v2 = (GPUUniformValueMatrix4FloatTransform *)v;
    return _transformedMatrix.isEqualsTo(v2->_transformedMatrix);
  }
  GPUUniformValue* deepCopy() const{
    return new GPUUniformValueMatrix4FloatTransform(this);
  }
  
  void copyFrom(GPUUniformValue* v){
    _m.copyValue( ((GPUUniformValueMatrix4FloatTransform*)v)->_m );
    _transformedMatrix.copyValue( ((GPUUniformValueMatrix4FloatTransform*)v)->_transformedMatrix );
  }
  
  std::string description() const{
    IStringBuilder *isb = IStringBuilder::newStringBuilder();
    isb->addString("Uniform Value Matrix44D.");
    std::string s = isb->getString();
    delete isb;
    return s;
  }
  
  const MutableMatrix44D* getValue() const{ return &_transformedMatrix;}
};
class GPUUniformMatrix4Float: public GPUUniform{
public:
  GPUUniformMatrix4Float(const std::string&name, IGLUniformID* id):GPUUniform(name,id, GLType::glMatrix4Float()){}
};
////////////////////////////////////////////////////////////////////////
class GPUUniformValueFloat:public GPUUniformValue{
public:
  double _value;
  
  GPUUniformValueFloat(double d):GPUUniformValue(GLType::glFloat()),_value(d){}
  
  void setUniform(GL* gl, const IGLUniformID* id) const{
    gl->uniform1f(id, (float)_value);
  }
  bool isEqualsTo(const GPUUniformValue* v) const{
    GPUUniformValueFloat *v2 = (GPUUniformValueFloat *)v;
    return _value == v2->_value;
  }
  GPUUniformValue* deepCopy() const{
    return new GPUUniformValueFloat(_value);
  }
  
  void copyFrom(GPUUniformValue* v){
    _value = ((GPUUniformValueFloat*)v)->_value;
  }
  
  std::string description() const{
    IStringBuilder *isb = IStringBuilder::newStringBuilder();
    isb->addString("Uniform Value Float: ");
    isb->addDouble(_value);
    std::string s = isb->getString();
    delete isb;
    return s;
  }
};
class GPUUniformFloat: public GPUUniform{
public:
  GPUUniformFloat(const std::string&name, IGLUniformID* id):GPUUniform(name,id, GLType::glFloat()){}
};
////////////////////////////////////////////////////////////////////////
#endif
