package edu.jabs.parking.gui;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import edu.jabs.parking.domain.ParkingLot;

/**
 * This class represents the Parking lot interaction window
 */
public class ParkingLotGUI extends JFrame
{

    //-----------------------------------------------------------------
    // Attributes
    //-----------------------------------------------------------------

    /**
     * Parking lot that is being managed
     */
    private ParkingLot parkingLot;

    //-----------------------------------------------------------------
    // GUI components
    //-----------------------------------------------------------------

    /**
     * Panel which shows the parking lot
     */
    private ParkingLotPanel parkingLotPanel;

    /**
     * Panel which realizes the operations
     */
    private OperationsPanel operationsPanel;

    /**
     * Panel which shows the parking lot information
     */
    private InformationPanel informationPanel;

    //-----------------------------------------------------------------
    // Constructors
    //-----------------------------------------------------------------

    /**
     * Builds the Graphic User Interface
     */
    public ParkingLotGUI( )
    {
        // Creates the parking lot with a rate of $1200
        parkingLot = new ParkingLot( 1200 );

        setTitle( "Parking rate: $" + parkingLot.getRate( ) );

        // Builds the panels
        parkingLotPanel = new ParkingLotPanel( parkingLot );
        operationsPanel = new OperationsPanel( this );
        informationPanel = new InformationPanel( );

        // Organizes main panel
        getContentPane( ).setLayout( new BorderLayout( ) );
        getContentPane( ).add( parkingLotPanel, BorderLayout.NORTH );
        getContentPane( ).add( operationsPanel, BorderLayout.CENTER );
        getContentPane( ).add( informationPanel, BorderLayout.SOUTH );

        setSize( 520, 450 );
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

        refreshInformation( );

    }

    //-----------------------------------------------------------------
    // Methods
    //-----------------------------------------------------------------

    /**
     * Moves Forward one hour in the time.
     */
    public void moveForward( )
    {
        parkingLot.moveForward( );
        refreshInformation( );
    }

    /**
     * Enters a car to the parking lot. It must ask the number plate and the place. If it is not possible to park the car
     * (closed parking lot or full parking lot), it must inform the error.
     */
    public void enter( )
    {
        String plate = JOptionPane.showInputDialog( this, "Please enter the number plate of the entering car" );
        if( plate != null )
        {
            int puesto = parkingLot.enterCar( plate );
            switch( puesto )
            {
                case ParkingLot.FULL:
                    JOptionPane.showMessageDialog( this, "We are sorry: There are no available parking places..." );
                    break;
                case ParkingLot.EXISTENT_CAR:
                    JOptionPane.showMessageDialog( this, "We are sorry: There is a car with the same number plate..." );
                    break;
                case ParkingLot.CLOSED:
                    JOptionPane.showMessageDialog( this, "We are sorry: The parking lot is closed..." );
                    break;
                default:
                    JOptionPane.showMessageDialog( this, "Welcome:\n Your car is in the place: " + ( puesto + 1 ) + "..." );
                    break;
            }
            refreshInformation( );
        }
        else
            JOptionPane.showMessageDialog( this, "Invalid number plate: Please try again..." );

    }

    /**
     * Exits a car from the parking lot. It must ask number plate and then shows the rate.
     * If the number plate does not exist, it must inform the error.
     */
    public void exit( )
    {
        String placa = JOptionPane.showInputDialog( this, "Please enter the number plate of the exiting car" );
        if( placa != null )
        {
            int valor = parkingLot.exitCar( placa );
            switch( valor )
            {
                case ParkingLot.CLOSED:
                    JOptionPane.showMessageDialog( this, "We are sorry: The parking lot is closed..." );
                    break;
                case ParkingLot.NONEXISTENT_CAR:
                    JOptionPane.showMessageDialog( this, "The car with number plate " + placa + " is not in the parking lot..." );
                    break;
                default:
                    JOptionPane.showMessageDialog( this, "Number Plate: " + placa + " has to pay $ " + valor );
                    break;
            }
            refreshInformation( );
        }
        else
            JOptionPane.showMessageDialog( this, "Invalid number plate: Please try again..." );
    }

    /**
     * Updates the displayed information
     */
    public void refreshInformation( )
    {
        parkingLotPanel.refreshParkingLot( );

        operationsPanel.setHout( parkingLot.getCurrentTime( ) );

        informationPanel.updateValues( parkingLot.calculateFreePlaces( ), parkingLot.getCash( ) );
    }

    //-----------------------------------------------------------------
    // Extension Points
    //-----------------------------------------------------------------

    /**
     * Executes option 1
     */
    public void funcReqOpt1( )
    {
        String answer = parkingLot.method1( );
        JOptionPane.showMessageDialog( this, answer, "Answer", JOptionPane.INFORMATION_MESSAGE );
    }

    /**
     * Executes option 2
     */
    public void funcReqOpt2( )
    {
        String answer = parkingLot.method2( );
        JOptionPane.showMessageDialog( this, answer, "Answer", JOptionPane.INFORMATION_MESSAGE );
    }

    //-----------------------------------------------------------------
    // Main
    //-----------------------------------------------------------------

    /**
     * Builds a parking lot and executes the application
     * @param args Application parameters. It is not necessary to use them.
     */
    public static void main( String[] args )
    {

        ParkingLotGUI handler = new ParkingLotGUI( );
        handler.setVisible( true );
    }
}