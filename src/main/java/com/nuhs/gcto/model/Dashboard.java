package com.nuhs.gcto.model;

import java.util.List;

public class Dashboard {
	int issueCount;
	int ideaCount;
	int innovationCount;
	int industryCount;
	List issues;
	List ideas;
	List innovations;
	List industries;

	public List getIdeas() {
		return ideas;
	}
	public void setIdeas(List ideas) {
		this.ideas = ideas;
	}
	public List getInnovations() {
		return innovations;
	}
	public void setInnovations(List innovations) {
		this.innovations = innovations;
	}
	public List getIndustries() {
		return industries;
	}
	public void setIndustries(List industries) {
		this.industries = industries;
	}
	public List getIssues() {
		return issues;
	}
	public void setIssues(List issues) {
		this.issues = issues;
	}

	public int getIssueCount() {
		return issueCount;
	}
	public void setIssueCount(int issueCount) {
		this.issueCount = issueCount;
	}
	public int getIdeaCount() {
		return ideaCount;
	}
	public void setIdeaCount(int ideaCount) {
		this.ideaCount = ideaCount;
	}
	public int getInnovationCount() {
		return innovationCount;
	}
	public void setInnovationCount(int innovationCount) {
		this.innovationCount = innovationCount;
	}
	public int getIndustryCount() {
		return industryCount;
	}
	public void setIndustryCount(int industryCount) {
		this.industryCount = industryCount;
	}


}
