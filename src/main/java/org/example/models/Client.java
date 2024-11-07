package org.example.models;

public class Client {

    int clientId;
    String name;
    String phoneNumber;
    String addressLine1;
    String addressLine2;
    String addressCity;
    String addressProvince;
    String addressRegion;
    String addressCountry;
    String addressPostCode;

    public Client(String addressPostCode, String addressCountry,
                  String addressRegion, String addressProvince,
                  String addressCity,
                  String addressLine2, String addressLine1, String phoneNumber,
                  String name, int clientId) {
        this.addressPostCode = addressPostCode;
        this.addressCountry = addressCountry;
        this.addressRegion = addressRegion;
        this.addressProvince = addressProvince;
        this.addressCity = addressCity;
        this.addressLine2 = addressLine2;
        this.addressLine1 = addressLine1;
        this.phoneNumber = phoneNumber;
        this.name = name;
        this.clientId = clientId;
    }
}
