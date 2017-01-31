package com.dao;

import com.elections.classes.Candidate;
import com.elections.classes.Voter;

public interface ElectionsDao {
	Candidate getCandidate();
	Voter getVoter();
}
