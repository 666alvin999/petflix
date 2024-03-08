package com.petflix.domain.port;

import com.petflix.domain.bean.Adoption;

import java.util.List;

public interface AdoptionPort {

	List<Adoption> getAllAdoptions();

	Adoption getAdoptionById(int id);

}
