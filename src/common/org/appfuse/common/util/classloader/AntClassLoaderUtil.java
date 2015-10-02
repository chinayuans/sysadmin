package org.appfuse.common.util.classloader;

import java.io.File;
import java.io.FilenameFilter;

import org.apache.tools.ant.AntClassLoader;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.types.Path;

public class AntClassLoaderUtil
{
    private AntClassLoaderUtil(){
    }
    
    
    public static ClassLoader getAntClassLoader(String[] directory)
    {
        Project project = new Project();
        project.init();
        Path path = new Path(project, null);
        
        for (int i = 0; i < directory.length; i++) {
           
            File dir = new File(directory[i]);
            String[] filePaths = null;
            if (dir.isDirectory()&& dir.exists()) {
                File[] files = dir.listFiles(new FilenameFilter ()
                    {
                    public boolean accept (File file, String name)
                    {
                      return name.endsWith (".jar") || name.endsWith (".zip");
                    }
                  });
                filePaths = new String[files.length];
                for (int j = 0; j < files.length; j++) {
                    filePaths[j] = files[j].getAbsolutePath();
                    path.setPath(filePaths[j]);
                }
            }
        }

        String[] paths=path.list();
        for (int i = 0; i < paths.length; i++) {
            System.out.println(paths[i]);
        }
        
        AntClassLoader loader = new AntClassLoader(project, path);
        return loader;
    }
}
