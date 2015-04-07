package com.tedneward.example;

import java.beans.*;
import java.util.*;

public class Person implements Comparable<Person> {
  private int age;
  private String name;
  private double salary;
  private String ssn;
  private boolean propertyChangeFired = false;
  protected static int count;

  public static class AgeComparator implements Comparator<Person> {
    public int compare(Person p1, Person p2) {
      return p1.getAge() - p2.getAge();
    }
  }

  public Person() {
    this("", 0, 0.0d);
    this.count++;
  }
  
  public Person(String n, int a, double s) {
    this.name = n;
    this.age = a;
    this.salary = s;
    this.count++;
  }

  public int count() {
    return this.count;
  }
  public void setName(String name) {
    if (name == null) {
      throw new IllegalArgumentException();
    } else {
      this.name = name;
    }
  }

  public void setAge(int age) {
    if (age < 0) {
      throw new IllegalArgumentException();
    } else {
      this.age = age;
    }
  }

  public void setSalary(double salary) {
    this.salary = salary;
  }

  public int getAge() {
    return this.age;
  }
  
  public String getName() {
    return this.name;
  }
  
  public double getSalary() {
    return this.salary;
  }
  
  public String getSSN() {
    return this.ssn;
  }
  public void setSSN(String value) {
    String old = this.ssn;
    this.ssn = value;
    
    this.pcs.firePropertyChange("ssn", old, value);
    propertyChangeFired = true;
  }
  public boolean getPropertyChangeFired() {
    return propertyChangeFired;
  }

  public double calculateBonus() {
    return this.salary * 1.10;
  }
  
  public String becomeJudge() {
    return "The Honorable " + this.name;
  }
  
  public int timeWarp() {
    return this.age + 10;
  }
  
  public boolean equals(Object other) {
    if (other == null || !(other instanceof Person)) {
      return false;
    } else if (name.equals(((Person)other).getName()) && age == ((Person)other).getAge()) {
      return true;
    } else {
      return false;
    }
  }

  @Override
  public String toString() {
    return "[Person name:" + this.name + " age:" + this.age + " salary:" + this.salary + "]";
  }

  public static ArrayList<Person> getNewardFamily() {
    ArrayList<Person> family = new ArrayList<Person>();
    family.add(new Person("Ted", 41, 250000));
    family.add(new Person("Charlotte", 43, 150000));
    family.add(new Person("Michael", 22, 10000));
    family.add(new Person("Matthew", 15, 0));
    return family;
  }

  @Override
  public int compareTo(Person other) {
    if (other.getSalary() > this.getSalary()) {
      return 1;
    } else if (this.getSalary() > other.getSalary()) {
      return -1;
    } else {
      return 0;
    }
  }

  // PropertyChangeListener support; you shouldn't need to change any of
  // these two methods or the field
  //
  private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
  public void addPropertyChangeListener(PropertyChangeListener listener) {
      this.pcs.addPropertyChangeListener(listener);
  }
  public void removePropertyChangeListener(PropertyChangeListener listener) {
      this.pcs.removePropertyChangeListener(listener);
  }
}
