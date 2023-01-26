import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import static java.nio.file.StandardOpenOption.CREATE;

public class ProductWriter
{
    public static void main(String[] args)
    {
        ArrayList<String> folks = new ArrayList<>();
        Scanner in = new Scanner(System.in);

        File workingDirectory = new File(System.getProperty("user.dir"));
        Path file = Paths.get(workingDirectory.getPath() + "\\src\\data.txt");

        boolean done = false;

        String productRec = "";
        String ID = "";
        String Name = "";
        String Description = "";
        double Cost = 0.0;

        do {
            ID = SafeInput.getNonZeroLenString(in,   "Enter the ID [6 digits]: ");
            Name = SafeInput.getNonZeroLenString(in,   "Enter the product name: ");
            Description = SafeInput.getNonZeroLenString(in,   "Enter the product description: ");
            Cost = SafeInput.getRangedInt(in, "Enter the cost of the product: ", 1, 9999);

            productRec = ID + ", " + Name + ", " + Description + ", " + Cost;
            folks.add(productRec);

            done = SafeInput.getYNConfirm(in, "Are you done?" );

        }while(!done);

        for( String p: folks)
            System.out.println(p);

        try
        {
            // Typical java pattern of inherited classes
            // we wrap a BufferedWriter around a lower level BufferedOutputStream
            OutputStream out =
                    new BufferedOutputStream(Files.newOutputStream(file, CREATE));
            BufferedWriter writer =
                    new BufferedWriter(new OutputStreamWriter(out));

            // Finally can write the file LOL!

            for(String rec : folks)
            {
                writer.write(rec, 0, rec.length());  // stupid syntax for write rec
                // 0 is where to start (1st char) the write
                // rec. length() is how many chars to write (all)
                writer.newLine();  // adds the new line

            }
            writer.close(); // must close the file to seal it and flush buffer
            System.out.println("Data file written!");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }



    }
}