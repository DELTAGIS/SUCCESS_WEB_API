package com.deltagis.success.domain.entities.project;

import com.deltagis.success.domain.entities.workspace.Workspace;
import com.deltagis.success.infrastructure.schema.Property;
import jakarta.persistence.*;
import lombok.*;

import java.util.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private UUID uid;

    @OneToOne(cascade = {CascadeType.ALL}, optional = true)
    private ProjectSetting settings;

    @Enumerated(EnumType.STRING)
    private ProjectType type;

    private String projectNumber;

    @Property(required = Property.Value.TRUE)
    @Column
    protected Date startDate;

    @Property(required = Property.Value.TRUE)
    @Column
    protected float duration;

    @Property(required = Property.Value.TRUE)
    @Column
    protected Currency localCurrency = Currency.getInstance("XOF");

    // TODO : @Convert(converter = CurrencyConverter.class)
    protected Set<Currency> otherCurrencies = new HashSet<>();

    @Enumerated(EnumType.STRING)
    private AccountingSystem accountingSystem = AccountingSystem.OTHER;

    @Column
    private boolean costabUse = false;

    @Column
    private boolean budgetTrackingUse = false;

    @Column
    private boolean sigfipUse = false;

    @Column
    private boolean paymentOrderUse = false;

    @Column
    private boolean wasfUse = false;

    @Column
    private boolean taxAccountingUse;

    @ManyToOne
    private Workspace workspace;

    public Set<Currency> getCurrencies() {
        Set<Currency> currencies = new HashSet<>();
        currencies.add(this.localCurrency);

        if (otherCurrencies != null) {
            currencies.addAll(this.otherCurrencies);
        }
        return currencies;
    }
}
