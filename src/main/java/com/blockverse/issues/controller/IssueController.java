package com.blockverse.issues.controller;

import com.blockverse.issues.dto.Issue;
import com.blockverse.issues.dto.request.AddIssueRequest;
import com.blockverse.issues.service.IssueService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;


@Slf4j
@CrossOrigin
@RestController
@RequestMapping("v1/issue-service/issues")
@Api(value = "Providing operations for the issue entity")
public class IssueController {

    private IssueService issueService;

    @Autowired
    public IssueController(IssueService issueService) {
        this.issueService = issueService;
    }

    @ApiOperation(value = "Find all issues", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Issues successfully found"),
            @ApiResponse(code = 500, message = "Internal server error while fetching all issues")
    })
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<List<Issue>> findAllIssues() {
        return ResponseEntity.ok(issueService.findAllIssues());
    }

    @ApiOperation(value = "Find a issue by id", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Issue successfully found"),
            @ApiResponse(code = 404, message = "The issue you were trying to retrieve was not found")
    })
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Issue> findIssueById(@PathVariable Long id) {
        Issue response = issueService.findIssueById(id);
        if (response != null) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @ApiOperation(value = "Remove an issue ", response = ResponseEntity.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Issue successfully removed"), @ApiResponse(code = 500, message = "Problem while removing the issue")})
    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<String> removeIssue(@PathVariable("id") Long id) {
        return ResponseEntity.ok(issueService.removeIssue(id));
    }


    @ApiOperation(value = "Find history records for a issue by the template id", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "History records successfully found"),
            @ApiResponse(code = 404, message = "The history record you were trying to retrieve was not found")
    })
    @GetMapping(value = "/history/{templateID}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<List<Issue>> findHistoryByTemplateID(@PathVariable("templateID") Long templateID) {
        List<Issue> response = issueService.findHistoryByTemplateID(templateID);
        if (response != null && !CollectionUtils.isEmpty(response)) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @ApiOperation(value = "Add a issue", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Issue successfully created"),
            @ApiResponse(code = 500, message = "Problem while creating the issue")
    })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Issue> addIssue(@Valid @RequestBody AddIssueRequest addIssueRequest) {
        Issue response = issueService.addIssue(addIssueRequest);
        if (response != null) {
            return ResponseEntity.created(URI.create("")).body(response);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Issue.builder().build());
        }
    }

    @ApiOperation(value = "Add new version of issue", response = ResponseEntity.class)
    @ApiResponses(value = {@ApiResponse(code = 201, message = "Issue successfully created"), @ApiResponse(code = 500, message = "Problem while creating the template")})
    @PostMapping(value = "/{templateID}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Issue> addNewVersionOfIssue(@PathVariable("templateID") Long templateID, @Valid @RequestBody AddIssueRequest addIssueRequest) {
        Issue response = issueService.addNewVersionOfIssue(templateID, addIssueRequest);
        if (response != null) {
            return ResponseEntity.created(URI.create("")).body(response);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Issue.builder().build());
        }
    }
}
