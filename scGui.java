import java.awt.*;
import java.awt.event.*;


public class scGui extends Frame implements ActionListener {
   
   private Label lblCount;    // Declare component Label
   private TextField tfCount; // Declare component TextField
   private Button btnCount;   // Declare component Button
   private int count = 0;     // Counter's value
   
   //gui cxr
   public scGui() {
   
      //left to right, top to bottom layout flow
      setLayout(new FlowLayout());    
 
      lblCount = new Label("Counter");
      add(lblCount); 
 
      tfCount = new TextField("0", 10);
      tfCount.setEditable(false);
      add(tfCount);
 
      btnCount = new Button("Count");
      add(btnCount);
 
      btnCount.addActionListener(this);      
 
      this.setTitle("Seam Carver Gui");  
      this.setSize(250, 100);     
 
      setVisible(true);         // "super" Frame shows
            
   }
   
   public static void main(String[] args) {
      scGui container = new scGui();      
   }
   
   @Override
   public void actionPerformed(ActionEvent evt) {
      ++count;      
      tfCount.setText(count + ""); // convert int to String
   }

}