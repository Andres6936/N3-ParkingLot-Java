package edu.jabs.parking.gui;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

/**
 * Panel that show the information about the state of the parking lot
 */
public class InformationPanel extends JPanel
{

    //-----------------------------------------------------------------
    // GUI Attributes
    //-----------------------------------------------------------------

    /**
     * Label for the cash value
     */
    private JLabel cashValueLabel;

    /**
     * Label for the number of empty places
     */
    private JLabel emptyPlacesLabel;

    /**
     * Textfield where the cash value is shown
     */
    private JTextField cashValueText;

    /**
     * Textfield where the number of empty places is shown
     */
    private JTextField emptyPlacesText;

    //-----------------------------------------------------------------
    // Constructors
    //-----------------------------------------------------------------

    /**
     * Builds the panel. <br>
     * <b>post: </b>plane was built
     */
    public InformationPanel( )
    {
        initialize( );
    }

    //-----------------------------------------------------------------
    // Methods
    //-----------------------------------------------------------------

    /**
     * Organizes the panel for displaying the cash value and the number of empty places
     */
    private void initialize( )
    {
        cashValueLabel = new JLabel( "Cash: " );

        cashValueText = new JTextField( 10 );
        cashValueText.setEditable( false );
        cashValueText.setForeground( Color.BLUE );
        cashValueText.setBackground( Color.WHITE );

        emptyPlacesLabel = new JLabel( "Empty places: " );

        emptyPlacesText = new JTextField( 10 );
        emptyPlacesText.setEditable( false );
        emptyPlacesText.setForeground( Color.BLUE );
        emptyPlacesText.setBackground( Color.WHITE );

        GridBagLayout gridbag = new GridBagLayout( );
        setLayout( gridbag );
        setBorder( new CompoundBorder( new EmptyBorder( 0, 0, 5, 0 ), new TitledBorder( "Information" ) ) );

        GridBagConstraints gbc = new GridBagConstraints( 0, 0, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets( 5, 5, 5, 5 ), 0, 0 );
        add( cashValueLabel, gbc );

        gbc = new GridBagConstraints( 1, 0, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets( 5, 5, 5, 5 ), 0, 0 );
        add( cashValueText, gbc );

        gbc = new GridBagConstraints( 0, 1, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets( 5, 5, 5, 5 ), 0, 0 );
        add( emptyPlacesLabel, gbc );

        gbc = new GridBagConstraints( 1, 1, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets( 5, 5, 5, 5 ), 0, 0 );
        add( emptyPlacesText, gbc );
    }

    /**
     * Updates the displayed information <br>
     * <b>post: </b>Displayed information is updated
     * @param emptyN - Number of empty places
     * @param cashV - Cash amount
     */
    public void updateValues( int emptyN, int cashV )
    {
        emptyPlacesText.setText( "" + emptyN );
        cashValueText.setText( "$ " + cashV );
    }

}