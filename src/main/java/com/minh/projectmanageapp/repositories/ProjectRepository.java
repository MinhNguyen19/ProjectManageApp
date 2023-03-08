package com.minh.projectmanageapp.repositories;

import com.minh.projectmanageapp.domain.Project;
import org.springframework.data.repository.CrudRepository;

public interface ProjectRepository extends CrudRepository<Project, Long> {
    @Override
    Iterable<Project> findAllById(Iterable<Long> longs);
}
