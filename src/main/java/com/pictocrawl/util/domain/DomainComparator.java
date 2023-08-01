package com.pictocrawl.util.domain;

import com.pictocrawl.util.URLUtility;

public class DomainComparator implements Comparator {
    private final DomainResolver resolver;
    private final URLUtility urlUtils;

    public DomainComparator() {
        resolver = new DomainResolver();
        urlUtils = new URLUtility();
    }

    @Override
    public boolean isSameDomain(String url, String domain) {
        if (urlUtils.validate(url)) {
            String compareDomain = resolver.getDomain(url);
            return compareDomain.equals(domain);
        } else {
            throw new IllegalArgumentException("Invalid URL");
        }
    }

}