package ooad_project;
public class Info extends JFrame {
    JLabel Label2;
    JButton b1;
    JDialog d;
    String str;
    int i=150;
    Font font1;

    
    Info(){
        d=new JDialog();
        d.setLayout(new BorderLayout());
        d.setContentPane(new JLabel(new ImageIcon("blocks-rainbow-3d-graphicscolorful-background-30288674 copy 3.jpg")));
        Icon normal = new ImageIcon(getClass().getResource("submit-button-blue-hi.png"));
       
        try{
            FileReader fr = new FileReader("ReadMe.txt");
            BufferedReader br = new BufferedReader(fr);
            while(( str = br.readLine())!=null){
                Label2=new JLabel();    
                Label2.setText(str);
                Label2.setBounds(350,i,650,30);
                font1 = new Font("Verdana", Font.BOLD, 20);
                Label2.setFont(font1);
                Label2.setForeground(Color.DARK_GRAY);
                i=i+50;
                d.add(Label2);
            }

        }catch(Exception e){System.out.println(e);}

        b1=new JButton("BACK");
        b1.setBounds(550,600,200,65);
        d.add(b1);
        ButtonHandler button=new ButtonHandler();
        b1.addActionListener(button);
        Toolkit tk = Toolkit.getDefaultToolkit();
        int xSize = ((int) tk.getScreenSize().getWidth());
        int ySize = ((int) tk.getScreenSize().getHeight());
        d.setSize(xSize,ySize);
        d.setVisible(true);
    }
    class ButtonHandler implements ActionListener  {
            @Override
            public void actionPerformed(ActionEvent e){
                if(e.getSource()==b1){
                    new FirstWindow();
                    d.dispose();
                }
            }    
    }      

}