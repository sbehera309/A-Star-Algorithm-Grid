package MapSearch;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.PriorityQueue;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.Scanner;


public class GridTest extends Application {
	static int userinputnumber;
	private double sceneWidth = 1280;
    private double sceneHeight = 760;

    private int n = 160;
    private int m = 120;

    double gridWidth = sceneWidth / n;
    double gridHeight = sceneHeight / m;
    
    Node[][] grid = new Node[160][120];
	Node start;
	Node end;
	

    MNode[][] playfield = new MNode[n][m];
	
	public GridTest(){
		
	}
	
	static PriorityQueue<Node> open = new PriorityQueue<Node>((n1, n2) -> {
        Node c1 = (Node)n1;
        Node c2 = (Node)n2;

        return c1.fCost<c2.fCost?-1:
                c1.fCost>c2.fCost?1:0;
    });
	
	static boolean closed[][] = new boolean[160][120];
    
    @Override
    public void start(Stage primaryStage) {


       try{ Group root = new Group();

		
		FileReader rd = new FileReader("Input.txt");
		BufferedReader buffread = new BufferedReader(rd);
		FileWriter wr = new FileWriter("Result.txt", false);
		BufferedWriter buff = new BufferedWriter(wr);
		int ecoa;
		int ecob;
		int scodaa;
		int scodabb;
		String line;
		StringTokenizer st;
		String cone;
		String ctwo;


		
		//Getting the start coordinates
		line = buffread.readLine();
		st = new StringTokenizer(line, ",");
		cone = st.nextToken();
		ctwo = st.nextToken();
		scodaa = Integer.parseInt(cone, 10);
		scodabb = Integer.parseInt(ctwo, 10);
		
		//Getting the end coordinates
		line = buffread.readLine();
		st = new StringTokenizer(line, ",");
		cone = st.nextToken();
		ctwo = st.nextToken();
		ecoa = Integer.parseInt(cone, 10);
		ecob = Integer.parseInt(ctwo, 10);

        // initialize playfield
		GridTest g = new GridTest();
		for(int i = 0; i < 120; i++){
			for(int c = 0; c < 160; c++){
				g.grid[c][i] = new Node();
				g.grid[c][i].y = i;
				g.grid[c][i].x = c;
				g.grid[c][i].hCost = Math.abs(i-ecob)+Math.abs(c-ecoa);
				g.grid[c][i].diagonalHcost = (int) (Math.abs(i-ecob)+Math.abs(c-ecoa)+Math.sqrt(2)*Math.min(Math.abs(i-ecob),Math.abs(c-ecoa)));
				g.grid[c][i].EuclideanHCost = (int) Math.sqrt(Math.abs(c-ecoa)*Math.abs(c-ecoa)+(Math.abs(i-ecob)+Math.abs(c-ecoa)));
				g.grid[c][i].EuclideanHCostSquared = Math.abs(c-ecoa)*Math.abs(c-ecoa)+(Math.abs(i-ecob)+Math.abs(c-ecoa));
				g.grid[c][i].YHCost = Math.abs(i-ecob);

			
			}
		}
		//Create the hard to traverse region
		for(int i = 1; i < 9; i++){
			line = buffread.readLine();
			st = new StringTokenizer(line, ",");
			String xcordinate = st.nextToken();
			String ycoordinate = st.nextToken();
			int xcoord = Integer.parseInt(xcordinate, 10);
			int ycoord = Integer.parseInt(ycoordinate, 10);
			g.SetHardTraverse(g.grid[xcoord][ycoord]);
		}
		
		for(int i = 0; i < 120; i++){
			for(int c = 0; c < 160; c++){
				Random r = new Random();
				int blockchance = r.nextInt(6);
				if(blockchance == 3){
				g.grid[c][i].type = 3;
				}
			}
		}
	
		
	
		for(int i = 1; i < 3; i++){
		      Random v = new Random();
		      int rchoic = v.nextInt(160);
		      if(i == 1){
		        int curr = 1;
		        g.grid[rchoic][0].type = 10;
		        while(curr <= 119 || rchoic <= 159){
		          Random twenty = new Random();
		          int ctwentyabc = twenty.nextInt(100);
		          if(ctwentyabc <= 20){
		            Random d = new Random();
		            int dirt1 = d.nextInt(2);
		            if(dirt1 == 1){
		              //If original path was from top to bottom
		              if(g.grid[rchoic][curr - 1].type == 10){
		                g.grid[rchoic][curr].type = 10;
		                if(rchoic == 159 || curr == 119){
		                  break;
		                }
		                rchoic += 1;
		              }
		              //If orginal path is from left to right
		              else if(g.grid[rchoic - 1][curr].type == 10){
		                g.grid[rchoic][curr].type = 10;
		                if(rchoic == 159 || curr == 119){
		                  break;
		                }
		                curr += 1;
		              }
		              //if original path is from right to left
		              else if(g.grid[rchoic + 1][curr].type == 10){
		                g.grid[rchoic][curr].type = 10;
		                if(rchoic == 159 || curr == 119){
		                  break;
		                }
		                curr -=1;
		              }
		            }else if(dirt1 == 2){
		              //If original path was from top to bottom
		              if(g.grid[rchoic][curr - 1].type == 10){
		                g.grid[rchoic][curr].type = 10;
		                if(rchoic == 159 || curr == 119){
		                  break;
		                }
		                rchoic -= 1;
		              }
		              //If original path is from left to right
		              else if(g.grid[rchoic - 1][curr].type == 10){
		                g.grid[rchoic][curr].type = 10;
		                if(rchoic == 159 || curr == 119){
		                  break;
		                }
		                curr -= 1;
		              }
		              //if original path is from right to left
		              else if(g.grid[rchoic + 1][curr].type == 10){
		                g.grid[rchoic][curr].type = 10;
		                if(rchoic == 159 || curr == 119){
		                  break;
		                }
		                curr +=1;
		              }
		            }
		          }
		          //Continues to move the same path
		          else{
		            if(g.grid[rchoic][curr - 1].type == 10){
		              g.grid[rchoic][curr].type = 10;

		             if(rchoic == 159 || curr == 119){
		                break;
		              }
		              curr += 1;
		            }
		            if(g.grid[rchoic - 1][curr].type == 10 ){
		              g.grid[rchoic][curr].type = 10;
		              if(rchoic == 159 || curr == 119){
		                break;
		              }
		              rchoic += 1;
		            }
		            if(rchoic + 1 <= 159){
		              if(g.grid[rchoic + 1][curr].type == 10){
		                g.grid[curr][rchoic].type = 10;
		                if(rchoic == 159 || curr == 119){
		                  break;
		                }
		                rchoic -= 1;
		              }
		            }else{
		              break;
		            }
		          }
		        }
		      }
		      else if(i == 2){
		        int curr = 118;
		        g.grid[rchoic][119].type = 10;
		        while(curr <= 0 || rchoic <= 159){
		          Random twenty = new Random();
		          int ctwentyabc = twenty.nextInt(100);
		          //Stays in the same row
		          if(ctwentyabc <= 20){
		            Random d = new Random();
		            int dirt1 = d.nextInt(2);
		            //Move Right
		            if(dirt1 == 1){
		              //If original path was from top to bottom
		              if(g.grid[rchoic][curr + 1].type == 10){
		            	  g.grid[rchoic][curr].type = 10;
		                  if(rchoic == 159 || curr == 0){
		                    break;
		                  }
		                  rchoic += 1;
		                }
		                //If original path is from left to right
		                else if(g.grid[rchoic - 1][curr].type == 10){
		                  g.grid[rchoic][curr].type = 10;
		                  if(rchoic == 159 || curr == 0){
		                    break;
		                  }
		                  curr -= 1;
		                }
		                //if original path is from right to left
		                else if(g.grid[rchoic + 1][curr].type == 10){
		                  g.grid[rchoic][curr].type = 10;
		                  if(rchoic == 159 || curr == 0){
		                    break;
		                  }
		                  curr -=1;
		                }
		              }else if(dirt1 == 2){
		                //If original path was from top to bottom
		                if(g.grid[rchoic][curr + 1].type == 10){
		                  g.grid[rchoic][curr].type = 10;
		                  if(rchoic == 159 || curr == 0){
		                    break;
		                  }
		                  rchoic -= 1;
		                }
		                //If original path is from left to right
		                else if(g.grid[rchoic - 1][curr].type == 10){
		                  g.grid[rchoic][curr].type = 10;
		                  if(rchoic == 159 || curr == 0){
		                    break;
		                  }
		                  curr -= 1;
		                }
		                //if original path is from right to left
		                else if(g.grid[rchoic + 1][curr].type == 10){
		                  g.grid[rchoic][curr].type = 10;
		                  if(rchoic == 159 || curr == 0){
		                    break;
		                  }
		                  curr +=1;
		                }
		              }
		            }
		            //Continues to move the same path
		            else{
		              if(g.grid[rchoic][curr + 1].type == 10){
		                g.grid[rchoic][curr].type = 10;
		                if(rchoic == 159 || curr == 0){
		                  break;
		                }
		                curr -= 1;
		              }
		              if(rchoic < 0 || curr < 0){
		            	  break;
		              }
		              if(g.grid[rchoic - 1][curr].type == 10 ){
		                g.grid[rchoic][curr].type = 10;
		                if(rchoic == 159 || curr == 0){
		                  break;
		                }
		                rchoic += 1;
		              }
		              if(rchoic + 1 <= 159){
		                if(g.grid[rchoic + 1][curr].type == 10){
		                  g.grid[rchoic][curr].type = 10;
		                  if(rchoic == 159 || curr == 0){
		                    break;
		                  }
		                  rchoic -= 1;
		                }
		              }else{
		                break;
		              }
		            }
		          }
		        }
		      }
		
		
	
		
		g.start = g.grid[scodaa][scodabb];
		g.end = g.grid[ecoa][ecob];
		g.grid[scodaa][scodabb].type = 1;	
		g.grid[ecoa][ecob].type = 2;
		long sysstart = System.currentTimeMillis();
		g.algorithms();
		long sysend = System.currentTimeMillis();
		long total;
		total = sysend-sysstart;
		FileWriter filea = new FileWriter("timings.txt", true);
		BufferedWriter youa = new BufferedWriter(filea);
		String temp = ""+total+"";
		youa.newLine();
		youa.write(temp);
		youa.close();
		filea.close();
		

		System.out.println("Time in milliseconds is " + total);
		
		if(closed[ecoa][ecob]){
            //Trace back the path 
             Node current = g.grid[ecoa][ecob];
             while(current.parent!=null){
                 current.type = 6;
                 current = current.parent;
             } 
             }
		g.grid[ecoa][ecob].type = 2;
		//Create the GUI Map
		System.out.println("Starting");
		for(int i = 0; i <120; i++){
			for(int c = 0; c < 160; c++){
				
					// create node
					MNode node = new MNode("", c * gridWidth, i * gridHeight, g.grid[c][i].type, g.grid[c][i].weight);

					// add node to group
					root.getChildren().add( node);
   

			}
		}

        Scene scene = new Scene( root, sceneWidth, sceneHeight);

        primaryStage.setScene( scene);
        primaryStage.show();
        buffread.close();
        buff.close();
       }catch(IOException e){
			e.printStackTrace();
		}

    }
    


    public void SetHardTraverse(Node curr){
		int xOgnA = curr.x - 15;
		int yOgnB = curr.y - 15;
		Random ran = new Random();
		int hardCount = 0;
		
		for(int i = xOgnA; i < (xOgnA + 31); i++){
			for(int c = yOgnB; c < (xOgnA + 31); c++){
				if((i >= 0 && i < 160) && (c >= 0 && c < 120)){
					if(ran.nextInt(2) > 0 && hardCount < 480){
						this.grid[i][c].weight = 2;
						this.grid[i][c].type = 4;
						hardCount++;
					}
				}
			}
		}
	}
		
    public static void main(String[] args) {
        MapGenerate map = new MapGenerate();
		map.genMap();
    	@SuppressWarnings("resource")
		Scanner reader = new Scanner(System.in);  // Reading from System.in
    	System.out.println(" Enter 1 for Uniform Cost Search\n 2 for A Star with Manhattan Hueristic\n 3 for Weighted A Star\n 4 for A Star with Diagonal Hueristic\n 5 for A Star with Eucledean Hueristic\n 6 for A Star with Eucledean Squared Hueristic\n 7 For A Star with Y Hueristic Squared");
    	userinputnumber = reader.nextInt();
        launch(args);
    }

    public static class MNode extends StackPane {

        public MNode( String name, double x, double y, int type, double weight) {

            // create rectangle
            Rectangle rectangle = new Rectangle( 8, (760/120));
            rectangle.setStroke(Color.BLACK);
            if(type == 0){
            	rectangle.setFill(Color.WHITE);
            }else if(type == 4){
            	rectangle.setFill(Color.DARKGRAY);
            }else if(type == 1){
            	rectangle.setFill(Color.CHARTREUSE);
            }else if(type == 2){
            	rectangle.setFill(Color.RED);
            }else if(type == 3){
            	rectangle.setFill(Color.BLACK);
            }else if(type == 6){
            	rectangle.setFill(Color.GOLD);
            }else if(type == 7){
            	rectangle.setFill(Color.DARKORANGE);
            }else if(type == 10){
            	rectangle.setFill(Color.BLUE);
            }

            // create label
            Label label = new Label( name);

            // set position
            setTranslateX( x);
            setTranslateY( y);

            getChildren().addAll( rectangle, label);

        }

    }
    public static void uCst(Node curr, Node temp, int cost){
		if(temp == null || closed[temp.x][temp.y]){
			return;
		}
		int totalCost = 0;
		if (userinputnumber == 1){
		totalCost =  0 + cost;
		}else if (userinputnumber == 2){
		totalCost = temp.hCost + cost;
		}else if (userinputnumber == 3){
			totalCost = (2*temp.hCost) + cost; 
		}else if (userinputnumber == 4){
			totalCost = temp.diagonalHcost + cost;
		}else if (userinputnumber == 5){
			totalCost = temp.EuclideanHCost + cost;
		}else if (userinputnumber == 6){
			totalCost = temp.EuclideanHCostSquared + cost;
		}
		else if (userinputnumber == 7){
			totalCost = temp.YHCost*temp.YHCost + cost;
		}
		boolean isExt = open.contains(temp);
		if(!isExt || (totalCost < temp.fCost)){
			temp.fCost = totalCost;
			temp.parent = curr;
			if(!isExt){
				open.add(temp);
			}
		}
		
	}
    
    public void algorithms(){
        
        open.add(this.start);
        Node temp;
        Node curr;
 
        
        
        while(true){
          curr = open.poll();
          if(curr == null){
            break;
          }
          closed[curr.x][curr.y] = true;
          
          if(curr.equals(this.end)){
            return;
          }
        
          if(curr.x - 1 >= 0){
            temp = this.grid[curr.x-1][curr.y];
            if(temp.type == 4){
              uCst(curr, temp, curr.fCost + 20);
            }
            else if(temp.type == 10){
              uCst(curr, temp, curr.fCost + 2);
            }
            else{
              uCst(curr, temp, curr.fCost + 10);
            }
            
            if(curr.y-1 >= 0){
              temp = this.grid[curr.x-1][curr.y-1];
              if(temp.type == 4){
                uCst(curr, temp, curr.fCost + 28);
              }
              else if(temp.type == 10){
                uCst(curr, temp, curr.fCost + 3);
              }
              else{
                uCst(curr, temp, curr.fCost + 14);
              }
            }
            if(curr.y+1 < this.grid[0].length){
              temp = this.grid[curr.x-1][curr.y+1];
              if(temp.type == 4){
                uCst(curr, temp, curr.fCost + 28);
              }
              else if(temp.type == 10){
                uCst(curr, temp, curr.fCost + 3);
              }
              else{
                uCst(curr, temp, curr.fCost + 14);
              }
            }
          }
          
          if(curr.y-1 >= 0){
            temp = this.grid[curr.x][curr.y-1];
            if(temp.type == 4){
              uCst(curr, temp, curr.fCost + 20);
            }
            else if(temp.type == 10){
              uCst(curr, temp, curr.fCost + 2);
            }
            else{
              uCst(curr, temp, curr.fCost + 10);
            }
          }
          if(curr.y+1 < this.grid[0].length){
            temp = this.grid[curr.x][curr.y+1];
            if(temp.type == 4){
              uCst(curr, temp, curr.fCost + 20);
            }
            else if(temp.type == 10){
              uCst(curr, temp, curr.fCost + 2);
            }
            else{
              uCst(curr, temp, curr.fCost + 10);
            }
          }
          if(curr.x+1 < this.grid.length){
            temp = this.grid[curr.x+1][curr.y];
            if(temp.type == 4){
              uCst(curr, temp, curr.fCost + 20);
            }
            else if(temp.type == 10){
              uCst(curr, temp, curr.fCost + 2);
            }
            else{
              uCst(curr, temp, curr.fCost + 10);
            }
            
            if(curr.y-1 >= 0){
              temp = this.grid[curr.x+1][curr.y-1];
              if(temp.type == 4){
                uCst(curr, temp, curr.fCost + 28);
              }
              else if(temp.type == 10){
                uCst(curr, temp, curr.fCost + 3);
              }
              else{
                uCst(curr, temp, curr.fCost + 14);
              }
            }
            if(curr.y+1 < this.grid[0].length){
              temp = this.grid[curr.x+1][curr.y+1];
              if(temp.type == 4){
                uCst(curr, temp, curr.fCost + 28);
              }
              else if(temp.type == 10){
                uCst(curr, temp, curr.fCost + 3);
              }
              else{
                uCst(curr, temp, curr.fCost + 14);
              }
            }
          }
        }
      }
      
    }

