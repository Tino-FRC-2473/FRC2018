import java.io.File;

import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

public class RemoveUnmatchedFiles {

	//end result is that dirExtra has all the files that dirVal already has 
	//extra files are deleted
	private File[] dirExtra;
	private File[] dirVal;
	
	private ArrayList<String> fileExtraNames;
	private ArrayList<String> fileValNames;
	
	public static void main (String args[]){
		
		JFrame frame = new JFrame("FrameDemo");
		
		JFileChooser dirChooserImages = new JFileChooser();
		dirChooserImages.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		
		dirChooserImages.showOpenDialog(frame);
		
		//System.out.println(dirChooserImages.getCurrentDirectory());
		//System.out.println(dirChooserImages.getSelectedFile());
		
//		dirChooserImages.setCurrentDirectory(dirChooserImages.getSelectedFile());
//		System.out.println(dirChooserImages.getCurrentDirectory());
//		
//		//Make this file chooser a lot less sketch
//		//frame = new JFrame("FrameDemo");
//		//dirChooserImages.showOpenDialog(frame);
//		JFileChooser dirChooserLabels = new JFileChooser();
//		dirChooserLabels.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
//		
//		dirChooserLabels.showOpenDialog(frame);
//		
//		System.out.println(dirChooserLabels.getCurrentDirectory());
//		System.out.println(dirChooserLabels.getSelectedFile());
//		
//		dirChooserLabels.setCurrentDirectory(dirChooserLabels.getSelectedFile());
//		System.out.println(dirChooserLabels.getCurrentDirectory());
		
		
		
		
		String dir1 = dirChooserImages.getSelectedFile().getAbsolutePath(); 
		System.out.println(dir1);

		//make this less sketch
		String dir2 = dir1+"/LabelData";
		System.out.println(dir2);
		
		RemoveUnmatchedFiles sys = new RemoveUnmatchedFiles(dir1, dir2);
		//sys.deleteUnmatchedFile();
		System.out.println(sys.findUnmatchedFiles().toString());
		
		//System.out.println(sys.addSpaceEscapeCommand("i like pie yay more 1408sn "));
		//System.out.println(sys.addSpaceEscapeCommand(" blah  blah   blah  "));
		//System.out.println(sys.addSpaceEscapeCommand("nospacesinthistext"));

		System.exit(0);
	}

	//input 2 strings
	// 1. First parameter is directory folder of extra files
	// 2. Second parameter is directory for validation to check for extra files
	public RemoveUnmatchedFiles(String dirExtra, String dirVal){
		this.dirExtra = new File(dirExtra).listFiles();
		this.dirVal = new File(dirVal).listFiles();
		fileExtraNames = new ArrayList<String>(this.dirExtra.length);
		fileValNames = new ArrayList<String>(this.dirVal.length);
		
		for(File extra: this.dirExtra){
			String fileName = extra.getName();
			if(fileName.indexOf('.') != -1){
				fileName = fileName.substring(0, fileName.indexOf('.'));
				//since we do not want to remove folders :)
				fileExtraNames.add(fileName);
			}
		}
		
		for(File val: this.dirVal){
			String fileName = val.getName();
			fileName = fileName.substring(0, fileName.indexOf('.'));
			fileValNames.add(fileName);
		}
		
		//System.out.println(fileExtraNames.toString());
		//System.out.println(fileValNames.toString());
	}
	
	public String findUnmatchedFiles(){
		ArrayList<String> unmatchedExtra = (ArrayList<String>) fileExtraNames.clone();
		ArrayList<String> unmatchedVal = (ArrayList<String>) fileValNames.clone();
		
		ArrayList<String> matched = new ArrayList<String>();
		
		ArrayList<Integer> unmatchedExtraIndex = new ArrayList<Integer>(unmatchedExtra.size());
		ArrayList<Integer> unmatchedValIndex = new ArrayList<Integer>(unmatchedVal.size());
		
		//fill the index arrays with integers according to index
		for(int i = 0; i < unmatchedExtra.size(); i++){
			unmatchedExtraIndex.add(i);
		}
		for(int i = 0; i < unmatchedVal.size(); i++){
			unmatchedValIndex.add(i);
		}
		
		//loop through the string array to find which ones are unmatched and index
		for(int i = unmatchedExtra.size() - 1; i >= 0; i--){
			String s = unmatchedExtra.get(i);
			if(unmatchedVal.contains(s)){
				matched.add(unmatchedExtra.remove(i));
				unmatchedExtraIndex.remove(i);
				
				int index = unmatchedVal.indexOf(s);
				unmatchedVal.remove(index);
				unmatchedValIndex.remove(index);
				
			}
		}
		
		String terminalText = "rm ";
		
		//loop through index arrays to make a string
		for(int i = 0; i < unmatchedExtraIndex.size(); i++){
			int unmatchedFileIndex = unmatchedExtraIndex.get(i);
			String pathName = dirExtra[unmatchedFileIndex].getPath();
			
			System.out.println(pathName);
			
			if(pathName.contains(" ")){
				pathName = addSpaceEscapeCommand(pathName);
			}
			if(pathName.contains(".DS_Store")){
				//System.out.println("contineu");
				continue;
			}
			
			terminalText+= pathName + " ";
		}
		
		for(int i = 0; i < unmatchedValIndex.size(); i++){
			int unmatchedFileIndex = unmatchedValIndex.get(i);
			String pathName = dirVal[unmatchedFileIndex].getAbsolutePath();
			
			System.out.println(pathName);
			
			if(pathName.contains(" ")){
				pathName = addSpaceEscapeCommand(pathName);
			}
			if(pathName.contains(".DS_Store")){
				//System.out.println("contineu");
				continue;
			}
			
			terminalText+= pathName + " ";
		}
		
		System.out.println(unmatchedExtra.toString());
		System.out.println(unmatchedVal.toString());
		return terminalText;
		//return unmatchedExtra;
	}
	
	public String addSpaceEscapeCommand(String path){
		
		int spaceIndex = path.indexOf(' ');
		while(spaceIndex != -1){
			path = path.substring(0, spaceIndex) + "\\" + path.substring(spaceIndex);
			spaceIndex = path.indexOf(' ', spaceIndex + 2);
		}
		
		return path;
	}
	
	
	public void deleteUnmatchedFile(){
		int i = 0;
		boolean matchFound;
		while (i < dirExtra.length){
			matchFound = false;
			for (int j = 0; j < dirVal.length; j++){
				//if folder names match
				if ( doesMatch(dirExtra[i].getName(), dirVal[j].getName()) ){

					//match found, good to go
					matchFound = true;
					break;
				}	
			}

			//if match was not found then delete file
			if (!matchFound){
				System.out.println("delete");
				//				if (!dirExtra[i].delete()){
				//					System.out.println("Deleting was unsuccessful.");
				//				}
			}
			else {
				i++;
			}
			System.out.println("i" + i);
		}

	}

	//See if the two file directory paths have the same name
	// **Ignores everything after "."
	//Doesn't check type of file (.txt, .jpg, etc.)
	public boolean doesMatch(String fileName, String fileNameVal){

		if (fileName.indexOf(".") != -1){
			fileName = fileName.substring(0, fileName.indexOf("."));
			System.out.println("1: " + fileName);
		}
		if (fileNameVal.indexOf(".") != -1){
			fileNameVal = fileNameVal.substring(0, fileNameVal.indexOf("."));
			System.out.println("2: " + fileNameVal);
		}
		return (fileName == fileNameVal);
	}
}