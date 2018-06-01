package ru.babudzhi.person;

public class Person {
    private String name1 = "";
    private String name2 = "";
    private String name3 = "";

    public Person getPerson(){
          return this;
    }

    public String getName3() {return this.name3;}
    public String getName1() {return this.name1;}
    public String getName2() {return this.name2;}

    public void setName1(String name1) {this.name1 = name1;}
    public void setName2(String name2) {this.name2 = name2;}
    public void setName3(String name3) {this.name3 = name3;}

    public void setPerson(String name1_, String name2_, String name3_){
        this.name3 = name3_;
        this.name1 = name1_;
        this.name2 = name2_;
    }
}
