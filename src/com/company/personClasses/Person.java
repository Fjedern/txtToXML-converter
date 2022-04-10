package com.company.personClasses;

import java.util.ArrayList;

public class Person {
    private final String firstname;
    private final String lastname;
    private Address address;
    private Phone phone;
    private ArrayList<FamilyMember> familyMembers = new ArrayList<>();

    public Person(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Phone getPhone() {
        return phone;
    }

    public void setPhone(Phone phone) {
        this.phone = phone;
    }

    public ArrayList<FamilyMember> getFamilyMembers() {
        return familyMembers;
    }

    public void addFamilyMember(FamilyMember familyMember){
        familyMembers.add(familyMember);
    }

    @Override
    public String toString() {
        return "Person{" +
                "firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", adress=" + address +
                ", phone=" + phone +
                ", FamilyMembers=" + familyMembers +
                '}';
    }
}
