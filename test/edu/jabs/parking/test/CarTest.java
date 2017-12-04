package edu.jabs.parking.test;

import junit.framework.TestCase;
import edu.jabs.parking.domain.Car;

/**
 * Test for Car Class
 */
public class CarTest extends TestCase
{
    //-----------------------------------------------------------------
    // Attributes
    //-----------------------------------------------------------------

    /**
     * Represented car
     */
    private Car car;

    //-----------------------------------------------------------------
    // Methods
    //-----------------------------------------------------------------

    /**
     * Prepares the test data for scenario 1
     */
    public void setScenario1( )
    {
        car = new Car( "abc123", 12 );
    }

    /**
     * Tests basic data recovering
     */
    public void testData( )
    {
        setScenario1( );

        String plate = car.getNumberPlate( );
        int hour = car.getArrivalHour( );
        assertEquals( "Returned number plate is incorrect", "abc123", plate );
        assertEquals( "Returned arrival time is incorrect", 12, hour );
    }

    /**
     * Tests correct calculation of the parking time for a car
     */
    public void testTimeInParkingLot( )
    {
        setScenario1( );

        assertEquals( "Calculated parking time is incorrect", 1, car.getTimeInParkingLot( 12 ) );
        assertEquals( "Calculated parking time is incorrect", 2, car.getTimeInParkingLot( 13 ) );
        assertEquals( "Calculated parking time is incorrect", 4, car.getTimeInParkingLot( 15 ) );
        assertEquals( "Calculated parking time is incorrect", 6, car.getTimeInParkingLot( 17 ) );
    }

    /**
     * Prueba que el carro verifique correctamente si la placa buscada es la suya
     *
     */
    public void testHasNumberPlate( )
    {
        setScenario1( );

        assertFalse( "Car Number Plate is different", car.hasNumberPlate( "zzz123" ) );
        assertTrue( "Car did not recognize its number plate", car.hasNumberPlate( "abc123" ) );
    }
}
