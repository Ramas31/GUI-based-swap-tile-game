package ooad_project;
public class Game{
    int rows;
    int columns;
    int category;
    Game(int r, int c , int cate){
        rows=r;
        columns=c;
        category=cate;
    }
    public void drawMainBoard() throws IOException{

        Board b= new Board(rows,columns,category);

    }

}
