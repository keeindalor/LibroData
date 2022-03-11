import java.util.ArrayList;
import java.util.List;

public class PublishingRetailers {
    String ID, name;
    List<IPublishingArtifact> artifacts = new ArrayList<IPublishingArtifact>();
    List<Countries> countries = new ArrayList<Countries>();

    public PublishingRetailers(String ID, String name) {
        this.ID = ID;
        this.name = name;
    }
}
