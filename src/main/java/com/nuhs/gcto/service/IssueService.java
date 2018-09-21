package com.nuhs.gcto.service;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nuhs.gcto.model.Issue;
import com.nuhs.gcto.repository.IssueRepository;

@Service
public class IssueService {
	final static Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	@Autowired
	private IssueRepository issueRepository;

	public Issue addIssue(Issue issue) {
		logger.debug("addIssue");
        Issue savedIssue = issueRepository.save(issue);
        return savedIssue;
	}
	
	public List getAllIssues() {
		logger.debug("getAllIssues");
		List<Issue> issues = new ArrayList<>();
		issueRepository.findAll().forEach(e -> issues.add(e));
		return issues;
	}

	public List getTopIssues(int size) {
		logger.debug("getTopIssues");
		List issues = new ArrayList<>();
		Iterable iterable = issueRepository.findAll();
		Iterator iterator = iterable.iterator(); 
		int count = 0;
		while(iterator.hasNext()) {
			issues.add(iterator.next());
			count++;
			if(count == size) {
				break;
			}
		}
		return issues;
	}
}
