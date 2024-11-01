import java.util.ArrayList;

public class AdminRole implements FileNames{
    public TrainerDatabase database;

    public AdminRole() {
        this.database = new TrainerDatabase(TRAINER_FILENAME);
    }

    public void addTrainer(String trainerID,String name,String mail,String speciality,String phoneNumber){
        Trainer trainer = new Trainer(trainerID, name, mail, speciality, phoneNumber);
        database.insertRecord(trainer);
    }

    public ArrayList<Trainer> getListOfTrainers(){
        return database.returnAllRecords();
    }

    public void removeTrainer(String key){
        database.deleteRecord(key);
    }

    public void logout(){
        database.saveToFile();
    }
}
