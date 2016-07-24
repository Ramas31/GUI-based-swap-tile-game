package ooad_project;
public class Board extends JFrame implements ActionListener{

    JButton finish;
    JButton autoSolve;
    JButton reset;
    JButton hs;
    Tile[] tileArray;
    JLabel score,urscore,time;
    Tile[] shuffle;
    BufferedImage[] imArray;
    JButton[] buttonTiles;
    int numClicks=0;
    int oddClickIndex = 0;
    int currentIndex = 0;
    int rows;
    int columns;
    int category;
    Timer myTimer;
    Timer timerSW;
    int totalMoves = 0;
    public static int autosolved=0;
    Random rd= new Random();
    Tile x= new Tile();
    JDialog d;
    int millisec;
    int sec;
    int mins;
    int hrs;
    int mutex;
    timeClass t1;
    autosolveClass t2 ;
    Board(int r, int c, int cate) throws IOException{

        
        super("PICPUZZLE");
        score=new JLabel("Number of Swaps");
        score.setBounds(1000,200,200,50);
        Font font1 = new Font("Verdana", Font.BOLD, 20);
        score.setFont(font1);
        score.setForeground(Color.DARK_GRAY);
        urscore = new JLabel(totalMoves+"");
        urscore.setBounds(1070,230,200,50);
        urscore.setFont(font1);
        urscore.setForeground(Color.BLACK);
        JLabel timevar = new JLabel("Time");
        timevar.setBounds(1050,300,200,50);
        timevar.setFont(font1);
        timevar.setForeground(Color.DARK_GRAY);
        time=new JLabel(hrs+":"+mins+":"+sec);
        time.setBounds(1050,340,200,50);
        time.setFont(font1);
        time.setForeground(Color.BLACK);
        hs = new JButton ("High Score");
        hs.setBounds(800,150,100,50);
        finish = new JButton("Finish");
        finish.setBounds(800,250,100,50);

        autoSolve = new JButton("AutoSolve");
        autoSolve.setBounds(800,350,100,50);
        reset = new JButton("Reset");
        reset.setBounds(800,450,100,50);
        rows = r;
        
        columns = c;
        category = cate;
        tileArray = new Tile[r*c];
        shuffle = new Tile[r*c];
        buttonTiles = new JButton[r*c];
        d=new JDialog();
        d.setLayout(new BorderLayout());
        d.setContentPane(new JLabel(new ImageIcon("blocks-rainbow-3d-graphicscolorful-background-30288674 copy 2.jpg")));
        Toolkit tk = Toolkit.getDefaultToolkit();
        int xSize = ((int) tk.getScreenSize().getWidth());
        int ySize = ((int) tk.getScreenSize().getHeight());
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

        initializeTiles();

        for(int i=0; i<r*c; i++)
        {
            Icon ic=new ImageIcon(tileArray[i].imageLoc); 
            buttonTiles[i]=new JButton(ic);  
      
            buttonTiles[i].setBounds(tileArray[i].x,tileArray[i].y,tileArray[i].tileWidth,tileArray[i].tileHeight); 
            buttonTiles[i].addActionListener(this);
            d.add(buttonTiles[i]);
        }
        myTimer=new Timer(5000,new TimerListener());
        myTimer.start();
        timerSW=new Timer(500000,new TimerListener1());
        timerSW.start();
        d.add(finish);
        d.add(autoSolve);
        d.add(reset);
        d.add(hs);
        d.add(score);
        d.add(urscore);
        d.add(time);
        d.add(timevar);
        t1=new timeClass();  
        t1.start(); 
        t2 = new autosolveClass(); 
        
        
        autoSolve.addActionListener(this);
        finish.addActionListener(this);
        reset.addActionListener(this);
        hs.addActionListener(this);
        d.setSize(xSize,ySize);
        d.setVisible(true);

    }
    private class TimerListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
        try{
            getShuffledBoard();
            Sound.GAME.stop();
            myTimer.stop();
            

        }catch(Exception eq){}
        }
    }
    private class TimerListener1 implements ActionListener{
        public void actionPerformed(ActionEvent e){
            
        
        }
    }

    public void actionPerformed(ActionEvent e){  

        int flag=0;
        numClicks++;
        if(myTimer.isRunning()){
            return;
        }

        if(e.getSource()==hs){
          HighScoreManagerFrags highScore = new HighScoreManagerFrags();
          highScore.getScore();
        }
        if(e.getSource() ==autoSolve){
            t2.start(); 
            mutex=1;
            if(autosolved==1){
                System.out.println(autosolved);
                mutex=0;
                t2.stop();
            }
        }
        if(e.getSource()==reset){
            try{
                if(mutex==1){
                    return;
                }
                autosolved=0;
                totalMoves=0;
                getShuffledBoard();
            }catch(Exception eq){}
        }


        if(e.getSource() ==finish){
            if(autosolved==1)
                    return;
                else{
                	timerSW.stop();
            for(int i=0; i<rows*columns; i++){
                if(!(shuffle[i].imageLoc.equals(tileArray[i].imageLoc))){
                    flag=1;
                    break;
                }
                
            }
            if(flag==0){
                try{
                FileWriter fw = new FileWriter("scores.txt",true);
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write(Register.Name +" " + (1000-(totalMoves*10)) + "\n");
                JOptionPane.showMessageDialog(null,"Congratulations!! You succesfully completed the game\n"+"Your Score is"+ " " + (1000-(totalMoves*10)));
                bw.close();
            }catch(Exception er){}

            }
            else{
                JOptionPane.showMessageDialog(null,"Puzzle is not Solved!");
            }
        }
        }
            
            // which button was clicked?
        for (int i = 0; i < rows*columns; i++) {
            if (e.getSource() == buttonTiles[i]) {   
                currentIndex = i;
            }
        }
        
        if (numClicks ==2) {                
            x.swapTiles(shuffle,currentIndex,oddClickIndex);
            Icon ic1=new ImageIcon(shuffle[currentIndex].imageLoc); 
            Icon ic2=new ImageIcon(shuffle[oddClickIndex].imageLoc); 
            buttonTiles[currentIndex].setIcon(ic1);
                  // buttonTiles[currentIndex].addActionListener(this);
            buttonTiles[oddClickIndex].setIcon(ic2);numClicks=0;
            totalMoves++;
            urscore.setText(""+totalMoves);
                   //buttonTiles[oddClickIndex].addActionListener(this);
        }
        else{  
            oddClickIndex=currentIndex;     
        }
    }
            


    class timeClass extends Thread{
        public void run(){
        try{
            while(timerSW.isRunning()){
                Thread.sleep(1000);
                    sec++;
                    
                
                if(sec>59){
                    sec=0;
                    mins++;
                }
                if(mins>59){
                    mins=0;
                    hrs++;
                }
                time.setText(hrs+":"+mins+":"+sec);
            }
            

            }catch(Exception eq){}
        }
    }
    class autosolveClass extends Thread{

        public void run(){
            try{
                int k=0;
                while(k<rows*columns){
                    for(int i=0; i<rows*columns; i++){
                       
                       if(shuffle[i].imageLoc.equals(tileArray[k].imageLoc))
                       {
                            
                            x.swapTiles(shuffle,k,i);
                            Icon ic1=new ImageIcon(shuffle[k].imageLoc); 
                            Icon ic2=new ImageIcon(shuffle[i].imageLoc); 
                            buttonTiles[k].setIcon(ic1);
                            buttonTiles[i].setIcon(ic2);
                            //myTimer.start();
                            k++;
                            Thread.sleep(1000);
                            break;
                        }

                    }
                }
                JOptionPane.showMessageDialog(null,"Solved");
                totalMoves=0;
                autosolved=1;
     
            }catch(Exception eq){}
        }
        
         
    }

    public void initializeTiles() throws IOException{
        getCroppedImages();

        int k=0;
        for(int i=0; i<rows; i++){
            for(int j=0; j<columns; j++){ 
                tileArray[k]=new Tile(10+imArray[k].getWidth()*j,80+imArray[k].getHeight()*i,imArray[k].getWidth(),imArray[k].getHeight(),k,imArray[k],"img" +k+ ".jpg");
                shuffle[k]=new Tile(10+imArray[k].getWidth()*j,80+imArray[k].getHeight()*i,imArray[k].getWidth(),imArray[k].getHeight(),k,imArray[k],"img" +k+ ".jpg");
                k++;
            }

        }

    }


    public void getShuffledBoard() throws IOException
    {
        for(int i=0; i<rows*columns; i++){
            int a= rd.nextInt(rows*columns);
            int b=rd.nextInt(rows*columns);
            while(a==b){
                b=rd.nextInt(rows*columns);
            }

                x.swapTiles(shuffle,a,b);
                Icon ic1=new ImageIcon(shuffle[a].imageLoc); 
                Icon ic2=new ImageIcon(shuffle[b].imageLoc); 
                buttonTiles[a].setIcon(ic1);
                // buttonTiles[currentIndex].addActionListener(this);
                buttonTiles[b].setIcon(ic2);numClicks=0;
                //buttonTiles[oddClickIndex].addActionListener(this);
                 
        }

    }
    public void getCroppedImages() throws IOException{

        image imageimage= new image();
        imageimage.fetchImage(category);
        imageimage.setCroppedImages();
        imArray=imageimage.sendCroppedImages();

    }


}
