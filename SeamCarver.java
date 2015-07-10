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
      
      if ( x == 0 || y == 0 ||
            x == picture.width()-1 ||
            y == picture.height()-1){      
         top = this.picture.get(x, y);
         bottom = top;
         left = top;
         right = top;         
      } else { 
         top = this.picture.get(x, y-1);
         bottom = this.picture.get(x, y+1);
         right = this.picture.get(x+1, y);
         left = this.picture.get(x-1, y);
      }
      
      rdx = right.getRed() - left.getRed();
      bdx = right.getBlue() - left.getBlue();
      gdx = right.getGreen() - left.getGreen();

      rdy = top.getRed() - bottom.getRed();
      bdy = top.getBlue() - bottom.getBlue();
      gdy = top.getGreen() - bottom.getGreen(); 
      
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
      //dp base case
      for (int i = 0; i < h; i ++) {
         minEnergyMap[i][colCounter] = energy(i,colCounter);
      }
      for ( int col = 1; col < w; col++) {
         for (int row = 1; row < h-1; row ++) {
            double currPixEnergy = energy(row, col);          
            double left = minEnergyMap[row][col-1];
            double leftTop = minEnergyMap[row-1][col-1];
            double leftBottom = minEnergyMap[row+1][col-1];
            
            double smallest = left;
            if (smallest > leftTop) smallest = leftTop;
            if (smallest > leftBottom) smallest = leftBottom;
            minEnergyMap[row][col]= currPixEnergy + smallest;
         }
      }
      return hSeam;
   }
   public   int[] findVerticalSeam(){
   // sequence of indices for vertical seam
      return null;
   }
   public void removeHorizontalSeam(int[] seam){   // remove horizontal seam from current picture
   
   }
   
   public void removeVerticalSeam(int[] seam){     // remove vertical seam from current picture
   }
}
