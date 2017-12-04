package edu.jabs.parking.test;

import junit.framework.TestCase;
import edu.jabs.parking.domain.ParkingLot;

/**
 * Test for ParkingLot Class
 */
public class ParkingLotTest extends TestCase
{
    //-----------------------------------------------------------------
    // Attributes
    //-----------------------------------------------------------------

    /**
     * Parking lot for scenario 1
     */
    private ParkingLot parkingLot1;

    /**
     * Parking lot for scenario 2
     */
    private ParkingLot parkingLot2;

    //-----------------------------------------------------------------
    // Methods
    //-----------------------------------------------------------------

    /**
     * Builds scenario 1
     */
    private void setupScenario1( )
    {
        parkingLot1 = new ParkingLot( 1000 );
    }

    /**
     * Builds scenario 2
     */
    private void setupScenario2( )
    {
        parkingLot2 = new ParkingLot( 1000 );
        parkingLot2.enterCar( "111" );
        parkingLot2.enterCar( "222" );
    }

    /**
     * Verifies the correct time increment
     */
    public void testMoveForward( )
    {
        setupScenario1( );

        int currentTime1 = parkingLot1.getCurrentTime( );
        parkingLot1.moveForward( );
        int horaActual2 = parkingLot1.getCurrentTime( );
        assertEquals( "Time did not move forward correctly", currentTime1 + 1, horaActual2 );
    }

    /**
     * Verifies the correct empty places calculation
     */
    public void testCalculateEmptyPlaces( )
    {
        setupScenario1( );
        setupScenario2( );

        assertEquals( "Empty parking lot has not the correct number of empty places", ParkingLot.SIZE, parkingLot1.calculateFreePlaces( ) );

        assertEquals( "Non-Empty parking lot has not the correct number of empty places", ParkingLot.SIZE - 2, parkingLot2.calculateFreePlaces( ) );

        // Makes the parking lot full
        for( int i = 3; i < ParkingLot.SIZE; i++ )
        {
            parkingLot2.enterCar( "" + i + "" + i + "" + i );
            assertEquals( "Non-Empty parking lot has not the correct number of empty places", ParkingLot.SIZE - i, parkingLot2.calculateFreePlaces( ) );
        }

        // Verifies that if the parking lot is full, it will no receive cars or change the number of available places
        for( int i = 0; i < 10; i++ )
        {
            parkingLot2.enterCar( "aaa" + i + "" + i + "" + i );
            assertEquals( "Full parking lot has not the correct number of empty places", 0, parkingLot2.calculateFreePlaces( ) );
        }
    }

    /**
     * Verifies that rate could be changed
     *
     */
    public void testSetRate( )
    {
        setupScenario1( );

        int oldRate = parkingLot1.getRate( );
        int newRate = 2000;

        parkingLot1.setRate( newRate );
        assertTrue( "Rate was not modified", oldRate != newRate && parkingLot1.getRate( ) == newRate );
    }

    /**
     * Verifies that cash ammount is calculed correctly
     */
    public void testGetCash( )
    {
        setupScenario1( );

        int rate = parkingLot1.getRate( );
        int expectedCash = 0;
        assertEquals( "Starting cash should be 0", expectedCash, parkingLot1.getCash( ) );

        // Entering and exiting car
        parkingLot1.enterCar( "111" );
        parkingLot1.exitCar( "111" );
        expectedCash += rate;
        assertEquals( "A car during one hour should cost 1000", expectedCash, parkingLot1.getCash( ) );

        // Car took one hour
        parkingLot1.enterCar( "222" );
        parkingLot1.moveForward( );
        parkingLot1.exitCar( "222" );
        expectedCash += rate * 2;
        assertEquals( "A car during two hour should cost 2000 more", expectedCash, parkingLot1.getCash( ) );
    }

    /**
     * Verifies usual entering of cars
     */
    public void testEnterCar1( )
    {
        setupScenario1( );

        // Parquear un carro
        int pos1 = parkingLot1.enterCar( "111" );
        assertFalse( "Car was not parked in the empty place", pos1 == ParkingLot.FULL );
        assertFalse( "Car was not parked with the open parking lot", pos1 == ParkingLot.CLOSED );
        assertTrue( "The assigned place is still empty", parkingLot1.isOccupied( pos1 ) );
        assertFalse( "The car can not be exited", parkingLot1.exitCar( "111" ) == ParkingLot.NONEXISTENT_CAR );
    }

    /**
     * Verifies that during entering of cars, none of them is assigned to an occupied place
     */
    public void testEnterCar2( )
    {
        setupScenario1( );

        // Verifies that none of the cars is assigned the same place that an already parked car
        boolean[] vacios = new boolean[ParkingLot.SIZE];

        for( int i = 0; i < ParkingLot.SIZE * 2; i++ )
        {
            int posC = parkingLot1.enterCar( "ppp" + i );
            if( posC != ParkingLot.FULL && posC != ParkingLot.CLOSED )
            {
                assertFalse( "New car was parked in the other car place", vacios[ posC ] );
                vacios[ posC ] = true;
            }
        }
    }

    /**
     * Verifies that during entering of cars, if the parking lot is full, this condition is informed.
     * Verifies also that if a car exits after the parking lot is full, next car can be parked.
     */
    public void testEnterCar3( )
    {
        setupScenario1( );

        // Makes the parking lot full
        for( int i = 0; i < ParkingLot.SIZE * 2; i++ )
        {
            int posC = parkingLot1.enterCar( "ppp" + i );
            if( i > ParkingLot.SIZE )
            {
                assertEquals( "It must warn that the parking lot is full", posC, ParkingLot.FULL );
            }
        }

        parkingLot1.exitCar( "ppp0" );
        int posNueva = parkingLot1.enterCar( "nuevo" );
        assertFalse( "New car should have an assigned place", posNueva == ParkingLot.FULL );
    }

    /**
     * Verifies that cars can not be parked if the parking lot is closed
     */
    public void testEnterCar4( )
    {
        setupScenario1( );

        while( parkingLot1.isOpen( ) )
            parkingLot1.moveForward( );

        int posC = parkingLot1.enterCar( "placa" );
        assertEquals( "The car entered when the parking lot was closed", ParkingLot.CLOSED, posC );
    }

    /**
     * Verifies that two cars with the same number plate can not be parked
     */
    public void testEnterCar5( )
    {
        setupScenario1( );

        // Parks a car
        parkingLot1.enterCar( "111" );

        int posCarro2 = parkingLot1.enterCar( "111" );
        assertFalse( "Two cars with the same number plate were parked", posCarro2 != ParkingLot.EXISTENT_CAR );
    }

    /**
     * Verifies methos isOpen
     */
    public void testIsOpen( )
    {
        setupScenario1( );

        for( int i = parkingLot1.getCurrentTime( ); i < ParkingLot.CLOSE_HOUR + 2; i++ )
        {
            int actual = parkingLot1.getCurrentTime( );
            if( actual >= ParkingLot.OPEN_HOUR && actual < ParkingLot.CLOSE_HOUR )
            {
                assertTrue( "Parking lot is closed while it should be open", parkingLot1.isOpen( ) );
            }
            else
            {
                assertFalse( "Parking lot is open while it should be closed", parkingLot1.isOpen( ) );
            }

            parkingLot1.moveForward( );
        }
    }

    /**
     * Verifies method is occupied for each place
     */
    public void testIsOccupied( )
    {
        setupScenario1( );

        // Verifies that the assigned places are occupied
        int[] posiciones = new int[ParkingLot.SIZE];

        for( int i = 0; i < ParkingLot.SIZE; i++ )
        {
            posiciones[ i ] = parkingLot1.enterCar( "ppp" + i );
            assertTrue( "Car parking place did not appear as occupied", parkingLot1.isOccupied( posiciones[ i ] ) );
        }

        // Exits all the cars from parking lot and verifies that all places are empty
        for( int i = 0; i < ParkingLot.SIZE; i++ )
        {
            parkingLot1.exitCar( "ppp" + i );
            assertFalse( "Parking place left by the car did not appear as empty", parkingLot1.isOccupied( posiciones[ i ] ) );
        }
    }

    /**
     * Verifies exitCar
     *
     */
    public void testExitCar1( )
    {
        setupScenario1( );
        int tarifa = parkingLot1.getRate( );

        // Makes the parking lot full
        for( int i = 0; i < ParkingLot.SIZE; i++ )
        {
            parkingLot1.enterCar( "ppp" + i );
        }

        // Exits all the cars from the parking lot at the same hour
        for( int i = 0; i < ParkingLot.SIZE; i++ )
        {
            int valor = parkingLot1.exitCar( "ppp" + i );

            assertFalse( "Parked car was not found", valor == ParkingLot.NONEXISTENT_CAR );
            assertEquals( "Rate is incorrect", tarifa, valor );
        }

        // Makes the parking lot full
        for( int i = 0; i < ParkingLot.SIZE; i++ )
        {
            parkingLot1.enterCar( "ppp" + i );
        }

        parkingLot1.moveForward( );

        // Exits all the cars from the parking lot after one hour
        for( int i = 0; i < ParkingLot.SIZE; i++ )
        {
            int valor = parkingLot1.exitCar( "ppp" + i );

            assertFalse( "Parked car was not found", valor == ParkingLot.NONEXISTENT_CAR );
            assertEquals( "Rate is incorrect", tarifa * 2, valor );
        }
    }

    /**
     * Verifies exitCar with cars that were not in the parking lot
     */
    public void testExitCar2( )
    {
        setupScenario1( );

        // Exits non-parked cars from the parking lot
        for( int i = 0; i < ParkingLot.SIZE; i++ )
        {
            int valor = parkingLot1.exitCar( "ppp" + i );
            assertTrue( "Non-parked car was found", valor == ParkingLot.NONEXISTENT_CAR );
        }
    }

    /**
     * Verifies that cars can not be exited with a closed parking lot
     */
    public void testSacarCarro4( )
    {
        setupScenario2( );

        while( parkingLot2.isOpen( ) )
            parkingLot2.moveForward( );

        int posC = parkingLot2.exitCar( "111" );
        assertEquals( "The car entered when the parking lot was closed", ParkingLot.CLOSED, posC );
    }

}