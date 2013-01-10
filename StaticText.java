/*

	Copyright (c) 2000 Benjamin Crowell. All rights reserved.
	
	About the GPL License
		This program is free software; you can redistribute it and/or modify
	    it under the terms of the GNU General Public License as published by
	    the Free Software Foundation; either version 2 of the License, or
	    (at your option) any later version.

	    This program is distributed in the hope that it will be useful,
	    but WITHOUT ANY WARRANTY; without even the implied warranty of
	    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
	    GNU General Public License for more details.

	    You should have received a copy of the GNU General Public License
	    along with this program; if not, write to the Free Software
	    Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
*/


import java.awt.*;


class StaticText extends Panel {
  private String text;
  private Color textColor;
  private Font theFont;
  private int theFontHeight,theFontAscent,theFontDescent,theFontMaxAdvance;
  private FontMetrics theFM;
  private Dimension minimumSize = new Dimension();
  private Dimension preferredSize = new Dimension();
	private int xLeftIndent = 3; // kludge


  public StaticText(String s) {
    this.text = s;
    textColor = Color.black;

	  theFont = new Font("sansserif",Font.PLAIN,9);;
	  setMySize();
  }

  public void setText(String s) {
    text = s;
    setMySize();
    update(getGraphics());
  }

  private void setMySize() {
	  setFont(theFont); // method from Component, p. 292
	  theFM = getFontMetrics(theFont);
	  theFontHeight = theFM.getHeight();
	  theFontAscent = theFM.getAscent();
	  theFontDescent = theFM.getDescent();
	  theFontMaxAdvance = theFM.getMaxAdvance();
	  int w = (xLeftIndent)/2+3+theFM.stringWidth(text);
	  int h = theFontHeight*3/2;
	  /*
	  setSize(w,h);
	  minimumSize.setSize(w,h);
	  preferredSize.setSize(w,h);
	  */
	  resize(w,h);
     minimumSize.width = w;
     minimumSize.height = h;
     preferredSize.width = w;
     preferredSize.height = h;
    
  }

  public void paint(Graphics g) {
    Rectangle r = getBounds();
	 int w = r.width;
	 int h = r.height;
	 g.setColor(textColor);
	 g.drawString(text,3,h*2/3);
  }

	public Dimension getMinimumSize() {
	  return minimumSize;
	}
	public Dimension getPreferredSize() {
	  return preferredSize;
	}
	public void setPreferredWidth(int w) {
	  preferredSize.width = w;
	}


}

