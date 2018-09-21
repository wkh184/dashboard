package com.nuhs.gcto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nuhs.gcto.model.Dashboard;

@Service
public class DashboardService {
	@Autowired
	private IssueService issueService;
	
	public Dashboard prepareDashBoard() {
		Dashboard dashboard = new Dashboard();
		dashboard.setIssueCount(100);
		dashboard.setIdeaCount(200);
		dashboard.setInnovationCount(300);
		dashboard.setIndustryCount(100);
		List issues = issueService.getTopIssues(5);
		dashboard.setIssues(issues);
		return dashboard;		
	}
}
