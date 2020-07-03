package com.blockverse.issues.persistence.repository;

import com.blockverse.issues.persistence.entity.IssueEntity;

import net.logstash.logback.encoder.org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class RepositoryService {

    private IssueRepository issueRepository;

    @Autowired
    public RepositoryService(IssueRepository issueRepository) {
        this.issueRepository = issueRepository;
    }

    public void saveAllIssues(List<IssueEntity> issueEntities) {
        try {
            issueRepository.saveAll(issueEntities);
        } catch (Exception exception) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    ExceptionUtils.getRootCauseMessage(exception.getCause()));
        }
    }

    public IssueEntity findIssueById(Long id) {
        return issueRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Issue with id:" + id + " not found"));
    }

    public IssueEntity saveIssue(IssueEntity issueEntity) {
        try {
            return issueRepository.save(issueEntity);
        } catch (Exception exception) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    ExceptionUtils.getRootCauseMessage(exception.getCause()));
        }
    }

    public List<IssueEntity> findAllIssues() {
        try {
            return issueRepository.findAll();
        } catch (Exception exception) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    ExceptionUtils.getRootCauseMessage(exception.getCause()));
        }
    }

    public List<IssueEntity> findIssueByTemplateID(Long templateID) {
        try {
            return issueRepository.findByTemplateID(templateID);
        } catch (Exception exception) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    ExceptionUtils.getRootCauseMessage(exception.getCause()));
        }
    }



    public void deleteIssue(IssueEntity issueEntity) {
        try {
            issueRepository.delete(issueEntity);
        } catch (Exception exception) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Error while deleting data from database on record with ID: " + issueEntity.getId());
        }
    }
}
