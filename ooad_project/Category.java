
package ooad_project;
public class Category extends JFrame{
    JLabel Label1;
    JDialog d;
    JButton b1,b2,b3,b4;
    Category(){
        d=new JDialog();

        d.setLayout(new BorderLayout());
        d.setContentPane(new JLabel(new ImageIcon("blocks-rainbow-3d-graphicscolorful-background-30288674 copy 2.jpg")));
        Icon normal = new ImageIcon(getClass().getResource("submit-button-blue-hi.png"));
        Label1 = new JLabel();
        Label1.setText("Choose a Category");
        Label1.setBounds(550,250,210,30);
        Font font1 = new Font("Verdana", Font.BOLD, 20);
        Label1.setFont(font1);
        Label1.setForeground(Color.DARK_GRAY);
        b1=new JButton("Animals");
        b1.setBounds(550,350,200,65);
        b2=new JButton("Landscapes");
        b2.setBounds(550,450,200,65);
        b3=new JButton("Paintings");
        b3.setBounds(550,550,200,65);
        b4=new JButton ("Vehicles");
        b4.setBounds(550,650,200,65);

        ButtonHandler button=new ButtonHandler();
        b1.addActionListener(button);
        b2.addActionListener(button);
        b3.addActionListener(button);
        b4.addActionListener(button);
        d.add(Label1);
        d.add(b1);
        d.add(b2);
        d.add(b3);
        d.add(b4);

        Toolkit tk = Toolkit.getDefaultToolkit();
        int xSize = ((int) tk.getScreenSize().getWidth());
        int ySize = ((int) tk.getScreenSize().getHeight());
        d.setSize(xSize,ySize);
        d.setVisible(true);
        d.addWindowListener(new WindowAdapter(){
             @Override
             public void windowClosing(WindowEvent e){ 
                int i=JOptionPane.showConfirmDialog(null, "Exit anyway?", "Exit", 0);
                 if(i==1){
                     new FirstWindow();
                 }
                 else{
                     JOptionPane.showMessageDialog(null,"exiting..");
                     Sound.GAME.stop();
                 }
                e.getWindow().dispose(); 
             }
         });


    }
    class ButtonHandler implements ActionListener  {
        @Override
        public void actionPerformed(ActionEvent e){
            if(e.getSource()==b1){
                Game gm= new Game(4,4,1);
                    try{
                    gm.drawMainBoard();
                }catch(Exception eqq){}
                    d.dispose();
                
            }
            if(e.getSource()==b2){
              Game gm= new Game(4,4,2);
                    try{
                    gm.drawMainBoard();
                }catch(Exception eqq){}
                    d.dispose();
                
            }
            if(e.getSource()==b3){
              Game gm= new Game(4,4,3);
                    try{
                    gm.drawMainBoard();
                }catch(Exception eqq){}
                    d.dispose();
                
            }
            if(e.getSource()==b4){
                Game gm= new Game(4,4,4);
                    try{
                    gm.drawMainBoard();
                }catch(Exception eqq){}
                    d.dispose();
                
            }
        }
    }

}