package edu.jabs.parking.gui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

/**
 * This panel shows the current time and executes the operations for the parking lot
 */
public class OperationsPanel extends JPanel implements ActionListener
{
    //-----------------------------------------------------------------
    // Constants
    //-----------------------------------------------------------------

    /**
     * Enter command
     */
    private final static String ENTER = "ENTER";

    /**
     * Exit command
     */
    private final static String EXIT = "EXIT";

    /**
     * Move Forward command
     */
    private final static String MOVE_FORWARD = "MOVE_FORWARD";

    /**
     * Extension 1 command
     */
    private final static String OPTION_1 = "OPTION 1";

    /**
     * Extension 2 command
     */
    private final static String OPTION_2 = "OPTION 2";

    //-----------------------------------------------------------------
    // Attributes
    //-----------------------------------------------------------------

    /**
     * Application main window reference
     */
    private ParkingLotGUI parkingLotGUI;

    //-----------------------------------------------------------------
    // GUI attributes
    //-----------------------------------------------------------------

    /**
     * Field which shows the time
     */
    private JTextField hourText;

    /**
     * Button for entering a car
     */
    private JButton enterButton;

    /**
     * Button for exiting a car
     */
    private JButton exitButton;

    /**
     * Button for moving forward an hour
     */
    private JButton moveForwardButton;

    /**
     * Button for executing option 1
     */
    private JButton option1Button;

    /**
     * Button for executing option 2
     */
    private JButton option2Button;

    //-----------------------------------------------------------------
    // Constructors
    //-----------------------------------------------------------------

    /**
     * Builds the panel. <br>
     * <b>post: </b>The panel was built
     * @param pli - Application main window reference - pli!=null
     */
    public OperationsPanel( ParkingLotGUI pli )
    {
        parkingLotGUI = pli;
        initialize( );
    }

    //-----------------------------------------------------------------
    // Methods
    //-----------------------------------------------------------------

    /**
     * Initializes and organizes the panel components
     */
    private void initialize( )
    {
        setLayout( new FlowLayout( ) );

        hourText = new JTextField( 10 );
        hourText.setEditable( false );
        hourText.setForeground( Color.BLUE );
        hourText.setBackground( getBackground( ) );
        hourText.setBorder( new TitledBorder( "Current time" ) );
        add( hourText );

        JPanel buttonsPanel = new JPanel( );

        enterButton = new JButton( );
        enterButton.setText( "Enter" );
        enterButton.setActionCommand( ENTER );
        enterButton.addActionListener( this );
        buttonsPanel.add( enterButton );

        exitButton = new JButton( );
        exitButton.setText( "Exit" );
        exitButton.setActionCommand( EXIT );
        exitButton.addActionListener( this );
        buttonsPanel.add( exitButton );

        moveForwardButton = new JButton( );
        moveForwardButton.setText( "Move Forward" );
        moveForwardButton.setActionCommand( MOVE_FORWARD );
        moveForwardButton.addActionListener( this );
        buttonsPanel.add( moveForwardButton );

        option1Button = new JButton( );
        option1Button.setText( "Option 1" );
        option1Button.setActionCommand( OPTION_1 );
        option1Button.addActionListener( this );
        buttonsPanel.add( option1Button );

        option2Button = new JButton( );
        option2Button.setText( "Option 2" );
        option2Button.setActionCommand( OPTION_2 );
        option2Button.addActionListener( this );
        buttonsPanel.add( option2Button );

        add( buttonsPanel );
    }

    /**
     * Changes the displayed hour <br>
     * <b>post: </b>New time is displayed
     * @param hour - New hour to be shown
     */
    public void setHout( int hour )
    {
        hourText.setText( hour + ":00" );
    }

    /**
     * Executes the actions according to the pressed button
     * @param event Button Click event
     */
    public void actionPerformed( ActionEvent event )
    {
        String command = event.getActionCommand( );
        if( command.equals( ENTER ) )
        {
            parkingLotGUI.enter( );
        }
        else if( command.equals( EXIT ) )
        {
            parkingLotGUI.exit( );
        }
        else if( command.equals( MOVE_FORWARD ) )
        {
            parkingLotGUI.moveForward( );
        }
        else if( command.equals( OPTION_1 ) )
        {
            parkingLotGUI.funcReqOpt1( );
        }
        else if( command.equals( OPTION_2 ) )
        {
            parkingLotGUI.funcReqOpt2( );
        }
    }
}