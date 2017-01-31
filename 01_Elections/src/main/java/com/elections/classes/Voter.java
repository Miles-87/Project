package com.elections.classes;

import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class Voter {
	private int voterId;
	private Constituency constituency;
	private Set<Candidate> candidates = new TreeSet<>((c1, c2) -> {
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
	
	public Voter() {
		// TODO Auto-generated constructor stub
	}
	
	public Voter(int voterId, Constituency constituency) {
		super();
		this.voterId = voterId;
		this.constituency = constituency;
	}
	public int getVoterId() {
		return voterId;
	}
	public void setVoterId(int voterId) {
		this.voterId = voterId;
	}
	public Constituency getConstituency() {
		return constituency;
	}
	public void setConstituency(Constituency constituency) {
		this.constituency = constituency;
	}
	public Set<Candidate> getCandidates() {
		return candidates;
	}
	public void setCandidates(Set<Candidate> candidates) {
		this.candidates = candidates;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((candidates == null) ? 0 : candidates.hashCode());
		result = prime * result + ((constituency == null) ? 0 : constituency.hashCode());
		result = prime * result + voterId;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Voter other = (Voter) obj;
		if (candidates == null) {
			if (other.candidates != null)
				return false;
		} else if (!candidates.equals(other.candidates))
			return false;
		if (constituency != other.constituency)
			return false;
		if (voterId != other.voterId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Voter [voterId=" + voterId + ", constituency=" + constituency + ", candidates=" + candidates + "]";
	}
	public void votingSimulation(){
		if (candidates != null && !candidates.isEmpty())
		{
			int idx = 1;
			for (Candidate c : candidates)
			{
				System.out.println(idx + ". " + c.getFirstName() + " " + c.getLastName());
				++idx;
			}
			
			int number = 0;
			do
			{
				System.out.println("Chose candidate number (1-" + candidates.size() + ")");
				Scanner sc = new Scanner(System.in);
				number = sc.nextInt();
			}
			while(number > candidates.size()|| number <=0);
			
			idx = 1;
			for (Candidate c : candidates)
			{
				if (idx == number)
				{
					c.setVotes(c.getVotes() + 1);
					break;
				}
				++idx;
			}
		}
		else
		{
			System.out.println("Candidate list is empty");
		}
		
		
	}
	
	
}
