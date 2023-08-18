package com.pictocrawl.model.image;

public class SVGImage extends Image {

    public SVGImage(int id, String name, boolean isLogo, String url, int websiteId) {
        super(id, name, isLogo, url, websiteId);
    }

    @Override
    public String getType() {
        return "SVG";
    }

    @Override
    public String toString() {
        return "SVGImage{" +
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