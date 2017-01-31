package com.main;

import java.util.HashSet;
import java.util.Set;

import com.dao.ElectionsDaoJdbc;
import com.elections.classes.Candidate;
import com.elections.classes.Elections;
import com.elections.classes.Voter;

public class Main {

	public static void main(String[] args) {
		ElectionsDaoJdbc db = ElectionsDaoJdbc.getInstance();
		
		Set<Candidate> candidates = new HashSet<>();
		for(int i =0; i<10; i++){
			candidates.add(db.getCandidate());
		}
		
		candidates.forEach(a -> {System.out.println(a);});
		
		Set<Voter> voters = new HashSet<>();
		for(int i =0; i<25; i++){
			voters.add(db.getVoter());
		}
		voters.forEach(a -> {System.out.println(a);});
		
		
		Elections el = new Elections();
		el.getVotersAndCandidatesromDatabase();
		el.fillVotersCandidates();
		el.voteMethod();
		
		el.showVotersList();
	}

}
