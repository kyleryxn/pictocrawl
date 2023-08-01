package com.pictocrawl.model.image;

public class GIFImage extends Image {

    public GIFImage(int id, String name, boolean isLogo, String url) {
        super(id, name, isLogo, url);
    }

    @Override
    public String getType() {
        return GIFImage.class.getTypeName();
    }

    @Override
    public String toString() {
        return "GIFImage{" +
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