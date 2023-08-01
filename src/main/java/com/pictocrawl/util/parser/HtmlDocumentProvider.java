package com.pictocrawl.util.parser;

import org.jsoup.nodes.Document;

public interface HtmlDocumentProvider {

    Document getDocument(String url);

}