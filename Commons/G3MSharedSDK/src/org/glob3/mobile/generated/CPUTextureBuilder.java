package org.glob3.mobile.generated; 
//
//  CPUTextureBuilder.cpp
//  G3MiOSSDK
//
//  Created by José Miguel S N on 23/07/12.
//  Copyright (c) 2012 __MyCompanyName__. All rights reserved.
//

//
//  CPUTextureBuilder.hpp
//  G3MiOSSDK
//
//  Created by José Miguel S N on 23/07/12.
//  Copyright (c) 2012 __MyCompanyName__. All rights reserved.
//



public class CPUTextureBuilder extends TextureBuilder
{

//C++ TO JAVA CONVERTER WARNING: 'const' methods are not available in Java:
//ORIGINAL LINE: const void createTextureFromImage(GL* gl, const IFactory* factory, IImage* image, int width, int height, IImageListener* listener, boolean autodelete) const
  public final void createTextureFromImage(GL gl, IFactory factory, IImage image, int width, int height, IImageListener listener, boolean autodelete)
  {
	if (image == null)
	{
	  ILogger.instance().logWarning("Creating blank Image");
	  factory.createImageFromSize(width, height, listener, autodelete);
	}
	else if (image.getHeight() == height && image.getWidth() == width)
	{
	  listener.imageCreated(image.shallowCopy());
	}
	else
	{
	  image.scale(width, height, listener, autodelete);
	}
  }

//C++ TO JAVA CONVERTER WARNING: 'const' methods are not available in Java:
//ORIGINAL LINE: const void createTextureFromImages(GL* gl, const IFactory* factory, const java.util.ArrayList<IImage*>& images, const java.util.ArrayList<RectangleI*>& rectangles, int width, int height, IImageListener* listener, boolean autodelete) const
  public final void createTextureFromImages(GL gl, IFactory factory, java.util.ArrayList<IImage> images, java.util.ArrayList<RectangleI> rectangles, int width, int height, IImageListener listener, boolean autodelete)
  {
  
	final int imagesSize = images.size();
  
	if (imagesSize == 0 || imagesSize != rectangles.size())
	{
	  ILogger.instance().logWarning("Creating blank Image");
	  //return factory->createImageFromSize(width, height);
	  factory.createImageFromSize(width, height, listener, autodelete);
	}
	else if (imagesSize == 1)
	{
	  RectangleI rectangle = rectangles.get(0);
	  images.get(0).subImage(rectangle, new CPUTextureBuilderSubImageImageLister(width, height, listener, autodelete), true);
	}
	else
	{
	  final java.util.ArrayList<IImage> tailImages = new java.util.ArrayList<IImage>();
	  java.util.ArrayList<RectangleI> tailRectangles = new java.util.ArrayList<RectangleI>();
	  for (int i = 1; i < imagesSize; i++)
	  {
		tailImages.add(images.get(i));
		tailRectangles.add(rectangles.get(i));
	  }
  
	  images.get(0).combineWith(tailImages, tailRectangles, width, height, listener, autodelete);
  
  //    const IImage* base;
  //    int i;
  //    const RectangleI baseRec(0, 0, width, height);
  //    // if (rectangles.size() > 0 && rectangles[0]->equalTo(baseRec)){
  //    if (rectangles[0]->equalTo(baseRec)){
  //      base = images[0]->shallowCopy();
  //      i = 1;
  //    }
  //    else {
  //      base = factory->createImageFromSize(width, height,
  //                                          new CPUTextureBuilderIImageListener(),
  //                                          true);
  //      i = 0;
  //    }
  //
  //    for (; i < images.size(); i++) {
  //      const IImage* currentImage = images[i];
  //      const RectangleI* currentRect = rectangles[i];
  //
  //      IImage* im2 = base->combineWith(*currentImage, *currentRect, width, height);
  //      delete base;
  //      base = im2;
  //    }
  //    return base;
	}
  }

}