/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

public class Payment {
    int trxnid;
    int pid;
    String pname;
    String cusname;
    int quantity;
    String bkashno;
    double amount;

    public Payment(int trxnid, int pid, String pname, String cusname, int quantity, String bkashno, double amount) {
        this.trxnid = trxnid;
        this.pid = pid;
        this.pname = pname;
        this.cusname = cusname;
        this.quantity = quantity;
        this.bkashno = bkashno;
        this.amount = amount;
    }

    public int getTrxnid() {
        return trxnid;
    }

    public void setTrxnid(int trxnid) {
        this.trxnid = trxnid;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getCusname() {
        return cusname;
    }

    public void setCusname(String cusname) {
        this.cusname = cusname;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getBkashno() {
        return bkashno;
    }

    public void setBkashno(String bkashno) {
        this.bkashno = bkashno;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
    
}
