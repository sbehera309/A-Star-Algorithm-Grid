package MapSearch;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class MapGenerate {
	String[] arrval = new String[10];
	
	public MapGenerate(){
	}
	
	public void genMap(){
		try{
			FileWriter writ = new FileWriter("Input.txt", false);
			BufferedWriter buffwrit = new BufferedWriter(writ);
			
			Random startx = new Random();
			Random starty = new Random();
			Random endx = new Random();
			Random endy = new Random();
			int valuesx = startx.nextInt(160);
			int valuesy = starty.nextInt(20);
			int valueex = endx.nextInt(160);
			int valueey = endy.nextInt(120 - 100) + 100;
			
			String start = valuesx + "," + valuesy;
			String end = valueex + "," + valueey;
			
			arrval[0] = start;
			arrval[1] = end;
			
			buffwrit.write(arrval[0]);
			buffwrit.newLine();
			buffwrit.write(arrval[1]);
			buffwrit.newLine();
			
			for(int i = 2; i < 10; i++){
				Random x = new Random();
				Random y = new Random();
				int val1;
				int val2;
				val1 = x.nextInt(160);
				val2 = y.nextInt(120);
				String temp = val1 + "," + val2;
				arrval[i] = temp;
				buffwrit.write(arrval[i]);
				buffwrit.newLine();
			}
			buffwrit.close();
		}catch (IOException e){
			e.printStackTrace();
		}	
	}

}
