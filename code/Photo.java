public class Photo {
	// Constructor
	public Photo(String path, LinkedList<String> tags);
	// Return the path (full file name) of the photo. A photo is uniquely identified by its path.
	public String getPath();
	// Return all tags associated with the photo
	public LinkedList<String> getTags();
}
