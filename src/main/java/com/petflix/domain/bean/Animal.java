package com.petflix.domain.bean;

public record Animal(int id, String name, String type, int age, String presentationUrl, Member managingMember) {
}

