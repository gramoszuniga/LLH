/*
    Feed.java
    Final Project

    Revision History:
        Gonzalo Ramos Zúñiga, 2017.12.17: Created
 */

package ca.on.einfari.llh.data;

public class Feed {

    private String title;
    private String link;
    private String pubDate;

    public Feed(String title, String link, String pubDate) {
        this.title = title;
        this.link = link;
        this.pubDate = pubDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

}