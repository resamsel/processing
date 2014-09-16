package com.resamsel;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import processing.event.MouseEvent;

/**
 * (c) 2014 René Samselnig
 * <p>
 *
 * @author $ Author:resamsel $
 * @version $ Date:10.09.2014 $
 */
public abstract class InteractiveExportPApplet extends ExportPApplet
{
   private static final long serialVersionUID = 4742908307123193790L;

   boolean interactive = false;

   boolean showFrames = false;

   boolean debug = false;

   List<Field> fields = getFields(getClass());

   static List<Field> getFields(Class<?> clazz)
   {
      List<Field> fields = new ArrayList<Field>();
      for(Field field : clazz.getDeclaredFields())
         if(!Modifier.isTransient(field.getModifiers()) && !Modifier.isStatic(field.getModifiers()))
            fields.add(field);
      return fields;
   }

   /**
    * 
    * @see com.resamsel.ExportPApplet#draw()
    */
   @Override
   public void draw()
   {
      if(!interactive)
         advance();

      if(debug)
      {
         pushMatrix();
         translate(width - 20, height - 20 - fields.size() * 20);
         textAlign(RIGHT);
         String text;
         for(Field field : fields)
         {
            try
            {
               field.setAccessible(true);
               text = String.format("%s = %s", field.getName(), field.get(this).toString());
            }
            catch(IllegalArgumentException | IllegalAccessException e)
            {
               continue;
            }

            translate(0, 20);
            text(text, 0, 0);
         }
         textAlign(LEFT);
         popMatrix();
      }

      if(showFrames)
         text(String.format("fps = %.1f", frameRate), 20, height - 20);

      super.draw();
   }

   protected abstract void advance();

   /**
    * To be overridden.
    */
   protected void advanceFromMouse()
   {
   }

   /**
    * To be overridden.
    * 
    * @param event
    */
   protected void advanceFromScroll(int amount)
   {
   }

   /**
    * 
    * @see com.resamsel.ExportPApplet#keyPressed()
    */
   @Override
   public void keyPressed()
   {
      super.keyPressed();

      // Prevent doing dumb things when exporting
      if(isExport())
         return;

      if(key == 'f')
         showFrames = !showFrames;
      if(key == 'd')
         debug = !debug;
   }

   public void mousePressed()
   {
      if(!isExport())
      {
         interactive = !interactive;
         advanceFromMouse();
      }
   }

   public void mouseMoved()
   {
      if(!isExport() && interactive)
         advanceFromMouse();
   }

   /**
    * @param event
    * @see processing.core.PApplet#mouseWheel(processing.event.MouseEvent)
    */
   @Override
   public void mouseWheel(MouseEvent event)
   {
      if(!isExport() && interactive)
         advanceFromScroll(event.getCount());
   }
}
