package components;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

public class FileChooser {
	/*
	 * =============================
	 * 			  PROPS 
	 * =============================
	*/
	
	private JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView()
															  .getHomeDirectory()
												);
	
	
	/*
	 * =============================
	 * 			  METHODS 
	 * =============================
	*/
	
	public String openFile() {
		jfc.setFileSelectionMode( JFileChooser.FILES_ONLY );
		jfc.setDialogTitle("Import a picture");
		jfc.setMultiSelectionEnabled(false);
		jfc.setAcceptAllFileFilterUsed(false);
		jfc.addChoosableFileFilter( new FileNameExtensionFilter("PNG and JPG images", "png", "jpg"));
		
		int returnValue = jfc.showDialog(null, "Import");

		if (returnValue == JFileChooser.APPROVE_OPTION) {
			File selectedFile = jfc.getSelectedFile();
			
			return selectedFile.getAbsolutePath();
		}
		
		return null;
	}
	
	public String saveFile() {
		int returnValue = jfc.showSaveDialog(null);
		
		if(returnValue == JFileChooser.APPROVE_OPTION) {
			File selectedFile = jfc.getSelectedFile();
			
			return selectedFile.getAbsolutePath();
		}
		
		return null;
	}
}
