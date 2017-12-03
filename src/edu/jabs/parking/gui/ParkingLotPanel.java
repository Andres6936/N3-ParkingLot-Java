package edu.jabs.parking.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import edu.jabs.parking.domain.ParkingLot;

/**
 * This panel shows the parking lot state
 */
public class ParkingLotPanel extends JPanel
{
    //-----------------------------------------------------------------
    // GUI Attributes
    //-----------------------------------------------------------------

    /**
     * Indicates the parking place
     */
    private JTextField places[];

    //-----------------------------------------------------------------
    // Attributes
    //-----------------------------------------------------------------

    /**
     * Parking lot reference
     */
    private ParkingLot parkingLot;

    //-----------------------------------------------------------------
    // Constructors
    //-----------------------------------------------------------------

    /**
     * Builds the panel. <br>
     * <b>post: </b>Panel was built
     * @param pl - Reference to the parking lot to be displayed
     */
    public ParkingLotPanel( ParkingLot pl )
    {
        parkingLot = pl;
        initialize( );
    }

    //-----------------------------------------------------------------
    // Methods
    //-----------------------------------------------------------------

    /**
     * Organizes the components for representing the parking lot
     */
    private void initialize( )
    {
        setLayout( new GridLayout( 5, 20 ) );
        setBorder( new CompoundBorder( new EmptyBorder( 0, 0, 5, 0 ), new TitledBorder( "Parking Lot" ) ) );
        setPreferredSize( new Dimension( 10, 170 ) );
        places = new JTextField[parkingLot.calculateFreePlaces( )];
        for( int i = 0; i < parkingLot.calculateFreePlaces( ); i++ )
        {
            places[ i ] = new JTextField( );
            places[ i ].setText( "" + ( i + 1 ) );
            places[ i ].setEnabled( false );
            places[ i ].setBackground( Color.BLUE );
            add( places[ i ] );
        }
    }

    /**
     * Updates the representation of the parking lot<br>
     * <b>post: </b>Updates displayed information
     */
    public void refreshParkingLot( )
    {
        for( int i = 0; i < 87; i++ )
        {
            if( parkingLot.isOccupied( i ) )
                places[ i ].setBackground( Color.RED );
            else
                places[ i ].setBackground( Color.BLUE );
        }
    }
}
