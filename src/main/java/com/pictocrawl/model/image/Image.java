package com.pictocrawl.model.image;

public abstract class Image implements Comparable<Image> {
    private final int id;
    private final String name;
    private final boolean isLogo;
    private final String url;
        private final int websiteId;

    public Image(int id, String name, boolean isLogo, String url, int websiteId) {
        this.id = id;
        this.name = name;
        this.isLogo = isLogo;
        this.url = url;
        this.websiteId = websiteId;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isLogo() {
        return isLogo;
    }

    public String getUrl() {
        return url;
    }

    public int getWebsiteId() {
        return websiteId;
    }

    public abstract String getType();

}