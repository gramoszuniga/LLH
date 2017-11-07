/*
    Quote.java
    Assignment 2

    Revision History:
        Gonzalo Ramos Zúñiga, 2017.10.29: Created
 */

package ca.on.einfari.llh.data;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Quote {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String email;
    private String description;

    public Quote(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}