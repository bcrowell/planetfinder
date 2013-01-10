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


class City {
  public String name;
  public String country;
  public double popMillions;
  public double latDegrees;
  public double lonDegrees;
  public double timeZone;
  public boolean haveLanguages;
  public Language languages[];

  public City (String name,String country,double popMillions,double latDegrees,double lonDegrees
  					,double timeZone) {
    this.name = name;
    this.country = country;
    this.popMillions = popMillions;
    this.latDegrees = latDegrees;
    this.lonDegrees = lonDegrees;
    this.timeZone = timeZone;
    haveLanguages = false;
  }
  
  public City (String name,String country,double popMillions,double latDegrees,double lonDegrees
  					,double timeZone,String language1,String language2) {
    this.name = name;
    this.country = country;
    this.popMillions = popMillions;
    this.latDegrees = latDegrees;
    this.lonDegrees = lonDegrees;
    this.timeZone = timeZone;
    haveLanguages = true;
    this.languages = new Language[] {new Language(language1),new Language(language2)};;
  }
  
  public City (String name,String country,double popMillions,double latDegrees,double lonDegrees
  					,double timeZone,String language) {
    this.name = name;
    this.country = country;
    this.popMillions = popMillions;
    this.latDegrees = latDegrees;
    this.lonDegrees = lonDegrees;
    this.timeZone = timeZone;
    haveLanguages = true;
    this.languages = new Language[] {new Language(language)};
  }
  
  public boolean isInLanguageList(Language language) {
    if (!haveLanguages) return false;
    for (int i=0; i<languages.length; i++) {
      if (language.equals(languages[i])) return true;
    }
    return false;
  }
}
