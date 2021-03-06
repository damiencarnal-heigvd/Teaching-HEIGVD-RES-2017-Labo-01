package ch.heigvd.res.lab01.impl.explorers;

import ch.heigvd.res.lab01.interfaces.IFileExplorer;
import ch.heigvd.res.lab01.interfaces.IFileVisitor;
import java.io.File;
import java.util.Arrays;

/**
 * This implementation of the IFileExplorer interface performs a depth-first
 * exploration of the file system and invokes the visitor for every encountered
 * node (file and directory). When the explorer reaches a directory, it visits all
 * files in the directory and then moves into the subdirectories.
 * 
 * @author Olivier Liechti
 * @author Damien Carnal
 */
public class DFSFileExplorer implements IFileExplorer {

  @Override
  public void explore(File rootDirectory, IFileVisitor vistor) {
     
      vistor.visit(rootDirectory);

      if (rootDirectory.isDirectory()){
         // Creation d'un tableau avec le contenu du fichier
         File[] files = rootDirectory.listFiles();
         Arrays.sort(files);

         // On visite d'abord les fichier
         for (File file : files){
            if (file.isFile()){
               vistor.visit(file);
            }
         }

         // ensuite on explore en profondeur
         for (File file : files){
            if (file.isDirectory()){
               explore(file, vistor);
            }
         }
      }

  }

}
