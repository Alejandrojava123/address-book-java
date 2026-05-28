import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class AddressBook {

    private HashMap<String, String> contacts;
    private final String fileName;

    public AddressBook(String fileName) {
        this.fileName = fileName;
        contacts = new HashMap<>();
        load();
    }

    public void load() {

        File file = new File(fileName);

        if (!file.exists()) {
            System.out.println("Archivo no encontrado.");
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {

            String line;

            while ((line = br.readLine()) != null) {

                String[] data = line.split(",");

                if (data.length == 2) {
                    contacts.put(data[0], data[1]);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void save() {

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {

            for (Map.Entry<String, String> entry : contacts.entrySet()) {

                bw.write(entry.getKey() + "," + entry.getValue());
                bw.newLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void list() {

        System.out.println("\nContactos:");

        if (contacts.isEmpty()) {
            System.out.println("La agenda está vacía.");
            return;
        }

        for (Map.Entry<String, String> entry : contacts.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }

    public void create(String phone, String name) {

        contacts.put(phone, name);
        save();

        System.out.println("Contacto agregado.");
    }

    public void delete(String phone) {

        if (contacts.remove(phone) != null) {

            save();
            System.out.println("Contacto eliminado.");

        } else {
            System.out.println("No existe el contacto.");
        }
    }
}