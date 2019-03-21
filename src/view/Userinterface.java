package view;

import model.LokalBruger;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;

public class Userinterface {
    public Userinterface() {

    }
    public void showhighscore(Map<String,Integer> map){
        String message="\nHighscore \n ";
        for (Map.Entry<String, Integer> entry : map.entrySet())
        {
            message += "\n\n\n" + "Name:" + entry.getKey() + "Score:" + entry.getValue();

        }

        JOptionPane.showMessageDialog(null, message,"Highscore",JOptionPane.INFORMATION_MESSAGE,null);
    }
    public int failedLogin(){
        return JOptionPane.showConfirmDialog(null,"Fejl i brugernavn eller kode","Login Fejlede",JOptionPane.CLOSED_OPTION,JOptionPane.INFORMATION_MESSAGE);
    }
    public int afslutningsDialog(String besked, String title) {
        int dialogButton = JOptionPane.YES_NO_OPTION;
        return JOptionPane.showConfirmDialog(null, besked, title, dialogButton);
    }

    public int resultatDialog(String synligt, ArrayList brugte, int forkerte) throws URISyntaxException {
        return JOptionPane.showConfirmDialog(null, "Ordet:\n" + synligt + "\n" + "Brugte bogstaver:\n" + brugte + "\n" + "Antal Forkerte: " + forkerte,
                "Galgespillet",
                JOptionPane.CLOSED_OPTION,
                JOptionPane.INFORMATION_MESSAGE, tegnImageIcon(forkerte));
    }

    public String gætBogstavDialog() {

        String tast = JOptionPane.showInputDialog(null, "Gæt på et bogstav", "Galgespillet", JOptionPane.INFORMATION_MESSAGE);

        if (tast != null) {
            if (tast.length() > 1) {
                JOptionPane.showMessageDialog(null, "Indtast kun 1 bogstav");
            }

        }
        return tast;
    }

    public LokalBruger loginDialog() {
        JTextField xField = new JTextField(5);
        JTextField yField = new JTextField(5);

        JPanel myPanel = new JPanel();
        myPanel.add(new JLabel("Navn"));
        myPanel.add(xField);
        myPanel.add(Box.createHorizontalStrut(15)); // a spacer
        myPanel.add(new JLabel("Kode"));
        myPanel.add(yField);

        int result = JOptionPane.showConfirmDialog(null, myPanel,
                "Indtast navn og password", JOptionPane.OK_CANCEL_OPTION);
        String uname = "";
        String pass = "";
        if (result == JOptionPane.OK_OPTION) {
            uname = xField.getText();
            pass = String.valueOf(yField.getText());
        }


        return new LokalBruger(uname, pass);

    }

    public ImageIcon tegnImageIcon(int i) throws URISyntaxException {

        String imageFile ="";
        switch (i) {
            case 0:
                imageFile = "galge.png";
                break;

            case 1:
                imageFile = "forkert1.png";
                break;
            case 2:
                imageFile = "forkert2.png";
                break;

            case 3:
                imageFile = "forkert3.png";
                break;
            case 4:
                imageFile = "forkert4.png";
                break;
            case 5:
                imageFile = "forkert5.png";
                break;
            case 6:
                imageFile = "forkert6.png";
                break;
            case 7:
                imageFile = "gameover.png";
                break;

        }


        BufferedImage image = null;

        try {
            image = ImageIO.read(this.getClass().getClassLoader().getResource(imageFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ImageIcon(image);


    }



}
