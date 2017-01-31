package com.elections.classes;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import com.dao.ElectionsDao;
import com.dao.ElectionsDaoJdbc;

public class Elections {
	private static Set<Candidate> candidates = new TreeSet<>((c1, c2) -> {
		if (c1.getFirstName().equals(c2.getFirstName()))
		{
			if (c1.getLastName().equals(c2.getLastName()))
			{
				return c1.getConstituency().compareTo(c2.getConstituency());
			}
			return c1.getLastName().compareTo(c2.getLastName());
		}
		return c1.getFirstName().compareTo(c2.getFirstName());
	});
	
	private static Set<Voter> voters = new HashSet<>();
	
	
	
	public static void getVotersAndCandidatesromDatabase()
	{
		ElectionsDao db = ElectionsDaoJdbc.getInstance();
		for(int i =0; i<10; i++){
			candidates.add(db.getCandidate());
				
		}
		for(int i =0; i<25; i++){
			voters.add(db.getVoter());
		}
	}
	public static void fillVotersCandidates(){
		for(Voter v : voters )
		{
			v.setCandidates(
					candidates
					.stream()
					.filter(c -> c.getConstituency().equals(v.getConstituency()))
					.collect(Collectors.toSet())
			);
		}
	}
	
	public static void showVotersList()
	{
		voters.forEach(v -> {
			System.out.println(v);
		});
	}
	
	public static void voteMethod()
	{
		int idx = 1;
		for(Voter v : voters) 
		{
			System.out.println("VOTER NO. " + idx);
			v.votingSimulation();
			idx++;
			
		};
		
	
	}
	

}
