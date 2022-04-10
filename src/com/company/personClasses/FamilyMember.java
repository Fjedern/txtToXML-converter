package com.company.personClasses;

public class FamilyMember {
    private final String name;
    private final String birth;
    private Address address;
    private Phone phone;

    public FamilyMember(String name, String birth) {
        this.name = name;
        this.birth = birth;
    }

    public String getName() {
        return name;
    }

    public String getBirth() {
        return birth;
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

    @Override
    public String toString() {
        return "FamilyMember{" +
                "name='" + name + '\'' +
                ", birth='" + birth + '\'' +
                ", adress=" + address +
                ", phone=" + phone +
                '}';
    }
}
