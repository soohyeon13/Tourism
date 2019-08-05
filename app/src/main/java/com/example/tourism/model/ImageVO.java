package com.example.tourism.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ImageVO {
    @SerializedName("meta")
    public Meta meta;

    public Meta getMeta() {return meta;}

    @SerializedName("documents")
    public List<Document> documents;

    public List<Document> getDocuments() {return documents;}

    public class Meta {
        @SerializedName("total_count")
        Integer totalCount;
        @SerializedName("pageable_count")
        Integer pageableCount;
        @SerializedName("is_end")
        Boolean isEnd;

        private Integer getTotalCount() {return totalCount;}
        private Integer getPageableCount() {return pageableCount;}
        private Boolean getIsEnd() {return isEnd;}
    }

    public class Document {
        @SerializedName("image_url")
        public String imageUrl;

        private String getImageUrl() {return imageUrl;}
    }
}
