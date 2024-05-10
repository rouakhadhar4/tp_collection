package fichiertp;
import java.io.*;
import static java.lang.System.*;
import java.util.Date;
public class test {

    public static void main(String[] args) throws IOException {
        System.out.println("Répertoire courant : "+System.getProperty("user.dir"));
        File rep = new File(".");
        out.println("Chemin relatif : "+ rep.getPath()); 
        out.println("Chemin absolu : "+ rep.getAbsolutePath()); 

        for (File élément: File.listRoots()) 
            out.println("Racine : "+élément);

        for (File élément : rep.listFiles()) 
            if (élément.isDirectory()) {
                out.print(élément.getName()+"\t");
                if (élément.getName().length()<8)
                    out.print("\t");
                out.println("<REP>");
            }
        for (File élément : rep.listFiles())
            if (élément.isFile()){
                out.print(élément.getName()+"\t");
                if (élément.getName().length()<8) out.print("\t");
                out.printf("%tc", new Date(élément.lastModified()));
                out.printf("\t%10d octets\n", élément.length());
            }
 
        
        
        //exercice 2 
        
        File reper = new File(".");

        System.out.println("Liste des répertoires commençant par la lettre 'b' :");
        Filtre filtreB = new Filtre('b');
        File[] répertoiresB = reper.listFiles(filtreB);
        if (répertoiresB != null) {
            for (File répertoire : répertoiresB) {
                System.out.println(répertoire.getName());
                for (File élément : répertoire.listFiles()) {
                    out.print("\t");
                    if (élément.getName().length() < 8) out.print("\t");
                    if (élément.isDirectory()) out.print("<REP>");
                    out.printf("\t%tc", new Date(élément.lastModified()));
                    out.printf("\t%5d octets\n", élément.length());
                }
            }
        } else {
            System.out.println("Aucun répertoire trouvé.");
        }
    }
        
        
        
       
    }



