package com.petflix.domain.port;

import com.petflix.domain.bean.Adopter;

import java.util.List;
import java.util.Set;

public interface AdopterPort {

	Adopter getAdopterById(int id);

	List<Adopter> getAdoptersByIds(Set<Integer> ids);

}
