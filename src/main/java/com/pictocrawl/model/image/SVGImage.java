package com.pictocrawl.model.image;

public class SVGImage extends Image {

    public SVGImage(int id, String name, boolean isLogo, String url) {
        super(id, name, isLogo, url);
    }

    @Override
    public String getType() {
        return null;
    }

    @Override
    public String toString() {
        return "SVGImage{" +
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