import java.lang.Math;

//-- Data are maintained internally in units of au, calendar days, & radians.
//-- Constructor uses degrees.
//-- Coordinate system: x=vernal equinox, z=E's ang mom, y = z x x

class Planet {
  public String name;
  double mass;
  //-- used both in constructor and internally:
  double period; // sidereal period in days
  double a;		// semimajor axis in AU
  double e;		// eccentricity
  //-- used only in constructor:
  double i;		// inclination
  double lan;	// long of ascending node
  double lp;	// long of perihelion in deg.
  double ml2000;	//mean long at 00:00 UT on 1/1/2000
  //-- not used in constructor:
  public Cartesian u; // unit vector pointing from c.m. to perihelion
  public Cartesian v; // unit vector pointing in direction of angular momentum
  public Cartesian w; // = u x v
  double tPeri; // days between 00:00 UT on 1/1/2000 and
  						// first subsequent perihelion
  double b; // = sqrt((1-e)/(1+e))
  public double firstPerihelionAfterJ2000;
  double polarCoordFactor; // = a(1-e)(1+e)
  double c; // = lat of perihelion
  boolean mag;
  double mag1,mag2,mag3,mag4;

  public Planet(String name,
  						double mass,	// in units of the earth's mass
  						double period, // sidereal period in days
  						double a,	// semimajor axis in AU
  						double e,	// eccentricity
  						double i,	// inclination in deg.
  						double lan,	// long of ascending node in deg.
  						double lp,	// long of perihelion in deg.
  						double ml2000,	// mean long at 00:00 UT on 1/1/2000
  						boolean mag,double mag1,double mag2,double mag3,double mag4
  		) {
    this.period = period;
    this.name = name;
    this.mass = mass;
    this.a = a;
    this.e = e;
    this.i = i*Math.PI/180.d;
    this.lan = make0to2Pi(lan*Math.PI/180.d);
    this.lp = make0to2Pi(lp*Math.PI/180.d);
    this.ml2000 = make0to2Pi(ml2000*Math.PI/180.d);
    this.b = Math.sqrt((1.d-e)/(1.d+e));
    this.mag = mag;
    this.mag1 = mag1;
    this.mag2 = mag2;
    this.mag3 = mag3;
    this.mag4 = mag4;
    
    Cartesian q = Cartesian.latLongToUnitVector(0.d,this.lan);
    			 //-- q=ascending node=intersection of orbital planes of E and this planet
    v = (Cartesian.zHat()).scalarMult(Math.cos(this.i)).plus(
    		((q.crossProduct(Cartesian.zHat())).makeUnitLength()).scalarMult(Math.sin(this.i)));
    c = Math.atan2(v.x*Math.cos(this.lp)+v.y*Math.sin(this.lp),-v.z);
    			//-- c=latitude of perihelion, determined by setting u.v=0
    if (c<Math.PI/2.d) c = c + Math.PI;
    if (c>Math.PI/2.d) c = c - Math.PI;
    u = Cartesian.latLongToUnitVector(c,this.lp);
    w = u.crossProduct(v);
    
    firstPerihelionAfterJ2000 = make0to2Pi((lp-ml2000)*Math.PI/180.)*period/(2.*Math.PI);
        
    polarCoordFactor = a*(1.d-e)*(1.d+e);
  }
  
  private double make0to2Pi(double x) {
    long j = Math.round(x/(2.*Math.PI));
    double y = x - j*2.*Math.PI;
    while (y<0.) y = y + 2.*Math.PI;
    while (y>2.*Math.PI) y = y - 2.*Math.PI;
    return y;
  }
  
  public double getLatOfPerihelion() {
    return c;
  }
  
  public double getLonOfPerihelion() {
    return this.lp;
  }
  
  public double getLat(double daysSinceNewYears2000,double angularToleranceInRadians,boolean err) {
    Cartesian p = position(daysSinceNewYears2000, angularToleranceInRadians, err);
    if (err) return 0.d;
    return Math.asin(p.makeUnitLength().z);
  }
  
  public double getLon(double daysSinceNewYears2000,double angularToleranceInRadians,boolean err) {
    Cartesian p = position(daysSinceNewYears2000, angularToleranceInRadians, err);
    if (err) return 0.d;
    return Math.atan2(p.y,p.x);
  }
  
  public Cartesian position(double daysSinceNewYears2000,double angularToleranceInRadians,boolean err) {
    double timeSincePerihelion = daysSinceNewYears2000-firstPerihelionAfterJ2000;
    long j;
    j = Math.round(timeSincePerihelion/period);
    timeSincePerihelion = timeSincePerihelion - j*period;
    if (timeSincePerihelion>period) timeSincePerihelion = timeSincePerihelion - period;
    if (timeSincePerihelion<0.d) timeSincePerihelion = timeSincePerihelion + period;
    double angleFromPerihelion = timeSincePerihelionToAngle(timeSincePerihelion,angularToleranceInRadians,err);
    if (err)
      return new Cartesian(0.d,0.d,0.d);
    else {
      double r = polarCoordFactor/(1.d+e*Math.cos(angleFromPerihelion));
      Cartesian uComp = u.scalarMult(r*Math.cos(angleFromPerihelion));
      Cartesian wComp = w.scalarMult(-r*Math.sin(angleFromPerihelion));
      return uComp.plus(wComp);
    }
  }
  
  private double angleToTimeSincePerihelion(double anglePastPerihelion) {
    double theta = anglePastPerihelion+Math.PI;
    double xi;
    if (theta>Math.PI*2.d) theta = theta - Math.PI*2.d;
    try {
      xi = 2.d*Math.atan(Math.tan(theta/2.d)/b);
    }
    catch (Exception ee) {
      xi = Math.PI;
    }
    double timeSinceAphelion = (period/(4.d*Math.PI))*(2.*xi+e*Math.sin(xi));
    double t = timeSinceAphelion + period/2.;
    if (t>period) t = t-period;
    return t;
  }
  
  	// tolerance in radians
  private double timeSincePerihelionToAngle(double timeSincePerihelion,double tolerance,boolean err) {
    double t,changeAngle,dThetadT,blah,foo,theta,xi;
    int maxIterations = 1000;
    boolean bailNextTime = false;
    double angle = 2.d*Math.PI*timeSincePerihelion/period; //-- initial guess
    int nIterations = 0;
    double oldTimeError = period*10.;
    double timeError;
    while(true) {
      t = angleToTimeSincePerihelion(angle);
      theta = angle + Math.PI;
      foo = Math.cos(theta/2.d);
      try {
        blah = Math.tan(theta/2.d)/b;
        xi = 2.d*Math.atan(blah);
        dThetadT = 4*Math.PI*b*(1.d+blah*blah)*foo*foo/(period*(2.d+e*Math.cos(xi)));
      }
      catch (Exception ee) {
        dThetadT = 4*Math.PI*Math.sin(theta/2.)*Math.sin(theta/2.)/(b*period*(2.d-e));
      }
      timeError = timeSincePerihelion-t;
      changeAngle = timeError*dThetadT;
      //-- Update angle:
      angle = angle + changeAngle;
      //-- Bail-out logic:
      ++nIterations;
      err = (nIterations>maxIterations) || (nIterations>10 && Math.abs(timeError)>oldTimeError);
      if (bailNextTime || err) return angle;
      oldTimeError = Math.abs(timeError);
      bailNextTime =  (Math.abs(changeAngle)<tolerance);
    }
  }
  
  public double magnitude(double distanceFromSunAU,double distanceFromEarthAU,double fvRadians) {
    if (mag) {
      double fv = fvRadians*180./Math.PI;
      return mag1+5*Math.log(distanceFromSunAU*distanceFromEarthAU)/Math.log(10.)
      			+mag2*fv+mag3*Math.exp(mag4*Math.log(fv));
    }
    else {
      return -999.;
    }
  }
  
}
