import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class SeamCarver {

   private Picture picture;
   public SeamCarver(Picture picture){
   // create a seam carver object based on the given pictures
      this.picture=picture;
   }
   public Picture picture(){  // current picture
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
                  // energy of pixel at column x and row y
      return 0;
  }
   public   int[] findHorizontalSeam(){
   // sequence of indices for horizontal seam
      return null;
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
