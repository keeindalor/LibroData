import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PublishingBrand implements IPublishingArtifact {

    String name, ID;
    List<Book> books = new ArrayList<Book>();

    public PublishingBrand(String name, String ID) {
        this.name = name;
        this.ID = ID;
    }
    public String Publish() {
        String dateFormated, author ="", information;

        information = "<xml>" + "\n\t<publishingBrand>" +
               "\n\t\t<ID>" + this.ID + "</ID>" +
               "\n\t\t<Name>" + this.name +"</Name>" +
               "\n\t</publishingBrand>" +
               "\n\t<books>";
        for (Book b : books) {
            Date date = b.createdOn.getInstance().getTime();
            DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
            dateFormated = dateFormat.format(date);
            for (Author a : b.authors) {
                author += a.firstName + " " + a.lastName;
                author += ",";
            }
            author = author.substring(0, author.length() - 1);
            information += "\n\t\t<book>" +
                    "\n\t\t\t<title>" + b.name + "</title>"+
                    "\n\t\t\t<subtitle>" + b.subtitle + "</subtitle>" +
                    "\n\t\t\t<isbn>" + b.ISBN + "</isbn>" +
                    "\n\t\t\t<pageCount>" + b.pageCount + "</pageCount>" +
                    "\n\t\t\t<keywords>" + b.keywords + "</keywords>" +
                    "\n\t\t\t<languageID>" + b.languageID + "</languageID>" +
                    "\n\t\t\t<createdOn>" + dateFormated + "</createdOn>" +
                    "\n\t\t\t<authors>" + author + "</authors>"+
                    "\n\t\t</book>";
        }
        information += "\n\t</book>" + "\n</xml>";
        return information;
    }
}
