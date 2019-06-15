package com.example.news.model;

import java.net.URL;
import java.util.List;

public class ModelClass {

    private String status;
    private int totalResults;
    private List<Articles> articles;


//
//    public modelClass(String status, int totalResults, List<Articles> articles) {
//        this.status = status;
//        this.totalResults = totalResults;
//        this.articles = articles;
//    }

    public String getStatus() {
        return status;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public List<Articles> getArticles() {
        return articles;
    }

    public class Articles {
        private String author;
        private String title;
        private String description;
        private String url;
        private String urlToImage;
        private String publishedAt;
        private String content;
        private Source source;

        public Source getSource() {
            return source;
        }

        public String getAuthor() {
            return author;
        }

        public String getTitle() {
            return title;
        }

        public String getDescription() {
            return description;
        }

        public String getUrl() {
            return url;
        }

        public String getUrlToImage() {
            return urlToImage;
        }

        public String getPublishedAt() {
            return publishedAt;
        }

        public String getContent() {
            return content;
        }

        public class Source {
            private String id;
            private String name;

            public String getId() {
                return id;
            }

            public String getName() {
                return name;
            }
        }
    }

}

