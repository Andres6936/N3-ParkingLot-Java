package edu.jabs.parking.domain;

/**
 * This class represents a car inside the parking lot
 */
public class Car
{
    //-----------------------------------------------------------------
    // Attributes
    //-----------------------------------------------------------------

    /** number plate of the car*/
    private String numberPlate;

    /** arrival time of the car */
    private int arrivalHour;

    //-----------------------------------------------------------------
    // Constructors
    //-----------------------------------------------------------------

    /**
     * Creates a car with the basic information
     * @param nPlate - Number Plate - nPlate != null
     * @param nHour - Arrival Time
     */
    public Car( String nPlate, int nHour )
    {
        numberPlate = nPlate;
        arrivalHour = nHour;
    }

    //-----------------------------------------------------------------
    // Methods
    //-----------------------------------------------------------------

    /**
     * Returns the number plate of the car
     * @return numberPlate
     */
    public String getNumberPlate( )
    {
        return numberPlate;
    }

    /**
     * Returns the arrival time of the car
     * @return arrivalTime
     */
    public int getArrivalHour( )
    {
        return arrivalHour;
    }

    /**
     * Points if the number plate of the car is equal to the parameter
     * @param nNumberPlate - Number plate that is being compared - nNumberPlate != null
     * @return true if number plates are the same
     */
    public boolean hasNumberPlate( String nNumberPlate )
    {
        if( numberPlate.equalsIgnoreCase( nNumberPlate ) )
            return true;
        else
            return false;
    }

    /**
     * Calculates the hours that the car has to pay, according to its time in the parking lot
     * @param exitHour - Hour in which the car exits the parking lot - exitHour >= horaLlegada
     * @return Time in parking lot
     */
    public int getTimeInParkingLot( int exitHour )
    {
        return exitHour - arrivalHour + 1;
    }
}