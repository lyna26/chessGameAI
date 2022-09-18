package Gamer;

import java.util.ArrayList;

import piece.piece;
import utilities.color;

public class gamer {
	
	private color side;

	private String name;
	
	private ArrayList<piece> pieces= new ArrayList<piece>();
	
	private String type ;
	
	public color getSide() {
		return side;
	}

	public void setSide(color side) {
		this.side = side;
	}

	public String getName() {
		return name;
	}

	
	public String getType() {
		return type;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<piece> getPieces() {
		return pieces;
	}

	public void setPieces(ArrayList<piece> pieces) {
		this.pieces = pieces;
	}

	
	public gamer(color side, String name, String type) {
		this.side = side;
		this.name = name;
		this.type = type;
	}
}
