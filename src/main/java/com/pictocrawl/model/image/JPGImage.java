package com.pictocrawl.model.image;

public class JPGImage extends Image {

    public JPGImage(int id, String name, boolean isLogo, String url) {
        super(id, name, isLogo, url);
    }

    @Override
    public String getType() {
        return null;
    }

    @Override
    public String toString() {
        return "JPGImage{" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                ", isLogo=" + isLogo()+
                ", url='" + getUrl() + '\'' +
                '}';
    }

    @Override
    public int compareTo(Image o) {
        return Integer.compare(getId(), o.getId());
    }

}