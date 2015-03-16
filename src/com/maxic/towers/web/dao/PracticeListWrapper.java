package com.maxic.towers.web.dao;

import java.util.ArrayList;
import java.util.List;

public class PracticeListWrapper {

	private List<Practice> practiceList;
	
	public PracticeListWrapper() {
		this.practiceList = new ArrayList<Practice>();
	}
	
	public List<Practice> getPracticeList() {
        return practiceList;
    }

    public void setPracticeList(List<Practice> practiceList) {
        this.practiceList = practiceList;
    }

    public void add(Practice practice) {
        this.practiceList.add(practice);
    }

	public int size() {
		
		return practiceList.size();
	}
}
