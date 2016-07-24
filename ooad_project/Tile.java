package ooad_project;
public class Tile{

    int x;
    int y;
    int tileHeight;
    int tileWidth;
    int tileNumber;
    BufferedImage im;
    String imageLoc;


    Tile(){
    }
    Tile(int x, int y,int tileWidth,int tileHeight,int tileNumber,BufferedImage im,String imageLoc){
        this.x=x;
        this.y=y;
        this.tileWidth=tileWidth;
        this.tileHeight=tileHeight;
        this.tileNumber=tileNumber;
        this.im=im;
        this.imageLoc=imageLoc;
    }

    public void swapTiles(Tile[] arr,int x, int y){
      Tile ob=arr[x];
      Tile ob1=arr[y];
      Tile temp= new Tile(0,0,0,0,0,ob.im,"");
      

      temp.x=ob.x;
      temp.y=ob.y;
      temp.tileWidth=ob.tileWidth;
      temp.tileHeight=ob.tileHeight;
      temp.im=ob.im;
      temp.imageLoc=ob.imageLoc;
      
      ob.x=ob1.x;
      ob.y=ob1.y;
      ob.tileWidth=ob1.tileWidth;
      ob.tileHeight=ob1.tileHeight;
      ob.im=ob1.im;
      ob.imageLoc=ob1.imageLoc;

      ob1.x=temp.x;
      ob1.y=temp.y;
      ob1.tileWidth=temp.tileWidth;
      ob1.tileHeight=temp.tileHeight;
      ob1.im=temp.im;
      ob1.imageLoc=temp.imageLoc;
      
      arr[x]=ob;
      arr[y]=ob1;
    }

}

