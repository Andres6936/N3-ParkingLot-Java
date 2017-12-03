package edu.jabs.parking.domain;

/**
 * This class represents a place in the parking lot
 */
public class Place
{
    //-----------------------------------------------------------------
    // Attributes
    //-----------------------------------------------------------------

    /** Possible car in the place. If there is not a car, car == null */
    private Car car;

    /** Place number in the parking lot */
    private int nPlace;

    //-----------------------------------------------------------------
    // Constructors
    //-----------------------------------------------------------------

    /**
     * Creates an empty place <br>
     * <b>post: </b>An empty place is created
     * @param nPlaceP - Number of place
     */
    public Place( int nPlaceP )
    {
        car = null;
        nPlace = nPlaceP;
    }

    //-----------------------------------------------------------------
    // Methods
    //-----------------------------------------------------------------

    /**
     * Returns the car in the place. If there is not a car in the place, it returns null
     * @return car
     */
    public Car getCar( )
    {
        return car;
    }

    /**
     * Indicates if the place is occupied.
     * @return true if the place is occupied.
     */
    public boolean isOccupied( )
    {
        return car != null;
    }

    /**
     * Parks a car in the place<br>
     * <b>pre: </b>Place is empty <br>
     * <b>post: </b> Place is occupied with the new car pCar
     * @param pCar - Car that is being parked - pCar != null
     */
    public void parkCar( Car pCar )
    {
        car = pCar;
    }

    /**
     * Exits the car from the place. <br>
     * <b>post: </b>Place is empty
     */
    public void exitCar( )
    {
        car = null;
    }

    /**
     * Returns the number of the place
     * @return nPlace
     */
    public int getPlaceNumber( )
    {
        return nPlace;
    }

    /**
     * Indicates if the car has the paramater number plate
     * @param numberPlate
     * @return true if the car has the same numberPlate
     */
    public boolean hasCarWithPlate( String numberPlate )
    {
        if( car == null )
            return false;
        else if( car.hasNumberPlate( numberPlate ) )
            return true;
        else
            return false;
    }

}