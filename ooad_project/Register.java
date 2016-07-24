
package ooad_project;
public class Register extends JFrame {
        JLabel Label2;
        JTextArea Area1;
        JButton b1;
        static String Name;
        JDialog d;
        
        Register(){

            d=new JDialog();
            d.setLayout(new BorderLayout());
            d.setContentPane(new JLabel(new ImageIcon("blocks-rainbow-3d-graphicscolorful-background-30288674 copy 2.jpg")));
            Icon normal = new ImageIcon(getClass().getResource("submit-button-blue-hi.png"));
            
            Label2=new JLabel();
            Label2.setText("Enter your name");
            Label2.setBounds(550,250,210,30);
            Font font1 = new Font("Verdana", Font.BOLD, 20);
            Label2.setFont(font1);
            Label2.setForeground(Color.DARK_GRAY);
            
            
            Area1=new JTextArea();
            Area1.setBounds(545,300,210,40);
            Font font2 = new Font("Verdana", Font.BOLD, 30);
            Area1.setFont(font2);
            Area1.setForeground(Color.DARK_GRAY);
            
            b1=new JButton(normal);
            b1.setBounds(550,400,200,65);
            
            ButtonHandler button=new ButtonHandler();
            b1.addActionListener(button);
            
            d.setTitle("Register");
            d.setLocationRelativeTo(null);
            d.add(Label2);
            d.add(Area1);
            d.add(b1);
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
                     new Register();
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
            if((e.getSource()==b1)&&!(Area1.getText()).equals("")){
                Name=Area1.getText();
                StringTokenizer name=new StringTokenizer(Name," ");
                if(!(name.nextToken().equals(Name))){
                    JOptionPane.showMessageDialog(null,"No spaces allowed");
                    d.dispose();
                    new Register();
                }
                else{
                    new Category();
                    d.dispose();
                
                }
            }
            else{
                JOptionPane.showMessageDialog(null,"Please enter a name!");
            }
        }
    }       

}
class image {

    String imageLocation[]= new String[4];
    BufferedImage img;
    BufferedImage imgs[];

    image(){
        imageLocation[0]="animals";
        imageLocation[1]="landscapes";
        imageLocation[2]="paintings";
        imageLocation[3]="vehicles";
    }
    public  void fetchImage(int category) throws IOException 
    {
        category=category-1;
        File dir = new File(imageLocation[category]);
        File[] files = dir.listFiles();
        
        int size = files.length;
        Random r= new Random();
        int rd = r.nextInt(size);
        File file = new File(files[rd].getPath()); 
        FileInputStream fis = new FileInputStream(file);  
        img = ImageIO.read(fis); 
        
    }
    public void setCroppedImages() throws IOException {


        int rows = 4; 
        int cols = 4;  
        int chunks = rows * cols;  
        int chunkWidth = img.getWidth() / cols; // determines the chunk width and height  
        int chunkHeight = img.getHeight() / rows;  
        int count = 0;  
        imgs = new BufferedImage[chunks]; //Image array to hold image chunks  
        for (int x = 0; x < rows; x++) {  
            for (int y = 0; y < cols; y++) {  
                //Initialize the image array with image chunks  
                imgs[count] = new BufferedImage(chunkWidth, chunkHeight, img.getType());  
                // draws the image chunk  
                Graphics2D gr = imgs[count++].createGraphics();  
                gr.drawImage(img, 0, 0, chunkWidth, chunkHeight, chunkWidth * y, chunkHeight * x, chunkWidth * y + chunkWidth, chunkHeight * x + chunkHeight, null);  
                gr.dispose();  
            }  
        }   
  
        //writing mini images into image files  
        for (int i = 0; i < imgs.length; i++) {  
            ImageIO.write(imgs[i], "jpg", new File("img" + i + ".jpg"));  
        }  
    }  

    public BufferedImage[] sendCroppedImages(){
        return imgs;
    }


}