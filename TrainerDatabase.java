import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class TrainerDatabase {
    private ArrayList<Trainer> records;
    private String filename;

    public TrainerDatabase(String filename) {
        this.filename = filename + ".txt";
        this.records = new ArrayList<>();
    }

    public void readFromFile(){
        try{
            File file = new File(filename);
            Scanner reader = new Scanner(file);

            while (reader.hasNextLine()){
                String trainerData = reader.nextLine();
                Trainer trainer = createRecordFrom(trainerData);
                records.add(trainer);
            }
            reader.close();
        }catch (FileNotFoundException e){
            System.out.println("ERROR: File Not Found");
            e.printStackTrace();
        }
    }

    public Trainer createRecordFrom(String line){
        String[] tokens = line.split(",");

        String trainerID = tokens[0];
        String name = tokens[1];
        String mail = tokens[2];
        String speciality = tokens[3];
        String phoneNumber = tokens[4];

        Trainer trainer = new Trainer(trainerID, name, mail, speciality, phoneNumber);
        return trainer;
    }

    public ArrayList<Trainer> returnAllRecords(){
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

    public Trainer getRecord(String key){
        int recordIndex = getRecordIndex(key);
        if(recordIndex == -1)
            return null;
        return records.get(recordIndex);
    }

    public void insertRecord(Trainer record){
        if (getRecordIndex(record.getSearchKey()) == -1)
        {
            records.add(record);
            return;
        }
        System.out.println("Trainer Already Exists.");
    }

    public void deleteRecord(String key){
        int recordIndex = getRecordIndex(key);
        if (recordIndex != -1)
        {
            records.remove(recordIndex);
            return;
        }
        System.out.printf("Trainer Doesn't Exist");
    }

    public void saveToFile(){
        try {
            FileWriter writer = new FileWriter(filename);

            int recordsSize = records.size();
            for (int i = 0; i < recordsSize; i++)
            {
                Trainer trainer = records.get(i);
                writer.write(trainer.lineRepresentation() + "\n");
            }
            writer.close();
        }catch (IOException e){
            System.out.printf("ERROR: Cannot Write to File");
            e.printStackTrace();
        }
    }
}
