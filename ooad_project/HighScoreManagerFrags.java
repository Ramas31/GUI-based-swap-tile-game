package ooad_project;
public class HighScoreManagerFrags extends JFrame {
    
    private ArrayList<Score> scores;
    private DefaultListModel<String> lm=new DefaultListModel<String>();
    private JList jl;
    JDialog f;
    JLabel label,l2,l3,l4,l5;

    public HighScoreManagerFrags() {
        scores = new ArrayList<Score>();
    }
    
    
    public ArrayList<Score> getScores() {
        sort();
        return scores;
    }
    
    
    private void sort() {
        ScoreComparator comparator = new ScoreComparator();
        Collections.sort(scores, comparator);
   }
    
    public void addScore(String name, int score) {
       
        scores.add(new Score(name,score));
        } 

     
    public void getHighscoreString() {
        String highscoreString = "";
        int max = 5;

        ArrayList<Score> scores;
        scores = getScores();

        int i = 0;
        int x = scores.size();
        if (x > max) {
            x = max;
        }
        try{
        File f = new File("highscore.txt");
        
         if(!f.exists()) {
        f.createNewFile();
        } 
        FileWriter fw = new FileWriter("highscore.txt");
        BufferedWriter bw=new BufferedWriter(fw);
        while (i < x) {
            highscoreString = (i + 1) + ".  " + scores.get(i).getName() + "        " + scores.get(i).getScore() + "\n";
            i++;
            bw.write(highscoreString);
           // lm.addElement(highscoreString);
            }
            bw.close();
        }
        catch(IOException e){System.out.println("Error:"+e);}
        
        
        f=new JDialog();
        
        try{
        FileReader fr=new FileReader("highscore.txt");
        BufferedReader br=new BufferedReader(fr);
        String S;
        S=br.readLine();
       label=new JLabel(S);
        label.setBounds(50,40,200,100);
        S=br.readLine();
        l2=new JLabel(S);
        l2.setBounds(50,40,200,200);
        S=br.readLine();
        l3=new JLabel(S);
        l3.setBounds(50,40,200,300);
        S=br.readLine();
        l4=new JLabel(S);
        l4.setBounds(50,40,200,400);
        S=br.readLine();
        l5=new JLabel(S);
        l5.setBounds(50,40,200,500);
            br.close();
        }
      
        
        catch(Exception e){}
    
        f.setTitle("High Score");
        f.setSize(400,400);
        f.setLocationRelativeTo(null);
        f.setLayout(null);        
        f.setVisible(true);
        f.add(label);
        f.add(l2);
        f.add(l3);
        f.add(l4);
        f.add(l5);
       
   }
    
    public void getScore() {
        HighScoreManagerFrags hm = new HighScoreManagerFrags();
        
            String fileName = "scores.txt";
            String line = null;
          
            try {
                FileReader fileReader = 
                    new FileReader(fileName);

                BufferedReader bufferedReader = 
                    new BufferedReader(fileReader);
                     int i=0;
               
                    
                            
                        while( (line = bufferedReader.readLine()) != null) {
                            {   
                                String[] s=line.split(" ");
                                i=Integer.parseInt(s[1]);
                                hm.addScore(s[0],i);}
                           
                }   
                bufferedReader.close();      
            }
            
            catch(FileNotFoundException ex) {
                System.out.println(
                    "Unable to open file '" + 
                    fileName + "'");                
            }
            catch(IOException ex) {
                System.out.println(
                    "Error reading file '" 
                    + fileName + "'");                  
            }
             
           hm.getHighscoreString();
        }
}

