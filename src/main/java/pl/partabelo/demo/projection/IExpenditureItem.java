package pl.partabelo.demo.projection;

import java.time.LocalDateTime;

public interface IExpenditureItem {
    String getItemName();
    Double getCost();
    String getDescription();
    LocalDateTime getCreateTime();
}
