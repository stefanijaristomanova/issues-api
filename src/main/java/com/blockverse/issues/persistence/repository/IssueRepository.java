package com.blockverse.issues.persistence.repository;

import com.blockverse.issues.persistence.entity.IssueEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface IssueRepository extends JpaRepository<IssueEntity, Long> {

    List<IssueEntity> findByTemplateID(Long templateID);

}