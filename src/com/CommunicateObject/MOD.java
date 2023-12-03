package com.CommunicateObject;

import java.io.Serializable;

public class MOD implements Serializable {
    MODE mod;
    public MOD(MODE mod){this.mod = mod;}
    public MOD(){this.mod = null;}
    public void setMod(MODE mod) {this.mod = mod;}
    public MODE getMOD(){return this.mod;}
}
