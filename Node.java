package MapSearch;

public class Node {
	int weight;
	int x;
	int y;
	int type; // 0 - normal, 1 - start, 2 - goal, 3 - block
	Node parent;
	int hCost = 0;
	int fCost = 0;
	int diagonalHcost = 0;
	int EuclideanHCost = 0;
	int EuclideanHCostSquared = 0;
	int YHCost = 0;


	Node uParent;
	
	public Node(){
		this.weight = 1;
		this.type = 0;
	}
}
