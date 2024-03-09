package com.petflix.domain.bean;

import com.petflix.domain.bean.animalfields.AnimalType;
import com.petflix.domain.bean.memberfield.MemberCity;

import java.util.List;
import java.util.Objects;

public class VideoFilters {

	private List<AnimalType> animalTypes;
	private List<MemberCity> memberCities;

	public VideoFilters(List<AnimalType> animalTypes, List<MemberCity> memberCities) {
		this.animalTypes = animalTypes;
		this.memberCities = memberCities;
	}

	public List<MemberCity> getMemberCities() {
		return memberCities;
	}

	public List<AnimalType> getAnimalTypes() {
		return animalTypes;
	}

	public void setAnimalTypes(List<AnimalType> animalTypes) {
		this.animalTypes = animalTypes;
	}

	public void setMemberCities(List<MemberCity> memberCities) {
		this.memberCities = memberCities;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		VideoFilters that = (VideoFilters) o;
		return Objects.equals(getAnimalTypes(), that.getAnimalTypes()) && Objects.equals(getMemberCities(), that.getMemberCities());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getAnimalTypes(), getMemberCities());
	}

	@Override
	public String toString() {
		return "VideoFilters{" +
			"animalTypes=" + animalTypes +
			", memberCities=" + memberCities +
			'}';
	}
}
