package com.svetikov.datespring.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Blob;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "image_model")
public class ImagesModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id_image;
    @Lob
    private byte[] blob_image;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date date_image;

    public ImagesModel() {
    }

    public ImagesModel(byte[] blob_image, Date date_image) {
        this.blob_image = blob_image;
        this.date_image = date_image;
    }
}
