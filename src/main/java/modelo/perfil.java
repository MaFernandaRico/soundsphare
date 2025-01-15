// UserProfile.java
package modelo;

import java.util.List;

public class perfil {
    private String username;
    private String email;
    private String registrationDate;
    private String birthDate;
    private int friendsCount;
    private List<String> posts;
    private List<String> friends;

    public perfil(String username, String email, String registrationDate, String birthDate, int friendsCount, List<String> posts, List<String> friends) {
        this.username = username;
        this.email = email;
        this.registrationDate = registrationDate;
        this.birthDate = birthDate;
        this.friendsCount = friendsCount;
        this.posts = posts;
        this.friends = friends;
    }

    // Getters y setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public int getFriendsCount() {
        return friendsCount;
    }

    public void setFriendsCount(int friendsCount) {
        this.friendsCount = friendsCount;
    }

    public List<String> getPosts() {
        return posts;
    }

    public void setPosts(List<String> posts) {
        this.posts = posts;
    }

    public List<String> getFriends() {
        return friends;
    }

    public void setFriends(List<String> friends) {
        this.friends = friends;
    }
}
