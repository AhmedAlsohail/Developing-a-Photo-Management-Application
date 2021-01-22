public class Album {
	// Constructor
	public Album(String name, String condition, PhotoManager manager);
	// Return the name of the album
	public String getName();
	// Return the condition associated with the album
	public String getCondition();
	// Return the manager
	public PhotoManager getManager();
	// Return all photos that satisfy the album condition
	public LinkedList<Photo> getPhotos();
	// Return the number of tag comparisons used to find all photos of the album
	public int getNbComps();
}
