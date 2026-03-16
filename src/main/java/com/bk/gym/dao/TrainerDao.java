package com.bk.gym.dao;

import com.bk.gym.entity.Trainer;
import java.util.List;

public interface TrainerDao {
    void save(Trainer trainer);
    Trainer findById(Long id);
    List<Trainer> findAll();
    void update(Trainer trainer);
    void delete(Long id);
}
