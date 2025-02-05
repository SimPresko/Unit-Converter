import javax.swing.*;
import javax.swing.plaf.basic.BasicComboBoxUI;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class MyFrame extends JFrame {

    //Declare variables
    public JButton buttonLength;
    public JButton buttonTemp;
    public JPanel headerPanel;
    public JPanel mainPanel;
    public JTextField inputField;
    public String[] lengthUnits;
    public String[] temperatureUnits;
    public Map<String,Unit> units;
    public JComboBox<String> fromUnitBox;
    public JComboBox<String> toUnitBox;
    public JTextField outputField;
    public MenuConfig menuConfig;


    MyFrame(){
        initializeUI();
        declareUnits();
    }

    private void initializeUI(){
        //Configure UI when calling the constructor

        //Set frame Properties
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500,500);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setTitle("Unit Converter");

        //Initialize UI items in the frame
        buttonLength = new JButton("Length");
        buttonTemp = new JButton("Temperature");
        mainPanel = new JPanel();
        headerPanel = new JPanel();
        inputField = new JTextField();
        //HashMap for all units
        units = new HashMap<>();
        //String arrays for the unit types
        lengthUnits = new String[]{"Meter","Kilometer","Centimeter","Millimeter","Micrometer","Nanometer","Mile","Yard","Foot","Inch"};
        temperatureUnits = new String[]{"Celsius","Kelvin","Fahrenheit"};
        fromUnitBox = new JComboBox<>(lengthUnits);
        toUnitBox = new JComboBox<>(lengthUnits);
        outputField = new JTextField();
        //Initialize Classes
        menuConfig = new MenuConfig(buttonLength);

        //Set properties for the button that goes to the length panel
        buttonLength.setFocusPainted(false);
        buttonLength.setBounds(0,10,80,35);
        buttonLength.setFont(new Font("Segoe UI",Font.BOLD,16));
        buttonLength.setBackground(new Color(0x3A0CA3));
        buttonLength.setForeground(Color.white);
        buttonLength.setMargin(new Insets(0,5,0,5));
        //Make the button selected by default
        buttonLength.setBorder(BorderFactory.createMatteBorder(2,2,0,2,Color.black));

        //Set properties for the button that goes to the temperature panel
        buttonTemp.setFocusPainted(false);
        buttonTemp.setBounds(90,10,115,35);
        buttonTemp.setFont(new Font("Segoe UI",Font.BOLD,16));
        buttonTemp.setBackground(new Color(0x4361EE));
        buttonTemp.setForeground(Color.white);
        buttonTemp.setMargin(new Insets(0,5,0,5));
        buttonTemp.setBorder(BorderFactory.createMatteBorder(2,2,2,2,Color.black));

        //Add action listeners to the buttons
        buttonLength.addActionListener(e->{
            menuConfig.makeTabClickable(buttonLength);
            repaint();
            fromUnitBox.setModel(new JComboBox<>(lengthUnits).getModel());
            toUnitBox.setModel(new JComboBox<>(lengthUnits).getModel());
            toUnitBox.setSelectedItem(toUnitBox.getItemAt(1));
        });

        buttonTemp.addActionListener(e->{
            menuConfig.makeTabClickable(buttonTemp);
            repaint();
            fromUnitBox.setModel(new JComboBox<>(temperatureUnits).getModel());
            toUnitBox.setModel(new JComboBox<>(temperatureUnits).getModel());
            toUnitBox.setSelectedItem(toUnitBox.getItemAt(1));
        });
        //Set properties for the input and output text fields
        inputField.setBounds(100,50,300,50);
        inputField.setFont(new Font("Segoe UI",Font.BOLD,24));
        inputField.setForeground(new Color(0x4361EE));

        outputField.setBounds(100,330,300,50);
        outputField.setFont(new Font("Segoe UI",Font.BOLD,24));
        outputField.setEditable(false);
        outputField.setBackground(new Color(0x3A0CA3));
        outputField.setBorder(BorderFactory.createLineBorder(new Color(0x4361EE),3));
        outputField.setForeground(Color.white);

        //Set properties for the unit combo boxes
        fromUnitBox.setBounds(130,150,240,50);
        fromUnitBox.setUI(new BasicComboBoxUI());
        fromUnitBox.setFont(new Font("Segoe UI",Font.BOLD,25));
        fromUnitBox.setBorder(null);
        fromUnitBox.setForeground(Color.white);
        fromUnitBox.setFocusable(false);
        fromUnitBox.setBackground(new Color(0x4361EE));


        toUnitBox.setBounds(130,230,240,50);
        toUnitBox.setUI(new BasicComboBoxUI());
        toUnitBox.setFont(new Font("Segoe UI",Font.BOLD,24));
        toUnitBox.setBorder(null);
        toUnitBox.setForeground(Color.white);
        toUnitBox.setFocusable(false);
        toUnitBox.setSelectedItem(toUnitBox.getItemAt(1));
        toUnitBox.setBackground(new Color(0x4361EE));



        //Set properties for the panel in the background of the buttons
        headerPanel.setBackground(new Color(0x4361EE));
        headerPanel.setBounds(0,0,500,45);
        headerPanel.setBorder(BorderFactory.createMatteBorder(0,0,2,0,Color.black));
        headerPanel.setLayout(null);

        //Set properties for the main panel
        mainPanel.setBackground(new Color(0x3A0CA3));
        mainPanel.setBorder(BorderFactory.createMatteBorder(0,2,2,2,Color.black));
        mainPanel.setBounds(0,45,486,418);
        mainPanel.setLayout(null);

        //Add the items to the panels and then to the frame
        headerPanel.add(buttonTemp);
        headerPanel.add(buttonLength);
        mainPanel.add(inputField);
        mainPanel.add(outputField);
        mainPanel.add(fromUnitBox);
        mainPanel.add(toUnitBox);
        this.add(headerPanel);
        this.add(mainPanel);

        //Set frame to be visible
        this.setLayout(null);
        this.setVisible(true);
    }

    //Declaring a class for every unit and putting it in a map
    private void declareUnits(){
        for (String s : lengthUnits){
            double convFactor = switch (s) {
                case "Meter" -> 1.0;
                case "Kilometer" -> 0.001;
                case "Centimeter" -> 100.0;
                case "Millimeter" -> 1000.0;
                case "Micrometer" -> 1000000.0;
                case "Nanometer" -> 1000000000.0;
                case "Mile" -> 0.0006213712;
                case "Yard" -> 1.0936132983;
                case "Foot" -> 3.280839895;
                case "Inch" -> 39.37007874;
                default -> -1.0;
            };
            Unit u = new LengthUnit(s,"length", convFactor);
            units.put(s,u);
        }
        for (String s : temperatureUnits){
            Unit u = new TemperatureUnit(s,"temperature");
            units.put(s,u);
        }
    }


}
