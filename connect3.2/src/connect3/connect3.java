package connect3;

import java.io.IOException;
import java.util.Scanner;

public class connect3 {
	

	static int clickedCol = 10;
    static boolean buttonClicked = false;

	public static void main(String[] args) {
		
		
		connectBoard mainConnect3 = new connectBoard();
		Scanner scanner = new Scanner(System.in);
    	
    	
    	System.out.println("How many players will be playing; 1, 2, or 3");
        int vsCpuInt = 10;
        
        
        
        
        boolean colUpdated = false;
		
		while(!colUpdated) {
			
			buttonClicked = false;
			vsCpuInt = 10;
			
			try {
				
				if (System.in.available() > 0) {
					
					vsCpuInt = scanner.nextInt();
					
                }
				
            } catch (IOException e) {
                e.printStackTrace();
            }
			
			if((vsCpuInt >= 1) && (vsCpuInt <= 3) ) {
				
				colUpdated = true;
				break;
				
			}
			
			else if(buttonClicked){
				
				vsCpuInt = clickedCol;
				
				if((vsCpuInt >= 1) && (vsCpuInt <= 3) ) {
					
					colUpdated = buttonClicked;
					
				}
				
			}
			
		}
        
        
        
        
        
        
        
        
        boolean vsCpuBool = false;
        
        if(vsCpuInt == 1) {
        	vsCpuBool = true;
        }
        
        if(vsCpuInt == 2) {
        	vsCpuBool = true;
        }
        
        if(vsCpuInt == 3) {
        	vsCpuBool = false;
        }
        
        if(!vsCpuBool) {mainConnect3.play();}
        
        if(vsCpuInt == 2) {
        	
        	System.out.println("You will be playing with a AI");
        	
        	mainConnect3.play(vsCpuInt);
        	
        }
        
        if(vsCpuInt == 1) {
	
        	System.out.println("You will be playing with two AIs");
	
        	mainConnect3.play(vsCpuInt);
	
        }

        scanner.close();
        

	}

}
