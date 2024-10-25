public class Trainer {
    private String trainerID, name, mail, speciality, phoneNumber;

    public Trainer(String trainerID, String name, String mail, String speciality, String phoneNumber) {
        this.trainerID = trainerID;
        this.name = name;
        this.mail = mail;
        this.speciality = speciality;
        this.phoneNumber = phoneNumber;
    }

    public String lineRepresentation(){
        return trainerID+","+name+","+mail+","+speciality+","+phoneNumber;
    }

    public String getSearchKey(){
        return trainerID;
    }
}
