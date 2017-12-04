package edu.jabs.parking.test;

import junit.framework.TestCase;
import edu.jabs.parking.domain.Car;
import edu.jabs.parking.domain.Place;

/**
 * Test for Place Class
 */
public class PlaceTest extends TestCase
{

    //-----------------------------------------------------------------
    // Attributes
    //-----------------------------------------------------------------

    /**
     * Place for Scenario 1
     */
    private Place place1;

    /**
     * Car for Scenario 1
     */
    private Car car1;

    /**
     * Place for Scenario 2
     */
    private Place place2;

    /**
     * Car for Scenario 2
     */
    private Car car2;

    //-----------------------------------------------------------------
    // Methods
    //-----------------------------------------------------------------

    /**
     * Initializes scenario 1: one place and one car, but the place is empty
     */
    private void setupScenario1( )
    {
        car1 = new Car( "aaa111", 6 );
        place1 = new Place( 1 );
    }

    /**
     * Initializes the scenario 2: one place and one car parked in the place
     */
    private void setupScenario2( )
    {
        car2 = new Car( "bbb222", 6 );
        place2 = new Place( 2 );
        place2.parkCar( car2 );
    }

    /**
     * Tests that method getCar returns the expected information for an empty place
     */
    public void testGetCar1( )
    {
        setupScenario1( );

        assertNull( "place 1 is empty but getCar() did not return null", place1.getCar( ) );
    }

    /**
     * Tests that method getCar returns the expected information for an occupied place
     */
    public void testGetCar2( )
    {
        setupScenario2( );

        assertTrue( "place 2 did not return expected car", car2 == place2.getCar( ) );
    }

    /**
     * Tests method getPlaceNumber
     */
    public void testGetPlaceNumber( )
    {
        setupScenario1( );

        assertEquals( "Returned number is incorrect", 1, place1.getPlaceNumber( ) );
    }

    /**
     * Tests method isOccupied
     */
    public void testIsOccupied( )
    {
        setupScenario1( );
        setupScenario2( );

        assertFalse( "Place 1 should be occupied", place1.isOccupied( ) );
        assertTrue( "Place 2 should be occupied", place2.isOccupied( ) );
    }

    /**
     * Tests parking a car in an empty place
     */
    public void testParkCar( )
    {
        setupScenario1( );

        assertNull( "Place 1 is empty but getCar() did not return null", place1.getCar( ) );
        place1.parkCar( car1 );
        assertTrue( "Place 1 did not return expected car: car was not parked", car1 == place1.getCar( ) );
        assertTrue( "Place 1 should be occupied", place1.isOccupied( ) );
    }

    /**
     * Tests exiting a car form a place
     *
     */
    public void testExitCar( )
    {
        setupScenario2( );

        assertTrue( "Place 2 did not return expected car", car2 == place2.getCar( ) );
        place2.exitCar( );
        assertNull( "Place 2 should be empty but getCar did not return null", place2.getCar( ) );
        assertFalse( "Place 2 should be empty", place2.isOccupied( ) );
    }

    /**
     * Tests method hasCarWithPlace
     *
     */
    public void testHasCarWithPlate( )
    {
        setupScenario2( );

        assertFalse( "Place found a wrong plate", place2.hasCarWithPlate( "aaa111" ) );
        assertTrue( "Place did not find the plate of the parked car", place2.hasCarWithPlate( "bbb222" ) );
    }
}