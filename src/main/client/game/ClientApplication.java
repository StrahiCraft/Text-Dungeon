package client.game;

public class ClientApplication {
    private static Client client;

    public static Client getClientInstance(){
        return client;
    }

    public static void main(String[] args) {
        client = new Client();
        client.start();
    }
}
