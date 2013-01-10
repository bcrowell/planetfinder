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


#ifndef __PLANET_DEF__
#define __PLANET_DEF__

#include <string>
#include <math.h>

#ifndef M_PI
#define M_PI 3.14159265358979323846
#endif

#include "r3/linear.h"
#include "r3/texture.h"

#define MathAbs(x) (((x) > 0) ? (x) : (-(x)))

//-- Data are maintained internally in units of au, calendar days, & radians.
//-- Constructor uses degrees.
//-- Coordinate system: x=vernal equinox, z=E's ang mom, y = z x x

class Planet 
{
public: 
	Planet () {}
	
	~Planet () {}
	
	Planet(const std::string& name,
		   float	mass,		// in units of the earth's mass
		   float	period,		// sidereal period in days
		   float	a,			// semimajor axis in AU
		   float	e,			// eccentricity
		   float	i,			// inclination in deg.
		   float	lan,		// long of ascending node in deg.
		   float	lp,			// long of perihelion in deg.
		   float	ml2000,		// mean long at 00:00 UT on 1/1/2000
		   bool	mag,
		   float	mag1,
		   float	mag2,
		   float	mag3,
		   float	mag4) 
	: texture( 0 )
	{
		Init(name,
			 mass,	
			 period,
			 a,		
			 e,		
			 i,		
			 lan,	
			 lp,		
			 ml2000,
			 mag,
			 mag1,
			 mag2,
			 mag3,
			 mag4);
	}
	
	void Init(const std::string& name,
			  float mass,	// in units of the earth's mass
			  float period,	// sidereal period in days
			  float a,		// semimajor axis in AU
			  float e,		// eccentricity
			  float i,		// inclination in deg.
			  float lan,	// long of ascending node in deg.
			  float lp,		// long of perihelion in deg.
			  float ml2000,	// mean long at 00:00 UT on 1/1/2000
			  bool mag,
			  float mag1,
			  float mag2,
			  float mag3,
			  float mag4) 
	{
		this->period = period;
		this->name = name;
		this->mass = mass;
		this->a = a;
		this->e = e;
		this->i = float( i * M_PI / 180.0 );
		this->lan = make0to2Pi( float( lan * M_PI / 180.0 ) );
		this->lp = make0to2Pi( float( lp * M_PI / 180.0 ) );
		this->ml2000 = make0to2Pi( float( ml2000 * M_PI / 180.0 ) );
		this->b = float( sqrt( ( 1.0-e ) / ( 1.0+e ) ) );
		this->mag = mag;
		this->mag1 = mag1;
		this->mag2 = mag2;
		this->mag3 = mag3;
		this->mag4 = mag4;
		
		r3::Vec3f q; 
		q = latLongToUnitVector(0.0,this->lan);
		
		float cos_i = 0, sin_i = 0;
		cos_i = cos(this->i);
		sin_i = sin(this->i);
		
		r3::Vec3f zH(0, 0 ,1); //z = z.zHat;
		r3::Vec3f qxzh = q.Cross( zH );
		qxzh.Normalize();
		v = zH * cos_i + qxzh * sin_i;
		
		float cos_lp = 0, sin_lp = 0;
		cos_lp = cos(this->lp);
		sin_lp = sin(this->lp);
		
		c = atan2( v.x*cos_lp + v.y*sin_lp, -v.z);
		
		if (c<M_PI/2.0) 
			c = c + float( M_PI );
		if (c>M_PI/2.0) 
			c = c - float( M_PI );
		
		u = latLongToUnitVector(c, this->lp);
		w = u.Cross( v );
		
		firstPerihelionAfterJ2000 = make0to2Pi( float( ( lp - ml2000 ) * M_PI / 180 ) ) * period / float( 2. * M_PI );
		
		polarCoordFactor = float( a * (1.0-e) * (1.0+e) );
		if ( texture == NULL ) {
			std::string texname;
			for ( int i = 0; i < (int)name.size(); i++ ) {
				texname.push_back( tolower( name[ i ] ) );
			}
			texname += ".jpg";
			texture = r3::CreateTexture2DFromFile( texname.c_str(), r3::TextureFormat_RGBA );
			scale = 1.0f;
		}
	}
	
	std::string name;
	float mass;
	
	//-- used both in constructor and internally:
	float period;	// sidereal period in days
	float a;		// semimajor axis in AU
	float e;		// eccentricity
	
private:
	//-- used only in constructor:
	float i;		// inclination
	float lan;		// long of ascending node
	float lp;		// long of perihelion in deg.
	float ml2000;	//mean long at 00:00 UT on 1/1/2000
	
public:
	//-- not used in constructor:
	r3::Vec3f u;		// unit vector pointing from c.m. to perihelion
	r3::Vec3f v;		// unit vector pointing in direction of angular momentum
	r3::Vec3f w;		// = u x v
	float tPeri;		// days between 00:00 UT on 1/1/2000 and
	// first subsequent perihelion
	float b;			// = sqrt((1-e)/(1+e))
	float firstPerihelionAfterJ2000;
	float polarCoordFactor;	// = a(1-e)(1+e)
	float c;					// = lat of perihelion
	bool mag;
	float mag1,mag2,mag3,mag4;
	float scale;
	r3::Texture2D *texture;
	
private: 
	float make0to2Pi(float x) 
	{
		//long j = Math.round(x/(2.*M_PI));
		float j;
		
		j = float( floor(x/(2.0*M_PI) + 0.5) );
		
		float y = x - float( j * 2.0 * M_PI );
		while (y<0.0) y = y + float( 2. * M_PI );
		while (y>2.0*M_PI) y = y - float( 2.0 * M_PI );
		return y;
	}
	
public:
	float getLatOfPerihelion() 
	{
		return c;
	}
	
	float getLonOfPerihelion() 
	{
		return this->lp;
	}
	
	float getLat(float daysSinceNewYears2000,
				 float angularToleranceInRadians,
				 bool err) 
	{
		r3::Vec3f p = position( daysSinceNewYears2000, angularToleranceInRadians );
		p.Normalize();
		return asin(p.z);
	}
	
	float getLon(float daysSinceNewYears2000, float angularToleranceInRadians)
	{
		r3::Vec3f p = position(daysSinceNewYears2000, angularToleranceInRadians );
		return atan2(p.y, p.x);
	}
	
	r3::Vec3f position(float daysSinceNewYears2000, float angularToleranceInRadians ) 
	{
		float timeSincePerihelion = daysSinceNewYears2000-firstPerihelionAfterJ2000;
		int j;
		//j = Math.round(timeSincePerihelion/period);
		float res;
		res = float( floor( timeSincePerihelion/period + 0.5f) );
		j= (int) res;
		
		timeSincePerihelion = timeSincePerihelion - j*period;
		if (timeSincePerihelion>period) 
			timeSincePerihelion = timeSincePerihelion - period;
		if (timeSincePerihelion<0.0) 
			timeSincePerihelion = timeSincePerihelion + period;
		float angleFromPerihelion = timeSincePerihelionToAngle( timeSincePerihelion, angularToleranceInRadians );		
		float cosP = 0, sinP = 0, r;
		
		cosP = cos(angleFromPerihelion);
		sinP = sin(angleFromPerihelion);
		
		r = polarCoordFactor/(1.0+e*cosP);
		r3::Vec3f uComp = u*(r*cosP);
		r3::Vec3f wComp = w*(-r*sinP);
		return uComp+wComp;
	}
	
private:
	float angleToTimeSincePerihelion(float anglePastPerihelion) 
	{
		float theta = anglePastPerihelion+M_PI;
		float xi, res;
		if (theta>M_PI*2.0) 
			theta = theta - M_PI*2.0;
		
		res = tan((theta/2.0));
		{
			xi = atan( res/b );
			xi *= 2;
			// FIXME do I need an error case here?
		}
		
		//try 
		//{
		//	xi = 2.0*Math.atan(Math.tan(theta/2.0)/b);
		//}
		//catch (Exception ee) 
		//{
		//	xi = M_PI;
		//}
		
		float sin_xi = 0;
		
		sin_xi = sin(xi);
		
		float timeSinceAphelion = (period/(4.0*M_PI))*(2.0*xi+e*sin_xi);
		float t = timeSinceAphelion + period/2.0;
		if (t>period) t = t-period;
		return t;
	}
	
  	// tolerance in radians
	float timeSincePerihelionToAngle( float timeSincePerihelion, float tolerance ) 
	{
		float t,changeAngle,dThetadT = 0,blah,foo,theta,xi;
		int maxIterations = 1000;
		bool bailNextTime = false;
		float angle = 2.0*M_PI*timeSincePerihelion/period; //-- initial guess
		int nIterations = 0;
		float oldTimeError = period*10.;
		float timeError;
		bool alwaysTrue = true;
		bool err = false;
		while(alwaysTrue) 
		{
			t = angleToTimeSincePerihelion(angle);
			theta = angle + M_PI;
			foo = cos(theta/2.0);
			//foo = Math.cos(theta/2.0);
			
			bool error = false;
			blah = tan( theta/2.0 );
			blah = blah/b;
			xi = atan( blah );
			xi = 2.0*xi;
			float cos_xi = cos( xi );
			dThetadT = 4*M_PI*b*(1.0+blah*blah)*foo*foo/
			(period*(2.0+e*cos_xi));
			
			if  (error)
			{
				//float sin_th = 0;
				//Math::Sin(sin_th, theta/2.0);
				
				//dThetadT = 4*M_PI*sin_th*sin_th/(b*period*(2.0-e));
			}
			
			//try 
			//{
			//	blah = Math.tan(theta/2.0)/b;
			//	xi = 2.0*Math.atan(blah);
			//	dThetadT = 4*M_PI*b*(1.0+blah*blah)*foo*foo/(period*(2.0+e*Math.cos(xi)));
			//}
			//catch (Exception ee) 
			//{
			//	dThetadT = 4*M_PI*Math.sin(theta/2.)*Math.sin(theta/2.)/(b*period*(2.0-e));
			//}
			timeError = timeSincePerihelion-t;
			changeAngle = timeError*dThetadT;
			
			//-- Update angle:
			angle = angle + changeAngle;
			//-- Bail-out logic:
			++nIterations;
			err = (nIterations>maxIterations) || (nIterations>10 && MathAbs(timeError)>oldTimeError);
			if (bailNextTime || err) 
				return angle;
			oldTimeError = MathAbs(timeError);
			bailNextTime =  (MathAbs(changeAngle)<tolerance);
		}
		return 0;
	}
	
public:
	
	float magnitude(float distanceFromSunAU,
					float distanceFromEarthAU,
					float fvRadians) 
	{
		if (mag) 
		{
			float fv = fvRadians*180.0/M_PI;
			
			float log10, logfv, res, temp;;
			
			log10 = log(10.0f);
			temp = log(distanceFromSunAU*distanceFromEarthAU);
			res = mag1 + 5*temp/log10 + mag2*fv;
			logfv = log(fv);
			temp = exp(mag4*logfv);
			res+= mag3*temp;
			
			return res;
			//return mag1+5*Math.log(distanceFromSunAU*distanceFromEarthAU)/Math.log(10.0)
			//	   +mag2*fv+mag3*Math.exp(mag4*Math.log(fv));
		}
		else 
		{
			return -999.0;
		}
	}
};

typedef Planet * PPlanet;

#endif //__PLANET_DEF__