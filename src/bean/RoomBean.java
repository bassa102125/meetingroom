package bean;

public class RoomBean {
	//フィールド--------------------
		private static final long serialVersionUID =1L;
		private String id;
		private String name;
	//コンストラクタ--------------------
		public RoomBean(String id,String name) {
		this.id = id;
		this.name = name;
	}
	//メッソッド--------------------
		public String getId(){
			return id;
		}
		public String getName(){
			return name;
		}
		public String toString(){
			return "?";
		}
}
