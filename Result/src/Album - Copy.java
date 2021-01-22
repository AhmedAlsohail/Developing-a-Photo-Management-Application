public class Album {
	private String name;
	private String condition;
	private PhotoManager manager;
	// Constructor
	public Album(String name, String condition, PhotoManager manager) {
		this.name = name;
		this.condition = condition;
		this.manager = manager;
	}
	
	// Return the name of the album
	public String getName() {
		return name;
	}
	
	// Return the condition associated with the album
	public String getCondition() {
		return condition;
	}
	
	// Return the manager
	public PhotoManager getManager() {
		return manager;
	}
	
	// Return all photos that satisfy the album condition
	public LinkedList<Photo> getPhotos(){
		//first declare the LinkedList that we will return.
		LinkedList<Photo> Photos = new LinkedList<>();
		//get the BST from the photo manager.
		BST<LinkedList<Photo>> p = manager.getPhotos();
		//number of tags
		int count = getNbTags();
		//array containing the conditions.
		String tags[] = new String[count];
		//other variables we will use

		//the condition is empty then it include everything
		if(condition == "" || condition == " AND ") {
			//make new LinkedList of type String which have all tags 
			return manager.getPhotoList();
		}
		
		//use TagsArray Method to put each tag separetly in the array.
		tags = TagsArray();

		//now use the array to search for each tag in the BST.
		fillLL(Photos, tags, count, p);
		/*
		//for loop to go through tags[] array
		for(i = 0; i< count; i++) {
			//if we do not find the tag in BST we skip it.
			if(!(p.findKey(tags[i])))
					break;
			//if it is true then we will retrieve it in the LinkedList t.
			//this temporary LinkedList will have the photos with this tag.
			LinkedList<Photo> t = p.retrieve();
		
			t.findFirst();	
			
			//if statement is for adding the first photo in the our List (Photo) from 't' LinkedList.
			if(Photos.empty()) {
				Photos.insert(t.retrieve());
				//if statement to check if this is the only photo we need to add
				if(t.last())
					break;
				t.findNext();
			}
			
			//after the first one we will add the photos in 't' LinkedList from the second one until before the last one.
			while(!t.last()) {
				//go to the first image in Photos
				Photos.findFirst();
				//this flag is to check if we are going to add the photo or not.
					flag = false;
					//go through the Photos LinkedList to make sure nothing duplicate.
					while(!Photos.last()) {
						//if statement to check if this photo from "t LinkedList" does not already exist.
						//if it dosen't then the flag will be true.
						if(!Photos.retrieve().equals(t.retrieve()))
							flag = true;
						Photos.findNext();
					}
					//repeat one last time for the last photo.
					if(!Photos.retrieve().equals(t.retrieve()))
						flag = true;
				
				//check if the flag became true then we add the photo.
				if(flag)
					Photos.insert(t.retrieve());
				//go the the next t to do the same thing again.
				t.findNext();
			}
			
			//repeat one last time for the last photo.
			Photos.findFirst();
			flag = false;
			while(!Photos.last()) {
				if(!Photos.retrieve().equals(t.retrieve()))
					flag = true;
				Photos.findNext();
			}
			if(!Photos.retrieve().equals(t.retrieve()))
				flag = true;
		
		if(flag) {
			Photos.insert(t.retrieve());
		}
		}*/
		

		//now we will remove the photos that have one or more condition but not all of them.
		if(Photos.empty())
			return Photos;
		Photos.findFirst();
		
		if(Photos.last())
			return Photos;
		
		while(!Photos.last()) {
			//I have the array tags from before which have the conditions
			//now I will make a temp array for the current Photos tags
			LinkedList<String> tempLL = Photos.retrieve().getTags();
			String[] tempArray = tempLL.toStringArray();
			boolean keep[] = new boolean[tags.length];
			boolean next = true;

			//make them all false
			for(int r=0;r<tags.length;r++)
				keep[r] = false;
		
			for(int r =0; r<tags.length;r++) {
				for(int q=0; q<tempArray.length;q++) {
					if(tags[r].equals(tempArray[q]))
						keep[r] = true;
				}
			}
			
			for(int r=0;r<tags.length;r++) {
				if(keep[r] == false) {
					Photos.remove();
					next = false;
					break;
				}
			}
			
			if(next)
			Photos.findNext();
		}
		
		//I have the array tags from before which have the conditions
		//now I will make a temp array for the current Photos tags
		LinkedList<String> tempLL = Photos.retrieve().getTags();
		String[] tempArray = tempLL.toStringArray();
		boolean keep[] = new boolean[tags.length];
		boolean next = true;


		//make them all false
		for(int r=0;r<tags.length;r++)
			keep[r] = false;
	
		for(int r =0; r<tags.length;r++) {
			for(int q=0; q<tempArray.length;q++) {
				if(tags[r].equals(tempArray[q]))
					keep[r] = true;
			}
		}
		
		for(int r=0;r<tags.length;r++) {
			if(keep[r] == false) {
				Photos.remove();
				next = false;
				break;
			}
		}
		
		return Photos;
		}
	
	// Return the number of tag comparisons used to find all photos of the album
	public int getNbComps() {
		if(condition.equals(""))
			return 0;
			
		BST temp = manager.getPhotos();
		int count = getNbTags();
		String tags[] = new String[count];
		tags = TagsArray();
		int num = 0;

		for(int i=0; i<count;i++) {
			int t = temp.findKeySteps(tags[i]);
			num += t;
		}
		return num;
	}
	
	  /* 
	  return the number of tags in condition, for example
	  "Animal AND Bear AND Panda" will return 3
	  */ 
	private int getNbTags() {
		if(condition == null || condition == "")
			return 0;
		
		int count = 1;
		for(int i=0; i<condition.length();i++) {
			if(
					(i>=5) &&
					(condition.charAt(i) == ' ') &&
					(condition.charAt(i-1) == 'D') &&
					(condition.charAt(i-2) == 'N') &&
					(condition.charAt(i-3) == 'A') &&
					(condition.charAt(i-4) == ' ')
			  )
				count++;
		}
		return count;
	}
	
	//return array with the condition separated.
	private String[] TagsArray() {
		int i=0;
		int k=0;
		int j=0;
		int count = getNbTags();
		String tags[] = new String[count];
		//make array of Strings with the conditions.
		//for loop to go through each character in the conditions.
		for(i =0; i<condition.length()-1;i++) {
			
		/*  if the Character we are currently in are " " (space) and the next are "A","N","D" and another " ".
			then we know this is " AND " and take the substring from k to i which will be our tag.
			i+4 <= condition.length()-1 , this is because we won't find " AND " in the last 4 characters
			and if we tried to we will get null pointer
	   */
			if(		(i+4 <= condition.length()-1) &&
					(condition.charAt(i) == ' ') &&
					(condition.charAt(i+1) == 'A') &&
					(condition.charAt(i+2) == 'N') &&
					(condition.charAt(i+3) == 'D') &&
					(condition.charAt(i+4) == ' ')) {
				//this if statement is needed when condition starts with " AND " so we don't count it.
				if(i != 0) {
					//insert the substring from k to i.
					tags[j] = condition.substring(k,i);
					//increase j
					j++;
				}
				//i + 4 , because i are currently in the beginning of " AND " and we want to skip it,
				//it is +4 instead of +5 because the for loop increase the i value so it will be "i+5"
				i += 4;
				//k is the start of the tag name, which is i+1, since we already increased i by 4.
				k = i+1;
			}
		}
		//this if statement is needed when last condition is only one char.
		if(k==i-1)
			tags[j] = condition.substring(k,i);
		else {
			tags[j] = condition.substring(k,i+1);

		}
		return tags;
	}
	
	//return LL with all photos.
	private void fillLL(LinkedList<Photo> Photos, String tags[], int count, BST<LinkedList<Photo>> p) {

		boolean flag;
		//for loop to go through tags[] array
		for(int i = 0; i< count; i++) {
			//if we do not find the tag in BST we skip it.
			if((p.findKey(tags[i]))) {

				//if it is true then we will retrieve it in the LinkedList t.
				//this temporary LinkedList will have the photos with this tag.
				LinkedList<Photo> t = p.retrieve();
			
				t.findFirst();
				
				//if statement is for adding the first photo in the our List (Photo) from 't' LinkedList.
				if(Photos.empty()) {
					Photos.insert(t.retrieve());
					//if statement to check if this is the only photo we need to add
					if(!t.last())
					t.findNext();
				}
				
				//after the first one we will add the photos in 't' LinkedList from the second one until before the last one.
				while(!t.last()) {
					//go to the first image in Photos
					Photos.findFirst();
					//this flag is to check if we are going to add the photo or not.
						flag = true;
						//go through the Photos LinkedList to make sure nothing duplicate.
						while(!Photos.last()) {
							//if statement to check if this photo from "t LinkedList" does not already exist.
							//if it dosen't then the flag will be true.
							if(Photos.retrieve().equals(t.retrieve()))
								flag = false;
							Photos.findNext();
						}
						//repeat one last time for the last photo.
						if(Photos.retrieve().equals(t.retrieve()))
							flag = false;
					
					//check if the flag became true then we add the photo.
					if(flag)
						Photos.insert(t.retrieve());
					//go the the next t to do the same thing again.
					t.findNext();
				}
				
				//repeat one last time for the last photo.
				Photos.findFirst();
				flag = true;
				while(!Photos.last()) {
					if(Photos.retrieve().equals(t.retrieve()))
						flag = false;
					Photos.findNext();
				}
				if(Photos.retrieve().equals(t.retrieve()))
					flag = false;
			
			if(flag) {
				Photos.insert(t.retrieve());
			}
			}
		}
	}
	
}
