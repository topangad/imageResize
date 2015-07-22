import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.awt.Color;

public class SeamCarver {

   private Picture picture;
   public SeamCarver(Picture picture){  
      this.picture=picture;
   }
   public Picture picture(){
      return this.picture;
   }
   public int width(){
   // width of current picture
      return this.picture.width();
   }                            
   public int  height(){
      // height of current picture
      return this.picture.height();
   }
   public  double energy(int x, int y){
      Color top, bottom, left, right;
      double dx, dy, energy;
      double rdx, bdx, gdx, rdy, bdy, gdy;      
      
      //handles boundary pixels
       top = this.picture.get(x,( y == 0 ) ? height()-1 : y - 1);
       bottom = this.picture.get(x, ( y == height()-1 ) ? 0 : y + 1);
       left = this.picture.get( ( x == 0 ) ? width()-1 : x - 1, y);
       right = this.picture.get( ( x == width()-1) ? 0 : x + 1, y);     
      
      rdx = Math.abs(right.getRed() - left.getRed());
      bdx = Math.abs(right.getBlue() - left.getBlue());
      gdx = Math.abs(right.getGreen() - left.getGreen());

      rdy = Math.abs(top.getRed() - bottom.getRed());
      bdy = Math.abs(top.getBlue() - bottom.getBlue());
      gdy = Math.abs(top.getGreen() - bottom.getGreen()); 
      
      dx = rdx*rdx + bdx*bdx + gdx*gdx;
      dy = rdy*rdy + bdy*bdy + gdy*gdy;      
      
      return dx + dy;
  }
  
  public   int[] findHorizontalSeam(){
   // sequence of indices for horizontal seam
      int h = this.picture.height();
      int w = this.picture.width();
      double[][] minEnergyMap = new double[h][w]; 
      int[] hSeam = new int[w];      
      int minEnergyPix = 0;
      int colCounter = 0;
      //dp base case x = cols, y = rows
      for (int i = 0; i < h; i ++) {
         minEnergyMap[i][0] = energy(colCounter, i);
      }
      for ( int col = 1; col < w; col++) {
         for (int row = 1; row < h-1; row ++) {
            double currPixEnergy = energy(col, row);          
            double left = minEnergyMap[row][col-1];
            double leftTop = minEnergyMap[row-1][col-1];
            double leftBottom = minEnergyMap[row+1][col-1];
            
            double smallest = left;
            if (smallest > leftTop) smallest = leftTop;
            if (smallest > leftBottom) smallest = leftBottom;
            minEnergyMap[row][col]= currPixEnergy + smallest;
         }
      }
      
      hSeam = getMinSeam(minEnergyMap);
      
      return hSeam;
   }
   
   public int[] getMinSeam(double [][] grid) {
      int rows=grid.length;      
      int cols=grid[0].length;
      int[] hseam = new int[cols];
      double min = grid [0][cols-1];
      int minIndex = 0;
      
      for (int i = 1 ; i < rows; i++){
         if(grid[i][cols-1] < min) {
            min = grid[i][cols-1];
            minIndex = i;
         }
      }
      
      for ( int i = cols-1 ; i >= 1 ; i--) {
      //swap x and y for rows and cols, minIndex represents they coordinate
         double curEnergy = energy(i, minIndex);
         
         double target = grid[minIndex][i] - curEnergy;
         
         hseam[i] = minIndex;
         //have to do edge cases check
         if(minIndex == 0) {            
            if(grid[minIndex+1][i-1] == target) {
               minIndex = minIndex +1;
            }            
         }
         else if (minIndex == rows-1) {
            if(grid[minIndex-1][i-1] == target) {
               minIndex = minIndex-1;
            }
         }
         else {
            if(grid[minIndex+1][i-1] == target) {
               minIndex = minIndex+1;
            }  else if(grid[minIndex-1][i-1] == target) {
               minIndex = minIndex-1;
            }

         }
         
                  
      }
      hseam[0] = minIndex;

      return hseam;
   }
   public   int[] findVerticalSeam(){
   // sequence of indices for vertical seam
      int [] vseam = new int [2];
      vseam[0]=1;
      vseam[1]=2;
      return null;
   }
   public void removeHorizontalSeam(int[] seam){   // remove horizontal seam from current picture
   
   }
   
   public void removeVerticalSeam(int[] seam){     // remove vertical seam from current picture
   }
}
