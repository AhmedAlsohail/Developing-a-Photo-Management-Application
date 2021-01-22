
public class PhotoManager {
	private LinkedList<Photo> photoList;
	private BST<LinkedList<Photo>> tagsBST;
	
	public PhotoManager() {
		photoList = new LinkedList<>();
		tagsBST = new BST<>();
	}
	
	// Add a photo
	public void addPhoto(Photo p) {
		LinkedList<String> emptyTags = new LinkedList<>();
		emptyTags.insert("");
		Photo p2 = new Photo(p.getPath(), emptyTags);
		boolean flag = true;
		
		//if the photo has no path then we won't add it.
		if(p.getPath() == null)
			return ;
		
		
		if((p.getTags() == null || p.getTags().empty()))
			p = p2;
		
		//if the photoList is empty we add the photo.
		if(photoList.empty()) {
			photoList.insert(p);
			tagsBST = getPhotos();
			return;
		}
				
		//if it is not empty we have to check if it dosen't exist before.
		photoList.findFirst();
		
		while(!photoList.last()) {
			if(photoList.retrieve().getPath().equals(p.getPath()))
				flag = false;
			photoList.findNext();
		}
		if(photoList.retrieve().getPath().equals(p.getPath()))
			flag = false;
		
		if(flag)
		photoList.insert(p);
		
		tagsBST = getPhotos();
	}
	
	// Delete a photo
	public void deletePhoto(String path)
	{
		//if photoList is empty then there is nothing to delete.
		if(photoList.empty())
			return;
		
		photoList.findFirst();
		
		while(!photoList.last()) 
		{
			if(photoList.retrieve().getPath().equals(path))	{
				photoList.remove();	
				break;
			}
			photoList.findNext();
		}
		if(photoList.retrieve().getPath().equals(path))
			photoList.remove();
		
		if(!photoList.empty())
		tagsBST = getPhotos();

	}
	
	// Return the inverted index of all managed photos as BST
	public BST<LinkedList<Photo>> getPhotos(){
		BST <LinkedList<Photo>> photos = new BST<>();
		LinkedList<String> tags;
		
		if(photoList.empty())
			return photos;
		
		photoList.findFirst();
		
		while(!photoList.last()) {
			tags = photoList.retrieve().getTags();
			tags.findFirst();
			if(!tags.retrieve().equals("")) {
				while(!tags.last()) {
					if(!photos.findKey(tags.retrieve())) {
						photos.insert(tags.retrieve(), new LinkedList<Photo>());
						photos.retrieve().insert(photoList.retrieve());
					}else {
						photos.retrieve().insert(photoList.retrieve());
					}
					tags.findNext();
				}
				
				if(!photos.findKey(tags.retrieve())) {
					photos.insert(tags.retrieve(), new LinkedList<Photo>());
					photos.retrieve().insert(photoList.retrieve());
				}else {
					photos.retrieve().insert(photoList.retrieve());
				}
			}
			photoList.findNext();
		}
		
		tags = photoList.retrieve().getTags();
		tags.findFirst();
		if(!tags.retrieve().equals("")) {			
			while(!tags.last()) {
				if(!photos.findKey(tags.retrieve())) {
					photos.insert(tags.retrieve(), new LinkedList<Photo>());
					photos.retrieve().insert(photoList.retrieve());
				}else {
					photos.retrieve().insert(photoList.retrieve());
				}
				tags.findNext();
			}
			
			if(!photos.findKey(tags.retrieve())) {
				photos.insert(tags.retrieve(), new LinkedList<Photo>());
				photos.retrieve().insert(photoList.retrieve());
			}else {
				photos.retrieve().insert(photoList.retrieve());
			}
		}

		tagsBST = photos;
		return photos;
	}
	
	//getter for PhotoList
	public LinkedList<Photo> getPhotoList(){
		return photoList;
	}
	
}
