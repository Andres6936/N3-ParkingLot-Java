package edu.jabs.parking.domain;

/**
 * This class represents a parking lot with SIZE places
 */
public class ParkingLot
{
    //-----------------------------------------------------------------
    // Constants
    //-----------------------------------------------------------------

    /**
     * Indicates the number of places in the parking lot
     */
    public static final int SIZE = 87;

    /**
     *Error code for the full parking lot
     */
    public static final int FULL = -1;

    /**
     * Error code for the closed parking lot
     */
    public static final int CLOSED = -2;

    /**
     * Error code for a searched car that is not in the parking lot
     */
    public static final int NONEXISTENT_CAR = -3;

    /**
     * Error code for an entering car with a number plate equal to another in the parking lot
     */
    public static final int EXISTENT_CAR = -4;

    /**
     * Hour in which the parking lot is open
     */
    public static final int OPEN_HOUR = 6;

    /**
     * Hour in which the parking lot is closed
     */
    public static final int CLOSE_HOUR = 20;

    //-----------------------------------------------------------------
    // Attributes
    //-----------------------------------------------------------------

    /** Fixed-size array for the number of places */
    private Place places[];

    /** Parking lot rate */
    private int rate;

    /** Parking lot cash */
    private int cash;

    /** Current time in the parking lot*/
    private int currentTime;

    /** Indicates if the parking lot is open */
    private boolean open;

    //-----------------------------------------------------------------
    // Constructors
    //-----------------------------------------------------------------

    /**
     * Creates a parking lot with the basic information. <br>
     * <b>post: </b> Parking lot was created with the established rate
     * @param nRate - parking lot rate
     */
    public ParkingLot( int nRate )
    {
        currentTime = OPEN_HOUR;
        open = true;
        rate = nRate;
        cash = 0;
        // Creates the array of places and initializes each one
        places = new Place[SIZE];
        for( int i = 0; i < SIZE; i++ )
            places[ i ] = new Place( i );

    }

    //-----------------------------------------------------------------
    // Methods
    //-----------------------------------------------------------------

    /**
     * Enters a car to the parking lot <br>
     * <b>post: </b>Car was parked in the indicated place
     * @param nNumberPlate - Number plate of the entering car - nNumberPlate!=null
     * @return place in which the car must be parked. <br>
     *         If the parking lot is full, it returns FULL. <br>
     *         If the parking lot is closed, it returns CLOSED.
     */
    public int enterCar( String nNumberPlate )
    {
        if( !open )
            return CLOSED;
        else
        {
            // Searches in the parking lot for a car with the indicated number plate
            int carPlace = searchCarPlace( nNumberPlate.toUpperCase( ) );
            if( carPlace != NONEXISTENT_CAR )
                return EXISTENT_CAR;

            // Searches an empty place an adds the car on it
            int place = searchFreePlace( );
            if( place != FULL )
            {
                Car carroEntrando = new Car( nNumberPlate, currentTime );
                places[ place ].parkCar( carroEntrando );
            }
            return place;
        }
    }

    /**
     * Exits a car from the parking lot and calculates the amount to be paid<br>
     * <b>post: </b> Car exited the parking lot and its place is now empty
     * @param numberPlate - Number plate of the exiting car - numberPlate != null
     * @return the value to be paid.
     * 		   if the car is not in the parking lot, it returns NONEXISTENT CAR
     *         If the parking lot is closed, it returns CLOSED
     */
    public int exitCar( String numberPlate )
    {
        if( !open )
            return CLOSED;

        int place = searchCarPlace( numberPlate.toUpperCase( ) );
        if( place == NONEXISTENT_CAR )
            return NONEXISTENT_CAR;
        else
        {
            Car car = places[ place ].getCar( );
            int nHours = car.getTimeInParkingLot( currentTime );
            int owe = nHours * rate;
            cash = cash + owe;
            places[ place ].exitCar( );
            return owe;
        }
    }

    /**
     * Indicates the cash amount in the parking lot.
     * @return cash
     */
    public int getCash( )
    {
        return cash;
    }

    /**
     * Indicates the amount of empty places
     * @return empty places
     */
    public int calculateFreePlaces( )
    {
        int puestosLibres = 0;
        for( int i = 0; i < SIZE; i++ )
            if( !places[ i ].isOccupied( ) )
                puestosLibres = puestosLibres + 1;
        return puestosLibres;
    }

    /**
     * Changes the current parking rate
     * @param nRate - new parking rate
     */
    public void setRate( int nRate )
    {
        rate = nRate;
    }

    /**
     * Searches an empty place and returns it.
     * @return number of the empty place
     *         if an empty place is not found, it returns FULL
     */
    private int searchFreePlace( )
    {
        int puesto = FULL;
        for( int i = 0; i < SIZE && puesto == FULL; i++ )
            if( !places[ i ].isOccupied( ) )
                puesto = i;
        return puesto;
    }

    /**
     * Indicates the number of place in which there is a car with the given number plate.
     * @param numberPlate - Number plate of the searched car - numberPlate != null
     * @return number of the place in which the car is
     *         if the car is not found, it returns NONEXISTENT_CAR
     */
    private int searchCarPlace( String numberPlate )
    {
        int puesto = NONEXISTENT_CAR;
        for( int i = 0; i < SIZE && puesto == NONEXISTENT_CAR; i++ )
            if( places[ i ].hasCarWithPlate( numberPlate ) )
                puesto = i;
        return puesto;
    }

    /**
     * Moves forward an hour in the parking lot. If the current time is equal to the close hour, the parking lot is closed.
     */
    public void moveForward( )
    {
        if( currentTime <= CLOSE_HOUR )
        {
            currentTime = ( currentTime + 1 );
        }
        if( currentTime == CLOSE_HOUR )
            open = false;
    }

    /**
     * Returns the current time
     * @return currentTime
     */
    public int getCurrentTime( )
    {
        return currentTime;
    }

    /**
     * Indicates if the parking lot is open
     * @return true if the parking lot is open
     */
    public boolean isOpen( )
    {
        return open;
    }

    /**
     * Returns the parking rate
     * @return rate
     */
    public int getRate( )
    {
        return rate;
    }

    /**
     * Indicates if the place i is occupied
     * @param i - The place which it is wanted to know if is occupied - i >= 0 y i < places.length
     * @return true if the place is occupied.
     */
    public boolean isOccupied( int i )
    {
        return places[ i ].isOccupied( );
    }

    //-----------------------------------------------------------------
    // Extension points
    //-----------------------------------------------------------------

    /**
     * Executes option 1 and its result
     * @return answer1
     */
    public String method1( )
    {
        return "answer1";
    }

    /**
     * Executes option 2 and its result
     * @return answer2
     */
    public String method2( )
    {
        return "asnwer2";
    }

}