package sprint2;

import java.io.*;

public class PersistenceManager {
    private static SiteManager driver = null;

    /**
     * No ARG Constructor
     */
    public PersistenceManager() {

    }

    /**
     * Static method to save the entire system
     * 
     * @param SiteManager inDriver
     * @param File        inFile
     * @return boolean
     */
    public static boolean save(SiteManager inDriver, File inFile) {

        try {
            // Saving of object in a file
            // TODO:Ensure when he gives the final sprint that this path is looked at.
            FileOutputStream file = new FileOutputStream(inFile.getName().toString());
            ObjectOutputStream out = new ObjectOutputStream(file);

            // Method for serialization of object
            out.writeObject(inDriver);

            out.close();
            file.close();

            System.out.println("Object has been serialized");
            return true;
        }

        catch (IOException ex) {
            System.out.println("IOException is caught");
            ex.printStackTrace();
            return false;
        }

    }

    /**
     * Static method to read the system from file and return a SiteManager object.
     * 
     * @param File inFile
     * @return SiteManager
     */

    public static SiteManager read(File inFile) {
        try {
            // Reading the object from a file
            // TODO:Ensure when he gives the final sprint that this path is looked at.
            FileInputStream file = new FileInputStream(System.getProperty("user.dir")+"/"+inFile.getName().toString());
            ObjectInputStream in = new ObjectInputStream(file);

            // Method for deserialization of object
            SiteManager driver2 = (SiteManager) in.readObject();
            // System.out.println(in.readObject());
            in.close();
            file.close();

            System.out.println(inFile.getName().toString());
            System.out.println("Object has been deserialized");
            return driver2;
        }

        catch (IOException ex) {
            System.out.println("IOException is caught");
            ex.printStackTrace();
            return driver;
        }

        catch (ClassNotFoundException ex) {
            System.out.println("ClassNotFoundException is caught");

            System.out.println(System.getProperty("user.dir")+"/"+inFile.getName().toString());
            return driver;
        }

    }
}