package com.bk.gym.dao;

import com.bk.gym.entity.Training;
import java.util.List;

public interface TrainingDao {
    void save(Long id, Training training);
    Training findById(Long id);
    List<Training> findAll();
    void update(Long id, Training training);
    void delete(Long id);
}
