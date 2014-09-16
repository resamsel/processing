package com.resamsel;

import processing.core.PApplet;

/**
 * (c) 2014 René Samselnig
 * <p>
 *
 * @author $ Author:resamsel $
 * @version $ Date:10.09.2014 $
 */
public abstract class ExportPApplet extends PApplet
{
   private static final long serialVersionUID = 1666118877568163656L;

   private boolean export = false;

   protected int proportion = 3;

   /**
    * 
    * @see processing.core.PApplet#draw()
    */
   @Override
   public void draw()
   {
      export();
   }

   public void keyPressed()
   {
      if(key == 'e')
         if(!export)
            startExport();
         else
            stopExport();
   }

   private void export()
   {
      if(!export)
         return;

      if(frameCount >= getMaxFrameCount())
         stopExport();
      if(frameCount % proportion == 0 && frameCount < getMaxFrameCount())
         saveFrame(String.format("%s-####.png", getClass().getSimpleName().toLowerCase()));
   }

   private void startExport()
   {
      export = true;
      frameCount = 0;
      resetAdvance();
      smooth(8);
   }

   private void stopExport()
   {
      export = false;
      smooth(2);
   }

   protected abstract int getMaxFrameCount();

   protected abstract void resetAdvance();

   /**
    * @return the export
    */
   protected boolean isExport()
   {
      return export;
   }
}
