package com.bk.core.dao;

import com.bk.core.model.Training;
import java.util.List;

public interface TrainingDao {
    void save(Long id, Training training);
    Training findById(Long id);
    List<Training> findAll();
    void update(Long id, Training training);
    void delete(Long id);
}