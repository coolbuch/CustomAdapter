package com.example.customadapterdemo;

import java.io.Serializable;
import java.util.Comparator;

public class User implements Comparable<User> {
    String name, phone;
    Sex sex;

    public User(String name, String phoneNumber, Sex sex) {
        this.name = name;
        this.phone = phoneNumber;
        this.sex = sex;
    }

    @Override
    public int compareTo(User o)
    {
       return this.name.compareTo(o.name);
    }
}

class UserPhoneComparator implements Comparator<User>
{

    @Override
    public int compare(User o1, User o2)
    {
       return o1.phone.compareTo(o2.phone);
    }
}

class UserSexComparator implements Comparator<User>
{

    @Override
    public int compare(User o1, User o2)
    {
        return o1.sex.compareTo(o2.sex);
    }
}
