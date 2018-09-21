package com.nuhs.gcto.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nuhs.gcto.model.Issue;

@Repository
public interface IssueRepository extends CrudRepository<Issue, Long>{
    List<Issue> findByUser(String user);

}
