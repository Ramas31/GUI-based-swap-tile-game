package ooad_project;
public class FirstWindow extends JFrame {
    public JButton b1,b2,b3,b4;
    public JTextArea t1;
    public JDialog d;
    FirstWindow(){
    	Sound.GAME.loop();
        d= new JDialog();
        d.setLayout(new BorderLayout());
        d.setContentPane(new JLabel(new ImageIcon("blocks-rainbow-3d-graphicscolorful-background-30288674.jpg")));
        Icon normal = new ImageIcon(getClass().getResource("113501,O.png"));
        Icon normal2 = new ImageIcon(getClass().getResource("113501,O copy.png"));
        Icon normal3 = new ImageIcon(getClass().getResource("abc.png"));
        Icon normal4 = new ImageIcon(getClass().getResource("letsswap_icon.png"));
         
        b4 = new JButton(normal4);
        b4.setBounds(500,50,320,320);
        b1=new JButton(normal);
        b1.setBounds(550,400,200,71);
        b2 = new JButton(normal2);
        b2.setBounds(550,500,200,71);
        b3=new JButton(normal3);
        b3.setBounds(550,600,200,71);
        d.add(b4);
        d.add(b1);
        d.add(b2);
        d.add(b3);
        ButtonHandler button=new ButtonHandler(); 
        b3.addActionListener(button);
        b1.addActionListener(button);
        b2.addActionListener(button);
        Toolkit tk = Toolkit.getDefaultToolkit();
        int xSize = ((int) tk.getScreenSize().getWidth());
        int ySize = ((int) tk.getScreenSize().getHeight());
        d.setSize(xSize,ySize);
        d.show();
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
                new Register();
                d.dispose();
            }
            if(e.getSource()==b2){
                new Info();
                d.dispose();
            }
            if(e.getSource()==b3){
               System.exit(0);
            }
        }
    }
}