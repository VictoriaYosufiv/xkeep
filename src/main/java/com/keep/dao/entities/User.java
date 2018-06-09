package com.keep.dao.entities;

import java.util.Objects;

 /**
 * Клас, що відповідає рядку таблиці user. Кожен об'єкт цього класу містить дані одного рядка.
  */

public class User {

    private long id;
    private String username;
    private String password;
    private String name;
    private String status;
    private String role;

     //Конструктори
    public User(long id, String username, String password, String name, String status, String role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.status = status;
        this.role = role;
    }
    //не знаю чи треба??????
     public User(String username, String password, String name, String status, String role) {
         this.id = 0L;
         this.username = username;
         this.password = password;
         this.name = name;
         this.status = status;
         this.role = role;
     }
    public User() {
    }

     //Методи Get та Set

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }



     /**Переробила тільки на login
      * Check login
      * @param username
      * @return true if user exists
      */
     public boolean loginbpCheck(String username, String password){

         if(username.toLowerCase().equals(this.username.toLowerCase())){
             return true;
         }

         return false;
     }


    /**
     * Check login and password
     * @param username
     * @param password
     * @return true if user exists
     */
    public boolean loginCheck(String username, String password){

        if(username.toLowerCase().equals(this.username.toLowerCase())
                && password.equals(this.password)){
            return true;
        }

        return false;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", status='" + status + '\'' +
                ", role='" + role + '\'' +
                '}';
    }

    // Порівняння двох об’єктів User
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o; //приведення типів

        return id == user.id &&  //трохи по іншому
                Objects.equals(username, user.username) &&
                Objects.equals(name, user.name) &&
                Objects.equals(status, user.status) &&
                Objects.equals(role, user.role);
    }

   // Переведення об1єкта в число // трошки по іншому
    @Override
    public int hashCode() {

        return Objects.hash(id, username, name, status, role);
    }
}
