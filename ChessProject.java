import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.lang.model.util.ElementScanner6;
import javax.swing.*;

/*
	This class can be used as a starting point for creating your Chess game project. The only piece that
	has been coded is a white pawn...a lot done, more to do!
*/





public class ChessProject extends JFrame implements MouseListener, MouseMotionListener {
    JLayeredPane layeredPane;
    JPanel chessBoard;
    JLabel chessPiece;
    int xAdjustment;
    int yAdjustment;
    int landingX;
    int landingY;
    int xMovement;
    int yMovement;
	int startX;
	int startY;
	int initialX;
	int initialY;
	JPanel panels;
	JLabel pieces;


    public ChessProject(){
        Dimension boardSize = new Dimension(600, 600);

        //  Use a Layered Pane for this application
        layeredPane = new JLayeredPane();
        getContentPane().add(layeredPane);
        layeredPane.setPreferredSize(boardSize);
        layeredPane.addMouseListener(this);
        layeredPane.addMouseMotionListener(this);

        //Add a chess board to the Layered Pane
        chessBoard = new JPanel();
        layeredPane.add(chessBoard, JLayeredPane.DEFAULT_LAYER);
        chessBoard.setLayout( new GridLayout(8, 8) );
        chessBoard.setPreferredSize( boardSize );
        chessBoard.setBounds(0, 0, boardSize.width, boardSize.height);

        for (int i = 0; i < 64; i++) {
            JPanel square = new JPanel( new BorderLayout() );
            chessBoard.add( square );

            int row = (i / 8) % 2;
            if (row == 0)
                square.setBackground( i % 2 == 0 ? Color.white : Color.gray );
            else
                square.setBackground( i % 2 == 0 ? Color.gray : Color.white );
        }

        // Setting up the Initial Chess board.
		for(int i=8;i < 16; i++){
       		pieces = new JLabel( new ImageIcon("WhitePawn.png") );
			panels = (JPanel)chessBoard.getComponent(i);
	        panels.add(pieces);
		}
		pieces = new JLabel( new ImageIcon("WhiteRook.png") );
		panels = (JPanel)chessBoard.getComponent(0);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("WhiteKnight.png") );
		panels = (JPanel)chessBoard.getComponent(1);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("WhiteKnight.png") );
		panels = (JPanel)chessBoard.getComponent(6);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("WhiteBishop.png") );
		panels = (JPanel)chessBoard.getComponent(2);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("WhiteBishop.png") );
		panels = (JPanel)chessBoard.getComponent(5);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("WhiteKing.png") );
		panels = (JPanel)chessBoard.getComponent(3);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("WhiteQueen.png") );
		panels = (JPanel)chessBoard.getComponent(4);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("WhiteRook.png") );
		panels = (JPanel)chessBoard.getComponent(7);
	    panels.add(pieces);
		for(int i=48;i < 56; i++){
       		pieces = new JLabel( new ImageIcon("BlackPawn.png") );
			panels = (JPanel)chessBoard.getComponent(i);
	        panels.add(pieces);
		}
		pieces = new JLabel( new ImageIcon("BlackRook.png") );
		panels = (JPanel)chessBoard.getComponent(56);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("BlackKnight.png") );
		panels = (JPanel)chessBoard.getComponent(57);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("BlackKnight.png") );
		panels = (JPanel)chessBoard.getComponent(62);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("BlackBishop.png") );
		panels = (JPanel)chessBoard.getComponent(58);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("BlackBishop.png") );
		panels = (JPanel)chessBoard.getComponent(61);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("BlackKing.png") );
		panels = (JPanel)chessBoard.getComponent(59);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("BlackQueen.png") );
		panels = (JPanel)chessBoard.getComponent(60);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("BlackRook.png") );
		panels = (JPanel)chessBoard.getComponent(63);
	    panels.add(pieces);
    }

	/*
		This method checks if there is a piece present on a particular square.
	*/
	private Boolean piecePresent(int x, int y){
		Component c = chessBoard.findComponentAt(x, y);
		if(c instanceof JPanel){
			return false;
		}
		else{
			return true;
		}
	}

	/*
		This is a method to check if a piece is a Black piece.
	*/
	private Boolean checkWhiteOponent(int newX, int newY){//The whole point of this is to check for the players opponent on the board at the e.getX and getY (where you are putting your piece)
		// in this case it checks for a white oponent which is black. 
		Boolean oponent;
		Component c1 = chessBoard.findComponentAt(newX, newY);
		JLabel awaitingPiece = (JLabel)c1;
		String tmp1 = awaitingPiece.getIcon().toString();
		if(((tmp1.contains("Black")))){
			if((tmp1.contains("BlackKing"))){
				JOptionPane.showMessageDialog(null, "White Wins!");
				System.exit(0);
			}
			oponent = true;
		}
		else{
			oponent = false;
		}
		return oponent;
	}
	private Boolean checkBlackOponent(int newX, int newY){
		Boolean oponent;
		Component c1 = chessBoard.findComponentAt(newX, newY);
		JLabel awaitingPiece = (JLabel)c1;
		String tmp1 = awaitingPiece.getIcon().toString();
		if(((tmp1.contains("White")))){//This looks for a white King at the landing, at this point the piece is already taken, meaning that the message "Black wins will show."
			if((tmp1.contains("WhiteKing"))){
				JOptionPane.showMessageDialog(null, "Black Wins!");
				System.exit(0);
			}
			oponent = true;
		}
		else{
			oponent = false;
		}
		return oponent;
	}
	private Boolean checkBlackKing(int newX, int newY){
		Boolean oponent;
		Component c1 = chessBoard.findComponentAt(newX, newY);
		JLabel awaitingPiece = (JLabel)c1;
		String tmp1 = awaitingPiece.getIcon().toString();
		if(((tmp1.contains("BlackKing")))){
			oponent = true;
		}
		else{
			oponent = false;
		}
		return oponent;
	}
	private Boolean checkWhiteKing(int newX, int newY){
		Boolean oponent;
		Component c1 = chessBoard.findComponentAt(newX, newY);
		JLabel awaitingPiece = (JLabel)c1;
		String tmp1 = awaitingPiece.getIcon().toString();
		if(((tmp1.contains("WhiteKing")))){
			oponent = true;
		}
		else{
			oponent = false;
		}
		return oponent;
	}
	/*
		This method is called when we press the Mouse. So we need to find out what piece we have
		selected. We may also not have selected a piece!
	*/
    public void mousePressed(MouseEvent e){
        chessPiece = null;
        Component c =  chessBoard.findComponentAt(e.getX(), e.getY());
        if (c instanceof JPanel)
			return;

        Point parentLocation = c.getParent().getLocation();
        xAdjustment = parentLocation.x - e.getX();
        yAdjustment = parentLocation.y - e.getY();
        chessPiece = (JLabel)c;
		initialX = e.getX();
		initialY = e.getY();
		startX = (e.getX()/75);
		startY = (e.getY()/75);
        chessPiece.setLocation(e.getX() + xAdjustment, e.getY() + yAdjustment);
        chessPiece.setSize(chessPiece.getWidth(), chessPiece.getHeight());
        layeredPane.add(chessPiece, JLayeredPane.DRAG_LAYER);
    }

    public void mouseDragged(MouseEvent me) {
        if (chessPiece == null) return;
         chessPiece.setLocation(me.getX() + xAdjustment, me.getY() + yAdjustment);
     }

 	/*
		This method is used when the Mouse is released...we need to make sure the move was valid before
		putting the piece back on the board.
	*/
    public void mouseReleased(MouseEvent e) {
        if(chessPiece == null) return;

        chessPiece.setVisible(false);
		Boolean whiteSuccess =false;
		Boolean blackSuccess = false;
    Component c =  chessBoard.findComponentAt(e.getX(), e.getY());
		String tmp = chessPiece.getIcon().toString();
		String pieceName = tmp.substring(0, (tmp.length()-4));
		Boolean validMove = false;

    int landingX = (e.getX()/75);
    int landingY = (e.getY()/75);
    int xMovement = Math.abs((e.getX()/75)-startX);
    int yMovement = Math.abs((e.getY()/75)-startY);
    System.out.println("__");
      System.out.println("The piece that is being moved is : "+pieceName);
      System.out.println("The starting coordinates are: "+"("+startX+","+startY+")");
      System.out.println("The xMovement is : "+xMovement);
      System.out.println("The yMovement is : "+yMovement);
      System.out.println("The landing coordinates are : "+"("+landingX+","+landingY+")");
      System.out.println("__");

		/*
			The only piece that has been enabled to move is a White Pawn...but we should really have this is a separate
			method somewhere...how would this work.

			So a Pawn is able to move two squares forward one its first go but only one square after that.
			The Pawn is the only piece that cannot move backwards in chess...so be careful when committing
			a pawn forward. A Pawn is able to take any of the opponent’s pieces but they have to be one
			square forward and one square over, i.e. in a diagonal direction from the Pawns original position.
			If a Pawn makes it to the top of the other side, the Pawn can turn into any other piece, for
			demonstration purposes the Pawn here turns into a Queen.
		*/
		try {
			
		
	if(pieceName.contains("Queen")){//Queen is made from the bishop and the Rook, it is given a unlimited amount of moves, but is limited by the spaces of the entire board or is something is in the way.//
		Boolean inTheWay = false;
			int distance = Math.abs(startX - landingX);
			if (((landingX < 0) || (landingX > 7)) || ((landingY < 0) || (landingY > 7))) {
				validMove = false;
				//These ifs move diaginally in every direction
			} 
			else{
				if(((Math.abs(startX - landingX) !=0)&&(Math.abs(startY-landingY) == 0)) || ((Math.abs(startX-landingX)==0)&&(Math.abs(landingY - startY)!=0))){
					if(Math.abs(startX-landingX)!=0){
						int Xmovement = Math.abs(startX-landingX);
						if(startX-landingX > 0){
							for(int i=0;i<Xmovement; i++){
								if(piecePresent(initialX-(i*75), e.getY())){
									inTheWay = true;
									break;
								}
								else{
									inTheWay = false;
								}
							}
						}
						else{
							for(int i =0;i<Xmovement;i++){
								if(piecePresent(initialX+(i*75), e.getY())){
									inTheWay=true;
									break;
								}
								else{
									inTheWay = false;
								}
							}
						}
					}
					else{
						int Ymovement = Math.abs(startY-landingY);
						if(startY-landingY>0){
							for(int i=0;i<Ymovement;i++){
								if(piecePresent(e.getX(), initialY-(i*75))){
									inTheWay = true;
									break;
								}
								else{
									inTheWay= false;
								}
							}
						}
						else{
							for(int i=0;i <Ymovement;i++){
								if(piecePresent(e.getX(), initialY+(i*75))){
									inTheWay=true;
									break;
								}
								else{
									inTheWay=false;
								}
							}
						}
					}
					if(inTheWay){
						validMove=false;
					}
					else{
						if(piecePresent(e.getX(), e.getY())){
							if(pieceName.contains("White")){
									if(checkWhiteOponent(e.getX(), e.getY())){
										validMove = true;
									}
									else{
										validMove = false;
									}
								}
								else{
									if(checkBlackOponent(e.getX(), e.getY())){
										validMove=true;
									}
									else{
										validMove = false;
									}
								}
							}
							else{
								validMove = true;
							}
						}
					
					}
					else{
						if (Math.abs(startX - landingX) == Math.abs(startY - landingY)) {
							if ((startX - landingX < 0) && (startY - landingY < 0)) {
								for (int i = 0; i < distance; i++) {
									if (piecePresent((initialX + (i * 75)), (initialY + (i * 75)))) {
										inTheWay = true;
									}
								}
							} else if ((startX - landingX < 0) && (startY - landingY > 0)) {
								for (int i = 0; i < distance; i++) {
									if (piecePresent((initialX + (i * 75)), (initialY - (i * 75)))) {
										inTheWay = true;
									}
								}
							} else if ((startX - landingX > 0) && (startY - landingY > 0)) {
								for (int i = 0; i < distance; i++) {
									if (piecePresent((initialX - (i * 75)), (initialY - (i * 75)))) {
										inTheWay = true;
									}
								}
							} else if ((startX - landingX > 0) && (startY - landingY < 0)) {
								for (int i = 0; i < distance; i++) {
									if (piecePresent((initialX - (i * 75)), (initialY + (i * 75)))) {
										inTheWay = true;
									}
								}
							}
		
							if (inTheWay) {
								validMove = false;
							} else {
								if (piecePresent(e.getX(), (e.getY()))) {
									if (pieceName.contains("White")) {
										if (checkWhiteOponent(e.getX(), e.getY())) {
											validMove = true;
										} else {
											validMove = false;
										}
									} else {
										if (checkBlackOponent(e.getX(), e.getY())) {
											validMove = true;
										} else {
											validMove = false;
										}
									}
								} else {
									validMove = true;
								}
							}
						}
						else{
							validMove = false;
						}
					}
			}
}
		else if(pieceName.contains("BlackKing")){
			if(((startY - landingY == 1) && (startX - landingX == 0))||//This means it can move forward,  based on the position of the piece//
			 (startY - landingY == -1) && (startX - landingX == 0)|| //This means it can move back,  based on the position of the piece//
			 (startY - landingY == 0) && (startX - landingX == 1)||//This means it can move left,  based on the position of the piece//
			 (startY - landingY == 0) && (startX - landingX == -1)||//This means it can move right,  based on the position of the piece//
			 (startX - landingX == -1) && (startY - landingY == -1)||//This means it can move Dia right down,  based on the position of the piece//
			 (startX - landingX == 1) && (startY - landingY == 1)||//This means it can move Dia left down,  based on the position of the piece//
			 (startX - landingX == 1) && (startY - landingY == -1)||//This means it can move Dia right up,  based on the position of the piece//
			 (startX - landingX == -1) && (startY - landingY == 1)//This means it can move Dia left up,  based on the position of the piece//
			 )//This means it can move right,  based on the position of the piece//
			 {
				
				
				 if((piecePresent(e.getX(), (e.getY() - 75)) && checkWhiteKing(e.getX(), e.getY() - 75))||//This makes basically a border around the piece so that it cant put itself into check or take a king.
				 (piecePresent(e.getX(), (e.getY() + 75)) && checkWhiteKing(e.getX(), e.getY() + 75))||//It works by checking all the squares you are trying to move to for a white king in this instance.
				 (piecePresent(e.getX()+75, (e.getY() + 75)) && checkWhiteKing(e.getX()+75, e.getY() + 75))||
				 (piecePresent(e.getX()-75, (e.getY()  -75)) && checkWhiteKing(e.getX()-75, e.getY() -75))||
				 (piecePresent(e.getX()+75, (e.getY()  -75)) && checkWhiteKing(e.getX()+75, e.getY() -75))||
				 (piecePresent(e.getX()-75, (e.getY())) && checkWhiteKing(e.getX()-75, e.getY()))||
				 (piecePresent(e.getX()+75, (e.getY())) && checkWhiteKing(e.getX()+75, e.getY()))||
				 (piecePresent(e.getX()-75, (e.getY()+75)) && checkWhiteKing(e.getX()-75, e.getY() +75))
				 )
				 
				 
				 {
					 validMove = false;
				 }
				 else{
					if(piecePresent(e.getX(), e.getY())){
						
							if(checkBlackOponent(e.getX(), e.getY())){
								validMove = true;
							}
							else{
								validMove = false;
							}				
					}
					else if(!piecePresent(e.getX(), e.getY())){
						validMove = true;
					}
				}
			}
		}//This is where the black king ends

		else if(pieceName.contains("WhiteKing")){
			if(((startY - landingY == 1) && (startX - landingX == 0))||//This means it can move forward,  based on the position of the piece//
			 (startY - landingY == -1) && (startX - landingX == 0)|| //This means it can move back,  based on the position of the piece//
			 (startY - landingY == 0) && (startX - landingX == 1)||//This means it can move left,  based on the position of the piece//
			 (startY - landingY == 0) && (startX - landingX == -1)||//This means it can move right,  based on the position of the piece//
			 (startX - landingX == -1) && (startY - landingY == -1)||//This means it can move Dia right down,  based on the position of the piece//
			 (startX - landingX == 1) && (startY - landingY == 1)||//This means it can move Dia left down,  based on the position of the piece//
			 (startX - landingX == 1) && (startY - landingY == -1)||//This means it can move Dia right up,  based on the position of the piece//
			 (startX - landingX == -1) && (startY - landingY == 1)//This means it can move Dia left up,  based on the position of the piece//
			 )//This means it can move right,  based on the position of the piece//
			 {
				
				
				if((piecePresent(e.getX(), (e.getY() - 75)) && checkBlackKing(e.getX(), e.getY() - 75))||//Small issue here, when I move it to 1 and 8 on the x axis, it tries to check outside the board which breaks the piece.(I could not find a way to fix this)//
				(piecePresent(e.getX(), (e.getY() + 75)) && checkBlackKing(e.getX(), e.getY() + 75))||
				(piecePresent(e.getX()+75, (e.getY() + 75)) && checkBlackKing(e.getX()+75, e.getY() + 75))||
				(piecePresent(e.getX()-75, (e.getY()  -75)) && checkBlackKing(e.getX()-75, e.getY() -75))||
				(piecePresent(e.getX()+75, (e.getY()  -75)) && checkBlackKing(e.getX()+75, e.getY() -75))||
				(piecePresent(e.getX()-75, (e.getY())) && checkBlackKing(e.getX()-75, e.getY()))||
				(piecePresent(e.getX()+75, (e.getY())) && checkBlackKing(e.getX()+75, e.getY()))||
				(piecePresent(e.getX()-75, (e.getY()+75)) && checkBlackKing(e.getX()-75, e.getY() +75))
				)
				
				
				{
					validMove = false;
				}
				else{
				   if(piecePresent(e.getX(), e.getY())){
					   
						   if(checkWhiteOponent(e.getX(), e.getY())){
							   validMove = true;
						   }
						   else{
							   validMove = false;
						   }				
				   }
				   else if(!piecePresent(e.getX(), e.getY())){
					   validMove = true;
				   }
			   }
		   }
			
		}//This is where the black king ends


		if (pieceName.contains("Bishop")) {//start of the bishop\\
			Boolean inTheWay = false;
			int distance = Math.abs(startX - landingX);
			if (((landingX < 0) || (landingX > 7)) || ((landingY < 0) || (landingY > 7))) {
				validMove = false;
				//These ifs move diaginally in every direction
			} else {
				validMove = true;
				if (Math.abs(startX - landingX) == Math.abs(startY - landingY)) {
					if ((startX - landingX < 0) && (startY - landingY < 0)) {
						for (int i = 0; i < distance; i++) {
							if (piecePresent((initialX + (i * 75)), (initialY + (i * 75)))) {
								inTheWay = true;
							}
						}
					} else if ((startX - landingX < 0) && (startY - landingY > 0)) {
						for (int i = 0; i < distance; i++) {
							if (piecePresent((initialX + (i * 75)), (initialY - (i * 75)))) {
								inTheWay = true;
							}
						}
					} else if ((startX - landingX > 0) && (startY - landingY > 0)) {
						for (int i = 0; i < distance; i++) {
							if (piecePresent((initialX - (i * 75)), (initialY - (i * 75)))) {
								inTheWay = true;
							}
						}
					} else if ((startX - landingX > 0) && (startY - landingY < 0)) {
						for (int i = 0; i < distance; i++) {
							if (piecePresent((initialX - (i * 75)), (initialY + (i * 75)))) {
								inTheWay = true;
							}
						}
					}

					if (inTheWay) {
						validMove = false;
					} else {
						if (piecePresent(e.getX(), (e.getY()))) {
							if (pieceName.contains("White")) {
								if (checkWhiteOponent(e.getX(), e.getY())) {
									validMove = true;
								} else {
									validMove = false;
								}
							} else {
								if (checkBlackOponent(e.getX(), e.getY())) {
									validMove = true;
								} else {
									validMove = false;
								}
							}
						} else {
							validMove = true;
						}
					}
				} else {
					validMove = false;
				}
			}
		}//end of the bishop
	
		else if(pieceName.contains("Rook")){
			boolean inTheWay = false;
			if(((landingX<0)|| (landingX > 7)) ||(landingY < 0) || (landingY > 7)){//Inatial movement of the piece//
				validMove = false;
			}
			else{
				if(((Math.abs(startX - landingX) !=0)&&(Math.abs(startY-landingY) == 0)) || ((Math.abs(startX-landingX)==0)&&(Math.abs(landingY - startY)!=0))){
					if(Math.abs(startX-landingX)!=0){
						int Xmovement = Math.abs(startX-landingX);
						/*In the rook, it runs the same way as the bishop but rather than it go diaginally it will go x and y*/
						if(startX-landingX > 0){
							for(int i=0;i<Xmovement; i++){
								if(piecePresent(initialX-(i*75), e.getY())){
									inTheWay = true;
									break;
								}
								else{
									inTheWay = false;
								}
							}
						}
						else{
							for(int i =0;i<Xmovement;i++){
								if(piecePresent(initialX+(i*75), e.getY())){
									inTheWay=true;
									break;
								}
								else{
									inTheWay = false;
								}
							}
						}
					}
					else{
						int Ymovement = Math.abs(startY-landingY);
						if(startY-landingY>0){
							for(int i=0;i<Ymovement;i++){
								if(piecePresent(e.getX(), initialY-(i*75))){
									inTheWay = true;
									break;
								}
								else{
									inTheWay= false;
								}
							}
						}
						else{
							for(int i=0;i <Ymovement;i++){
								if(piecePresent(e.getX(), initialY+(i*75))){
									inTheWay=true;
									break;
								}
								else{
									inTheWay=false;
								}
							}
						}
					}
					if(inTheWay){
						validMove=false;
					}
					else{
						if(piecePresent(e.getX(), e.getY())){
							if(pieceName.contains("White")){
									if(checkWhiteOponent(e.getX(), e.getY())){
										validMove = true;
									}
									else{
										validMove = false;
									}
								}
								else{
									if(checkBlackOponent(e.getX(), e.getY())){
										validMove=true;
									}
									else{
										validMove = false;
									}
								}
							}
							else{
								validMove = true;
							}
						}
					
					}
					else{
						validMove = false;
					}
				}
		}//End of rook











		else if (pieceName.equals("BlackKnight")){
			if(((xMovement == 1)&&(yMovement==2))||((xMovement ==2) &&(yMovement == 1))){
				if(!piecePresent(e.getX(), e.getY())){
					validMove = true;
				}
				else{
					if(pieceName.contains("Black")){
	
						if(checkBlackOponent(e.getX(), e.getY())){
							validMove = true;
						}
					}
					else{
						if(checkWhiteOponent(e.getX(), e.getY())){
							validMove = true;
						}
					}
				}
				
				}
		}//end of black knight
		
		else if(pieceName.equals("BlackPawn")){
			if(startY == 6){ /*Pawn is making its first move here<---
				/* If the pawn is on move 1, it can move 1/2 squares,
				but it cant move after this, move up the board, in a Y direction
				there is no movement in the x direction.
				The else if checks if the pawns are the enemy or friendly
				it also checks if there is a piece in that box*/
				

				///// inital start ////
				if(((yMovement == 1)||(yMovement == 2))&&(startY > landingY)&&(xMovement == 0)){
					if(yMovement == 2){
						if((!piecePresent(e.getX(), e.getY()))&&(!piecePresent(e.getX(), e.getY()+75))){
							validMove = true;
							
						}
					}
					else {
						if(!piecePresent(e.getX(), e.getY())){
						validMove = true; 
						
						
						}
					}
				}///// inital start end//// 
				
				/* This is for any other move on the board*/
				else if ((yMovement == 1)&&(startY > landingY)&&(xMovement == 1)){
					if(piecePresent(e.getX(), e.getY())){
						if(checkBlackOponent(e.getX(), e.getY())){
							validMove = true;
						}
					}
				}/* ends the else if condition*/			
			}
				else{/*This is where the pawn is making all the sub moves(all the exceptions)*/
					if(((yMovement == 1))&&(startY > landingY)&&(xMovement == 0)){
						if(!piecePresent(e.getX(), e.getY())){
							validMove = true;
							if(landingY == 0){
								blackSuccess= true;
							}
						}
					}
					else if ((yMovement == 1)&&(startY > landingY)&&(xMovement == 1)){
						if(piecePresent(e.getX(), e.getY())){
							if(checkBlackOponent(e.getX(), e.getY())){
								validMove = true;
								if(landingY == 0){
									blackSuccess = true;
								}
							}
						}
					}
				}

		}
		
		else if(pieceName.equals("WhitePawn")){
			if(startY == 1){ /*Pawn is making its first move here<---
				/* If the pawn is on move 1, it can move 1/2 squares,
				but it cant move after this, move up the board, in a Y direction
				there is no movement in the x direction.
				The else if checks if the pawns are the enemy or friendly
				it also checks if there is a piece in that box*/
				

				///// inital start(This is just the black pawn, but inverted.) ////
				if(((yMovement == 1)||(yMovement == 2))&&(startY < landingY)&&(xMovement == 0)){
					if(yMovement == 2){
						if((!piecePresent(e.getX(), e.getY()))&&(!piecePresent(e.getX(), e.getY()+75))){
							validMove = true;
							
						}
					}
					else {
						if(!piecePresent(e.getX(), e.getY())){
						validMove = true; 
						
						
						}
					}
				}///// inital start end//// 
				
				/* This is for any other move on the board*/
				else if ((yMovement == 1)&&(startY < landingY)&&(xMovement == 1)){
					if(piecePresent(e.getX(), e.getY())){
						if(checkWhiteOponent(e.getX(), e.getY())){
							validMove = true;
						}
					}
				}/* ends the else if condition*/			
			}
				else{/*This is where the pawn is making all the sub moves(all the exceptions)*/
					if(((yMovement == 1))&&(startY < landingY)&&(xMovement == 0)){
						if(!piecePresent(e.getX(), e.getY())){
							validMove = true;
							if(landingY == 7){
								whiteSuccess= true;
							}
						}
					}
					else if ((yMovement == 1)&&(startY < landingY)&&(xMovement == 1)){
						if(piecePresent(e.getX(), e.getY())){
							if(checkWhiteOponent(e.getX(), e.getY())){
								validMove = true;
								if(landingY == 7){
									whiteSuccess = true;
								}
							}
						}
					}
				}

		}




		else if(pieceName.equals("WhiteKnight")){
		/* the knight moves in an L, so if it moves on the x axis 2 times, it can only move once on the 
		Y and viceversa we need to check for pieces on the tiles white moving*/
		if(((xMovement == 1)&&(yMovement==2))||((xMovement ==2) &&(yMovement == 1))){
			if(!piecePresent(e.getX(), e.getY())){
				validMove = true;
			}
			else{
				if(pieceName.contains("White")){

					if(checkWhiteOponent(e.getX(), e.getY())){
						validMove = true;
					}
				}
				else{
					if(checkBlackOponent(e.getX(), e.getY())){
						validMove = true;
					}
				}
			}
			
			}
	
		}
	} catch (Exception f) {
		validMove = false;//This method stops the pieces from falling of the boards by saying if they dont move to a square on the board they will go back to normal due to it being an exception for an invalid move.
	}
		
		
		/*This method makes the pawn change to a Queen, by saying when it lands on (7 or it lands on 1)gets to the end turn the piece to a Queen.*/
		if (!validMove) {
			int location = 0;
			if (startY == 0) {
				location = startX;
			} else {
				location = (startY * 8) + startX;
			}
			String pieceLocation = pieceName + ".png";
			pieces = new JLabel(new ImageIcon(pieceLocation));
			panels = (JPanel) chessBoard.getComponent(location);
			panels.add(pieces);
		} else {
			if (blackSuccess) {
				int location = 0 + (e.getX() / 75);
				if (c instanceof JLabel) {
					Container parent = c.getParent();
					parent.remove(0);
					pieces = new JLabel(new ImageIcon("BlackQueen.png"));
					parent = (JPanel) chessBoard.getComponent(location);
					parent.add(pieces);
				} else {
					Container parent = (Container) c;
					pieces = new JLabel(new ImageIcon("BlackQueen.png"));
					parent = (JPanel) chessBoard.getComponent(location);
					parent.add(pieces);
				}
			} else if (whiteSuccess) {
				int location = 56 + (e.getX() / 75);
				if (c instanceof JLabel) {
					Container parent = c.getParent();
					parent.remove(0);
					pieces = new JLabel(new ImageIcon("WhiteQueen.png"));
					parent = (JPanel) chessBoard.getComponent(location);
					parent.add(pieces);
				} else {
					Container parent = (Container) c;
					pieces = new JLabel(new ImageIcon("WhiteQueen.png"));
					parent = (JPanel) chessBoard.getComponent(location);
					parent.add(pieces);
				}
			} else {
				if (c instanceof JLabel) {
					Container parent = c.getParent();
					parent.remove(0);
					parent.add(chessPiece);
				} else {
					Container parent = (Container) c;
					parent.add(chessPiece);
				}
				chessPiece.setVisible(true);
			}
		}
    }

    public void mouseClicked(MouseEvent e) {

    }
    public void mouseMoved(MouseEvent e) {
   }
    public void mouseEntered(MouseEvent e){

    }
    public void mouseExited(MouseEvent e) {

    }

	/*
		Main method that gets the ball moving.
	*/
    public static void main(String[] args) {
        JFrame frame = new ChessProject();
        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE );
        frame.pack();
        frame.setResizable(true);
        frame.setLocationRelativeTo( null );
        frame.setVisible(true);
     }
}
