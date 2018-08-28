package com.svetikov.datespring.repository;

import com.svetikov.datespring.models.ImagesModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface ImageRepository extends CrudRepository<ImagesModel,Long> {
    @Query(value = "select image_model.id_image from image_model",nativeQuery = true)
    List<Long> findAllIDImage();

    @Query(value = "select * from image_model i",nativeQuery = true)
    List<Object> findAllImageModel();
}
