package mx.com.kubo.services;

import mx.com.kubo.model.TempPassword;
import mx.com.kubo.model.TempPasswordPK;

public interface TempPasswordService {
 public abstract TempPassword getTempPassByID(TempPasswordPK tempPK);
 public abstract boolean addNewTempPass(TempPassword newTempPass);
 public abstract boolean updateTempPass(TempPassword tempass);
 public abstract TempPassword getTempPassByPass(String pass);
}
