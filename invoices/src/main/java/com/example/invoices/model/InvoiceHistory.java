package com.example.invoices.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vladmihalcea.hibernate.type.array.IntArrayType;
import com.vladmihalcea.hibernate.type.array.StringArrayType;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import com.vladmihalcea.hibernate.type.json.JsonStringType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "invoice_history")
@TypeDefs({
        @TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
})
public class InvoiceHistory {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    @Column(name = "updated_date")
    private Timestamp updatedDate;

    @Column(name = "invoice_obj")
    private String invoiceHistory;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "invoice_id")
    private Invoice invoice;
    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    public InvoiceHistory() {
    }

    public InvoiceHistory(Timestamp updatedDate, Invoice invoiceHistory , String invoiceHistoryStr, Employee employee){
    this.updatedDate = updatedDate;
    this.invoice = invoiceHistory;
    this.invoiceHistory = invoiceHistoryStr;
    this.employee = employee;
}

    public Timestamp getUpdatedDate() {
        return updatedDate;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public Employee getEmployee() {
        return employee;
    }

    public String getInvoiceHistory() {
        return invoiceHistory;
    }
}
