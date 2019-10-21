package net.ivanvega.mibasedatosp77a;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.Date;

public  class Contacto implements Serializable {
    int id;
    String usuario;
            String email;
            String tel;
            String fecNac;

    public Contacto(int id, String usuario, String email, String tel, String fecNac) {
        this.id = id;
        this.usuario = usuario;
        this.email = email;
        this.tel = tel;
        this.fecNac = fecNac;
    }

    //protected Contacto(Parcel in) {
    //    id = in.readInt();
    //    usuario = in.readString();
    //    email = in.readString();
    //    tel = in.readString();
    //}

    //public static final Creator<Contacto> CREATOR = new Creator<Contacto>() {
    //    @Override
    //    public Contacto createFromParcel(Parcel in) {
    //        return new Contacto(in);
    //    }

    //    @Override
    //    public Contacto[] newArray(int size) {
    //        return new Contacto[size];
    //    }
    //};

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getFecNac() {
        return fecNac;
    }

    public void setFecNac(String fecNac) {
        this.fecNac = fecNac;
    }

    public String toString(){
        return "Contacto No° " + id + "\n" + usuario + "\n" + email + "\n" + fecNac;
    }

    //@Override
    //public int describeContents() {
    //    return 0;
    //}

    //@Override
    //public void writeToParcel(Parcel parcel, int i) {
    //    parcel.writeInt(id);
    //    parcel.writeString(usuario);
    //    parcel.writeString(email);
    //    parcel.writeString(tel);
    //}
}
