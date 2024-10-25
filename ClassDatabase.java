import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class ClassDatabase {
    private ArrayList<Class> records;
    private String filename;

    public ClassDatabase(String filename) {
        this.filename = filename + ".txt";
        this.records = new ArrayList<>();
    }

    public void readFromFile(){
        try{
            File file = new File(filename);
            Scanner reader = new Scanner(file);

            while (reader.hasNextLine()){
                String classData = reader.nextLine();
                Class theClass = createRecordFrom(classData);
                records.add(theClass);
            }
            reader.close();
        }catch (FileNotFoundException e){
            System.out.println("ERROR: File Not Found");
            e.printStackTrace();
        }
    }

    public Class createRecordFrom(String line){
        String[] tokens = line.split(",");

        String classID = tokens[0];
        String className = tokens[1];
        String trainerID = tokens[2];
        int duration =Integer.parseInt(tokens[3]);
        int availableSeats = Integer.parseInt(tokens[4]);

        Class theClass = new Class(classID, className, trainerID, duration, availableSeats);
        return theClass;
    }

    public ArrayList<Class> returnAllRecords(){
        return records;
    }

    public int getRecordIndex(String key){
        int recordsSize = records.size();
        for (int i = 0; i < recordsSize; i++){
            if (records.get(i).getSearchKey().equalsIgnoreCase(key))
                return i;
        }
        return -1;
    }

    public boolean contains(String key){
        return (getRecordIndex(key) != -1);
    }

    public Class getRecord(String key){
        int recordIndex = getRecordIndex(key);
        if(recordIndex == -1)
            return null;
        return records.get(recordIndex);
    }

    public void insertRecord(Class record){
        if (getRecordIndex(record.getSearchKey()) == -1)
        {
            records.add(record);
            return;
        }
        System.out.println("Class Already Exists.");
    }

    public void deleteRecord(String key){
        int recordIndex = getRecordIndex(key);
        if (recordIndex != -1)
        {
            records.remove(recordIndex);
            return;
        }
        System.out.printf("Class Doesn't Exist");
    }

    public void saveToFile(){
        try {
            FileWriter writer = new FileWriter(filename);

            int recordsSize = records.size();
            for (int i = 0; i < recordsSize; i++)
            {
                Class theClass = records.get(i);
                writer.write(theClass.lineRepresentation() + "\n");
            }
            writer.close();
        }catch (IOException e){
            System.out.printf("ERROR: Cannot Write to File");
            e.printStackTrace();
        }
    }
}
