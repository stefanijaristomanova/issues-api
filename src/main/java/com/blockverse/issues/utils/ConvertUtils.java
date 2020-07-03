package com.blockverse.issues.utils;

import com.blockverse.issues.dto.Issue;
import com.blockverse.issues.persistence.entity.IssueEntity;

import java.util.List;
import java.util.stream.Collectors;


public class ConvertUtils {

    public static Issue convertToIssues(IssueEntity issueEntity) {
        ModelMapperUtils modelMapper = new ModelMapperUtils();

        Issue response = modelMapper.map(issueEntity, Issue.class);

        return response;
    }

    public static List<Issue> convertToFindHistoryByTemplateID(List<IssueEntity> issueEntities) {
        return issueEntities.stream()
                .map(ConvertUtils::convertToIssues)
                .collect(Collectors.toList());
    }
}
