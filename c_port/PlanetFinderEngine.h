/*  This file is a part of PlanetFinder. PlanetFinder is a Series 60 application
    for locating the planets in the sky. It is a porting of a Java Applet by 
	Benjamin Crowell to the Series 60 Developer Platform. See 
	http://www.lightandmatter.com/area2planet.shtml for the original version.
	Java Applet: Copyright (C) 2000, Benjamin Crowell
    Series 60 Version: Copyright (C) 2004, Kostas Giannakakis

    PlanetFinder is free software; you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation; either version 2 of the License, or
    (at your option) any later version.

    PlanetFinder is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with PlanetFinder; if not, write to the Free Software
    Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
*/

#ifndef __PLANET_FINDER_MAIN_DEF__
#define __PLANET_FINDER_MAIN_DEF__

#include "Cartesian.h"
#include "BrightStarCatalog.h"
#include "Planet.h"
#include "MoonPerturbations.h"
#include "r3/texture.h"
#include "../render.h"

#if __APPLE__
#include <unistd.h>
#endif

struct TPlanetFinderSettings
{
	bool iNakedEyePlanetsOnly;
	bool iShowPlanetsNames;
	bool iDaylightsavings;
	float iLatitude;
	float iLongitude;

};


float GetPhaseEarthRotation();

class CPlanetFinderEngine
{
public:
	// Astronomy Constants
	static const float earthRotationTilt;	// radians
	static const float earthRotationPeriod;	// days
	static const float earthRotationPhase;
	
	static const float sunMass; // in units of the earth's mass

	
	void Construct();

	// Initialisation
	void Init(TPlanetFinderSettings& aSettings);
	void init(float longitude, float latitude );
	void SetSize(int Width, int Height);
private:
	void initPlanets();

	// Drawing
public:
	void buildSolarSystemList( std::vector< star3map::Sprite > & solarsystem );
private:
	void drawDot(r3::Vec4f c, int diam, const r3::Vec3f &direction );
	int magToDotDiam(float mag);
	r3::Vec4f magToDotColor(float mag);
	// Time
private:

	// User's settings
	float longitude,latitude;

	int secondsSince2000;
	double daysSince2000;

	static const int PLANETS_NUMBER = 9;


	Planet mercury, venus, earth, mars, jupiter, saturn, uranus, neptune, pluto, moon;	
	PPlanet planets[ PLANETS_NUMBER ];
	
	r3::Texture2D *sunTexture;

	BrightStarCatalog brightStarCatalog;

	// Drawing elements;
	int Width, Height;
	int skySide;
	r3::Vec2f skyCenter;
};

#endif //__PLANET_FINDER_MAIN_DEF__
