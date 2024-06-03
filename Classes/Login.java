import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.*;
public class Login extends JFrame {
    private JPanel loginPanel;
    private JLabel Heading;
    private JLabel userID;
    private JTextField userIDInput;
    private JTextField pwInput;
    private JLabel pwLabel;
    private JButton loginButton;

    public Login(){
        setContentPane(loginPanel);
        setTitle("BookMyMovie");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1000,1000);
        setLocationRelativeTo(null);
        setVisible(true);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        loginPanel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    ObjectInputStream inStream=null;
                    try{
                        inStream=new ObjectInputStream(new FileInputStream("userID.csv"));
                        UserId sample;
                        try{
                            while(true){
                                sample= (UserId)inStream.readObject();
                               if(userIDInput.getText().equals(sample.getEmail()) && pwInput.getText().equals(sample.getPassword)){
                                   JFrame Option= new JFrame();
                                   Option.show();
                                   dispose();
                               }else{
                                   JOptionPane.showMessageDialog(null, "Wrong username or password","Message",JOptionPane.ERROR_MESSAGE);
                               }
                            }
                        }catch(EOFException e){}
                    }catch(ClassNotFoundException e){
                    }catch(IOException e){
                    }

                }
            }
        });

    }

    public static void main(String[] args) {
        new Login();



    }



}
