package game;

import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.FancyGroundFactory;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.World;
import game.actor.Enia;
import game.actor.Trader;
import game.actor.player.Astrologer;
import game.actor.player.Bandit;
import game.actor.player.Player;
import game.actor.player.PlayerClass;
import game.actor.player.Samurai;
import game.actor.player.Wretch;
import game.ground.*;
import game.item.RemembranceOfGrafted;
import game.utils.FancyMessage;
import java.util.Arrays;
import java.util.List;

/**
 * The main class to start the game.
 * Created by:
 * @author Adrian Kristanto
 * Modified by: Yi Xuan Lim, Zhi Hui Tee
 *
 */

public class Application {

	public static void main(String[] args) {

		World world = new World(new Display());

		FancyGroundFactory groundFactory = new FancyGroundFactory(
				new Dirt(), new Wall(), new Floor(),
				new Graveyard(), new GustOfWind(), new PuddleOfWater(),
				new SiteOfLostGrace(), new Cliff(), new Barrack(), new Cage(), new SummonSign());

		List<String> map = Arrays.asList(
				"..nnnn................................................~~~~~~~~~~~~~~~~~~~~~",
				"......................#####....######..................~~~~~~~~~~~~~~~~~~~~",
				"..nnnn................#..___....____#...................~~~~~~~~~~~~~~~~~~~",
				"..................................__#....................~~~~~~~~~~~~~~~~~~",
				"......................._____........#.....................~~~~~~~~~~~~~~~~~",
				"......................#............_#......................~~~~~~~~~~~~~~~~",
				"......................#...........###......................................",
				"..........................=................................................",
				"...........................................................................",
				"~~~~~~~~~~~.......................###___###................................",
				"~~~~~~~~~~~~....................++__._____++nnnn...........................",
				"~~~~~~~~~~~~~................nnnnnn______nn++nnn...........................",
				"~~~~~~~~~~~~.................&&&&&&&__+___#..nnnn..........................",
				"~~~~~~~~~~~..................&&&&.###___###..nnnn..........................",
				"~~~~~~~~~~..........................#___#..................................",
				"..................................=........................................",
				"................................................................=..........",
				"...........................................................................",
				"..####__##...........................................&&&......######..##...",
				"..#.....__...........................................&&&......#....____....",
				"..#___..............&&&..............................&&&........__.....#...",
				"..####__###.........&&&......................................._.....__.#...",
				"....................&&&.......................................###..__###...",
				"...........................................................................");

		List<String> castle = Arrays.asList(
				"...........................................................................",
				"..................<...............<........................................",
				"...........................................................................",
				"##############################################...##########################",
				"............................#................#.......B..............B......",
				".....B...............B......#.&&&.......&&&..#.............................",
				"..............................&<&.......&<&................................",
				".....B...............B......#.&&&.......&&&..#.......B..............B......",
				"............................#................#.............................",
				"#####################..#############...############.####..#########...#####",
				"...............#++++++++++++#................#++++++++++++#................",
				"...............#++++++++++++...<.........<...#++++++++++++#................",
				"...............#++++++++++++..................++++++++++++#................",
				"...............#++++++++++++#................#++++++++++++#................",
				"#####...##########.....#############...#############..#############...#####",
				".._______........................B......B........................B.....B...",
				"_____..._..____....&&........<..............<..............................",
				".........____......&&......................................................",
				"...._______..................<..............<....................<.....<...",
				"#####....##...###..#####...##########___###############......##.....####...",
				"+++++++++++++++++++++++++++#...................#+++++++++++++++++++++++++++",
				"+++++++++++++++++++++++++++....................#+++++++++++++++++++++++++++",
				"+++++++++++++++++++++++++++#....................+++++++++++++++++++++++++++",
				"+++++++++++++++++++++++++++#...................#+++++++++++++++++++++++++++");

		List<String> roundtable = Arrays.asList(
				"##################",
				"#________________#",
				"#________________#",
				"#________________#",
				"#________________#",
				"#________________#",
				"#________________#",
				"#________________#", 
				"#________________#",
				"#________________#",
				"########___#######");

		List<String> boss = Arrays.asList(
				"+++++++++++++++++++++++++",
				".........................",
				"..=......................",
				".........................",
				".........................",
				".........................",
				".........................",
				".........................",
				"+++++++++++++++++++++++++"
		);

		// initialises game maps
		GameMap gameMap = new GameMap(groundFactory, map);
		GameMap castleMap  = new GameMap(groundFactory,castle);
		GameMap roundtableMap = new GameMap(groundFactory,roundtable);
		GameMap bossMap = new GameMap(groundFactory,boss);
		world.addGameMap(gameMap);
		world.addGameMap(castleMap);
		world.addGameMap(roundtableMap);
		world.addGameMap(bossMap);

		Display display = new Display();
		// BEHOLD, ELDEN RING
		for (String line : FancyMessage.ELDEN_RING.split("\n")) {
			display.println(line);
			try {
				Thread.sleep(200);
			} catch (Exception exception) {
				exception.printStackTrace();
			}
		}

		// Allow selection for the player class
		PlayerClass playerClass = null;
		while (playerClass == null) {
			display.print("Select your role:\na: Astrologer\nb: Bandit\ns: Samurai\nw: Wretch\n");
			switch (display.readChar()) {
				case 'a' -> playerClass = new Astrologer();
				case 'b' -> playerClass = new Bandit();
				case 's' -> playerClass = new Samurai();
				case 'w' -> playerClass = new Wretch();
			}
		}

		// set first site of grace
		SiteOfLostGrace firstSite = new SiteOfLostGrace();
		gameMap.at(38,11).setGround(firstSite);

		// initialise the doors to be placed on the maps
		GoldenFogDoor limgrave1 = new GoldenFogDoor(gameMap.at(36,11), "Limgrave");
		GoldenFogDoor limgrave2 = new GoldenFogDoor(gameMap.at(37,12),"Limgrave");
		GoldenFogDoor castle1 = new GoldenFogDoor(castleMap.at(36,11),"Stormveil Castle");
		GoldenFogDoor castle2 = new GoldenFogDoor(castleMap.at(37,12), "Stormveil Castle");
		GoldenFogDoor roundtable1 = new GoldenFogDoor(roundtableMap.at(10,5), "Roundtable Hold");
		GoldenFogDoor boss1 = new GoldenFogDoor(bossMap.at(10,5),"Boss room");

		// Place the doors inside the maps
		limgrave1.getSource().setGround(limgrave1);
		limgrave2.getSource().setGround(limgrave2);
		castle1.getSource().setGround(castle1);
		castle2.getSource().setGround(castle2);
		roundtable1.getSource().setGround(roundtable1);
		boss1.getSource().setGround(boss1);

		// Link the doors around
		// link limgrave1 with roundtable1
		limgrave1.setDestination(roundtable1.getSource(),roundtable1.getSourceName());
		// link roundtable1 with limgrave1
		roundtable1.setDestination(limgrave1.getSource(), limgrave1.getSourceName());

		// link limgrave2 with castle1
		limgrave2.setDestination(castle1.getSource(), castle1.getSourceName());
		// link castle1 with limgrave2
		castle1.setDestination(limgrave2.getSource(), limgrave2.getSourceName());

		// link castle2 with boss room
		castle2.setDestination(boss1.getSource(), boss1.getSourceName());
		// link boss room with castle2
		boss1.setDestination(castle2.getSource(), castle2.getSourceName());

		// Setup player and pre activate first site
		Player player = new Player(playerClass);
		player.setLastVisitedSite(gameMap.at(38,11));
		player.addItemToInventory(new RemembranceOfGrafted());
		world.addPlayer(player, gameMap.at(36, 10));

		// Add trader enia and merchant kale
		gameMap.at(38,10).addActor(new Enia());
		gameMap.at(39,12).addActor(new Trader());

		world.run();


	}




}
