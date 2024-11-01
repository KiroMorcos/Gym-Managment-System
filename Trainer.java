public class Trainer extends Human{
    private String  speciality;

    public Trainer(String trainerID, String name, String mail, String speciality, String phoneNumber) {
        super(trainerID, name, mail, phoneNumber);
        this.speciality = speciality;
    }

    @Override
    public String lineRepresentation(){
        return id + "," + name + "," + mail + "," + speciality + "," + phoneNumber;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return mail;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getSpeciality() {
        return speciality;
    }
}
