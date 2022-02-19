package com.example.mongodb;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class PersonUpdate {
  @NotBlank
  private String name;
  @NotNull
  private Integer age;

  public PersonUpdate() {
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getAge() {
    return age;
  }

  public void setAge(Integer age) {
    this.age = age;
  }

  @Override
  public String toString() {
    return "PersonUpdate{" + "name='" + name + '\'' + ", age=" + age + '}';
  }
}
