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




class Language {
  private String code; // Keep this private, because it's not safe to compare against.
  							// Avoid problems with case, and with locales that return things like
  							// "eng"
  public Language(String code) {
    this.code = standardizeCode(code);
  }

  public boolean equals(Language l) {
    return code.equals(l.code);
  }
  
  public String standardizeCode(String code) {
    // The default action is just to return the first 2 characters, converted to lower
    // case. We only need to special-case the ones where the native or anglicized version
    // of the name does not begin with the two code letters.
    String c = code.toLowerCase();
    
    if (c.length()==2) return c; // for efficiency
    
    if (c.equals("german")) return "de";
    if (c.equals("greek")) return "el";
    if (c.equals("spanish")) return "es";
    if (c.equals("dutch")) return "nl";
    if (c.equals("chinese")) return "zh";
    if (c.startsWith("por")) return "pt";
    if (c.startsWith("swe")) return "sv";
    if (c.startsWith("tur")) return "tr";

    return c.substring(0,1);
  }
}
