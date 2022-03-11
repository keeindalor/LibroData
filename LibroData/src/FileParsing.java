import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.security.Key;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.StringTokenizer;

public class FileParsing {
    static List<Author> authors = new ArrayList<Author>();
    static List<Book> books = new ArrayList<Book>();
    static List<Language> languages = new ArrayList<Language>();
    static List<EditorialGroup> groups = new ArrayList<EditorialGroup>();
    static List<PublishingBrand> brands = new ArrayList<PublishingBrand>();
    static List<PublishingRetailers> retailers = new ArrayList<PublishingRetailers>();
    static List<Countries> countries = new ArrayList<Countries>();

    static void readFromFile() {
        try (BufferedReader br = new BufferedReader(new FileReader("books.in"))) {
            String line;
            String[] lineProceser;
            String[] dateProceser;
            int count = 0;
            while ((line = br.readLine()) != null) {
                if (count == 0) {
                    count++;
                    continue;
                }
                lineProceser = line.split("###");
                String ID = lineProceser[0],name = lineProceser[1], subtitle = lineProceser[2], ISBN = lineProceser[3],
                        pageCount = lineProceser[4], keywords = lineProceser[5], languageId = lineProceser[6];
                Calendar createdOn = Calendar.getInstance();
                dateProceser = lineProceser[7].split("\\.| |:");
                createdOn.set(Calendar.DAY_OF_MONTH, Integer.parseInt(dateProceser[0]));
                createdOn.set(Calendar.MONTH, Integer.parseInt(dateProceser[1]));
                createdOn.set(Calendar.YEAR, Integer.parseInt(dateProceser[2]));
                createdOn.set(Calendar.HOUR, Integer.parseInt(dateProceser[3]));
                createdOn.set(Calendar.MINUTE, Integer.parseInt(dateProceser[4]));
                createdOn.set(Calendar.SECOND, Integer.parseInt(dateProceser[5]));
                Book book = new Book(name, subtitle, ISBN, keywords, languageId, ID, pageCount, createdOn);
                books.add(book);
            }
        }
        catch (FileNotFoundException ex){
            System.out.println("File not found");
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedReader br = new BufferedReader(new FileReader("authors.in"))) {
            int count = 0;
            String line;
            String[] lineProceser;
            while ((line = br.readLine()) != null) {
                if (count == 0) {
                    count++;
                    continue;
                }
                lineProceser = line.split("###");
                String firstName, lastName, ID;
                firstName = lineProceser[1];
                lastName = lineProceser[2];
                ID = lineProceser[0];
                Author author = new Author(firstName, lastName, ID);
                authors.add(author);
            }
        }
        catch (FileNotFoundException ex){
            System.out.println("File not found");
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedReader br = new BufferedReader(new FileReader("countries.in"))) {
            int count = 0;
            String line;
            String[] lineProceser;
            while ((line = br.readLine()) != null) {
                if (count == 0) {
                    count++;
                    continue;
                }
                lineProceser = line.split("###");
                String ID, countryCode;
                ID = lineProceser[0];
                countryCode = lineProceser[1];
                Countries country = new Countries(ID, countryCode);
                countries.add(country);
            }
        }
        catch (FileNotFoundException ex){
            System.out.println("File not found");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        try (BufferedReader br = new BufferedReader(new FileReader("editorial-groups.in"))) {
            int count = 0;
            String line;
            String[] lineProceser;
            while ((line = br.readLine()) != null) {
                if (count == 0) {
                    count++;
                    continue;
                }
                lineProceser = line.split("###");
                String ID, name;
                ID = lineProceser[0];
                name = lineProceser[1];
                EditorialGroup group = new EditorialGroup(name, ID);
                groups.add(group);
            }
        }
        catch (FileNotFoundException ex){
            System.out.println("File not found");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        try (BufferedReader br = new BufferedReader(new FileReader("publishing-brands.in"))) {
            int count = 0;
            String line;
            String[] lineProceser;
            while ((line = br.readLine()) != null) {
                if (count == 0) {
                    count++;
                    continue;
                }
                lineProceser = line.split("###");
                String ID, name;
                ID = lineProceser[0];
                name = lineProceser[1];
                PublishingBrand brand = new PublishingBrand(name, ID);
                brands.add(brand);
            }
        }
        catch (FileNotFoundException ex){
            System.out.println("File not found");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        try (BufferedReader br = new BufferedReader(new FileReader("publishing-retailers.in"))) {
            int count = 0;
            String line;
            String[] lineProceser;
            while ((line = br.readLine()) != null) {
                if (count == 0) {
                    count++;
                    continue;
                }
                lineProceser = line.split("###");
                String ID, name;
                ID = lineProceser[0];
                name = lineProceser[1];
                PublishingRetailers retailer = new PublishingRetailers(ID, name);
                retailers.add(retailer);
            }
        }
        catch (FileNotFoundException ex){
            System.out.println("File not found");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        try (BufferedReader br = new BufferedReader(new FileReader("languages.in"))) {
            int count = 0;
            String line;
            String[] lineProceser;
            while ((line = br.readLine()) != null) {
                if (count == 0) {
                    count++;
                    continue;
                }
                lineProceser = line.split("###");
                String ID, code, translation;
                ID = lineProceser[0];
                code = lineProceser[1];
                translation = lineProceser[2];
                Language language = new Language(code, translation, ID);
                languages.add(language);

            }
        }
        catch (FileNotFoundException ex){
            System.out.println("File not found");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        try (BufferedReader br = new BufferedReader(new FileReader("books-authors.in"))) {
            int count = 0;
            String line;
            String[] lineProceser;
            while ((line = br.readLine()) != null) {
                if (count == 0) {
                    count++;
                    continue;
                }
                lineProceser = line.split("###");
                String bookID, authorID;
                bookID = lineProceser[0];
                authorID = lineProceser[1];
                for (Book b : books) {
                    if (b.ID.equals(bookID)) {
                        for (Author a : authors) {
                            if (a.ID.equals(authorID)) {
                                b.authors.add(a);
                                break;
                            }
                        }
                        break;
                    }
                }
            }
        }
        catch (FileNotFoundException ex){
            System.out.println("File not found");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        try (BufferedReader br = new BufferedReader(new FileReader("editorial-groups-books.in"))) {
            int count = 0;
            String line;
            String[] lineProceser;
            while ((line = br.readLine()) != null) {
                if (count == 0) {
                    count++;
                    continue;
                }
                lineProceser = line.split("###");
                String bookID, groupID;
                bookID = lineProceser[1];
                groupID = lineProceser[0];
                for (EditorialGroup g : groups){
                    if (g.ID.equals(groupID)) {
                        for (Book b : books) {
                            if (b.ID.equals(bookID)) {
                                g.books.add(b);
                                break;
                            }
                        }
                        break;
                    }
                }
            }
        }
        catch (FileNotFoundException ex){
            System.out.println("File not found");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        try (BufferedReader br = new BufferedReader(new FileReader("publishing-brands-books.in"))) {
            int count = 0;
            String line;
            String[] lineProceser;
            while ((line = br.readLine()) != null) {
                if (count == 0) {
                    count++;
                    continue;
                }
                lineProceser = line.split("###");
                String bookID, publisherID;
                bookID = lineProceser[1];
                publisherID = lineProceser[0];
                for (PublishingBrand p : brands) {
                    if (p.ID.equals(publisherID)) {
                        for (Book b : books) {
                            if (b.ID.equals(bookID)) {
                                p.books.add(b);
                                break;
                            }
                        }
                        break;
                    }
                }
            }
        }
        catch (FileNotFoundException ex){
            System.out.println("File not found");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        try (BufferedReader br = new BufferedReader(new FileReader("publishing-retailers-books.in"))) {
            int count = 0;
            String line;
            String[] lineProceser;
            while ((line = br.readLine()) != null) {
                if (count == 0) {
                    count++;
                    continue;
                }
                lineProceser = line.split("###");
                String bookID, retailerID;
                bookID = lineProceser[1];
                retailerID = lineProceser[0];
                for (PublishingRetailers r : retailers) {
                    if (r.ID.equals(retailerID)) {
                        for (Book b : books) {
                            if (b.ID.equals(bookID)) {
                                r.artifacts.add(b);
                                break;
                            }
                        }
                        break;
                    }
                }
            }
        }
        catch (FileNotFoundException ex){
            System.out.println("File not found");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        try (BufferedReader br = new BufferedReader(new FileReader("publishing-retailers-countries.in"))) {
            int count = 0;
            String line;
            String[] lineProceser;
            while ((line = br.readLine()) != null) {
                if (count == 0) {
                    count++;
                    continue;
                }
                lineProceser = line.split("###");
                String countryID, retailerID;
                countryID = lineProceser[1];
                retailerID = lineProceser[0];
                for (PublishingRetailers r : retailers) {
                    if (r.ID.equals(retailerID)) {
                        for (Countries c : countries) {
                            if (c.ID.equals(countryID)) {
                                r.countries.add(c);
                                break;
                            }
                        }
                        break;
                    }
                }
            }
        }
        catch (FileNotFoundException ex){
            System.out.println("File not found");
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedReader br = new BufferedReader(new FileReader("publishing-retailers-editorial" +
                "-groups.in"))) {
            int count = 0;
            String line;
            String[] lineProceser;
            while ((line = br.readLine()) != null) {
                if (count == 0) {
                    count++;
                    continue;
                }
                lineProceser = line.split("###");
                String groupID, retailerID;
                groupID = lineProceser[1];
                retailerID = lineProceser[0];
                for (PublishingRetailers r : retailers) {
                    if (r.ID.equals(retailerID)) {
                        for (EditorialGroup g : groups) {
                            if (g.ID.equals(groupID)) {
                                r.artifacts.add(g);
                                break;
                            }
                        }
                        break;
                    }
                }
            }
        }
        catch (FileNotFoundException ex){
            System.out.println("File not found");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        try (BufferedReader br = new BufferedReader(new FileReader("publishing-retailers-publishing" +
                "-brands.in"))) {
            int count = 0;
            String line;
            String[] lineProceser;
            while ((line = br.readLine()) != null) {
                if (count == 0) {
                    count++;
                    continue;
                }
                lineProceser = line.split("###");
                String groupID, retailerID;
                groupID = lineProceser[1];
                retailerID = lineProceser[0];
                for (PublishingRetailers r : retailers) {
                    if (r.ID.equals(retailerID)) {
                        for (PublishingBrand b : brands) {
                            if (b.ID.equals(groupID)) {
                                r.artifacts.add(b);
                                break;
                            }
                        }
                        break;
                    }
                }
            }
        }
        catch (FileNotFoundException ex){
            System.out.println("File not found");
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }
}
