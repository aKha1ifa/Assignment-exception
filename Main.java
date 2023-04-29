import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        try {
            String filename = args[0];
            if (!filename.endsWith(".arxml")) {
                throw new NotVaildAutosarFileException("Invalid file extension");
            }
            File file = new File(filename);
            FileInputStream inputStream = new FileInputStream(file);
            StringBuilder stringBuilder = new StringBuilder();
            int x,flag=0;
            while ((x = inputStream.read()) != -1) {
                stringBuilder.append((char) x);
            }

            String data = stringBuilder.toString();
            Scanner scanner = new Scanner(data);
            ArrayList<Container> containers = new ArrayList<>();
            while (scanner.hasNextLine()) {
                flag=1;
                String line = scanner.nextLine();
                if (line.contains("<CONTAINER")) {
                    String UUId = line.substring(line.indexOf("UUID=\""), line.indexOf("\">"));
                    String Shortname = scanner.nextLine();
                    String Sname = Shortname.substring(Shortname.indexOf(">") + 1, Shortname.indexOf("</"));
                    String Longname = scanner.nextLine();
                    String Lname = Longname.substring(Longname.indexOf(">") + 1, Longname.indexOf("</"));
                    Container container = new Container();
                    container.setUUID(UUId);
                    container.setSname(Sname);
                    container.setLname(Lname);
                    containers.add(container);
                }
            }
            if (flag==0)
            {
                throw new EmptyAutosarFileException("Empty file ") ;
            }
            Collections.sort(containers);
            String outname = filename.substring(0, filename.indexOf(".")) + "_mod.arxml";
            FileOutputStream outputStream = new FileOutputStream(outname);
            outputStream.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n".getBytes());
            outputStream.write("<AUTOSAR>\n".getBytes());
            for (int i = 0; i < containers.size(); i++) {
                outputStream.write(containers.get(i).toString().getBytes());
            }
            outputStream.write("</AUTOSAR>\n".getBytes());

        } catch (FileNotFoundException e) {
            e = new FileNotFoundException("File Not Found!");
        } catch (IOException e) {
            e = new IOException("IO Exception!");
        } catch (NotVaildAutosarFileException e) {
            e = new NotVaildAutosarFileException("Not Valid");
        }
        catch(EmptyAutosarFileException e){
            System.out.println("");
        }
    }
}
class EmptyAutosarFileException extends RuntimeException{
    public EmptyAutosarFileException(String m){
        System.out.println(m);
    }
}
class NotVaildAutosarFileException extends Exception
{
    public NotVaildAutosarFileException(String m)
    {
        System.out.println(m);
    }
}



class Container implements Comparable<Container>
{
    private String UUID;
    private String Sname;
    private String Lname;
    public Container()
    {}


    public String GetUUid()
    {
        return UUID;
    }
    public String GetSname()
    {
        return Sname;
    }
    public String Lname()
    {
        return Lname;
    }

    public void setLname(String lname) {
        Lname = lname;
    }

    public void setSname(String sname) {
        Sname = sname;
    }

    public void setUUID(String UUID) {
        this.UUID = UUID;
    }

    @Override
    public String toString() {
        return "<CONTAINER UUID=\""+UUID+"\">\n"
                +"   <SHORT-NAME>"+Sname+"</SHORT-NAME>\n"
                +"   <LONG-NAME>"+Lname+"</LONG-NAME>\n"
                +"</CONTAINER>\n" ;
    }

    @Override
    public int compareTo(Container o) {
        if(this.Sname.charAt(9)>o.GetSname().charAt(9))
        {return 1;}
        else if (this.Sname.charAt(9)<o.GetSname().charAt(9))
        {return -1;}
        else
        {return 0;}
    }
}