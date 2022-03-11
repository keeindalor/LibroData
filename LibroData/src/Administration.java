import com.sun.jdi.connect.LaunchingConnector;

import java.io.EOFException;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class Administration {

    static public List<Book> getBooksForPublishingRetailerID(int publishingRetailerID) {
        HashSet<Book> uniqueBook = new HashSet<Book>();
        String prID = String.valueOf(publishingRetailerID);
        for (PublishingRetailers p : FileParsing.retailers) {
            if (p.ID.equals(prID)) {
                for (IPublishingArtifact a : p.artifacts){
                    if (a instanceof Book){
                        uniqueBook.add((Book)a);
                    }
                    else if (a instanceof EditorialGroup) {
                        uniqueBook.addAll(((EditorialGroup) a).books);
                    }
                    else if (a instanceof PublishingBrand){
                        uniqueBook.addAll(((PublishingBrand) a).books);
                    }
                }
            }
        }
        return new ArrayList<Book>(uniqueBook);
    }
    static public List<Language> getLanguagesForPublishingRetailerID(int publishingRetailerID) {
        HashSet<Language> uniqueLanguage = new HashSet<Language>();
        String prID = String.valueOf(publishingRetailerID);
        for (PublishingRetailers p : FileParsing.retailers) {
            if (p.ID.equals(prID)) {
                for (IPublishingArtifact a : p.artifacts) {
                    if (a instanceof Book) {
                        for (Language l : FileParsing.languages){
                            if (l.ID.equals(((Book) a).languageID)){
                                uniqueLanguage.add(l);
                                break;
                            }
                        }
                    }
                    else if (a instanceof EditorialGroup) {
                        for(Book b : ((EditorialGroup) a).books){
                            for (Language l : FileParsing.languages) {
                                if (l.ID.equals(b.languageID)){
                                    uniqueLanguage.add(l);
                                    break;
                                }
                            }
                        }
                    }
                    else if (a instanceof PublishingBrand) {
                        for (Book b : ((PublishingBrand)a).books){
                            for (Language l : FileParsing.languages) {
                                if (l.ID.equals(b.languageID)) {
                                    uniqueLanguage.add(l);
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }
        return new ArrayList<Language>(uniqueLanguage);
    }
    static public List<Countries> getCountriesForBookID(int bookID) {
        HashSet<Countries> countriesUnique = new HashSet<Countries>();
        String bID = String.valueOf(bookID);

        for (PublishingRetailers p : FileParsing.retailers) {
            for (IPublishingArtifact a : p.artifacts){
                if (a instanceof Book) {
                    if (((Book) a).ID.equals(bID)){
                        countriesUnique.addAll( p.countries);
                    }
                }
                else if (a instanceof EditorialGroup){
                    for (Book b : ((EditorialGroup)a).books){
                        if (b.ID.equals(bID)){
                            countriesUnique.addAll( p.countries);
                        }
                    }
                }
                else if (a instanceof PublishingBrand) {
                    for (Book b : ((PublishingBrand)a).books) {
                        if (b.ID.equals(bID)) {
                            countriesUnique.addAll( p.countries);
                        }
                    }
                }
            }
        }
        return new ArrayList<Countries>(countriesUnique);
    }
    public static List<Book> getCommonBooksForRetailerIDs(int retailerID1, int retailerID2) {
        String id1, id2;
        id1 = String.valueOf(retailerID1);
        id2 = String.valueOf(retailerID2);
        List<Book> commonBooks = new ArrayList<Book>();
        List<Book> booksRet1 = getBooksForPublishingRetailerID(retailerID1);
        List<Book> booksRet2 = getBooksForPublishingRetailerID(retailerID2);
        for (Book b1 : booksRet1){
            if (booksRet2.contains(b1)) {
                commonBooks.add(b1);
            }
        }
        return commonBooks;
    }
    public static List<Book> getAllBooksForRetailerIDs (int retailerID1, int retailerID2) {
        String id1, id2;
        id1 = String.valueOf(retailerID1);
        id2 = String.valueOf(retailerID2);
        HashSet<Book> allBooksOnce = new HashSet<Book>();
        List<Book> booksRet1 = getBooksForPublishingRetailerID(retailerID1);
        List<Book> booksRet2 = getBooksForPublishingRetailerID(retailerID2);
        allBooksOnce.addAll(booksRet1);
        allBooksOnce.addAll(booksRet2);

        return new ArrayList<Book>(allBooksOnce);

    }

    public static void main(String[] args) {
        List <Book> uniqueBooks, commonBooks, allBooks;
        List <Language> languages;
        List <Countries> countries;

        FileParsing.readFromFile();
        uniqueBooks = getBooksForPublishingRetailerID(5);
        languages = getLanguagesForPublishingRetailerID(5);
        countries = getCountriesForBookID(489);
        commonBooks = getCommonBooksForRetailerIDs(5, 7);
        allBooks = getAllBooksForRetailerIDs(5,7);

        System.out.println("Books for retailer ID");
        for (Book b : uniqueBooks) {
            System.out.print(b.ID + " ");
        }
        System.out.println();
        System.out.println("Languages for retailer ID");
        for (Language l : languages) {
            System.out.print(l.name + " ");
        }
        System.out.println();
        System.out.println("Countries for book ID");
        for (Countries c : countries) {
            System.out.println(c.countryCode + " ");
        }
        System.out.println();
        System.out.println("Common books for two retailers:");
        for (Book b : commonBooks) {
            System.out.println(b.name + " ");
        }
        System.out.println();
        System.out.println("All books for two retailers:");
        for (Book b : allBooks) {
            System.out.print(b.name + " ");
        }
        uniqueBooks = getBooksForPublishingRetailerID(10);
        languages = getLanguagesForPublishingRetailerID(10);
        countries = getCountriesForBookID(487);
        commonBooks = getCommonBooksForRetailerIDs(10, 14);
        allBooks = getAllBooksForRetailerIDs(10,14);

        System.out.println("Books for retailer ID");
        for (Book b : uniqueBooks) {
            System.out.print(b.name + " ");
        }
        System.out.println();
        System.out.println("Languages for retailer ID");
        for (Language l : languages) {
            System.out.print(l.name + " ");
        }
        System.out.println();
        System.out.println("Countries for book ID");
        for (Countries c : countries) {
            System.out.println(c.countryCode + " ");
        }
        System.out.println();
        System.out.println("Common books for two retailers:");
        for (Book b : commonBooks) {
            System.out.println(b.name + " ");
        }
        System.out.println();
        System.out.println("All books for two retailers:");
        for (Book b : allBooks) {
            System.out.print(b.name + " ");
        }
        uniqueBooks = getBooksForPublishingRetailerID(15);
        languages = getLanguagesForPublishingRetailerID(15);
        countries = getCountriesForBookID(488);
        commonBooks = getCommonBooksForRetailerIDs(15, 17);
        allBooks = getAllBooksForRetailerIDs(15,17);

        System.out.println("Books for retailer ID");
        for (Book b : uniqueBooks) {
            System.out.print(b.name + " ");
        }
        System.out.println();
        System.out.println("Languages for retailer ID");
        for (Language l : languages) {
            System.out.print(l.name + " ");
        }
        System.out.println();
        System.out.println("Countries for book ID");
        for (Countries c : countries) {
            System.out.print(c.countryCode + " ");
        }
        System.out.println();
        System.out.println("Common books for two retailers:");
        for (Book b : commonBooks) {
            System.out.print(b.name + " ");
        }
        System.out.println();
        System.out.println("All books for two retailers:");
        for (Book b : allBooks) {
            System.out.print(b.name + " ");
        }
        uniqueBooks = getBooksForPublishingRetailerID(25);
        languages = getLanguagesForPublishingRetailerID(25);
        countries = getCountriesForBookID(513);
        commonBooks = getCommonBooksForRetailerIDs(25, 7);
        allBooks = getAllBooksForRetailerIDs(25,7);

        System.out.println("Books for retailer ID");
        for (Book b : uniqueBooks) {
            System.out.print(b.name + " ");
        }
        System.out.println();
        System.out.println("Languages for retailer ID");
        for (Language l : languages) {
            System.out.print(l.name + " ");
        }
        System.out.println();
        System.out.println("Countries for book ID");
        for (Countries c : countries) {
            System.out.print(c.countryCode + " ");
        }
        System.out.println();
        System.out.println("Common books for two retailers:");
        for (Book b : commonBooks) {
            System.out.print(b.name + " ");
        }
        System.out.println();
        System.out.println("All books for two retailers:");
        for (Book b : allBooks) {
            System.out.print(b.name + " ");
        }
        uniqueBooks = getBooksForPublishingRetailerID(30);
        languages = getLanguagesForPublishingRetailerID(30);
        countries = getCountriesForBookID(519);
        commonBooks = getCommonBooksForRetailerIDs(30, 27);
        allBooks = getAllBooksForRetailerIDs(30,27);

        System.out.println("Books for retailer ID");
        for (Book b : uniqueBooks) {
            System.out.print(b.name + " ");
        }
        System.out.println();
        System.out.println("Languages for retailer ID");
        for (Language l : languages) {
            System.out.print(l.name + " ");
        }
        System.out.println();
        System.out.println("Countries for book ID");
        for (Countries c : countries) {
            System.out.print(c.countryCode + " ");
        }
        System.out.println();
        System.out.println("Common books for two retailers:");
        for (Book b : commonBooks) {
            System.out.print(b.name + " ");
        }
        System.out.println();
        System.out.println("All books for two retailers:");
        for (Book b : allBooks) {
            System.out.print(b.name + " ");
        }

    }
}
