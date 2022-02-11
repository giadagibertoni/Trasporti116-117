package httprequest;

public class ResourceNotFoundException extends Exception  {
    public ResourceNotFoundException() {
        super("Resource not found");
    }
}
