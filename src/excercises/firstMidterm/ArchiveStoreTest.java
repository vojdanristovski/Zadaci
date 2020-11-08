package excercises.firstMidterm;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.locks.Lock;

public class ArchiveStoreTest {
    public static void main(String[] args) {
        ArchiveStore store = new ArchiveStore();
        Date date = new Date(113, 10, 7);
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        int n = scanner.nextInt();
        scanner.nextLine();
        scanner.nextLine();
        int i;
        for (i = 0; i < n; ++i) {
            int id = scanner.nextInt();
            long days = scanner.nextLong();
            Date dateToOpen = new Date(date.getTime() + (days * 24 * 60
                    * 60 * 1000));
            LockedArchive lockedArchive = new LockedArchive(id, dateToOpen);
            store.archiveItem(lockedArchive, date);
        }
        scanner.nextLine();
        scanner.nextLine();
        n = scanner.nextInt();
        scanner.nextLine();
        scanner.nextLine();
        for (i = 0; i < n; ++i) {
            int id = scanner.nextInt();
            int maxOpen = scanner.nextInt();
            SpecialArchive specialArchive = new SpecialArchive(id, maxOpen);
            store.archiveItem(specialArchive, date);
        }
        scanner.nextLine();
        scanner.nextLine();
        while(scanner.hasNext()) {
            int open = scanner.nextInt();
            try {
                store.openItem(open, date);
            } catch(NonExistingItemException e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println(store.getLog());
    }
}

abstract class Archive{
    private int id;
    private Date dateArchived;

    public Archive(int id) {
        this.id = id;
        this.dateArchived = null;
    }

    public int getId() {
        return id;
    }

    public Date getDateArchived() {
        return dateArchived;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDateArchived(Date dateArchived) {
        this.dateArchived = dateArchived;
    }
    public abstract String getArchiveType();
}
class LockedArchive extends Archive{
    Date dateToOpen;

    public LockedArchive(int id,Date dateToOpen) {
        super(id);
        this.dateToOpen = dateToOpen;
    }

    @Override
    public String getArchiveType() {
        return "Locked";
    }
}
class SpecialArchive extends Archive{
    int maxOpen;
    int opened;
    public SpecialArchive(int id,int maxOpen) {
        super(id);
        this.maxOpen = maxOpen;
        opened=0;
    }

    @Override
    public String getArchiveType() {
        return "Special";
    }
    public void getOpened(){
        opened++;
    }
}
class ArchiveStore{
    List<Archive> archives;
    private final StringBuilder sb;
    public ArchiveStore() {
        this.archives = new ArrayList<>();
        this.sb = new StringBuilder();

    }
    void archiveItem(Archive item,Date date){
        archives.add(item);
        archives.get(archives.indexOf(item)).setDateArchived(date);
        sb.append(String.format("Item %d archived at %s",item.getId(),date)).append("\n");
    }
    void openItem(int id,Date date) throws NonExistingItemException {
        Archive a = this.archives.stream().filter(archive -> archive.getId()==id).findAny().orElse(null);
        if(a==null){
            throw new NonExistingItemException(String.format("Item with id %d doesn't exist",id));
        }
        String type = a.getArchiveType();
        if(type.equals("Locked")){
            LockedArchive l = (LockedArchive) a;
            if(date.before(l.dateToOpen)){
                this.sb.append(String.format("Item %d cannot be opened before %s",id,l.dateToOpen.toString())).append("\n");
                return ;
            }
        }
        else{
            SpecialArchive s = (SpecialArchive) a;
            if(s.opened == s.maxOpen){
                this.sb.append(String.format("Item %d cannot be opened more than %d times",id,s.maxOpen)).append("\n");
                return;
            }
            s.getOpened();
        }
        sb.append(String.format("Item %d opened at %s",id,date.toString())).append("\n");
    }
    String getLog(){
        return sb.toString().replaceAll("GMT","UTC");
    }
}
class NonExistingItemException extends Exception{
    public NonExistingItemException(String message) {
        super(message);
    }
}