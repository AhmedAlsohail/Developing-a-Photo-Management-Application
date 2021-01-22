

public class test4 {

	public static void main(String[] args) {
		
		LinkedList<String> tag1 = new LinkedList<String>();
		tag1.insert("animal");
		tag1.insert("headgehog");
		tag1.insert("apple");
		tag1.insert("grass");

		LinkedList<String> tag2 = new LinkedList<String>();
		tag2.insert("animal");
		tag2.insert("bear");
		tag2.insert("cub");
		tag2.insert("grass");

		LinkedList<String> tag3 = new LinkedList<String>();
		tag3.insert("insect");
		tag3.insert("butterfly");
		tag3.insert("flower");
		tag3.insert("color");

		LinkedList<String> tag4 = new LinkedList<String>();
		tag4.insert("insect");
		tag4.insert("butterfly");
		tag4.insert("flower");
		tag4.insert("black");

		LinkedList<String> tag5 = new LinkedList<String>();
		tag5.insert("animal");
		tag5.insert("fox");
		tag5.insert("tree");
		tag5.insert("grass");

		LinkedList<String> tag6 = new LinkedList<String>();
		tag6.insert("animal");
		tag6.insert("bear");
		tag6.insert("panda");
		tag6.insert("grass");

		LinkedList<String> tag7 = new LinkedList<String>();
		tag7.insert("animal");
		tag7.insert("wolf");
		tag7.insert("sky");
		tag7.insert("snow");

		LinkedList<String> tag8 = new LinkedList<String>();
		tag8.insert("animal");
		tag8.insert("raccoon");
		tag8.insert("log");
		tag8.insert("snow");

		Photo p1 = new Photo("hedgehog.jpg", tag1);
		Photo p2 = new Photo("bear.jpg", tag2);
		Photo p3 = new Photo("butterfly1.jpg", tag3);
		Photo p4 = new Photo("butterfly2.jpg", tag4);
		Photo p5 = new Photo("fox.jpg", tag5);
		Photo p6 = new Photo("panda.jpg", tag6);
		Photo p7 = new Photo("wolf.jpg", tag7);
		Photo p8 = new Photo("raccoon.jpg", tag8);
		Photo p9 = new Photo("naruto.jpg", new LinkedList<String>());

		PhotoManager pm = new PhotoManager();

		pm.addPhoto(p1);
		pm.addPhoto(p2);
		pm.addPhoto(p3);
		pm.addPhoto(p4);
		pm.addPhoto(p5);
		pm.addPhoto(p6);
		pm.addPhoto(p7);
		pm.addPhoto(p8);
		pm.addPhoto(p9);
		
		Album a = new Album("bears", "animal", pm);

		System.out.println(a.getNbComps());

	}

}