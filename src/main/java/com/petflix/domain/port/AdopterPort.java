package com.petflix.domain.port;

import com.petflix.domain.bean.ActionSuccess;
import com.petflix.domain.bean.Adopter;

import java.util.List;
import java.util.Set;

public interface AdopterPort {

	List<Adopter> getAllAdopters();

	List<Adopter> getAdoptersByIds(Set<Integer> ids);

	ActionSuccess createAdopter(Adopter adopter);

}
