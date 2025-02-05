import javax.swing.*;
import java.awt.*;
public class MenuConfig {
    JButton selectedButton;

    MenuConfig(JButton button){
        selectedButton = button;
    }

    public void makeTabClickable(JButton button){
        selectedButton.setBorder(BorderFactory.createMatteBorder(2,2,2,2,Color.black));
        selectedButton.setBackground(new Color(0x4361EE));
        button.setBackground(new Color(0x3A0CA3));
        button.setBorder(BorderFactory.createMatteBorder(2,2,0,2,Color.black));
        selectedButton = button;
    }

}
