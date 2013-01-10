import java.lang.Math;

public class Cartesian {
  public double x;
  public double y;
  public double z;
  

  public Cartesian(double x,double y,double z) {
    this.x = x;
    this.y = y;
    this.z = z;
  }
  
  public static Cartesian xHat() {
    return new Cartesian(1.d,0.d,0.d);
  }
  
  public static Cartesian yHat() {
    return new Cartesian(0.d,1.d,0.d);
  }
  
  public static Cartesian zHat() {
    return new Cartesian(0.d,0.d,1.d);
  }
  
  public double dotProduct(Cartesian v) {
    return x*v.x+y*v.y+z*v.z;
  }

  public double squaredMagnitude() {
    return this.dotProduct(this);
  }
  
  public double magnitude() {
    return Math.sqrt(squaredMagnitude());
  }
  
  public Cartesian crossProduct(Cartesian v) {
    return new Cartesian(
    	y*v.z-z*v.y,
    	z*v.x-x*v.z,
    	x*v.y-y*v.x
    );
  }
  
  public Cartesian plus(Cartesian v) {
    return new Cartesian(x+v.x,y+v.y,z+v.z);
  }
  
  public Cartesian negative() {
    return new Cartesian(-x,-y,-z);
  }
  
  public Cartesian scalarMult(double s) {
    return new Cartesian(x*s,y*s,z*s);
  }
  
  public Cartesian makeUnitLength() {
    return scalarMult(1.d/magnitude());
  }
  
  public double angleInPlane(Cartesian u,Cartesian v) {
    return Math.atan2(dotProduct(v),dotProduct(u));
  }
 
  public static Cartesian latLongToUnitVector(double lat,double lon) {
    return new Cartesian(
      Math.cos(lat)*Math.cos(lon),
      Math.cos(lat)*Math.sin(lon),
      Math.sin(lat)
    );
  }
  
  public double angleBetween(Cartesian v) {
    return Math.acos(makeUnitLength().dotProduct(v.makeUnitLength()));
  }
  
}
