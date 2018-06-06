package com.example.android.jinarya.Objects;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Aadhyamo on 07/01/18.
 */

public class Name implements Parcelable {

    private String name;
    private String age;
    private String gender;

    public Name( String name, String age, String gender ) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    protected Name(Parcel in) {
        name = in.readString();
        age = in.readString();
        gender = in.readString();
    }

    public static final Creator<Name> CREATOR = new Creator<Name>() {
        @Override
        public Name createFromParcel(Parcel in) {
            return new Name(in);
        }

        @Override
        public Name[] newArray(int size) {
            return new Name[size];
        }
    };

    public String getName() {
        return name;
    }

    public String getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    //checks if two name objects are equal
    @Override
    public boolean equals(Object obj) {

        if (obj instanceof Name) {
            Name temp = (Name) obj;
            return this.name.equals(temp.name) && this.age.equals(temp.age) && this.gender.equals(temp.gender);
        } else {
            return false;
        }
    }

    //used for removing duplicates
    @Override
    public int hashCode() {
        return (this.name.hashCode() + this.age.hashCode() + this.gender.hashCode());
    }

    //TODO delete gender mention after rigorous testing and before launch
    //makes sure the listView object content is in human friendly readable form
    @Override
    public String toString() {
        return name + ", age: " + age + ", gender: " + gender;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(age);
        dest.writeString(gender);
    }
}
