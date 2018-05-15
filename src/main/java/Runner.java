import db.DBChild;
import models.Child;

import java.util.List;

public class Runner {

    public static void main(String[] args) {

        Child liesl = new Child("Liesl", 16, "Soprano");
        Child friedrich = new Child("Friedrich", 14, "Tenor");
        Child louisa = new Child("Louisa", 13, "Contralto");
        Child kurt = new Child("Kurt", 11, "Tenor");
        Child brigitta = new Child("Brigitta", 10, "Soprano");
        Child marta = new Child("Marta", 7, "Mezzo-Soprano");
        Child gretl = new Child("Gretl", 5, "Soprano");

        DBChild.save(liesl);
        DBChild.save(friedrich);
        DBChild.save(louisa);
        DBChild.save(kurt);
        DBChild.save(brigitta);
        DBChild.save(marta);
        DBChild.save(gretl);

        List<Child> allChildren = DBChild.listAll();

        Child foundChild = DBChild.find(liesl.getName());

        List<Child> childrenByAge = DBChild.listByAge();

        List<Child> childrenBySoprano = DBChild.listByRange("Soprano");

        friedrich.setRange("Bass");
        friedrich.setAge(15);
        DBChild.Update(friedrich);

        Child changedChild = DBChild.find(friedrich.getName());

        List<Child> childrenUnder10 = DBChild.listByAgeUnderTen();

        Child oldestChild = DBChild.findOldest();
    }
}
