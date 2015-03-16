import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Scanner;

public class FavoriteArtists {
 
    public static ArrayList<String> readFile(File f){
        Scanner fileInput = null;
        ArrayList<String> fullList = new ArrayList<String>();
        try {
            fileInput = new Scanner(new FileReader(f));
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        while(fileInput.hasNextLine()) {
            String tmpLine = fileInput.nextLine();
            fullList.add(tmpLine);
        }
        fileInput.close();
        return fullList;
    }
    
    public static void countArtists(ArrayList<String> fullList){
        HashMap<String, Integer> counter = new HashMap<String, Integer>();
        ArrayList<String> popularArtists = new ArrayList<String>();
        for(String line: fullList){
            List<String> favoriteArtists = Arrays.asList(line.split(","));
            for (int j = 0; j < favoriteArtists.size(); j++){
                if (counter.containsKey(favoriteArtists.get(j))){
                    int current = counter.get(favoriteArtists.get(j));
                    counter.put(favoriteArtists.get(j), current + 1);
                    if (current + 1 == 50){
                        popularArtists.add(favoriteArtists.get(j));
                        /* System.out.println(favoriteArtists.get(j)); */
                    }
                }
                else {
                    counter.put(favoriteArtists.get(j), 1);
                }
            }
        }
        findPairs(fullList, popularArtists);
    }
    
    public static void findPairs (ArrayList<String> fullList, ArrayList<String> popularArtists){
        HashMap<ArrayList<String>, Integer> artistPairs = new HashMap<ArrayList<String>, Integer>();
        for(String line: fullList){
            List<String> favoriteArtists = Arrays.asList(line.split(","));
            for(int j = 0; j < popularArtists.size(); j++){
                for(int k = 0; k < popularArtists.size(); k++){
                    if(favoriteArtists.contains(popularArtists.get(j)) &&
                            favoriteArtists.contains(popularArtists.get(k)) &&
                            j < k){
                        ArrayList<String> pair = new ArrayList<String>();
                        pair.add("");
                        pair.add("");
                        pair.set(0, popularArtists.get(j));
                        pair.set(1, popularArtists.get(k));
                        if(artistPairs.containsKey(pair)){
                            int current = artistPairs.get(pair);
                            artistPairs.put(pair, current + 1);
                        }
                        else {
                            artistPairs.put(pair, 1);
                        }
                    }
                }
            }
        }

        for(ArrayList<String> a: artistPairs.keySet()) {
            if(artistPairs.get(a) >= 50) {
                System.out.print(a + " ");
                System.out.println(artistPairs.get(a));
            }
        }
    }
    
    public static void writeFile(HashMap<ArrayList<String>, Integer> artistPairs){
        Scanner in = new Scanner(System.in);
        System.out.println("Enter new file name: ");
        String newName = in.nextLine();
        FileWriter fw = null;
        try {
            fw = new FileWriter(newName);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        for (Entry<ArrayList<String>, Integer> entry : artistPairs.entrySet())
        {
            if(entry.getValue().compareTo(50) < 1){
                try {
                    fw.write(entry.getKey().get(0) + " and " + entry.getKey().get(1) +  "\n");
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
        try {
            fw.close();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        in.close();
    }
 
    public static void main(String[] args) {
        String fileName = "";
        Scanner input = new Scanner(System.in);
        System.out.println("Enter file name: ");
        /* fileName = input.nextLine(); */
        fileName = "artists.txt";
        try {
            File f = new File(fileName);
            ArrayList<String> fullList = readFile(f);
            countArtists(fullList);
            }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        input.close();
    }
}
