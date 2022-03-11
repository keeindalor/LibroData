import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Book implements IPublishingArtifact {

    String name, subtitle, ISBN, keywords, ID, pageCount, languageID;
    Calendar createdOn;
    List<Author> authors = new ArrayList<Author>();
    public Book(String name, String subtitle, String ISBN, String keywords, String languageID, String ID,
                String pageCount, Calendar createdOn) {
        this.name = name;
        this.subtitle = subtitle;
        this.ISBN = ISBN;
        this.keywords = keywords;
        this.languageID = languageID;
        this.ID = ID;
        this.pageCount = pageCount;
        this.createdOn = createdOn;
        this.authors = new ArrayList<Author>();

    }
    public String Publish() {
        String dateFormated, author="", information;

        Date date = createdOn.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy hh:mm:ss");
        dateFormated = dateFormat.format(date);

        for (Author a : authors) {
            author += a.firstName + " " + a.lastName;
            author += ",";
        }
        author = author.substring(0, author.length() - 1);
        information = "<xml>" +
                "\n\t<title>" + this.name + "</title>"+
                "\n\t<subtitle>" + this.subtitle + "</subtitle>" +
                "\n\t<isbn>" + this.ISBN + "</isbn>" +
                "\n\t<pageCount>" + this.pageCount + "</pageCount>" +
                "\n\t<keywords>" + this.keywords + "</keywords>" +
                "\n\t<languageID>" + this.languageID + "</languageID>" +
                "\n\t<createdOn>" + dateFormated + "</createdOn>" +
                "\n\t<authors>" + author + "</authors>"+
                "\n</xml>";
        return information;
    }
}
