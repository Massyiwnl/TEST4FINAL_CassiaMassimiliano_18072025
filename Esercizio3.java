import java.util.*;

// Observer
interface Observer {
    void update(Notification notification);
}

// Concrete Observer
class User implements Observer {
    private String name;
    public User(String name) { this.name = name; }
    @Override
    public void update(Notification notification) {
        System.out.println(name + " ha ricevuto una notifica: " + notification.getMessage());
    }
    public String getName() { 
        return name; 
    }
}

// Notifica (Product per Factory)
abstract class Notification {
    private String message;
    public Notification(String message) { 
        this.message = message; 
    }
    public String getMessage() { 
        return message; 
    }
}
class EmailNotification extends Notification {
    public EmailNotification(String message) { 
        super("[EMAIL] " + message); 
    }
}
class SMSNotification extends Notification {
    public SMSNotification(String message) { 
        super("[SMS] " + message); 
    }
}
class PushNotification extends Notification {
    public PushNotification(String message) {
         super("[PUSH] " + message); 
        }
}

// Factory Method per notifiche
class NotificationFactory {
    public static Notification createNotification(String type, String message) {
        switch(type.toLowerCase()) {
            case "email": 
                return new EmailNotification(message);
            case "sms": 
                return new SMSNotification(message);
            case "push":
                return new PushNotification(message);
            default: 
                throw new IllegalArgumentException("Tipo di notifica non supportato.");
        }
    }
}

// Subject Singleton:
class NotificationService {
    private static NotificationService instance = null;
    private List<Observer> observers = new ArrayList<>();

    private NotificationService() {}

    public static NotificationService getInstance() {
        if (instance == null) {
            instance = new NotificationService();
        }
        return instance;
    }

    public void addObserver(Observer o) {
        observers.add(o);
    }
    public void removeObserver(Observer o) {
        observers.remove(o);
    }
    public List<Observer> getObservers() { return observers; }

    public void notifyAllObservers(Notification notification) {
        for (Observer o : observers) {
            o.update(notification);
        }
    }
    public void sendNotification(Notification notification) {
        if (observers.isEmpty()) {
            System.out.println("Nessun utente registrato per ricevere notifiche.");
            return;
        }
        System.out.println("Invio notifica a tutti gli utenti registrati...");
        notifyAllObservers(notification);
    }
}

// Main 
public class Esercizio3 {
    public static void main(String[] args) {
        NotificationService service = NotificationService.getInstance();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nMENU SISTEMA NOTIFICHE");
            System.out.println("1. Registra nuovo utente");
            System.out.println("2. Visualizza utenti registrati");
            System.out.println("3. Invia una notifica");
            System.out.println("0. Esci");
            System.out.print("Scegli un'opzione: ");
            String scelta = scanner.nextLine();

            switch (scelta) {
                case "1":
                    System.out.print("Nome utente da registrare: ");
                    String nome = scanner.nextLine();
                    service.addObserver(new User(nome));
                    System.out.println("Utente " + nome + " registrato con successo.");
                    break;
                case "2":
                    List<Observer> utenti = service.getObservers();
                    System.out.println("Utenti registrati:");
                    if (utenti.isEmpty()) {
                        System.out.println("- Nessun utente registrato.");
                    } else {
                        for (Observer o : utenti) {
                            if (o instanceof User) {
                                System.out.println("- " + ((User) o).getName());
                            }
                        }
                    }
                    break;
                case "3":
                    if (service.getObservers().isEmpty()) {
                        System.out.println("Nessun utente registrato. Registra almeno un utente prima di inviare notifiche.");
                        break;
                    }
                    System.out.print("Tipo notifica (email/sms/push): ");
                    String tipo = scanner.nextLine();
                    System.out.print("Messaggio della notifica: ");
                    String msg = scanner.nextLine();
                    try {
                        Notification notifica = NotificationFactory.createNotification(tipo, msg);
                        service.sendNotification(notifica);
                    } catch (Exception ex) {
                        System.out.println("Errore: " + ex.getMessage());
                    }
                    break;
                case "0":
                    System.out.println("Uscita dal programma.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opzione non valida.");
            }
        }
    }
}
