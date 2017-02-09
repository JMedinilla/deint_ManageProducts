package com.afg.MngProductContentProvider.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by joselu on 11/01/17.
 */

public class Invoice {

    int id;
    int idPharmacy;
    String date;
    int status;
    List<InvoiceLine> lines;

    public Invoice(int id, int idPharmacy, String date, int status, ArrayList<InvoiceLine> lines) {
        this.id = id;
        this.idPharmacy = idPharmacy;
        this.date = date;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public int getIdPharmacy() {
        return idPharmacy;
    }

    public String getDate() {
        return date;
    }

    public int getStatus() {
        return status;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIdPharmacy(int idPharmacy) {
        this.idPharmacy = idPharmacy;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<InvoiceLine> getLines() {
        return lines;
    }

    public void setLines(List<InvoiceLine> lines) {
        this.lines = lines;
    }
}
