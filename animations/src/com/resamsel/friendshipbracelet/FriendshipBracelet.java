package com.resamsel.friendshipbracelet;

import static java.util.Arrays.asList;

import java.util.ArrayList;
import java.util.List;

import processing.core.PShape;

import com.resamsel.InteractiveExportPApplet;

/**
 * (c) 2014 René Samselnig
 * <p>
 *
 * @author $ Author:resamsel $
 * @version $ Date:15.09.2014 $
 */
public class FriendshipBracelet extends InteractiveExportPApplet
{
   private static final long serialVersionUID = 3042352305784011879L;

   int frame;

   int framesPerKnot = 25;

   float knotDiameter;

   float knotSize;

   float marginX;

   float marginY = 0;

   int knot;

   transient List<List<PShape>> pattern = new ArrayList<List<PShape>>();

   transient List<PShape> line;

   transient PShape c01, c02, c03, c04, c05, c06, c07, c08, c09, c10, c11, c12;

   transient float x, y;

   int knots;

   /**
    * 
    * @see processing.core.PApplet#setup()
    */
   @Override
   public void setup()
   {
      size(720, 450, P2D);
      background(0);

      proportion = 2;

      c01 = Knot.build(this, color(166, 206, 227));
      c02 = Knot.build(this, color(31, 120, 180));
      c03 = Knot.build(this, color(178, 223, 138));
      c04 = Knot.build(this, color(51, 160, 44));
      c05 = Knot.build(this, color(251, 154, 153));
      c06 = Knot.build(this, color(227, 26, 28));
      c07 = Knot.build(this, color(253, 191, 111));
      c08 = Knot.build(this, color(255, 127, 0));
      c09 = Knot.build(this, color(202, 178, 214));
      c10 = Knot.build(this, color(106, 61, 154));
      c11 = Knot.build(this, color(255, 255, 153));
      c12 = Knot.build(this, color(177, 89, 40));

      // @formatter:off
		// standard
		add(c02, c04, c06, c08, c10, c12,
		       c02, c04, c06, c08, c10);
		add(c01, c02, c04, c06, c08, c10,
		       c01, c02, c04, c06, c08);
		add(c03, c01, c02, c04, c06, c08,
		       c03, c01, c02, c04, c06);
		add(c05, c03, c01, c02, c04, c06,
		       c05, c03, c01, c02, c04);
		add(c07, c05, c03, c01, c02, c04,
		       c07, c05, c03, c01, c02);
		add(c09, c07, c05, c03, c01, c02,
		       c09, c07, c05, c03, c01);
		add(c11, c09, c07, c05, c03, c01,
		       c11, c09, c07, c05, c03);
		add(c12, c11, c09, c07, c05, c03,
		       c12, c11, c09, c07, c05);
		add(c10, c12, c11, c09, c07, c05,
		       c10, c12, c11, c09, c07);
		add(c08, c10, c12, c11, c09, c07,
		       c08, c10, c12, c11, c09);
		add(c06, c08, c10, c12, c11, c09,
		       c06, c08, c10, c12, c11);
		add(c04, c06, c08, c10, c12, c11,
		       c04, c06, c08, c10, c12);

		// small zig zag
		add(c02, c04, c06, c08, c10, c12,
		       c02, c04, c06, c08, c10);

		// zig zag
		add(c02, c04, c06, c08, c10, c12,
		       c02, c04, c06, c08, c10);
		add(c01, c02, c04, c06, c08, c10,
		       c01, c02, c04, c06, c08);
		add(c03, c01, c02, c04, c06, c08,
		       c03, c01, c02, c04, c06);
		add(c05, c03, c01, c02, c04, c06,
		       c05, c03, c01, c02, c04);
		add(c07, c05, c03, c01, c02, c04,
		       c07, c05, c03, c01, c02);
		add(c09, c07, c05, c03, c01, c02,
		       c07, c05, c03, c01, c02);
		add(c07, c05, c03, c01, c02, c04,
		       c05, c03, c01, c02, c04);
		add(c05, c03, c01, c02, c04, c06,
		       c03, c01, c02, c04, c06);
		add(c03, c01, c02, c04, c06, c08,
		       c01, c02, c04, c06, c08);
		add(c01, c02, c04, c06, c08, c10,
		       c02, c04, c06, c08, c10);

		// squares
      add(c02, c04, c06, c06, c04, c02,
             c04, c06, c01, c06, c04);
      add(c02, c06, c01, c01, c06, c02,
             c06, c01, c03, c01, c06);
      add(c06, c01, c03, c03, c01, c06,
             c06, c01, c03, c01, c06);
      add(c02, c06, c01, c01, c06, c02,
             c04, c06, c01, c06, c04);
      add(c02, c04, c06, c06, c04, c02,
             c05, c04, c06, c04, c05);
      add(c02, c05, c04, c04, c05, c02,
             c07, c05, c04, c05, c07);
      add(c02, c05, c04, c04, c05, c02,
             c05, c04, c06, c04, c05);
      add(c02, c04, c06, c06, c04, c02,
             c04, c06, c01, c06, c04);
      add(c02, c06, c01, c01, c06, c02,
             c06, c01, c03, c01, c06);
      add(c06, c01, c03, c03, c01, c06,
             c06, c01, c03, c01, c06);
      add(c02, c06, c01, c01, c06, c02,
             c04, c06, c01, c06, c04);
      add(c02, c04, c06, c06, c04, c02,
             c05, c04, c06, c04, c05);
      add(c02, c05, c04, c04, c05, c02,
             c05, c04, c06, c04, c05);

		// small zig zag (again)
		add(c02, c04, c06, c08, c10, c12,
		       c02, c04, c06, c08, c10);
		// @formatter:on

      knots = (pattern.get(0).size() + 1) / 2;
   }

   /**
    * 
    * @see com.resamsel.InteractiveExportPApplet#draw()
    */
   @Override
   public void draw()
   {
      background(0);

      knotDiameter = width / knots;
      knotSize = (float)Math.ceil(Math.sqrt(Math.pow(knotDiameter, 2) / 2));
      marginX = width / 2 - knots * knotDiameter / 2 + knotDiameter / 2;
      marginY = -(frame % framesPerKnot) * knotDiameter / framesPerKnot;
      knot = frame / framesPerKnot;

      for(int i = 0; i < height / knotDiameter + 2; i++)
      {
         line = pattern.get((knot + i) % pattern.size());
         for(int j = 0; j < line.size(); j++)
         {
            if(j < (line.size() + 1) / 2)
            {
               x = j * knotDiameter;
               y = i * knotDiameter;
            }
            else
            {
               x = (j - (line.size() + 1) / 2) * knotDiameter + knotDiameter / 2;
               y = i * knotDiameter + knotDiameter / 2;
            }
            pushMatrix();
            translate(marginX + x, marginY + y);
            scale(knotSize);
            shape(line.get(j));
            popMatrix();
         }
      }
      super.draw();
   }

   protected void add(PShape... shapes)
   {
      pattern.add(asList(shapes));
   }

   /**
    * 
    * @see com.resamsel.InteractiveExportPApplet#advance()
    */
   @Override
   protected void advance()
   {
      frame = (frame + 1) % getMaxFrameCount();
   }

   /**
    * 
    */
   @Override
   protected void advanceFromScroll(int amount)
   {
      frame = (getMaxFrameCount() + frame + amount) % getMaxFrameCount();
   }

   /**
    * @return
    * @see com.resamsel.ExportPApplet#getMaxFrameCount()
    */
   @Override
   protected int getMaxFrameCount()
   {
      return framesPerKnot * pattern.size();
   }

   /**
    * 
    * @see com.resamsel.ExportPApplet#resetAdvance()
    */
   @Override
   protected void resetAdvance()
   {
      frame = 0;
   }
}
