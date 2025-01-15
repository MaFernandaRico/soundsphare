package modelo;

public class publicacion {
    private int id;
    private String username;
    private String content;
    private String date;

    public publicacion(String username, String content, String date) {
        this.username = username;
        this.content = content;
        this.date = date;
    }

    public publicacion(int id, String username, String content, String date) {
        this.id = id;
        this.username = username;
        this.content = content;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getContent() {
        return content;
    }

    public String getDate() {
        return date;
    }
}
