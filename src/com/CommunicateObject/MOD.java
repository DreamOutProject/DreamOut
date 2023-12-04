package com.CommunicateObject;

import java.io.Serializable;
import java.util.Objects;

public class MOD implements Serializable {
    MODE mod;
    public MOD(MODE mod){this.mod = mod;}
    public MOD(){this.mod = null;}
    public void setMod(MODE mod) {this.mod = mod;}
    public MODE getMOD(){return this.mod;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MOD mod1)) return false;
        return mod == mod1.mod;
    }

    @Override
    public int hashCode() {
        return Objects.hash(mod);
    }
}
