

/**
 * Celestial Body class for NBody
 * @author ola
 *
 */
public class CelestialBody {

	private double myXPos;
	private double myYPos;
	private double myXVel;
	private double myYVel;
	private double myMass;
	private String myFileName;

	/**
	 * Create a Body from parameters	
	 * @param xp initial x position
	 * @param yp initial y position
	 * @param xv initial x velocity
	 * @param yv initial y velocity
	 * @param mass of object
	 * @param filename of image for object animation
	 */
	public CelestialBody(double xp, double yp, double xv,
			             double yv, double mass, String filename){
		// TODO: complete constructor
		myXPos = xp;
		myYPos = yp;
		myXVel = xv;
		myYVel = yv;
		myMass = mass;
		myFileName = filename;

	}

	/**
	 * Copy constructor: copy instance variables from one
	 * body to this body
	 * @param b used to initialize this body
	 */
	public CelestialBody (CelestialBody b){
		// TODO: complete constructor
		myXPos = b.getX();
		myYPos = b.getY();
		myXVel = b.getXVel();
		myYVel = b.getYVel();
		myMass = b.getMass();
		myFileName = b.getName();
	}

	/**
	 * Getter method: gets the instance variable myXPos
	 * and returns x-position of this body.
	 * @return value of x-position.
	 */

	public double getX() {
		// TODO: complete method

		return myXPos;
	}

	/**
	 * Getter method: gets the instance variable myYPos
	 * and returns y-position of this body.
	 * @return value of y-position.
	 */

	public double getY() {
		// TODO: complete method
		return myYPos;
	}

	/**
	 * Getter method: gets the instance variable myXVel
	 * and returns x-velocity of this body.
	 * @return value of x-velocity.
	 */

	public double getXVel() {
		// TODO: complete method
		return myXVel;
	}
	/**
	 * Return y-velocity of this Body.
	 * @return value of y-velocity.
	 */
	public double getYVel() {
		// TODO: complete method
		return myYVel;
	}

	/**
	 * Getter method: gets the instance variable myMass
	 * and returns mass of this body
	 * @return value of mass
	 */

	public double getMass() {
		// TODO: complete method
		return myMass;
	}

	/**
	 * Getter method: gets the instance variable myFileName
	 * and returns filename of this body
	 * @return value of filename (String)
	 */

	public String getName() {
		// TODO: complete method
		return myFileName;
	}

	/**
	 * Return the distance between this body and another
	 * @param b the other body to which distance is calculated
	 * @return distance between this body and b
	 */
	public double calcDistance(CelestialBody b) {

		return Math.sqrt ( (myXPos - b.getX()) * (myXPos - b.getX()) + (myYPos - b.getY()) * (myYPos - b.getY()));
	}


	/**
	 * Return the force exerted by this body and another
	 * @param b the other body to which force is calculated
	 * @return force exerted between this body and b
	 */

	public double calcForceExertedBy(CelestialBody b) {
		double G = 6.67*1e-11;
		return (G * (b.getMass()) * myMass / (calcDistance(b) * calcDistance(b)));
	}

	/**
	 * Return the x-force exerted by this body and another
	 * @param b the other body to which x-force is calculated
	 * @return force in x direction exerted between this body and b
	 */

	public double calcForceExertedByX(CelestialBody b) {
		// TODO: complete method
		return (calcForceExertedBy(b) * (b.getX() - myXPos) / (calcDistance(b)));
	}

	/**
	 * Return the y-force exerted by this body and another
	 * @param b the other body to which y-force is calculated
	 * @return force in y direction exerted between this body and b
	 */

	public double calcForceExertedByY(CelestialBody b) {
		// TODO: complete method
		return (calcForceExertedBy(b) * (b.getY() - myYPos) / (calcDistance(b)));
	}

	/**
	 * Return the net x-force exerted by this body and another
	 * Adds all x-forces exerted by each body with respect to another
	 * @param bodies all bodies to calculate x-force
	 * @return sum of x-force exerted by each body and b
	 */

	public double calcNetForceExertedByX(CelestialBody[] bodies) {
		// TODO: complete method
		double sum = 0.0;
		for (CelestialBody b : bodies) {
			if (! b.equals(this)) {
				sum = sum + calcForceExertedByX(b);
			}
		}
		return sum;
	}


	/**
	 * Return the net y-force exerted by this body and another
	 * Adds all y-forces exerted by each body with respect to another
	 * @param bodies all bodies to calculate y-force
	 * @return sum of y-force exerted by each body and b
	 */

	public double calcNetForceExertedByY(CelestialBody[] bodies) {
		double sum = 0.0;
		for (CelestialBody b : bodies) {
			if (! b.equals(this)) {
				sum = sum + calcForceExertedByY(b);
			}
		}
		return sum;
	}

	/**
	 * Updates the values for instance variables (position and velocity) in order to
	 * make the simulation run
	 * @param deltaT time step to update position and velocity
	 * @param xforce net x-forces on this body by all other bodies
	 * @param yforce net y-forces on this body by all other bodies
	 */

	public void update(double deltaT, 
			           double xforce, double yforce) {
		// TODO: complete method
		double ax = xforce / myMass;
		double ay = yforce / myMass;
		double nvx = myXVel + deltaT * ax;
		double nvy = myYVel + deltaT * ay;
		double nx = myXPos + deltaT * nvx;
		double ny = myYPos + deltaT * nvy;
		myXPos = nx;
		myYPos = ny;
		myXVel = nvx;
		myYVel = nvy;



	}

	/**
	 * Draws this planet's image at its current position
	 */
	public void draw() {
		StdDraw.picture(myXPos,myYPos,"images/"+myFileName);
	}
}
