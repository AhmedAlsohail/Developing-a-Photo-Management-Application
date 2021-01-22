public class PhotoManager {
	// Constructor
	public Photomanager();
	// Add a photo
	public void addPhoto(Photo p);
	// Delete a photo
	public void deletePhoto(String path);
	// Return the inverted index of all managed photos
	public BST<LinkedList<Photo>> getPhotos();
}
