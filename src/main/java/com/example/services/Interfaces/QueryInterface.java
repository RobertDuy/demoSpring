package com.example.services.Interfaces;

import java.util.List;

import com.example.criteria.Criteria;

public interface QueryInterface<T, V extends Criteria> {
	public T getEntity(String sessionToken, V criteria);
	public List<T> getListEntity(String sessionToken,V criteria);
	public int getCount(String sessionToken, V criteria);
}
