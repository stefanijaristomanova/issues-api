package com.blockverse.issues.service.impl;

import com.blockverse.issues.dto.Issue;
import com.blockverse.issues.dto.request.AddIssueRequest;
import com.blockverse.issues.persistence.entity.IssueEntity;
import com.blockverse.issues.persistence.repository.RepositoryService;
import com.blockverse.issues.service.IssueService;
import com.blockverse.issues.utils.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.blockverse.issues.utils.SequenceGenerator;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;


@Service
public class IssueServiceImpl implements IssueService{

    private RepositoryService repositoryService;
    private SequenceGenerator sequenceGenerator;

    @Autowired
    public IssueServiceImpl(RepositoryService repositoryService, SequenceGenerator sequenceGenerator) {
        this.repositoryService = repositoryService;
        this.sequenceGenerator = sequenceGenerator;
    }

    @Override
    public List<Issue> findAllIssues() {
        return repositoryService.findAllIssues()
                .stream()
                .collect(Collectors.toMap(
                        IssueEntity::getTemplateID,
                        Function.identity(),
                        BinaryOperator.maxBy(Comparator.comparing(IssueEntity::getVersion))))
                .values()
                .stream()
                .sorted(Comparator.comparing(IssueEntity::getName))
                .map(ConvertUtils::convertToIssues)
                .collect(Collectors.toList());
    }

    @Override
    public Issue findIssueById(Long id) {
        return ConvertUtils.convertToIssues(repositoryService.findIssueById(id));
    }


    @Override
    public Issue addIssue(AddIssueRequest addIssueRequest) {

        IssueEntity issueEntity = IssueEntity.builder()
                .templateID(generateTemplateId())
                .version(Integer.valueOf("1"))
                .status("Open")
                .createdBy("user")
                .dateCreated(LocalDateTime.now())
                .modifiedBy("user")
                .dateModified(LocalDateTime.now())
                .deleted(Integer.valueOf(0))
                .name(addIssueRequest.getName())
                .description(addIssueRequest.getDescription())
                .type(addIssueRequest.getType())
                .build();


        return ConvertUtils.convertToIssues(repositoryService.saveIssue(issueEntity));
    }

    @Override
    public Issue addNewVersionOfIssue(Long templateID, AddIssueRequest addIssueRequest) {
        IssueEntity issueEntity = IssueEntity.builder()
                .templateID(templateID)
                .version(findNextVersion(templateID))
                .status("In Progress")
                .createdBy("user")
                .dateCreated(LocalDateTime.now())
                .modifiedBy("user")
                .dateModified(LocalDateTime.now())
                .deleted(Integer.valueOf(0))
                .name(addIssueRequest.getName())
                .description(addIssueRequest.getDescription())
                .type(addIssueRequest.getType())
                .build();

        return ConvertUtils.convertToIssues(repositoryService.saveIssue(issueEntity));
    }

    @Override
    public List<Issue> findHistoryByTemplateID(Long templateID) {
        return ConvertUtils.convertToFindHistoryByTemplateID(repositoryService.findIssueByTemplateID(templateID)
                .stream()
                .sorted(Comparator.comparing(IssueEntity::getVersion))
                .collect(Collectors.toList()));
    }

    @Override
    public Integer findNextVersion(Long templateID) {
        List<IssueEntity> historyList = repositoryService.findIssueByTemplateID(templateID);
        return historyList.size() + 1;
    }

    @Override
    public String removeIssue(Long id) {
        IssueEntity issueEntity = repositoryService.findIssueById(id);
        repositoryService.deleteIssue(issueEntity);
        return "Successfully deleted issue with id:" + id;
    }

    private Long generateTemplateId() {
        return sequenceGenerator.nextId();
    }
}
