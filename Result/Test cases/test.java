

public class test {

	public static void main(String[] args) {
		
		LinkedList<String> tag1 = new LinkedList<String>();
		tag1.insert("AABear");
		tag1.insert("Animal");
		tag1.insert("Flower");

		LinkedList<String> tag2 = new LinkedList<String>();
		tag2.insert("Flower");
		tag2.insert("Person");

		LinkedList<String> tag3 = new LinkedList<String>();
		tag3.insert("Animal");
		tag3.insert("Dog");
		
		LinkedList<String> tag4 = new LinkedList<String>();
		tag4.insert("Dog");
		tag4.insert("bear");
		
		Photo p1 = new Photo("Alo.jpg", tag1);
		Photo p2 = new Photo("Hlo.jpg", tag2);
		Photo p3 = new Photo("No.jpg", tag3);
		Photo p4 = new Photo("a.jpg", new LinkedList<>());
		
		PhotoManager pM = new PhotoManager();
		
		pM.addPhoto(p1);
		pM.addPhoto(p2);
		pM.addPhoto(p3);
		pM.addPhoto(p4);
		
		BST<LinkedList<Photo>> p = pM.getPhotos();
		
		p.findKey("");
		
		LinkedList<Photo> a = p.retrieve();
		a.findFirst();
		
		while(!a.last()) {
			System.out.println(a.retrieve().getPath());
			a.findNext();
		}
		System.out.print(a.retrieve().getPath());
		
		System.out.println("\n-----------------------------");
		BST<LinkedList<Photo>> k = pM.getPhotos();
		
		k.findKey("Dog");
		
		LinkedList<Photo> b = k.retrieve();
		b.findFirst();
		
		while(!b.last()) {
			System.out.println(b.retrieve().getPath());
			b.findNext();
		}
		System.out.print(b.retrieve().getPath());
	}
	
}
