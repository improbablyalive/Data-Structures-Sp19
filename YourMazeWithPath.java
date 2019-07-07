import java.util.*;
import java.awt.Point;
import java.util.LinkedList;

public class YourMazeWithPath{       
    private InputGraphicMaze maze;
    private int R, C; 
    private int[][] V;                                                          // stores vector names for path, 
                                                                                // not the real maze
    Boolean done = false;
    //YOU'LL NEED TO ADD MORE CODE HERE!!!!!!! 

public YourMazeWithPath(){       
   // an R rows x C columns maze
   maze = new InputGraphicMaze();
   R=maze.Rows(); C=maze.Cols();  
   V=new int[R+1][C+1];
   //YOU'LL NEED TO ADD MORE CODE HERE!!!!!!!
   for (int i=1; i<=R; i++)
       for (int j=1; j<=C; j++) V[i][j]=0;                                      // assign values of V[][], they will not
                                                                                // be used but are needed to instantiate
                                                                                // the matrix

   // Path holds the cells of the path
   LinkedList<Point> Path = new LinkedList<Point>();
   // Create the path
   CreatePath(maze, 1, 1, R, C, Path);
   // show the path in the maze
   maze.showPath(Path);
}

// Creates the path through maze, starting at cell (srow, scol)
// and ending at cell (erow and ecol), the true path in L
public void CreatePath(InputGraphicMaze maze, int srow, int scol, 
        int erow, int ecol, LinkedList<Point> L){

    int r=srow, c=scol, R=maze.Rows(), C=maze.Cols();
    DFT(maze, r, c, R, C, L);
    
}
public void DFT(InputGraphicMaze maze, int srow, int scol, 
        int erow, int ecol, LinkedList<Point> L){
    
    int r=srow, c=scol, R=maze.Rows(), C=maze.Cols(), tcell = V[r][c];
    Point u = new Point(r,c);
    
    if(tcell!=1){                                                               // a visited cell stores the value of 1
        r=(int) u.getX();
        c=(int) u.getY();
        if((r==erow) && (c==ecol)) done = true;                                 // base case is the last cell, cell=last
                                                                                // --> pathing is complete
        else{  
            if ( (!done) && ((r>1)&&(V[r-1][c]!=1)) && (maze.can_go(r,c,'U')) ){// check path up
                V[r][c] = 1;
                DFT(maze, r-1, c, R, C, L);
            }
            if ( (!done) && ((c<C)&&(V[r][c+1]!=1)) && (maze.can_go(r,c,'R')) ){// check path right
                V[r][c] = 1;
                DFT(maze, r, c+1, R, C, L);
            }
            if ( (!done) && ((r<R)&&(V[r+1][c]!=1)) && (maze.can_go(r,c,'D')) ){// check path down
                V[r][c]=1;
                DFT(maze, r+1, c, R, C, L);
            }
            if ( (!done) && ((1<c)&&(V[r][c-1]!=1)) && (maze.can_go(r,c,'L')) ){// check path left
                V[r][c]=1; 
                DFT(maze, r, c-1, R, C, L);
            }
       }

       if(done) L.addFirst( u );
    }
}
public static void main(String[] args){                                         // actual program call lol
        new YourMazeWithPath();
}

}