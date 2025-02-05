import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.plaf.basic.BasicComboBoxUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class MyFrame extends JFrame {

    //Declare variables
    public JButton buttonLength;
    public JButton buttonTemp;
    public JButton buttonWeight;
    public JButton buttonTime;
    public JPanel headerPanel;
    public JPanel mainPanel;
    public JTextField inputField;
    public String[] lengthUnits;
    public String[] temperatureUnits;
    public String[] weightUnits;
    public String[] timeUnits;
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
        buttonWeight = new JButton("Weight");
        buttonTime = new JButton("Time");
        mainPanel = new JPanel();
        headerPanel = new JPanel();
        inputField = new JTextField();
        //HashMap for all units
        units = new HashMap<>();
        //String arrays for the unit types
        lengthUnits = new String[]{"Meter", "Kilometer", "Centimeter", "Millimeter", "Micrometer", "Nanometer", "Mile",
                "Yard", "Foot", "Inch"};
        temperatureUnits = new String[]{"Celsius", "Kelvin", "Fahrenheit"};
        weightUnits = new String[]{"Kilogram", "Gram", "Milligram", "Metric Ton", "Long Ton", "Short Ton", "Pound",
                "Ounce", "Carat"};
        timeUnits = new String[]{"Second", "Millisecond", "Microsecond", "Nanosecond", "Minute", "Hour", "Day", "Week",
                "Month", "Year"};
        //Combo boxes
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

        buttonWeight.setFocusPainted(false);
        buttonWeight.setBounds(215,10,80,35);
        buttonWeight.setFont(new Font("Segoe UI",Font.BOLD,16));
        buttonWeight.setBackground(new Color(0x4361EE));
        buttonWeight.setForeground(Color.white);
        buttonWeight.setMargin(new Insets(0,5,0,5));
        buttonWeight.setBorder(BorderFactory.createMatteBorder(2,2,2,2,Color.black));

        buttonTime.setFocusPainted(false);
        buttonTime.setBounds(305,10,60,35);
        buttonTime.setFont(new Font("Segoe UI",Font.BOLD,16));
        buttonTime.setBackground(new Color(0x4361EE));
        buttonTime.setForeground(Color.white);
        buttonTime.setMargin(new Insets(0,5,0,5));
        buttonTime.setBorder(BorderFactory.createMatteBorder(2,2,2,2,Color.black));

        //Add action listeners to the buttons
        buttonLength.addActionListener(e->{
            menuConfig.makeTabClickable(buttonLength);
            fromUnitBox.setModel(new JComboBox<>(lengthUnits).getModel());
            toUnitBox.setModel(new JComboBox<>(lengthUnits).getModel());
            toUnitBox.setSelectedItem(toUnitBox.getItemAt(1));
            updateOutput();
            repaint();
        });

        buttonTemp.addActionListener(e->{
            menuConfig.makeTabClickable(buttonTemp);
            fromUnitBox.setModel(new JComboBox<>(temperatureUnits).getModel());
            toUnitBox.setModel(new JComboBox<>(temperatureUnits).getModel());
            toUnitBox.setSelectedItem(toUnitBox.getItemAt(1));
            updateOutput();
            repaint();

        });

        buttonWeight.addActionListener(e->{
            menuConfig.makeTabClickable(buttonWeight);
            fromUnitBox.setModel(new JComboBox<>(weightUnits).getModel());
            toUnitBox.setModel(new JComboBox<>(weightUnits).getModel());
            toUnitBox.setSelectedItem(toUnitBox.getItemAt(1));
            updateOutput();
            repaint();
        });

        buttonTime.addActionListener(e->{
            menuConfig.makeTabClickable(buttonTime);
            fromUnitBox.setModel(new JComboBox<>(timeUnits).getModel());
            toUnitBox.setModel(new JComboBox<>(timeUnits).getModel());
            toUnitBox.setSelectedItem(toUnitBox.getItemAt(1));
            updateOutput();
            repaint();
        });


        //Set properties for the input and output text fields
        inputField.setBounds(100,50,300,50);
        inputField.setFont(new Font("Segoe UI",Font.BOLD,24));
        inputField.setForeground(new Color(0x4361EE));
        inputField.setCaretColor(new Color(0x4361EE));
        inputField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateOutput();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateOutput();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateOutput();
            }
        });

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
        fromUnitBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateOutput();
            }
        });


                toUnitBox.setBounds(130, 230, 240, 50);
        toUnitBox.setUI(new BasicComboBoxUI());
        toUnitBox.setFont(new Font("Segoe UI",Font.BOLD,24));
        toUnitBox.setBorder(null);
        toUnitBox.setForeground(Color.white);
        toUnitBox.setFocusable(false);
        toUnitBox.setSelectedItem(toUnitBox.getItemAt(1));
        toUnitBox.setBackground(new Color(0x4361EE));
        toUnitBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateOutput();
            }
        });



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
        headerPanel.add(buttonLength);
        headerPanel.add(buttonTemp);
        headerPanel.add(buttonWeight);
        headerPanel.add(buttonTime);
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
                case "Mile" -> 1/1609.344;
                case "Yard" -> 1/0.9144;
                case "Foot" -> 1/0.3048;
                case "Inch" -> 1/0.0254;
                default -> -1.0;
            };
            Unit u = new LengthUnit(s,"length", convFactor);
            units.put(s,u);
        }
        for (String s : temperatureUnits){
            Unit u = new TemperatureUnit(s,"temperature");
            units.put(s,u);
        }
        for (String s : weightUnits){
            double convFactor = switch (s) {
                case "Kilogram" -> 1.0;
                case "Gram" -> 1000.0;
                case "Milligram" -> 1000000.0;
                case "Metric Ton" -> 0.001;
                case "Long Ton" -> 1/1016.04608;
                case "Short Ton" -> 1/907.184;
                case "Pound" -> 1/0.453592;
                case "Ounce" -> 1/0.0283495;
                case "Carat" -> 5000.0;
                default -> -1.0;
            };
            Unit u = new WeightUnit(s,"weight", convFactor);
            units.put(s,u);
        }
        for (String s : timeUnits){
            double convFactor = switch (s) {
                case "Minute" -> 1.0;
                case "Second" -> 60.0;
                case "Millisecond" -> 60000.0;
                case "Microsecond" -> 60000000.0;
                case "Nanosecond" -> 60000000000.0;
                case "Hour" -> 1/60.0;
                case "Day" -> 1/1440.0;
                case "Week" -> 1/10080.0;
                case "Month" -> 1/43200.0;
                case "Year" -> 1/525600.0;
                default -> -1.0;
            };
            Unit u = new TimeUnit(s,"time", convFactor);
            units.put(s,u);
        }
    }

    //Update output field when something is typed in the input field
    public void updateOutput(){
        String input = inputField.getText();
        try {
            if (!input.isEmpty()){
                double value = Double.parseDouble(input);
                String fromUnit = (String) fromUnitBox.getSelectedItem();
                String toUnit = (String) toUnitBox.getSelectedItem();
                double result = units.get(toUnit).fromBaseUnit(units.get(fromUnit).toBaseUnit(value));
                outputField.setText(String.valueOf(result));
            }
        } catch (Exception e){
            if (input.contains(",")) outputField.setText("Use '.' instead of ','");
            else outputField.setText("Enter valid number");
            System.out.println("Exception");
        }
    }


}
