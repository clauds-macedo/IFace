public class FeedException extends Exception{

    String message;
    public FeedException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return "Ocorreu um erro ao postar no feed: " + message;
    }
}
