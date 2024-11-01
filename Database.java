import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public abstract class Database <T extends Interface> {
    protected String filename;
    protected ArrayList<T> records;

    public Database(String filename){
        this.filename = filename + ".txt";
        this.records = new ArrayList<>();
        readFromFile();
    }

    protected void readFromFile(){
        try{
            File file = new File(filename);
            Scanner reader = new Scanner(file);

            while (reader.hasNextLine()){
                String data = reader.nextLine();
                T t = createRecordFrom(data);
                records.add(t);
            }
            reader.close();
        }catch (FileNotFoundException e){
            System.out.println("ERROR: File Not Found");
            e.printStackTrace();
        }
    }

    protected abstract T createRecordFrom(String line);

    protected ArrayList<T> returnAllRecords(){
        return records;
    }

    protected int getRecordIndex(String key){
        int recordsSize = records.size();
        for (int i = 0; i < recordsSize; i++){
            if (records.get(i).getSearchKey().equalsIgnoreCase(key))
                return i;
        }
        return -1;
    }

    protected boolean contains(String key){
        return (getRecordIndex(key) != -1);
    }

    protected T getRecord(String key){
        int recordIndex = getRecordIndex(key);
        if(recordIndex == -1)
            return null;
        return records.get(recordIndex);
    }

    public boolean insertRecord(T record){
        if (getRecordIndex(record.getSearchKey()) == -1)
        {
            records.add(record);
            return true;
        }
        System.out.println("This Record Already Exists.");
        return false;
    }

    protected boolean deleteRecord(String key){
        int recordIndex = getRecordIndex(key);
        if (recordIndex != -1)
        {
            records.remove(recordIndex);
            return true;
        }
        System.out.printf("This Record Doesn't Exist");
        return false;
    }

    protected void saveToFile(){
        try {
            FileWriter writer = new FileWriter(filename);

            int recordsSize = records.size();
            for (int i = 0; i < recordsSize; i++)
            {
                T t = records.get(i);
                writer.write(t.lineRepresentation() + "\n");
            }
            writer.close();
        }catch (IOException e){
            System.out.printf("ERROR: Cannot Write to File");
            e.printStackTrace();
        }
    }
}