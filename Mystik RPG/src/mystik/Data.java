package mystik;

import java.util.List;

	public class Data {

		public static String MapTitle;
		public static Data data;
		public static ItemLoad item;
		public static MonsterLoad monster;
		public static EntranceLoad entrance;
		public static TeleportLoad teleport;
		public static SignLoad sign;
		public static NumberLoad id;
		public static String dan;
		public static int number;
		public static int tile;
		public static String[] wepN;

	            private String title;
	            private List<ItemLoad> items;
	            private List<MonsterLoad> monsters;
	            private List<EntranceLoad> entrances;
	            private List<TeleportLoad> teleports;
	            private List<NumberLoad> ids;
	            private List<SignLoad> signs;
	            private int[][] map;

	            
	            public String getTitle() { return title; }
	            public List<ItemLoad> getItems() { return items; }
	            public List<MonsterLoad> getMonsters() { return monsters; }
	            public List<EntranceLoad> getEntrances() { return entrances; }
	            public List<TeleportLoad> getTeleports() { return teleports; }
	            public List<NumberLoad> getIDS() { return ids; }
	            public List<SignLoad> getSigns() { return signs; }
	            public int[][] getMap() { return map; }

	            public void setTitle(String title) { this.title = title; }
	            public void setItems(List<ItemLoad> items) { this.items = items; }
	            public void setMonsters(List<MonsterLoad> monsters) { this.monsters = monsters; }
	            public void setEntrances(List<EntranceLoad> entrances) { this.entrances = entrances; }
	            public void setTeleports(List<TeleportLoad> teleports) { this.teleports = teleports; }
	            public void setIDs(List<NumberLoad> ids) { this.ids = ids; }
	            public void setSigns(List<SignLoad> signs) { this.signs = signs; }
	            public void setMap(int[][] map) { this.map=  map; }
	        }
