public abstract class Human extends Interface {
    protected String id, name, mail, phoneNumber;

    public Human(String id, String name, String mail, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.mail = mail;
        this.phoneNumber = phoneNumber;
    }

    protected String getSearchKey(){
        return id;
    }
}
