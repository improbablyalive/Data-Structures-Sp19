// Breath-first  traversal for the path.  
import java.util.*;
import java.awt.Point;
import java.util.LinkedList;

public class MyMazeWithPath
{       
    private InputGraphicMaze maze;                                              // actual maze item
    private int R, C; 
    private int[][] V;                                                          // stores vector names for path, 
                                                                                // not the real maze

    public MyMazeWithPath() 
    {       
        // an R rows x C columns maze
        maze = new InputGraphicMaze();
        R=maze.Rows(); C=maze.Cols(); V=new int[R+1][C+1];
        for (int i=1; i<=R; i++)
           for (int j=1; j<=C; j++) V[i][j]=0;                                  // assign values of V[][], they will not
                                                                                // be used but are needed to instantiate
                                                                                // the matrix
            
        // Path holds the cells of the path
        LinkedList<Point> Path = new LinkedList<Point>();                       // the point data type holds an x and y
                                                                                // coordinate that we can sub for 
                                                                                // x=Row and y=columns   
        //Create the path
        CreatePath(maze, 1, 1, R, C, Path);
        
        // show the path in the maze
        maze.showPath(Path);
    }

    public boolean CreatePath(InputGraphicMaze maze, int srow, int scol,        // srow = Starting Row, erow = Ending Row
                                int erow, int ecol, LinkedList<Point> L){
        int r=srow, c=scol, R=maze.Rows(), C=maze.Cols();
        int size=R*C+1;                                                         // P is the Point array
        Point[] P=new Point[size];                                              // P will act as the collection for the
        boolean done=false;                                                     // path that is generated
                                                                                // P will store visited vertices
        
        V[srow][scol]=1;                                                        
        int scell=(srow-1)*C+scol; 
        P[scell]=new Point(0,0); 
        Point u = new Point(r, c); 
        LinkedList<Point> Q = new LinkedList<Point>();                          // Q is the queue of paths to be explored
                                                                                // vertices are added if viable and removed
                                                                                // if they do not yield to the correct path
                                                                                // Q is a temp path
        Q.add(u);
        
        while ((Q.size()!=0)&&(!done)){             
           u=Q.remove();                                                        // pull the first item from Q
           r=(int) u.getX(); c=(int) u.getY();
           if ((r==erow)&&(c==ecol)) done=true;                                 // base case is the last cell, cell=last
                                                                                // --> pathing is complete
           else{  
              if ((r>1)&&(V[r-1][c]!=1)&&(maze.can_go(r, c,'U'))){              // check path up
                  V[r-1][c]=1; P[(r-2)*C+c]=u;                                  // if path avail, go that way and add it
                  Q.add(new Point(r-1, c));                                     // to Q
              }
              if ((c<C)&&(V[r][c+1]!=1)&&(maze.can_go(r, c,'R'))){              // check path right
                    V[r][c+1]=1; P[(r-1)*C+c+1]=u; 
                    Q.add(new Point(r, c+1));
                 }
              if ((r<R)&&(V[r+1][c]!=1)&&(maze.can_go(r, c, 'D'))){             // check path down
                  V[r+1][c]=1; P[r*C+c]=u; 
                  Q.add(new Point(r+1, c));
              }              
              if ((c>1)&&(V[r][c-1]!=1)&&(maze.can_go(r, c, 'L'))){             // check path left
                  V[r][c-1]=1; P[(r-1)*C+c-1]=u; 
                  Q.add(new Point(r, c-1));
              }
           }           
        } //end of while
        
        while (!u.equals(P[scell])) {                                           // while vertex is not the sPoint
           r=(int) u.getX(); c=(int) u.getY();                                  // add it to the end path
           L.addFirst(u); 
           u=P[(r-1)*C+c]; 
        }
        return done;
    }
 
    public static void main(String[] args){ new MyMazeWithPath();} // <== actual program call lol
}