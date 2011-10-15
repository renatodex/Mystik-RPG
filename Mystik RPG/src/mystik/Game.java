package mystik;

/**
MYSTIK RPG
STARTED May 23, 2010

(c) 2011


[ TO DO ]

== WARNING: THIS IS A WARNING ==

The code ahead is seriously disorganized into ONE GIANT file with a few files here and there. 
Do not, I repeat, do not gauge your eyes out. This code took a long time to write and it would
feel very insecure if you make fun of it. 

 **/

/**
 *  To fix...
 *  [When jFrame on opens... make text input or game panel focused] [ DONE ]
 *  [ On pick-up... only show "You picked up"... once" [ DONE ]
 */

import mystik.Player;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.io.*;
import java.net.*;
import java.util.*;
import javax.imageio.ImageIO;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.image.RasterFormatException;
import java.io.File;


import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.JApplet;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JScrollPane;

import javax.swing.border.Border;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.*;
import java.net.URL;
import javax.swing.border.LineBorder;

import javax.swing.JPanel;
import javax.swing.BorderFactory;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

import com.google.gson.Gson;

public class Game extends JApplet implements KeyListener, MouseListener,
            ActionListener {

      Image[] tiles;
      Image[] weapon;
      Image[] monster;
      Image[] other;
      String username;
      JLabel theUser;
      String[] user;
      String[] itemsDropped;
      Boolean[] monsterRight;
      Boolean[] monsterLeft;
      Boolean moveUp = true;
      Boolean moveDown = true;
      Boolean moveLeft = true;
      Boolean moveRight = true;
      Boolean beingAttacked = false;
      
      JPanel pnPanel0;

      JPanel pnPanel3;

      JPanel pnHeadInfoPanel;
      JButton btHeadIcon;
      JLabel lbLabel0;

      JPanel pnBodyInfoPanel;
      JButton btBodyIcon;

      JPanel pnLeftArmInfoPanel;
      JButton btLeftArmIcon;

      JPanel pnRightArmInfoPanel;
      JButton btRightArmIcon;

      JPanel pnLegsInfoPanel;
      JButton btLegsIcon;
      JProgressBar pbExpBar;

      JPanel pnPanel12;
      JLabel lbLabel2;
      JLabel lbLabel3;
      JLabel lbLabel4;
      JLabel lbLabel5;
      JLabel lbLabel6;
      JLabel lbLabel7;
      JLabel lbLabel8;
      JLabel lbLevelInfoLabel;
      JLabel lbExpInfoLabel;
      JLabel lbExpLeftInfoLabel;
      JLabel lbGoldInfoLabel;
      JLabel lbStepsInfoLabel;
      JLabel lbKillsInfoLabel;
      JLabel lbDeathsInfoLabel;
      JButton btInfoOK;
      
      int headWear, leftArmWear, rightArmWear, bodyWear, legWear;
      
      int x, y, px, py, tx, ty; // x tile - y tile // player x - player y // tile
      // x - tile y
      
      //test
      boolean left, right, down, up, canMove, respawn;
      boolean drawFlail, drawBattleaxe, drawSword;
      public  int[][] board;
      public  int[][] pushBoard;
      boolean[] weaponPicked;
      JLabel lx, ly; // to see where we are!
      int r1, r2, u1, u2, l1, l2, d1, d2, spawnX, spawnY;
      int rightSide = 480;
      int downSide = 320;
      /**
      *
      * GAME SETTINGS
      *
      */
      final int NUM_TILES = 523;
      final int NUM_WEAPS = 7;
      final int NUM_MONS = 18;
      final int NUM_OTHS = 1;
      int mapX = 15;
      int mapY = 10;
      String txtLastFX, txtLastFY;
      boolean boolRC, boolLC, boolUC, boolDC;
      int leftMap, upMap, rightMap, downMap, currentMap, map;
      public static JTextArea c = new JTextArea();
      public JTextArea stats = new JTextArea();
      public JTextArea testDan = new JTextArea();
      public  String newline;



      public static JApplet applet; 
      public  Font TimesR = new Font("MonoSpaced", Font.BOLD, 15);
      public static ArrayList<String> arr;
      public  Game anItem;
      public  boolean firstpush = false;
      String name, desc, typeOf, attackAdd, defenseAdd, canSell, canEat,
                  earnedCoins, canEquip;
      public  int blkStr;
      public  Random roll = new Random();
      // public  String me.getUsername();
      public  int[] userPX;
      public  int[] userPY;
      public Map<String, Player> players = new HashMap<String, Player>();
      private ArrayList<String> droppedItems = new ArrayList<String>();
      private ArrayList<String> maplist = new ArrayList<String>();
      private ArrayList<String> monstersActive = new ArrayList<String>();
      private ArrayList<String> entrances = new ArrayList<String>();
      private ArrayList<String> teleports = new ArrayList<String>();
      private ArrayList<String> signs = new ArrayList<String>();
      public  String[] tokens;
      public  String[] temp;
      public  boolean isResp = false;
      public  String myCommand = "move";
      public JScrollPane sp;
      public Player me;
      public  boolean chatOne = false;
      public int red = 0;
      public static JTextField say = new JTextField();
      public GamePanel game = new GamePanel();
      int xpos, ypos;
      int rect1xco, rect1yco, rect1width, rect1height;
      boolean gameClicked;
      public DefaultListModel model = new DefaultListModel();
      public JLabel usersOn, labelX, labelY, labelRoom, labelStatus,  introLabel, introLabel_two, lName, lHealth, lExp, lAttack, lDefence, lLevel;
	  public JLabel labelUser;
      public JLabel lN2, lH2, lL2, lE2, lA2, lD2, signLabel;
      public JLabel extraSpace1, extraSpace2, extraSpace3, extraSpace4, extraSpace5, extraSpace6;
      public JLabel charInfo;
      public int count = 0;
      public JOptionPane introDuct;
      public JList list;
      public String currentItem;
      public String draw, htmlx;
      public String beforeCheck;
      public JPanel userPanel, xPanel, yPanel, titlePanel, roomPanel, introPanel, statusPanel, invOne, main, rightside, gameOn, statsNow, statsPanel, charPanel, signPanel;
      public String roomName, signhtml;
      public String itemIDdropped;
      public boolean pressedG;
      public boolean tele;
      public boolean FLP = true;
      public boolean FUP = true;
      public boolean FDP = true;
      public boolean FRP = true;
      public boolean charOpen = false;
      public int mapLoadNow = 1;
      public String attackedBy;
      public boolean isInBattle = false;
      public int lastPushed = 0;
      int lTile = 15;
      int theTile = 0;
      int qepe = 0;
      Boolean spotted = false;
      Boolean battleFinished = false;
      String attacker = "";
      Boolean attackUp = false;
      Boolean attackDown = false;
      Boolean attackLeft = false;
      Boolean attackRight = false;
      Boolean monMove = true;
      int stepsTill = 0;
      public int check_down_Y, check_up_Y, checkX, checkY, check_left_X, check_right_X;
      public boolean firstStrike = false;
      public int newMonHp, yourHit;
      public static Rectangle clip;


	  // Right Click Inventory
	  public JPopupMenu popup, leftArmPopup;
	  public JMenuItem item, cancelMenuItem, useMenuItem, dropMenuItem, equipMenuItem, wearMenuItem, unequipMenuItem;
	  public JPopupMenu useCancelPopup, useDropPopup, useUsePopup, useEquipPopup, useWearPopup, useUnequipPopup;


	  // Character Sheet
	public JButton headIcon;
	public JLabel head, titleChar;
	public JPanel leftx, rightx;
	
	// mapload
	public static String MapTitle;
	public static Data data;
	public static Data dataNow;
	public static Data dataMap;
	public static Data dataLeft;
	public static Data dataArray[];
	public static ItemLoad itemLoad;
	public static MonsterLoad monsterLoad;
	public static EntranceLoad entranceLoad;
	public static TeleportLoad teleportLoad;
	public static NumberLoad numberLoad;
	public static SignLoad signLoad;
	public static String dan;
	public static FileReader fr;
	public static int number;
	public static int tile;
	public static String fileNow;
	public static String[] wepN;
	public static String[] mapStr;
	
	public int leftFileLoad = 0;
	

      // Network schtuff
      public  PrintStream os = null;
       Socket clientSocket = null;
       BufferedReader in;
       boolean closed;
       
       

      int lastX, lastY, row, col, prow, pcol;
      JLabel lbl1, lbl2, p1, p2, lblRC;


      int attack, defense, gold, level, exp, currhp, maxhp;

      public  void showInventory(ArrayList<String> theList) {
            for (int i = 0; i < theList.size(); i++) {
                  //System.out.println(theList.get(i));
            }
      }

      public void init() {

            setLayout(new BorderLayout());
            invOne = new JPanel(); // inventory panel
            new Exception();
            
            mapStr = new String[100];
            dataArray = new Data[100];
            


            // JPanel banner = new JPanel();

            main = new JPanel(new BorderLayout());
            main.setPreferredSize(new Dimension(631,521));
            rightside = new JPanel(new GridLayout(2,1));
            charPanel = new JPanel();
            statsPanel = new JPanel(new GridLayout(6,1));
            rightside.setPreferredSize(new Dimension(118,342));
            statsNow = new JPanel(new GridLayout(18,1));
			sp = new JScrollPane(c);

            main.add(game, BorderLayout.CENTER); // game screen;
			game.setPreferredSize(new Dimension(512,352));
            main.add(sp, BorderLayout.SOUTH); // chat/textarea screen
            add(main, BorderLayout.CENTER);
            add(say, BorderLayout.SOUTH);


            /**
             * arr.add("Hatchet"); arr.add("Sword"); arr.add("Shield"); arr.add(gold
             * + " Gold"); //System.out.println("You have " + arr.size() +
             * " items in your inventory."); showInventory(arr);
             **/

// List stuff
   ActionListener menuListener = new ActionListener()
        {
            public void actionPerformed(ActionEvent event)
            {

                String invAction = event.getActionCommand(); // Use, Cancel, Wear, Drop?


                int itemSelect = list.getSelectedIndex();
                Object actItem = list.getModel().getElementAt(itemSelect); // on which item?

                String rightclickDrop = getItem(actItem.toString())[8];


                //c.append("\nPopup menu item [" + invAction + "] [ " + actItem + " ] was pressed.");

                boolean  isDropping = invAction.startsWith("Drop");
                boolean  isEquipping = invAction.startsWith("Equip");
                
                int rightStrDrop = Integer.parseInt(rightclickDrop);
				
                if (isDropping == true) {
					c.append("\nYou dropped a " + getItem(actItem.toString())[0] + ".");
					model.remove(list.getSelectedIndex());
					dropItemNow(rightStrDrop, spawnX, spawnY);
					repaint();
				}
                
                if (isEquipping == true) {
                	
                	
                	// Wearing a body armor
                	if (getItem(actItem.toString())[2] == "3") {
                    	if (bodyWear == 0) {                		
      
    					c.append("\nYou equipped a " + getItem(actItem.toString())[0] + ".");
    					int newWepId = Integer.parseInt(getItem(actItem.toString())[8])-1;
    					btBodyIcon.setIcon(new ImageIcon(weapon[newWepId])); // NOI18N
    					btBodyIcon.setToolTipText(getItem(actItem.toString())[0]);
    		            bodyWear = newWepId;
    		            model.remove(list.getSelectedIndex());
                    	}else{
                    		c.append("\nYou are already wielding something.");
                    	}
                    	}
                	
                	// Wielding a weapon
                	if (getItem(actItem.toString())[2] == "2") {
                	if (leftArmWear == 0) {                		
  
					c.append("\nYou equipped a " + getItem(actItem.toString())[0] + ".");
					int newWepId = Integer.parseInt(getItem(actItem.toString())[8])-1;
					btLeftArmIcon.setIcon(new ImageIcon(weapon[newWepId])); // NOI18N
		            btLeftArmIcon.setToolTipText(getItem(actItem.toString())[0]);
		            addAttack(Integer.parseInt(getItem(actItem.toString())[3]));
		            leftArmWear = newWepId;
		            model.remove(list.getSelectedIndex());
                	}else{
                		c.append("\nYou are already wielding something.");
                	}
                	}
		            
				

			
					repaint();
				}
                
            }
        };


   useUsePopup = new JPopupMenu();
   useDropPopup = new JPopupMenu();
   useCancelPopup = new JPopupMenu();
   useEquipPopup = new JPopupMenu();
   useWearPopup = new JPopupMenu();

   popup = new JPopupMenu();

   useMenuItem = new JMenuItem("Use");
   useMenuItem.addActionListener(menuListener);

   dropMenuItem = new JMenuItem("Drop");
   dropMenuItem.addActionListener(menuListener);

   equipMenuItem = new JMenuItem("Equip");
   equipMenuItem.addActionListener(menuListener);

   wearMenuItem = new JMenuItem("Wear");
   wearMenuItem.addActionListener(menuListener);

   cancelMenuItem = new JMenuItem("Cancel");
   cancelMenuItem.addActionListener(menuListener);


            list = new JList(model);
            list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

            list.addMouseListener( new MouseAdapter()
					{
						public void mousePressed(MouseEvent e)
						{
							if ( SwingUtilities.isRightMouseButton(e) )
							{
								System.out.println("Row: " + list.locationToIndex(e.getPoint()));
								list.setSelectedIndex(list.locationToIndex(e.getPoint()));
							}
						}

						public void mouseReleased(MouseEvent ex)
						{
							if ( SwingUtilities.isRightMouseButton(ex) )
							{
								popup.removeAll();
								System.out.println("All items removed.");
							}
						}
					}
		);
            

            list.addMouseListener(new MousePopupListener());
            



            say.addKeyListener(this);

            list.setVisible(true);

            list.setFocusable(false);
            //list.setPreferredSize(new Dimension(100,480));

               c.setBackground(Color.white);
			            c.setForeground(Color.black);
			            c.setFont(TimesR);
			            c.setEditable(true);

            c.setLineWrap(true);

			        //Color color = new Color(255, 255, 255);
			        UIManager.put("ScrollBar.background", Color.BLACK);
			        UIManager.put("ScrollBar.darkShadow", Color.BLACK);
			        UIManager.put("ScrollBar.foreground", Color.WHITE);
			        UIManager.put("ScrollBar.highlight", Color.BLACK);
			        UIManager.put("ScrollBar.shadow", Color.red);
			        UIManager.put("ScrollBar.thumb", Color.BLACK);
			        UIManager.put("ScrollBar.thumbDarkShadow", Color.BLACK);
			        UIManager.put("ScrollBar.thumbHighlight", Color.BLACK);
			        UIManager.put("ScrollBar.thumbShadow", Color.BLACK);
			        UIManager.put("ScrollBar.track", Color.WHITE);
			        UIManager.put("ScrollBar.trackHighlight", Color.BLUE);
			         sp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        sp.getVerticalScrollBar().setUI(new BasicScrollBarUI());
        sp.setPreferredSize(new Dimension(520,150));

        list.setBorder(new LineBorder(Color.BLACK,4));
        //c.setBackground(new Color(255,255,255, 127));






			main.add(rightside, BorderLayout.EAST); // stat screen

            rightside.add(list); // inventory list
            rightside.add(statsPanel); // stats


            
            
            
			attack = 2;
			defense = 2;
			gold = 0;
			exp = 52;
			level = getLevels(exp);
			c.append("\nI am level: " + getLevels(exp));
			currhp = 10;
			maxhp = 10;
			
			int mapToLoad = 1;
			
			
			board = loadBoard(1);
			pushBoard = loadPush(1);

			String TempMystik = System.getenv("APPDATA") + "\\.mystik";
			String TempMystikTiles = System.getenv("APPDATA") + "\\.mystik\\tiles";
			String TempMystikMaps = System.getenv("APPDATA") + "\\.mystik\\maps";
			    new File(TempMystik).mkdir();
			    
			    //new File(TempMystikTiles).mkdir();
			    new File(TempMystikMaps).mkdir();

			    File file=new File(System.getenv("APPDATA") + "\\.mystik\\maps\\main.txt");
			    
			    
			    
			    File fileReadMe =new File(System.getenv("APPDATA") + "\\.mystik\\readme.txt");
			    boolean Readexists = fileReadMe.exists();
			    
			    if (!Readexists) {
			    	try {
			    		
			    		URL readURL = new URL("http://mystikrpg.com/readme.txt");
				        BufferedReader xin = new BufferedReader(new InputStreamReader(readURL.openStream()));
				        String str;
					    BufferedWriter xout = new BufferedWriter(new FileWriter(TempMystik+"\\readme.txt"));
					    while ((str = xin.readLine()) != null) {
					        xout.write(str);
					        xout.newLine();
					    }

				    xout.close();
				    xin.close();
				} catch (MalformedURLException e) {
				} catch (IOException e) {
				}
			    
			    }
			    
			    
			    boolean exists = file.exists();
			//System.out.println(file + " > " + exists);
			    
			    /**
			    File tileCheckFile = new File(System.getenv("APPDATA") + "\\.mystik\\tiles\\t0.png");
			    boolean tileExists = tileCheckFile.exists();
			    if (!tileExists) {
	    			// Grabs tiles.png and downloads to mystik
	    			for (int i = 0; i < NUM_TILES; i++) {

	    					try {
	    			System.out.println("Downloading tile " + i + " of "+NUM_TILES);
	    			File outputFileLocation = new File (System.getenv("APPDATA") + "\\.mystik\\tiles\\t"+i+".png");             
	    			String imgURL="http://mystikrpg.com/line_tile/t"+i+".png";
	    			URL urlItem = new URL(imgURL);
	    			Image imageBR = ImageIO.read(urlItem);
	    			BufferedImage cpimg=(BufferedImage) imageBR;
	    			ImageIO.write(cpimg, "png", outputFileLocation);
	    					} catch (MalformedURLException e1) {
	    						e1.printStackTrace();
	    					} catch (IOException ex) {
	    						ex.printStackTrace();
	    					}
	    			}
	    			System.out.println("Tiles downloaded!"); 
			    } **/
			    
			    
			    if (!exists) {
			    	
			    	currentMap = 1;
			    	c.append("main.txt not found. Creating map...");
			    	try {
			    		
	                // Grabs items.gif and downloads to mystik
			    	System.out.println("Downloading items...");
			    	File outputFileLocation = new File (System.getenv("APPDATA") + "\\.mystik\\items.gif");             
	      			String imgURL="http://mystikrpg.com/items.gif";
	    			URL urlItem = new URL(imgURL);
	    			Image imageBR = ImageIO.read(urlItem);
	    			BufferedImage cpimg=(BufferedImage) imageBR;
	    			ImageIO.write(cpimg, "gif", outputFileLocation);
	    			System.out.println("Items downloaded!");
	    			
	                // Grabs monsters.gif and downloads to mystik
	    			System.out.println("Downloading monsters...");
			    	outputFileLocation = new File (System.getenv("APPDATA") + "\\.mystik\\monsters.gif");             
	      			imgURL="http://mystikrpg.com/monsters.gif";
	    			urlItem = new URL(imgURL);
	    			imageBR = ImageIO.read(urlItem);
	    			cpimg=(BufferedImage) imageBR;
	    			ImageIO.write(cpimg, "gif", outputFileLocation);
	    			System.out.println("Monsters downloaded!");

	    			
			    URL url = new URL("http://mystikrpg.com/main.txt");
				
			    BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
			    String strx;
			    BufferedWriter out = new BufferedWriter(new FileWriter(TempMystik+"\\maps\\main.txt"));
			    while ((strx = in.readLine()) != null) {
			        out.write(strx);
			        out.newLine();
			    }

			    out.close();
			    in.close();


			} catch (MalformedURLException e) {
			} catch (IOException e) {
			}
			
			
			//mapToLoad = 7;
			//monstersActive.add("1|13|9|1|nothing");
			//addSign(13,8,"Welcome to the game.<br /><br />Outside world is locked. Make your own world.");
			//addEntrance(12,4,167);
			//addEntrance(3,5,166);
			//addEntrance(7,7,166);
			
			//dropItem(6,10,2,1);
			//dropItem(1,5,3,1);
			
			

			c.append("\nMap created!");
			}else{
				
				
				try {
					fr = new FileReader(System.getenv("APPDATA") + "\\.mystik\\maps\\main.txt");
				}catch(FileNotFoundException fne) {
					fne.printStackTrace();
				}


				File directory = new File(System.getenv("APPDATA") + "\\.mystik\\maps");
				String filename[] = directory.list();

				//System.out.println("FILENAME LENGTH! " + filename.length);
				int howManyFiles = 0;
				for (int qi = 0; qi < filename.length; qi++) {
				String listFilenames = System.getenv("APPDATA") + "\\.mystik\\maps\\"+filename[qi];

				try {
					fr = new FileReader(listFilenames);
				}catch(FileNotFoundException fne) {
					fne.printStackTrace();
				}
					    StringBuffer sb = new StringBuffer();
					    char[] b = new char[1000];
					    int n = 0;
					    try {
					    while ((n = fr.read(b)) > 0) {
					         sb.append(b, 0, n);
						 }
						 }catch(IOException rex) {
							 rex.printStackTrace();
						 }
					    String fileString = sb.toString();
					    //mapStr[qi] = fileString;
					    
						try {
							dataArray[qi] = new Gson().fromJson(fileString, Data.class);
							}catch (Exception er) {
								er.printStackTrace();
							}
					    
					    
					    //System.out.println

				//System.out.println("<< " + listFilenames);
				//System.out.println(fileString);
				howManyFiles++;
				
				
				//System.out.println("HERA DERP\n"+fileString);
					try {
					data = new Gson().fromJson(fileString, Data.class);
					}catch (Exception er) {
						er.printStackTrace();
					}
/**
				System.out.println("==========================\n JSON MAP LOAD...\n==========================\n");

					System.out.println("Name of map: " + data.getTitle());
					System.out.println("ID of map: " + data.getIDS().get(0).getCurrentMap());
					**/
					maplist.add(Integer.toString(data.getIDS().get(0).getCurrentMap()));
					
					try {
					String mapToMatch = System.getenv("APPDATA") + "\\.mystik\\maps\\main.txt";
					
					if (mapToMatch.equals(listFilenames)) {
						fileNow = fileString;
					}
					}catch (Exception erxx) {
						erxx.printStackTrace();
					}
										

				// Items
				String[] wepN = new String[100];
				String[] wepX = new String[100];
				String[] wepY = new String[100];
				//String[] wepMap = new String[100];
				int[] tile = new int[256];
				
		

				int wepQty = 0;

				try {
				for (int ci=0; ci < wepN.length; ci++) {

				    if (data.getItems().get(ci).getID() == null || "".equals(data.getItems().get(ci).getID())) {
				        System.out.println("There was an error processing ITEMS");
				        break;
				    }

				    wepN[ci] = data.getItems().get(ci).getID();
				    wepX[ci] = Integer.toString(data.getItems().get(ci).getX());
				    wepY[ci] = Integer.toString(data.getItems().get(ci).getY());
				    //wepMap[i] = Integer.toString(data.getItems().get(i).getTheMap());

				    wepQty++;
				}
				}catch(Exception xe) { }
				// End loop of items


				
				// Monsters
				String[] monN = new String[100];
				String[] monX = new String[100];
				String[] monY = new String[100];
				//String[] monMap = new String[100];

				int monQty = 0;

				try {
				for (int moni=0; moni < monN.length; moni++) {
				    if (data.getMonsters().get(moni).getID() == null || "".equals(data.getMonsters().get(moni).getID())) {
				        System.out.println("There was an error processing MONSTERS");
				        break;
				    }
				    
				    monN[moni] = data.getMonsters().get(moni).getID();
				    monX[moni] = Integer.toString(data.getMonsters().get(moni).getX());
				    monY[moni] = Integer.toString(data.getMonsters().get(moni).getY());
				    //monMap[moni] = Integer.toString(data.getMonsters().get(moni).getOnMap());
				    monQty++;
				}
				}catch(Exception xe) { }
				// End loop of monsters

				// Signs
				String[] signY = new String[100];
				String[] signX = new String[100];
				String[] signText = new String[100];
				String[] signMap = new String[100];

				int signQty = 0;

				try {
				for (int signI=0; signI < signX.length; signI++) {

					signX[signI] = Integer.toString(data.getSigns().get(signI).getSignX());
					signY[signI] = Integer.toString(data.getSigns().get(signI).getSignY());
					signText[signI] = data.getSigns().get(signI).getSignText();
					//signMap[signI] = Integer.toString(data.getSigns().get(signI).getSignMap());
					
					
				    signQty++;
				}
				}catch(Exception xe) { }
				// End loop of signs
				
				// Entrances
				String[] entY = new String[100];
				String[] entX = new String[100];
				String[] tile_after_ent = new String[100];
				String[] entItemReq = new String[100];
				//String[] entMap = new String[100];

				int entQty = 0;

				try {
				for (int enti=0; enti < entY.length; enti++) {

					entX[enti] = Integer.toString(data.getEntrances().get(enti).getEntX());
					entY[enti] = Integer.toString(data.getEntrances().get(enti).getEntY());
					tile_after_ent[enti] = Integer.toString(data.getEntrances().get(enti).getTileAfter());
					entItemReq[enti] = Integer.toString(data.getEntrances().get(enti).getItemReq_ent());
					//entMap[enti] = Integer.toString(data.getEntrances().get(enti).getEntMap());
					
				    entQty++;
				}
				}catch(Exception xe) { }
				// End loop of entrances

				
				// Teleports
				String[] toY = new String[100];
				String[] toX = new String[100];
				String[] toMap = new String[100];
				String[] fromY = new String[100];
				String[] fromX = new String[100];
				String[] fromMap = new String[100];
				String[] item_req = new String[100];

				int teleQty = 0;

				try {
				for (int teleI=0; teleI < entY.length; teleI++) {

					toX[teleI] = Integer.toString(data.getTeleports().get(teleI).getToX());
					toY[teleI] = Integer.toString(data.getTeleports().get(teleI).getToY());
					toMap[teleI] = Integer.toString(data.getTeleports().get(teleI).getToMap());
					fromY[teleI] = Integer.toString(data.getTeleports().get(teleI).getFromY());
					fromX[teleI] = Integer.toString(data.getTeleports().get(teleI).getFromX());
					fromMap[teleI] = Integer.toString(data.getTeleports().get(teleI).getFromMap());
					item_req[teleI] = Integer.toString(data.getTeleports().get(teleI).getItemReq());

				    teleQty++;
				}
				}catch(Exception xe) { }
				// End loop of teleports

				//System.out.println("\n==========================\n SIGNS IN MAP\n==========================\n");
				//System.out.println("# OF ITEMS: " + signQty + "\n");
				
				
			
				
				for (int ai=0; ai < signQty; ai++) {
					
					addSignNow(Integer.parseInt(signX[ai]), Integer.parseInt(signY[ai]),signText[ai], data.getIDS().get(0).getCurrentMap());
				      
					//System.out.println(">> ID: " + wepN[bi] + " (" + wepX[bi] + ", " + wepY[bi] + ")");
				    }
				
				//System.out.println("\n==========================\n ITEMS IN MAP\n==========================\n");
				//System.out.println("# OF ITEMS: " + wepQty + "\n");
				
				

				
				for (int bi=0; bi < wepQty; bi++) {
					//public void dropItemNow(int dropId, int dropX, int dropY, int dropMap) {
					dropItem(Integer.parseInt(wepN[bi]),Integer.parseInt(wepX[bi]),Integer.parseInt(wepY[bi]), data.getIDS().get(0).getCurrentMap());
					
				        //System.out.println(">> ID: " + wepN[bi] + " (" + wepX[bi] + ", " + wepY[bi] + ")");
				    }

				//System.out.println("\n==========================\n MONSTER IN MAP\n==========================\n");
				//System.out.println("# OF MONSTERS: " + monQty + "\n");

				for (int mi=0; mi < monQty; mi++) {
					addMonster(Integer.parseInt(monN[mi]), Integer.parseInt(monX[mi]), Integer.parseInt(monY[mi]), data.getIDS().get(0).getCurrentMap());
					//addMonster(3, 4, 4, 6);
				       // System.out.println(">> ID: " + monN[mi] + " (" + monX[mi] + ", " + monY[mi] + ")");
				    }


				//System.out.println("\n==========================\n ENTRANCES IN MAP\n==========================\n");
				//System.out.println("# OF ENTRANCES: " + entQty + "\n");

				for (int ei=0; ei < entQty; ei++) {
					// addEntrance(int entX, int entY, int tileAfter, int onMap) {
					
					if (entItemReq[ei].equals("0")) {
						
						addEntrance(Integer.parseInt(entX[ei]), Integer.parseInt(entY[ei]), Integer.parseInt(tile_after_ent[ei]));
					}else{
						addEntranceItem(Integer.parseInt(entX[ei]), Integer.parseInt(entY[ei]), Integer.parseInt(tile_after_ent[ei]), Integer.parseInt(entItemReq[ei]));
					}
					
				       // System.out.println(">> Tile After: " + tile_after_ent[ei] + ", Item needed:  " + entItemReq[ei] + " (" + entX[ei] + ", " + entY[ei] + ")");
				    }


				//System.out.println("\n==========================\n TELEPORTS IN MAP\n==========================\n");
				//System.out.println("# OF TELEPORTS: " + teleQty + "\n");

				for (int ti=0; ti < teleQty; ti++) {
					// public void addTeleport(int fromX, int fromY, int fromMap, int toX, int toY, int toMap) {
				        //System.out.println(">> From Map: " + fromMap[ti] + " (" + fromX[ti] + ", " + fromY[ti] + ") --> To Map " 
				        		//+ toMap[ti] + " (" + toX[ti] + ", " + toY[ti] + ")");
				        
				        if (item_req[ti].equals("0")) {
				        	addTeleport(Integer.parseInt(fromX[ti]),Integer.parseInt(fromY[ti]),Integer.parseInt(fromMap[ti]),Integer.parseInt(toX[ti]),Integer.parseInt(toY[ti]),Integer.parseInt(toMap[ti]));
				        }else{
				        	addTeleportItem(Integer.parseInt(fromX[ti]),Integer.parseInt(fromY[ti]),Integer.parseInt(fromMap[ti]),Integer.parseInt(toX[ti]),Integer.parseInt(toY[ti]),Integer.parseInt(toMap[ti]), Integer.parseInt(item_req[ti]));
				        }
				        
				        
				}
				
				    }

			//System.out.println("FILE TO LOAD: MAIN.TXT >>\n"+fileNow);	
				
				// LeftArm stuff
				   ActionListener leftArmListener = new ActionListener()
				        {
				            public void actionPerformed(ActionEvent event)
				            {

				                String invAction = event.getActionCommand(); // Use, Cancel, Wear, Drop?

				                
				                c.append("\nClicking on... " + invAction);
				                
				                Object actItem = leftArmWear+1;
				                
				                boolean  isDropping = invAction.startsWith("Drop");
				                boolean  isUnequipping = invAction.startsWith("Unequip");
				                
				                String rightclickDrop = getItem(actItem.toString())[8];
				                
				                int rightStrDrop = Integer.parseInt(rightclickDrop);
								
				                if (isDropping) {
									c.append("\nYou unequipped and dropped a " + getItem(actItem.toString())[0] + ".");
									dropItemNow(rightStrDrop, spawnX, spawnY);
									btLeftArmIcon.setIcon(new ImageIcon(other[0])); // NOI18N
						            btLeftArmIcon.setToolTipText("N/A");
						            subAttack(Integer.parseInt(getItem(actItem.toString())[3]));
									leftArmWear = 0;
									repaint();
								}
				                
				                if (isUnequipping) {
									c.append("\nYou unequipped your " + getItem(actItem.toString())[0] + ".");
									btLeftArmIcon.setIcon(new ImageIcon(other[0])); // NOI18N
						            btLeftArmIcon.setToolTipText("N/A");
						            model.addElement(getItem(actItem.toString())[0]);
						            subAttack(Integer.parseInt(getItem(actItem.toString())[3]));
									leftArmWear = 0;
									repaint();
								}
				                
				                
				            }
				        };


				   useUsePopup = new JPopupMenu();
				   useDropPopup = new JPopupMenu();
				   useCancelPopup = new JPopupMenu();
				   useUnequipPopup = new JPopupMenu();
				   useWearPopup = new JPopupMenu();

				   leftArmPopup = new JPopupMenu();

				   useMenuItem = new JMenuItem("Use");
				   useMenuItem.addActionListener(leftArmListener);
				   
				   unequipMenuItem = new JMenuItem("Unequip");
				   unequipMenuItem.addActionListener(leftArmListener);

				   dropMenuItem = new JMenuItem("Drop");
				   dropMenuItem.addActionListener(leftArmListener);

				   cancelMenuItem = new JMenuItem("Cancel");
				   cancelMenuItem.addActionListener(leftArmListener);
				
				
							
				try {
					dataNow = new Gson().fromJson(fileNow, Data.class);
					}catch (Exception er) {
						er.printStackTrace();
					}
					
	                  String inputFileLocation = System.getenv("APPDATA") + "\\.mystik\\items.gif";
	                  BufferedImage originalImage = readImage(inputFileLocation);
	                  other = new Image[NUM_OTHS];
	                  
	                  try {
	                  for (int xi = 0; xi < NUM_OTHS; xi++) {                	  
	                      int cropHeight = 32;
	                      int cropWidth = 32;
	                      int cropStartX = xi*32;
	                      int cropStartY = 32;
	                      
	                  BufferedImage processedImage = cropMyImage(originalImage, cropWidth, cropHeight, cropStartX, cropStartY);
	                  other[xi] = processedImage;
	            }
	                  }catch(Exception loadCrop) {
	                	  loadCrop.printStackTrace();
	                  }
					
		            pnPanel0 = new JPanel();
		            pnPanel0.setBackground(Color.RED);
		            pnPanel0.setOpaque(false);
		            GridBagLayout gbPanel0 = new GridBagLayout();
		            
		            GridBagConstraints gbcPanel0 = new GridBagConstraints();
		            pnPanel0.setLayout( gbPanel0 );
		            
		            //gbcPanel0.ipady = 35;
		  

		            pnPanel3 = new JPanel();
		            pnPanel3.setBackground(Color.YELLOW);
		            pnPanel3.setOpaque(false);
		            GridBagLayout gbPanel3 = new GridBagLayout();
		            GridBagConstraints gbcPanel3 = new GridBagConstraints();
		            pnPanel3.setLayout( gbPanel3 );

		            pnHeadInfoPanel = new JPanel();
		            pnHeadInfoPanel.setBorder( BorderFactory.createTitledBorder( "Head" ) );
		            pnHeadInfoPanel.setOpaque(false);
		            
		            GridBagLayout gbHeadInfoPanel = new GridBagLayout();
		            GridBagConstraints gbcHeadInfoPanel = new GridBagConstraints();
		            gbcHeadInfoPanel.weightx = 11;
		            gbcHeadInfoPanel.weighty = 11;
		            pnHeadInfoPanel.setLayout( gbHeadInfoPanel );

		            btHeadIcon = new JButton( ""  );
		            btHeadIcon.setIcon(new ImageIcon(other[0])); // NOI18N
		            btHeadIcon.setToolTipText("N/A");
		            btHeadIcon.setBorderPainted(false);
		            btHeadIcon.setDefaultCapable(false);
		            btHeadIcon.setDoubleBuffered(true);
		            btHeadIcon.setFocusPainted(false);
		            btHeadIcon.setIconTextGap(0);
		            btHeadIcon.setBackground(Color.white); 
		            gbcHeadInfoPanel.gridx = 0;
		            gbcHeadInfoPanel.gridy = 0;
		            gbcHeadInfoPanel.gridwidth = 3;
		            gbcHeadInfoPanel.gridheight = 3;
		            gbcHeadInfoPanel.fill = GridBagConstraints.BOTH;
		            gbcHeadInfoPanel.weightx = 1;
		            gbcHeadInfoPanel.weighty = 0;
		            gbcHeadInfoPanel.anchor = GridBagConstraints.NORTH;
		            gbHeadInfoPanel.setConstraints( btHeadIcon, gbcHeadInfoPanel );
		            pnHeadInfoPanel.add( btHeadIcon );
		            gbcPanel3.gridx = 1;
		            gbcPanel3.gridy = 4;
		            gbcPanel3.gridwidth = 3;
		            gbcPanel3.gridheight = 3;
		            gbcPanel3.fill = GridBagConstraints.BOTH;
		            gbcPanel3.weightx = 1;
		            gbcPanel3.weighty = 0;
		            gbcPanel3.insets = new Insets( 0,5,5,5 );
		            gbcPanel3.anchor = GridBagConstraints.NORTH;
		            gbPanel3.setConstraints( pnHeadInfoPanel, gbcPanel3 );
		            pnPanel3.add( pnHeadInfoPanel );

		            lbLabel0 = new JLabel( "Character Information",JLabel.CENTER);
		            gbcPanel3.gridx = 1;
		            gbcPanel3.gridy = 1;
		            gbcPanel3.gridwidth = 18;
		            gbcPanel3.gridheight = 2;
		            gbcPanel3.fill = GridBagConstraints.BOTH;
		            gbcPanel3.weightx = 1;
		            gbcPanel3.weighty = 1;
		            gbcPanel3.anchor = GridBagConstraints.CENTER;
		            gbPanel3.setConstraints( lbLabel0, gbcPanel3 );
		            pnPanel3.add( lbLabel0 );

		            pnBodyInfoPanel = new JPanel();
		            pnBodyInfoPanel.setBorder( BorderFactory.createTitledBorder( "Body" ) );
		            pnBodyInfoPanel.setOpaque(false);
		            GridBagLayout gbBodyInfoPanel = new GridBagLayout();
		            GridBagConstraints gbcBodyInfoPanel = new GridBagConstraints();
		            pnBodyInfoPanel.setLayout( gbBodyInfoPanel );

		            btBodyIcon = new JButton( ""  );
		            btBodyIcon.setIcon(new ImageIcon(other[0])); // NOI18N
		            btBodyIcon.setToolTipText("N/A");
		            btBodyIcon.setBorderPainted(false);
		            btBodyIcon.setDefaultCapable(false);
		            btBodyIcon.setDoubleBuffered(true);
		            btBodyIcon.setFocusPainted(false);
		            btBodyIcon.setIconTextGap(0);
		            btBodyIcon.setBackground(Color.white); 
		            gbcBodyInfoPanel.gridx = 0;
		            gbcBodyInfoPanel.gridy = 0;
		            gbcBodyInfoPanel.gridwidth = 3;
		            gbcBodyInfoPanel.gridheight = 3;
		            gbcBodyInfoPanel.fill = GridBagConstraints.BOTH;
		            gbcBodyInfoPanel.weightx = 1;
		            gbcBodyInfoPanel.weighty = 0;
		            gbcBodyInfoPanel.anchor = GridBagConstraints.NORTH;
		            gbBodyInfoPanel.setConstraints( btBodyIcon, gbcBodyInfoPanel );
		            pnBodyInfoPanel.add( btBodyIcon );
		            gbcPanel3.gridx = 1;
		            gbcPanel3.gridy = 8;
		            gbcPanel3.gridwidth = 3;
		            gbcPanel3.gridheight = 3;
		            gbcPanel3.fill = GridBagConstraints.BOTH;
		            gbcPanel3.weightx = 1;
		            gbcPanel3.weighty = 0;
		            gbcPanel3.anchor = GridBagConstraints.NORTH;
		            gbPanel3.setConstraints( pnBodyInfoPanel, gbcPanel3 );
		            pnPanel3.add( pnBodyInfoPanel );

		            pnLeftArmInfoPanel = new JPanel();
		            pnLeftArmInfoPanel.setBorder( BorderFactory.createTitledBorder( "Left Arm" ) );
		            pnLeftArmInfoPanel.setOpaque(false);
		            GridBagLayout gbLeftArmInfoPanel = new GridBagLayout();
		            GridBagConstraints gbcLeftArmInfoPanel = new GridBagConstraints();
		            pnLeftArmInfoPanel.setLayout( gbLeftArmInfoPanel );

		            btLeftArmIcon = new JButton( ""  );
		            btLeftArmIcon.setIcon(new ImageIcon(other[0])); // NOI18N
		            btLeftArmIcon.setToolTipText("N/A");
		            btLeftArmIcon.setBorderPainted(false);
		            btLeftArmIcon.setDefaultCapable(false);
		            btLeftArmIcon.setDoubleBuffered(true);
		            btLeftArmIcon.setFocusPainted(false);
		            btLeftArmIcon.setIconTextGap(0);
		            btLeftArmIcon.setBackground(Color.white); 
		            btLeftArmIcon.addMouseListener(new LeftArmListener());
		            gbcLeftArmInfoPanel.gridx = 0;
		            gbcLeftArmInfoPanel.gridy = 0;
		            gbcLeftArmInfoPanel.gridwidth = 3;
		            gbcLeftArmInfoPanel.gridheight = 3;
		            gbcLeftArmInfoPanel.fill = GridBagConstraints.BOTH;
		            gbcLeftArmInfoPanel.weightx = 1;
		            gbcLeftArmInfoPanel.weighty = 0;
		            gbcLeftArmInfoPanel.anchor = GridBagConstraints.NORTH;
		            gbLeftArmInfoPanel.setConstraints( btLeftArmIcon, gbcLeftArmInfoPanel );
		            pnLeftArmInfoPanel.add( btLeftArmIcon );
		            gbcPanel3.gridx = 5;
		            gbcPanel3.gridy = 8;
		            gbcPanel3.gridwidth = 3;
		            gbcPanel3.gridheight = 3;
		            gbcPanel3.fill = GridBagConstraints.BOTH;
		            gbcPanel3.weightx = 1;
		            gbcPanel3.weighty = 0;
		            gbcPanel3.anchor = GridBagConstraints.NORTH;
		            gbPanel3.setConstraints( pnLeftArmInfoPanel, gbcPanel3 );
		            pnPanel3.add( pnLeftArmInfoPanel );

		            pnRightArmInfoPanel = new JPanel();
		            pnRightArmInfoPanel.setBorder( BorderFactory.createTitledBorder( "Right Arm" ) );
		            pnRightArmInfoPanel.setOpaque(false);
		            GridBagLayout gbRightArmInfoPanel = new GridBagLayout();
		            GridBagConstraints gbcRightArmInfoPanel = new GridBagConstraints();
		            pnRightArmInfoPanel.setLayout( gbRightArmInfoPanel );

		            btRightArmIcon = new JButton( ""  );
		            btRightArmIcon.setIcon(new ImageIcon(other[0])); // NOI18N
		            btRightArmIcon.setToolTipText("N/A");
		            btRightArmIcon.setBorderPainted(false);
		            btRightArmIcon.setDefaultCapable(false);
		            btRightArmIcon.setDoubleBuffered(true);
		            btRightArmIcon.setFocusPainted(false);
		            btRightArmIcon.setIconTextGap(0);
		            btRightArmIcon.setBackground(Color.white); 
		            gbcRightArmInfoPanel.gridx = 0;
		            gbcRightArmInfoPanel.gridy = 0;
		            gbcRightArmInfoPanel.gridwidth = 3;
		            gbcRightArmInfoPanel.gridheight = 3;
		            gbcRightArmInfoPanel.fill = GridBagConstraints.BOTH;
		            gbcRightArmInfoPanel.weightx = 1;
		            gbcRightArmInfoPanel.weighty = 0;
		            gbcRightArmInfoPanel.anchor = GridBagConstraints.NORTH;
		            gbRightArmInfoPanel.setConstraints( btRightArmIcon, gbcRightArmInfoPanel );
		            pnRightArmInfoPanel.add( btRightArmIcon );
		            gbcPanel3.gridx = 5;
		            gbcPanel3.gridy = 12;
		            gbcPanel3.gridwidth = 3;
		            gbcPanel3.gridheight = 3;
		            gbcPanel3.fill = GridBagConstraints.BOTH;
		            gbcPanel3.weightx = 1;
		            gbcPanel3.weighty = 0;
		            gbcPanel3.anchor = GridBagConstraints.NORTH;
		            gbPanel3.setConstraints( pnRightArmInfoPanel, gbcPanel3 );
		            pnPanel3.add( pnRightArmInfoPanel );

		            pnLegsInfoPanel = new JPanel();
		            pnLegsInfoPanel.setBorder( BorderFactory.createTitledBorder( "Legs" ) );
		            pnLegsInfoPanel.setOpaque(false);
		            GridBagLayout gbLegsInfoPanel = new GridBagLayout();
		            GridBagConstraints gbcLegsInfoPanel = new GridBagConstraints();
		            pnLegsInfoPanel.setLayout( gbLegsInfoPanel );

		            btLegsIcon = new JButton( ""  );
		            btLegsIcon.setIcon(new ImageIcon(other[0])); // NOI18N
		            btLegsIcon.setToolTipText("derp");
		            btLegsIcon.setBorderPainted(false);
		            btLegsIcon.setDefaultCapable(false);
		            btLegsIcon.setDoubleBuffered(true);
		            btLegsIcon.setFocusPainted(false);
		            btLegsIcon.setIconTextGap(0);
		            btLegsIcon.setBackground(Color.white); 
		            gbcLegsInfoPanel.gridx = 0;
		            gbcLegsInfoPanel.gridy = 0;
		            gbcLegsInfoPanel.gridwidth = 3;
		            gbcLegsInfoPanel.gridheight = 3;
		            gbcLegsInfoPanel.fill = GridBagConstraints.BOTH;
		            gbcLegsInfoPanel.weightx = 1;
		            gbcLegsInfoPanel.weighty = 0;
		            gbcLegsInfoPanel.anchor = GridBagConstraints.NORTH;
		            gbLegsInfoPanel.setConstraints( btLegsIcon, gbcLegsInfoPanel );
		            pnLegsInfoPanel.add( btLegsIcon );
		            gbcPanel3.gridx = 1;
		            gbcPanel3.gridy = 12;
		            gbcPanel3.gridwidth = 3;
		            gbcPanel3.gridheight = 3;
		            gbcPanel3.fill = GridBagConstraints.BOTH;
		            gbcPanel3.weightx = 1;
		            gbcPanel3.weighty = 0;
		            gbcPanel3.anchor = GridBagConstraints.NORTH;
		            gbPanel3.setConstraints( pnLegsInfoPanel, gbcPanel3 );
		            pnPanel3.add( pnLegsInfoPanel );

		            pbExpBar = new JProgressBar( );
		            pbExpBar.setValue(90);
		            pbExpBar.setStringPainted(true);
		            gbcPanel3.gridx = 1;
		            gbcPanel3.gridy = 17;
		            gbcPanel3.gridwidth = 13;
		            gbcPanel3.gridheight = 2;
		            gbcPanel3.fill = GridBagConstraints.BOTH;
		            gbcPanel3.weightx = 1;
		            gbcPanel3.weighty = 0;
		            gbcPanel3.anchor = GridBagConstraints.NORTH;
		            gbPanel3.setConstraints( pbExpBar, gbcPanel3 );
		            pnPanel3.add( pbExpBar );

		            pnPanel12 = new JPanel();
		            pnPanel12.setPreferredSize(new Dimension(115,200));
		            pnPanel12.setBorder( BorderFactory.createTitledBorder( "Information" ) );
		            pnPanel12.setOpaque(false);
		            GridBagLayout gbPanel12 = new GridBagLayout();
		            GridBagConstraints gbcPanel12 = new GridBagConstraints();
		            pnPanel12.setLayout( gbPanel12 );

		            lbLabel2 = new JLabel( "Level:"  );
		            gbcPanel12.gridx = 1;
		            gbcPanel12.gridy = 1;
		            gbcPanel12.gridwidth = 3;
		            gbcPanel12.gridheight = 1;
		            gbcPanel12.fill = GridBagConstraints.BOTH;
		            gbcPanel12.weightx = 1;
		            gbcPanel12.weighty = 1;
		            gbcPanel12.anchor = GridBagConstraints.NORTH;
		            gbPanel12.setConstraints( lbLabel2, gbcPanel12 );
		            pnPanel12.add( lbLabel2 );

		            lbLabel3 = new JLabel( "EXP:"  );
		            gbcPanel12.gridx = 1;
		            gbcPanel12.gridy = 2;
		            gbcPanel12.gridwidth = 3;
		            gbcPanel12.gridheight = 1;
		            gbcPanel12.fill = GridBagConstraints.BOTH;
		            gbcPanel12.weightx = 1;
		            gbcPanel12.weighty = 1;
		            gbcPanel12.anchor = GridBagConstraints.NORTH;
		            gbPanel12.setConstraints( lbLabel3, gbcPanel12 );
		            pnPanel12.add( lbLabel3 );

		            lbLabel4 = new JLabel( "EXP left:    "  );
		            gbcPanel12.gridx = 1;
		            gbcPanel12.gridy = 3;
		            gbcPanel12.gridwidth = 3;
		            gbcPanel12.gridheight = 1;
		            gbcPanel12.fill = GridBagConstraints.BOTH;
		            gbcPanel12.weightx = 1;
		            gbcPanel12.weighty = 1;
		            gbcPanel12.anchor = GridBagConstraints.NORTH;
		            gbPanel12.setConstraints( lbLabel4, gbcPanel12 );
		            pnPanel12.add( lbLabel4 );

		            lbLabel5 = new JLabel( "Gold:"  );
		            gbcPanel12.gridx = 1;
		            gbcPanel12.gridy = 5;
		            gbcPanel12.gridwidth = 3;
		            gbcPanel12.gridheight = 1;
		            gbcPanel12.fill = GridBagConstraints.BOTH;
		            gbcPanel12.weightx = 1;
		            gbcPanel12.weighty = 1;
		            gbcPanel12.anchor = GridBagConstraints.NORTH;
		            gbPanel12.setConstraints( lbLabel5, gbcPanel12 );
		            pnPanel12.add( lbLabel5 );

		            lbLabel6 = new JLabel( "Steps:"  );
		            gbcPanel12.gridx = 1;
		            gbcPanel12.gridy = 6;
		            gbcPanel12.gridwidth = 3;
		            gbcPanel12.gridheight = 1;
		            gbcPanel12.fill = GridBagConstraints.BOTH;
		            gbcPanel12.weightx = 1;
		            gbcPanel12.weighty = 1;
		            gbcPanel12.anchor = GridBagConstraints.NORTH;
		            gbPanel12.setConstraints( lbLabel6, gbcPanel12 );
		            pnPanel12.add( lbLabel6 );

		            lbLabel7 = new JLabel( "Kills:"  );
		            gbcPanel12.gridx = 1;
		            gbcPanel12.gridy = 8;
		            gbcPanel12.gridwidth = 3;
		            gbcPanel12.gridheight = 1;
		            gbcPanel12.fill = GridBagConstraints.BOTH;
		            gbcPanel12.weightx = 1;
		            gbcPanel12.weighty = 1;
		            gbcPanel12.anchor = GridBagConstraints.NORTH;
		            gbPanel12.setConstraints( lbLabel7, gbcPanel12 );
		            pnPanel12.add( lbLabel7 );

		            lbLabel8 = new JLabel( "Deaths:"  );
		            gbcPanel12.gridx = 1;
		            gbcPanel12.gridy = 9;
		            gbcPanel12.gridwidth = 3;
		            gbcPanel12.gridheight = 1;
		            gbcPanel12.fill = GridBagConstraints.BOTH;
		            gbcPanel12.weightx = 1;
		            gbcPanel12.weighty = 1;
		            gbcPanel12.anchor = GridBagConstraints.NORTH;
		            gbPanel12.setConstraints( lbLabel8, gbcPanel12 );
		            pnPanel12.add( lbLabel8 );

		            lbLevelInfoLabel = new JLabel( "0"  );
		            gbcPanel12.gridx = 4;
		            gbcPanel12.gridy = 1;
		            gbcPanel12.gridwidth = 5;
		            gbcPanel12.gridheight = 1;
		            gbcPanel12.fill = GridBagConstraints.BOTH;
		            gbcPanel12.weightx = 1;
		            gbcPanel12.weighty = 1;
		            gbcPanel12.anchor = GridBagConstraints.NORTH;
		            gbPanel12.setConstraints( lbLevelInfoLabel, gbcPanel12 );
		            pnPanel12.add( lbLevelInfoLabel );

		            lbExpInfoLabel = new JLabel( "0"  );
		            gbcPanel12.gridx = 4;
		            gbcPanel12.gridy = 2;
		            gbcPanel12.gridwidth = 5;
		            gbcPanel12.gridheight = 1;
		            gbcPanel12.fill = GridBagConstraints.BOTH;
		            gbcPanel12.weightx = 1;
		            gbcPanel12.weighty = 1;
		            gbcPanel12.anchor = GridBagConstraints.NORTH;
		            gbPanel12.setConstraints( lbExpInfoLabel, gbcPanel12 );
		            pnPanel12.add( lbExpInfoLabel );

		            lbExpLeftInfoLabel = new JLabel( "0"  );
		            gbcPanel12.gridx = 4;
		            gbcPanel12.gridy = 3;
		            gbcPanel12.gridwidth = 5;
		            gbcPanel12.gridheight = 1;
		            gbcPanel12.fill = GridBagConstraints.BOTH;
		            gbcPanel12.weightx = 1;
		            gbcPanel12.weighty = 1;
		            gbcPanel12.anchor = GridBagConstraints.NORTH;
		            gbPanel12.setConstraints( lbExpLeftInfoLabel, gbcPanel12 );
		            pnPanel12.add( lbExpLeftInfoLabel );

		            lbGoldInfoLabel = new JLabel( "0"  );
		            gbcPanel12.gridx = 4;
		            gbcPanel12.gridy = 5;
		            gbcPanel12.gridwidth = 5;
		            gbcPanel12.gridheight = 1;
		            gbcPanel12.fill = GridBagConstraints.BOTH;
		            gbcPanel12.weightx = 1;
		            gbcPanel12.weighty = 1;
		            gbcPanel12.anchor = GridBagConstraints.NORTH;
		            gbPanel12.setConstraints( lbGoldInfoLabel, gbcPanel12 );
		            pnPanel12.add( lbGoldInfoLabel );

		            lbStepsInfoLabel = new JLabel( "0"  );
		            gbcPanel12.gridx = 4;
		            gbcPanel12.gridy = 6;
		            gbcPanel12.gridwidth = 5;
		            gbcPanel12.gridheight = 1;
		            gbcPanel12.fill = GridBagConstraints.BOTH;
		            gbcPanel12.weightx = 1;
		            gbcPanel12.weighty = 1;
		            gbcPanel12.anchor = GridBagConstraints.NORTH;
		            gbPanel12.setConstraints( lbStepsInfoLabel, gbcPanel12 );
		            pnPanel12.add( lbStepsInfoLabel );

		            lbKillsInfoLabel = new JLabel( "0"  );
		            gbcPanel12.gridx = 4;
		            gbcPanel12.gridy = 8;
		            gbcPanel12.gridwidth = 5;
		            gbcPanel12.gridheight = 1;
		            gbcPanel12.fill = GridBagConstraints.BOTH;
		            gbcPanel12.weightx = 1;
		            gbcPanel12.weighty = 1;
		            gbcPanel12.anchor = GridBagConstraints.NORTH;
		            gbPanel12.setConstraints( lbKillsInfoLabel, gbcPanel12 );
		            pnPanel12.add( lbKillsInfoLabel );

		            lbDeathsInfoLabel = new JLabel( "0"  );
		            gbcPanel12.gridx = 4;
		            gbcPanel12.gridy = 9;
		            gbcPanel12.gridwidth = 5;
		            gbcPanel12.gridheight = 1;
		            gbcPanel12.fill = GridBagConstraints.BOTH;
		            gbcPanel12.weightx = 1;
		            gbcPanel12.weighty = 1;
		            gbcPanel12.anchor = GridBagConstraints.NORTH;
		            gbPanel12.setConstraints( lbDeathsInfoLabel, gbcPanel12 );
		            pnPanel12.add( lbDeathsInfoLabel );
		            gbcPanel3.gridx = 9;
		            gbcPanel3.gridy = 4;
		            gbcPanel3.gridwidth = 10;
		            gbcPanel3.gridheight = 11;
		            gbcPanel3.fill = GridBagConstraints.BOTH;
		            gbcPanel3.weightx = 1;
		            gbcPanel3.weighty = 0;
		            gbcPanel3.anchor = GridBagConstraints.NORTH;
		            gbPanel3.setConstraints( pnPanel12, gbcPanel3 );
		            pnPanel3.add( pnPanel12 );

		            btInfoOK = new JButton( "OK"  );
		            gbcPanel3.gridx = 15;
		            gbcPanel3.gridy = 17;
		            gbcPanel3.gridwidth = 4;
		            gbcPanel3.gridheight = 2;
		            gbcPanel3.fill = GridBagConstraints.BOTH;
		            gbcPanel3.weightx = 1;
		            gbcPanel3.weighty = 0;
		            gbcPanel3.anchor = GridBagConstraints.NORTH;
		            gbPanel3.setConstraints( btInfoOK, gbcPanel3 );
		            pnPanel3.add( btInfoOK );
		            gbcPanel0.gridx = 0;
		            gbcPanel0.gridy = 0;
		            gbcPanel0.gridwidth = 20;
		            gbcPanel0.gridheight = 20;
		            gbcPanel0.fill = GridBagConstraints.BOTH;
		            gbcPanel0.weightx = 1;
		            gbcPanel0.weighty = 0;
		            gbcPanel0.anchor = GridBagConstraints.NORTH;
		            gbPanel0.setConstraints( pnPanel3, gbcPanel0 );
		            pnPanel0.add( pnPanel3 );

		            

		            // CHARACTER SHEET
					headIcon = new JButton();
					JButton closedChar = new JButton("Close Character Sheet");
					head = new JLabel("Head");
					titleChar = new JLabel("Character Information");

					pnPanel0.setPreferredSize(new Dimension(300, 285));
					charPanel.add(pnPanel0);

				    
				    closedChar.addActionListener(new ActionListener()
			    {
			      public void actionPerformed(ActionEvent e)
			      {
						c.append("\nYou closed the Character Sheet.");
						charPanel.setVisible(false);
						charOpen = false;
			      }
			    });

		            charPanel.setPreferredSize(new Dimension(360, 300));
					charPanel.setBackground(Color.WHITE);
					charPanel.setVisible(false);

			        
					// END CHARACTER SHEET
				
				for (int ji = 0; ji < howManyFiles; ji++) {
					
					
					
					//c.append("\nfilename[qi]: " + filename[ji]);
					if (filename[ji].equals("main.txt")) {
						System.out.println("Main detected....");
						c.append("main.txt detected! Loading game bundle... ");
						
						mapToLoad = dataNow.getIDS().get(0).getCurrentMap();

						data.setTitle(dataNow.getTitle());
						data.getIDS().get(0).setMapID(dataNow.getIDS().get(0).getCurrentMap());
						
						
								
						
						//data.setMap(board);
						
						//c.append("\nji:" +ji + " filename 1: x" + filename[ji] + " VS  main.txt. You're in -- " + data.getTitle());
									try {
							
										
				   for (int row = 0; row < dataNow.getMap().length; row++) {
					  
					      for (int col = 0; col < dataNow.getMap()[row].length; col++)
					      {
					        int index = dataNow.getMap()[row][col];
					        //System.out.println(index);
					        //System.out.println("data.getCurrentMap() - " + data.getCurrentMap());
					        
					        	
					        		board[row][col] = index;
					        	
					        
					      }

					    }
				}catch(Exception rere) { 
					
					rere.printStackTrace(); }
					//board = loadBoard(7); pushBoard = loadPush(7); }
						
					}
					} 
			
				


			
			// add Monsters to game
			// ID, X, Y, Map

		    //addMonster(3, 4, 4, 6);



			//addEntrance(10,8,166,1);

			// addTeleport(int fromX, int fromY, int fromMap, int toX, int toY, int toMap) {
			

}


	
			currentMap = mapToLoad;
			spawnY = 7;
			spawnX = 2;

				username = "Guest";
			



			extraSpace1 = new JLabel("--------", JLabel.LEFT);
			extraSpace2 = new JLabel("--------", JLabel.LEFT);
			extraSpace3 = new JLabel("--------", JLabel.LEFT);
			extraSpace4 = new JLabel("--------", JLabel.LEFT);
			extraSpace5 = new JLabel("--------", JLabel.LEFT);
			extraSpace6 = new JLabel("--------", JLabel.LEFT);

			Border emptyBorder = BorderFactory.createEmptyBorder(5, 5, 0, 0);
			statsPanel.setBorder(emptyBorder);


			lName = new JLabel("["+username+"]", JLabel.CENTER);
			lHealth = new JLabel("Health ["+currhp + "/" + maxhp+"]", JLabel.LEFT);
			lLevel = new JLabel("Level ["+level+"]", JLabel.LEFT);
			lExp = new JLabel("EXP ["+exp+"]", JLabel.LEFT);
			lAttack = new JLabel("Attack ["+attack+"]", JLabel.LEFT);
			lDefence = new JLabel("Defense ["+defense+"]", JLabel.LEFT);

			statsPanel.add(lName);

			statsPanel.add(lHealth);

			statsPanel.add(lLevel);

			statsPanel.add(lExp);

			statsPanel.add(lAttack);

			statsPanel.add(lDefence);

			/**
			lName = new JLabel("Name", JLabel.LEFT);
			lN2 = new JLabel(username, JLabel.LEFT);
			lHealth = new JLabel("Health", JLabel.LEFT);
			lH2 = new JLabel(currhp + "/" + maxhp, JLabel.LEFT);
			lLevel = new JLabel("Level", JLabel.LEFT);
			lL2 = new JLabel(""+level, JLabel.LEFT);
			lExp = new JLabel("EXP", JLabel.LEFT);
			lE2 = new JLabel(""+exp, JLabel.LEFT);
			lAttack = new JLabel("Attack", JLabel.LEFT);
			lA2 = new JLabel(""+attack, JLabel.LEFT);
			lDefence = new JLabel("Defense", JLabel.LEFT);
			lD2 = new JLabel(""+defense, JLabel.LEFT);



            statsPanel.add(lName);
           statsPanel.add(lN2);

            statsPanel.add(lHealth);
            statsPanel.add(lH2);

            statsPanel.add(lLevel);
            statsPanel.add(lL2);

            statsPanel.add(lExp);
            statsPanel.add(lE2);

            statsPanel.add(lAttack);
            statsPanel.add(lA2);

            statsPanel.add(lDefence);
            statsPanel.add(lD2);
**/




           //statsNow.add(statsLabel);

            game.setOpaque(false);

            // game.setBounds(0, 0, 416, 288);

            c.append("\nWelcome to Mystik, " + username + "!");
            //addSign(1,8,"<span style='color:red;'>HERP</span> a derp~",1);

            //this.setFocusable(true);
            addMouseListener(this);

            try {
                  tiles = new Image[NUM_TILES];
                  File[] filea = new File[NUM_TILES];
                  
 
                  
/**
                 DO THIS FOR WHOLE IMGE LOAD 
                  for (int xi = 0; xi < NUM_TILES; xi++) {                	  
                      int cropHeight = 32;
                      int cropWidth = 32;
                      int cropStartX = (xi%36)*32;
                      int cropStartY = xi/36*32;
                      
                      
                  BufferedImage processedImage = cropMyImage(originalImage, cropWidth, cropHeight, cropStartX, cropStartY);
                  tiles[xi] = processedImage;
            }
                  
                  for (int i = 0; i < NUM_TILES; i++) {

					  		filea[i] = new File(System.getenv("APPDATA") + "\\.mystik\\tiles\\t"+i+".png");
					  		tiles[i] = ImageIO.read(filea[i]);

					  //tiles[i] = tileGen.class.getResource(String.format("line_tile/t%d.png", i));
                      //tiles[i] = ImageIO.read(getClass().getResource(String.format("line_tile/t%d.png", i)));
                  } **/
                  
                  for (int i = 0; i < NUM_TILES; i++) {
					  Toolkit tk = this.getToolkit();
					  		tiles[i] = tk.getImage(this.getClass().getResource(String.format("tiles/t%d.png", i)));
					  		}

                  weapon = new Image[NUM_WEAPS];
                  /**for (int xi = 0; xi < NUM_WEAPS; xi++) {
                        weapon[xi] = ImageIO.read(getClass().getResource(
                                    String.format("weapon/w%d.gif", xi)));
                  }**/
                  
                  
           
                  String inputFileLocation = System.getenv("APPDATA") + "\\.mystik\\items.gif";
                  BufferedImage originalImage = readImage(inputFileLocation);
                  
              
                  
             

                  for (int xi = 0; xi < NUM_WEAPS; xi++) {                	  
                      int cropHeight = 32;
                      int cropWidth = 32;
                      int cropStartX = xi*32;
                      int cropStartY = 0;
                      
                  BufferedImage processedImage = cropMyImage(originalImage, cropWidth, cropHeight, cropStartX, cropStartY);
                  weapon[xi] = processedImage;
            }
                  
                  

                  


                                
                  inputFileLocation = System.getenv("APPDATA") + "\\.mystik\\monsters.gif";
                  originalImage = readImage(inputFileLocation);
                  
                  

                  monster = new Image[NUM_MONS];
                  
                  for (int xi = 0; xi < NUM_MONS; xi++) {                	  
                      int cropHeight = 32;
                      int cropWidth = 32;
                      int cropStartX = xi*32;
                      int cropStartY = 0;
                      
                  BufferedImage processedImage = cropMyImage(originalImage, cropWidth, cropHeight, cropStartX, cropStartY);
                  monster[xi] = processedImage;
            }
                  
            } catch (Exception ex) {
                  ex.printStackTrace();
            }

            // demo
            // player = getImage(getClass().getResource("me.gif")); // our player
            game.addKeyListener(this);
            canMove = true;

            //String txtY = Integer.toString(spawnY);
            //String txtX = Integer.toString(spawnX);

            py = spawnY * 32;
            px = spawnX * 32;

            lastY = spawnY;
            lastX = spawnX;

            //this.setFocusable(true);
            //game.setFocusable(true);
            //c.setFocusable(false);

            invOne.setVisible(true);

            // blkStr = board[5][5];
            // //System.out.println("Teh black one: " +blkStr);
            // board[5][5] = 26;

			labelStatus = new JLabel("Sorry, the game crashed!");

			labelStatus.setForeground(Color.WHITE.brighter());
			statusPanel = new JPanel();
			statusPanel.setBackground(Color.RED.darker());
			statusPanel.add(labelStatus);
			statusPanel.setPreferredSize(new Dimension(611,511));
			statusPanel.setOpaque(false);
		    labelStatus.setVisible(false);




            labelUser = new JLabel("Guest");
            labelX = new JLabel("X: 10");
            labelY = new JLabel("Y: 6");
            
            
            String loadMapText;
            
            if (exists) {
            	labelRoom = new JLabel(data.getTitle());
            	loadMapText = "Map successfully loaded via <br />" + System.getenv("APPDATA") + "\\.mystik\\maps\\main.txt";
			  }else{
				  labelRoom = new JLabel("Main");
				  loadMapText = "Map successfully created at <br />" + System.getenv("APPDATA") + "\\.mystik\\maps\\main.txt";
			  }
            
            String html = "<html><h1 style='font: 15pt verdana;font-weight:bold; color:yellow;'>Welcome to Mystik, Guest!</h1>"
			  + "Thanks to <span style='color:yellow;font;weight:bold;'>David E Gervais</span> for his all-mighty graphics. He has made"
			  + "<br />pretty much everything you see, from tiles to weapons to monsters!"
			 // + "<br /><br />Special thanks to <span style='color:yellow;font;weight:bold;'>Melissa Seifman</span>, who introduced me to Java, "
			  + "<br /><br />Thanks to everyone on <span style='color:yellow;font;weight:bold;'>reddit</span> who've given me constructive <br />criticism and <span style='color:yellow;font;weight:bold;'>Stack Overflow</span> who've answered all of my questions."
			  + "<br /><br />v2.1 brings multiple map support, bug fixes, signs, and more! <br />Read blog for more details. http://blog.mystikrpg.com"
			  + "<br /><br />" + loadMapText
			  + "<br /><br />Press any arrow key to continue!</html>";
            introLabel = new JLabel(html);
            signLabel = new JLabel(signhtml);




			labelUser.setForeground(Color.WHITE);
			userPanel = new JPanel();
			userPanel.setBackground(Color.GREEN.darker());
			userPanel.add(labelUser);
			game.add(userPanel);

            labelX.setForeground(Color.BLACK);
			xPanel = new JPanel();
			xPanel.setBackground(Color.YELLOW.darker());
			xPanel.add(labelX);
			game.add(xPanel);

            labelY.setForeground(Color.RED.darker());
			yPanel = new JPanel();
			yPanel.setBackground(Color.WHITE);
			yPanel.add(labelY);
			game.add(yPanel);

            labelRoom.setForeground(Color.WHITE.brighter());
			roomPanel = new JPanel();
			roomPanel.setBackground(Color.BLACK.brighter());
			roomPanel.add(labelRoom);
			game.add(roomPanel);

			game.add(charPanel);
			
			signPanel = new JPanel();
			signLabel.setForeground(Color.WHITE.brighter());
			//introLabel_two.setForeground(Color.WHITE.darker());

			signPanel.setBackground(new Color(0,0,0,95));
			signPanel.add(signLabel);
			//introPanel.add(introLabel_two);

			signPanel.setPreferredSize(new Dimension(400,240));
			game.add(signPanel);
			signPanel.setVisible(false);

			introPanel = new JPanel();
			introLabel.setForeground(Color.WHITE.brighter());
			//introLabel_two.setForeground(Color.WHITE.darker());

			introPanel.setBackground(new Color(0,0,0,95));
			introPanel.add(introLabel);
			//introPanel.add(introLabel_two);

			introPanel.setPreferredSize(new Dimension(400,240));
			game.add(introPanel);
			introPanel.setVisible(true);

			gameOn = new JPanel();
			gameOn.setBackground(Color.BLACK.brighter());
			htmlx = "";
			theUser = new JLabel(htmlx);
			gameOn.setPreferredSize(new Dimension(390,200));
			gameOn.add(theUser);
			game.add(gameOn);
			gameOn.setOpaque(false);
			theUser.setVisible(false);


            game.requestFocusInWindow();


			addtoInv("4");
			addtoInv("6");

			
			
						

            repaint();
try {
            playerLogin();
}catch(IOException test) { test.printStackTrace(); }
            repaint();

      }


      public void start() {
           // new Thread(this).start();

      }

      public static BufferedImage cropMyImage(BufferedImage img, int cropWidth,
              int cropHeight, int cropStartX, int cropStartY) throws Exception {
              BufferedImage clipped = null;
              Dimension size = new Dimension(cropWidth, cropHeight);

              createClip(img, size, cropStartX, cropStartY);

              try {
              int w = clip.width;
              int h = clip.height;

              //System.out.println("Crop Width " + w);
              //System.out.println("Crop Height " + h);
              //System.out.println("Crop Location " + "(" + clip.x + "," + clip.y + ")");

              clipped = img.getSubimage(clip.x, clip.y, w, h);

              //System.out.println("Image Cropped. New Image Dimension: " + clipped.getWidth() + "w X " + clipped.getHeight() + "h");
              } catch (RasterFormatException rfe) {
              System.out.println("Raster format error: " + rfe.getMessage());
              return null;
              }
              return clipped;
              }
      
      public void playerLogin() throws IOException {
try {

            // me.getUsername() = "Guest #" + roll.nextInt(110);
            // String me.getUsername() = getParameter("name");

            me = new Player();
            //me.setUsername("Guest #" + roll.nextInt(110));
            me.setUsername("Guest");
            me.setPlayerImage(ImageIO.read(getClass().getResource("me.gif")));
            me.setX(224);
            me.setY(352);
            me.setMap(1);
            me.setCommand("move");
            players.put(me.getUsername(), me);

            repaint();

            //System.out.println(me.getUsername() + " was added. player: " + me);
            os.println(me.getUsername() + "|" + me.getX() + "|" + me.getY() + "|"
                        + me.getMap() + "|" + me.getCommand());


}catch(Exception rp) {
	//System.out.println("\nPlayer login error: " + rp.getMessage());
}
      }

      // List stuff start

      class MousePopupListener extends MouseAdapter
	  	{
	  		public void mousePressed(MouseEvent e)
	  		{
	  			checkPopup(e);
	  		}

	  		public void mouseClicked(MouseEvent e)
	  		{
	  			checkPopup(e);
	  		}

	  		public void mouseReleased(MouseEvent e)
	  		{
	  			checkPopup(e);
	  		}

	  		private void checkPopup(MouseEvent e)
	  		{
	  			if (e.isPopupTrigger())
	  			{
	  				

	  				int itemSelectx = list.getSelectedIndex();
	  				Object actItemx = list.getModel().getElementAt(itemSelectx);


				                  String isAWeapon = getItem(actItemx.toString())[9];
				                  String listWepName = getItem(actItemx.toString())[0];



	  			if (isAWeapon == "1") {
									popup.add(equipMenuItem);
									equipMenuItem.setText("Equip " + listWepName);
					  				popup.add(dropMenuItem);
					  				dropMenuItem.setText("Drop " + listWepName);
					  			}

	  			if (isAWeapon == "2") {
									popup.add(equipMenuItem);
									equipMenuItem.setText("Equip " + listWepName);
					  				popup.add(dropMenuItem);
					  				dropMenuItem.setText("Drop " + listWepName);
					  			}



	  			popup.add(cancelMenuItem);


	  				popup.show(list, e.getX(), e.getY()); // show item at mouse
	  				popup.revalidate(); // revalidate
	  				
	  				
	  			}
	  		}
	}
      
      class LeftArmListener extends MouseAdapter
	  	{
	  		public void mousePressed(MouseEvent lae)
	  		{
	  			checkPopup(lae);
	  		}

	  		public void mouseClicked(MouseEvent lae)
	  		{
	  			checkPopup(lae);
	  		}

	  		public void mouseReleased(MouseEvent lae)
	  		{
	  			checkPopup(lae);
	  		}

	  		private void checkPopup(MouseEvent lae)
	  		{
	  			if (lae.isPopupTrigger())
	  			{

	  				
	  				
	  				Object actItemx = leftArmWear+1;




				                  String isAWeapon = getItem(actItemx.toString())[9];
				                  String listWepName = getItem(actItemx.toString())[0];
	
				                  
									leftArmPopup.add(unequipMenuItem);
									unequipMenuItem.setText("Unequip " + listWepName);
									leftArmPopup.add(dropMenuItem);
					  				dropMenuItem.setText("Drop " + listWepName);
					  				
	  				
	  				if (leftArmWear != 0) {  
	  				leftArmPopup.add(unequipMenuItem);
	  				leftArmPopup.add(dropMenuItem);
	  				}
	  				leftArmPopup.add(cancelMenuItem);



	  				leftArmPopup.show(btLeftArmIcon, lae.getX(), lae.getY()); // show item at mouse
	  				leftArmPopup.revalidate(); // revalidate
	  				
	  				
	  			}
	  		}
	}

      // List stuff end



      public void mousePressed(MouseEvent e) {
      }

      public void mouseReleased(MouseEvent e) {
      }

      public void mouseEntered(MouseEvent e) {
      }

      public void mouseExited(MouseEvent e) {
      }

      // equip - use - select
      public void mouseClicked(MouseEvent e) {
/**
            xpos = me.getX();
            ypos = me.getY();

            rect1xco = 0;
            rect1yco = 0;
            rect1width = 480;
            rect1height = 320;

            if (xpos > rect1xco && xpos < rect1xco + rect1width && ypos > rect1yco
                        && ypos < rect1yco + rect1height) {
                  // game.requestFocusInWindow();
                  c.append("\nYou clicked on the game.");
            } **/

      }

      private  final HashSet<Integer> PUSHABLE_TILES = new HashSet<Integer>();
       {
		   PUSHABLE_TILES.add(163);
		   PUSHABLE_TILES.add(133);
		   PUSHABLE_TILES.add(145);
	   }

       private static void createClip(BufferedImage img, Dimension size,
    		   int clipX, int clipY) throws Exception {

    		   boolean isClipAreaAdjusted = false;


    		   if (clipX < 0) {
    		   clipX = 0;
    		   isClipAreaAdjusted = true;
    		   }
    		   /**Checking for negative Y Co-ordinate**/
    		   if (clipY < 0) {
    		   clipY = 0;
    		   isClipAreaAdjusted = true;
    		   }

    		   /**Checking if the clip area lies outside the rectangle**/
    		   if ((size.width + clipX) <= img.getWidth()
    		   && (size.height + clipY) <= img.getHeight()) {

    		   clip = new Rectangle(size);
    		   clip.x = clipX;
    		   clip.y = clipY;
    		   } else {

    		   /**
    		   * Checking if the width of the clip area lies outside the image.
    		   * If so, making the image width boundary as the clip width.
    		   */
    		   if ((size.width + clipX) > img.getWidth())
    		   size.width = img.getWidth() - clipX;

    		   /**
    		   * Checking if the height of the clip area lies outside the image.
    		   * If so, making the image height boundary as the clip height.
    		   */
    		   if ((size.height + clipY) > img.getHeight())
    		   size.height = img.getHeight() - clipY;

    		   /**Setting up the clip are based on our clip area size adjustment**/
    		   clip = new Rectangle(size);
    		   clip.x = clipX;
    		   clip.y = clipY;

    		   isClipAreaAdjusted = true;

    		   }
    		   if (isClipAreaAdjusted)
    		   System.out.println("Crop Area Lied Outside The Image."
    		   + " Adjusted The Clip Rectangle\n");
    		   }

    		   public static BufferedImage readImage(String fileLocation) {
    		   BufferedImage img = null;
    		   try {
    		   img = ImageIO.read(new File(fileLocation));
    		   //System.out.println("Image Read. Image Dimension: " + img.getWidth() + "w X " + img.getHeight() + "h");
    		   } catch (IOException e) {
    		   e.printStackTrace();
    		   }
    		   return img;
    		   }
       
      private  final HashSet<Integer> BLOCKED_TILES = new HashSet<Integer>();
       {

    	   BLOCKED_TILES.add(1);
    	   BLOCKED_TILES.add(2);
    	   BLOCKED_TILES.add(3);
    	   BLOCKED_TILES.add(4);
    	   BLOCKED_TILES.add(5);
    	   BLOCKED_TILES.add(6);
    	   BLOCKED_TILES.add(7);
    	   BLOCKED_TILES.add(8);
    	   BLOCKED_TILES.add(9);
    	   BLOCKED_TILES.add(19);
    	   BLOCKED_TILES.add(20);
    	   BLOCKED_TILES.add(21);
    	   BLOCKED_TILES.add(22);
    	   BLOCKED_TILES.add(23);
    	   BLOCKED_TILES.add(24);
    	   BLOCKED_TILES.add(25);
    	   BLOCKED_TILES.add(26);
    	   BLOCKED_TILES.add(27);
    	   BLOCKED_TILES.add(28);
    	   BLOCKED_TILES.add(29);
    	   BLOCKED_TILES.add(30);
    	   BLOCKED_TILES.add(37);
    	   BLOCKED_TILES.add(39);
    	   BLOCKED_TILES.add(41);
    	   BLOCKED_TILES.add(43);
    	   BLOCKED_TILES.add(45);
    	   BLOCKED_TILES.add(55);
    	   BLOCKED_TILES.add(56);
    	   BLOCKED_TILES.add(57);
    	   BLOCKED_TILES.add(58);
    	   BLOCKED_TILES.add(59);
    	   BLOCKED_TILES.add(60);
    	   BLOCKED_TILES.add(61);
    	   BLOCKED_TILES.add(62);
    	   BLOCKED_TILES.add(63);
    	   BLOCKED_TILES.add(64);
    	   BLOCKED_TILES.add(65);
    	   BLOCKED_TILES.add(66);
    	   BLOCKED_TILES.add(67);
    	   BLOCKED_TILES.add(68);
    	   BLOCKED_TILES.add(69);
    	   BLOCKED_TILES.add(70);
    	   BLOCKED_TILES.add(71);
    	   BLOCKED_TILES.add(72);
    	   BLOCKED_TILES.add(73);
    	   BLOCKED_TILES.add(76);
    	   BLOCKED_TILES.add(79);
    	   BLOCKED_TILES.add(81);
    	   BLOCKED_TILES.add(90);
    	   BLOCKED_TILES.add(91);
    	   BLOCKED_TILES.add(92);
    	   BLOCKED_TILES.add(93);
    	   BLOCKED_TILES.add(94);
    	   BLOCKED_TILES.add(95);
    	   BLOCKED_TILES.add(96);
    	   BLOCKED_TILES.add(97);
    	   BLOCKED_TILES.add(98);
    	   BLOCKED_TILES.add(99);
    	   BLOCKED_TILES.add(106);
    	   BLOCKED_TILES.add(107);
    	   BLOCKED_TILES.add(108);
    	   BLOCKED_TILES.add(115);
    	   BLOCKED_TILES.add(116);
    	   BLOCKED_TILES.add(117);
    	   BLOCKED_TILES.add(118);
    	   BLOCKED_TILES.add(119);
    	   BLOCKED_TILES.add(120);
    	   BLOCKED_TILES.add(121);
    	   BLOCKED_TILES.add(122);
    	   BLOCKED_TILES.add(123);
    	   BLOCKED_TILES.add(124);
    	   BLOCKED_TILES.add(125);
    	   BLOCKED_TILES.add(126);
    	   BLOCKED_TILES.add(127);
    	   BLOCKED_TILES.add(128);
    	   BLOCKED_TILES.add(129);
    	   BLOCKED_TILES.add(130);
    	   BLOCKED_TILES.add(131);
    	   BLOCKED_TILES.add(132);
    	   BLOCKED_TILES.add(133);
    	   BLOCKED_TILES.add(134);
    	   BLOCKED_TILES.add(135);
    	   BLOCKED_TILES.add(136);
    	   BLOCKED_TILES.add(139);
    	   BLOCKED_TILES.add(140);
    	   BLOCKED_TILES.add(141);
    	   BLOCKED_TILES.add(142);
    	   BLOCKED_TILES.add(143);
    	   BLOCKED_TILES.add(144);
    	   BLOCKED_TILES.add(145);
    	   BLOCKED_TILES.add(146);
    	   BLOCKED_TILES.add(147);
    	   BLOCKED_TILES.add(148);
    	   BLOCKED_TILES.add(149);
    	   BLOCKED_TILES.add(150);
    	   BLOCKED_TILES.add(151);
    	   BLOCKED_TILES.add(152);
    	   BLOCKED_TILES.add(153);
    	   BLOCKED_TILES.add(154);
    	   BLOCKED_TILES.add(155);
    	   BLOCKED_TILES.add(156);
    	   BLOCKED_TILES.add(157);
    	   BLOCKED_TILES.add(158);
    	   BLOCKED_TILES.add(159);
    	   BLOCKED_TILES.add(160);
    	   BLOCKED_TILES.add(161);
    	   BLOCKED_TILES.add(162);
    	   BLOCKED_TILES.add(163);
    	   BLOCKED_TILES.add(164);
    	   BLOCKED_TILES.add(165);
    	   BLOCKED_TILES.add(169);
    	   BLOCKED_TILES.add(170);
    	   BLOCKED_TILES.add(171);
    	   BLOCKED_TILES.add(172);
    	   BLOCKED_TILES.add(173);
    	   BLOCKED_TILES.add(174);
    	   BLOCKED_TILES.add(175);
    	   BLOCKED_TILES.add(176);
    	   BLOCKED_TILES.add(177);
    	   BLOCKED_TILES.add(178);
    	   BLOCKED_TILES.add(179);
    	   BLOCKED_TILES.add(180);
    	   BLOCKED_TILES.add(181);
    	   BLOCKED_TILES.add(183);
    	   BLOCKED_TILES.add(185);
    	   BLOCKED_TILES.add(187);
    	   BLOCKED_TILES.add(190);
    	   BLOCKED_TILES.add(191);
    	   BLOCKED_TILES.add(192);
    	   BLOCKED_TILES.add(196);
    	   BLOCKED_TILES.add(197);
    	   BLOCKED_TILES.add(198);
    	   BLOCKED_TILES.add(199);
    	   BLOCKED_TILES.add(200);
    	   BLOCKED_TILES.add(201);
    	   BLOCKED_TILES.add(202);
    	   BLOCKED_TILES.add(203);
    	   BLOCKED_TILES.add(204);
    	   BLOCKED_TILES.add(205);
    	   BLOCKED_TILES.add(206);
    	   BLOCKED_TILES.add(207);
    	   BLOCKED_TILES.add(211);
    	   BLOCKED_TILES.add(212);
    	   BLOCKED_TILES.add(213);
    	   BLOCKED_TILES.add(214);
    	   BLOCKED_TILES.add(217);
    	   BLOCKED_TILES.add(220);
    	   BLOCKED_TILES.add(222);
    	   BLOCKED_TILES.add(223);
    	   BLOCKED_TILES.add(225);
    	   BLOCKED_TILES.add(226);
    	   BLOCKED_TILES.add(227);
    	   BLOCKED_TILES.add(228);
    	   BLOCKED_TILES.add(232);
    	   BLOCKED_TILES.add(233);
    	   BLOCKED_TILES.add(234);
    	   BLOCKED_TILES.add(235);
    	   BLOCKED_TILES.add(236);
    	   BLOCKED_TILES.add(237);
    	   BLOCKED_TILES.add(238);
    	   BLOCKED_TILES.add(239);
    	   BLOCKED_TILES.add(240);
    	   BLOCKED_TILES.add(241);
    	   BLOCKED_TILES.add(242);
    	   BLOCKED_TILES.add(243);
    	   BLOCKED_TILES.add(244);
    	   BLOCKED_TILES.add(246);
    	   BLOCKED_TILES.add(247);
    	   BLOCKED_TILES.add(249);
    	   BLOCKED_TILES.add(250);
    	   BLOCKED_TILES.add(252);
    	   BLOCKED_TILES.add(259);
    	   BLOCKED_TILES.add(260);
    	   BLOCKED_TILES.add(261);
    	   BLOCKED_TILES.add(262);
    	   BLOCKED_TILES.add(263);
    	   BLOCKED_TILES.add(264);
    	   BLOCKED_TILES.add(271);
    	   BLOCKED_TILES.add(272);
    	   BLOCKED_TILES.add(273);
    	   BLOCKED_TILES.add(274);
    	   BLOCKED_TILES.add(275);
    	   BLOCKED_TILES.add(276);
    	   BLOCKED_TILES.add(277);
    	   BLOCKED_TILES.add(278);
    	   BLOCKED_TILES.add(279);
    	   BLOCKED_TILES.add(280);
    	   BLOCKED_TILES.add(283);
    	   BLOCKED_TILES.add(285);
    	   BLOCKED_TILES.add(288);
    	   BLOCKED_TILES.add(319);
    	   BLOCKED_TILES.add(320);
    	   BLOCKED_TILES.add(321);
    	   BLOCKED_TILES.add(322);
    	   BLOCKED_TILES.add(328);
    	   BLOCKED_TILES.add(329);
    	   BLOCKED_TILES.add(330);
    	   BLOCKED_TILES.add(331);
    	   BLOCKED_TILES.add(332);
    	   BLOCKED_TILES.add(333);
    	   BLOCKED_TILES.add(334);
    	   BLOCKED_TILES.add(335);
    	   BLOCKED_TILES.add(336);
    	   BLOCKED_TILES.add(337);
    	   BLOCKED_TILES.add(338);
    	   BLOCKED_TILES.add(339);
    	   BLOCKED_TILES.add(340);
    	   BLOCKED_TILES.add(341);
    	   BLOCKED_TILES.add(342);
    	   BLOCKED_TILES.add(349);
    	   BLOCKED_TILES.add(350);
    	   BLOCKED_TILES.add(351);
    	   BLOCKED_TILES.add(352);
    	   BLOCKED_TILES.add(353);
    	   BLOCKED_TILES.add(354);
    	   BLOCKED_TILES.add(355);
    	   BLOCKED_TILES.add(361);
    	   BLOCKED_TILES.add(362);
    	   BLOCKED_TILES.add(363);
    	   BLOCKED_TILES.add(364);
    	   BLOCKED_TILES.add(365);
    	   BLOCKED_TILES.add(366);
    	   BLOCKED_TILES.add(367);
    	   BLOCKED_TILES.add(368);
    	   BLOCKED_TILES.add(369);
    	   BLOCKED_TILES.add(370);
    	   BLOCKED_TILES.add(371);
    	   BLOCKED_TILES.add(372);
    	   BLOCKED_TILES.add(373);
    	   BLOCKED_TILES.add(374);
    	   BLOCKED_TILES.add(375);
    	   BLOCKED_TILES.add(376);
    	   BLOCKED_TILES.add(377);
    	   BLOCKED_TILES.add(378);
    	   BLOCKED_TILES.add(379);
    	   BLOCKED_TILES.add(380);
    	   BLOCKED_TILES.add(381);
    	   BLOCKED_TILES.add(388);
    	   BLOCKED_TILES.add(389);
    	   BLOCKED_TILES.add(390);
    	   BLOCKED_TILES.add(391);
    	   BLOCKED_TILES.add(392);
    	   BLOCKED_TILES.add(393);
    	   BLOCKED_TILES.add(394);
    	   BLOCKED_TILES.add(397);
    	   BLOCKED_TILES.add(398);
    	   BLOCKED_TILES.add(399);
    	   BLOCKED_TILES.add(400);
    	   BLOCKED_TILES.add(401);
    	   BLOCKED_TILES.add(402);
    	   BLOCKED_TILES.add(403);
    	   BLOCKED_TILES.add(404);
    	   BLOCKED_TILES.add(405);
    	   BLOCKED_TILES.add(406);
    	   BLOCKED_TILES.add(407);
    	   BLOCKED_TILES.add(408);
    	   BLOCKED_TILES.add(409);
    	   BLOCKED_TILES.add(410);
    	   BLOCKED_TILES.add(411);
    	   BLOCKED_TILES.add(412);
    	   BLOCKED_TILES.add(413);
    	   BLOCKED_TILES.add(414);
    	   BLOCKED_TILES.add(415);
    	   BLOCKED_TILES.add(416);
    	   BLOCKED_TILES.add(417);
    	   BLOCKED_TILES.add(418);
    	   BLOCKED_TILES.add(419);
    	   BLOCKED_TILES.add(420);
    	   BLOCKED_TILES.add(421);
    	   BLOCKED_TILES.add(422);
    	   BLOCKED_TILES.add(423);
    	   BLOCKED_TILES.add(430);
    	   BLOCKED_TILES.add(431);
    	   BLOCKED_TILES.add(432);
    	   BLOCKED_TILES.add(433);
    	   BLOCKED_TILES.add(434);
    	   BLOCKED_TILES.add(435);
    	   BLOCKED_TILES.add(436);
    	   BLOCKED_TILES.add(437);
    	   BLOCKED_TILES.add(438);
    	   BLOCKED_TILES.add(439);
    	   BLOCKED_TILES.add(440);
    	   BLOCKED_TILES.add(441);
    	   BLOCKED_TILES.add(442);
    	   BLOCKED_TILES.add(443);
    	   BLOCKED_TILES.add(444);
    	   BLOCKED_TILES.add(445);
    	   BLOCKED_TILES.add(446);
    	   BLOCKED_TILES.add(447);
    	   BLOCKED_TILES.add(448);
    	   BLOCKED_TILES.add(449);
    	   BLOCKED_TILES.add(450);
    	   BLOCKED_TILES.add(451);
    	   BLOCKED_TILES.add(452);
    	   BLOCKED_TILES.add(453);
    	   BLOCKED_TILES.add(454);
    	   BLOCKED_TILES.add(455);
    	   BLOCKED_TILES.add(456);
    	   BLOCKED_TILES.add(469);
    	   BLOCKED_TILES.add(470);
    	   BLOCKED_TILES.add(471);
    	   BLOCKED_TILES.add(472);
    	   BLOCKED_TILES.add(473);
    	   BLOCKED_TILES.add(474);
    	   BLOCKED_TILES.add(475);
    	   BLOCKED_TILES.add(476);
    	   BLOCKED_TILES.add(477);
    	   BLOCKED_TILES.add(478);
    	   BLOCKED_TILES.add(479);
    	   BLOCKED_TILES.add(480);
    	   BLOCKED_TILES.add(481);
    	   BLOCKED_TILES.add(482);
    	   BLOCKED_TILES.add(483);
    	   BLOCKED_TILES.add(484);
    	   BLOCKED_TILES.add(485);
    	   BLOCKED_TILES.add(486);
    	   BLOCKED_TILES.add(487);
    	   BLOCKED_TILES.add(488);
    	   BLOCKED_TILES.add(489);
    	   BLOCKED_TILES.add(490);
    	   BLOCKED_TILES.add(491);
    	   BLOCKED_TILES.add(492);
    	   BLOCKED_TILES.add(493);
    	   BLOCKED_TILES.add(494);
    	   BLOCKED_TILES.add(495);
    	   BLOCKED_TILES.add(496);
    	   BLOCKED_TILES.add(499);
    	   BLOCKED_TILES.add(500);
    	   BLOCKED_TILES.add(501);
    	   BLOCKED_TILES.add(502);
    	   BLOCKED_TILES.add(503);
    	   BLOCKED_TILES.add(504);
    	   BLOCKED_TILES.add(505);
    	   BLOCKED_TILES.add(506);
    	   BLOCKED_TILES.add(507);
    	   BLOCKED_TILES.add(508);
    	   BLOCKED_TILES.add(509);
    	   BLOCKED_TILES.add(510);
    	   BLOCKED_TILES.add(511);
    	   BLOCKED_TILES.add(512);
    	   BLOCKED_TILES.add(513);



            // add more tiles here
      }

      public void actionPerformed(ActionEvent actionevent) {
            // game.requestFocusInWindow();
            c.setCaretPosition(c.getDocument().getLength());
      }

      public Player getPlayer(String username) {
            return players.get(username);
      }

      public void destroy() {


// SAVE CODE HERE
	  }




      public boolean hasItem(String e) {
		  if(model.contains(e)) {
			  return true;
		  }else{
			  return false;
		  }
	  }

      public boolean isInteger( String input )
	  {
	     try
	     {
	        Integer.parseInt( input );
	        return true;
	     }
	     catch( Exception x)
	     {
	        return false;
	     }
	  }

	  public void pickItem() {

		  for (int i = 0; i < droppedItems.size(); i++) {

		  String[] pickingUp = droppedItems.get(i).split("\\|");

		 if (currentMap == Integer.parseInt(pickingUp[3]) && spawnX == Integer.parseInt(pickingUp[1]) && spawnY == Integer.parseInt(pickingUp[2])) {

			 //c.append("\nCAPTURED a " + getItem(pickingUp[0])[0]);

			 //model.addElement(getItem(pickingUp[0])[0]);



			 repaint();

		 }





}

	  }

	  public boolean com(String e) {


if (say.getText().indexOf(e) > -1) {
	return true;
}else{
	return false;
}
	  }

	  public void doCommand(String e, String who) {

		  if (e.equals("/item")) {

			  String itemSpawn[] = who.split(",");

if (!getItem(itemSpawn[0])[0].equals("Null")) {
			  c.append("\nYou spawned a " + getItem(itemSpawn[0])[0] + " underneath you.");
			  dropItemNow(Integer.parseInt(getItem(itemSpawn[0])[8]), spawnX, spawnY);
		  }else{
			 c.append("\nInvalid item ID.");
		  }

		  }
		  if (e.equals("/tele")) {

			  String teleTo[] = who.split(",");

			  int teleX = Integer.parseInt(teleTo[0]) * 32;
			  int teleY = Integer.parseInt(teleTo[1]) * 32;

			  if (blocked(Integer.parseInt(teleTo[0]),Integer.parseInt(teleTo[1]))) {
				  c.append("\nSorry, the block X: " + teleTo[0] + ", Y: " + teleTo[1] + " is blocked!");


			  }else{

			  me.setX(teleX);
			  me.setY(teleY);
			  spawnX = Integer.parseInt(teleTo[0]);
			  spawnY = Integer.parseInt(teleTo[1]);
			  px = teleX;
			  py = teleY;
			  lastX = Integer.parseInt(teleTo[0]);
			  lastY = Integer.parseInt(teleTo[1]);
			  c.append("\nTeleported to X: " + teleTo[0] + ", Y: " + teleTo[1]);
			  tele = true;
		  } // end teleport



		  }

	  }

	  public void addMonsterNow(int dMonsterID, int monX, int monY) {

			 monstersActive.add(Integer.toString(dMonsterID) + "|" + Integer.toString(monX) + "|" + Integer.toString(monY) + "|"+currentMap+"|nothing");

		  }
	  
	  public void addMonster(int dMonsterID, int monX, int monY, int monMap) {

			 monstersActive.add(Integer.toString(dMonsterID) + "|" + Integer.toString(monX) + "|" + Integer.toString(monY) + "|"+Integer.toString(monMap)+"|nothing");

		  }
	  
	  public void addSign(int signX, int signY, String signSays) {

		 signs.add(Integer.toString(signX) + "|" + Integer.toString(signY) + "|" + signSays + "|"+currentMap);
		 droppedItems.add("7|" + Integer.toString(signX) + "|" + Integer.toString(signY) + "|" + currentMap + "|drop|Guest|yes");
		 //c.append("\nSIGN - X: " + signX + " Y: " + signY + " Map: " + currentMap); 
	  }
	  
	  public void addSignNow(int signX, int signY, String signSays, int signMap) {

			 signs.add(Integer.toString(signX) + "|" + Integer.toString(signY) + "|" + signSays + "|"+signMap);
			 droppedItems.add("7|" + Integer.toString(signX) + "|" + Integer.toString(signY) + "|" + Integer.toString(signMap) + "|drop|Guest|yes");
			 
		  }

	 public void addTeleport(int fromX, int fromY, int fromMap, int toX, int toY, int toMap) {
	  		 teleports.add(Integer.toString(fromX) + "|" + Integer.toString(fromY) + "|" + Integer.toString(fromMap) + "|" + Integer.toString(toX) + "|" + Integer.toString(toY) + "|" + Integer.toString(toMap)+"|0");
	  }

	 public void addTeleportItem(int fromX, int fromY, int fromMap, int toX, int toY, int toMap, int item_req) {
	  		 teleports.add(Integer.toString(fromX) + "|" + Integer.toString(fromY) + "|" + Integer.toString(fromMap) + "|" + Integer.toString(toX) + "|" + Integer.toString(toY) + "|" + Integer.toString(toMap) + "|" + Integer.toString(item_req));
	  }


	 public void addEntrance(int entX, int entY, int tileAfter) {
	  		 entrances.add(Integer.toString(entX) + "|" + Integer.toString(entY) + "|" + Integer.toString(tileAfter) + "|" + currentMap +"|0");
	  }

	 public void addEntranceItem(int entX, int entY, int tileAfter, int itemReq) {
	  		 entrances.add(Integer.toString(entX) + "|" + Integer.toString(entY) + "|" + Integer.toString(tileAfter) + "|" + currentMap + "|" + Integer.toString(itemReq));
	  }

	  public void dropItem(int dropId, int dropX, int dropY, int dropMap) {
	  droppedItems.add(Integer.toString(dropId) + "|" + Integer.toString(dropX) + "|" + Integer.toString(dropY) + "|" + Integer.toString(dropMap) + "|drop|Guest|yes");

}
	  
	  public void dropItemNow(int dropId, int dropX, int dropYX) {

try {
		  droppedItems.add(Integer.toString(dropId) + "|" + Integer.toString(dropX) + "|" + Integer.toString(dropYX) + "|" + currentMap + "|drop|Guest|yes");
}catch(Exception herp) { herp.printStackTrace(); }
}

	  public void checkCommand() {

		  List<String> dan = Arrays.asList("/item", "/tele");
		  String[] whoKick;
		  whoKick = say.getText().split(" ");


boolean contains = dan.contains(whoKick[0]);
if (whoKick[0].indexOf("/") > -1) {
if (contains) {
	doCommand(whoKick[0], whoKick[1]);
	}else{
							 c.append("\nInvalid command: " + whoKick[0]);
						 }
					 }

					 say.setText("");
					 c.setCaretPosition(c.getDocument().getLength());

	  }

public void doBattle(int e, int monster_id, String monFull) {

	//c.append("\nMoveLeft: " + moveLeft + " | moveRight: " + moveRight);

	// ID in arraylist, ID of monster, max HP of monster

	String strMonID = Integer.toString(monster_id);

	if (!firstStrike) {

	attacker = e +"|"+ getMonster(strMonID)[6] +"|"+getMonster(strMonID)[1];

}
firstStrike = true;

	String[] attackingMe = attacker.split("\\|");

	String[] monNow = monFull.split("\\|");

	spotted = true;
	isInBattle = true;
	beingAttacked = true;

	monstersActive.set(e, monNow[0] + "|" + monNow[1] + "|"+ monNow[2] +"|"+monNow[3]+"|attacking");

yourHit = monsterFormula(level, attack, defense);
newMonHp = Integer.parseInt(attackingMe[2]) - yourHit;


		qepe++;


		if (newMonHp < 0) {
try {
			c.append("\nYou killed a Level " + getMonster(monNow[0])[7] + " "+ getMonster(monNow[0])[0]+".");
			c.append("\nYou gained " + getMonster(monNow[0])[4] + " EXP and " + getMonster(monNow[0])[3] + " gold!");
			exp = exp + Integer.parseInt(getMonster(monNow[0])[4]);
			gold = gold + Integer.parseInt(getMonster(monNow[0])[3]);
			firstStrike = false;

				int commas = 1;
				for(int i = 0; i < getMonster(monNow[0])[5].length(); i++) {
					if(getMonster(monNow[0])[5].charAt(i) == ',') commas++;
				}

			int rewardDrop = roll.nextInt(commas);


			dropItemNow(Integer.parseInt(getItem(itemsDropped[rewardDrop])[8]), Integer.parseInt(monNow[1]), Integer.parseInt(monNow[2]));


			lExp.setText("EXP: " + exp);
System.out.println("MONSTER DEAD");
for (String mons : monstersActive) {
				System.out.println(mons);
			
			}

			monstersActive.remove(e);
	
			
			
			repaint();
			qepe = 0;
			beingAttacked = false;
			attacker = "";
			isInBattle = false;
			moveDown = true;
			moveRight = true;
			moveLeft = true;
			moveUp = true;
			
		}catch(Exception herpa) { herpa.printStackTrace(); } 	
}else{


try {


//DecimalFormat df = new DecimalFormat("###,###,###");

attacker = e +"|"+ getMonster(strMonID)[6] +"|"+newMonHp;

/*
String[] monBattle = attacker.split("\\|");


monstersActive.set(e, monNow[0] + "|" + monNow[1] + "|"+ monNow[2] +"|"+newMonHp+"|attacking");
*/

//c.append("\nYou hit the "+ getMonster(monNow[0])[0] + " for " + df.format(rawr) + "!");
c.append("\nYou hit for " + yourHit + "\nYour HP: " +currhp + " / Monster's HP: " + attackingMe[2]);



	/*

	DO BATTLE SYSTEM HERE

	YOU HIT FOR
	THEY HIT YOU
	BLAH BLAH BLAH

	*/
}catch(Exception herpx) { herpx.printStackTrace(); } 	

}


//c.append("\n1. UP: " +moveUp+ " | DOWN: " +moveDown+ " | LEFT: " +moveLeft+" | RIGHT: " + moveRight);


}

public static int monsterFormula(int e,int myAttack, int myDefense) {

 int xTotal = 0;
 for(int i=1; i<e; i++) {
        xTotal += (int)Math.floor(i + myAttack * Math.pow(myDefense, (i / 42.0)));
    }
      return (int)Math.floor(xTotal/8.0);
}

      public void keyPressed(KeyEvent e) {

    		for (int i = 0; i < signs.size(); i++) {
    			String[] signDrop = signs.get(i).split("\\|");
    			if (Integer.parseInt(signDrop[0]) != spawnX || Integer.parseInt(signDrop[1]) != spawnY) {
    				signPanel.setVisible(false);
    			}
    		}
    	  
    	  
		  if(introPanel.isVisible()) introPanel.setVisible(false);


tele = false;

myCommand = "move";
            // getPlayers();

            /*******
            * START MOVE MONSTERS
            ********/


           // Boolean monsterRight[] = new Boolean[monstersActive.size()];
           // Boolean monsterLeft[] = new Boolean[monstersActive.size()];

if (stepsTill > 3) {
	monMove = true;
	stepsTill = 0;
}else{
	monMove = false;
}

//looq
int qx= 0;

try {

			for (String mons : monstersActive) {

				String[] monCheck = mons.split("\\|");


							String[] checkDrop = mons.split("\\|");


							itemsDropped = getMonster(checkDrop[0])[5].split(",");


							if (currentMap == Integer.parseInt(monCheck[3])) {

							//String forMap = Integer.toString(currentMap);


//derp
int whichWay = roll.nextInt(2);
int whichDirection = roll.nextInt(2);
int newDirMove = 0;



if (beingAttacked) spotted = true;

if (!beingAttacked) {
	monstersActive.set(qx, monCheck[0] + "|" + monCheck[1] + "|"+ monCheck[2] +"|"+monCheck[3]+"|nothing");
}

try {

if (whichWay > 0) {

	if (whichDirection > 0) {
									newDirMove = Integer.parseInt(monCheck[1])+1;

								}else{
									newDirMove = Integer.parseInt(monCheck[1])-1;
								} // end whichDirection

								if (blocked(newDirMove,Integer.parseInt(monCheck[2])) == false) {
									if (monMove) monstersActive.set(qx, monCheck[0] + "|" + newDirMove + "|"+monCheck[2]+"|"+monCheck[3]+"|"+monCheck[4]);
								}
								}else{ // end WhichWay
	if (whichDirection > 0) {
									newDirMove = Integer.parseInt(monCheck[2])+1;
								}else{
									newDirMove = Integer.parseInt(monCheck[2])-1;
								} // end WhichDirection
								if (blocked(Integer.parseInt(monCheck[1]), newDirMove) == false) {

									if (monMove) monstersActive.set(qx, monCheck[0] + "|" + monCheck[1] + "|"+ newDirMove +"|"+monCheck[3]+"|"+monCheck[4]);

								} //end WhichWay
							} // end of equal nothing
						}catch (Exception ere) { System.out.println("Haha, error!"); }

check_down_Y = spawnY-1;
check_up_Y = spawnY+1;
check_left_X = spawnX-1;
check_right_X = spawnX+1;
checkX = spawnX;
checkY = spawnY;

//if (!isInBattle && attackedBy.equals(monCheck[0])) {

// FIGHT FROM BOTTOM
if (Integer.parseInt(monCheck[2]) == check_down_Y && Integer.parseInt(monCheck[1]) == checkX && e.getKeyCode() == KeyEvent.VK_UP) {
		moveUp = false;
		attackDown = true;
		beingAttacked = true;
		doBattle(qx,Integer.parseInt(monCheck[0]), mons);
		spotted = true;
		attackedBy = monCheck[0];
}else if (spotted == false){
	moveUp = true;
	beingAttacked = false;
	attacker = "";
	isInBattle = false;
}
// END FIGHT FROM BOTTOM




// FIGHT FROM TOP
if (Integer.parseInt(monCheck[2]) == check_up_Y && Integer.parseInt(monCheck[1]) == checkX && e.getKeyCode() == KeyEvent.VK_DOWN) {
		moveDown = false;
		attackUp = true;
		doBattle(qx,Integer.parseInt(monCheck[0]), mons);
		spotted = true;
		attackedBy = monCheck[0];
}else if (spotted == false){
	moveDown = true;
	beingAttacked = false;
	attacker = "";
}
// END FIGHT FROM TOP

// FIGHT FROM LEFT
if (Integer.parseInt(monCheck[1]) == check_right_X && Integer.parseInt(monCheck[2]) == checkY && e.getKeyCode() == KeyEvent.VK_RIGHT) {
		moveRight = false;
		attackLeft = true;
		doBattle(qx,Integer.parseInt(monCheck[0]), mons);
		spotted = true;
		attackedBy = monCheck[0];
}else if (spotted == false){
	moveRight = true;
	beingAttacked = false;
	attacker = "";
}
// END FIGHT FROM LEFT

// FIGHT FROM RIGHT
if (Integer.parseInt(monCheck[1]) == check_left_X && Integer.parseInt(monCheck[2]) == checkY && e.getKeyCode() == KeyEvent.VK_LEFT) {
	moveLeft = false;
	attackRight = true;
	doBattle(qx,Integer.parseInt(monCheck[0]), mons);
	spotted = true;
	attackedBy = monCheck[0];
}else if (spotted == false){
	moveLeft = true;
	beingAttacked = false;
	attacker = "";
}
// END FIGHT FROM RIGHT

// Fleeing from battle

//c.append("\nbeingAttacked: " + beingAttacked);

//Top battle flee


 if (monCheck[0].equals(attackedBy)) {

if (beingAttacked && moveUp == false && e.getKeyCode() == KeyEvent.VK_LEFT && isInBattle || beingAttacked && moveUp == false && e.getKeyCode() == KeyEvent.VK_DOWN && isInBattle || beingAttacked && moveUp == false && e.getKeyCode() == KeyEvent.VK_RIGHT && isInBattle) {
c.append("\nYou flee from the battle!");
//c.append("\nYou flee from the battle! From Down");
moveUp = true;
spotted = false;
beingAttacked = false;
firstStrike = false;
}

//Bottom battle flee
if (beingAttacked && moveDown == false && e.getKeyCode() == KeyEvent.VK_LEFT && isInBattle || beingAttacked && moveDown == false && e.getKeyCode() == KeyEvent.VK_UP && isInBattle || beingAttacked && moveDown == false && e.getKeyCode() == KeyEvent.VK_RIGHT && isInBattle) {
c.append("\nYou flee from the battle!");
//c.append("\nYou flee from the battle! From Up");
moveDown = true;
spotted = false;
beingAttacked = false;
firstStrike = false;
}

//Left battle flee
if (beingAttacked && moveLeft == false && e.getKeyCode() == KeyEvent.VK_UP && isInBattle || beingAttacked && moveLeft == false && e.getKeyCode() == KeyEvent.VK_DOWN && isInBattle || beingAttacked && moveLeft == false && e.getKeyCode() == KeyEvent.VK_RIGHT && isInBattle) {
c.append("\nYou flee from the battle!");
//c.append("\nYou flee from the battle! FROM Right");
moveLeft = true;
spotted = false;
beingAttacked = false;
firstStrike = false;
}

//Right battle flee
if (beingAttacked && moveRight == false && e.getKeyCode() == KeyEvent.VK_LEFT && isInBattle || beingAttacked && moveRight == false && e.getKeyCode() == KeyEvent.VK_DOWN && isInBattle || beingAttacked && moveRight == false && e.getKeyCode() == KeyEvent.VK_UP && isInBattle) {
c.append("\nYou flee from the battle!");
//c.append("\nYou flee from the battle! From Left");
moveRight = true;
spotted = false;
beingAttacked = false;
firstStrike = false;
}

//c.append("\n2. UP: " +moveUp+ " | DOWN: " +moveDown+ " | LEFT: " +moveLeft+" | RIGHT: " + moveRight);
//c.append("\nAttackedBy: " + attackedBy + " | monCheck[0]: " + monCheck[0]);
}

}


// OTHER DIRECTIONS TO FIGHT FROM






						qx++;


//c.append("\nIn Battle? " + isInBattle + "| Spotted?" + spotted + " | being Attacked: " + beingAttacked);



}
}catch(Exception eqq) {}


            /*******
            * END MOVE MONSTERS
            ********/

            //System.out.println("Command? " + myCommand);

            if (isInBound(lastX, lastY) == true) {
                  //System.out.println("\nYOU WENT OFF THE GRID.\n");
            }

            right = true;
            left = true;
            up = true;
            down = true;

            // c.append("\nInventory selection: " + choice);

            // DESCRIBE ITEM




/**
 * PUSHING TILES
 **/
try {
	int downBlock = spawnY + 2;
	int upBlock = spawnY - 2;
	int leftBlock = spawnX - 2;
	int rightBlock = spawnX + 2;
	//int rightPush = spawnX + 1;
	//int leftPush = spawnX - 1;
	//int upPush = spawnY - 1;
	//int downPush = spawnY + 1;
//	int chgTo = spawnX;

	 if (e.isShiftDown())
	 {
		 
			for (int i = 0; i < signs.size(); i++) {
				String[] signDrop = signs.get(i).split("\\|");

				if (Integer.parseInt(signDrop[0]) == spawnX && Integer.parseInt(signDrop[1]) == spawnY) {
					
		            signhtml = "<html>"+signDrop[2]+"</html>";
		            //signhtml = "<html><img src='https://si3.twimg.com/profile_images/1334420762/porfiletwitter_reasonably_small.jpg' /> <br />hahaha</html>";
		           
					
					
					signLabel.setText(signhtml);
					
					if(!signPanel.isVisible()) {
						signPanel.setVisible(true);
						c.append("\nSign opened...");
					}else{
						signPanel.setVisible(false);
						c.append("\nSign closed...");
					}
					
				}
				
			}
		 
	 }
	
 if (e.isShiftDown() && e.getKeyCode() == KeyEvent.VK_RIGHT)
{
	if(!hasItem("Big Hammer") && pushBoard[spawnY][spawnX+1] == 163) {
		c.append("\nYou need a hammer to move a boulder this strong!");
	}else{

	//c.append("\nYou pushed the tile to the right...");
if (!blocked(rightBlock,spawnY) && pushable(pushBoard[spawnY][rightBlock]) == false) {

	pushBoard[spawnY][spawnX+2] = pushBoard[spawnY][spawnX+1];
	pushBoard[spawnY][spawnX+1] = pushBoard[spawnY][spawnX];
}
}

}
 if (e.isShiftDown() && e.getKeyCode() == KeyEvent.VK_DOWN)
{
if (!blocked(spawnX, downBlock) && pushable(pushBoard[downBlock][spawnX]) == false) {
	pushBoard[spawnY+2][spawnX] = pushBoard[spawnY+1][spawnX];
	pushBoard[spawnY+1][spawnX] = pushBoard[spawnY][spawnX];
	//c.append("\nYou pushed the tile down...");
}
}

 if (e.isShiftDown() && e.getKeyCode() == KeyEvent.VK_LEFT)
{

	//c.append("\nYou pushed the tile to the left...");
if (!blocked(leftBlock,spawnY) && pushable(pushBoard[spawnY][leftBlock]) == false) {

	pushBoard[spawnY][spawnX-2] = pushBoard[spawnY][spawnX-1];
	pushBoard[spawnY][spawnX-1] = pushBoard[spawnY][spawnX];
}

}

 if (e.isShiftDown() && e.getKeyCode() == KeyEvent.VK_UP)
{
if (!blocked(spawnX, upBlock) && pushable(pushBoard[upBlock][spawnX]) == false) {
	pushBoard[spawnY-2][spawnX] = pushBoard[spawnY-1][spawnX];
	pushBoard[spawnY-1][spawnX] = pushBoard[spawnY][spawnX];
	//c.append("\nYou pushed the tile up...");
}
}
repaint();
}catch(ArrayIndexOutOfBoundsException er) {  }

/**
 * END OF PUSHING TILES
 **/


 if (e.isControlDown())
{

	if (userPanel.isOpaque()) {
		userPanel.setOpaque(false);
		labelUser.setVisible(false);
		labelY.setVisible(false);
		labelX.setVisible(false);
		xPanel.setOpaque(false);
		yPanel.setOpaque(false);
		roomPanel.setOpaque(false);
		labelRoom.setVisible(false);
		c.append("\nStatistics are now hidden.");
	}else if (!userPanel.isOpaque()) {
		userPanel.setOpaque(true);
		labelUser.setVisible(true);
		labelY.setVisible(true);
		labelX.setVisible(true);
		xPanel.setOpaque(true);
		yPanel.setOpaque(true);
		roomPanel.setOpaque(true);
		labelRoom.setVisible(true);
		c.append("\nStatistics are now displaying.");
	}

	game.requestFocusInWindow();

}
if (e.getKeyCode() == KeyEvent.VK_5) {
	for (String s : maplist) {
	    System.out.println(s);
}
}


if (e.getKeyCode() == KeyEvent.VK_1) {

//int itemNo = 0;
System.out.println("\n\nXXXXXXXXXXXXXXXXXXX\n\n");

for (int i = 0; i < droppedItems.size(); i++) {
	System.out.println(droppedItems.get(i));
				//String[] checkDrop = droppedItems.get(i).split("\\|");
                  //System.out.println(droppedItems.get(i) + "\n - X: " + checkDrop[0] + " Y:" + checkDrop[1] + " Tile After: " + checkDrop[2] + " on Map: " + checkDrop[3] +"\n\n");
            }

System.out.println("\n\nXXXXXXXXXXXXXXXXXXX\n\n");

}
if (e.getKeyCode() == KeyEvent.VK_4) {
	c.append("\nMap refreshed!");
	
	this.getAppletContext().showDocument(this.getDocumentBase(), "_self");
}


if (e.getKeyCode() == KeyEvent.VK_3) {
	System.out.println("\n======================\n SIGNS ON MAP");
	
	for (int i = 0; i < signs.size(); i++) {
		System.out.println(signs.get(i));
	}

}

if (e.getKeyCode() == KeyEvent.VK_2) {

System.out.println("STATS qepe: " + qepe + " | qx: " + qx + " | moveUp: " + moveUp);

//int monNo = 0;
System.out.println("\n======================\n MONSTERS ON CURRENT MAP");

for (int i = 0; i < monstersActive.size(); i++) {
				String[] checkDrop = monstersActive.get(i).split("\\|");
				itemsDropped = getMonster(checkDrop[0])[5].split(",");

				if (currentMap == Integer.parseInt(checkDrop[3])) {

				System.out.println("\n--------------------\n" + monstersActive.get(i) + "\n------\n");
				System.out.println("Name: "+ getMonster(checkDrop[0])[0] +" (ID: "+ getMonster(checkDrop[0])[6] +")\n");
				System.out.println("Coords: [X: " + checkDrop[1] + ", Y: " + checkDrop[2] + "]\n");
				System.out.println("Mapped on: "+ setRoom(Integer.parseInt(checkDrop[3])) +" (Map ID: " + checkDrop[3] +")\n");
				System.out.println("HP: " + getMonster(checkDrop[0])[1] + " | Gold drop: " + getMonster(checkDrop[0])[3] + " | EXP worth: " + getMonster(checkDrop[0])[4] + "\n");
				System.out.println("Currently doing: " + checkDrop[4]);
				System.out.print("Loot: ");

				for (int itemsOnMon = 0; itemsOnMon < getMonster(checkDrop[0])[5].length(); itemsOnMon++) {
				try {
				System.out.print("[" + getItem(itemsDropped[itemsOnMon])[0] + "] ");
			}catch(ArrayIndexOutOfBoundsException rx ) {};
				}

			}

         }

System.out.println("\n======================\n");

}


if (e.getKeyCode() == KeyEvent.VK_C) {

if (charOpen == false) {
	c.append("\nYou opened the Character Sheet.");
	charPanel.setVisible(true);
	charOpen = true;
}else{
	c.append("\nYou closed the Character Sheet.");
	charPanel.setVisible(false);
	charOpen = false;
}


}


if(!list.isSelectionEmpty()) {
if (e.getKeyCode() == KeyEvent.VK_X) {

int listID = list.getSelectedIndex();
Object droppedItem = list.getModel().getElementAt(listID);

itemIDdropped = getItem(droppedItem.toString())[8];

c.append("\nYou dropped a " + getItem(droppedItem.toString())[0] + ".");
myCommand = "drop";


model.remove(list.getSelectedIndex());
int d_itemID = Integer.parseInt(itemIDdropped);
dropItemNow(d_itemID, spawnX, spawnY);


}
}

/**
 if (e.getKeyCode() == KeyEvent.VK_F)
{

	sp.setOpaque(false);
	sp.getViewport().setOpaque(false);
	c.setOpaque(false);

	/**
	if (sp.isVisible())
	{
		sp.setVisible(false);
	}
	else
	{
		sp.setVisible(true);
	}
}
**/

/**
            if (e.getKeyCode() == KeyEvent.VK_D) {

				  int chosenItem = list.getSelectedIndex();
				   Object sel = list.getModel().getElementAt(chosenItem);


                  String xda = sel.toString();
                  String descInv = getItem(xda)[1];
                  c.append("\n" +descInv);
            } **/

            if (e.getKeyCode() == KeyEvent.VK_S) {
                  int numToReplace = 0;
                  int replacement = 7;
                  // loop through each nested array
                  for (int i = 0; i < board.length; i++) {
                        // loop through each element of the nested array
                        for (int j = 0; j < board[i].length; j++) {
                              if (board[i][j] == numToReplace) {
                                    board[i][j] = replacement;
                              }
                        }
                  }
            }


// doors


// down check
 if (e.isShiftDown() && e.getKeyCode() == KeyEvent.VK_DOWN)
{

for (String s : entrances) {
	String[] entranceCheck = s.split("\\|");

	int nowMap = currentMap;
	int tileNow = Integer.parseInt(entranceCheck[2]);
		int nowX = Integer.parseInt(entranceCheck[0]);
		int nowY = Integer.parseInt(entranceCheck[1]);
	int downY = Integer.parseInt(entranceCheck[1])-1;

if (!entranceCheck[4].equals("0")) {

	String itemReq = entranceCheck[4];
    if (currentMap == nowMap && spawnX == nowX && spawnY == downY) {
if(hasItem(getItem(itemReq)[0])) {
		c.append("\nYou unlock with help from an item.");
		board[nowY][nowX] = tileNow;
	}else{
		c.append("\nYou need a " + getItem(itemReq)[0] + " to open.");
	}
	}
}else{

	    if (currentMap == nowMap && spawnX == nowX && spawnY == downY) {

			c.append("\nYou unlock it.");
			board[nowY][nowX] = tileNow;
	}

}
}
} // end down check


// right check
 if (e.isShiftDown() && e.getKeyCode() == KeyEvent.VK_RIGHT)
{

for (String s : entrances) {
	String[] entranceCheck = s.split("\\|");
	int nowMap = currentMap;
	int tileNow = Integer.parseInt(entranceCheck[2]);
		int nowX = Integer.parseInt(entranceCheck[0]);
		int nowY = Integer.parseInt(entranceCheck[1]);
	int leftX = Integer.parseInt(entranceCheck[0])-1;

if (!entranceCheck[4].equals("0")) {

	String itemReq = entranceCheck[4];
    if (currentMap == nowMap && spawnX == leftX && spawnY == nowY) {
if(hasItem(getItem(itemReq)[0])) {
		c.append("\nYou unlock  with help from an item.");
		board[nowY][nowX] = tileNow;
	}else{
		c.append("\nYou need a " + getItem(itemReq)[0] + " to open.");
	}
	}
}else{

	    if (currentMap == nowMap && spawnX == leftX && spawnY == nowY) {

			c.append("\nYou unlock it.");
			board[nowY][nowX] = tileNow;
	}

}
}
} // end right check

// left check
 if (e.isShiftDown() && e.getKeyCode() == KeyEvent.VK_LEFT)
{

for (String s : entrances) {
	String[] entranceCheck = s.split("\\|");
	int nowMap = currentMap;
	int tileNow = Integer.parseInt(entranceCheck[2]);
		int nowX = Integer.parseInt(entranceCheck[0]);
		int nowY = Integer.parseInt(entranceCheck[1]);
	int leftX = Integer.parseInt(entranceCheck[0])+1;

if (!entranceCheck[4].equals("0")) {

	String itemReq = entranceCheck[4];
    if (currentMap == nowMap && spawnX == leftX && spawnY == nowY) {
if(hasItem(getItem(itemReq)[0])) {
		c.append("\nYou unlock  with help from an item.");
		board[nowY][nowX] = tileNow;
	}else{
		c.append("\nYou need a " + getItem(itemReq)[0] + " to open.");
	}
	}
}else{

	    if (currentMap == nowMap && spawnX == leftX && spawnY == nowY) {

			c.append("\nYou unlock it.");
			board[nowY][nowX] = tileNow;
	}

}
}
} // end left check


 
// up check
 if (e.isShiftDown() && e.getKeyCode() == KeyEvent.VK_UP)
{

for (String s : entrances) {
	String[] entranceCheck = s.split("\\|");
	int nowMap = currentMap;
	int tileNow = Integer.parseInt(entranceCheck[2]);
		int nowX = Integer.parseInt(entranceCheck[0]);
		int nowY = Integer.parseInt(entranceCheck[1]);
	int downY = Integer.parseInt(entranceCheck[1])+1;

if (!entranceCheck[4].equals("0")) {

	String itemReq = entranceCheck[4];
    if (currentMap == nowMap && spawnX == nowX && spawnY == downY) {
if(hasItem(getItem(itemReq)[0])) {
		c.append("\nYou unlock with help from an item.");
		board[nowY][nowX] = tileNow;
	}else{
		c.append("\nYou need a " + getItem(itemReq)[0] + " to open.");
	}
	}
}else{

	    if (currentMap == nowMap && spawnX == nowX && spawnY == downY) {

			c.append("\nYou unlock it.");
			board[nowY][nowX] = tileNow;
	}

}
}
} // end up check

            // PICK UP
            if (e.getKeyCode() == KeyEvent.VK_Z) {

pressedG = true;

//int itemNo = 0;



boolean onceNow = false;

for (int i = 0; i < droppedItems.size(); i++) {

if (!onceNow) {
	
				String[] checkDrop = droppedItems.get(i).split("\\|");
				////System.out.println(droppedItems.get(i) + " -- ID of weapon: " + checkDrop[0] + " (Name: "+ getItem(checkDrop[0])[0] +" [ID: "+ getItem(checkDrop[0])[8] +"]) X:" + checkDrop[1] + " Y: " + checkDrop[2] + "\n\n");
//System.out.println("Current Map: " + currentMap + "| Item's map: " + checkDrop[3] + " -- My X " + spawnX + "| Item's X " + checkDrop[1] + " -- My Y " + spawnY + "| Item's Y " + checkDrop[2] + " ???");
if (!checkDrop[0].equals("7")) {
	
				
				if (currentMap == Integer.parseInt(checkDrop[3]) && spawnX == Integer.parseInt(checkDrop[1]) && spawnY == Integer.parseInt(checkDrop[2])) {

c.append("\nYou picked up a " + getItem(checkDrop[0])[0] + ".");
	myCommand = "pickup";
itemIDdropped = getItem(checkDrop[0])[8];
onceNow = true;
				}
}

}

            }


 } // end pickup _G

/*
 
            try {
            	 
  
                  if (blocked(spawnX - 1, spawnY) == true && spawnX == mapX) {
                        left = false;
                        //System.out.println("You can't go left!");
                       
                  }
                  
                  if (blocked(spawnX + 1, spawnY) == true && spawnX == 0) {
                        right = false;
                        //System.out.println("You can't go right!");
                        c.append("\ncant go right did this. Line: 1078");
                  }
                  if (blocked(spawnX, spawnY + 1) == true && spawnX == mapX) {
                        down = false;
                        //System.out.println("You can't go down!");
                  }
                  if (blocked(spawnX, spawnY - 1) == true && spawnX == mapX) {
                        up = false;
                        //System.out.println("You can't go up!");
                  }

            } catch (ArrayIndexOutOfBoundsException xe) {
                  xe.printStackTrace();
            }
            System.out.println("6 CAN DO IT!"); */
            
            try {
                  if (blocked(spawnX + 1, spawnY) == true || pushBoard[spawnY][spawnX+1] != 0)
                        right = false;


            } catch (ArrayIndexOutOfBoundsException xe) {
                  //System.out.println("\n\ncc Border AIOOB Exception!!\n\n");
            }

            try {
                  if (blocked(spawnX - 1, spawnY) == true || pushBoard[spawnY][spawnX-1] != 0)
                        left = false;
            } catch (ArrayIndexOutOfBoundsException xe) {
                  //System.out.println("\n\ncc Border AIOOB Exception!!\n\n");
            }
           
            try {
                  if (blocked(spawnX, spawnY + 1) == true || pushBoard[spawnY+1][spawnX] != 0)
                        down = false;
            } catch (ArrayIndexOutOfBoundsException xe) {
                  //System.out.println("\n\ncc Border AIOOB Exception!!\n\n");
            }

            try {
                  if (blocked(spawnX, spawnY - 1) == true || pushBoard[spawnY-1][spawnX] != 0)
                        up = false;
            } catch (ArrayIndexOutOfBoundsException xe) {
                  //System.out.println("\n\ncc Border AIOOB Exception!!\n\n");
            }

           

            if (blocked(12, 7) == true && spawnX == mapX && spawnY - 1 == 1 - mapX) {
                  up = false;
            }

            int r1 = lastX + 1;
            if (lastX > 0) {
                  r1 = lastX + 1;
            } else {
                  r1 = 0;
            }
            int r2 = lastY;

            int u1 = lastX;
            int u2;
            if (lastY > 0) {
                  u2 = lastY - 1;
            } else {
                  u2 = 0;
            }

            int l1;
            if (spawnX > 0) {
                  l1 = lastX - 1;
            } else {
                  l1 = 0;
            }
            int l2 = spawnY;

            int d1 = lastX;
            int d2;
            if (lastY > 0) {
                  d2 = lastY + 1;
            } else {
                  d2 = 0;
            }
            
            try {
                  if (blocked(r1, r2) == true || pushBoard[spawnY][spawnX+1] != 0)
                        right = false; // we cannot go right

                  if (blocked(u1, u2) == true || pushBoard[spawnY-1][spawnX] != 0)
                        up = false; // we cannot go up
                  if (blocked(l1, l2) == true || pushBoard[spawnY][spawnX-1] != 0) {
                        left = false; // we cannot go left
                 
                  }
                  if (blocked(d1, d2) == true || pushBoard[spawnY+1][spawnX] != 0)
                        down = false; // we cannot go down

            } catch (ArrayIndexOutOfBoundsException xe) {
                  //System.out.println("\n\nFuture block Array Index Out of Bounds Exception!!\n\n");
            }
            
            leftMap = 0;
            upMap = 0;
            rightMap = 0;
            downMap = 0;

            boolRC = false;
            boolLC = false;
            boolUC = false;
            boolDC = false;

            // Border Checks
            if (spawnX == mapX && e.getKeyCode() == KeyEvent.VK_RIGHT && moveRight == true) {
                  boolRC = true;
            }

            if (spawnX == 0 && e.getKeyCode() == KeyEvent.VK_LEFT && moveLeft == true) {
                  boolLC = true;
            }

            if (spawnY == 0 && e.getKeyCode() == KeyEvent.VK_UP && moveUp == true) {
                  boolUC = true;
            }

            if (spawnY == mapY && e.getKeyCode() == KeyEvent.VK_DOWN && moveDown == true) {
                  boolDC = true;
            }

            /** MAP SWITCHING **/
            /** MAP SWITCHING **/
            /** MAP SWITCHING **/


// map route
            

            
            
        	for (String s : maplist) {
        	    //c.append("\nS: " + s + " data.getTitle() " + data.getTitle());
        		if (Integer.parseInt(s) == currentMap) {
        			/** c.append("\nMAP: " + data.getTitle());
        			
                    c.append("\nRightMap == " + data.getRightMap());
                    c.append("\nLeftMap == " + data.getLeftMap());
        			**/
        			
        			if (boolLC) {
        			
        			leftMap = dataNow.getIDS().get(0).getLeftMap();
        			rightMap = data.getIDS().get(0).getRightMap();
        			}else if(boolRC) {
        			leftMap = data.getIDS().get(0).getLeftMap();
            		rightMap = dataNow.getIDS().get(0).getRightMap();
        			}
        			
        			if (boolDC) {
            			
            			downMap = dataNow.getIDS().get(0).getDownMap();
            			upMap = data.getIDS().get(0).getUpMap();
            			}else if(boolUC) {
            			downMap = data.getIDS().get(0).getDownMap();
                		upMap = dataNow.getIDS().get(0).getUpMap();
            			}
        		}
        	}

           

            if (leftMap == 0 && spawnX == 0) {
                  left = false;   
            }
            if (rightMap == 0 && spawnX == mapX)
                  right = false;

            if (downMap == 0 && spawnY == mapY)
                  down = false;
            if (upMap == 0 && spawnY == 0)
                  up = false;

            if (e.getKeyCode() == KeyEvent.VK_UP && up == true && moveUp == true) {
                  spawnY = spawnY - 1;
                  stepsTill++;
                  //System.out.println("You went up.");
            }
            if (e.getKeyCode() == KeyEvent.VK_DOWN && down == true && moveDown == true) {
                  spawnY = spawnY + 1;
                  stepsTill++;
                  //System.out.println("You went down.");
            }
            if (e.getKeyCode() == KeyEvent.VK_RIGHT && right == true && moveRight == true) {
                  spawnX = spawnX + 1;
                  stepsTill++;
                  //System.out.println("You went right.");
            }
            if (e.getKeyCode() == KeyEvent.VK_LEFT && left == true && moveLeft == true) {
                  spawnX = spawnX - 1;
                  stepsTill++;
                  //System.out.println("You went left.");
            }

            // RIGHT TO LEFT
            if (boolRC == true && right == true) {
                  spawnX = 0;
                  lastX = spawnX;
                  //spawnY = spawnY;
                  board = loadBoard(rightMap);
                  pushBoard = loadPush(1);
                  px = 0;

                  int currMapToLoad = currentMap;
                  currentMap = dataNow.getIDS().get(0).getRightMap();
                   
   				File directory = new File(System.getenv("APPDATA") + "\\.mystik\\maps");
 				String filename[] = directory.list();
 				leftFileLoad = 0;
 				for (int qi = 0; qi < filename.length; qi++) {
 					
 			String listFilenames = System.getenv("APPDATA") + "\\.mystik\\maps\\"+filename[qi];
 			//System.out.println("CURRENT!!! >> " + listFilenames);
 			
 if (dataNow.getIDS().get(0).getRightMap() == currentMap) {

 					try {
 						fr = new FileReader(listFilenames);
 					}catch(FileNotFoundException fne) {
 						fne.printStackTrace();
 					}
 						    StringBuffer sb = new StringBuffer();
 						    char[] b = new char[1000];
 						    int n = 0;
 						    try {
 						    while ((n = fr.read(b)) > 0) {
 						         sb.append(b, 0, n);
 							 }
 							 }catch(IOException rex) {
 								 rex.printStackTrace();
 							 }
 						    String fileStringxT = sb.toString();
 				//System.out.println(fileStringxT);
 							try {
 								dataMap = new Gson().fromJson(fileStringxT, Data.class);
 								}catch (Exception er) {
 									er.printStackTrace();
 								}
 								
 								if (currMapToLoad == dataArray[qi].getIDS().get(0).getLeftMap()) {
 			
 									
 									try {
 										   for (int row = 0; row < dataArray[qi].getMap().length; row++) {
 											      for (int col = 0; col < dataArray[qi].getMap()[row].length; col++)
 											      {
 											        int index = dataArray[qi].getMap()[row][col];
 											        		board[row][col] = index;
 											      }
 											    }
 										}catch(Exception rere) { rere.printStackTrace(); }
 										
 										dataNow.getIDS().get(0).setRightMap(dataArray[qi].getIDS().get(0).getRightMap());
										dataNow.getIDS().get(0).setLeftMap(currMapToLoad);
										
										dataNow.getIDS().get(0).setUpMap(dataArray[qi].getIDS().get(0).getUpMap());
										dataNow.getIDS().get(0).setDownMap(dataArray[qi].getIDS().get(0).getDownMap());
										roomName = dataArray[qi].getTitle();
										
 								}
 				} 

 leftFileLoad = qi;
 //c.append("\nleftFileLoad: " + qi);


 				}
 				currMapToLoad = dataNow.getIDS().get(0).getRightMap();
                  
            }
            
          //c.append("\nRight Map: : " + dataNow.getRightMap() + " | Left Map: " + dataNow.getLeftMap());
          //c.append("\nUp Map: : " + dataNow.getUpMap() + " | Down Map: " + dataNow.getDownMap());

            // LEFT TO RIGHT
            if (boolLC == true && left == true) {
            	
      
                  spawnX = mapX;
                  lastX = spawnX;
			
						
						
                  pushBoard = loadPush(1);
                  px = rightSide;
                  //py = py;
                 int currMapToLoad = currentMap;
                 currentMap = dataNow.getIDS().get(0).getLeftMap();
                  
  				File directory = new File(System.getenv("APPDATA") + "\\.mystik\\maps");
				String filename[] = directory.list();
				leftFileLoad = 0;
				for (int qi = 0; qi < filename.length; qi++) {
					
			String listFilenames = System.getenv("APPDATA") + "\\.mystik\\maps\\"+filename[qi];
			//System.out.println("CURRENT!!! >> " + listFilenames);
			
if (dataNow.getIDS().get(0).getLeftMap() == currentMap) {

					try {
						fr = new FileReader(listFilenames);
					}catch(FileNotFoundException fne) {
						fne.printStackTrace();
					}
						    StringBuffer sb = new StringBuffer();
						    char[] b = new char[1000];
						    int n = 0;
						    try {
						    while ((n = fr.read(b)) > 0) {
						         sb.append(b, 0, n);
							 }
							 }catch(IOException rex) {
								 rex.printStackTrace();
							 }
						    String fileStringxT = sb.toString();
				System.out.println(fileStringxT);
							try {
								dataMap = new Gson().fromJson(fileStringxT, Data.class);
								}catch (Exception er) {
									er.printStackTrace();
								}
								
								if (currMapToLoad == dataArray[qi].getIDS().get(0).getRightMap()) {
			
									
									try {
										   for (int row = 0; row < dataArray[qi].getMap().length; row++) {
											      for (int col = 0; col < dataArray[qi].getMap()[row].length; col++)
											      {
											        int index = dataArray[qi].getMap()[row][col];
											        		board[row][col] = index;
											      }
											    }
										}catch(Exception rere) { rere.printStackTrace(); }
										dataNow.getIDS().get(0).setRightMap(currMapToLoad);
										dataNow.getIDS().get(0).setLeftMap(dataArray[qi].getIDS().get(0).getLeftMap());
										
										dataNow.getIDS().get(0).setUpMap(dataArray[qi].getIDS().get(0).getUpMap());
										dataNow.getIDS().get(0).setDownMap(dataArray[qi].getIDS().get(0).getDownMap());
										roomName = dataArray[qi].getTitle();
										
								}
				} 


leftFileLoad = qi;
//c.append("\nleftFileLoad: " + qi);


				}
				currMapToLoad = dataNow.getIDS().get(0).getLeftMap();
				
				
            }
            // TOP TO BOTTOM
           if (boolUC == true && up == true) {
                  spawnY = mapY;
                  lastY = spawnY;
                  //spawnX = spawnX;
                  board = loadBoard(upMap);
                  pushBoard = loadPush(1);
                 // px = px;
                  py = downSide;

                  int currMapToLoad = currentMap;
                  currentMap = dataNow.getIDS().get(0).getUpMap();
                   
   				File directory = new File(System.getenv("APPDATA") + "\\.mystik\\maps");
 				String filename[] = directory.list();
 				leftFileLoad = 0;
 				for (int qi = 0; qi < filename.length; qi++) {
 					
 			String listFilenames = System.getenv("APPDATA") + "\\.mystik\\maps\\"+filename[qi];
 			//System.out.println("CURRENT!!! >> " + listFilenames);
 			
 			
 if (dataNow.getIDS().get(0).getUpMap() == currentMap) {

 					try {
 						fr = new FileReader(listFilenames);
 					}catch(FileNotFoundException fne) {
 						fne.printStackTrace();
 					}
 						    StringBuffer sb = new StringBuffer();
 						    char[] b = new char[1000];
 						    int n = 0;
 						    try {
 						    while ((n = fr.read(b)) > 0) {
 						         sb.append(b, 0, n);
 							 }
 							 }catch(IOException rex) {
 								 rex.printStackTrace();
 							 }
 						    String fileStringxT = sb.toString();
 				System.out.println(fileStringxT);
 							try {
 								dataMap = new Gson().fromJson(fileStringxT, Data.class);
 								}catch (Exception er) {
 									er.printStackTrace();
 								}
 								
 								if (currMapToLoad == dataArray[qi].getIDS().get(0).getDownMap()) {
 			
 									
 									try {
 										   for (int row = 0; row < dataArray[qi].getMap().length; row++) {
 											      for (int col = 0; col < dataArray[qi].getMap()[row].length; col++)
 											      {
 											        int index = dataArray[qi].getMap()[row][col];
 											        		board[row][col] = index;
 											      }
 											    }
 										}catch(Exception rere) { rere.printStackTrace(); }
 										// c.append("\nYou're on " + dataArray[qi].getTitle() + "!");
 										dataNow.getIDS().get(0).setUpMap(dataArray[qi].getIDS().get(0).getUpMap());
										dataNow.getIDS().get(0).setDownMap(currMapToLoad);
										
										dataNow.getIDS().get(0).setRightMap(dataArray[qi].getIDS().get(0).getRightMap());
										dataNow.getIDS().get(0).setLeftMap(dataArray[qi].getIDS().get(0).getLeftMap());
										roomName = dataArray[qi].getTitle();
 								}
 				} 

 leftFileLoad = qi;
 //c.append("\nleftFileLoad: " + qi);


 				}
 				currMapToLoad = dataNow.getIDS().get(0).getUpMap();
            }
 
           // BOTTOM TO TOP
            if (boolDC == true && down == true) {
                  spawnY = 0;
                  lastY = spawnY;
                  //spawnX = spawnX;
                  board = loadBoard(downMap);
                  pushBoard = loadPush(downMap);
                  //px = px;
                  py = 0;

                  int currMapToLoad = currentMap;
                  currentMap = dataNow.getIDS().get(0).getDownMap();
                   
   				File directory = new File(System.getenv("APPDATA") + "\\.mystik\\maps");
 				String filename[] = directory.list();
 				leftFileLoad = 0;
 				for (int qi = 0; qi < filename.length; qi++) {
 					
 			String listFilenames = System.getenv("APPDATA") + "\\.mystik\\maps\\"+filename[qi];
 			//System.out.println("CURRENT!!! >> " + listFilenames);
 			
 			
 if (dataNow.getIDS().get(0).getDownMap() == currentMap) {

 					try {
 						fr = new FileReader(listFilenames);
 					}catch(FileNotFoundException fne) {
 						fne.printStackTrace();
 					}
 						    StringBuffer sb = new StringBuffer();
 						    char[] b = new char[1000];
 						    int n = 0;
 						    try {
 						    while ((n = fr.read(b)) > 0) {
 						         sb.append(b, 0, n);
 							 }
 							 }catch(IOException rex) {
 								 rex.printStackTrace();
 							 }
 						    String fileStringxT = sb.toString();
 				System.out.println(fileStringxT);
 							try {
 								dataMap = new Gson().fromJson(fileStringxT, Data.class);
 								}catch (Exception er) {
 									er.printStackTrace();
 								}
 								
 								if (currMapToLoad == dataArray[qi].getIDS().get(0).getUpMap()) {
 			
 									
 									try {
 										   for (int row = 0; row < dataArray[qi].getMap().length; row++) {
 											      for (int col = 0; col < dataArray[qi].getMap()[row].length; col++)
 											      {
 											        int index = dataArray[qi].getMap()[row][col];
 											        		board[row][col] = index;
 											      }
 											    }
 										}catch(Exception rere) { rere.printStackTrace(); }
 										 c.append("\nYou're on " + dataArray[qi].getTitle() + "!");
 										dataNow.getIDS().get(0).setDownMap(dataArray[qi].getIDS().get(0).getDownMap());
										dataNow.getIDS().get(0).setUpMap(currMapToLoad);
										
										dataNow.getIDS().get(0).setRightMap(dataArray[qi].getIDS().get(0).getRightMap());
										dataNow.getIDS().get(0).setLeftMap(dataArray[qi].getIDS().get(0).getLeftMap());
										roomName = dataArray[qi].getTitle();						
										
 								}
 				} 

 leftFileLoad = qi;
 //c.append("\nleftFileLoad: " + qi);


 				}
 				currMapToLoad = dataNow.getIDS().get(0).getDownMap();
                  
                  
            }

            /** MAP SWITCHING **/
            /** MAP SWITCHING **/
            /** MAP SWITCHING **/

            

           // String currX = Integer.toString(spawnX);
           // String currY = Integer.toString(spawnY);
            //System.out.println("Current/Last Y: " + currY + "/" + lastY);
            //System.out.println("Current/Last X: " + currX + "/" + lastX + " - "+ board[spawnY][spawnX] + "\n\n");

            if (boolLC == false) {
                  if (left == true) {
                        if (e.getKeyCode() == KeyEvent.VK_LEFT && moveLeft == true) {
                              left = true;
                              px = px - 32;
                              lastX = lastX - 1;
                        }
                  }
            } else {
                  if (leftMap > 0)
                        px = rightSide;
            }

            if (boolRC == false) {
                  if (right == true) {
                        if (e.getKeyCode() == KeyEvent.VK_RIGHT && moveRight == true) {
                              right = true;
                              px = px + 32;
                              lastX = lastX + 1;
                        }
                  }
            } else {
                  if (rightMap > 0)
                        px = 0;
            }

            if (boolDC == false) {

                  if (down == true) {
                        if (e.getKeyCode() == KeyEvent.VK_DOWN && moveDown == true) {
                              down = true;
                              py = py + 32;
                              lastY = lastY + 1;

                        }
                  }
            } else {
                  if (downMap > 0)
                        py = 0;

            }

            if (boolUC == false) {
                  if (up == true) {

                        if (e.getKeyCode() == KeyEvent.VK_UP && moveUp == true) {
                              up = true;
                              py = py - 32;
                              lastY = lastY - 1;
                        }
                  }
            } else {
                  if (upMap > 0)
                        py = downSide;
            }

          

// Teleport Handler
for (String q : teleports) {
	// teleports.add(Integer.toString(fromX) 0
	//+ "|" + Integer.toString(fromY) + "|" 1
	//+ Integer.toString(fromMap) + "|" 2
	//+ Integer.toString(toX) + "|" 3
	//+ Integer.toString(toY) 4
	//+ "|" + Integer.toString(toMap)); 5
	System.out.println(q);

	String[] teleportCheck = q.split("\\|");

	if (Integer.parseInt(teleportCheck[2]) == currentMap) {

		if (spawnX == Integer.parseInt(teleportCheck[0]) && spawnY == Integer.parseInt(teleportCheck[1])) {



if (!teleportCheck[6].equals("0")) {
	String itemReqTele = teleportCheck[6];
	if(hasItem(getItem(itemReqTele)[0])) {
			   board = loadBoard(Integer.parseInt(teleportCheck[5]));
			   pushBoard = loadPush(Integer.parseInt(teleportCheck[5]));
			   px = Integer.parseInt(teleportCheck[3])*32;
			   py = Integer.parseInt(teleportCheck[4])*32;
			   currentMap = Integer.parseInt(teleportCheck[5]);
			   spawnX = Integer.parseInt(teleportCheck[3]);
			   spawnY = Integer.parseInt(teleportCheck[4]);
			   lastX = spawnX;
			   lastY = spawnY;
			   repaint();
	}else{
		c.append("\nYou need a " + getItem(itemReqTele)[0] + " before you can go through...");
	}


}else{
			   board = loadBoard(Integer.parseInt(teleportCheck[5]));
			   pushBoard = loadPush(Integer.parseInt(teleportCheck[5]));
			   px = Integer.parseInt(teleportCheck[3])*32;
			   py = Integer.parseInt(teleportCheck[4])*32;
			   currentMap = Integer.parseInt(teleportCheck[5]);
			   spawnX = Integer.parseInt(teleportCheck[3]);
			   spawnY = Integer.parseInt(teleportCheck[4]);
			   lastX = spawnX;
			   lastY = spawnY;
			   repaint();
		   }
		}

	}

} // end teleports

            me = players.get(me.getUsername());

            if (e.getKeyCode() == KeyEvent.VK_ENTER) {

                  myCommand = "chat";

                  myCommand = "chat";
                  //os.println(me.getUsername() + "|"+ currentMap +"|" + say.getText() + "|yes|chat");
                  c.append("\n"+me.getUsername()+": " + say.getText());
                  say.setText("");
                 
checkCommand();

            }

            repaint();


            me.setX(px);
            me.setY(py);
            me.setMap(currentMap);


//c.append("\nMy Command: " + myCommand);


if (myCommand == "pickup") {

beforeCheck = "yes";
             //os.println(itemIDdropped + "|" + spawnX + "|" + spawnY + "|"+ currentMap + "|pickup|" + me.getUsername()+ "|yes");


for (int i = 0; i < droppedItems.size(); i++) {
	String[] clientUp = droppedItems.get(i).split("\\|");

	if (currentMap == Integer.parseInt(clientUp[3]) && spawnX == Integer.parseInt(clientUp[1]) && spawnY == Integer.parseInt(clientUp[2])) {

droppedItems.remove(clientUp[0] + "|" + clientUp[1] + "|" + clientUp[2] + "|" + clientUp[3] + "|drop|" + clientUp[5] + "|" + clientUp[6]);
model.addElement(getItem(clientUp[0])[0]);

}
}
            }else if (myCommand == "drop") {



				  //    os.println(itemIDdropped + "|" + spawnX + "|" + spawnY + "|" + currentMap + "|drop|" + me.getUsername() + "|yes");
				//System.out.print("dropped:: " + itemIDdropped + "|" + spawnX + "|" + spawnY + "|" + currentMap + "|pickup|" + me.getUsername() + "|yes");
}

            me.setCommand(myCommand);



 labelY.setText("Y: " + spawnY);
            labelX.setText("X: " + spawnX);

            System.out.println("Y: " + spawnY + " | X: " + spawnX +"\n============");
            
            File filea=new File(System.getenv("APPDATA") + "\\.mystik\\main.txt");
		  
		    try {
         if (roomName == null) {
		    labelRoom.setText(dataNow.getTitle());
		    }else{
            labelRoom.setText(roomName);
		    }
            
		    } catch(Exception hapa) { }
		   
		   //c.append("\ndataNow.getTitle(): " + dataNow.getTitle() + " | data.getTitle(): " + data.getTitle() + " | Room name:" + roomName);
            
             c.setCaretPosition(c.getDocument().getLength());






            repaint();


      }

      public  String setRoom(int e) {
		  if (e == 1) roomName = "Respawn Point";
		  if (e == 2) roomName = "Stoney Trail";
		  if (e == 3) roomName = "Mapacide";
		  if (e == 4) roomName = "Pushapolis";

		  return roomName;
	  }

      public void keyReleased(KeyEvent e) {

      } // ignore

      public void keyTyped(KeyEvent e) {
      } // ignore

      public void addtoInv(String e) {

		  model.addElement(getItem(e)[0]);

	  }

      public void addInv(String e) {



            c.append("\nxYou picked up a " + getItem(e)[0] + ".");
            // for (String item : arr) list.add(item);
            model.addElement(getItem(e)[0]);
            //arr.add(getItem(e)[0]);
      }

      public String[] getMonster(String q) {

            String mName = "Null";
            String mHP = "1";
            String mDesc = "None.";
            String mGold = "1";
            String mExp = "1";
            String mItems = "0";
            String monsterID = "0";
            String mLevel = "0";

            if (q.equals("1") || q.equals("Goblin")) {

				mName = "Goblin";
				mHP = "10";
				mDesc = "Ugly greedy, usually green, creaters.";
				mGold = "6";
				mExp = "6";
				mItems = "1,2,3,4";
				monsterID = "1";
				mLevel = "4";

			}

            if (q.equals("2") || q.equals("Arachnid")) {

				mName = "Arachnid";
				mHP = "3";
				mDesc = "A giant creepy arachnid";
				mGold = "2";
				mExp = "2";
				mItems = "1,2,3,4";
				monsterID = "2";
				mLevel = "1";

			}

            if (q.equals("3") || q.equals("Eagle")) {

				mName = "Eagle";
				mHP = "20";
				mDesc = "A giant creepy arachnid";
				mGold = "15";
				mExp = "13";
				mItems = "1,2,3,4,1,2,3,4,1,6";
				monsterID = "3";
				mLevel = "9";

			}

            if (q.equals("4") || q.equals("Orc Chieftan")) {

				mName = "Orc Chieftan";
				mHP = "10";
				mDesc = "A deadly purple Orc waiting to strike.";
				mGold = "10";
				mExp = "9";
				mItems = "1,2,3,4";
				monsterID = "4";
				mLevel = "11";

			}
            
            if (q.equals("5") || q.equals("Water Beast")) {

				mName = "Water Beast";
				mHP = "13";
				mDesc = "A beast from the water with hair from Medusa.";
				mGold = "12";
				mExp = "11";
				mItems = "1,2,4";
				monsterID = "5";
				mLevel = "12";

			}
            
            if (q.equals("6") || q.equals("Sand Drake")) {

				mName = "Sand Drake";
				mHP = "15";
				mDesc = "A deadly dragon covered in sand.";
				mGold = "15";
				mExp = "15";
				mItems = "1,1,1,1,2,2,2,3,4,4,4,4,5";
				monsterID = "6";
				mLevel = "16";

			}
            
            if (q.equals("7") || q.equals("Human Mage")) {

				mName = "Human Mage";
				mHP = "17";
				mDesc = "Mastered the arts of wizardry and tom-foolery.";
				mGold = "16";
				mExp = "19";
				mItems = "1,2";
				monsterID = "7";
				mLevel = "18";

			}
            
            if (q.equals("8") || q.equals("Fiery Salamandar")) {

				mName = "Fiery Salamandar";
				mHP = "15";
				mDesc = "A salamandar covered in sleek red.";
				mGold = "5";
				mExp = "14";
				mItems = "1,2,3,4";
				monsterID = "8";
				mLevel = "14";

			}
            
            if (q.equals("9") || q.equals("Skeleton Warrior")) {

				mName = "Skeleton Warrior";
				mHP = "18";
				mDesc = "A warrior risen from the grave.";
				mGold = "14";
				mExp = "14";
				mItems = "1,2,3,4";
				monsterID = "9";
				mLevel = "15";

			}
            
            if (q.equals("10") || q.equals("Human Warrior")) {

				mName = "Human Warrior";
				mHP = "21";
				mDesc = "Dangerous with blades.";
				mGold = "18";
				mExp = "20";
				mItems = "1,2,3,4,1,1,1,2,2,2,2,2,5";
				monsterID = "10";
				mLevel = "19";

			}
            
            if (q.equals("11") || q.equals("Captain")) {

				mName = "Captain";
				mHP = "20";
				mDesc = "A skillfull warrior in his top phisique.";
				mGold = "20";
				mExp = "23";
				mItems = "1,2,3,1,1,1,1,1,1,1,1,1,1,2,2,2,2,4,5";
				monsterID = "11";
				mLevel = "22";

			}
            
            if (q.equals("13") || q.equals("Taurus")) {

				mName = "Taurus";
				mHP = "30";
				mDesc = "Stunning display with flail accuracy. Watch out.";
				mGold = "26";
				mExp = "22";
				mItems = "1,2,3,4,1,1,1,2,2,2,2,4,5,1,1,1,2,2,2,2,4,5,1,1,1,2,2,2,2,4,5";
				monsterID = "13";
				mLevel = "7";

			}
            
            if (q.equals("13") || q.equals("Maggot")) {

				mName = "Maggot";
				mHP = "4";
				mDesc = "Eww, a maggot.";
				mGold = "3";
				mExp = "3";
				mItems = "1";
				monsterID = "13";
				mLevel = "3";

			}
            
            if (q.equals("14") || q.equals("Catapillar")) {

				mName = "Catapillar";
				mHP = "3";
				mDesc = "It's an insect -- the *drum roll* catapillar!";
				mGold = "2";
				mExp = "2";
				mItems = "1";
				monsterID = "14";
				mLevel = "4";

			}
            
            if (q.equals("15") || q.equals("Wasp")) {

				mName = "Wasp";
				mHP = "2";
				mDesc = "Kill it, quickly! They sting and stuff.";
				mGold = "1";
				mExp = "1";
				mItems = "1";
				monsterID = "15";
				mLevel = "1";

			}
            
            if (q.equals("16") || q.equals("Human Knight")) {

				mName = "Human Knight";
				mHP = "19";
				mDesc = "Mastered the skill of a swordmen.";
				mGold = "16";
				mExp = "15";
				mItems = "1,2,3,4,1,2,3,4,1,2,3,4,1,2,3,4";
				monsterID = "16";
				mLevel = "21";

			}
            
            if (q.equals("17") || q.equals("Basilisk")) {

				mName = "Basilisk";
				mHP = "25";
				mDesc = "A deadly creature from the old times.";
				mGold = "22";
				mExp = "19";
				mItems = "1,2,3,4";
				monsterID = "17";
				mLevel = "25";

			}
            
            if (q.equals("18") || q.equals("Red Dragon")) {

				mName = "Red Dragon";
				mHP = "28";
				mDesc = "A fire-breathing dragon.";
				mGold = "29";
				mExp = "29";
				mItems = "5";
				monsterID = "18";
				mLevel = "30";

			}
            

            return new String[] { mName, mHP, mDesc, mGold, mExp, mItems, monsterID, mLevel};

	  }

      public  String[] getItem(String e) {

		  	/**
		  	Types of Armor

		  	1 - shield
		  	2 - weapon
		  	3 - torso armor
		  	4 - legging armor
		  	5 - head

		  	Type of Item

		  	1 - Weapon
		  	2 - Armor
		  	3 - Quest Item
		  	4 - Special Item

		  	**/

           // String[] stats = new String[9];

            String name = "Null";
            String desc = "None";
            //String typeOfArmor = "0";
            String attackAdd = "0";
            String defenseAdd = "0";
            String canSell = "false";
            String canEat = "false";
            String earnedCoins = "0";
            String typeOfItem = "0";
            String itemID = "0";

            if (e.equals("1") || e.equals("Battleaxe")) {

			                  name = "Battleaxe";
			                  typeOf = "2";
			                  attackAdd = "4";
			                  earnedCoins = "10";
			                  typeOfItem = "1";
			                  itemID = "1";
			                  desc = "Double the sharpness, double the fun!";
            }

            if (e.equals("2") || e.equals("Flail")) {

                  name = "Flail";
                  typeOf = "2";
                  attackAdd = "2";
                  earnedCoins = "5";
                  typeOfItem = "1";
                  itemID = "2";
                  desc = "A spiked ball on a chain with a handle. Neat.";
            }

            if (e.equals("3") || e.equals("Sword")) {

			                  name = "Sword";
			                  typeOf = "2";
			                  attackAdd = "6";
			                  earnedCoins = "15";
			                  typeOfItem = "1";
			                  itemID = "3";
			                  desc = "Only the most generic weapon in today's RPGs... ever.";
            }

            if (e.equals("4") || e.equals("Big Hammer")) {

			                  name = "Big Hammer";
			                  typeOf = "2";
			                  attackAdd = "5";
			                  earnedCoins = "25";
			                  typeOfItem = "1";
			                  itemID = "4";
			                  desc = "Can be used to move large rocks and boulders. Also serves as a weapon.";
            }

            if (e.equals("5") || e.equals("Mace")) {

			                  name = "Mace";
			                  typeOf = "1";
			                  attackAdd = "2";
			                  earnedCoins = "15";
			                  typeOfItem = "1";
			                  itemID = "5";
			                  desc = "Used by magical wizards.";
            }

            if (e.equals("6") || e.equals("Golden Platechest")) {

			                  name = "Golden Platechest";
			                  typeOf = "3";
			                  defenseAdd = "10";
			                  earnedCoins = "35";
			                  typeOfItem = "2";
			                  itemID = "6";
			                  desc = "One of the rarest golden armor pieces in the game.";
            }

            if (e.equals("7") || e.equals("Sign")) {

                name = "Sign";
                typeOfItem = "4";
                itemID = "7";
                desc = "Used to create signs.";
}


            return new String[] { name, desc, typeOf, attackAdd, defenseAdd,
                        canSell, canEat, earnedCoins, itemID, typeOfItem};

      }

      public void showInventory() {

            for (int i = 0; i < model.getSize(); i++) {
                  //System.out.println(model.getElementAt(i));
            }
      }

public int[][] loadPush(int pushMap) {
	if (pushMap == 1) {
		return new int[][] {
{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0
 },
{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0
 },
{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0
 },
{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0
 },
{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0
 },
{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0
 },
{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0
 },
{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0
 },
{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0
 },
{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0
 },
{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0
 }
};
}else if (pushMap == 4) {

	return new int[][] {
{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0
 },
{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0
 },
{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 133, 133, 0, 0
 },
{ 0, 0, 0, 0, 0, 0, 133, 0, 133, 0, 133, 133, 0, 0, 0, 0
 },
{ 0, 0, 0, 0, 0, 0, 133, 133, 133, 133, 133, 0, 0, 0, 0, 0
 },
{ 0, 0, 0, 0, 0, 0, 0, 133, 0, 0, 133, 133, 133, 133, 0, 0
 },
{ 0, 0, 0, 0, 0, 133, 0, 133, 0, 0, 0, 133, 0, 0, 0, 0
 },
{ 0, 0, 0, 0, 0, 0, 0, 133, 0, 133, 0, 0, 0, 0, 0, 0
 },
{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0
 },
{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0
 },
{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0
 }
};


 }else if (pushMap == 5) {

	return new int[][] {
{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0
 },
{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0
 },
{ 0, 0, 145, 0, 145, 0, 145, 145, 145, 0, 145, 0, 145, 0, 0, 0
 },
{ 0, 0, 145, 145, 145, 0, 145, 0, 145, 0, 145, 0, 145, 0, 0, 0
 },
{ 0, 0, 0, 0, 145, 0, 145, 0, 145, 0, 145, 0, 145, 0, 0, 0
 },
{ 0, 0, 145, 145, 145, 0, 145, 145, 145, 0, 145, 145, 145, 0, 0, 0
 },
{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0
 },
{ 0, 0, 145, 0, 0, 0, 145, 0, 145, 0, 145, 145, 0, 145, 0, 0
 },
{ 0, 0, 145, 0, 145, 0, 145, 0, 145, 0, 145, 0, 145, 145, 0, 0
 },
{ 0, 0, 0, 145, 0, 145, 0, 0, 145, 0, 145, 0, 0, 145, 0, 0
 },
{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0
 }
};


}else if (pushMap == 2 || pushMap == 3 || pushMap == 6 || pushMap == 7) {
		return new int[][] {
{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0
 },
{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0
 },
{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0
 },
{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0
 },
{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0
 },
{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0
 },
{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0
 },
{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0
 },
{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0
 },
{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0
 },
{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0
 }
};
}
return pushBoard;
}

      public int[][] loadBoard(int map) {
            if (map == 1) {
                  return new int[][] {
                		  { 1, 1, 1, 1, 1, 1, 1, 190, 115, 1, 1, 1, 1, 1, 1, 2},
                		  { 190, 190, 190, 190, 190, 190, 190, 190, 13, 148, 148, 148, 148, 148, 121, 2},
                		  { 1, 520, 127, 127, 127, 127, 127, 13, 13, 148, 167, 167, 167, 148, 343, 1},
                		  { 1, 520, 127, 166, 166, 166, 127, 13, 13, 148, 167, 167, 167, 148, 343, 1},
                		  { 1, 520, 127, 166, 166, 166, 127, 13, 13, 148, 148, 148, 183, 148, 343, 1},
                		  { 1, 520, 364, 174, 127, 361, 127, 13, 13, 13, 13, 13, 13, 13, 13, 1},
                		  { 115, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 115},
                		  { 1, 514, 13, 13, 394, 343, 145, 220, 145, 145, 145, 13, 13, 13, 13, 1},
                		  { 1, 514, 13, 13, 343, 118, 145, 166, 166, 166, 145, 13, 13, 13, 13, 1},
                		  { 1, 514, 514, 13, 118, 118, 145, 166, 166, 522, 145, 13, 13, 13, 13, 1},
                		  { 1, 1, 1, 115, 1, 1, 145, 145, 145, 145, 145, 1, 1, 1, 1, 1}
};
            

									}

            return board;
      }

      public boolean blocked(int tx, int ty) {
            return BLOCKED_TILES.contains(board[ty][tx]);
      }
      
      public int addAttack(int e) {
    	  attack = attack + Integer.parseInt(Integer.toString(e));
    	  lAttack.setText("Attack ["+attack+"]");
    	  return attack;
    }
      
      public int subAttack(int e) {
    	  attack = attack - Integer.parseInt(Integer.toString(e));
    	  lAttack.setText("Attack ["+attack+"]");
    	  return attack;
    }
      
      public int getLevels(int exp) {
    	  
    	  if (exp < 52) { level = 2;}
    	  else if (exp < 110) { level = 3;}
    	  else if (exp < 175) { level = 4;}
    	  else if (exp < 246) { level = 5;}
    	  else if (exp < 325) { level = 6;}
    	  else if (exp < 413) { level = 7;}
    	  else if (exp < 510) { level = 8;}
    	  else if (exp < 616) { level = 9;}
    	  else if (exp < 734) { level = 10;}
    	  else if (exp < 865) { level = 11;}
    	  else if (exp < 1008) { level = 12;}
    	  else if (exp < 1167) { level = 13;}
    	  else if (exp < 1342) { level = 14;}
    	  else if (exp < 1536) { level = 15;}
    	  else if (exp < 1749) { level = 16;}
    	  else if (exp < 1985) { level = 17;}
    	  else if (exp < 2245) { level = 18;}
    	  else if (exp < 2531) { level = 19;}
    	  else if (exp < 2848) { level = 20;}
    	  else if (exp < 3197) { level = 21;}
    	  else if (exp < 3582) { level = 22;}
    	  else if (exp < 4007) { level = 23;}
    	  else if (exp < 4476) { level = 24;}
    	  else if (exp < 4993) { level = 25;}
    	  else if (exp < 5564) { level = 26;}
    	  else if (exp < 6193) { level = 27;}
    	  else if (exp < 6888) { level = 28;}
    	  else if (exp < 7655) { level = 29;}
    	  else if (exp < 8502) { level = 30;}
    	  else if (exp < 9435) { level = 31;}
    	  else if (exp < 10466) { level = 32;}
    	  else if (exp < 11603) { level = 33;}
    	  else if (exp < 12858) { level = 34;}
    	  else if (exp < 14243) { level = 35;}
    	  else if (exp < 15772) { level = 36;}
    	  else if (exp < 17459) { level = 37;}
    	  else if (exp < 19321) { level = 38;}
    	  else if (exp < 21376) { level = 39;}
    	  else if (exp < 23644) { level = 40;}
    	  else if (exp < 26148) { level = 41;}
    	  else if (exp < 28912) { level = 42;}
    	  else if (exp < 31962) { level = 43;}
    	  else if (exp < 35329) { level = 44;}
    	  else if (exp < 39046) { level = 45;}
    	  else if (exp < 43149) { level = 46;}
    	  else if (exp < 47677) { level = 47;}
    	  else if (exp < 52677) { level = 48;}
    	  else if (exp < 58195) { level = 49;}
    	  else if (exp < 64288) { level = 50;}
    	  else if (exp < 71013) { level = 51;}
    	  else if (exp < 78437) { level = 52;}
    	  else if (exp < 86633) { level = 53;}
    	  else if (exp < 95681) { level = 54;}
    	  else if (exp < 105670) { level = 55;}
    	  else if (exp < 116697) { level = 56;}
    	  else if (exp < 128871) { level = 57;}
    	  else if (exp < 142311) { level = 58;}
    	  else if (exp < 157148) { level = 59;}
    	  else if (exp < 173529) { level = 60;}
    	  else if (exp < 191614) { level = 61;}
    	  else if (exp < 211579) { level = 62;}
    	  else if (exp < 233622) { level = 63;}
    	  else if (exp < 257958) { level = 64;}
    	  else if (exp < 284825) { level = 65;}
    	  else if (exp < 314487) { level = 66;}
    	  else if (exp < 347236) { level = 67;}
    	  else if (exp < 383392) { level = 68;}
    	  else if (exp < 423310) { level = 69;}
    	  else if (exp < 467382) { level = 70;}
    	  else if (exp < 516039) { level = 71;}
    	  else if (exp < 569760) { level = 72;}
    	  else if (exp < 629070) { level = 73;}
    	  else if (exp < 694553) { level = 74;}
    	  else if (exp < 766850) { level = 75;}
    	  else if (exp < 846671) { level = 76;}
    	  else if (exp < 934799) { level = 77;}
    	  else if (exp < 1032098) { level = 78;}
    	  else if (exp < 1139523) { level = 79;}
    	  else if (exp < 1258129) { level = 80;}
    	  else if (exp < 1389078) { level = 81;}
    	  else if (exp < 1533655) { level = 82;}
    	  else if (exp < 1693280) { level = 83;}
    	  else if (exp < 1869518) { level = 84;}
    	  else if (exp < 2064099) { level = 85;}
    	  else if (exp < 2278932) { level = 86;}
    	  else if (exp < 2516125) { level = 87;}
    	  else if (exp < 2778005) { level = 88;}
    	  else if (exp < 3067142) { level = 89;}
    	  else if (exp < 3386373) { level = 90;}
    	  else if (exp < 3738830) { level = 91;}
    	  else if (exp < 4127973) { level = 92;}
    	  else if (exp < 4557619) { level = 93;}
    	  else if (exp < 5031985) { level = 94;}
    	  else if (exp < 5555725) { level = 95;}
    	  else if (exp < 6133979) { level = 96;}
    	  else if (exp < 6772421) { level = 97;}
    	  else if (exp < 7477315) { level = 98;}
    	  else if (exp < 8255580) { level = 99;}
    	  
    	  return level;
    	  
      }

      public boolean pushable(int pushableTile) {

		  		if (pushableTile != 0) {
					return true;
			}else{
			return false;
		}

      }

      public boolean isInBound(int r, int c) {
            return (r >= 0) && (r < 8) && (c >= 12) && (c < 1);
      }

      class GamePanel extends JPanel {

	
		//private static final long serialVersionUID = -7066484297336098626L;
		

    	  
    	  
            public void paintComponent(Graphics g) {
            	/**
    			for (int row = 0; row < data.getMap().length; row++) {
                    for (int col = 0; col < data.getMap()[row].length; col++) {

                    	int index = data.getMap()[row][col];
                    	board[row][col] = index;
                        g.drawImage(tiles[index], 32 * col, 32 * row, this);
               

                    }
              }**/
            	
            	
                for (row = 0; row < board.length; row++) {
                    for (col = 0; col < board[row].length; col++) {
                          int index = board[row][col];
                          g.drawImage(tiles[index], 32 * col, 32 * row, this);

                    }
              } 
                this.getParent().validate();
                getToolkit().sync();
            	/** do this for WHOLE tile image load
                  for (row = 0; row < board.length; row++) {
                        for (col = 0; col < board[row].length; col++) {
                              int index = board[row][col];
                              int newIndex = index - 1;
                              g.drawImage(tiles[newIndex], 32 * col, 32 * row, this);
                        }
                  } **/
try {
                  for (prow = 0; prow < pushBoard.length; prow++) {
                        for (pcol = 0; pcol < pushBoard[prow].length; pcol++) {
                              int pindex = pushBoard[prow][pcol];
                              if (pindex != 0) {
                              g.drawImage(tiles[pindex], 32 * pcol, 32 * prow, this);
						  }

                        }
                  }
			  }catch(Exception ew) { ew.printStackTrace(); }



                  // qq
                  /**
                   * for(int runx = 0;runx < 10;runx++) { g.drawImage(userImg[runx],
                   * userPX[runx], userPY[runx], this); }
                   **/

                   for (int i = 0; i < droppedItems.size(); i++) {
				   				String[] drawItem = droppedItems.get(i).split("\\|");

				   				//add everything plus one???

				                
				                if(currentMap == Integer.parseInt(drawItem[3])) {
									int itemIDDraw = Integer.parseInt(drawItem[0])-1;
									int itemX = Integer.parseInt(drawItem[1]);
									int itemY = Integer.parseInt(drawItem[2]);
									
							
									
									try {
									g.drawImage(weapon[itemIDDraw], 32 * itemX, 32 * itemY, this);
								}catch(Exception re) { }
									myCommand = "drop";
								} //end if on item

            } // end for loop


              // monsters drop
            for (int i = 0; i < monstersActive.size(); i++) {
				   				String[] drawMonster = monstersActive.get(i).split("\\|");

				                int monsterNo = Integer.parseInt(getMonster(drawMonster[0])[6]) - 1;
				                	//System.out.println("currentMap: " + currentMap + " drawMonster[3]: " drawMonster[3]);
				                if(currentMap == Integer.parseInt(drawMonster[3])) {
									int monsterDrawID = Integer.parseInt(drawMonster[0])-1;
									int dMonsX = Integer.parseInt(drawMonster[1]);
									int dMonsY = Integer.parseInt(drawMonster[2]);
									try {
									g.drawImage(monster[monsterDrawID], 32 * dMonsX, 32 * dMonsY, this);
								}catch(Exception re) { }
									myCommand = "addMonster";
								} //end if on item

            } // end for loop

            try {


                for (Player player : players.values()) {
                      //System.out.println("++++++++++++++++++++++++++");
                      //System.out.println("current map: " + player.getMap());
                      if (player.getUsername() == me.getUsername())
                            player.setMap(currentMap);
                      if (player.getUsername() == me.getUsername())
                            player.setCommand(myCommand);
                      if (player.getMap() == currentMap) {
                            g.drawImage(player.getPlayerImage(), player.getX(), player
                                        .getY(), this);

                      }
                      //System.out.println("Command-- " + player.getCommand() + " > "
                              //    + player.getUsername() + " moved to X:" + player.getX()
                                 // + " and Y: " + player.getY());

                      //System.out.println("++++++++++++++++++++++++++");
                }
          } catch (Exception dan) {
                //System.out.println("No one is on.");
          }
            
            
            int runOnce = 0;
            while (runOnce > 1) {
				repaint();
				runOnce++;
			}




            }

      }
      
      /**
       public static void main(String[] args)
      {
        JFrame frame=new JFrame("Mystik RPG - v2.2");
        applet=new Game();
        
        frame.setFocusable(true);
        frame.getContentPane().add(applet,BorderLayout.CENTER);
        frame.setSize(646,558);
        frame.addWindowListener(new WindowAdapter()
           {public void windowClosing(WindowEvent evt)
             { applet.stop(); applet.destroy(); System.exit(0); 
           } } );
        
        applet.init();
        applet.start();    
        frame.setVisible(true);
        frame.requestFocusInWindow();
        frame.setFocusable(true);
        say.requestFocusInWindow();
      } **/ 
      
} // end whole thing


