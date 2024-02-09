package com.pictocrawl.model.image;

public class JPEGImage extends Image {

    public JPEGImage(int id, String name, boolean isLogo, String url, int websiteId) {
        super(id, name, isLogo, url, websiteId);
    }

    @Override
    public String getType() {
        return "JPEG";
    }

    @Override
    public String toString() {
        return "JPEGImage{" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                ", isLogo=" + isLogo()+
                ", url='" + getUrl() + '\'' +
                ", websiteId='" + getWebsiteId() + '\'' +
                '}';
    }

    @Override
    public int compareTo(Image o) {
        return Integer.compare(getId(), o.getId());
    }

}
