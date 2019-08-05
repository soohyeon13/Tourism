package com.example.tourism.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

//레트로핏으로부터 받아오는 데이터의 모든걸 표현할 수 있도록 변수를 모두 만들어 주어야됨.
public class ResultVO {
    //변수의 이름이 SerializedName이랑 같으면 쓸필요 없지만 그냥 써줬음
    @SerializedName("meta")
    public Meta meta;

    @SerializedName("documents")
    public List<Document> documents;

    public Meta getMeta() {
        return meta;
    }

    public List<Document> getDocuments() {
        return documents;
    }

    public class Meta{
        @SerializedName("total_count")
        Integer totalCount;
        @SerializedName("pageable_count")
        Integer pageableCount;
        @SerializedName("is_end")
        Boolean isEnd;

        public Integer getTotalCount() {
            return totalCount;
        }

        public Integer getPageableCount() {
            return pageableCount;
        }

        public Boolean getEnd() {
            return isEnd;
        }
    }
    public class Document {
        @SerializedName("image_url")
        public String imageUrl;

        @SerializedName("thumbnail_url")
        public String thumbnailUrl;

        @SerializedName("doc_url")
        public String docUrl;

        @SerializedName("display_sitename")
        public String displaySitename;

        public String collection;
        public String datetime;
        public Integer width;
        public Integer height;

        public String getImageUrl() {
            return imageUrl;
        }

        public String getThumbnailUrl() {
            return thumbnailUrl;
        }

        public String getDocUrl() {
            return docUrl;
        }

        public String getDisplaySitename() {
            return displaySitename;
        }

        public String getCollection() {
            return collection;
        }

        public String getDatetime() {
            return datetime;
        }

        public Integer getWidth() {
            return width;
        }

        public Integer getHeight() {
            return height;
        }
    }
}
