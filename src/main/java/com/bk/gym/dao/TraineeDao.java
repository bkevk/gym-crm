package com.bk.gym.dao;

import com.bk.gym.model.Trainee;
import java.util.List;

public interface TraineeDao {
    void save(Trainee trainee);
    Trainee findById(Long id);
    List<Trainee> findAll();
    void update(Trainee trainee);
    void delete(Long id);
}
