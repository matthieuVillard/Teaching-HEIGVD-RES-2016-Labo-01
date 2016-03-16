package ch.heigvd.res.lab01.impl.explorers;

import ch.heigvd.res.lab01.interfaces.IFileExplorer;
import ch.heigvd.res.lab01.interfaces.IFileVisitor;
import java.io.File;

/**
 * This implementation of the IFileExplorer interface performs a depth-first
 * exploration of the file system and invokes the visitor for every encountered
 * node (file and directory). When the explorer reaches a directory, it visits all
 * files in the directory and then moves into the subdirectories.
 * 
 * @author Olivier Liechti
 */
public class DFSFileExplorer implements IFileExplorer {

  @Override
  public void explore(File rootDirectory, IFileVisitor v) {
    if(rootDirectory != null){

      // start visit method from visitor to visit the file and doing necessary tests (as checking if it is a file)
      v.visit(rootDirectory);

      // Do a DFS from the rootDirectory
      if(rootDirectory.isDirectory()){

        File[] filesToExplore = rootDirectory.listFiles(); // Get all the files in the rootDirectory

        // Call explore methode for each file / directory recursively
        for (File fileToExplore : filesToExplore) {
          explore(fileToExplore, v);
        }

      }
    }
  }

}
